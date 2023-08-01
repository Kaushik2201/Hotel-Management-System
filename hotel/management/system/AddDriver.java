
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class AddDriver extends JFrame implements ActionListener {
    
    JTextField tfname, tfcompany, tfage, tfmodel, tfloc, tfphone;
    JComboBox cbgen, cbava;
    JButton add, cancel;
    
    AddDriver(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        
        setBounds(300, 200, 950, 470);
        
        JLabel lblheading = new JLabel ("ADD DERIVERS");
        lblheading.setBounds(150, 10, 200,20);
        lblheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(lblheading);
        
        JLabel lblname = new JLabel ("NAME");
        lblname.setBounds(60, 50, 120,30);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 50, 150, 30);
        add(tfname);
        
        JLabel lblage = new JLabel ("AGE");
        lblage.setBounds(60, 90, 200,30);
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblage);
      
        tfage = new JTextField();
        tfage.setBounds(200, 90, 150, 30);
        add(tfage);
        
        JLabel lblgen = new JLabel ("GENDER");
        lblgen.setBounds(60, 130, 200,30);
        lblgen.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgen);
        
        String gen [] = {"Male", "Female"};
        cbgen = new JComboBox(gen);
        cbgen.setBounds(200, 130, 150, 30);
        cbgen.setBackground(Color.white);
        add(cbgen);
        
        JLabel lblcompany = new JLabel ("CAR COMPANY");
        lblcompany.setBounds(60, 170, 120,30);
        lblcompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcompany);
        
        tfcompany = new JTextField();
        tfcompany.setBounds(200, 170, 150, 30);
        add(tfcompany);
        
        JLabel lblmodel = new JLabel ("CAR MODEL");
        lblmodel.setBounds(60, 210, 200,30);
        lblmodel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblmodel);
        
        tfmodel = new JTextField();
        tfmodel.setBounds(200, 210, 150, 30);
        add(tfmodel);
        
        JLabel lblavl = new JLabel ("STATUS");
        lblavl.setBounds(60, 250, 120,30);
        lblavl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblavl);
        
        String ava [] = {"Available", "Busy"};
        cbava = new JComboBox(ava);
        cbava.setBounds(200, 250, 150, 30);
        cbava.setBackground(Color.white);
        add(cbava);
        
        JLabel lbllocation = new JLabel ("LOCATION");
        lbllocation.setBounds(60, 290, 1200,30);
        lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbllocation);
        
        tfloc = new JTextField();
        tfloc.setBounds(200, 290, 150, 30);
        add(tfloc);
        
        JLabel lblphone = new JLabel ("CONTACT");
        lblphone.setBounds(60, 330, 1200,30);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(200, 330, 150, 30);
        add(tfphone);
        
        add = new JButton("Add Driver");
        add.setForeground(Color.white);
        add.setBackground(Color.black);
        add.setBounds(60, 380, 130, 30);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Exit");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        cancel.setBounds(210, 380, 130, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 70, 500, 300);
        add(image);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
           String name = tfname.getText();
           String age = tfage.getText();
           String gen = (String)cbgen.getSelectedItem();
           String company = tfcompany.getText();
           String model = tfmodel.getText();
           String status = (String)cbava.getSelectedItem();
           String location = tfloc.getText();
           String phoneno = tfphone.getText();
           
           // Validation 
           
           if(name.equals("")){
               JOptionPane.showMessageDialog(null, "Driver name cannot be empty");
               return;
           }
           
           if(age.equals("")){
               JOptionPane.showMessageDialog(null, "Driver age cannot be empty");
               return;
           }
           
           if(company.equals("")){
               JOptionPane.showMessageDialog(null, "Car Company cannot be empty");
               return;
           }
           
           if(model.equals("")){
               JOptionPane.showMessageDialog(null, "Car Model cannot be empty");
               return;
           }
           
           if(location.equals("")){
               JOptionPane.showMessageDialog(null, "Location cannot be empty");
               return;
           }
           int x = 0;
            for(int i = 0; i<phoneno.length();i++){
                if(Character.isLetter(phoneno.charAt(i))|| !Character.isDigit(phoneno.charAt(i)) || Character.isWhitespace(phoneno.charAt(i))){
                    x++;
                }
            }
            if(phoneno.length()<10 || x>0 || phoneno.length()>10){
                JOptionPane.showMessageDialog(null, "Invalid Mobile Number");
                return;
            }
           
           
           try{
               
               Conn conn = new Conn();  // Creating the Connection 
               
               String query = "insert into driverinfo values ('"+name+"', '"+age+"', '"+gen+"', '"+company+"', '"+model+"', '"+status+"', '"+location+"', '"+phoneno+"')";
               
               // Storing in MySQL
               conn.s.executeUpdate(query);
               JOptionPane.showMessageDialog(null, "Driver added succesfully");
               setVisible(false);
               new Popupd();
               
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
        new AddDriver();
    }
}
