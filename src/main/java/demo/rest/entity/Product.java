package demo.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lkmc2
 * @date 2018/9/17
 * @description 产品
 */
@XmlRootElement(name = "product")
public class Product {

    private long id;
    private String name;
    private double price;

    public Product() {
    }

    public Product(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
