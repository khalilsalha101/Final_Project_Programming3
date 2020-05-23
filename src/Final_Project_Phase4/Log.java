/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Phase4;

import Final_Project_Phase2.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Alert;


/**
 *
 * @author khalil
 */
public class Log {
    public void logfiles(String str) 
    {
    try{
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
           String filename="logfile.log";
           FileWriter fileWriter = new FileWriter(filename,true);
           PrintWriter printWriter = new PrintWriter(fileWriter);
           printWriter.printf("\n"+formatter.format(date)+" :: "+str);
           printWriter.close();
    }
     catch(IOException ex)
        {
            Alert aletr=new Alert(Alert.AlertType.ERROR);
            aletr.setContentText(ex.getMessage());
            aletr.showAndWait();
        }
    }

}
