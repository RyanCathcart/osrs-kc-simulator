package net.kuhlroad.osrskcsim.assets;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that represents an OSRS Boss that has a name and a list of drops
 * @author Ryan Cathcart
 * @date 2020.11.08
 */
public class Boss {
    private String name;
    private ArrayList<Item> items;

    /**
     * Get the name of this Boss
     * @return The name of this boss
     */
    public String getName() {
        return name;
    }

    /**
     * Get the HashMap of this Boss' items mapped to their drop rates
     * @return The Boss item HashMap
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Set the name of this Boss
     * @param name The new name for this Boss
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the drop table for this Boss
     * @param items The new HashMap of this Boss' items mapped to their drop rates
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
