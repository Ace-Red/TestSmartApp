package testsmartapp;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class ViewApprovalRequestEmployee extends JFrame implements ActionListener {
    
    JTable table;
    Choice approvalrid;
    JButton search, back, detail;
    
    ViewApprovalRequestEmployee(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchLabel = new JLabel("Search by Approval Request ID");
        searchLabel.setBounds(20, 20, 250, 20);
        add(searchLabel);
        
        approvalrid = new Choice();
        approvalrid.setBounds(280, 20, 150, 20);
        add(approvalrid);    
        
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT ar.* FROM approvalrequest ar JOIN leaverequest lr ON ar.leaverequestid = lr.id WHERE lr.employeeid = '"+Account.id+"';");
            while(rs.next()){
                approvalrid.add(rs.getString("id"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT ar.* FROM approvalrequest ar JOIN leaverequest lr ON ar.leaverequestid = lr.id WHERE lr.employeeid = '"+Account.id+"';");
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
            String query = "SELECT * FROM public.approvalrequest WHERE id = '" + approvalrid.getSelectedItem()+"'";
            try{
                ConnectionDB c = new ConnectionDB();
                 ResultSet rs = c.s.executeQuery(query);
                 table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } else if(ae.getSource() == detail){
            setVisible(false);
            new DetailApprovalRequestEmployee(approvalrid.getSelectedItem());
        } else {
            setVisible(false);
            new Home();
        }
        
    }
    
    public static void main(String[] args) {
        new ViewApprovalRequest();
    }
}

