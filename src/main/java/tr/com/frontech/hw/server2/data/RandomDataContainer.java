package tr.com.frontech.hw.server2.data;

/**
 * Created by Mert on 15.2.2015.
 */
public class RandomDataContainer {

    private Long randomLong;
    private String randomString;

    /**
     * default constructor
     */
    public RandomDataContainer(){

    }

    public RandomDataContainer(Long randomLong, String randomString){
        this.randomLong = randomLong;
        this.randomString = randomString;
    }

    public Long getRandomLong() {
        return randomLong;
    }

    public void setRandomLong(Long randomLong) {
        this.randomLong = randomLong;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }
}
