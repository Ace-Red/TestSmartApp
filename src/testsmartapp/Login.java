package testsmartapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
        
public class Login extends JFrame implements ActionListener{
    JTextField textFieldUsername, textFieldPassword;
    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel username = new JLabel("Full Name");
        username.setBounds(40, 20, 100, 30);
        add(username);
        
        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(150, 20, 150, 30);
        add(textFieldUsername);
        
        JLabel password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        add(password);
        
        textFieldPassword = new JTextField();
        textFieldPassword.setBounds(150, 70, 150, 30);
        add(textFieldPassword);
        
        JButton login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        
        setSize(1000, 700);
        setLocation(450, 200);
        setVisible(true);
        
    }
    public void actionPerformed (ActionEvent ae){
        try {
            String dbUserName = textFieldUsername.getText();
            String dbPassword = textFieldPassword.getText();
            
            ConnectionDB c = new ConnectionDB();
            String query = "SELECT * FROM public.employee WHERE FullName = '"+dbUserName+"' AND Password = '"+dbPassword+"'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()){
                Account acc = new Account(rs.getString("id"));
                System.out.println(Account.id);
                System.out.println(Account.positionid);
                if(Account.positionid.equals("2")){
                    setVisible(false);
                    new Home();
                }
                else if (Account.positionid.equals("5")){
                    setVisible(false);
                    new HomeProjectManager();
                }
                else{
                    setVisible(false);
                    new HomeEmployee();
                }
               
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Value");
                setVisible(false);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
        new Login();
        
    }
}
