/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedicine.GUI;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    //Create variable
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        
        Label label = new Label();
        label.setText(message);
        label.setWrapText(true);
        label.getStyleClass().add("message");

        //Create two buttons
        Button yesButton = new Button("Yes");
        yesButton.getStyleClass().addAll("effect","green-color");
        Button noButton = new Button("No");
        noButton.getStyleClass().addAll("effect","dark-gray-color");
        HBox btnLayout = new HBox(10, yesButton,noButton);
        btnLayout.setAlignment(Pos.CENTER);

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10,label,btnLayout);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color:#eee;");
        layout.setMinWidth(250);
        layout.setMaxWidth(350);
        
        layout.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)){
                yesButton.fire();
            }else if (e.getCode().equals(KeyCode.ESCAPE)){
                noButton.fire();
            }
        });
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("/emedicine/css/css.css");
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.centerOnScreen();
        window.setScene(scene);
        window.setTitle(title);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }
}
