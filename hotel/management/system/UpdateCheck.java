
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;



public class UpdateCheck extends JFrame implements ActionListener{
    Choice croom;
    JTextField tfid, tfname, tfcheckin, tfdepo, tfrem;
    JButton check, update, back, cancel, checkout;
    
    UpdateCheck(){
        
        setLayout(null);
        setBounds(300, 200, 980, 500);
        
        getContentPane().setBackground(Color.white);
        
        JLabel head = new JLabel("Update Customer Status");
        head.setBounds(90, 20, 250, 30);
        head.setFont(new Font("Tahoma", Font.PLAIN,20));
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
            ResultSet rs = c.s.executeQuery("select * from roominfo where Room_Status = 'Occupied' ");
            while(rs.next()){
                croom.add(rs.getString("Room_Number"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(30,120,100,20);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 120, 160 ,25);
        add(tfname);
        
        JLabel lblids = new JLabel("Customer ID");
        lblids.setBounds(30,160,100,20);
        add(lblids);
        
        tfid = new JTextField();
        tfid.setBounds(200, 160, 160 ,25);
        add(tfid);
        
        JLabel lblcheckin = new JLabel("Check IN");
        lblcheckin.setBounds(30,200,100,20);
        add(lblcheckin);
        
        tfcheckin = new JTextField();
        tfcheckin.setBounds(200, 200, 160 ,25);
        add(tfcheckin);
        
        JLabel lbldepo = new JLabel("Amount Paid");
        lbldepo.setBounds(30,240,100,20);
        add(lbldepo);
        
        tfdepo = new JTextField();
        tfdepo.setBounds(200, 240, 160 ,25);
        add(tfdepo);
        
        JLabel lblrem = new JLabel("Remaining Amount");
        lblrem.setBounds(30,280,120,20);
        add(lblrem);
        
        tfrem = new JTextField();
        tfrem.setBounds(200, 280, 160 ,25);
        add(tfrem);
        
        check = new JButton("Check");
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.setBounds(30, 340, 100, 30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.setBounds(140, 340, 100, 30);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(250, 340, 100, 30);
        back.addActionListener(this);
        add(back);
        
        cancel = new JButton("Exit");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(230, 380, 100, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        checkout = new JButton("Procced to Checkout");
        checkout.setBackground(Color.black);
        checkout.setForeground(Color.white);
        checkout.setBounds(30, 380, 180, 30);
        checkout.addActionListener(this);
        add(checkout);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == back){
            setVisible(false);
            new UpdateCheck();
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
        }
        else if(ae.getSource() == check){
            String room = croom.getSelectedItem();
            
            try{
                Conn c = new Conn();
                String query = "select * from custinfo where Room_Number = '"+room+"'";
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    tfid.setText(rs.getString("ID_Number"));
                    tfname.setText(rs.getString("Name"));
                    tfcheckin.setText(rs.getString("Check_IN_Time"));
                    tfdepo.setText(rs.getString("Deposit"));
                }
                // To get the actual price of the room since it is not there in this table
                // rs is closed now after opening rs2 the system automatically closes it
                ResultSet rs2 = c.s.executeQuery("select * from roominfo where Room_Number = '"+room+"'");
                while(rs2.next()){
                    String price = rs2.getString("Room_Price");
                    // We cannot subtract the values from a string so we use parseInt func
                    int amtpaid = Integer.parseInt(price) - Integer.parseInt(tfdepo.getText());
                    tfrem.setText(""+amtpaid);  // Concat as setText takes String where as amtpaid is an Integer
                    
                    
                }
            } 
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else if(ae.getSource() == update){
            String roomn = croom.getSelectedItem();
            String name = tfname.getText();
            String id = tfid.getText();
            String checkin = tfcheckin.getText();
            String amt = tfdepo.getText();
            
            try{
                Conn c = new Conn();
                String query1 ="update custinfo set Name = '"+name+"' where Room_Number ='"+roomn+"' AND Check_OUT_Time = 'Staying'";
                String query2 ="update custinfo set ID_Number = '"+id+"' where Room_Number ='"+roomn+"'AND Check_OUT_Time = 'Staying'";
                String query3 ="update custinfo set Check_IN_Time = '"+checkin+"' where Room_Number ='"+roomn+"'AND Check_OUT_Time = 'Staying'";
                String query4 ="update custinfo set Deposit = '"+amt+"' where Room_Number ='"+roomn+"'AND Check_OUT_Time = 'Staying'";
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);
                c.s.executeUpdate(query4);
                JOptionPane.showMessageDialog(null, "Customer Details Updated Successfully");
                setVisible(false);
                new UpdateCheck();
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == checkout){
            setVisible(false);
            new Checkout();
        }
    }
    
    
    
    public static void main(String args[]){
        
        new UpdateCheck();
    }
    
}
