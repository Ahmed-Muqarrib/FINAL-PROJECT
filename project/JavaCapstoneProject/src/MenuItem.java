import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuItem {
    
    private String itemName;
    private double itemPrice;
    private String imageURL; 
    private String itemDescription; 
    public static String filename = "input.txt";

    private int quantity;

    MenuItem(String itemName, double itemPrice, String imageURL, String itemDescription) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.imageURL = imageURL;   
        this.itemDescription = itemDescription;
    }

    MenuItem(String itemName, double itemPrice, int quantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public MenuItem() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    static ArrayList<MenuItem> items = new ArrayList<>();

    public static ArrayList<MenuItem> readFromFile() {

    try (Scanner scanner = new Scanner(new File(filename))) {

        while(scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            String[] lineData = currentLine.split(",");
            String itemName = lineData[0];
            double price = Double.valueOf(lineData[1]);
            String image = lineData[2];
            String itemDesc = lineData[3];
            items.add(new MenuItem(itemName, price, image, itemDesc));
        }
        
    }
    catch (IOException ex) {
        System.err.println("File Not Found");
    } return items;
    }

    public static ArrayList<MenuItem> cart = new ArrayList<>();

    public static ArrayList<MenuItem> initializeCart() {

        ArrayList<MenuItem> itemSetter = readFromFile();

        for (MenuItem items : itemSetter) {
            cart.add(new MenuItem(items.getItemName(), items.getItemPrice(), 0));
        }
        
        return cart;
    }

    @Override
    public String toString() {
        return "Name: " + getItemName() + " \nPrice: " + getItemPrice() 
        + " \nImage URL: " + getImageURL() + " \nDescription: " 
        + getItemDescription();
    }
}
