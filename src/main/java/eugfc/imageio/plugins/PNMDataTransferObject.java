
package eugfc.imageio.plugins;

import java.util.ArrayList;
import java.util.List;

public class PNMDataTransferObject {
    
    private int maxGrayValue;
    private String magicNumber;
    private final List<String> comments;

    public PNMDataTransferObject() {
        this.maxGrayValue = 0xFF;
        this.magicNumber = "";
        this.comments = new ArrayList<>();
    }

    public int getMaxValue() {
        return maxGrayValue;
    }

    public void setMaxGrayValue(int maxGrayValue) {
        this.maxGrayValue = maxGrayValue;
    }

    public String getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }
    
}
