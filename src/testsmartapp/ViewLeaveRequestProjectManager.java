package testsmartapp;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewLeaveRequestProjectManager extends JFrame implements ActionListener {
    
    JTable table;
    Choice leaverid;
    JButton search, back, detail;
    static String dbUserName;
    ViewLeaveRequestProjectManager(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchLabel = new JLabel("Search by Leave Request ID");
        searchLabel.setBounds(20, 20, 250, 20);
        add(searchLabel);
        
        leaverid = new Choice();
        leaverid.setBounds(280, 20, 150, 20);
        add(leaverid);    
        
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT lr.* FROM LeaveRequest lr JOIN Employee e ON lr.EmployeeID = e.ID JOIN EmployeeProject ep ON e.ID = ep.EmployeeID JOIN Project p ON ep.ProjectID = p.ID JOIN Employee pm ON p.ProjectManagerID = pm.ID WHERE p.projectManagerID = '"+Account.id+"' AND lr.Status = FALSE AND NOT EXISTS (SELECT 1 FROM ApprovalRequest ar WHERE ar.LeaveRequestID = lr.ID AND ar.Status = FALSE) AND NOT EXISTS (SELECT 1 FROM ApprovalRequest ar2 WHERE ar2.LeaveRequestID = lr.ID AND ar2.ApproverId = '"+Account.id+"');");
            while(rs.next()){
                leaverid.add(rs.getString("id"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT lr.* FROM LeaveRequest lr JOIN Employee e ON lr.EmployeeID = e.ID JOIN EmployeeProject ep ON e.ID = ep.EmployeeID JOIN Project p ON ep.ProjectID = p.ID JOIN Employee pm ON p.ProjectManagerID = pm.ID WHERE p.projectManagerID = '"+Account.id+"' AND lr.Status = FALSE AND NOT EXISTS (SELECT 1 FROM ApprovalRequest ar WHERE ar.LeaveRequestID = lr.ID AND ar.Status = FALSE) AND NOT EXISTS (SELECT 1 FROM ApprovalRequest ar2 WHERE ar2.LeaveRequestID = lr.ID AND ar2.ApproverId = '"+Account.id+"');");
            table.setModel(DbUtils.resultSetToTableModel(rs));            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        search = new JButton("Search");
        search.setBounds(20, 70, 80,20);
        search.addActionListener(this);
        add(search);
        
        detail = new JButton("Detail");
        detail.setBounds(120, 70, 80,20);
        detail.addActionListener(this);
        add(detail);
        
        back = new JButton("Back");
        back.setBounds(420, 70, 80,20);
        back.addActionListener(this);
        add(back);
                
        setSize(1000, 700);
        setLocation(450, 200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == search){
            String query = "SELECT * FROM public.leaverequest WHERE id = '" + leaverid.getSelectedItem()+"'";
            try{
                ConnectionDB c = new ConnectionDB();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } else if(ae.getSource() == detail){
            //setVisible(false);
            //new DetailLeaveRequest(leaverid.getSelectedItem());
        } else {
            setVisible(false);
            new HomeProjectManager();
        }
        
    }
    
    public static void main(String[] args) {
        new ViewLeaveRequestProjectManager();
    }
}