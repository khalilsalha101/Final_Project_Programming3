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
public class Add_BookController implements Initializable {

    @FXML
    private TextField txtisbn;
    @FXML
    private TextField txtbookname;
    @FXML
    private TextField txtauther;
    @FXML
    private Label lblerror1;
    @FXML
    private Label lblerror2;
    @FXML
    private Label lblerror3;
    @FXML
    private Label lblerror4;
    @FXML
    private ComboBox<Integer> comboxtypeid;
    @FXML
    private TableView<BookType> tableviewbooktype;
    @FXML
    private TableColumn<BookType, Integer> tcTypeid;
    @FXML
    private TableColumn<BookType,String> tcTypename;
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
        logs.logfiles("Welcome To Add And View Book Page");
        
        }
        catch(Exception ex)
        {
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(ex.getMessage());
             alert.showAndWait();
        }
        
        tcTypeid.setCellValueFactory(new PropertyValueFactory("typeid"));
        tcTypename.setCellValueFactory(new PropertyValueFactory("typename"));
       combox();
        
      try {
             rs=this.statement.executeQuery("SELECT * from booktype");
                while(rs.next()){
                BookType booktype = new BookType();
                booktype.setTypeid(rs.getInt("typeid"));
                booktype.setTypename(rs.getString("typename"));
                tableviewbooktype.getItems().add(booktype);
              
                
                 
            }
            logs=new Log();
            logs.logfiles("Show All Book in DataBase");
        } catch (SQLException ex) {
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(ex.getMessage());
             alert.showAndWait();
        }
       
    }    

    @FXML
    private void buttonAddHandle(ActionEvent event) throws SQLException {
                        
                    if(!txtisbn.getText().equals(""))
                    {
                        if(!txtbookname.getText().equals(""))
                        {
                            if(!txtauther.getText().equals(""))
                            {
                                if(comboxtypeid.getValue()!=null)
                                {
                                    lblerror1.setText("");
                                    lblerror2.setText("");
                                    lblerror3.setText("");
                                    lblerror4.setText("");
                                    Integer isbn=Integer.parseInt(txtisbn.getText());
                                    String Bookname=txtbookname.getText();
                                    String auther=txtauther.getText();
                                    Integer Typeid=comboxtypeid.getValue();

                                    String sql2="Insert Into books values("+isbn+",'"+Bookname+"','"+auther+"',"+Typeid+")"; 
                                    this.statement.executeUpdate(sql2);
                                    lblsuccess.setText("Add New Book is Successfully");
                                    logs=new Log();
                                    logs.logfiles("Insert New Book :: "+" book name::"+Bookname+", ISBN::"+isbn+", Auther::"+auther+", Type ID::"+Typeid);
                                    
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

    @FXML
    private void buttonResetHandle(ActionEvent event) {
        txtisbn.setText("");
        txtbookname.setText("");
        txtauther.setText("");
        lblerror1.setText("");
        lblerror2.setText("");
        lblerror3.setText("");
        lblerror4.setText("");
         lblsuccess.setText("");
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
            logs.logfiles("Fill Combox of TypeId");
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
