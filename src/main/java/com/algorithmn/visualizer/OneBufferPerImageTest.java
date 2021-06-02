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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OneBufferPerImageTest extends Application {



        @Override
        public void start(Stage stage) {
            int width = 1600;
            int height = 1600;
            byte[] data = new byte[width*height];

            int contador = 1;
            int maxNumbers =  height /16;

            List<Integer> randomNumbers =  new ArrayList<>();
            for (int i= 1; i<=maxNumbers+1;i++){
                randomNumbers.add(i);
            }

            Collections.shuffle(randomNumbers);

                // con po
            for (int y = 1 ; y < height ; y++) {
                contador = 0;
                for (int x = 1 ; x < width; x++) {
                    double val = 1;

                    int currentNumber = randomNumbers.get(contador)*16;
                    if (x % 16 == 0){
                        val =0;
                        contador++;
                    }

                    if (y == currentNumber && x == contador * 16){
                        val = 0;
                        for (int i = 0; i< 16; i++){
                            data[x + y*height + i] = (byte)( val *255);
                        }
                        contador++;
                        x +=16;
                    } else
                    data[x + y*height] = (byte)( val *255);
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
            Scene scene = new Scene(root, 1600, 1600);
            stage.setScene(scene);
            stage.show();
        }


    public static void main(String[] args) {
        launch(args);
    }
}
