
package eugfc.imageio.plugins.pbm;

import java.io.IOException;
import javax.imageio.spi.ImageWriterSpi;
import eugfc.imageio.plugins.AbstractImageWriter;

public class PBMImageWriter extends AbstractImageWriter {

    public PBMImageWriter(ImageWriterSpi originatingProvider) {
        super(originatingProvider);
    }

    private int argbToByte(int px) {
        return (px == 0xFFFFFFFF) ? 0 : 1;
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
