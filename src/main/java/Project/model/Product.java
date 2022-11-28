package Project.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 50, message = "lớn hơn 1 và nhỏ hơn 50 ký tự")
    private String name;
@NotNull(message = "Chưa có ảnh")
    private String img;
    @Min(value = 5000, message = "Lớn hơn 5k nhé")
    private double price;
    private boolean status;

    @ManyToOne
    private Category category;


    public Product() {
    }

    public Product(Long id, String name, String img, double price, boolean status, Category category) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.status = status;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
