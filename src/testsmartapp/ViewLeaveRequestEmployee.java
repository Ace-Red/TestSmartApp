
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

public class ViewLeaveRequestEmployee extends JFrame implements ActionListener {
    
    JTable table;
    Choice leaverid;
    JButton search, back, detail, add;
    ViewLeaveRequestEmployee(){
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
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.leaverequest WHERE employeeid = '" + Account.id+"'");
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
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.leaverequest WHERE employeeid = '" + Account.id+"'");
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
        
        add = new JButton("Add");
        add.setBounds(120, 70, 80,20);
        add.addActionListener(this);
        add(add);
        
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
            setVisible(false);
            new DetailLeaveRequestEmployee(leaverid.getSelectedItem());
        } else if(ae.getSource() == add){
            //setVisible(false);
            //new DetailLeaveRequest(leaverid.getSelectedItem());
        } 
        else {
            setVisible(false);
            new HomeEmployee();
        }
        
    }
    
    public static void main(String[] args) {
        new ViewLeaveRequestEmployee();
    }
}
