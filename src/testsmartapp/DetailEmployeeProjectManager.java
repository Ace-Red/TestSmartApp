package testsmartapp;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import testsmartapp.ConnectionDB;
import testsmartapp.ViewEmployee;

public class DetailEmployeeProjectManager extends JFrame implements ActionListener {
 
    
    public void actionPerformed(ActionEvent ae) {
            setVisible(false);
            new ViewEmployeeProjectManager();
    }
    public static void main(String[] args) {
        
    }
}
