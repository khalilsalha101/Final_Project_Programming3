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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class Update_Information_UserController implements Initializable {

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
    private Button buttonEdit;
    @FXML
    private Button buttonReset;
    @FXML
    private Button buttonHome;
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
            logs.logfiles("Welcome To Update Information for users");
        }
        catch(Exception ex)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }    

    @FXML
    private void txtuseridHandle(ActionEvent event) throws SQLException {
        try{
        String sql="select * from users where UserID="+txtuserid.getText()+"";
        rs=this.statement.executeQuery(sql);
        rs.next();
        txtusername.setText(rs.getString("username"));
        txtgender.setText(rs.getString("gender"));
        txtaddress.setText(rs.getString("address"));
        DOBdate.setValue((LocalDate.parse(rs.getString("dob"))));
        
        }
        catch(SQLException ex)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }

    @FXML
    private void buttonEditHandle(ActionEvent event) throws SQLException {
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
                         String sql = "Update Users Set Username='" + username + "', gender='" + 
                                            gender + "', address='" + address + "',DOB='"+DoB+"' Where userid=" +userid;
                         this.statement.executeUpdate(sql);
                        lblsuccess.setText("Update User inforamtion is Successfuly");
                         logs=new Log();
                        logs.logfiles("Update User for User Id::"+userid+"Update User Name::"+username+", gender::"+gender+", address::"+address+", DOB::"+DoB);
                         
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
    private void buttonHomeHandle(ActionEvent event) throws IOException {
          stage=(Stage)buttonHome.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("User_Page.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    logs=new Log();
                    logs.logfiles("Go To User Page");
        
    }
    
}
