
package eugfc.imageio.plugins;

import java.io.IOException;
import java.util.List;
import java.awt.image.DataBufferInt;
import java.awt.image.RenderedImage;
import javax.imageio.IIOImage;
import javax.imageio.ImageWriter;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

public abstract class AbstractImageWriter extends ImageWriter {

    protected ImageOutputStream stream;
    
    public AbstractImageWriter(ImageWriterSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType,
                                            ImageWriteParam param) {
        return null;
    }

    @Override
    public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
        return null;
    }

    @Override
    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
        return null;
    }

    @Override
    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
        return null;
    }

    @Override
    public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws
        IOException {
        if (!(output instanceof ImageOutputStream)) {
            return;
        }

        final PNMDataTransferObject dto;
        if (image.getRenderedImage() instanceof PNMBufferedImage) {
            dto = ((PNMBufferedImage) image.getRenderedImage()).getDataTransferObject();
        } else {
            dto = new PNMDataTransferObject();
        }
        
        stream = (ImageOutputStream) output;
        final RenderedImage renderedImage = image.getRenderedImage();
        final int width  = renderedImage.getWidth();
        final int height = renderedImage.getHeight();
        final int[] pixels = ((DataBufferInt) renderedImage.getData().getDataBuffer()).getData();
        
        clearAbortRequest();

        processImageStarted(0);

        writeHeader(dto, width, height);

        if (PNMUtils.isAsciiFormat(dto.getMagicNumber())) {
            writeAsciiImage(pixels, width, height);
        } else if (PNMUtils.isBinaryFormat(dto.getMagicNumber())) {
            writeBinaryImage(pixels, width, height);
        }
        
        if (abortRequested()) {
            processWriteAborted();
        } else {
            processImageComplete();
        }
    }
   
    private void writeAsciiImage(int[] pixels, int width, int height) throws IOException {
        
        for (int i = 0, idx = 0, cc = 0; i < height; i++) {
            for (int j = 0; j < width; j++, idx = (width * i + j)) {

                processImageProgress(idx * 100F / (width * height));

                String strValue = argbToAscii(pixels[idx]);

                cc += strValue.length() + 1;

                if (cc >= 70) {
                    write("\n");
                    cc = strValue.length() + 1;
                }
                
                write(strValue);
            }
        }
    }

    private void writeBinaryImage(int[] pixels, int width, int height) throws IOException {

        for (int i = 0, idx = 0; i < height; i++) {
            
            for (int j = 0; j < width; j++, idx = (width * i + j)) {
                
                processImageProgress(idx * 100F / (width * height));
                
                argbToBinary(pixels[idx]);
            }
        }
        
    }
    
    protected void writeHeader(PNMDataTransferObject dto, int width, int height) throws IOException {
        writeMagicNumber(dto.getMagicNumber());
        writeComments(dto.getComments());        
        writeDimension(width, height);
    }

    protected void writeMaxValue(int maxValue) {
        write(maxValue + "\n");
    }

    protected void writeDimension(int width, int height) {
        write(width + " " + height + '\n');
    }

    protected void writeComments(List<String> comments) {
        for (String comment : comments) {
            write(comment + '\n');
        }
    }

    protected void writeMagicNumber(String magicNumber) {
        write(magicNumber + '\n');
    }

    private void write(String s) {
        try {
            stream.write(s.getBytes("UTF-8"));
        } catch (IOException ex) {}
    }
    
    protected abstract String argbToAscii(int px);
    
    protected abstract void argbToBinary(int px);
}
