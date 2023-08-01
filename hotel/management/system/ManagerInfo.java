
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import net.proteanit.sql.*;


public class ManagerInfo extends JFrame implements ActionListener {
    JTable table;
    JButton back;
    JScrollPane js;
    JLabel count;
    
    ManagerInfo(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(300, 200, 1000, 600);
        
        JLabel h = new JLabel("Royal Arched Manager(s)");
        h.setBounds(10, 25, 500, 40);
        h.setFont(new Font("Serif", Font.BOLD, 30));
        add(h);
       
        
        table = new JTable();
        table.setBounds(6, 120, 970, 250);
        table.setBackground(Color.WHITE);
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        // Taking Data from Data base and displaying it
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from employeeinfo2 where Position = 'Manager'");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setAutoCreateRowSorter(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        count = new JLabel("The Number of Managers(s) are : "+ table.getRowCount());
        count.setBounds(30, 400, 200, 30);
        add(count);
        
        back = new JButton("Exit");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(420, 470, 120, 30);
        back.addActionListener(this);
        add(back);
        
        js = new JScrollPane(table);      
        js.setBounds(6, 120, 970, 250);
        js.setBackground(Color.white);
        add(js);
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        
        new ManagerInfo();
    }
    
}
