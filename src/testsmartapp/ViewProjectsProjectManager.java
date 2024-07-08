package testsmartapp;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewProjectsProjectManager extends JFrame implements ActionListener {
    
    JTable table;
    Choice projectid;
    JButton search, back, detail;
    
    ViewProjectsProjectManager(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchLabel = new JLabel("Search by Project ID");
        searchLabel.setBounds(20, 20, 250, 20);
        add(searchLabel);
        
        projectid = new Choice();
        projectid.setBounds(280, 20, 150, 20);
        add(projectid);    
        
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.project WHERE projectmanagerid = '"+Account.id+"' ORDER BY id");
            while(rs.next()){
                projectid.add(rs.getString("id"));
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        try {
            ConnectionDB c = new ConnectionDB();
            ResultSet rs = c.s.executeQuery("SELECT * FROM public.project WHERE projectmanagerid = '"+Account.id+"' ORDER BY id");
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
            String query = "SELECT * FROM public.project WHERE id = '" + projectid.getSelectedItem()+"'";
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
            new DetailProjectProjectManager(projectid.getSelectedItem());
        } else {
            setVisible(false);
            new HomeProjectManager();
        }
        
    }
    
    public static void main(String[] args) {
        new ViewProjectsProjectManager();
    }
}
