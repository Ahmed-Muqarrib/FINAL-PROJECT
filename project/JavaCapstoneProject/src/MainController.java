import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.Action;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController extends MenuItem {

    public User loggedIn;

    @FXML
    protected Button ChkOutButton;

    @FXML
    protected ImageView image1;

    @FXML
    protected ImageView image2;

    @FXML
    protected ImageView image3;

    @FXML
    protected ImageView image4;

    @FXML
    protected ImageView image5;

    @FXML
    public Label item1;

    @FXML
    protected Label item2;

    @FXML
    protected Label item3;

    @FXML
    protected Label item4;

    @FXML
    protected Label item5;

    @FXML
    protected Label itemdesc1;

    @FXML
    protected Label itemdesc2;

    @FXML
    protected Label itemdesc3;

    @FXML
    protected Label itemdesc4;

    @FXML
    protected Label itemdesc5;

    @FXML
    protected Label itemsLbl;

    @FXML
    protected Label price1;

    @FXML
    protected Label price2;

    @FXML
    protected Label price3;

    @FXML
    protected Label price4;

    @FXML
    protected Label price5;

    @FXML
    protected Label priceLbl;

    @FXML
    protected Label restaurantNameLbl;

    @FXML
    protected Label totalItemsLbl;

    @FXML
    protected Label totalPriceLbl;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static ArrayList < MenuItem > cart = initializeCart();

    public void addToCart(int x) { //method to add items to cart
        cart.set(x, new MenuItem(cart.get(x).getItemName(), cart.get(x).getItemPrice(), cart.get(x).getQuantity() + 1));

        String nameList = "";
        String priceList = "";
        double totalPrice = 0;
        for (MenuItem item: cart) {
            if (item.getQuantity() >= 1) {
                nameList += item.getItemName() + " x" + item.getQuantity() + "\n";
                priceList += ("$" + df.format(item.getItemPrice() * item.getQuantity()) + "\n");
                totalPrice += (item.getItemPrice() * item.getQuantity());
            }
        }

        double tax = totalPrice * 0.13;

        itemsLbl.setText(nameList);
        priceLbl.setText(priceList);
        totalItemsLbl.setText("Total : \nTax(13%) : \nGrand Total : ");
        totalPriceLbl.setText("$" + df.format(totalPrice) + "\n$" + df.format(tax) + "\n$" + df.format(totalPrice + tax));
    }

    public void removeFromCart(int x) { //method to remove items from the cart
        if (cart.get(x).getQuantity() >= 1) {
            cart.set(x, new MenuItem(cart.get(x).getItemName(), cart.get(x).getItemPrice(), cart.get(x).getQuantity() - 1));
        }

        String nameList = "";
        String priceList = "";
        double totalPrice = 0;
        for (MenuItem item: cart) {
            if (item.getQuantity() >= 1) {
                nameList += item.getItemName() + " x" + item.getQuantity() + "\n";
                priceList += ("$" + df.format(item.getItemPrice() * item.getQuantity()) + "\n");
                totalPrice += (item.getItemPrice() * item.getQuantity());
            }
        }

        double tax = totalPrice * 0.13;

        itemsLbl.setText(nameList);
        priceLbl.setText(priceList);
        totalItemsLbl.setText("Total : \nTax(13%) : \nGrand Total : ");
        totalPriceLbl.setText("$" + df.format(totalPrice) + "\n$" + df.format(tax) + "\n$" + df.format(totalPrice + tax));
    }

    @FXML
    void add1(ActionEvent event) {
        addToCart(0);
    }

    @FXML
    void add2(ActionEvent event) {
        addToCart(1);
    }

    @FXML
    void add3(ActionEvent event) {
        addToCart(2);
    }

    @FXML
    void add4(ActionEvent event) {
        addToCart(3);
    }

    @FXML
    void add5(ActionEvent event) {
        addToCart(4);
    }

    @FXML
    void remove1(ActionEvent event) {
        removeFromCart(0);
    }

    @FXML
    void remove2(ActionEvent event) {
        removeFromCart(1);
    }

    @FXML
    void remove3(ActionEvent event) {
        removeFromCart(2);
    }

    @FXML
    void remove4(ActionEvent event) {
        removeFromCart(3);
    }

    @FXML
    void remove5(ActionEvent event) {
        removeFromCart(4);
    }

    ArrayList < Label > itemNameList = new ArrayList < > ();
    ArrayList < Label > itemPriceList = new ArrayList < > ();
    ArrayList < Label > itemDescList = new ArrayList < > ();
    ArrayList < ImageView > itemImageList = new ArrayList < > ();

    @FXML
    void setItemNameList() {
        itemNameList.add(item1);
        itemNameList.add(item2);
        itemNameList.add(item3);
        itemNameList.add(item4);
        itemNameList.add(item5);
    }

    @FXML
    void setItemPriceList() {
        itemPriceList.add(price1);
        itemPriceList.add(price2);
        itemPriceList.add(price3);
        itemPriceList.add(price4);
        itemPriceList.add(price5);
    }

    @FXML
    void setItemDescList() {
        itemDescList.add(itemdesc1);
        itemDescList.add(itemdesc2);
        itemDescList.add(itemdesc3);
        itemDescList.add(itemdesc4);
        itemDescList.add(itemdesc5);
    }

    @FXML
    void setItemImageList() {
        itemImageList.add(image1);
        itemImageList.add(image2);
        itemImageList.add(image3);
        itemImageList.add(image4);
        itemImageList.add(image5);
    }

    @FXML
    void setItems() {

        ArrayList <MenuItem> items = readFromFile();

        this.setItemNameList();
        int counter = 0;
        for (Label label: this.itemNameList) {
            label.setText(items.get(counter).getItemName());
            counter++;
        }

        counter = 0;
        this.setItemPriceList();
        for (Label label: this.itemPriceList) {
            label.setText("$" + items.get(counter).getItemPrice());
            counter++;
        }

        counter = 0;
        this.setItemDescList();
        for (Label label: this.itemDescList) {
            label.setText(items.get(counter).getItemDescription());
            counter++;
        }

        counter = 0;
        this.setItemImageList();
        for (ImageView imageView: this.itemImageList) {
            imageView.setImage(new Image(items.get(counter).getImageURL()));
            counter++;
        }

    }

    @FXML
    void onClickCheckout(ActionEvent event) throws IOException {

        if (itemsLbl.getText() != "") {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckOutPage.fxml"));
            Parent secondRoot = loader.load();
            CheckOutPageController controller = loader.getController();

            controller.checkoutCartItem.setText(this.itemsLbl.getText());
            controller.checkoutCartPrice.setText(this.priceLbl.getText());

            double totalPrice = 0;
            for (MenuItem item: cart) {
                if (item.getQuantity() >= 1) {
                    totalPrice += (item.getItemPrice() * item.getQuantity());
                }
            }

            double tax = totalPrice * 0.13;
            double deliveryFee = 0.99;
            controller.checkoutCartTotal.setText("Total : \nTax(13%) : \nDelivery Fee : \nGrand Total : ");
            controller.checkoutCartTotalPrice.setText("$" + df.format(totalPrice) + "\n$" + df.format(tax) + "\n$" + deliveryFee +
                "\n$" + df.format(totalPrice + tax + deliveryFee));

            Scene secondScene = new Scene(secondRoot);
            Stage stage = (Stage) ChkOutButton.getScene().getWindow();
            stage.setTitle("Checkout!");
            stage.setScene(secondScene);
            stage.show();
        } else {
            Alert error = new Alert(AlertType.ERROR, "Add at least one item to cart");
            error.getButtonTypes().setAll(ButtonType.OK);
            error.show();
        }

    }

}