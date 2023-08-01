
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;


public class UpdateRoom extends JFrame implements ActionListener{
    Choice croom, crs;
    JTextField tfname, tfcleanings;
    JButton check, update, back, cancel;
    
    UpdateRoom(){
        
        setLayout(null);
        setBounds(300, 200, 980, 450);
        
        getContentPane().setBackground(Color.white);
        
        JLabel head = new JLabel("Update Room Status");
        head.setBounds(90, 20, 250, 30);
        head.setFont(new Font("Tahoma", Font.PLAIN,25));
        head.setForeground(Color.blue);
        add(head);
        
        JLabel lblid = new JLabel("Room Number");
        lblid.setBounds(30, 80, 100, 30);
        add(lblid);
        
        croom = new Choice();
        croom.setBounds(200, 85, 160, 30);
        add(croom);
        
        // Geting data of all rooms from MySQL
        try{
            
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from custinfo where Check_OUT_Time = 'Staying' ");
            while(rs.next()){
                croom.add(rs.getString("Room_Number"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(30,130,100,20);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 130, 160 ,25);
        add(tfname);
        
        JLabel lblrooms = new JLabel("Room Status");
        lblrooms.setBounds(30,180,100,20);
        add(lblrooms);
        
        crs = new Choice();
        crs.setBounds(200, 180, 160 ,25);
        add(crs);
        
        JLabel lblcleanings = new JLabel("Claeaning Status");
        lblcleanings.setBounds(30,230,100,20);
        add(lblcleanings);
        
        tfcleanings = new JTextField();
        tfcleanings.setBounds(200, 230, 160 ,25);
        add(tfcleanings);
        
        
        
        check = new JButton("Check");
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.setBounds(30, 300, 100, 30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.setBounds(140, 300, 100, 30);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(250, 300, 100, 30);
        back.addActionListener(this);
        add(back);
        
        cancel = new JButton("Exit");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(140, 340, 100, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == back){
            setVisible(false);
            new UpdateRoom();
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
        }
        else if(ae.getSource() == check){
            String room = croom.getSelectedItem();
            
            try{
                Conn c = new Conn();
                ResultSet rs2 = c.s.executeQuery("select * from custinfo where Room_Number = '"+room+"' ");
                while(rs2.next()){
                    
                    tfname.setText(rs2.getString("Name"));
                    
                }
                Conn c1 = new Conn();
                String query = "select * from roominfo where Room_Number = '"+room+"'";
                ResultSet rs = c1.s.executeQuery(query);
                crs.removeAll();
                while(rs.next()){
                    crs.add(rs.getString("Room_Status"));
                    tfcleanings.setText(rs.getString("Cleaning_Status"));
                    
                }
            } 
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else if(ae.getSource() == update){
            String roomn = croom.getSelectedItem();
            String cleans = tfcleanings.getText();
            String rooms = crs.getSelectedItem();
            if(cleans.isEmpty()){
                JOptionPane.showMessageDialog(null, "Cleaning Status Cannot be empty");
            }
            else{
            
            try{
                Conn c = new Conn();
                String query1 ="update roominfo set Room_Status = '"+rooms+"' where Room_Number ='"+roomn+"' ";
                String query2 ="update roominfo set Cleaning_Status = '"+cleans+"' where Room_Number ='"+roomn+"' ";
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Room Status Updated Successfully");
                setVisible(false);
                new UpdateRoom();
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        }
    }
    
    
    
    public static void main(String args[]){
        
        new UpdateRoom();
    }
    
}
