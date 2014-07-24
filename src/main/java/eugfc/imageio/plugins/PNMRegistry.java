
package eugfc.imageio.plugins;

import eugfc.imageio.plugins.pbm.PBMImageReaderSpi;
import eugfc.imageio.plugins.pbm.PBMImageWriterSpi;
import eugfc.imageio.plugins.pgm.PGMImageReaderSpi;
import eugfc.imageio.plugins.pgm.PGMImageWriterSpi;
import eugfc.imageio.plugins.ppm.PPMImageReaderSpi;
import eugfc.imageio.plugins.ppm.PPMImageWriterSpi;
import javax.imageio.spi.IIORegistry;

public class PNMRegistry {

    public static void registerAllServicesProviders() {
        IIORegistry.getDefaultInstance().registerServiceProvider(new PBMImageReaderSpi());
        IIORegistry.getDefaultInstance().registerServiceProvider(new PBMImageWriterSpi());
        IIORegistry.getDefaultInstance().registerServiceProvider(new PGMImageReaderSpi());
        IIORegistry.getDefaultInstance().registerServiceProvider(new PGMImageWriterSpi());
        IIORegistry.getDefaultInstance().registerServiceProvider(new PPMImageReaderSpi());
        IIORegistry.getDefaultInstance().registerServiceProvider(new PPMImageWriterSpi());
    }

}
