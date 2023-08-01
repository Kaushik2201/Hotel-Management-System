
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import net.proteanit.sql.*;

import java.util.*;


public class PickUp extends JFrame implements ActionListener {
    JTable table;
    JButton back, cancel, exit, update, dea;
    Choice ccar;
    JCheckBox avl;
    JLabel count;
    ArrayList<String>list = new ArrayList<>();
    JScrollPane js;
    
    PickUp(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(300, 200, 1050, 600);
        
        JLabel h = new JLabel("PickUp Service");
        h.setBounds(300, 15, 500, 50);
        h.setFont(new Font("Serif", Font.PLAIN, 40));
        add(h);
        
        JLabel lblcar = new JLabel("Type of Car");
        lblcar.setBounds(50,100,100,20);
        add(lblcar);
        
        
        ccar = new Choice();
        ccar.setBounds(150,100,200,25);
        ccar.setBackground(Color.white);
        add(ccar);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from driverinfo");
            while(rs.next()){
                String x = rs.getString("Car_Model");
                if(!list.contains(x)){
                    list.add(x);
                ccar.add(x);
                }
                
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        avl = new JCheckBox("Display only Available");
        avl.setBounds(700,100,250,30);
        avl.setBackground(Color.white);
        add(avl);
       
        
        
        
        
        table = new JTable();
        table.setBounds(6, 170, 1020, 230);
        table.setBackground(Color.WHITE);
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 15));
        table.setAutoCreateRowSorter(true);
        //add(table); //Removed to put ScrollBar
        // Taking Data from Data base and displaying it
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from driverinfo");
            // Storing this data in that table so that it can be displayed out using DbUtils Class in rs2xml.jar file
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        count = new JLabel("The Total Numbers of Drivers are : "+table.getRowCount());
        count.setBounds(20, 430, 300, 30);
        add(count);
        
      
        
        
        back = new JButton("Submit");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(150, 500, 120, 30);
        back.addActionListener(this);
        add(back);
        
        
        cancel = new JButton("Back");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(610, 500, 120, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        exit = new JButton("Exit");
        exit.setForeground(Color.white);
        exit.setBackground(Color.black);
        exit.setBounds(760, 500, 120, 30);
        exit.addActionListener(this);
        add(exit);
        
        update = new JButton("Assign Driver");
        update.setForeground(Color.white);
        update.setBackground(Color.black);
        update.setBounds(300, 500, 120, 30);
        update.addActionListener(this);
        add(update);
        
        dea = new JButton("Deassign Driver");
        dea.setForeground(Color.white);
        dea.setBackground(Color.black);
        dea.setBounds(450, 500, 130, 30);
        dea.addActionListener(this);
        add(dea);
        

        js = new JScrollPane(table);      
        js.setBounds(6, 170, 1020, 230);
        js.setBackground(Color.white);
        add(js);
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == cancel){
            setVisible(false);
            new PickUp();
            
            
        }
        else if(ae.getSource() == back){
            
            try{
            Conn conn = new Conn();
            String query1 = "select * from driverinfo where Car_Model = '"+ccar.getSelectedItem()+"' ";
            String query2 = "select * from driverinfo where Status = 'Available' AND Car_Model = '"+ccar.getSelectedItem()+"' ";
            ResultSet rs;
            
            if(avl.isSelected()){
                rs = conn.s.executeQuery(query2);
            }
            else{
                rs = conn.s.executeQuery(query1);
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }
            if(table.getRowCount() == 0){
                JOptionPane.showMessageDialog(null, "No Drivers Available");
            }
        count.setVisible(false);
        count = new JLabel("The Total Numbers of Drivers are : "+table.getRowCount());
        count.setBounds(20, 430, 300, 30);
        add(count);
            
        }
        else if(ae.getSource() == exit){
            setVisible(false);
        }
        else if(ae.getSource() == update){
            setVisible(false);
            new UpdateDriver();
        }
        else if(ae.getSource() == dea){
            setVisible(false);
            new UpdateDri();
        }
    }
    
    public static void main(String args[]){
        
        new PickUp();
    }
    
}
