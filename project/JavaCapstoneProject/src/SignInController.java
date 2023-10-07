import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SignInController extends MainController {

    private ArrayList <User> users = new ArrayList<>();
    private static String filename = "users.txt";

    @FXML
    private Button createacct;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Button logInButton;

    @FXML
    void initialize() { // Executes on Load

        try (Scanner scanner = new Scanner(new File(filename))) {

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] lineData = currentLine.split(",");
                String username = lineData[0];
                String password = lineData[1];

                users.add(new User(username, password));
            }

        } catch (IOException ex) {
            System.err.println("File Not Found");
        }
    }

    @FXML
    void onClickCreate(ActionEvent event) {

        if (username.getText().equals("") || password.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR, "Fields Left Empty");
            alert.show();
            return;
        }

        for (User user: users) {

            // Check if account exists
            if (username.getText().equals(user.getName())) {
                Alert alert = new Alert(AlertType.ERROR, "Account Already Exists");
                alert.showAndWait();
                return;
            }
        }
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename, true));

            writer.println(username.getText() + "," + password.getText());
            writer.close();
            // Adding new user to arraylist
            users.add(new User(username.getText(), password.getText()));
            Alert alert = new Alert(AlertType.CONFIRMATION, "Account Created, Please Log In!");
            alert.showAndWait();

        } catch (IOException ex) {
            System.out.println("File not Found");
        }
    }

    @FXML
    void onClickLogIn(ActionEvent event) throws IOException { // Log In

        if (username.getText().equals("") || password.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR, "Fields Left Empty");
            alert.show();
            return;
        }

        boolean found = false;

        for (User user: users) {
            if (user.getName().equals(username.getText()) && user.getPassword().equals(password.getText())) {
                found = true;
                break;
            }
        }
        if (!found) {
            Alert alert = new Alert(AlertType.ERROR, "Account Not Found");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent secondRoot = loader.load();
        MainController controller = loader.getController();
        controller.loggedIn = new User(username.getText(), password.getText());

        controller.setItems();

        Scene secondScene = new Scene(secondRoot);
        Stage stage = (Stage) logInButton.getScene().getWindow();
        stage.setTitle("Order Online");
        stage.setScene(secondScene);
        stage.show();
    }

}