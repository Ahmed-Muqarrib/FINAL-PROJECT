import java.io.IOException;
import java.text.DecimalFormat;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CheckOutPageController extends MainController {

    @FXML
    protected Label checkoutCartItem;

    @FXML
    protected Label checkoutCartPrice;

    @FXML
    protected Label checkoutCartTotal;

    @FXML
    protected Label checkoutCartTotalPrice;

    @FXML
    protected Label restaurantNameLbl;

    @FXML
    protected TextField txtAddress;

    @FXML
    protected TextField txtPhone;

    @FXML
    protected TextField txtTip;

    @FXML
    protected Label errorTip;

    @FXML
    protected Button backButton;

    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent secondRoot = loader.load();
        MainController controller = loader.getController();

        controller.setItems();

        final DecimalFormat df = new DecimalFormat("0.00");

        String nameList = "";
        String priceList = "";
        double totalPrice = 0;
        for (MenuItem item: controller.cart) {
            if (item.getQuantity() >= 1) {
                nameList += item.getItemName() + " x" + item.getQuantity() + "\n";
                priceList += ("$" + df.format(item.getItemPrice() * item.getQuantity()) + "\n");
                totalPrice += (item.getItemPrice() * item.getQuantity());
            }
        }

        double tax = totalPrice * 0.13;

        controller.itemsLbl.setText(nameList);
        controller.priceLbl.setText(priceList);
        controller.totalItemsLbl.setText("Total : \nTax(13%) : \nGrand Total : ");
        controller.totalPriceLbl.setText("$" + df.format(totalPrice) + "\n$" + df.format(tax) + "\n$" + df.format(totalPrice + tax));

        Scene secondScene = new Scene(secondRoot);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Order Online");
        stage.setScene(secondScene);
        stage.show();
    }

    @FXML
    void onClickOrder(ActionEvent event) throws IOException {
        if (txtPhone.getText() != "" && txtAddress.getText() != "") {
            try {
                Integer.parseInt(txtPhone.getText());
                Alert success = new Alert(AlertType.CONFIRMATION, "Order Successfully Placed");
                success.getButtonTypes().setAll(ButtonType.OK);
                success.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent secondRoot = loader.load();
                MainController controller = loader.getController();

                controller.cart.clear();
                controller.cart = initializeCart();
                controller.setItems();

                Scene secondScene = new Scene(secondRoot);
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setTitle("Order Online");
                stage.setScene(secondScene);
                stage.show();

            } catch (NumberFormatException e) {
                Alert failure = new Alert(AlertType.ERROR, "Please Enter a Valid Address and Phone Number");
                failure.show();
            }
        } else {
            Alert failure = new Alert(AlertType.ERROR, "Please Enter a Valid Address and Phone Number");
            failure.show();
        }
    }

    @FXML
    void onClickTip(ActionEvent event) {

        try {
            if (Double.parseDouble(txtTip.getText()) >= 0) {

                double tip = Double.parseDouble(txtTip.getText());

                final DecimalFormat df = new DecimalFormat("0.00");

                double totalPrice = 0;
                for (MenuItem item: MenuItem.cart) {
                    if (item.getQuantity() >= 1) {
                        totalPrice += (item.getItemPrice() * item.getQuantity());
                    }
                }

                double tax = totalPrice * 0.13;
                double deliveryFee = 0.99;
                checkoutCartTotal.setText("Total : \nTax(13%) : \nDelivery Fee : \nTip : \nGrand Total : ");
                checkoutCartTotalPrice.setText("$" + df.format(totalPrice) + "\n$" + df.format(tax) + "\n$" + deliveryFee +
                    "\n$" + tip + "\n$" + df.format(totalPrice + tax + deliveryFee + tip));

                errorTip.setText("");
                }
                else {
                    errorTip.setText("Tip can't be negative");
                }
        } catch (NumberFormatException e) {
            errorTip.setText("The tip must be a valid number value");
        }

    }

}