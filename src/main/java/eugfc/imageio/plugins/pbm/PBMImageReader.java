
package eugfc.imageio.plugins.pbm;

import eugfc.imageio.plugins.AbstractImageReader;
import javax.imageio.spi.ImageReaderSpi;

public class PBMImageReader extends AbstractImageReader {

    private int value;

    public PBMImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    protected void byteToBuffer(int px) {
        value = (px == 0) ? 0xFFFFFFFF : (0xFF << 24) | 0;
    }

    @Override
    protected boolean isBufferReady() {
        return true;
    }

    @Override
    protected int getBufferValue() {
        return value;
    }

}
