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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class Add_New_UserController implements Initializable {
    
    @FXML
    private TextField txtuserid;
    @FXML
    private Label lblerror1;
    @FXML
    private Label lblerror2;
    @FXML
    private Label lblerror3;
    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtgender;
    @FXML
    private TextField txtaddress;
    @FXML
    private DatePicker DOBdate;
    @FXML
    private Label lblerror4;
    @FXML
    private Label lblerror5;
    @FXML
    private Label lblsuccess;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonReset;
    @FXML
    private Button buttongologin;
   
    private Statement statement;
    private ResultSet rs;
    private Stage stage;
    private Parent root;
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
        logs.logfiles("Welcome  To Sign Up Page");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }    

    @FXML
    private void buttonAddHandle(ActionEvent event) throws SQLException {
    if(!txtuserid.getText().equals(""))
     {
         if(!txtusername.getText().equals(""))
         {
             if(!txtgender.getText().equals("") && (txtgender.getText().equalsIgnoreCase("female")||txtgender.getText().equalsIgnoreCase("male")))
             {
                if(!txtaddress.getText().equals(""))
                {
                    if(DOBdate.getValue()!=null)
                    {
                        lblerror1.setText("");
                        lblerror2.setText("");
                        lblerror3.setText("");
                        lblerror4.setText("");
                        lblerror5.setText("");
                         Integer userid=Integer.parseInt(txtuserid.getText());
                         String username=txtusername.getText();
                         String gender=txtgender.getText();
                         String address=txtaddress.getText();
                         LocalDate DoB=DOBdate.getValue();
                         String sql2="Insert Into users values("+userid+",'"+username+"','"+gender+"','"+address+"','"+DoB+"')"; 
                         this.statement.executeUpdate(sql2);
                         lblsuccess.setText("Add Succesfuly");
                         logs=new Log();
                         logs.logfiles("Add Successfuly for new User:: "+"username:: "+username+" gender:: "+gender+" address::"+address+" DOB::"+DoB);
                         
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
        lblerror1.setText("");
        lblerror2.setText("");
        lblerror3.setText("");
        lblerror4.setText("");
        lblerror5.setText("");
        txtuserid.setText("");
        txtusername.setText("");
        txtgender.setText("");
        txtaddress.setText("");
        DOBdate.setValue(null);
         lblsuccess.setText("");
       }

    @FXML
    private void buttongologinHandle(ActionEvent event) throws IOException {
        stage=(Stage)buttongologin.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    logs=new Log();
                    logs.logfiles("Go To Login Page");

    }
    
}
