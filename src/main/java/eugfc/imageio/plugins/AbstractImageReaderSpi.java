
package eugfc.imageio.plugins;

import java.io.IOException;
import javax.imageio.spi.ImageReaderSpi;
import static javax.imageio.spi.ImageReaderSpi.STANDARD_INPUT_TYPE;
import javax.imageio.stream.ImageInputStream;

public abstract class AbstractImageReaderSpi extends ImageReaderSpi {
    
    private static final String   _version = "1.0";
    
    private static final boolean  _supportsStandardStreamMetadataFormat = false;
    private static final String   _nativeStreamMetadataFormatName       = null;
    private static final String   _nativeStreamMetadataFormatClassName  = null;
    private static final String[] _extraStreamMetadataFormatNames       = null;
    private static final String[] _extraStreamMetadataFormatClassNames  = null;
    
    private static final boolean  _supportsStandardImageMetadataFormat = false;
    private static final String   _nativeImageMetadataFormatName       = null;
    private static final String   _nativeImageMetadataFormatClassName  = null;
    private static final String[] _extraImageMetadataFormatNames       = null;
    private static final String[] _extraImageMetadataFormatClassNames  = null;

    public AbstractImageReaderSpi(String vendorName, String[] names, String[] suffixes,
                                  String[] MIMETypes, String readerClassName, String[] writerSpiNames) {
        super(vendorName, _version, names, suffixes, MIMETypes,
              readerClassName, STANDARD_INPUT_TYPE, writerSpiNames,
              _supportsStandardStreamMetadataFormat,
              _nativeStreamMetadataFormatName,
              _nativeStreamMetadataFormatClassName,
              _extraStreamMetadataFormatNames,
              _extraStreamMetadataFormatClassNames,
              _supportsStandardImageMetadataFormat,
              _nativeImageMetadataFormatName,
              _nativeImageMetadataFormatClassName,
              _extraImageMetadataFormatNames,
              _extraImageMetadataFormatClassNames);
    }

    protected boolean canDecodeInput(Object source, String[] magicNumber) throws IOException {
        if (!(source instanceof ImageInputStream)) {
            return false;
	}
        
        ImageInputStream stream = (ImageInputStream) source;
        
        byte[] b = new byte[2];
        stream.readFully(b);
        
        String mn0 = new String(b);
        for (String mn1 : magicNumber) {
            if (mn0.equals(mn1)) {
                return true;
            }
        }

        return false;
    }

}
