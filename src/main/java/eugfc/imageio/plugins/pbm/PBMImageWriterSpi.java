
package eugfc.imageio.plugins.pbm;

import eugfc.imageio.plugins.AbstractImageWriterSpi;
import eugfc.imageio.plugins.PNMUtils;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageWriter;

public class PBMImageWriterSpi extends AbstractImageWriterSpi {

    private static final String   _vendorName = "Portable Bit Map";
    
    private static final String   _writerClassName =  PNMUtils.BASE_CLASS_PATH + ".pbm.PBMImageWriter";
    private static final String[] _readerSpiNames  = {PNMUtils.BASE_CLASS_PATH + ".pbm.PBMImageReaderSpi"};
    private static final String[] _names     = {"pbm", "pnm"};
    private static final String[] _suffixes  = {"pbm", "pnm"};
    private static final String[] _MIMETypes = { 
        "image/x-portable-bitmap",
        "image/x-portable-anymap"
    };
    
    public PBMImageWriterSpi() {
        super(_vendorName, _names,
              _suffixes, _MIMETypes,
              _writerClassName,
              _readerSpiNames);
    }

    @Override
    public ImageWriter createWriterInstance(Object extension) throws IOException {
        return new PBMImageWriter(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Portable Bit Map"; 
    }
    
}
