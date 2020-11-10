package net.kuhlroad.osrskcsim.assets;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * A class that represents an Item dropped by a Boss at a specific rate
 * @version 2020.11.10
 */
public class Item {
    private String name;
    private int dropRate;
    private SimpleBooleanProperty include;

    /**
     * Constructor for Item, initializing its name and drop rate
     * @param name      The Item's name
     * @param dropRate  The Item's drop rate
     */
    public Item(String name, int dropRate) {
        this.name = name;
        this.dropRate = dropRate;
        this.include = new SimpleBooleanProperty(true);
    }

    public String getName() {
        return name;
    }

    public int getDropRate() {
        return dropRate;
    }

    public boolean isIncluded() {
        return include.get();
    }

    public SimpleBooleanProperty includeProperty() {
        return include;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDropRate(int dropRate) {
        this.dropRate = dropRate;
    }

    public void setInclude(boolean include) {
        this.include.set(include);
    }
}
