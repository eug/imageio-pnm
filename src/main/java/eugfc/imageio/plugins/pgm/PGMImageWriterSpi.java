package eugfc.imageio.plugins.pgm;

import eugfc.imageio.plugins.AbstractImageWriterSpi;
import eugfc.imageio.plugins.PNMUtils;
import java.util.Locale;
import java.io.IOException;
import javax.imageio.ImageWriter;

public class PGMImageWriterSpi extends AbstractImageWriterSpi {

    private static final String   _vendorName = "Portable Gray Map";
    
    private static final String   _writerClassName =  PNMUtils.BASE_CLASS_PATH + ".pgm.PGMImageWriter";
    private static final String[] _readerSpiNames  = {PNMUtils.BASE_CLASS_PATH + ".pgm.PGMImageReaderSpi"};
    private static final String[] _names     = {"pgm", "pnm"};
    private static final String[] _suffixes  = {"pgm", "pnm"};
    private static final String[] _MIMETypes = { 
        "image/x-portable-graymap",
        "image/x-portable-anymap"
    };
    
    public PGMImageWriterSpi() {
        super(_vendorName, _names,
              _suffixes, _MIMETypes,
              _writerClassName,
              _readerSpiNames);
    }

    @Override
    public ImageWriter createWriterInstance(Object extension) throws IOException {
        return new PGMImageWriter(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Portable Gray Map"; 
    }

}
