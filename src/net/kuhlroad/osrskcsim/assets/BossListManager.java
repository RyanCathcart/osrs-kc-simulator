package net.kuhlroad.osrskcsim.assets;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that manages the application's list of Bosses. The data is pulled from an XML file using a SAX parser
 * @author Ryan Cathcart
 */
public class BossListManager {
    // Name of XML file
    private final String FILEPATH = BossListManager.class.getResource("/net/kuhlroad/osrskcsim/assets/boss-data.xml").toString();

    // Objects needed to parse XML document
    private SAXParserFactory factory;
    private SAXParser saxParser;

    // HashMap of Bosses
    private HashMap<String, Boss> bossList;

    public BossListManager() {
        bossList = new HashMap<>();
        generateBossList();
    }

    /**
     * Generates an ArrayList of Bosses from this class' data file
     */
    public void generateBossList() {
        factory = SAXParserFactory.newInstance();

        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        // Creates a new DefaultHandler object
        DefaultHandler handler = new DefaultHandler() {
            // Data hierarchy:
            private Boss boss = null;
                private boolean bName = false;
                private ArrayList<Item> items = null;
                    private boolean bItemname = false;
                    private String itemname = "";

                    private boolean bRate = false;
                    private int rate = 0;

            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equalsIgnoreCase("boss")) {
                    boss = new Boss();
                }

                if (qName.equalsIgnoreCase("name")) {
                    bName = true;
                }

                if (qName.equalsIgnoreCase("items")) {
                    items = new ArrayList<Item>();
                }

                if (qName.equalsIgnoreCase("itemname")) {
                    bItemname = true;
                }

                if (qName.equalsIgnoreCase("rate")) {
                    bRate = true;
                }
            }

            public void characters(char[] ch, int start, int length) throws SAXException {
                if (bName) {
                    boss.setName(new String(ch, start, length));
                    bName = false;
                }

                if (bItemname) {
                    itemname = new String(ch, start, length);
                    bItemname = false;
                }

                if (bRate) {
                    rate = Integer.parseInt(new String(ch, start, length));
                    bRate = false;
                }
            }

            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("boss")) {
                    bossList.put(boss.getName(), boss);
                }

                if (qName.equalsIgnoreCase("items")) {
                    boss.setItems(items);
                }

                if (qName.equalsIgnoreCase("item")) {
                    items.add(new Item(itemname, rate));
                }
            }
        };

        try {
            saxParser.parse(FILEPATH, handler);
            System.out.println("Found file: \n" + FILEPATH);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find: \n" + FILEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print the boss list to the console. Mostly for debug purposes
     */
    public void printBossList() {
        for (String s : bossList.keySet()) {
            ArrayList<Item> items = bossList.get(s).getItems();
            System.out.println(bossList.get(s).getName());
            for (Item item : items) {
                System.out.println("\t" + item.getName() + " : " + item.getDropRate());
            }
        }
    }

    /**
     * Gets the list of bosses
     * @return The list of bosses and their item drop chances
     */
    public HashMap<String, Boss> getBossList() {
        return bossList;
    }
}
