package testsmartapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeProjectManager extends JFrame implements ActionListener {
    JButton listEmployees, listProjects, listLRequests, listARequests;
    HomeProjectManager(){
        
        setLayout(null);
        
        JLabel heading = new JLabel("OUT OF OFFICE APP");
        heading.setBounds(650, 20, 400, 40);
        heading.setFont(new Font("serif",Font.BOLD, 25));
        add(heading);
        
        listEmployees = new JButton("list of employees");
        listEmployees.setBounds(650, 80, 250, 40);
        listEmployees.addActionListener(this);
        add(listEmployees);
        
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
        
        if (ae.getSource() == listEmployees){
            setVisible(false);
            new ViewEmployeeProjectManager();
        }
        else if (ae.getSource() == listProjects){
            setVisible(false);
            new ViewProjectsProjectManager();
        }
        else if (ae.getSource() == listLRequests){
            //setVisible(false);
            //new ViewLeaveRequest();
        }
        else {
            //setVisible(false);
            //new ViewApprovalRequest();
        }
        
    }
    
    public static void main(String[] args) {
        new HomeProjectManager();
    }
}
