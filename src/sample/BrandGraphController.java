package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BrandGraphController implements Initializable {

    static ArrayList<Brand> brands;
    static ArrayList<XYChart.Series<Number,String>> seriesArrayList = new ArrayList<>();
    @FXML
    private LineChart<Number,String> barChart;

    @FXML
    private NumberAxis yearAxis;

    @FXML
    private CategoryAxis citiesAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        brands = new ArrayList<>();
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }


        for(Brand brand : brands){
            XYChart.Series<Number,String> series = new XYChart.Series<>();
            series.setName(String.valueOf(brand.getYear()));

            series.getData().add(new XYChart.Data<>(brand.getValue(),brand.getName()));
            seriesArrayList.add(series);
        }



        Timeline tl = new Timeline();
        tl.getKeyFrames().add(
                new KeyFrame(Duration.millis(10),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                barChart.getData().addAll(seriesArrayList);
                            }
                        }
                ));
        tl.setAutoReverse(false);
        tl.play();

        citiesAxis.setAnimated(false);

    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        Parent parentScreen = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(parentScreen);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public static void readFromFile() throws FileNotFoundException {

        File file = new File("brand_values.txt");
        Brand brand = null;

        Scanner scanner = new Scanner(file);

        for (int i = 0; i < 2; i++) {
            scanner.nextLine();
        }

        int count = 0;

        scanner.useDelimiter(",");
        while (scanner.hasNextLine()) {
            if (count == 0) {
                scanner.nextLine();
                count = Integer.parseInt(scanner.nextLine());
            } else {
                String year = scanner.next();
                scanner.skip(scanner.delimiter());

                String name = scanner.next();
                scanner.skip(scanner.delimiter());

                String country = scanner.next();
                scanner.skip(scanner.delimiter());

                int value = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                String category = scanner.nextLine();

                brand = new Brand(year, name, country, value, category);
                brands.add(brand);
                count--;
            }
        }
    }

}

