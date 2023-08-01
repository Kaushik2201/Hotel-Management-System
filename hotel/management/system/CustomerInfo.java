
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import net.proteanit.sql.*;


public class CustomerInfo extends JFrame implements ActionListener {
    JTable table;
    JButton back, cancel , sea , checkout;
    JScrollPane js;
    JLabel count;
    JTextField search;
    
    CustomerInfo(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(300, 200, 1000, 600);
        
        JLabel h = new JLabel("Royal Arched Customers");
        h.setBounds(10, 25, 500, 40);
        h.setFont(new Font("Serif", Font.BOLD, 30));
        add(h);
       
        JLabel lblsearch = new JLabel("Search");
        lblsearch.setBounds(650, 60, 100, 30);
        add(lblsearch);
        
        search = new JTextField();
        search.setBounds(700, 60, 150, 30);
        add(search);
        
        sea = new JButton("Search");
        sea.setForeground(Color.white);
        sea.setBackground(Color.black);
        sea.setBounds(860, 65, 80, 20);
        sea.addActionListener(this);
        add(sea);
        
        table = new JTable();
        table.setBounds(6, 120, 970, 300);
        table.setBackground(Color.WHITE);
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        // Taking Data from Data base and displaying it
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from custinfo where Check_OUT_Time = 'Staying' ");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setAutoCreateRowSorter(true);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        count = new JLabel("The Total Number of Customers are : "+table.getRowCount());
        count.setBounds(30,430, 230, 30);
        add(count);
        
        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(40, 510, 120, 30);
        back.addActionListener(this);
        add(back);
        
        cancel = new JButton("Exit");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(200, 510, 120, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        checkout = new JButton("See Checked Out Customers ");
        checkout.setForeground(Color.white);
        checkout.setBackground(Color.black);
        checkout.setBounds(60, 470, 220, 30);
        checkout.addActionListener(this);
        add(checkout);
        
        js = new JScrollPane(table);      
        js.setBounds(6, 120, 970, 300);
        js.setBackground(Color.white);
        add(js);
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
            new CustomerInfo();
        }
        else if( ae.getSource() == cancel){
            setVisible(false);
        }
        else if( ae.getSource() == sea){
            String name = search.getText();
            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "Name Or Room Number Cannot be empty");
                return;
            }
            try{
                
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from custinfo where Name = '"+search.getText()+"' OR Room_Number = '"+search.getText()+"' ");
                
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    count.setVisible(false); 
                    count = new JLabel("The Total Number of Customers are : "+table.getRowCount());
                    count.setBounds(30,430, 230, 30);
                    add(count);
                
                if(table.getRowCount() == 0){
                    JOptionPane.showMessageDialog(null, "No Customer Found");
                }
  
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == checkout){
            try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from custinfo where Check_OUT_Time != 'Staying' ");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            count.setVisible(false); 
            count = new JLabel("The Total Number of Customers are : "+table.getRowCount());
            count.setBounds(30,430, 230, 30);
            add(count);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }
    }
    
    public static void main(String args[]){
        
        new CustomerInfo();
    }
    
}
