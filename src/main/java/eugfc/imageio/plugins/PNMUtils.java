
package eugfc.imageio.plugins;

public class PNMUtils {

    public static final String BASE_CLASS_PATH = "eugfc.imageio.plugins";
    
    private static final String[] ASCII_FORMAT  = {"P1", "P2", "P3"};
    private static final String[] BINARY_FORMAT = {"P4", "P5", "P6"};
    
    public static final boolean isAsciiFormat(String magicNumber) {
        return isFormat(magicNumber, ASCII_FORMAT);
    }

    public static final boolean isBinaryFormat(String magicNumber) {
        return isFormat(magicNumber, BINARY_FORMAT);
    }
    
    private static boolean isFormat(String magicNumber, String[] formats) {
        for (String mn : formats) {
            if (magicNumber.equals(mn)) {
                return true;
            }
        }
        return false;
    }
}
