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
public class Admin_PageController implements Initializable {

    @FXML
    private Button buttonAddBook;
    @FXML
    private Button buttonViewBorrow;
    @FXML
    private Button buttonViewUser;
    @FXML
    private Button buttonViewBook1;
    @FXML
    private Button buttonupdatedelete;
     @FXML
    private Button buttonupdatedeleteuser;
     @FXML 
     private Button buttonlogout;
    private Stage stage;
    private Parent root;
    private Log logs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logs=new Log();
        logs.logfiles("Welcome To Admin Page");
    }    

    @FXML
    private void buttonAddBookHandel(ActionEvent event) throws IOException {
        stage=(Stage)buttonViewBorrow.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("Add_Book.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        logs=new Log();
        logs.logfiles("Go To Add and View Book Page");
         
    }

    @FXML
    private void buttonViewBorrowHandel(ActionEvent event) throws IOException {
        stage=(Stage)buttonViewBorrow.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("View_Brrorw_Book.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        logs=new Log();
        logs.logfiles("Go To  View Borrow Book Page");
    }

    @FXML
    private void buttonViewUserHandel(ActionEvent event) throws IOException {
        stage=(Stage)buttonViewUser.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("Add_And_View_User.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        logs=new Log();
        logs.logfiles("Go To Add and View User Page");
    }

    @FXML
    private void buttonViewBookHandel(ActionEvent event) throws IOException {
        stage=(Stage)buttonViewBook1.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("ViewBooks.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        logs=new Log();
        logs.logfiles("Go To Search and View Book Page");
    }

    @FXML
    private void buttonupdatedeleteHandel(ActionEvent event) throws IOException {
     stage=(Stage)buttonViewBook1.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("Update_View_Book.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();    
        logs=new Log();
        logs.logfiles("Go To Update and Delete Book Page");
    }
    
      @FXML
    private void buttonupdatedeleteuserHandle(ActionEvent event) throws IOException {
         stage=(Stage)buttonViewBook1.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("Update_Delete_User.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        logs=new Log();
        logs.logfiles("Go To Update and Delete User Page");
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
    
    
}
