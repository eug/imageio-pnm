
package eugfc.imageio.plugins;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

public abstract class AbstractImageReader extends ImageReader {

    private int height;
    private int width;
    private int maxValue;
    private String magicNumber;
    private final List<String> comments;

    protected ImageInputStream stream;

    public AbstractImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
        comments = new ArrayList<>();
    }

    @Override
    public int getNumImages(boolean allowSearch) throws IOException {
        return 1;
    }

    @Override
    public int getWidth(int imageIndex) {
        if (imageIndex != 0) {
            throw new IndexOutOfBoundsException("imageIndex != 0");
        }
        return width;
    }

    @Override
    public int getHeight(int imageIndex) {
        if (imageIndex != 0) {
            throw new IndexOutOfBoundsException("imageIndex != 0");
        }
        return height;
    }

    @Override
    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    @Override
    public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
        return null;
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException {
        ArrayList<ImageTypeSpecifier> imageTypes = new ArrayList<>();
        imageTypes.add(
            ImageTypeSpecifier.createFromBufferedImageType(
                BufferedImage.TYPE_INT_ARGB)
        );
        return imageTypes.iterator();
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {

        if (!(getInput() instanceof ImageInputStream)) {
            return null;
        }

        stream = (ImageInputStream) getInput();

        // Clear any previous abort request
        clearAbortRequest();

        // Inform IIOReadProgressListeners of the start of the image
        processImageStarted(imageIndex);

        readHeader();

        final int bufferedImageType = getImageTypes(0).next().getBufferedImageType();
        final PNMBufferedImage bi = new PNMBufferedImage(width, height, bufferedImageType);
        final PNMDataTransferObject dto = bi.getDataTransferObject();

        if (PNMUtils.isAsciiFormat(magicNumber)) {
            readAsciiImage(stream, bi);
        } else if (PNMUtils.isBinaryFormat(magicNumber)) {
            readBinaryImage(stream, bi);
        }

        dto.setMagicNumber(magicNumber);
        dto.setMaxGrayValue(maxValue);

        for (String comment : comments) {
            dto.addComment(comment);
        }

        if (abortRequested()) {
            processReadAborted();
        } else {
            processImageComplete();
        }

        return bi;
    }

    protected void readHeader() {
        readMagicNumber();
        readDimension();
    }

    protected final void readMagicNumber() {
        try {
            // first an array with the length of 3 is defined
            // after, only two positions are used,
            // that's beacuse the last char (space or LF) is ignored
            final byte[] b = new byte[3];
            stream.readFully(b);
            magicNumber = new String(b, 0, 2);
        } catch (IOException ex) {
        }
    }

    protected final void readDimension() {
        if (this.width == 0 && this.height == 0) {
            final String[] splited = readLine().split(" ");

            width = Integer.parseInt(splited[0].trim());
            height = Integer.parseInt(splited[1].trim());

            // eventually all header values are inline,
            // in this case, the maxValue is defined
            if (splited.length > 2) {
                maxValue = Integer.parseInt(splited[2].trim());
            }
        }
    }

    protected final void readMaxValue() {
        if (maxValue == 0) {
            maxValue = Integer.parseInt(readLine());
        }
    }

    private final String readLine() {
        String line = null;

        while (true) {

            try {
                line = stream.readLine();
            } catch (IOException ex) {
            }

            // is EOF ?
            if (line == null) {
                return null;
            }

            // Replaces multiple spaces
            line = line.trim().replaceAll(" +", " ");

            // skip empty lines
            if (line.isEmpty()) {
                continue;
            }

            // If a commentary line was found, just add to comments array
            if (line.startsWith("#")) {
                comments.add(line);
            } else {
                return line;
            }
        }
    }

    private void readBinaryImage(ImageInputStream stream, PNMBufferedImage bi) throws IOException {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                
                // Update reading progress
                final float progress = ((width * i + j) * 100F) / (width * height);
                processImageProgress(progress);

                // Check for an asynchronous abort request
                if (abortRequested()) {
                    return;
                }
                
                do {
                    byteToBuffer(stream.readUnsignedByte());
                } while (!isBufferReady());
                
                bi.setRGB(j, i, getBufferValue());
            }
        }
    }

    private void readAsciiImage(ImageInputStream stream, PNMBufferedImage bi) {
        int i = 0, j = 0;
        String line = null;
        
        while ((line = readLine()) != null) {
            
            // Update the percentage estimate
            final float progress = ((width * i + j) * 100F) / (width * height);
            processImageProgress(progress);

            // Check for an asynchronous abort request
            if (abortRequested()) {
                return;
            }
            
            for (String value : line.split(" ")) {
                
                byteToBuffer(Integer.parseInt(value));
                
                if (isBufferReady()) {
                    bi.setRGB(j, i, getBufferValue());
                    j = ++j % width;
                    i = (j == 0) ? ++i % height : i;
                }

            }
        }
    }
    
    protected abstract void byteToBuffer(int px);
    
    protected abstract boolean isBufferReady();
    
    protected abstract int getBufferValue();

}
