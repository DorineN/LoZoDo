package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("subscribe.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        Subscribe db = new Subscribe();
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

}


