/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Phase4;

import Final_Project_Phase2.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class User_PageController implements Initializable {

    @FXML
    private Button buttonborrow;
    @FXML
    private Button buttonviewbook;
    @FXML
     private Button buttonupdate;
     @FXML 
     private Button buttonlogout;
     @FXML 
     private Button buttonshow;
     private Stage stage;
     private Parent root;
     private Log logs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logs=new Log();
        logs.logfiles("Welcome To User Page");
        
    }    

    @FXML
    private void buttonborrowHandle(ActionEvent event) throws IOException {
          stage=(Stage)buttonborrow.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("Add_Borrow_Book.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        logs=new Log();
        logs.logfiles("Go To Add Borrow Book");
        
    }

    @FXML
    private void buttonviewbookHandle(ActionEvent event) throws IOException {
        stage=(Stage)buttonviewbook.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("View_Book_User.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        logs=new Log();
        logs.logfiles("Go To View And Search  Books");
       
    }

    @FXML
    private void buttonupdateHandle(ActionEvent event) throws IOException {
         stage=(Stage)buttonlogout.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("Update_Information_User.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    logs=new Log();
                    logs.logfiles("Go To Update User Information");
                    
    }
    @FXML
    private void buttonlogoutHandle(ActionEvent event) throws IOException {
         stage=(Stage)buttonlogout.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                     logs=new Log();
                    logs.logfiles("Go To Login Page");
                     
    }
    @FXML
    private void buttonshowHandle(ActionEvent event) throws IOException {
         stage=(Stage)buttonlogout.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("Show_BorrowBook.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                     logs=new Log();
                    logs.logfiles("Go To Show Borrow Book");

    }
    
}
