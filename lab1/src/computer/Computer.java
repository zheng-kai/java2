package computer;

import component.*;

public class Computer {
    private String name;
    private CPU cpu;
    private Disk disk;
    private Mainboard mainboard;
    private Memory memory;
    public Computer(String name,CPU cpu, Disk disk, Mainboard mainboard, Memory memory){
        this.name = name;
        this.cpu = cpu;
        this.disk = disk;
        this.mainboard = mainboard;
        this.memory = memory;
    }
    public String getCPUName(){
        return cpu.getName();
    }
    public String getDiskName(){
        return disk.getName();
    }
    public String getMainboardName(){
        return mainboard.getName();
    }
    public String getMemoryName(){
        return memory.getName();
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    public void setMainboard(Mainboard mainboard) {
        this.mainboard = mainboard;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public void work(){
        cpu.work();
        disk.work();
        mainboard.work();
        memory.work();
    }
    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", cpu=" + cpu +
                ", disk=" + disk +
                ", mainboard=" + mainboard +
                ", memory=" + memory +
                '}';
    }

    public String getName() {
        return name;
    }
    public float getPrice(){
        float price = 0;
        if(cpu != null){
            price += cpu.getPrice();
        }
        if(disk != null){
            price += disk.getPrice();
        }
        if(mainboard != null){
            price += mainboard.getPrice();
        }
        if(memory != null){
            price += memory.getPrice();
        }
        return price;
    }
}
