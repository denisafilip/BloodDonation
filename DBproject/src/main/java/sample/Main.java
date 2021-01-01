package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import user.County;
import user.Scraper;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/CheckInFile.fxml"));
        Parent root = myLoader.load();
        primaryStage.setTitle("Sign Up");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
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
