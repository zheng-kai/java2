package component;

public abstract class Mainboard extends Goods implements Component {
    protected float speed;

    public Mainboard(String name, float price, float speed) {
        super(name, price);
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Mainboard{" +
                "speed=" + speed +" GHz"+
                ", name='" + name + '\'' +
                ", price=" + price + " RMB" +
                '}';
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public void work() {
        System.out.println(name + " work");
    }
}
