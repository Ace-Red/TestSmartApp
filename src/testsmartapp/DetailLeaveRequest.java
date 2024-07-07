package testsmartapp;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import testsmartapp.ConnectionDB;
import testsmartapp.ViewEmployee;

public class DetailLeaveRequest extends JFrame implements ActionListener{
    static String empid;
    JButton back, approve, reject;
    String leaverid;
    DetailLeaveRequest(String leaverid) {
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
        add(back);
        
        approve = new JButton("Approve");
        approve.setBounds(50, 550, 150, 40);
        approve.addActionListener(this);
        approve.setBackground(Color.BLACK);
        approve.setForeground(Color.WHITE);
        add(approve);
        
        reject = new JButton("Reject");
        reject.setBounds(200, 550, 150, 40);
        reject.addActionListener(this);
        reject.setBackground(Color.BLACK);
        reject.setForeground(Color.WHITE);
        add(reject);
        
        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == approve){
            int pmcount = 0; //количество PM на всех проектах сотрудника, который написал реквест
            try {
                ConnectionDB c = new ConnectionDB();
                String query = "SELECT COUNT(DISTINCT e.ID) AS ProjectManagerCount FROM Employee e JOIN EmployeeProject ep1 ON e.ID = ep1.EmployeeID JOIN Project p ON ep1.ProjectID = p.ID JOIN EmployeeProject ep2 ON p.ID = ep2.ProjectID JOIN Employee emp ON ep2.EmployeeID = emp.ID WHERE emp.ID = '"+DetailLeaveRequest.empid+"' AND e.PositionID = (SELECT ID FROM Position WHERE Name = 'Project Manager');";
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()) {
                     pmcount += Integer.parseInt(rs.getString("ProjectManagerCount"));
                 }  
            }
            catch (Exception e){
                e.printStackTrace();
            }
            int appreqcount = 0; //количество одобрений
            try {
                ConnectionDB c = new ConnectionDB();
                String query = "SELECT COUNT(*) AS ApprovalRequestCount FROM ApprovalRequest WHERE LeaveRequestID = '"+leaverid+"';";
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()) {
                     appreqcount += Integer.parseInt(rs.getString("ApprovalRequestCount"));
                 }     
            }
            catch (Exception e){
                e.printStackTrace();
            }
            if (appreqcount<pmcount+1){
                try {
                    ConnectionDB conn = new ConnectionDB();
                    String query = "INSERT INTO public.approvalrequest (approverid, leaverequestid, status, comment) VALUES ('"+Account.id+"', '"+leaverid+"', '"+true+"', '"+"Okey"+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Approve successfully");
                    setVisible(false);
                    new ViewLeaveRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
            else{
                try {
                    ConnectionDB conn = new ConnectionDB();
                    String query = "UPDATE public.leaverequest SET status = true WHERE id = '"+leaverid+"'";
                    String query2 = "INSERT INTO public.approvalrequest (approverid, leaverequestid, status, comment) VALUES ('"+Account.id+"', '"+leaverid+"', '"+true+"', Okey)";
                    conn.s.executeUpdate(query);
                    conn.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "Approve successfully");
                    setVisible(false);
                    new ViewLeaveRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        }
        else if (ae.getSource() == reject){
            try {
                    ConnectionDB conn = new ConnectionDB();
                    String query = "INSERT INTO public.approvalrequest (approverid, leaverequestid, status, comment) VALUES ('"+Account.id+"', '"+leaverid+"', '"+false+"', '"+"You must work"+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Reject successfully");
                    setVisible(false);
                    new ViewLeaveRequest();
            
        } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else {
            setVisible(false);
            new ViewLeaveRequest();
        }
    }

    public static void main(String[] args) {
        new DetailLeaveRequest("");
    }
}