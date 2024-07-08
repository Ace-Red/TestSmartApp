package testsmartapp;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployeeProjectManager extends JFrame implements ActionListener {
    
    JTable table;
    Choice fullNameEmp, projectch;
    JButton search, back, detail, setproject;
    
    ViewEmployeeProjectManager(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchLabel = new JLabel("Search by Full Name Employee");
        searchLabel.setBounds(20, 20, 250, 20);
        add(searchLabel);
        
        fullNameEmp = new Choice();
        fullNameEmp.setBounds(280, 20, 150, 20);
        add(fullNameEmp);    
        
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.employee ORDER BY fullname");
            while(rs.next()){
                fullNameEmp.add(rs.getString("fullname"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.employee ORDER BY id");
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
        
        setproject = new JButton("Set Project");
        setproject.setBounds(220, 70, 80,20);
        setproject.addActionListener(this);
        add(setproject);
        
        projectch = new Choice();
        projectch.setBounds(300, 70, 80, 20);
        add(projectch);
        
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT p.* FROM Project p WHERE NOT EXISTS (SELECT 1 FROM EmployeeProject ep JOIN Employee e ON ep.employeeid = e.id WHERE ep.projectid = p.id AND e.fullname = '"+fullNameEmp.getSelectedItem()+"');");
            while(rs.next()){
                projectch.add(rs.getString("id"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
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
            String query = "SELECT * FROM public.employee WHERE fullname = '"+fullNameEmp.getSelectedItem()+"'";
            try{
                ConnectionDB c = new ConnectionDB();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e){
                e.printStackTrace();
            }
            projectch.removeAll();
            try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT p.* FROM Project p WHERE NOT EXISTS (SELECT 1 FROM EmployeeProject ep JOIN Employee e ON ep.employeeid = e.id WHERE ep.projectid = p.id AND e.fullname = '"+fullNameEmp.getSelectedItem()+"');");
            while(rs.next()){
                projectch.add(rs.getString("id"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        } else if(ae.getSource() == detail){
            setVisible(false);
            new DetailEmployeeProjectManager(fullNameEmp.getSelectedItem());
        }
        else if(ae.getSource() == setproject){
           String idEmp ="";
            try {
                ConnectionDB conn = new ConnectionDB();
                String query2 = "SELECT * FROM public.employee WHERE FullName = '"+fullNameEmp.getSelectedItem()+"'";
               
                ResultSet rs = conn.s.executeQuery(query2);
                if (rs.next()){
                    idEmp = rs.getString("id");
                }
                String query = "INSERT INTO public.employeeproject (employeeid, projectid) VALUES ('"+idEmp+"', '"+projectch.getSelectedItem()+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Set Project Successfully");
                setVisible(false);
                new ViewEmployeeProjectManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            setVisible(false);
            new HomeProjectManager();
        }
        
    }
    
    public static void main(String[] args) {
        new ViewEmployeeProjectManager();
    }
}