package testsmartapp;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import testsmartapp.ConnectionDB;
import testsmartapp.ViewEmployee;

public class DetailApprovalRequest extends JFrame implements ActionListener{
    
    JButton back;
    String approvalid;
    DetailApprovalRequest(String approvalid) {
        this.approvalid = approvalid;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Approval Request");
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
        
        JLabel labelemployee = new JLabel("Approver");
        labelemployee.setBounds(50, 150, 150, 30);
        labelemployee.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemployee);
        
        JLabel lblemployee = new JLabel();
        lblemployee.setBounds(200, 150, 300, 30);
        lblemployee.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblemployee);
        
        JLabel labelleaverequestid = new JLabel("Leave Request ID");
        labelleaverequestid.setBounds(50, 250, 150, 30);
        labelleaverequestid.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelleaverequestid);
        
        JLabel lblleaverequestid = new JLabel();
        lblleaverequestid.setBounds(200, 250, 300, 30);
        lblleaverequestid.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblleaverequestid);
        
        JLabel labelstatus = new JLabel("Status(t/f)");
        labelstatus.setBounds(50, 300, 150, 30);
        labelstatus.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelstatus);
        
        JLabel lblstatus = new JLabel();
        lblstatus.setBounds(200, 300, 150, 30);
        lblstatus.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblstatus);
        
        JLabel labelcomment = new JLabel("Comment");
        labelcomment.setBounds(50, 350, 150, 30);
        labelcomment.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelcomment);
        
        JLabel lblcomment = new JLabel();
        lblcomment.setBounds(200, 350, 500, 30);
        lblcomment.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblcomment);
        
        
         try {
            ConnectionDB c = new ConnectionDB();
            String query = "SELECT * FROM public.approvalrequest where id = '"+approvalid+"'";
            ResultSet rs = c.s.executeQuery(query);
            
            String lvrid = "";
            String empid = "";
            while(rs.next()) {
                lblid.setText(rs.getString("id"));
                empid = rs.getString("approverid");
                lvrid = rs.getString("leaverequestid");
                lblcomment.setText(rs.getString("comment"));
                lblstatus.setText(rs.getString("status"));
            }
            String query2 = "SELECT * FROM public.employee where id = '"+empid+"'";
            ResultSet rs2 = c.s.executeQuery(query2);
            while(rs2.next()) {
                lblemployee.setText(rs2.getString("fullname"));
            }
            String query3 = "SELECT * FROM public.leaverequest where id = '"+lvrid+"'";
            ResultSet rs3 = c.s.executeQuery(query3);
            while(rs3.next()) {
                lblleaverequestid.setText(rs3.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
            setVisible(false);
            new ViewApprovalRequest();
    }

    public static void main(String[] args) {
        new DetailApprovalRequest("");
    }
}