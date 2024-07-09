package testsmartapp;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddLeaveRequestEmployee extends JFrame implements ActionListener{
    
    JTextField textfstartdate, textfenddate, textfcomment;
    JButton add, back;
    Choice reasonch;
    AddLeaveRequestEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Leave Request");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        JLabel labelstartdate = new JLabel("Start Date");
        labelstartdate.setBounds(50, 200, 150, 30);
        labelstartdate.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelstartdate);
        
        textfstartdate = new JTextField();
        textfstartdate.setBounds(200, 200, 150, 30);
        add(textfstartdate);
        
        JLabel labelenddate = new JLabel("End Date");
        labelenddate.setBounds(400, 200, 150, 30);
        labelenddate.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelenddate);
        
        textfenddate = new JTextField();
        textfenddate.setBounds(600, 200, 150, 30);
        add(textfenddate);
        
        JLabel labelcomment = new JLabel("Comment");
        labelcomment.setBounds(50, 250, 150, 30);
        labelcomment.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelcomment);
        
        textfcomment = new JTextField();
        textfcomment.setBounds(200, 250, 150, 30);
        add(textfcomment);
        
        JLabel labelsubvision = new JLabel("Subdivision");
        labelsubvision.setBounds(400, 250, 150, 30);
        labelsubvision.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsubvision);
        
        reasonch = new Choice();
        reasonch.setBounds(600, 250, 150, 30);
        add(reasonch);
        
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.absencereason ORDER BY id");
            while(rs.next()){
                reasonch.add(rs.getString("reason"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        add = new JButton("Add");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);
        
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
        if (ae.getSource() == add) {
            String startdate = textfstartdate.getText();
            String enddate = textfenddate.getText();
            String comment = textfcomment.getText();
            String query1 = "SELECT * FROM public.absencereason WHERE reason = '"+reasonch.getSelectedItem()+"'";
            String reasId = "";
            try {
                ConnectionDB c = new ConnectionDB();
                ResultSet rs = c.s.executeQuery(query1);
                while(rs.next()){
                reasId = rs.getString("id");
                }
            }
            catch (Exception e) {
            e.printStackTrace();
            }
            String reason = reasId;
            
            try {
                ConnectionDB conn = new ConnectionDB();
                String query = "INSERT INTO public.leaverequest (employeeid, absencereasonid, startdate, enddate, comment, status) VALUES ('"+Account.id+"', '"+reason+"', '"+startdate+"', '"+enddate+"', '"+comment+"', '"+false+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Add successfully");
                setVisible(false);
                new ViewLeaveRequestEmployee();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new ViewLeaveRequestEmployee();
        }
    }

    public static void main(String[] args) {
        new AddLeaveRequestEmployee();
    }
}