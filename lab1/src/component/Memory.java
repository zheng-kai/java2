package component;


public abstract class Memory extends Goods implements Component {
    protected int volume;

    public Memory(String name, float price, int volume) {
        super(name, price);
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "volume=" + volume +" GB"+
                ", name='" + name + '\'' +
                ", price=" + price + " RMB" +
                '}';
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public void work() {
        System.out.println(name + " work");
    }
}
