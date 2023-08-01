
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import java.util.*;


public class UpdateDri extends JFrame implements ActionListener {
    JButton slectdri, clear, cancel, pickup;
    Choice ccar ;
    ArrayList<String>list = new ArrayList<>();
    UpdateDri(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(300, 200, 800, 400);
        
        JLabel h = new JLabel("De assign Driver");
        h.setBounds(250, 65, 500, 50);
        h.setFont(new Font("Serif", Font.PLAIN, 40));
        add(h);
        
        JLabel lblcar = new JLabel("Driver Name");
        lblcar.setBounds(50,150,100,20);
        add(lblcar);
        
        
        ccar = new Choice();
        ccar.setBounds(150,150,200,25);
        ccar.setBackground(Color.white);
        add(ccar);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from driverinfo where Status = 'Busy' OR Location != 'Office'");
            while(rs.next()){
                String x = rs.getString("Name");
                if(!list.contains(x)){
                    list.add(x);
                ccar.add(x);
                }
                
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblloc = new JLabel("Driver Name");
        lblcar.setBounds(50,150,100,20);
        add(lblcar);
        
        
        
        slectdri = new JButton("De Assign");
        slectdri.setForeground(Color.white);
        slectdri.setBackground(Color.black);
        slectdri.setBounds(370, 145, 150, 30);
        slectdri.addActionListener(this);
        add(slectdri);
       
        
        
        clear = new JButton("Clear");
        clear.setForeground(Color.white);
        clear.setBackground(Color.black);
        clear.setBounds(100, 230, 120, 30);
        clear.addActionListener(this);
        add(clear);
       
        
        cancel = new JButton("Assign Driver");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(250, 230, 120, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        pickup = new JButton("Go Back to PickUp");
        pickup.setForeground(Color.white);
        pickup.setBackground(Color.black);
        pickup.setBounds(395, 230, 150, 30);
        pickup.addActionListener(this);
        add(pickup);
        

        
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == cancel){
            setVisible(false);
            new UpdateDriver();
            
            
        }
        else if(ae.getSource() == clear){
           setVisible(false);
           new UpdateDri();
            
        }
        else if(ae.getSource() == slectdri ){
            String name = ccar.getSelectedItem();
            String x = "Office";
            try{
            Conn c = new Conn();
            String query1 = "update driverinfo set Location = '"+x+"' where Name = '"+name+"'" ;
            String query2 = "update driverinfo set Status = 'Available' where Name = '"+name+"'";
            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);
            JOptionPane.showMessageDialog(null, "Driver Deassigned Successfully");
            if(list.size() == 1){
                JOptionPane.showMessageDialog(null, "All Drivers Deassigned");
                setVisible(false);
                new PickUp();
            }
            else{
             setVisible(false);
            new UpdateDri();
            }
            
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
            
        }
        else if (ae.getSource() == pickup){
            setVisible(false);
            new PickUp();
        }
        
        
    }
    
    public static void main(String args[]){
        
        new UpdateDri();
    }
    
}
