package main;

import component.CPU;
import component.Disk;
import component.Mainboard;
import component.Memory;
import computer.Computer;
import factory.Factory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ComputerStore {
    private static List<Computer> computers = new ArrayList<>();

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        init();
        showGoods();
        showDetail();
        showWork();
//        System.out.println(computer1);
//        System.out.println(computer2.getPrice());
//        System.out.println(computer3.getName());
//        computer1.work();
//        computer2.work();
//        computer3.work();
    }

    private static void init() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CPU cpu1 = Factory.getCPUInstance("AMD", "cpu1", 1000, 4);
        CPU cpu2 = Factory.getCPUInstance("AMD", "cpu2", 1200, 8);
        CPU cpu3 = Factory.getCPUInstance("Intel", "cpu3", 1300, 8);

        Disk disk1 = Factory.getDiskInstance("Seagate", "disk1", 300, 512);
        Disk disk2 = Factory.getDiskInstance("Seagate", "disk2", 400, 1024);
        Disk disk3 = Factory.getDiskInstance("WestDigitals", "disk3", 500, 1024);

        Mainboard mainboard1 = Factory.getMainboardInstance("Asus", "mainboard1", 1234, 2.34f);
        Mainboard mainboard2 = Factory.getMainboardInstance("Asus", "mainboard2", 2134, 3.45f);
        Mainboard mainboard3 = Factory.getMainboardInstance("Gigabyte", "mainboard3", 3124, 3.45f);

        Memory memory1 = Factory.getMemoryInstance("Kingston", "memory1", 500, 4);
        Memory memory2 = Factory.getMemoryInstance("Kingston", "memory2", 800, 8);
        Memory memory3 = Factory.getMemoryInstance("Samsung", "memory3", 900, 8);

        computers.add(Factory.getComputerInstance("computer1", cpu1, disk1, mainboard1, memory1));
        computers.add(Factory.getComputerInstance("computer2", cpu2, disk2, mainboard2, memory2));
        computers.add(Factory.getComputerInstance("computer3", cpu3, disk3, mainboard3, memory3));
    }

    public static void showGoods() {
        System.out.format("%-15s |%-15s |%-15s |%-15s |%-15s |%-15s\n", "Computer", "CPU", "Memory", "Disk", "Mainboard", "Price");
        for (int i = 0; i < 17 * 5; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (Computer com : computers) {
            System.out.format("%-15s |%-15s |%-15s |%-15s |%-15s |%-15f\n", com.getName(), com.getCPUName(), com.getMemoryName(), com.getDiskName(), com.getMainboardName(), com.getPrice());
        }
    }

    public static void showDetail() {
        for (Computer com : computers) {
            System.out.println(com);
        }
    }

    public static void showWork() {
        for (int i = 0; i < computers.size(); i++) {
            System.out.println("=========computer"+i+" work=========");
            computers.get(i).work();
        }
    }
}
