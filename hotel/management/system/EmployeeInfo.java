
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import net.proteanit.sql.*;


public class EmployeeInfo extends JFrame implements ActionListener {
    JTable table;
    JButton back, submit,cancel;
    JScrollPane js;
    JComboBox cbjob;
    JLabel count;
    
    EmployeeInfo(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(300, 200, 1000, 600);
        
        JLabel h = new JLabel("Royal Arched Employees");
        h.setBounds(10, 25, 500, 40);
        h.setFont(new Font("Serif", Font.BOLD, 30));
        add(h);
        
        JLabel h2 = new JLabel("Job Type ");
        h2.setBounds(10, 100, 150, 40);
        h2.setFont(new Font("Serif", Font.PLAIN, 20));
        add(h2);
        
        String str [] = {"Front Desk Clerks", "Porters", "Housekeeping", "Kitchen staff", "Room Service", "Chefs", "Waiter/Waitress", "Accountant"};
        cbjob = new JComboBox(str);        // This takes an array of string which shows in the dropdown
        cbjob.setBounds(100, 100, 150, 40);
        cbjob.setBackground(Color.WHITE);
        add(cbjob);
       
        
        table = new JTable();
        table.setBounds(6, 200, 970, 270);
        add(table);
        table.setBackground(Color.WHITE);
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        // Taking Data from Data base and displaying it
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from employeeinfo2 where Position != 'Manager'");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setAutoCreateRowSorter(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        count = new JLabel("The Total Number of Employees are : "+table.getRowCount());
        count.setBounds(700,490, 230, 30);
        add(count);
        
        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(250, 490, 120, 30);
        back.addActionListener(this);
        add(back);
        
        submit = new JButton("Submit");
        submit.setForeground(Color.white);
        submit.setBackground(Color.black);
        submit.setBounds(100, 490, 120, 30);
        submit.addActionListener(this);
        add(submit);
        
        cancel = new JButton("Exit");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(400, 490, 120, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        
        setVisible(true);
        js = new JScrollPane(table);      
        js.setBounds(6, 200, 970, 270);
        js.setBackground(Color.white);
        add(js);
        
    }
    public void actionPerformed(ActionEvent ae){
        int a = 0;
        if(ae.getSource() == back){
            setVisible(false);
            new EmployeeInfo();
        }
        else if(ae.getSource() == submit){
            try{
            Conn c = new Conn();
            String query = "Select * from employeeinfo2 where Position = '"+cbjob.getSelectedItem()+"' ";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            a = table.getRowCount();
            count.setVisible(false);
            count = new JLabel("The Total Number of Employees are : "+a);
            count.setBounds(700,490, 230, 30);
            add(count);
            if(a==0){
                JOptionPane.showMessageDialog(null, "No Employee Found");
            }
            
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        
        new EmployeeInfo();
    }
    
}
