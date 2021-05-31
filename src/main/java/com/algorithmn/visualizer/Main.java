package com.algorithmn.visualizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;


public class Main extends Application {



        @Override
        public void start(Stage stage) {
            int width = 1000;
            int height = 1000;
            byte[] data = new byte[width*height];



                // con po
            for (int y = 0 ; y < height ; y++) {
                for (int x = 0 ; x < width; x++) {

                    int columnIndex = (y * width);

//                    double val = x % 2 == 0 && y % 2 == 0 ? 0 : 1;
                    double val = x % 16 == 0 ? 0 : 1;
                    data[x + columnIndex] = (byte)(val * 255);
                }
            }




            //This byte is the opacity parameter, is set to full it goes from 0x00 to 0xff it determinates de opacity of the color
            PixelFormat<ByteBuffer> format = PixelFormat.getByteBgraPreInstance();
            byte alpha = (byte) 0xff ;

            // this is a parser to convert our byte buffer to format RBGA, because we have to transfer our 1 dimensional array to 4 values.
            //TODO improve to do the value conversion in a single iteration.
            byte[] convertedData = new byte[4*data.length];
            for (int i = 0 ; i < data.length ; i++) {
                convertedData[4*i] = convertedData[4*i+1] = convertedData[4*i+2] = data[i] ;
                convertedData[4*i+3] = alpha ;
            }

            //buffer image to our WritableImage
            ByteBuffer buffer = ByteBuffer.wrap(convertedData);

            WritableImage img = new WritableImage(new PixelBuffer<ByteBuffer>(width, height, buffer, format));



            ImageView imageView = new ImageView();
            //todo Ratio Preservation can create interesting images iif the size of the image is less than the stage image
//            imageView.setPreserveRatio(true);
            imageView.setImage(img);


            BorderPane root = new BorderPane(imageView);
            imageView.fitWidthProperty().bind(root.widthProperty());
            imageView.fitHeightProperty().bind(root.heightProperty());
            Scene scene = new Scene(root, 500, 500);
            stage.setScene(scene);
            stage.show();
        }


    public static void main(String[] args) {
        launch(args);
    }
}
