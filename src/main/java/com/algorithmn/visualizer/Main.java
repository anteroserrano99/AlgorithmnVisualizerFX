package com.algorithmn.visualizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.Buffer;
import java.nio.IntBuffer;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{



        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        Scene s = new Scene(root, 300, 275);

        primaryStage.setScene(s);

        IntBuffer buffer = IntBuffer.allocate(1);
        int[] pixels = buffer.array();

        PixelBuffer pixelBuffer = new PixelBuffer(1, 1, buffer, PixelFormat.getIntArgbPreInstance());


        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
