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

public class CityGraphController implements Initializable {
    static ArrayList<City> cities;
    static ArrayList<Integer> years = new ArrayList<>();
    static ArrayList<XYChart.Series<Number, String>> seriesArrayList = new ArrayList<>();
    @FXML
    private StackedBarChart<Number, String> barChart;

    @FXML
    private NumberAxis yearAxis;

    @FXML
    private CategoryAxis citiesAxis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cities = new ArrayList<>();
        try {
            readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            if (!isExists(city.getYear())) {
                years.add(city.getYear());
            }
        }


        for (int i = 0; i < years.size() / 3; i++) {
            int year = years.get(i);
            XYChart.Series<Number, String> series = new XYChart.Series<>();
            series.setName(String.valueOf(year));

            for (int j = 0; j < cities.size(); j++) {
                if (cities.get(j).getYear() == year) {
                    series.getData().add(new XYChart.Data<>(cities.get(j).getValue(), cities.get(j).getName()));
                }
            }

            seriesArrayList.add(series);
        }

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                barChart.getData().addAll(seriesArrayList);
                            }
                        }
                ));
        tl.setAutoReverse(false);
        tl.play();

        citiesAxis.setAnimated(false);
    }

    public static void readFile() throws FileNotFoundException {

        File file = new File("city_populations.txt");
        City city = null;

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
                int year = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                String name = scanner.next();
                scanner.skip(scanner.delimiter());

                String country = scanner.next();
                scanner.skip(scanner.delimiter());

                int value = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                String category = scanner.nextLine();

                city = new City(year, name, country, value, category);
                cities.add(city);
                count--;
            }
        }
    }


    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        Parent parentScreen = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(parentScreen);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public static boolean isExists(int year) {
        for (Integer i : years) {
            if (i == year) {
                return true;
            }
        }
        return false;
    }
}
