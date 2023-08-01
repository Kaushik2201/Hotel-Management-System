
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.util.Date;

import java.awt.event.*;



public class Checkout extends JFrame implements ActionListener {
    Choice croom;
    JLabel custname, checkin, checkout, amtrem;
    JButton check, ckeckout, back, exit, clear;
    
    Checkout(){
        
        setLayout(null);
        setBounds(300, 200, 800, 400);
        getContentPane().setBackground(Color.white);
        
        JLabel heading = new JLabel("Checkout");
        heading.setBounds(100, 20, 100, 30);
        heading.setForeground(Color.blue);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblroomno = new JLabel("Room Number");
        lblroomno.setBounds(30, 80, 100, 30);
        add(lblroomno);
        
        croom = new Choice();
        croom.setBounds(160, 85, 120, 35);
        add(croom);
        
        try{
            
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from roominfo where Room_Status = 'Occupied' ");
            while(rs.next()){
                croom.add(rs.getString("Room_Number"));
                
                
                
            }
            
        } 
        catch(Exception e){
            e.printStackTrace();
        }
        
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(290, 85, 20 ,20);
        add(image);
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(30, 130, 100, 30);
        add(lblname);
        
        custname = new JLabel("");
        custname.setBounds(160, 130, 200, 30);
        add(custname);
        
        JLabel lblcheckin = new JLabel("Check - IN Time");
        lblcheckin.setBounds(30, 180, 150, 30);
        add(lblcheckin);
        
        checkin = new JLabel("");
        checkin.setBounds(160, 180, 200, 30);
        add(checkin);
        
        JLabel lblcheckout = new JLabel("Check - OUT Time");
        lblcheckout.setBounds(30, 230, 150, 30);
        add(lblcheckout);
        
        Date date = new Date();
        
        checkout = new JLabel(""+date);
        checkout.setBounds(160, 230, 200, 30);
        add(checkout);
        
        JLabel lblamt = new JLabel("Amount to pay");
        lblamt.setBounds(30, 280, 150, 30);
        add(lblamt);
        
        amtrem = new JLabel("");
        amtrem.setBounds(160, 280, 150, 30);
        add(amtrem);
        
        check = new JButton("Check Details");
        check.setBounds(30, 320, 120, 30);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.addActionListener(this);
        add(check);
        
        ckeckout = new JButton("Checkout");
        ckeckout.setBounds(160, 320, 120, 30);
        ckeckout.setBackground(Color.black);
        ckeckout.setForeground(Color.white);
        ckeckout.addActionListener(this);
        add(ckeckout);
        
        back = new JButton("Update Details");
        back.setBounds(290, 320, 120, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        clear = new JButton("Clear");
        clear.setBounds(420, 320, 120, 30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);
        
        exit = new JButton("Exit");
        exit.setBounds(550, 320, 120, 30);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        exit.addActionListener(this);
        add(exit);
        
        ImageIcon i4  = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image2 = new JLabel(i6);
        image2.setBounds(350, 50, 400 ,250);
        add(image2);
        
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == check){
            String roomn = croom.getSelectedItem();
            String x = "";
            
            try{
                Conn c = new Conn();
                String query = "select * from custinfo where Room_Number = '"+roomn+"' ";
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    custname.setText(rs.getString("Name"));
                    checkin.setText(rs.getString("Check_IN_Time"));
                    x = rs.getString("Deposit");
                            
                }
                
                String query2 = "select * from roominfo where Room_Number = '"+roomn+"' ";
                ResultSet rs2 = c.s.executeQuery(query2);
                while(rs2.next()){
                    String a = rs2.getString("Room_Price");
                    int z = Integer.parseInt(a) - Integer.parseInt(x);
                    amtrem.setText(""+z);
                    
                    
                }
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == back ){
            
            setVisible(false);
            new UpdateCheck();
            
            
        }
        else if(ae.getSource() == ckeckout){
            String roomn = croom.getSelectedItem();
            if(amtrem.getText().equals("0")){
                try{
                Conn c = new Conn();
                String query = "update custinfo set Check_OUT_Time = '"+checkout.getText()+"' where Room_Number ='"+roomn+"' AND Check_OUT_Time = 'Staying'";
                String query2 = "update roominfo set Room_Status = 'Available' where Room_Number ='"+roomn+"' ";
                String query3 = "update roominfo set Cleaning_Status = 'Clean' where Room_Number ='"+roomn+"' ";
                c.s.executeUpdate(query);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null, "Checkout Successfull");
                setVisible(false);
                new Checkout();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please Update Room Status");
                
            }
        
        }
        else if(ae.getSource() == clear){
            setVisible(false);
            new Checkout();
        }
        else if(ae.getSource() == exit){
            setVisible(false);
        }
    }
    
    
    
    public static void main(String args[]){
        
        new Checkout();
    }
    
}
