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
public class ViewBooksController implements Initializable {

    @FXML
    private Button buttonsearch;
    @FXML
    private TextField txtsearchbook;
    @FXML
    private TableView<Book> tableviewbook;
    @FXML
    private TableColumn<Book, Integer> tcbookid;
    @FXML
    private TableColumn<Book, String> tcbookname;
    @FXML
    private TableColumn<Book, String> tcauther;
    @FXML
    private TableColumn<Book, String> tcTypename;
    @FXML
    private Button buttonshow;
      @FXML
    private Button buttonhome;
    private Log logs;

    /**
     * Initializes the controller class.
     */
    private Statement statement;
    private ResultSet rs;
    private Stage stage;
    private Parent root;
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
        
        tcbookid.setCellValueFactory(new PropertyValueFactory("bookId"));
        tcbookname.setCellValueFactory(new PropertyValueFactory("bookname"));
        tcauther.setCellValueFactory(new PropertyValueFactory("auther"));
        tcTypename.setCellValueFactory(new PropertyValueFactory("typename"));
        
                
    }    

    @FXML
    private void buttonsearchHandel(ActionEvent event) {
      
        String bookname=txtsearchbook.getText();
        try {
             rs=this.statement.executeQuery("SELECT b.BookID,b.Bookname,b.Auther,bt.typename"
                     + " FROM books b INNER JOIN booktype bt on b.TID=bt.TypeID"
                     + " where b.Bookname='"+bookname+"'");
                while(rs.next()){
                Book booklist = new Book();
                booklist.setBookId(rs.getInt("bookId"));
                booklist.setBookname(rs.getString("bookname"));
                booklist.setAuther(rs.getString("auther"));
                booklist.setTypename(rs.getString("typename"));
                tableviewbook.getItems().setAll(booklist);
              
                
                 
            }
                logs=new Log();
        logs.logfiles("Select All Books");
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
        
        
    }
    @FXML
    private void buttonshowhandel(ActionEvent event) {
         try {
             tableviewbook.getItems().clear();
             rs=this.statement.executeQuery("SELECT b.BookID,b.Bookname,b.Auther,bt.typename FROM books b INNER JOIN booktype bt on b.TID=bt.TypeID");
                while(rs.next()){
                Book booklist = new Book();
                booklist.setBookId(rs.getInt("bookId"));
                booklist.setBookname(rs.getString("bookname"));
                booklist.setAuther(rs.getString("auther"));
                booklist.setTypename(rs.getString("typename"));
                tableviewbook.getItems().add(booklist);
            }
                logs=new Log();
        logs.logfiles("Select All Books");
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
