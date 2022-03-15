package ru.kpfu.itis.kashapova;

public class Product {
    protected String name;
    protected String price;
    protected String url;

    public Product(String name, String price, String url) {
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return "\n#name: " + this.name +
                "\n#price: " + this.price +
                "\n#url: " + this.url + "\n";
    }
}
