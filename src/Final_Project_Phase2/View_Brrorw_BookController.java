/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Phase2;

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
public class View_Brrorw_BookController implements Initializable {

    @FXML
    private Button buttonsearch;
    @FXML
    private TextField txtsearch;
    @FXML
    private TableView<Borrow> tableviewBorrow;
    @FXML
    private TableColumn<Borrow,String> tcUserName;
    @FXML
    private TableColumn<Borrow,Integer> tcUserId;
    @FXML
    private TableColumn<Borrow, String> tcBookName;
    @FXML
    private TableColumn<Borrow, Integer> tcBookId;
    @FXML
    private TableColumn<Borrow,String> tcBorrowDate;
    @FXML
    private Button buttonshow;
    @FXML
    private Button buttonhome;
    private Statement statement;
    private ResultSet rs;
    private Stage stage;
    private  Parent root;
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
        logs.logfiles("Welcome To View Borrow Books");
        }
        catch(Exception ex)
        {
           Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
        tcUserName.setCellValueFactory(new PropertyValueFactory("username"));
        tcUserId.setCellValueFactory(new PropertyValueFactory("userid"));
        tcBookName.setCellValueFactory(new PropertyValueFactory("bookname"));
        tcBookId.setCellValueFactory(new PropertyValueFactory("bookid"));
        tcBorrowDate.setCellValueFactory(new PropertyValueFactory("borrowdate"));
    }    

    @FXML
    private void buttonsearchHandle(ActionEvent event) {
    String bookname=txtsearch.getText();
        try {
             rs=this.statement.executeQuery("SELECT u.UserName,u.UserID,bo.Bookname,bo.BookID,b.BorrowDate from borrow b INNER join users u on b.UID=u.UserID INNER join books bo on b.BID=bo.BookID where bo.Bookname='"+bookname+"'");
                while(rs.next()){
                Borrow borrow = new Borrow();
                borrow.setUsername(rs.getString("username"));
                borrow.setUserid(rs.getInt("userid"));
                borrow.setBookname(rs.getString("bookname"));
                borrow.setBookid(rs.getInt("bookid"));
                borrow.setBorrowdate(rs.getString("borrowdate"));
                tableviewBorrow.getItems().setAll(borrow);
              
                
                 
            }
                 logs=new Log();
        logs.logfiles("Search for Borrow Book");
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void buttonshowHandle(ActionEvent event) {
        try {
             tableviewBorrow.getItems().clear();
             rs=this.statement.executeQuery("SELECT u.UserName,u.UserID,bo.Bookname,bo.BookID,b.BorrowDate from borrow b INNER join users u on b.UID=u.UserID INNER join books bo on b.BID=bo.BookID");
                while(rs.next()){
                Borrow borrow = new Borrow();
                borrow.setUsername(rs.getString("username"));
                borrow.setUserid(rs.getInt("userid"));
                borrow.setBookname(rs.getString("bookname"));
                borrow.setBookid(rs.getInt("bookid"));
                borrow.setBorrowdate(rs.getString("borrowdate"));
               
                tableviewBorrow.getItems().add(borrow);
            }
                 logs=new Log();
        logs.logfiles("Select All Borrow Book");
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();       
        }
    }
    @FXML
    private void buttonhomeHandle(ActionEvent event) throws IOException {
            stage=(Stage)buttonhome.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("Admin_Page.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    logs=new Log();
                    logs.logfiles("Go To Admin Page");
    }
}
