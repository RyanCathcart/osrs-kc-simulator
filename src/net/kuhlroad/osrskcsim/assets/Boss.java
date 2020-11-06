package net.kuhlroad.osrskcsim.assets;

import java.util.HashMap;

/**
 * Class that represents an OSRS Boss that has a name and a list of drops
 * @author Ryan Cathcart
 */
public class Boss {
    private String name;
    private HashMap<String, Integer> items;

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }
}
