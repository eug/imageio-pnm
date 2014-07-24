
package eugfc.imageio.plugins;

import java.awt.image.BufferedImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.spi.ImageWriterSpi;

public abstract class AbstractImageWriterSpi extends ImageWriterSpi {

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

    public AbstractImageWriterSpi(String vendorName, String[] names, String[] suffixes,
                                  String[] MIMETypes, String writerClassName, String[] readerSpiNames) {
        
        super(vendorName, _version, names, suffixes, MIMETypes,
              writerClassName, STANDARD_OUTPUT_TYPE, readerSpiNames,
              _supportsStandardStreamMetadataFormat, _nativeStreamMetadataFormatName,
              _nativeStreamMetadataFormatClassName, _extraStreamMetadataFormatNames,
              _extraStreamMetadataFormatClassNames, _supportsStandardImageMetadataFormat,
              _nativeImageMetadataFormatName, _nativeImageMetadataFormatClassName,
              _extraImageMetadataFormatNames, _extraImageMetadataFormatClassNames);
    }
    
    
    
    @Override
    public boolean canEncodeImage(ImageTypeSpecifier type) {
        return type.equals(ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB));
    }

}
