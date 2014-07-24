
package eugfc.imageio.plugins.ppm;

import java.io.IOException;
import javax.imageio.spi.ImageWriterSpi;
import eugfc.imageio.plugins.AbstractImageWriter;
import eugfc.imageio.plugins.PNMDataTransferObject;

public class PPMImageWriter extends AbstractImageWriter {

    public PPMImageWriter(ImageWriterSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    protected void writeHeader(PNMDataTransferObject dto, int width, int height) throws IOException {
        super.writeHeader(dto, width, height);
        writeMaxValue(dto.getMaxValue());
    }
    
    @Override
    protected String argbToAscii(int px) {
        final int r = (px >> 16) & 0xFF;
        final int g = (px >> 8)  & 0xFF;
        final int b = px & 0xFF;
        return r + " " + g + " " + b + " ";
    }

    @Override
    protected void argbToBinary(int px) {
        final int r = (px >> 16) & 0xFF;
        final int g = (px >>  8) & 0xFF;
        final int b = px & 0xFF;
        
        try {
            stream.writeByte(r);
            stream.writeByte(g);
            stream.writeByte(b);
        } catch (IOException ex) {}
    }

}
