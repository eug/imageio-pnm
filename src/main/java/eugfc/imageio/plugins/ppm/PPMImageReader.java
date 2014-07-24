
package eugfc.imageio.plugins.ppm;

import eugfc.imageio.plugins.AbstractImageReader;
import javax.imageio.spi.ImageReaderSpi;

public class PPMImageReader extends AbstractImageReader {

    private int i = 0;

    private final int[] value = new int[3];

    public PPMImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    protected void readHeader() {
        super.readHeader();
        readMaxValue();
    }

    @Override
    protected void byteToBuffer(int px) {
        value[i++] = px;
    }

    @Override
    protected boolean isBufferReady() {
        return i == 3;
    }

    @Override
    protected int getBufferValue() {
        i = 0;
        final int r = value[0];
        final int g = value[1];
        final int b = value[2];
        return (0xFF << 24) | (r << 16) | (g << 8) | b;
    }

}
