
package eugfc.imageio.plugins.pgm;

import javax.imageio.spi.ImageReaderSpi;
import eugfc.imageio.plugins.AbstractImageReader;

public class PGMImageReader extends AbstractImageReader {

    private int value;

    public PGMImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    protected void readHeader() {
        super.readHeader();
        readMaxValue();
    }

    @Override
    protected void byteToBuffer(int px) {
        value = (255 << 24) | (px << 16) | (px << 8) | px;
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
