package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.County;
import user.Scraper;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        //testing purposes
        Scraper scraper = new Scraper();
        ArrayList<County> counties = scraper.webScrapingCounties();

        for (County c : counties) {
            System.out.println(c.getName() + " " + c.getNumberOfBloodBanks());
        }
    }
}
