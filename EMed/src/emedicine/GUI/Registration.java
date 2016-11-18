/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedicine.GUI;

import emedicine.Pharmacy;
import emedicine.User;
import emedicine.server.provider;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Registration {

    public Scene scene,scene1;
    public VBox root,root2;
    private double initX,initY;
    public Button exitBtn;
    
    public Registration(Stage window) {
        Label title = new Label("Registration");
        title.getStyleClass().addAll("message","marsala-color");
        HBox headLayout = new HBox(title);
        headLayout.setStyle("-fx-padding: 8px; -fx-background-color :rgb(235,235,235); -fx-background-radius: 10px 10px 0px 0px; -fx-border-radius:10px 10px 0px 0px;");
        headLayout.setAlignment(Pos.CENTER);
        
        RadioButton buyer = new RadioButton("Buyer");
        RadioButton pharmacy = new RadioButton("Pharmacy");
        ToggleGroup group = new ToggleGroup();
        buyer.setToggleGroup(group);
        pharmacy.setToggleGroup(group);
        HBox radioLayout = new HBox(10,buyer,pharmacy);
        radioLayout.setPadding(new Insets(10));
        
        Label nameLabel = new Label("Name");
        nameLabel.getStyleClass().add("lab");
        Label userNameLabel=new Label("Username");
        userNameLabel.getStyleClass().add("lab");
        Label emailLabel=new Label("Email");
        emailLabel.getStyleClass().add("lab");
        Label phoneLabel=new Label("Phone");
        phoneLabel.getStyleClass().add("lab");
        Label passwordLabel=new Label("Password");
        passwordLabel.getStyleClass().add("lab");
        Label confirmPasswordLabel=new Label("Retype password");
        confirmPasswordLabel.getStyleClass().add("lab");
        Label pharmacyNameLabel=new Label("Pharmacy name");
        pharmacyNameLabel.getStyleClass().add("lab");
        Label pharmacyDesLabel=new Label("Location");
        pharmacyDesLabel.getStyleClass().add("lab");
        
        TextField name = new TextField(); 
        name.getStyleClass().add("marsala-color");
        TextField userName = new TextField(); 
        userName.getStyleClass().add("marsala-color");
        TextField email = new TextField(); 
        email.getStyleClass().add("marsala-color");
        TextField phone = new TextField(); 
        phone.getStyleClass().add("marsala-color");
        PasswordField password = new PasswordField(); 
        password.getStyleClass().add("marsala-color");
        PasswordField confirmPassword = new PasswordField(); 
        confirmPassword.getStyleClass().add("marsala-color");
        TextField pharmacyName = new TextField();
        pharmacyName.getStyleClass().add("marsala-color");
        TextField pharmacyDes = new TextField(); 
        pharmacyDes.getStyleClass().add("marsala-color");
        
        Button btn = new Button("Register");
        btn.getStyleClass().addAll("effect","marsala-color");
        btn.setOnAction(e->{
            String nameText = name.getText().trim();
            String userNameText = userName.getText().trim();
            String emailText = email.getText().trim();
            String pass = password.getText().trim();
            String confirmPassText = confirmPassword.getText().trim();
            String pharmacyNameText = pharmacyName.getText().trim();
            String pharmacyDesText = pharmacyDes.getText().trim();
            String phoneText = phone.getText().trim();
            add(nameText,userNameText,emailText,pass,confirmPassText,pharmacyNameText,pharmacyDesText,phoneText,buyer.isSelected());
        });
        
        
        exitBtn = new Button("Back");
        exitBtn.getStyleClass().addAll("effect","dark-gray-color");
        
        HBox footerlayout = new HBox(15,btn,exitBtn);
        footerlayout.setStyle("-fx-alignment:center; -fx-padding: 12px; -fx-background-color :rgb(235,235,235); -fx-background-radius: 0px 0px 10px 10px; -fx-border-radius:0px 0px 10px 10px;");
        
        root = new VBox();
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
        
        buyer.selectedProperty().addListener(e->{
            GridPane bodyLayout = new GridPane();
            bodyLayout.setPadding(new Insets(10));
            bodyLayout.setHgap(10);
            bodyLayout.setVgap(10);
            bodyLayout.setStyle("-fx-background-color :rgb(248,248,248); -fx-background-radius: 0px 0px 20px 20px; -fx-border-radius:0px 0px 20px 20px;");
            ColumnConstraints c = new ColumnConstraints();
            c.setPrefWidth(110);
            bodyLayout.getColumnConstraints().add(c);
            ColumnConstraints c2 = new ColumnConstraints();
            c2.setPrefWidth(250);
            bodyLayout.getColumnConstraints().add(c2);
            if(buyer.isSelected()){
                bodyLayout.addColumn(0, nameLabel,userNameLabel,emailLabel,passwordLabel,confirmPasswordLabel);
                bodyLayout.addColumn(1, name,userName,email,password,confirmPassword);
            }else{
                bodyLayout.addColumn(0, nameLabel,pharmacyNameLabel,userNameLabel,emailLabel,passwordLabel,confirmPasswordLabel,phoneLabel,pharmacyDesLabel);
                bodyLayout.addColumn(1, name,pharmacyName,userName,email,password,confirmPassword,phone,pharmacyDes);
            }
            root.getChildren().clear();
            root.getChildren().addAll(headLayout,radioLayout,bodyLayout,footerlayout);
            if(scene != null){
                scene.setRoot(new VBox());
            }
            scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add("/emedicine/css/css.css");
            window.setScene(scene);
        });
        buyer.setSelected(true);
    }
    private void add(String name,String userName,String email,String pass,String confirmPass,String pharmacyName,String des,String phone,boolean buyer){
        if(name.isEmpty()){
            AlertBox.display("ERROR", "Enter name!");
        }else if(userName.isEmpty()){
            AlertBox.display("ERROR", "Enter username!");
        }else if(pharmacyName.isEmpty() && !buyer){
            AlertBox.display("ERROR", "Enter pharmacy name!");
        }else if(email.isEmpty()){
            AlertBox.display("ERROR", "Enter Email!");
        }else if(pass.isEmpty()){
            AlertBox.display("ERROR", "Enter password!");
        }else if(confirmPass.isEmpty()){
            AlertBox.display("ERROR", "Confirm password!");
        }else if(!pass.equals(confirmPass)){
            AlertBox.display("ERROR", "Passwords do not match!");
        }else if(phone.isEmpty() && !buyer){
            AlertBox.display("ERROR", "Enter phone number!");
        }else if(des.isEmpty() && !buyer){
            AlertBox.display("ERROR", "Enter pharmacy location!");
        }else{
            User user = new User(name, userName, pass, email, null);
            if(!buyer){
                Pharmacy pharmacy = new Pharmacy(pharmacyName, des, phone, user);
                user.pharmacy = pharmacy;
                if(provider.pharmacyRegistration(pharmacy)){
                    exitBtn.fire();
                    AlertBox.display("Done", "Pharmacy was added successfully");
                }else{
                    AlertBox.display("Error", "Account info is used {username | email | pharmacy name}!");
                }
            }else{
                if(provider.userRegistration(user)){
                    exitBtn.fire();
                    AlertBox.display("Done", "User was added successfully");
                }else{
                    AlertBox.display("Error", "Username or Email exists");
                }
            }
        }
    }
}
