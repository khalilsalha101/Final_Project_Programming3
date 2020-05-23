/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Phase3;

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
import javafx.scene.control.ComboBox;
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
public class Update_View_BookController implements Initializable {

    @FXML
    private TextField txtIsbn;
    @FXML
    private Label lblerror1;
    @FXML
    private Label lblerror2;
    @FXML
    private Label lblerror3;
    @FXML
    private TextField txtbookname;
    @FXML
    private TextField txtAuther;
    @FXML
    private Label lblerror4;
    @FXML
    private ComboBox<Integer> comboxtypeid;
    @FXML
    private TableView<Book> tableviewbooks;
    @FXML
    private TableColumn<Book,String> tcbookname;
    @FXML
    private TableColumn<Book, Integer> tcIsbn;
    @FXML
    private TableColumn<Book, String> tcAuther;
    @FXML
    private TableColumn<Book, Integer> tcTypeid;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonReset;
    @FXML
    private Button buttonhome;
    @FXML
    private Button buttonDelete;
    @FXML
    private Label lblsuccess1;
    private Stage stage;
    private Parent root;
    private Statement statement;
    private ResultSet rs;
     private Statement stat2;
    private ResultSet rs2;
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
                    logs.logfiles("Welcome Update and Delete Book Page");
        
        }
        catch(Exception ex)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
          tcIsbn.setCellValueFactory(new PropertyValueFactory("bookId"));
        tcbookname.setCellValueFactory(new PropertyValueFactory("bookname"));
        tcAuther.setCellValueFactory(new PropertyValueFactory("auther"));
        tcTypeid.setCellValueFactory(new PropertyValueFactory("tid"));
        
        try {
             rs=this.statement.executeQuery("select * from books");
                while(rs.next()){
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setBookname(rs.getString("bookname"));
                book.setAuther(rs.getString("auther"));
                book.setTid(rs.getInt("tid"));
              
                tableviewbooks.getItems().add(book);
         }
                logs=new Log();
                    logs.logfiles("Select All Books");
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();        
        }
        combox();
        tableviewbooks.getSelectionModel().selectedItemProperty().addListener(
                event-> showSelectedBook()
          );
    }    
    @FXML
    private void buttonUpdateHandle(ActionEvent event) throws SQLException {

         if(!txtIsbn.getText().equals(""))
                    {
                        if(!txtbookname.getText().equals(""))
                        {
                            if(!txtAuther.getText().equals(""))
                            {
                                if(comboxtypeid.getValue()!=null)
                                {
                                    
                                    Integer isbn=Integer.parseInt(txtIsbn.getText());
                                    String bookname=txtbookname.getText();
                                    String Auther=txtAuther.getText();
                                    Integer typeid=comboxtypeid.getValue();
                                       String sql = "Update Books Set Bookname='" + bookname + "', Auther='" + 
                                            Auther + "', tid=" + typeid + " Where bookid=" +isbn;
                                    this.statement.executeUpdate(sql);
                                    
                                    tableviewbooks.getItems().clear();

                                    referachTable();
                                     lblsuccess1.setText("Update Book is Successfuly");
                                     logs=new Log();
                                    logs.logfiles("Update Book for ISBN::"+isbn+"Update Book Name::"+bookname+", Auther::"+Auther+", TypeId::"+typeid);

                                }
                                else
                                {
                                     lblerror4.setText("Please Enter Type Id");
                                }
                            }
                            else
                            {
                                 lblerror3.setText("Please Enter The Auther Name");
                            }
                            
                        }
                        else
                        {
                            lblerror2.setText("Please Enter The Book Name");
                        }
                        
                    }
                    else
                    {
                        lblerror1.setText("Please Enter The ISBN for Book");
                    }
        
        
        

        
    } 
    public void referachTable()
    {
          try {
             rs=this.statement.executeQuery("select * from books");
                while(rs.next()){
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setBookname(rs.getString("bookname"));
                book.setAuther(rs.getString("auther"));
                book.setTid(rs.getInt("tid"));
              
                tableviewbooks.getItems().add(book);
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
    private void buttonResetHandle(ActionEvent event) {
        lblerror1.setText("");
        lblerror2.setText("");
        lblerror3.setText("");
        lblerror4.setText("");
        txtIsbn.setText("");
        txtbookname.setText("");
        txtAuther.setText("");
        lblsuccess1.setText("");
        
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
    private void showSelectedBook(){
        Book book = tableviewbooks.getSelectionModel().getSelectedItem();
        if( book!= null){
        txtIsbn.setText(String.valueOf(book.getBookId()));
        txtbookname.setText(book.getBookname());
        txtAuther.setText(book.getAuther());
        }

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
            rs2=stat2.executeQuery("select typeid from booktype");
            while(rs2.next())
            {
                Integer typeids=rs2.getInt("typeid");
                     
                comboxtypeid.getItems().addAll(typeids);
            }
             logs=new Log();
                    logs.logfiles("fill Combox of type id");
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private void buttonDeleteHandle(ActionEvent event) throws IOException, SQLException {
      if(!txtIsbn.getText().equals(""))
      {
          Integer isbn=Integer.parseInt(txtIsbn.getText());
         String sql="Delete from books where bookid="+isbn;
        this.statement.executeUpdate(sql);
        lblsuccess1.setText("Delete Book is Successfuly");
        tableviewbooks.getItems().clear();
        referachTable();
         logs=new Log();
         logs.logfiles("Delete Book for ISBN::"+isbn);
     }
      else
      {
          lblerror1.setText("Please Enter The ISBN for Book");
      }
   }

    
}
