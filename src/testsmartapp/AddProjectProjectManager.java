package testsmartapp;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddProjectProjectManager extends JFrame implements ActionListener{
    
    JTextField textfstatus, textfenddate, textftype, textfstartdate, textfcomment;
    JButton add, back;
    Choice chtype;
    AddProjectProjectManager() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Project");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        JLabel labeltype = new JLabel("Project Type");
        labeltype.setBounds(400, 200, 150, 30);
        labeltype.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeltype);
        
        chtype = new Choice();
        chtype.setBounds(600, 200, 150, 30);
        add(chtype);
        
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.projecttype ORDER BY id");
            while(rs.next()){
                chtype.add(rs.getString("typename"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        JLabel labelstartdate = new JLabel("Start Date");
        labelstartdate.setBounds(50, 250, 150, 30);
        labelstartdate.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelstartdate);
        
        textfstartdate = new JTextField();
        textfstartdate.setBounds(200, 250, 150, 30);
        add(textfstartdate);
        
        JLabel labelenddate = new JLabel("End Date");
        labelenddate.setBounds(400, 250, 150, 30);
        labelenddate.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelenddate);
        
        textfenddate = new JTextField();
        textfenddate.setBounds(600, 250, 150, 30);
        add(textfenddate);
       
        
        JLabel labelcomment = new JLabel("Comment");
        labelcomment.setBounds(50, 300, 150, 30);
        labelcomment.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelcomment);
        
        textfcomment = new JTextField();
        textfcomment.setBounds(200, 300, 150, 30);
        add(textfcomment);
        
        JLabel labelstatus = new JLabel("Status(t/f)");
        labelstatus.setBounds(400, 300, 150, 30);
        labelstatus.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelstatus);
        
        textfstatus = new JTextField();
        textfstatus.setBounds(600, 300, 150, 30);
        add(textfstatus);
        
        add = new JButton("Add");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setOpaque(true);
        add.setBorderPainted(false);
        add(add);
        
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
        if (ae.getSource() == add) {
            String startdate = textfstartdate.getText();
            String status = textfstatus.getText();
            
            String query1 = "SELECT * FROM public.projecttype WHERE typename = '"+chtype.getSelectedItem()+"'";
            String typeId = "";
            try {
                ConnectionDB c = new ConnectionDB();
                ResultSet rs = c.s.executeQuery(query1);
                while(rs.next()){
                typeId = rs.getString("id");
                
                }
            }
            catch (Exception e) {
            e.printStackTrace();
            }
            
            int type = Integer.parseInt(typeId);
            String enddate = textfenddate.getText();
            String comment = textfcomment.getText();
            
            try {
                ConnectionDB conn = new ConnectionDB();
                String query = "INSERT INTO public.project (projecttypeid, startdate, enddate, projectmanagerid, comment, status) VALUES ('"+type+"', '"+startdate+"', '"+enddate+"', '"+Account.id+"', '"+comment+"', '"+status+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Project add successfully");
                setVisible(false);
                new ViewProjectsProjectManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new ViewProjectsProjectManager();
        }
    }

    public static void main(String[] args) {
        new AddProjectProjectManager();
    }
}