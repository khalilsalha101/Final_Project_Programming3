/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Phase4;

import Final_Project_Phase4.*;
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
public class Update_Delete_UserController implements Initializable {

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
    private Label lblerror4;
    @FXML
    private TextField txtAddress;
    @FXML
    private DatePicker dateDOB;
    @FXML
    private Label lblerror5;
    @FXML
    private TableView<User> tableviewuser;
    @FXML
    private TableColumn<User, Integer> tcuserid;
    @FXML
    private TableColumn<User, String> tcusername;
    @FXML
    private TableColumn<User, String> tcgender;
    @FXML
    private TableColumn<User, String> tcAddress;
    @FXML
    private TableColumn<User, String> tcDOB;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonReset;
    @FXML
    private Button buttonhome;
    @FXML
    private Label lblsuccess1;
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
            logs.logfiles("Welcome To Update And Delete User");
        
        }
        catch(Exception ex)
        {
             Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
         tcuserid.setCellValueFactory(new PropertyValueFactory("userid"));
        tcusername.setCellValueFactory(new PropertyValueFactory("username"));
        tcgender.setCellValueFactory(new PropertyValueFactory("gender"));
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
        
        tableviewuser.getSelectionModel().selectedItemProperty().addListener(
                event-> showSelectedUser()
          );    }    

    @FXML
    private void buttonUpdateHandle(ActionEvent event) throws SQLException {
      if(!txtuserid.getText().equals(""))
     {
         if(!txtusername.getText().equals(""))
         {
             if(!txtgender.getText().equals("") && (txtgender.getText().equalsIgnoreCase("female")||txtgender.getText().equalsIgnoreCase("male")))
             {
                if(!txtAddress.getText().equals(""))
                {
                    if(dateDOB.getValue()!=null)
                    {
                        lblerror1.setText("");
                        lblerror2.setText("");
                        lblerror3.setText("");
                        lblerror4.setText("");
                        lblerror5.setText("");
                         Integer userid=Integer.parseInt(txtuserid.getText());
                         String username=txtusername.getText();
                         String gender=txtgender.getText();
                         String address=txtAddress.getText();
                         LocalDate DoB=dateDOB.getValue();
                         String sql = "Update Users Set Username='" + username + "', gender='" + 
                                            gender + "', address='" + address + "',DOB='"+DoB+"' Where userid=" +userid;
                         this.statement.executeUpdate(sql);
                         lblsuccess1.setText("Update User is Successfuly");
                         tableviewuser.getItems().clear();
                         referachTable();
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
    private void buttonDeleteHandle(ActionEvent event) throws SQLException {
           if(!txtuserid.getText().equals(""))
            {
                Integer userid=Integer.parseInt(txtuserid.getText());
                String sql="Delete from users where userid="+userid;
                this.statement.executeUpdate(sql);
                lblsuccess1.setText("Delete User is Successfuly");
                tableviewuser.getItems().clear();
                referachTable();
                logs=new Log();
                logs.logfiles("Delete User for User Id::"+userid);
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
        txtgender.setText("");
        txtAddress.setText("");
        lblerror1.setText("");
        lblerror2.setText("");
        lblerror3.setText("");
        lblerror4.setText("");
        lblerror5.setText("");
        dateDOB.setValue(null);
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
      private void showSelectedUser(){
        User user = tableviewuser.getSelectionModel().getSelectedItem();
        if( user!= null){
        txtuserid.setText(String.valueOf(user.getUserid()));
        txtusername.setText(user.getUsername());
        txtgender.setText(user.getGender());
        txtAddress.setText(user.getAddress());
        
        }

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
            logs.logfiles("Select All User in DataBase");
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
