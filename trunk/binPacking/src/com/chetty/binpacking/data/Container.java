package com.chetty.binpacking.data;

/**
 *
 * @author Babji Prashanth, Chetty
 */
public class Container implements Comparable<Container> {
    private int capacity;
    private int actualCapacity;
    private int usedCapacity;

    public Container(int actualCapacity) {
        this.actualCapacity = actualCapacity;
    }

    public int getCapacity() {
        return (actualCapacity - usedCapacity);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getActualCapacity() {
        return actualCapacity;
    }

    public void setActualCapacity(int actualCapacity) {
        this.actualCapacity = actualCapacity;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(int usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public int compareTo(Container container) {
        return (container != null) ? Integer.valueOf(this.getCapacity()).compareTo(container.getCapacity())
                                   : 1;
    }
}
