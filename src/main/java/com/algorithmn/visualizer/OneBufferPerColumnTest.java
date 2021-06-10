package com.algorithmn.visualizer;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OneBufferPerColumnTest extends Application {


    public static final int PIXEL_HEIGHT = 16;
    public static final int COLUMN_WIDTH = 16;

    public static final int HEIGHT = 1024;
    public static final int WIDTH = 1024;
    public static final int COLUMN_NUMBER = WIDTH / COLUMN_WIDTH;

    @Override
    public void start(Stage stage) throws IOException {

        int maxNumbers = WIDTH / COLUMN_WIDTH;
        int columnBufferSize = COLUMN_WIDTH * HEIGHT;


        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < maxNumbers; i++) {
            randomNumbers.add(i);
        }
        ArrayList<byte[]> buffers = new ArrayList<>();
        Collections.shuffle(randomNumbers);

        for (int bufferIndex = 0; bufferIndex < COLUMN_NUMBER; bufferIndex++) {

            int currentNumber = randomNumbers.get(bufferIndex) * PIXEL_HEIGHT;
            System.out.println(currentNumber);

            byte[] currentBuffer = setValueBuffer(currentNumber, columnBufferSize);
            //TODO IMPROVE THIS TO AVOID REITERATING OVER ALL THE PIXELS * 4
            byte[] convertedBuffer = convertBufferDataIntoARBG(currentBuffer);

            buffers.add(convertedBuffer);

        }

        //This byte is the opacity parameter, is set to full it goes from 0x00 to 0xff it determinates de opacity of the color
        PixelFormat<ByteBuffer> format = PixelFormat.getByteBgraPreInstance();


        //HERE WE CREATE OUR MULTIPLE IMAGES
        Node [] imageViews = new Node [COLUMN_NUMBER];
        for (int bufferIndex = 0; bufferIndex < COLUMN_NUMBER; bufferIndex++) {
            ByteBuffer buffer = ByteBuffer.wrap(buffers.get(bufferIndex));
            WritableImage img = new WritableImage(new PixelBuffer<ByteBuffer>(COLUMN_WIDTH, HEIGHT, buffer, format));
            ImageView imageView = new ImageView();
            imageView.setImage(img);

            imageViews[bufferIndex] = imageView;
        }


        HBox hBox = new HBox(imageViews);

        Scene scene = new Scene(hBox, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


    private byte[] setValueBuffer(int currentNumber, int currentBufferSize) {

        byte[] currentBuffer = new byte[currentBufferSize];

        for (int y = 0; y < HEIGHT; y++) {

            for (int x = 0; x < COLUMN_WIDTH; x++) {
                double val = 0.25;

                if (y >= currentNumber) {
                    val = 1;
                }

                currentBuffer[x + y * COLUMN_WIDTH] = (byte) (val * 255);
            }

        }
        return currentBuffer;
    }

    private byte[] convertBufferDataIntoARBG(byte[] currentBuffer) {
        byte alpha = (byte) 0xff;

        // this is a parser to convert our byte buffer to format RBGA, because we have to transfer our 1 dimensional array to 4 values.
        //TODO improve to do the value conversion in a single iteration.
        byte[] convertedData = new byte[4 * currentBuffer.length];
        for (int i = 0; i < currentBuffer.length; i++) {
            convertedData[4 * i] = convertedData[4 * i + 1] = convertedData[4 * i + 2] = currentBuffer[i];
            convertedData[4 * i + 3] = alpha;
        }

        return convertedData;

    }


    public static void main(String[] args) {
        launch(args);
    }
}
