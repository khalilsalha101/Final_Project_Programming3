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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class Add_And_View_UserController implements Initializable {

    @FXML
    private TextField txtuserid;
    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtgeneder;
   @FXML
    private TextField txtaddress;
    
    @FXML
    private Label lblerror1;
    @FXML
    private Label lblerror2;
    @FXML
    private Label lblerror3;
    @FXML
    private Label lblerror4;
   
    @FXML
    private Label lblerror5;
    @FXML
    private TableView<User> tableviewuser;
    @FXML
    private TableColumn<User, Integer> tcUserid;
    @FXML
    private TableColumn<User, String> tcUsername;
    @FXML
    private TableColumn<User, String> tcGender;
    @FXML
    private TableColumn<User, String> tcAddress;
    @FXML
    private TableColumn<User,String> tcDOB;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonReset;
    @FXML
    private DatePicker txtDOB;
    private Statement statement;
    private ResultSet rs;
    private Stage stage;
    private  Parent root;
    @FXML
    private Button buttonhome;
    @FXML
    private Label lblsuccess;
    private Log logs;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try
        {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/final_project_pr3?serverTimezone=UTC","root","");
        this.statement=con.createStatement();
        logs=new Log();
        logs.logfiles("Welcome To Add And View User");
        
        
        }
        catch(Exception ex)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(ex.getMessage());
             alert.showAndWait();
        }
        
        tcUserid.setCellValueFactory(new PropertyValueFactory("userid"));
        tcUsername.setCellValueFactory(new PropertyValueFactory("username"));
        tcGender.setCellValueFactory(new PropertyValueFactory("gender"));
        tcAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tcDOB.setCellValueFactory(new PropertyValueFactory("dob"));
        try {
             rs=this.statement.executeQuery("select * from users");
                while(rs.next()){
                User userlist = new User();
                userlist.setUserid(rs.getInt("userid"));
                userlist.setUsername(rs.getString("username"));
                userlist.setGender(rs.getString("gender"));
                userlist.setAddress(rs.getString("address"));
                userlist.setDob(rs.getString("dob"));
                tableviewuser.getItems().add(userlist);
                
                }
                 logs=new Log();
                 logs.logfiles("Show All User in DataBase");
                 
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(ex.getMessage());
             alert.showAndWait();
        }
        
    }    

    @FXML
    private void buttonAddHandle(ActionEvent event) throws SQLException {
    
     if(!txtuserid.getText().equals(""))
     {
         if(!txtusername.getText().equals(""))
         {
             if(!txtgeneder.getText().equals("") && (txtgeneder.getText().equalsIgnoreCase("female")||txtgeneder.getText().equalsIgnoreCase("male")))
             {
                if(!txtaddress.getText().equals(""))
                {
                    if(txtDOB.getValue()!=null)
                    {
                        lblerror1.setText("");
                        lblerror2.setText("");
                        lblerror3.setText("");
                        lblerror4.setText("");
                        lblerror5.setText("");
                         Integer userid=Integer.parseInt(txtuserid.getText());
                         String username=txtusername.getText();
                         String gender=txtgeneder.getText();
                         String address=txtaddress.getText();
                         LocalDate DoB=txtDOB.getValue();
                         String sql2="Insert Into users values("+userid+",'"+username+"','"+gender+"','"+address+"','"+DoB+"')"; 
                         this.statement.executeUpdate(sql2);
                         tableviewuser.getItems().clear();
                         referachTable();
                         lblsuccess.setText("Add New User is Successfully");
                          logs=new Log();
                          logs.logfiles("Add New User in DataBase");
                         
                    }
                    else
                    {
                        lblerror5.setText("Please Enter DOB");
                    }
                }
                else
                {
                    lblerror4.setText("Please Enter Address");
                }
             }
             else
             {
                 lblerror3.setText("Please Enter Gender");
             }
         }
         else
         {
             lblerror2.setText("Please Enter User Name");
         }
         
     }
     else
     {
         lblerror1.setText("Please Enter UserId");
     }
                       
        
        
    }

    @FXML
    private void buttonResetHandle(ActionEvent event) {
        txtuserid.setText("");
        txtusername.setText("");
        txtgeneder.setText("");
        txtaddress.setText("");
        lblerror1.setText("");
        lblerror2.setText("");
        lblerror3.setText("");
        lblerror4.setText("");
        lblerror5.setText("");
        lblsuccess.setText("");
        txtDOB.setValue(null);
            
    }
      public void referachTable()
    {
         try {
             rs=this.statement.executeQuery("select * from users");
                while(rs.next()){
                User userlist = new User();
                userlist.setUserid(rs.getInt("userid"));
                userlist.setUsername(rs.getString("username"));
                userlist.setGender(rs.getString("gender"));
                userlist.setAddress(rs.getString("address"));
                userlist.setDob(rs.getString("dob"));
                tableviewuser.getItems().add(userlist);
              
                
                 
            }
             logs=new Log();
             logs.logfiles("Show All User in DataBase");    
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
