
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import net.proteanit.sql.*;


public class SearchRooms extends JFrame implements ActionListener {
    JTable table;
    JButton back, cancel, exit;
    JComboBox cbbed;
    JCheckBox avl;
    JScrollPane js;
    JLabel count;
    
    SearchRooms(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(300, 200, 1050, 600);
        
        JLabel h = new JLabel("Search For Rooms");
        h.setBounds(300, 15, 500, 40);
        h.setFont(new Font("Serif", Font.PLAIN, 40));
        add(h);
        
        String bt[] = {"Single Bed", "Double Bed"};
        cbbed = new JComboBox(bt);
        cbbed.setBounds(50,80,150,30);
        cbbed.setBackground(Color.white);
        add(cbbed);
        
        avl = new JCheckBox("Only Display Available and Clean");
        avl.setBounds(700,80,250,30);
        avl.setBackground(Color.white);
        add(avl);
       
        
        
        
        
        table = new JTable();
        table.setBounds(6, 130, 1020, 300);
        table.setBackground(Color.WHITE);
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 15));
        table.setAutoCreateRowSorter(true);
        //add(table); //Removed to put ScrollBar
        // Taking Data from Data base and displaying it
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from roominfo;");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        count = new JLabel("The Total Number of Rooms are : "+table.getRowCount());
        count.setBounds(30,450, 230, 30);
        add(count);
        
      
        
        
        back = new JButton("Submit");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(300, 500, 120, 30);
        back.addActionListener(this);
        add(back);
        
        
        cancel = new JButton("Back");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(450, 500, 120, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        exit = new JButton("Exit");
        exit.setForeground(Color.white);
        exit.setBackground(Color.black);
        exit.setBounds(600, 500, 120, 30);
        exit.addActionListener(this);
        add(exit);
        

        js = new JScrollPane(table);      
        js.setBounds(6, 130, 1020, 300);
        js.setBackground(Color.white);
        add(js);
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == cancel){
            setVisible(false);
            new SearchRooms();
            
            
        }
        else if(ae.getSource() == back){
            
            try{
            Conn conn = new Conn();
            String query1 = "select * from roominfo where Bed_Type = '"+cbbed.getSelectedItem()+"' ";
            String query2 = "select * from roominfo where Room_Status = 'Available' AND Cleaning_Status = 'Clean' AND Bed_Type = '"+cbbed.getSelectedItem()+"' ";
            ResultSet rs;
            
            if(avl.isSelected()){
                rs = conn.s.executeQuery(query2);
            }
            else{
                rs = conn.s.executeQuery(query1);
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
            count.setVisible(false);
            count = new JLabel("The Total Number of Rooms are : "+table.getRowCount());
            count.setBounds(30,450, 230, 30);
            add(count);
        }
        catch(Exception e){
            e.printStackTrace();
        }
            
        }
        else if(ae.getSource() == exit){
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        
        new SearchRooms();
    }
    
}
