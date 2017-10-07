package test.study.select_city.bean;

/**
 * Created by Mr.Chen on 2017/8/27.
 */
public class Order {
    private String name;
    private int imageId;

    public Order(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
