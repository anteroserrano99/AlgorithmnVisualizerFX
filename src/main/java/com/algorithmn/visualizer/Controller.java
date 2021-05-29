package com.algorithmn.visualizer;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Controller{


    @FXML
    private Button boton;

    @FXML
    private GridPane gridPane;


    public void ejecutarBoton(ActionEvent actionEvent){
        System.out.println("oh shit");
        Rectangle r = new Rectangle(10, 10, 20, 20);

        gridPane.add(r,0,0);


    }


}
