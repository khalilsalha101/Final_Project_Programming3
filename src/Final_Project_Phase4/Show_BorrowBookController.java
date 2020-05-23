/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Phase4;

import Final_Project_Phase2.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class Show_BorrowBookController implements Initializable {

    @FXML
    private TextField txtuserid;
    @FXML
    private Label lblerror;
    @FXML
    private TableView<Borrow> tableviewbooks;
    @FXML
    private TableColumn<Borrow, String> tcbookname;
    @FXML
    private TableColumn<Borrow, Integer> tcIsbn;
    @FXML
    private TableColumn<Borrow,String> tcborrowdate;
    @FXML
    private Button buttonShow;
    @FXML
    private Button buttonReset;
    @FXML
    private Button buttonhome;
    private Stage stage;
    private Parent root;
    private Statement statement;
    private ResultSet rs;
    private Log logs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/final_project_pr3?serverTimezone=UTC","root","");
        this.statement=con.createStatement();
         logs=new Log();
            logs.logfiles("Welcome To Show Borrow Book For User Page");
        
        }
        catch(Exception ex)
        {
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(ex.getMessage());
             alert.showAndWait();
        }
     
        tcbookname.setCellValueFactory(new PropertyValueFactory("bookname"));
        tcIsbn.setCellValueFactory(new PropertyValueFactory("bookid"));
        tcborrowdate.setCellValueFactory(new PropertyValueFactory("borrowdate"));
        
    }    

    @FXML
    private void buttonShowHandle(ActionEvent event) {
         try {
             
             
             if(!txtuserid.getText().equals(""))
             {
                rs=this.statement.executeQuery("SELECT u.UserName,u.UserID,bo.Bookname,bo.BookID,b.BorrowDate from borrow b INNER join users u on b.UID=u.UserID INNER join books bo on b.BID=bo.BookID where u.UserID="+txtuserid.getText());
                while(rs.next()){
                Borrow borrow = new Borrow();
                borrow.setBookname(rs.getString("bookname"));
                borrow.setBookid(rs.getInt("bookid"));
                borrow.setBorrowdate(rs.getString("borrowdate"));
               
                tableviewbooks.getItems().add(borrow);
            }
                logs=new Log();
                logs.logfiles("Show All Borrow Book For User is Borrow This Book");
                
            }
             else
             {
                 lblerror.setText("Please Enter User Id");
             }
        } catch (SQLException ex) {
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(ex.getMessage());
             alert.showAndWait();
        }
    }

    @FXML
    private void buttonResetHandle(ActionEvent event) {
        txtuserid.setText("");
        lblerror.setText("");
        tableviewbooks.getItems().clear();
    }

    @FXML
    private void buttonhomeHandle(ActionEvent event) throws IOException {
        stage=(Stage)buttonhome.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("User_Page.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    logs=new Log();
            logs.logfiles("Go To User Page");
    }
    
}
