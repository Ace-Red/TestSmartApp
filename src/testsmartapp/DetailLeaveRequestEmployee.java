package testsmartapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DetailLeaveRequestEmployee extends JFrame implements ActionListener{
    static String empid;
    JButton back;
    String leaverid;
    DetailLeaveRequestEmployee(String leaverid) {
        this.leaverid = leaverid;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Leave Request");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        JLabel labelid = new JLabel("ID");
        labelid.setBounds(50, 200, 150, 30);
        labelid.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelid);
        
        JLabel lblid = new JLabel();
        lblid.setBounds(200, 200, 150, 30);
        lblid.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblid);
        
        JLabel labelemployee = new JLabel("Employee");
        labelemployee.setBounds(50, 150, 150, 30);
        labelemployee.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemployee);
        
        JLabel lblemployee = new JLabel();
        lblemployee.setBounds(200, 150, 300, 30);
        lblemployee.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblemployee);
        
        JLabel labelstartdate = new JLabel("Start Date");
        labelstartdate.setBounds(50, 250, 150, 30);
        labelstartdate.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelstartdate);
        
        JLabel lblstartdate = new JLabel();
        lblstartdate.setBounds(200, 250, 150, 30);
        lblstartdate.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblstartdate);
        
        JLabel labelenddate = new JLabel("End Date");
        labelenddate.setBounds(50, 300, 150, 30);
        labelenddate.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelenddate);
        
        JLabel lblenddate = new JLabel();
        lblenddate.setBounds(200, 300, 150, 30);
        lblenddate.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblenddate);
        
        JLabel labelreason = new JLabel("Reason");
        labelreason.setBounds(50, 350, 150, 30);
        labelreason.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelreason);
        
        JLabel lblreason = new JLabel();
        lblreason.setBounds(200, 350, 150, 30);
        lblreason.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblreason);
        
        JLabel labelstatus = new JLabel("Status(t/f)");
        labelstatus.setBounds(50, 400, 150, 30);
        labelstatus.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelstatus);
        
        JLabel lblstatus = new JLabel();
        lblstatus.setBounds(200, 400, 150, 30);
        lblstatus.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblstatus);
        
        JLabel labelcomment = new JLabel("Comment");
        labelcomment.setBounds(50, 450, 150, 30);
        labelcomment.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelcomment);
        
        JLabel lblcomment = new JLabel();
        lblcomment.setBounds(200, 450, 500, 30);
        lblcomment.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblcomment);
        
         try {
            ConnectionDB c = new ConnectionDB();
            String query = "SELECT * FROM public.leaverequest where id = '"+leaverid+"'";
            ResultSet rs = c.s.executeQuery(query);
            
            String absid = "";
            String empid = "";
            while(rs.next()) {
                lblid.setText(rs.getString("id"));
                empid = rs.getString("employeeid");
                lblstartdate.setText(rs.getString("startdate"));
                lblenddate.setText(rs.getString("enddate"));
                absid = rs.getString("absencereasonid");
                lblcomment.setText(rs.getString("comment"));
                lblstatus.setText(rs.getString("status")); 
            }
            this.empid = empid;
            String query2 = "SELECT * FROM public.employee where id = '"+empid+"'";
            ResultSet rs2 = c.s.executeQuery(query2);
            while(rs2.next()) {
                lblemployee.setText(rs2.getString("fullname"));
            }
            String query3 = "SELECT * FROM public.absencereason where id = '"+absid+"'";
            ResultSet rs3 = c.s.executeQuery(query3);
            while(rs3.next()) {
                lblreason.setText(rs3.getString("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        back = new JButton("Back");
        back.setBounds(550, 550, 150, 40);
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
            setVisible(false);
            new ViewLeaveRequestEmployee();
    }

    public static void main(String[] args) {
        new DetailLeaveRequestEmployee("");
    }
}

