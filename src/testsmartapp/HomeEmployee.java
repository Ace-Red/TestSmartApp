package testsmartapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeEmployee extends JFrame implements ActionListener{
    
    JButton listProjects, listLRequests, listARequests;
    HomeEmployee(){
        
        setLayout(null);
        
        JLabel heading = new JLabel("OUT OF OFFICE APP");
        heading.setBounds(650, 20, 400, 40);
        heading.setFont(new Font("serif",Font.BOLD, 25));
        add(heading);
        
        listProjects = new JButton("list of projects");
        listProjects.setBounds(650, 150, 250, 40);
        listProjects.addActionListener(this);
        add(listProjects);
        
        listLRequests = new JButton("list of leave requests");
        listLRequests.setBounds(650, 220, 250, 40);
        listLRequests.addActionListener(this);
        add(listLRequests);
        
        listARequests = new JButton("list of approval requests");
        listARequests.setBounds(650, 290, 250, 40);
        listARequests.addActionListener(this);
        add(listARequests);
        
        setSize(1200, 700);
        setLocation(250, 100);
        setVisible(true);
                
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if (ae.getSource() == listProjects){
            //setVisible(false);
            //new ViewProjects();
        }
        else if (ae.getSource() == listLRequests){
            setVisible(false);
            new ViewLeaveRequestEmployee();
        }
        else {
            setVisible(false);
            new ViewApprovalRequestEmployee();
        }
        
    }
    
    public static void main(String[] args) {
        new HomeEmployee();
    }
}