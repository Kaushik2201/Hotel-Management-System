
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

import java.util.*;


public class UpdateDriver extends JFrame implements ActionListener {
    JButton selectcar, slectdri, clear, cancel, slectmodel;
    JTextField tfloc;
    Choice ccar, cdriver, ccarm;
    ArrayList<String>list = new ArrayList<>();
    ArrayList<String>list2 = new ArrayList<>();
    UpdateDriver(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        setBounds(300, 200, 800, 500);
        
        JLabel h = new JLabel("Assign Driver");
        h.setBounds(250, 15, 500, 50);
        h.setFont(new Font("Serif", Font.PLAIN, 40));
        add(h);
        
        JLabel lblcar = new JLabel("Car Company");
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
                String x = rs.getString("Car_Company");
                if(!list.contains(x)){
                    list.add(x);
                ccar.add(x);
                }
                
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        selectcar = new JButton("Select Car Company");
        selectcar.setForeground(Color.white);
        selectcar.setBackground(Color.black);
        selectcar.setBounds(360, 140, 200, 30);
        selectcar.addActionListener(this);
        add(selectcar);
        
        JLabel lblcarm = new JLabel("Car Model");
        lblcarm.setBounds(50,200,100,20);
        add(lblcarm);
        
        
        ccarm = new Choice();
        ccarm.setBounds(150,200,200,25);
        ccarm.setBackground(Color.white);
        add(ccarm);
        
        slectmodel = new JButton("Select Car Model");
        slectmodel.setForeground(Color.white);
        slectmodel.setBackground(Color.black);
        slectmodel.setBounds(360, 240, 200, 30);
        slectmodel.addActionListener(this);
        add(slectmodel);
        
        JLabel lbldri = new JLabel("Driver Available");
        lbldri.setBounds(50,300,100,20);
        add(lbldri);
        
        
        cdriver = new Choice();
        cdriver.setBounds(150,300,200,25);
        cdriver.setBackground(Color.white);
        add(cdriver);
        
        JLabel lblloc = new JLabel("Enter Location");
        lblloc.setBounds(50,340,100,25);
        add(lblloc);
        
        tfloc = new JTextField();
        tfloc.setBounds(150, 340, 200, 25);
        add(tfloc);
        
        slectdri = new JButton("Assign Driver");
        slectdri.setForeground(Color.white);
        slectdri.setBackground(Color.black);
        slectdri.setBounds(370, 360, 150, 30);
        slectdri.addActionListener(this);
        add(slectdri);
       
        
        
        clear = new JButton("Clear");
        clear.setForeground(Color.white);
        clear.setBackground(Color.black);
        clear.setBounds(100, 420, 120, 30);
        clear.addActionListener(this);
        add(clear);
       
        
        cancel = new JButton("Exit");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(250, 420, 120, 30);
        cancel.addActionListener(this);
        add(cancel);
        

        
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == cancel){
            setVisible(false);
            new PickUp();
            
            
        }
        else if(ae.getSource() == clear){
           setVisible(false);
           new UpdateDriver();
            
        }
        else if(ae.getSource() == selectcar ){
            ccarm.removeAll();
            try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from driverinfo where Car_Company = '"+ccar.getSelectedItem()+"' ");
            
            while(rs.next()){
                String x = rs.getString("Car_Model");
                if(!list2.contains(x)){
                    list2.add(x);
                ccarm.add(x);
                }
            }
            
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
            
        }
        else if(ae.getSource() == slectdri ){
            String name = cdriver.getSelectedItem();
            String loc = tfloc.getText();
            if(loc.equals("")){
                JOptionPane.showMessageDialog(null, "Location Cannot be Empty");
                return;
            }
            try{
            Conn c = new Conn();
            String query1 = "update driverinfo set Status = 'Busy' where Name ='"+name+"'";
            String query2 = "update driverinfo set Location = '"+loc+"' where Name ='"+name+"'";
            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);
            JOptionPane.showMessageDialog(null, "Driver Assigned Successfully");
            setVisible(false);
            new PickUp();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        }
        else if(ae.getSource() == slectmodel ){
            cdriver.removeAll();
            try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from driverinfo where Car_Model = '"+ccarm.getSelectedItem()+"' AND Status = 'Available' ");
            
            while(rs.next()){
                cdriver.add(rs.getString("Name"));
            }
            int a = cdriver.getItemCount();
            if(a==0){
                JOptionPane.showMessageDialog(null, "No Drivers Available Currently ");
                
            }
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        }
    }
    
    public static void main(String args[]){
        
        new UpdateDriver();
    }
    
}
