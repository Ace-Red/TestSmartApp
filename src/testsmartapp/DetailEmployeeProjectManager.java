package testsmartapp;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import testsmartapp.ConnectionDB;
import testsmartapp.ViewEmployee;

public class DetailEmployeeProjectManager extends JFrame implements ActionListener {
    String fullname = "";
    JLabel textfstatus, textfname, textfphoto, textfoutoffbalance, textfpassword, positionch, peoplepartnerch, subdivisionch;
    JButton back;
    
    DetailEmployeeProjectManager(String fullname) {
        this.fullname = fullname;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Detail Employee");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        JLabel labelname = new JLabel("Full Name");
        labelname.setBounds(50, 200, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);
        
        textfname = new JLabel();
        textfname.setBounds(200, 200, 150, 30);
        textfname.setFont(new Font("serif", Font.PLAIN, 20));
        add(textfname);
        
        JLabel labeloutoffbalance = new JLabel("Out office balance");
        labeloutoffbalance.setBounds(400, 200, 150, 30);
        labeloutoffbalance.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeloutoffbalance);
        
        textfoutoffbalance = new JLabel();
        textfoutoffbalance.setBounds(600, 200, 150, 30);
        textfoutoffbalance.setFont(new Font("serif", Font.PLAIN, 20));
        add(textfoutoffbalance);
        
        JLabel labelpassword = new JLabel("Password");
        labelpassword.setBounds(50, 250, 150, 30);
        labelpassword.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelpassword);
        
        textfpassword = new JLabel();
        textfpassword.setBounds(200, 250, 150, 30);
        textfpassword.setFont(new Font("serif", Font.PLAIN, 20));
        add(textfpassword);
        
        JLabel labelsubvision = new JLabel("Subdivision");
        labelsubvision.setBounds(400, 250, 150, 30);
        labelsubvision.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsubvision);
        
        subdivisionch = new JLabel();
        subdivisionch.setBounds(600, 250, 150, 30);
        subdivisionch.setFont(new Font("serif", Font.PLAIN, 20));
        add(subdivisionch);
        
        JLabel labelposition = new JLabel("Position");
        labelposition.setBounds(50, 300, 150, 30);
        labelposition.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelposition);
        
        positionch = new JLabel();
        positionch.setBounds(200, 300, 150, 30);
        positionch.setFont(new Font("serif", Font.PLAIN, 20));
        add(positionch);   
                
        JLabel labelstatus = new JLabel("Status(t/f)");
        labelstatus.setBounds(400, 300, 150, 30);
        labelstatus.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelstatus);
        
        textfstatus = new JLabel();
        textfstatus.setBounds(600, 300, 150, 30);
        textfstatus.setFont(new Font("serif", Font.PLAIN, 20));
        add(textfstatus);
        
        JLabel labelpeoplepartner = new JLabel("People Partner");
        labelpeoplepartner.setBounds(50, 350, 150, 30);
        labelpeoplepartner.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelpeoplepartner);
        
        peoplepartnerch = new JLabel();
        peoplepartnerch.setBounds(200, 350, 150, 30);
        peoplepartnerch.setFont(new Font("serif", Font.PLAIN, 20));
        add(peoplepartnerch);
        
        
        try {
            ConnectionDB c = new ConnectionDB();
            String query = "SELECT * FROM public.employee where fullname = '"+fullname+"'";
            ResultSet rs = c.s.executeQuery(query);
            
            while(rs.next()) {
                textfname.setText(rs.getString("fullname"));
                textfoutoffbalance.setText(rs.getString("outofofficebalance"));
                textfpassword.setText(rs.getString("password"));
                subdivisionch.setText(rs.getString("subdivisionid"));
                positionch.setText(rs.getString("positionid"));
                textfstatus.setText(rs.getString("status"));
                peoplepartnerch.setText(rs.getString("peoplepartnerid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
            setVisible(false);
            new ViewEmployeeProjectManager();
    }
    public static void main(String[] args) {
        new DetailEmployeeProjectManager("");
    }
}
