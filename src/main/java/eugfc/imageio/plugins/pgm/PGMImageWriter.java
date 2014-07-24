
package eugfc.imageio.plugins.pgm;

import java.io.IOException;
import javax.imageio.spi.ImageWriterSpi;
import eugfc.imageio.plugins.AbstractImageWriter;
import eugfc.imageio.plugins.PNMDataTransferObject;

public class PGMImageWriter extends AbstractImageWriter {

    public PGMImageWriter(ImageWriterSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    protected void writeHeader(PNMDataTransferObject dto, int width, int height) throws IOException {
        super.writeHeader(dto, width, height);
        writeMaxValue(dto.getMaxValue());
    }
    
    private int argbToByte(int px) {
        return (0xFFFFFF << 8) ^ (px >> 16);
    }
    
    @Override
    protected String argbToAscii(int px) {
        return Integer.toString(argbToByte(px)) + " ";
    }

    @Override
    protected void argbToBinary(int px) {
        try {
            stream.writeByte(argbToByte(px));
        } catch (IOException ex) {}
    }

}
