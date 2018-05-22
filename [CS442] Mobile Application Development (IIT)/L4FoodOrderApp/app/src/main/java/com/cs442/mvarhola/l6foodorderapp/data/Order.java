package com.cs442.mvarhola.l6foodorderapp.data;

/**
 * Created by p0rt on 10/7/17.
 */

public class Order {

    private int id;
    private String timestamp;
    private String items;
    private String total;

    public Order() {
    }

    public Order(String timestamp, String items , String total) {
        super();
        this.timestamp = timestamp;
        this.items = items;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", timestamp=" + timestamp+ ", items=" + items+ ", total=" + total
                + "]";
    }
}
