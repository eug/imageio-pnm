
package eugfc.imageio.plugins.pbm;

import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageReader;
import eugfc.imageio.plugins.AbstractImageReaderSpi;
import eugfc.imageio.plugins.PNMUtils;

public class PBMImageReaderSpi extends AbstractImageReaderSpi {

    private static final String[] MAGIC_NUMBER = {"P1", "P4"};
    
    private static final String   _vendorName = "Portable Bit Map";
    private static final String   _readerClassName =  PNMUtils.BASE_CLASS_PATH + ".pbm.PBMImageReader";
    private static final String[] _writerSpiNames = { PNMUtils.BASE_CLASS_PATH + ".pbm.PBMImageWriterSpi" };
    
    private static final String[] _names     = {"pbm", "pnm"};
    private static final String[] _suffixes  = {"pbm", "pnm"};
    private static final String[] _MIMETypes = {
        "image/x-portable-bitmap",
        "image/x-portable-anymap"
    };
    
    public PBMImageReaderSpi() {
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
        return new PBMImageReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Portable Bit Map";
    }

}
