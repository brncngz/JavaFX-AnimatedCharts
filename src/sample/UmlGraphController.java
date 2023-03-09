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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UmlGraphController implements Initializable {

    static ArrayList<City> cities;
    static ArrayList<Integer> years = new ArrayList<>();
    static ArrayList<XYChart.Series<Number,String>> seriesArrayList = new ArrayList<>();
    @FXML
    private StackedBarChart<Number,String> barChart;

    @FXML
    private NumberAxis yearAxis;

    @FXML
    private CategoryAxis citiesAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cities = new ArrayList<>();
        try {
            readFromXml();
        } catch (Exception e) {
            e.printStackTrace();
        }


        for(int i=0; i<cities.size(); i++){
            City city = cities.get(i);
            if(!isExists(city.getYear())){
                years.add(city.getYear());
            }
        }

        for(int i=0; i<years.size() / 3 ; i++){
            int year = years.get(i);
            XYChart.Series<Number,String> series = new XYChart.Series<>();
            series.setName(String.valueOf(year));

            for(int j=0; j<cities.size(); j++){
                if(cities.get(j).getYear() == year){
                    series.getData().add(new XYChart.Data<>(cities.get(j).getValue(),cities.get(j).getName()));
                }
            }

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

    public static void readFromXml() throws ParserConfigurationException, IOException, SAXException {
        File file = new File("country_populations.xml");

        City city = null;
        String name;
        String country;
        int year;
        int value;
        String category;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(file);

        doc.getDocumentElement().normalize();

        NodeList chapters = doc.getElementsByTagName("city");

        for(int i=0;i < chapters.getLength();i++) {
            Element chapter = (Element) chapters.item(i);
            NodeList field = chapter.getElementsByTagName("field");

            Element element = (Element) field.item(0);
            name = element.getTextContent();

            element = (Element) field.item(1);
            country = element.getTextContent();

            element = (Element) field.item(2);
            year = Integer.parseInt(element.getTextContent());

            element = (Element) field.item(3);
            value = Integer.parseInt(element.getTextContent());

            element = (Element) field.item(4);
            category = element.getTextContent();

            city = new City(year,name,country,value,category);
            cities.add(city);
        }

    }

    public static boolean isExists(int year){
        for(Integer i : years){
            if(i == year){
                return true;
            }
        }
        return false;
    }
}
