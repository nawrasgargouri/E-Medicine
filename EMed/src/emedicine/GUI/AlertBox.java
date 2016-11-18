/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedicine.GUI;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.input.KeyCode;

public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage();
        
        Label label = new Label();
        label.setText(message);
        label.setWrapText(true);
        label.getStyleClass().add("message");
        Button closeButton = new Button("Ok");
        closeButton.getStyleClass().addAll("effect","blue-color");
        closeButton.setOnAction(e -> window.close());

        
        VBox layout = new VBox(10,label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setMinWidth(250);
        layout.setMaxWidth(350);
        
        layout.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.ESCAPE)){
                closeButton.fire();
            }
        });

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("/emedicine/css/css.css");
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.centerOnScreen();
        window.setTitle(title);
        window.setScene(scene);
        window.showAndWait();
        
    }

}