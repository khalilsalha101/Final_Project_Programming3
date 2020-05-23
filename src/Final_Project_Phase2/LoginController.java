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
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.util.calendar.BaseCalendar;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class LoginController implements Initializable {

   @FXML
   private Label lbllogin;
    @FXML
    private TextField txtusername;
    @FXML
    private Label lblerror1;
    @FXML
    private TextField txtuserid;
    @FXML
    private Label lblerror2;
    @FXML
    private Button buttonlogin;
    @FXML
    private Button buttonsignup;
    @FXML
    private Label lblerror3;
    private Statement statement;
    private ResultSet rs;
    private Statement stat2;
    private ResultSet rs2;
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
        this.stat2=con.createStatement();
        
       logs=new Log();
       logs.logfiles("Weclome To Login Form");
        
        
        
        }catch(Exception e)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        
        
       
         
       
    }    

    @FXML
    private void buttonloginhandle(ActionEvent event) throws SQLException, IOException {
        
      
        if(!txtusername.getText().equals(""))
        {
            if(!txtuserid.getText().equals(""))
            {
                lblerror1.setText("");
                lblerror2.setText("");
                String username=txtusername.getText();
                Integer userid=Integer.parseInt(txtuserid.getText());
                String adminname=txtusername.getText();
                Integer adminid=Integer.parseInt(txtuserid.getText());
                String sql="select UserID,UserName from users where UserName='"+username+"' and UserID="+userid+" ";
                String sql2="select admin_name,admin_Id from admin where admin_name='"+adminname+"' and admin_Id="+adminid+"";
                rs=this.statement.executeQuery(sql);
                rs2=this.stat2.executeQuery(sql2);
                 if(rs.next())
                 {
                    stage=(Stage)buttonlogin.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("User_Page.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    logs=new Log();
                    logs.logfiles("Login From User::"+rs.getString("username"));
                  
                 }
                else if(rs2.next())
                 {
                      
                    stage=(Stage)buttonlogin.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("Admin_Page.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    logs=new Log();
                    logs.logfiles("Login From Admin::"+rs2.getString("admin_name"));
                    
                 }
                 else
                 {
                      lblerror3.setText("Enter Correct User Name or User Id");
                      logs=new Log();
                    logs.logfiles("Error in Login process");
                      
                 }

            }
            else
            {
                lblerror2.setText("Please Enter User Id");
            }
            
        }
        else
        {
            lblerror1.setText("Please Enter User Name");
        }
        
        
    }

    @FXML
    private void buttonsignupHandle(ActionEvent event) throws IOException {
            stage=(Stage)buttonlogin.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getResource("Add_New_User.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    logs=new Log();
                    logs.logfiles("Go To Sign Up Page");
                    
       
        
    }
    
}
