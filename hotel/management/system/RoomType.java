
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.sql.*;

public class RoomType extends JFrame implements ActionListener {
    JButton yes, no, cancel;
    int s = 0;
    int d = 0;
    
    RoomType(){
        setLayout(null);
        setBounds(550,250,500,300);
        getContentPane().setBackground(Color.white);
        
        JLabel heading = new JLabel("WHICH ROOM DO YOU WANT ?");
        heading.setBounds(50, 60, 400, 100);
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        add(heading);
        
        yes = new JButton("SINGLE BED");
        yes.setBounds(60, 150, 120, 30);
        yes.setForeground(Color.white);
        yes.setBackground(Color.black);
        add(yes);
        yes.addActionListener(this);
        
        no = new JButton("DOUBLE BED");
        no.setBounds(200, 150, 120, 30);
        no.setForeground(Color.white);
        no.setBackground(Color.black);
        add(no);
        no.addActionListener(this);
        
        cancel = new JButton("Exit");
        cancel.setBounds(135, 190, 120, 30);
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        add(cancel);
        cancel.addActionListener(this);
        
        try{
            
            Conn conn = new Conn();
            String query = "select * from roominfo";
            ResultSet rs = conn.s.executeQuery(query); // Returns everything present in the mentioned table as an obj rs of ResultSet class
            while(rs.next()){     // To check each and every value
                String rstatus = rs.getString("Room_Status"); // To check if room is vacant
                String cstatus = rs.getString("Cleaning_Status");  // To check if room is clean
                String btype = rs.getString("Bed_Type");      // Bed Type that customer wants
                if(rstatus.equals("Available") && cstatus.equals("Clean") && btype.equals("Double Bed")){
                    // If room is vacant and clean
                    d++;  // If the room is clean and vacant add it to dropdown
                }
                if(rstatus.equals("Available") && cstatus.equals("Clean") && btype.equals("Single Bed")){
                    // If room is vacant and clean
                    s++;  // If the room is clean and vacant add it to dropdown
                }
            }
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
                
        
        setVisible(true);
    }
    
    
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == yes){
            if(s==0){
                JOptionPane.showMessageDialog(null, "Sorry No Single Bed Rooms Available");
                
            }
            else if(s>0){
            setVisible(false);
            new AddCustomerS();
            }
        }
        else if(ae.getSource() == no){
           if(d==0){
                JOptionPane.showMessageDialog(null, "Sorry No Double Bed Rooms Available");
                
            }
           else if (d>0){
            setVisible(false);
            new AddCustomerD();
            }
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        new RoomType();
    }
}
