/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedicine.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login {
    Stage window=new Stage();
    double initX,initY;
    
    public Login() {
        Label title = new Label("Welcome");
        title.getStyleClass().addAll("message","marsala-color");
        HBox headLayout = new HBox(title);
        headLayout.setStyle("-fx-padding: 8px; -fx-background-color :rgb(235,235,235); -fx-background-radius: 10px 10px 0px 0px; -fx-border-radius:10px 10px 0px 0px;");
        headLayout.setAlignment(Pos.CENTER);
        
        Label userNameLabel=new Label("Username");
        userNameLabel.getStyleClass().add("lab");
        Label passwordLabel=new Label("Password");
        passwordLabel.getStyleClass().add("lab");
        
        TextField userName = new TextField(); 
        userName.getStyleClass().add("marsala-color");
        PasswordField password = new PasswordField(); 
        password.getStyleClass().add("marsala-color");
        
        GridPane bodyLayout = new GridPane();
        bodyLayout.setPadding(new Insets(10));
        bodyLayout.setHgap(15);
        bodyLayout.setVgap(10);
        bodyLayout.addColumn(0, userNameLabel,passwordLabel);
        bodyLayout.addColumn(1, userName,password);
        bodyLayout.setStyle("-fx-background-color :rgb(248,248,248); -fx-background-radius: 0px 0px 20px 20px; -fx-border-radius:0px 0px 20px 20px;");
        GridPane.setHgrow(userName, Priority.ALWAYS);
        
        Button btn = new Button("Login");
        btn.getStyleClass().addAll("effect","marsala-color");
        btn.setOnAction(e->{
            String userNameText = userName.getText().trim();
            String passwordText = password.getText().trim();
            if(userNameText.isEmpty()){
                AlertBox.display("Error","Please enter username!");
            }else if(passwordText.isEmpty()){
                AlertBox.display("Error","Please enter password!");
            }else{
                System.err.println("Done");
                
            }
            
        });
        Button exitBtn = new Button("Exit");
        exitBtn.getStyleClass().addAll("effect","dark-gray-color");
        exitBtn.setOnAction(e->{close();});
        
        Button regist = new Button("Signup");
        regist.getStyleClass().addAll("effect","green-color");
        
        HBox footerlayout = new HBox(15,btn,exitBtn,regist);
        footerlayout.setStyle("-fx-alignment:center; -fx-padding: 12px; -fx-background-color :rgb(235,235,235); -fx-background-radius: 0px 0px 10px 10px; -fx-border-radius:0px 0px 10px 10px;");
        
        VBox root = new VBox(headLayout,bodyLayout,footerlayout);
        root.setStyle("-fx-alignment:center; -fx-border-style :solid; -fx-background-radius: 10px; -fx-border-radius:10px; -fx-border-color:rgb(0,209,229); -fx-border-width: 1;");
        
        root.setOnMousePressed((MouseEvent me) -> {
             initX = me.getScreenX() - window.getX();
             initY = me.getScreenY() - window.getY();
        });
        root.setOnMouseDragged((MouseEvent me) -> {
            window.setX(me.getScreenX() - initX);
            window.setY(me.getScreenY() - initY);
        });
        
        root.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)){
                btn.fire();
            }else if (e.getCode().equals(KeyCode.ESCAPE)){
                exitBtn.fire();
            }
        });
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/emedicine/css/css.css");
        
        Registration registWin = new Registration(window);
        regist.setOnAction(e->{
            window.setScene(registWin.scene);
        });
        registWin.exitBtn.setOnAction(e->{
            window.setScene(scene);
        });
        
        window.setOnCloseRequest(e->{
            e.consume();
            close();
        });
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle("Login");
        window.setScene(scene);
        window.show();
    }

    void close(){
        if(ConfirmBox.display("Exit!","Are you sure you want to exit?")){
            window.close();
        }
    }
}
