
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import net.proteanit.sql.*;


public class Department extends JFrame implements ActionListener {
    JTable table; 
    JTable table2;
    JButton back;
    
    Department(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(400, 250, 700, 500);
        
        
        JLabel l1 = new JLabel("Department");
        l1.setBounds(150, 40, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Budget");
        l2.setBounds(450, 40, 100, 20);
        add(l2);
        
        
        table = new JTable();
        table.setBounds(6, 95, 670, 150);
        add(table);
        
        // Taking Data from Data base and displaying it
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from department;");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel l3 = new JLabel("Contact Person");
        l3.setBounds(150, 245, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Mobile Number");
        l4.setBounds(450, 245, 100, 20);
        add(l4);
        
        table2 = new JTable();
        table2.setBounds(6, 280, 670, 100);
        add(table2);
        
        try{
            Conn conn2 = new Conn();
            ResultSet rs1 = conn2.s.executeQuery("select * from departinfo");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table2.setModel(DbUtils.resultSetToTableModel(rs1));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        back = new JButton("Exit");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(280, 410, 120, 30);
        back.addActionListener(this);
        add(back);
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }
    
    public static void main(String args[]){
        
        new Department();
    }
    
}
