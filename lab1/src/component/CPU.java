package component;


public abstract class CPU extends Good implements Component {
    protected int coreNum;

    public CPU(String name, float price, int coreNum) {
        super(name, price);
        this.coreNum = coreNum;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "coreNum=" + coreNum +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getCoreNum() {
        return coreNum;
    }

    @Override
    public void work() {
        System.out.println(name + " work");
    }
}
