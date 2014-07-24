
package eugfc.imageio.plugins.ppm;

import eugfc.imageio.plugins.AbstractImageWriterSpi;
import eugfc.imageio.plugins.PNMUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;


public class PPMImageWriterSpi extends AbstractImageWriterSpi {
    
    private static final String   _vendorName = "Portable Pixel Map";

    private static final String   _writerClassName =  PNMUtils.BASE_CLASS_PATH + ".ppm.PPMImageWriter";
    private static final String[] _readerSpiNames  = {PNMUtils.BASE_CLASS_PATH + ".ppm.PPMImageReaderSpi"};
    private static final String[] _names     = {"ppm", "pnm"};
    private static final String[] _suffixes  = {"ppm", "pnm"};
    private static final String[] _MIMETypes = { 
        "image/x-portable-pixmap",
        "image/x-portable-anymap"
    };
    
    public PPMImageWriterSpi() {
        super(_vendorName, _names,
              _suffixes, _MIMETypes,
              _writerClassName,
              _readerSpiNames);
    }

    @Override
    public ImageWriter createWriterInstance(Object extension) throws IOException {
        return new PPMImageWriter(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "Portable Pixel Map"; 
    }

}
