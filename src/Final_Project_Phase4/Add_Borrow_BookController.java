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
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class Add_Borrow_BookController implements Initializable {

    @FXML
    private TextField txtuserid;
    @FXML
    private Label lblerror1;
    @FXML
    private Label lblerror2;
    @FXML
    private Label lblerror3;
    @FXML
    private ComboBox<Integer> comboxisbn;
    @FXML
    private DatePicker borrowdate;
    @FXML
    private TableView<Book> tableviewbooks;
    @FXML
    private TableColumn<Book,String> tcbookname;
    @FXML
    private TableColumn<Book,Integer> tcIsbn;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonReset; 
    @FXML
    private Button buttonhome;
    @FXML 
    private Label lblsuccess;
     private Statement statement;
    private ResultSet rs;
    private Statement stat2;
    private ResultSet rs2;
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
        logs.logfiles("Welcome To Add And View Borrow Book");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        tcbookname.setCellValueFactory(new PropertyValueFactory("bookname"));
        tcIsbn.setCellValueFactory(new PropertyValueFactory("bookId"));
       combox();
        
      try {
             rs=this.statement.executeQuery("SELECT bookid,bookname from books");
                while(rs.next()){
                Book book = new Book();
                book.setBookname(rs.getString("bookname"));
                book.setBookId(rs.getInt("bookid"));
                book.setAuther("");
                book.setTypename("");
                tableviewbooks.getItems().add(book);
              
                
                 
            }
            logs=new Log();
            logs.logfiles("Show All Borrow Books");
        } catch (SQLException ex) {
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(ex.getMessage());
             alert.showAndWait();        }
    }    

    @FXML
    private void buttonAddHandle(ActionEvent event) throws SQLException {
        if(!txtuserid.getText().equals(""))
        {
            if(comboxisbn.getValue()!=null)
            {
                if(borrowdate.getValue()!=null)
                {
                    
                        lblerror1.setText("");
                        lblerror2.setText("");
                        lblerror3.setText("");
                        Integer userid=Integer.parseInt(txtuserid.getText());
                        Integer Bookid=comboxisbn.getValue();
                        LocalDate borrowdates=borrowdate.getValue();
                        String sql2="Insert Into borrow values("+userid+","+Bookid+",'"+borrowdates+"')"; 
                        this.statement.executeUpdate(sql2);
                        lblsuccess.setText("Add New Borrow Book is Successfully ");
                        logs=new Log();
                         logs.logfiles("Insert New Borrow Book:: "+"User Id::"+userid+", Book Id::"+Bookid+", Borrow Date::"+borrowdates);
                          
                }
                else
                {
                    lblerror3.setText("Please Enter Borrow Date");
                }
            }
            else
            {
                lblerror2.setText("Please Enter ISBN");
            }
        }
        else
        {
            lblerror1.setText("Please Enter Your user Id");
        }
    }

    @FXML
    private void buttonResetHandle(ActionEvent event) {
         txtuserid.setText("");
         lblerror1.setText("");
        lblerror2.setText("");
        lblerror3.setText("");
         lblsuccess.setText("");
        borrowdate.setValue(null);
      
    }
    private void combox()
    {
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/final_project_pr3?serverTimezone=UTC","root","");
        this.stat2=con.createStatement();
        
        }
        catch(Exception ex)
        {
           Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(ex.getMessage());
             alert.showAndWait();
        }
        try {
            rs2=stat2.executeQuery("select bookid from books");
            while(rs2.next())
            {
                Integer typeids=rs2.getInt("bookId");
                     
                comboxisbn.getItems().addAll(typeids);
            }
             logs=new Log();
            logs.logfiles("fill Combox of Book ID");
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
                    logs.logfiles("Go To Admin Page");
    }

}
