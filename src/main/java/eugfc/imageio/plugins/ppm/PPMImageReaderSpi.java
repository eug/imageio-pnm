
package eugfc.imageio.plugins.ppm;

import eugfc.imageio.plugins.AbstractImageReaderSpi;
import eugfc.imageio.plugins.PNMUtils;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageReader;

public class PPMImageReaderSpi extends AbstractImageReaderSpi {

    private static final String[] MAGIC_NUMBER = {"P3", "P6"};
    
    private static final String   _vendorName = "Portable Pix Map";
    private static final String   _readerClassName =  PNMUtils.BASE_CLASS_PATH + ".ppm.PPMImageReader";
    private static final String[] _writerSpiNames = { PNMUtils.BASE_CLASS_PATH + ".ppm.PPMImageWriterSpi" };
    
    private static final String[] _names     = {"ppm", "pnm"};
    private static final String[] _suffixes  = {"ppm", "pnm"};
    private static final String[] _MIMETypes = {
        "image/x-portable-pixmap",
        "image/x-portable-anymap"
    };

    public PPMImageReaderSpi() {
        super(_vendorName, _names, _suffixes, _MIMETypes, _readerClassName, _writerSpiNames);
    }

    @Override
    public boolean canDecodeInput(Object source) throws IOException {
        return canDecodeInput(source, MAGIC_NUMBER);
    }

    @Override
    public ImageReader createReaderInstance(Object extension) throws IOException {
        return new PPMImageReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Portable Pix Map";
    }

}
