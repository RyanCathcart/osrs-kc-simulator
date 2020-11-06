package net.kuhlroad.osrskcsim.assets;

import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Ryan Cathcart
 */
public class BossListManager extends DefaultHandler {
    // Name of XML file
    private final String FILEPATH = "boss-data.xml";

    // Objects needed to parse XML document
    private File file = new File(FILEPATH);

    private boolean hasName = false;
    private boolean hasItems = false;

    // ArrayList of Bosses
    private ArrayList<Boss> bossList;
    private Boss boss = null;

    public BossListManager() {

    }

    /**
     * Gets the list of bosses
     * @return The list of bosses and their item drop chances
     */
    public ArrayList<Boss> getBossList() {
        return bossList;
    }
}
