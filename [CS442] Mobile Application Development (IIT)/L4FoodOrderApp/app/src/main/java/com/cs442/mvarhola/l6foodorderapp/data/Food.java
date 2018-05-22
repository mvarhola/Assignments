package com.cs442.mvarhola.l6foodorderapp.data;

public class Food {

    private int id;
    private String name;
    private String price;
    private String description;

    public Food() {
    }

    public Food(String name, String price, String description) {
        super();
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Food [id=" + id + ", name=" + name+ ", price=" + price+ ", description=" + description
                + "]";
    }
}
