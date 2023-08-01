
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class Reception extends JFrame implements ActionListener{
    
    JButton newcost, rooms, dept,  allemp, costinf, man, ch, upt, urs, pickup, search, logout;
    String name = Login.usern;
    
    Reception(){
        
        setLayout(null);
        setBounds(350, 200, 800, 570);
        getContentPane().setBackground(Color.white);
        
        // Setting up all the Buttons 
        
        newcost = new JButton("NEW CUSTOMER FORM");
        newcost.setBounds(10, 30, 200, 30);
        newcost.setBackground(Color.black);
        newcost.setForeground(Color.white);
        add(newcost);
        newcost.addActionListener(this);
        
        rooms = new JButton("ROOMS");
        rooms.setBounds(10, 70, 200, 30);
        rooms.setBackground(Color.black);
        rooms.setForeground(Color.white);
        add(rooms);
        rooms.addActionListener(this);
        
        dept = new JButton("DEPARTMENT");
        dept.setBounds(10, 110, 200, 30);
        dept.setBackground(Color.black);
        dept.setForeground(Color.white);
        add(dept);
        dept.addActionListener(this);
        
        allemp = new JButton("ALL EMPLOYEES");
        allemp.setBounds(10, 150, 200, 30);
        allemp.setBackground(Color.black);
        allemp.setForeground(Color.white);
        add(allemp);
        allemp.addActionListener(this);
        
        costinf = new JButton("CUSTOMER INFO");
        costinf.setBounds(10, 190, 200, 30);
        costinf.setBackground(Color.black);
        costinf.setForeground(Color.white);
        add(costinf);
        costinf.addActionListener(this);
        
        man = new JButton("MANAGER INFO");
        man.setBounds(10, 230, 200, 30);
        man.setBackground(Color.black);
        man.setForeground(Color.white);
        add(man);
        man.addActionListener(this);
        
        ch = new JButton("CHECKOUT");
        ch.setBounds(10, 270, 200, 30);
        ch.setBackground(Color.black);
        ch.setForeground(Color.white);
        add(ch);
        ch.addActionListener(this);
        
        upt = new JButton("UPDATE STATUS");
        upt.setBounds(10, 310, 200, 30);
        upt.setBackground(Color.black);
        upt.setForeground(Color.white);
        add(upt);
        upt.addActionListener(this);
        
        urs = new JButton("UPDATE ROOM STATUS");
        urs.setBounds(10, 350, 200, 30);
        urs.setBackground(Color.black);
        urs.setForeground(Color.white);
        add(urs);
        urs.addActionListener(this);
        
        pickup = new JButton("PICKUP SERVICE");
        pickup.setBounds(10, 390, 200, 30);
        pickup.setBackground(Color.black);
        pickup.setForeground(Color.white);
        pickup.addActionListener(this);
        add(pickup);
        
        search = new JButton("SEARCH ROOMS");
        search.setBounds(10, 430, 200, 30);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        add(search);
        search.addActionListener(this);
        
        logout = new JButton("LOGOUT");
        logout.setBounds(10, 470, 200, 30);
        logout.setBackground(Color.black);
        logout.setForeground(Color.white);
        add(logout);
        logout.addActionListener(this);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250, 30, 500, 470);
        add(image);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == newcost){
            new RoomType();
        }
        else if(ae.getSource() == rooms){
            new Room();
        }
        else if (ae.getSource() == dept){
            setVisible(false);
            new Department();
        }
        else if(ae.getSource() == allemp){
            new EmployeeInfo();
        }
        else if(ae.getSource() == man){
            new ManagerInfo();
        }
        else if(ae.getSource() == costinf){
            new CustomerInfo();
        }
        else if(ae.getSource() == search){
            new SearchRooms();
        }
        else if(ae.getSource() == upt){
            new UpdateCheck();
        }
        else if(ae.getSource() == urs){
            new UpdateRoom();
        }
        else if(ae.getSource() == pickup ){
            new PickUp();
        }
        else if(ae.getSource() == ch){
            new Checkout();
        }
        else if(ae.getSource() == logout){
            JOptionPane.showMessageDialog(null, "Log Out Successful Thankyou " +name);
            setVisible(false);
            System.exit(0);      // To close all tabs and shut down the app
            
        }
        
    }
    
   public static void main(String args[]){
       
       new Reception();
   }
}
