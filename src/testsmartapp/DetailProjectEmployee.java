package testsmartapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DetailProjectEmployee extends JFrame implements ActionListener{
    
    JButton back;
    String projectid;
    DetailProjectEmployee(String projectid) {
        this.projectid = projectid;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Detail Project");
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
        
        JLabel labeltype = new JLabel("Project Type");
        labeltype.setBounds(50, 150, 150, 30);
        labeltype.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeltype);
        
        JLabel lblprojecttype = new JLabel();
        lblprojecttype.setBounds(200, 150, 300, 30);
        lblprojecttype.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblprojecttype);
        
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
        
        JLabel labelprojectmanager = new JLabel("Project Manager");
        labelprojectmanager.setBounds(50, 350, 150, 30);
        labelprojectmanager.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelprojectmanager);
        
        JLabel lblprojectmanager = new JLabel();
        lblprojectmanager.setBounds(200, 350, 150, 30);
        lblprojectmanager.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblprojectmanager);
        
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
            String query = "SELECT * FROM public.project where id = '"+projectid+"'";
            ResultSet rs = c.s.executeQuery(query);
            
            String prmid = "";
            String ptid = "";
            while(rs.next()) {
                lblid.setText(rs.getString("id"));
                ptid = rs.getString("projecttypeid");
                lblstartdate.setText(rs.getString("startdate"));
                lblenddate.setText(rs.getString("enddate"));
                prmid = rs.getString("projectmanagerid");
                lblcomment.setText(rs.getString("comment"));
                lblstatus.setText(rs.getString("status"));
            }
            String query2 = "SELECT * FROM public.projecttype where id = '"+ptid+"'";
            ResultSet rs2 = c.s.executeQuery(query2);
            while(rs2.next()) {
                lblprojecttype.setText(rs2.getString("typename"));
            }
            String query3 = "SELECT * FROM public.employee where id = '"+prmid+"'";
            ResultSet rs3 = c.s.executeQuery(query3);
            while(rs3.next()) {
                lblprojectmanager.setText(rs3.getString("fullname"));
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
            new ViewProjectsEmployee();
    }

    public static void main(String[] args) {
        new DetailProjectEmployee("");
    }
}
