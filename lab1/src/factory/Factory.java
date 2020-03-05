package factory;


import component.CPU;
import component.Disk;
import component.Mainboard;
import component.Memory;
import computer.Computer;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class Factory {
    @NotNull
    public static CPU getCPUInstance(String type, String name, float price, int coreNum) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (CPU) Class.forName("component.cpu." + type)
                .getConstructor(String.class, float.class, int.class)
                .newInstance(name, price, coreNum);
    }

    @NotNull
    public static Disk getDiskInstance(String type, String name, float price, int volume) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Disk) Class.forName("component.disk." + type)
                .getConstructor(String.class, float.class, int.class)
                .newInstance(name, price, volume);
    }

    @NotNull
    public static Mainboard getMainboardInstance(String type, String name, float price, float speed) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Mainboard) Class.forName("component.mainboard." + type)
                .getConstructor(String.class, float.class, float.class)
                .newInstance(name, price, speed);
    }

    @NotNull
    public static Memory getMemoryInstance(String type, String name, float price, int volume) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Memory) Class.forName("component.memory." + type)
                .getConstructor(String.class, float.class, int.class)
                .newInstance(name, price, volume);
    }

    @NotNull
    public static Computer getComputerInstance(String name, CPU cpu, Disk disk, Mainboard mainboard, Memory memory) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Computer) Class.forName("computer.Computer")
                .getConstructor(String.class, CPU.class,Disk.class,Mainboard.class,Memory.class)
                .newInstance(name, cpu, disk,mainboard,memory);
    }
}
