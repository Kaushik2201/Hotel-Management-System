
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class AddRooms extends JFrame implements ActionListener {
    
    JTextField tfroomno, tfprice;
    JComboBox cbaval, cbcln, cbtyp;
    JButton add, cancel;
    
    AddRooms(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        
        setBounds(300, 200, 950, 470);
        
        JLabel lblheading = new JLabel ("ADD ROOMS");
        lblheading.setBounds(150, 20, 200,20);
        lblheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(lblheading);
        
        JLabel lblroomno = new JLabel ("ROOM NUMBER");
        lblroomno.setBounds(60, 80, 120,30);
        lblroomno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblroomno);
        
        tfroomno = new JTextField();
        tfroomno.setBounds(200, 80, 150, 30);
        add(tfroomno);
        
        JLabel lblavl = new JLabel ("ROOM STATUS");
        lblavl.setBounds(60, 130, 200,30);
        lblavl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblavl);
        
        String avl [] = {"Available", "Occupied"};
        cbaval = new JComboBox(avl);
        cbaval.setBounds(200, 130, 150, 30);
        cbaval.setBackground(Color.white);
        add(cbaval);
        
        JLabel lblcl = new JLabel ("CLEANING STATUS");
        lblcl.setBounds(60, 180, 200,30);
        lblcl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcl);
        
        String cl [] = {"Clean", "Dirty"};
        cbcln = new JComboBox(cl);
        cbcln.setBounds(200, 180, 150, 30);
        cbcln.setBackground(Color.white);
        add(cbcln);
        
        JLabel lblprice = new JLabel ("ROOM PRICE");
        lblprice.setBounds(60, 230, 120,30);
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblprice);
        
        tfprice = new JTextField();
        tfprice.setBounds(200, 230, 150, 30);
        add(tfprice);
        
        JLabel lbtype = new JLabel ("BED TYPE");
        lbtype.setBounds(60, 280, 200,30);
        lbtype.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbtype);
        
        String typ [] = {"Single Bed", "Double Bed"};
        cbtyp = new JComboBox(typ);
        cbtyp.setBounds(200, 280, 150, 30);
        cbtyp.setBackground(Color.white);
        add(cbtyp);
        
        add = new JButton("Add Room");
        add.setForeground(Color.white);
        add.setBackground(Color.black);
        add.setBounds(60, 350, 130, 30);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Exit");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(210, 350, 130, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 70, 500, 300);
        add(image);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
           String roomn = tfroomno.getText();
           String rooms = (String)cbaval.getSelectedItem();
           String roomc = (String)cbcln.getSelectedItem();
           String roomp = tfprice.getText();
           String bt = (String)cbtyp.getSelectedItem();
           
           // Validation 
           
           if(roomn.equals("")){
               JOptionPane.showMessageDialog(null, "Room Number cannot be empty");
               return;
           }
           
           if(roomp.equals("")){
               JOptionPane.showMessageDialog(null, "Room Price cannot be empty");
               return;
           }
           
           
           try{
               
               Conn conn = new Conn();  // Creating the Connection 
               
               String query = "insert into roominfo values ('"+roomn+"', '"+rooms+"', '"+roomc+"', '"+roomp+"', '"+bt+"')";
               
               // Storing in MySQL
               conn.s.executeUpdate(query);
               JOptionPane.showMessageDialog(null, "Room added succesfully");
               setVisible(false);
               new Popupr();
               
           }
           catch(Exception e){
               e.printStackTrace();
           }
           
           
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
            
        }
    }
    
    
    
    
    public static void main (String args[]){
        new AddRooms();
    }
}
