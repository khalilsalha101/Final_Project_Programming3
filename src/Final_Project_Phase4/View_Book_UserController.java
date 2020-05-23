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
public class View_Book_UserController implements Initializable {

    @FXML
    private Button buttonsearch;
    @FXML
    private TextField txtsearch;
    @FXML
    private TableView<Book> tableviewbook;
    @FXML
    private TableColumn<Book,Integer> tcisbn;
    @FXML
    private TableColumn<Book,String> tcbookname;
    @FXML
    private TableColumn<Book,String> tcauther;
    @FXML
    private TableColumn<Book,String> tctypename;
    @FXML
    private Button buttonshow;
     @FXML
    private Button buttonhome;
    private Statement statement;
    private ResultSet rs;
    private Stage stage;
    private Parent root;
    private  Log logs;
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
        logs.logfiles("Welcome To View Books");
        }
        catch(Exception ex)
        {
           Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
        tcisbn.setCellValueFactory(new PropertyValueFactory("bookId"));
        tcbookname.setCellValueFactory(new PropertyValueFactory("bookname"));
        tcauther.setCellValueFactory(new PropertyValueFactory("auther"));
        tctypename.setCellValueFactory(new PropertyValueFactory("typename"));
        
    }    

    @FXML
    private void buttonsearchHandle(ActionEvent event) {
          String bookname=txtsearch.getText();
        try {
             rs=this.statement.executeQuery("SELECT b.BookID,b.Bookname,b.Auther,bt.Typename,bt.TypeID from books b INNER join booktype bt on b.TID=bt.TypeID where b.Bookname='"+bookname+"'");
                while(rs.next()){
                Book booklist = new Book();
                booklist.setBookId(rs.getInt("bookId"));
                booklist.setBookname(rs.getString("bookname"));
                booklist.setAuther(rs.getString("auther"));
                booklist.setTypename(rs.getString("typename"));
                tableviewbook.getItems().setAll(booklist);
              
                
                 
            }
                 logs=new Log();
        logs.logfiles("Search Book");
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void buttonshowHandle(ActionEvent event) {
         try {
             tableviewbook.getItems().clear();
             rs=this.statement.executeQuery("SELECT b.BookID,b.Bookname,b.Auther,bt.Typename,bt.TypeID from books b INNER join booktype bt on b.TID=bt.TypeID");
                while(rs.next()){
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setBookname(rs.getString("bookname"));
                book.setAuther(rs.getString("auther"));
                book.setTypename(rs.getString("typename"));
             
               
                tableviewbook.getItems().add(book);
            }
                 logs=new Log();
        logs.logfiles("Select All Book");
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();        
        }
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
