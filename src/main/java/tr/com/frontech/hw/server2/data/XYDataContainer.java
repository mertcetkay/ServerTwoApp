package tr.com.frontech.hw.server2.data;

/**
 * Created by Mert on 15.2.2015.
 */
public class XYDataContainer {

    private Integer x;
    private Integer y;

    /**
     * default constructor
     */
    public XYDataContainer(){

    }

    public XYDataContainer(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
