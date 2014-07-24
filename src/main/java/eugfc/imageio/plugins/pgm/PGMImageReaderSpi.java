
package eugfc.imageio.plugins.pgm;

import eugfc.imageio.plugins.AbstractImageReaderSpi;
import eugfc.imageio.plugins.PNMUtils;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageReader;

public class PGMImageReaderSpi extends AbstractImageReaderSpi {
    
    private static final String[] MAGIC_NUMBER = {"P2", "P5"};
    
    private static final String   _vendorName = "Portable Gray Map";
    private static final String   _readerClassName =   PNMUtils.BASE_CLASS_PATH + ".pgm.PGMImageReader";
    private static final String[] _writerSpiNames  = { PNMUtils.BASE_CLASS_PATH + ".pgm.PGMImageWriterSpi" };
    
    private static final String[] _names     = {"pgm", "pnm"};
    private static final String[] _suffixes  = {"pgm", "pnm"};
    private static final String[] _MIMETypes = {
        "image/x-portable-graymap",
        "image/x-portable-anymap"
    };

    public PGMImageReaderSpi() {
        super(_vendorName, _names,
              _suffixes, _MIMETypes,
              _readerClassName,
              _writerSpiNames);
    }

    @Override
    public boolean canDecodeInput(Object source) throws IOException {
        return canDecodeInput(source, MAGIC_NUMBER);
    }

    @Override
    public ImageReader createReaderInstance(Object extension) throws IOException {
        return new PGMImageReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Portable Gray Map";
    }

}
