
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import net.proteanit.sql.*;


public class Room extends JFrame implements ActionListener {
    JTable table;
    JButton back, Single, Double, cancel;
    JScrollPane js;
    JLabel count;

    
    
    Room(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(300, 200, 1050, 600);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(520, 30, 500, 500);
        add(image);
        
                
        table = new JTable();
        table.setBounds(6, 55, 500, 400);
        table.setBackground(Color.WHITE);
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 10));
        
        // Taking Data from Data base and displaying it
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from roominfo;");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setAutoCreateRowSorter(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        count = new JLabel("The Total Number of Rooms are : "+table.getRowCount());
        count.setBounds(100,460, 230, 30);
        add(count);
        
        Single = new JButton("Single Bed");
        Single.setForeground(Color.white);
        Single.setBackground(Color.black);
        Single.setBounds(20, 500, 100, 30);
        Single.addActionListener(this);
        add(Single);
        
        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(260, 500, 100, 30);
        back.addActionListener(this);
        add(back);
        
        Double = new JButton("Double Bed");
        Double.setForeground(Color.white);
        Double.setBackground(Color.black);
        Double.setBounds(135, 500, 110, 30);
        Double.addActionListener(this);
        add(Double);
        
        cancel = new JButton("Exit");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(380, 500, 100, 30);
        cancel.addActionListener(this);
        add(cancel);


        js = new JScrollPane(table);      
        js.setBounds(6, 55, 500, 400);
        js.setBackground(Color.white);
        add(js);
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
            new Room();
            
        }
        else if(ae.getSource() == Single){
            
            try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from roominfo where Bed_Type = 'Single Bed'");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            count.setVisible(false);
            count = new JLabel("The Total Number of Rooms are : "+table.getRowCount());
            count.setBounds(100,460, 230, 30);
            add(count);
        }
        catch(Exception e){
            e.printStackTrace();
        }
            
        }
        else if(ae.getSource() == Double){
            
            try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from roominfo where Bed_Type = 'Double Bed'");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
            count.setVisible(false);
            count = new JLabel("The Total Number of Rooms are : "+table.getRowCount());
            count.setBounds(100,460, 230, 30);
            add(count);
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
        
        new Room();
    }
    
}
