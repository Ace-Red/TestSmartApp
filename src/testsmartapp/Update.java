package testsmartapp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Update extends JFrame implements ActionListener{
    
    JTextField textfstatus, textfname, textfphoto, textfoutoffbalance, textfpassword;
    JButton update, back;
    String fullname;
    Choice positionch, peoplepartnerch, subdivisionch;
    Update(String fullname) {
        this.fullname = fullname;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        JLabel labelname = new JLabel("Full Name");
        labelname.setBounds(50, 200, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);
        
        textfname = new JTextField();
        textfname.setBounds(200, 200, 150, 30);
        add(textfname);
        
        JLabel labeloutoffbalance = new JLabel("Out office balance");
        labeloutoffbalance.setBounds(400, 200, 150, 30);
        labeloutoffbalance.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeloutoffbalance);
        
        textfoutoffbalance = new JTextField();
        textfoutoffbalance.setBounds(600, 200, 150, 30);
        add(textfoutoffbalance);
        
        JLabel labelpassword = new JLabel("Password");
        labelpassword.setBounds(50, 250, 150, 30);
        labelpassword.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelpassword);
        
        textfpassword = new JTextField();
        textfpassword.setBounds(200, 250, 150, 30);
        add(textfpassword);
        
        JLabel labelsubvision = new JLabel("Subdivision");
        labelsubvision.setBounds(400, 250, 150, 30);
        labelsubvision.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsubvision);
        
        subdivisionch = new Choice();
        subdivisionch.setBounds(600, 250, 150, 30);
        add(subdivisionch);
        
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.subdivision ORDER BY id");
            while(rs.next()){
                subdivisionch.add(rs.getString("name"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        JLabel labelposition = new JLabel("Position");
        labelposition.setBounds(50, 300, 150, 30);
        labelposition.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelposition);
        
        positionch = new Choice();
        positionch.setBounds(200, 300, 150, 30);
        add(positionch);   
        
       try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.position ORDER BY id");
            while(rs.next()){
                positionch.add(rs.getString("name"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        JLabel labelstatus = new JLabel("Status(t/f)");
        labelstatus.setBounds(400, 300, 150, 30);
        labelstatus.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelstatus);
        
        textfstatus = new JTextField();
        textfstatus.setBounds(600, 300, 150, 30);
        add(textfstatus);
        
        JLabel labelpeoplepartner = new JLabel("People Partner");
        labelpeoplepartner.setBounds(50, 350, 150, 30);
        labelpeoplepartner.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelpeoplepartner);
        
        peoplepartnerch = new Choice();
        peoplepartnerch.setBounds(200, 350, 150, 30);
        add(peoplepartnerch);
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.employee ORDER BY fullname");
            while(rs.next()){
                peoplepartnerch.add(rs.getString("fullname"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        JLabel labelphoto = new JLabel("Photo");
        labelphoto.setBounds(400, 350, 150, 30);
        labelphoto.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphoto);
        
        textfphoto = new JTextField();
        textfphoto.setBounds(600, 350, 150, 30);
        add(textfphoto);
        String query = "SELECT * FROM public.employee WHERE fullname = '"+fullname+"'";
        String query1 = "";
        String query2 = "";
        String query3 = "";
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                textfname.setText(rs.getString("fullname"));
                textfstatus.setText(rs.getString("status"));
                textfphoto.setText(rs.getString("photo"));
                textfoutoffbalance.setText(rs.getString("outofofficebalance"));
                textfpassword.setText(rs.getString("password")); 
                query1 = "SELECT * FROM public.subdivision WHERE id = '"+rs.getString("subdivisionid")+"'";
                query2 = "SELECT * FROM public.position WHERE id = '"+rs.getString("positionid")+"'";
                query3 = "SELECT * FROM public.employee WHERE id = '"+rs.getString("peoplepartnerid")+"'";
            }
            ResultSet rs1 = c.s.executeQuery(query1);
            while(rs1.next()){
                subdivisionch.select(rs1.getString("name"));
            }
            ResultSet rs2 = c.s.executeQuery(query2);
            while(rs2.next()){
                positionch.select(rs2.getString("name"));
            }
            ResultSet rs3 = c.s.executeQuery(query3);
            while(rs3.next()){
                peoplepartnerch.select(rs3.getString("fullname"));
            }
            
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        update = new JButton("Update");
        update.setBounds(250, 550, 150, 40);
        update.addActionListener(this);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setOpaque(true);
        update.setBorderPainted(false);
        add(update);
        
        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setOpaque(true);
        back.setBorderPainted(false);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String fname = textfname.getText();
            String status = textfstatus.getText();
            String photo;
            if (textfphoto.getText().equals("")){
                photo = "null";
            }
            else{
                photo = textfphoto.getText();
            }
            System.out.println(textfphoto.getText());
            System.out.println(photo);
            String query1 = "SELECT * FROM public.position WHERE name = '"+positionch.getSelectedItem()+"'";
            String posId = "";
            String query2 = "SELECT * FROM public.employee WHERE fullname = '"+peoplepartnerch.getSelectedItem()+"'";
            String partnerId = "";
            String query3 = "SELECT * FROM public.subdivision WHERE name = '"+subdivisionch.getSelectedItem()+"'";
            String subId = "";
            try {
                ConnectionDB c = new ConnectionDB();
                ResultSet rs = c.s.executeQuery(query1);
                while(rs.next()){
                posId = rs.getString("id");
                }
            }
            catch (Exception e) {
            e.printStackTrace();
            }
            try {
                ConnectionDB c = new ConnectionDB();
                ResultSet rs2 = c.s.executeQuery(query2);
                while(rs2.next()){
                    partnerId = rs2.getString("id");
                }
            }
            catch (Exception e) {
            e.printStackTrace();
            }
            try {
                ConnectionDB c = new ConnectionDB();
                ResultSet rs3 = c.s.executeQuery(query3);
                while(rs3.next()){
                    subId = rs3.getString("id");
                }
            }
            catch (Exception e) {
            e.printStackTrace();
            }
            String position = posId;
            String peoplepartner = partnerId;
            String subdivision = subId;
            String outoffbalance = textfoutoffbalance.getText();
            String password = textfpassword.getText();
            
            try {
                ConnectionDB conn = new ConnectionDB();
                String query = "UPDATE public.employee SET fullname = '"+fname+"', status = '"+status+"', subdivisionid = '"+subdivision+"', photo = '"+photo+"', positionid =  '"+position+"', outofofficebalance = '"+outoffbalance+"', peoplepartnerid = '"+peoplepartner+"', password = '"+password+"' WHERE fullname = '"+fullname+"'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new ViewEmployee();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new ViewEmployee();
        }
    }

    public static void main(String[] args) {
        new Update("");
    }
}
