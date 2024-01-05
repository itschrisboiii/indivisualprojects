package org.project.model;

public class Service {

    private int id;
    private String serviceName;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service {" +
                "id=" + id +
                "serviceName=" + serviceName +
                "price=" + price +
                "}";
    }
}
