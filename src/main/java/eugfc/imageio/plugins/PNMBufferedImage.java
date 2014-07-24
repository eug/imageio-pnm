
package eugfc.imageio.plugins;

import java.awt.image.BufferedImage;

public class PNMBufferedImage extends BufferedImage {

    private final PNMDataTransferObject dto;
    
    public PNMBufferedImage(int width, int height, int imageType) {
        super(width, height, imageType);
        this.dto = new PNMDataTransferObject();
    }
    
    public PNMDataTransferObject getDataTransferObject() {
        return dto;
    }
    
}
