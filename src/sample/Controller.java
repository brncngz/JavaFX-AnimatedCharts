package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button umlButton;

    @FXML
    private Button brandButton;

    @FXML
    private Button cityButton;

    @FXML
    void onBrandButtonClick(ActionEvent event) throws IOException {
        Parent parentScreen = FXMLLoader.load(getClass().getResource("BrandGraph.fxml"));
        Scene scene = new Scene(parentScreen);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }

    @FXML
    void onCityButtonClick(ActionEvent event) throws IOException {
        Parent parentScreen = FXMLLoader.load(getClass().getResource("cityGraph.fxml"));
        Scene scene = new Scene(parentScreen);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }

    @FXML
    void onumlButtonClick(ActionEvent event) throws IOException {
        Parent parentScreen = FXMLLoader.load(getClass().getResource("umlGraph.fxml"));
        Scene scene = new Scene(parentScreen);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
