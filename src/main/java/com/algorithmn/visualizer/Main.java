package com.algorithmn.visualizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        Scene s = new Scene(root, 300, 275);

        primaryStage.setScene(s);



        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
