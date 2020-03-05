package component;

public abstract class Goods {
    protected String name;
    protected float price;

    public Goods(String name, float price) {
        this.name = name;
        if (price <= 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price <= 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }
}
