package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.sql.*;

import java.util.Date;

import java.awt.event.*;

public class AddCustomerS extends JFrame implements ActionListener {
    
    JComboBox cbid;
    JTextField tfid, tfname, tfcon, tfdepo, tfnum;
    JRadioButton rdmale, rdfemale;
    Choice croom;   /* We have 2 options to show dropdown list by creating an obj of JComboBox class and by creating a 
    obj of choice class we have 2nd option to create the dropdown becuase here the rooms available are variable and are not fixed 
    but to have JComboBox the choices in the dropdown must be fixed not subjected to change and we can put these variable choices 
    in the dropdown made by Choice class using add func in JComboBox we dont get this add func.
    */
    JLabel checkintime;
    JButton add, cancel;
    
    AddCustomerS(){
        
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        
        setBounds(350, 200, 800, 550);
        
        JLabel text = new JLabel("New Customer Form (Single)");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Releway", Font.BOLD,20));
        add(text);
        
        JLabel lblid = new JLabel("Cutomer ID");
        lblid.setBounds(35, 80, 130, 20);
        lblid.setFont(new Font("Releway", Font.PLAIN,20));
        add(lblid);
        
        String id [] = {"Aadhar Card", "Passport", "PAN Card", "Voter - ID","Driving License"};
        cbid = new JComboBox(id);
        cbid.setBounds(200, 80, 150, 25);
        cbid.setBackground(Color.white);
        add(cbid);
        
        JLabel lblcid = new JLabel("ID Number");
        lblcid.setBounds(35, 120, 100, 20);
        lblcid.setFont(new Font("Releway", Font.PLAIN,20));
        add(lblcid);
        
        tfid = new JTextField();
        tfid.setBounds(200, 120, 150, 25);
        add(tfid);
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(35, 150, 150, 20);
        lblname.setFont(new Font("Releway", Font.PLAIN,20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 25);
        add(tfname);
        
        JLabel lblnum = new JLabel("Contact Number");
        lblnum.setBounds(35, 200, 150, 20);
        lblnum.setFont(new Font("Releway", Font.PLAIN,20));
        add(lblnum);
        
        tfnum = new JTextField();
        tfnum.setBounds(200, 200, 150, 25);
        add(tfnum);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(35, 250, 150, 20);
        lblgender.setFont(new Font("Releway", Font.PLAIN,20));
        add(lblgender);
        
        rdmale = new JRadioButton("Male");
        rdmale.setBackground(Color.white);
        rdmale.setBounds(200, 250, 60, 25);
        add(rdmale);
        
        rdfemale = new JRadioButton("Female");
        rdfemale.setBackground(Color.white);
        rdfemale.setBounds(290, 250, 90, 25);
        add(rdfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdmale);
        bg.add(rdfemale);
        
        JLabel lblcontry = new JLabel("Country");
        lblcontry.setBounds(35, 290, 120, 30);
        lblcontry.setFont(new Font("Releway", Font.PLAIN,20));
        add(lblcontry);
        
        tfcon = new JTextField();
        tfcon.setBounds(200, 290, 150, 25);
        add(tfcon);
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(35, 320, 150, 30);
        lblroom.setFont(new Font("Releway", Font.PLAIN,20));
        add(lblroom);
        
        croom = new Choice();  // The drop down is empty
        croom.setBounds(200, 320, 150, 25);
        add(croom);
        
        try{
            
            Conn conn = new Conn();
            String query = "select * from roominfo";
            ResultSet rs = conn.s.executeQuery(query); // Returns everything present in the mentioned table as an obj rs of ResultSet class
            
            while(rs.next()){     // To check each and every value
                String rstatus = rs.getString("Room_Status"); // To check if room is vacant
                String cstatus = rs.getString("Cleaning_Status");  // To check if room is clean
                String btype = rs.getString("Bed_Type");      // Bed Type that customer wants
                String room = rs.getString("Room_Number");
                if(rstatus.equals("Available") && cstatus.equals("Clean") && btype.equals("Single Bed")){
                    // If room is vacant and clean
                    croom.add(room);  // If the room is clean and vacant add it to dropdown
                }
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        //1 + 2 + 3 + 4 + 5 + "Kaushik" + 1 + 2 + 3 + 4 + 5     IMP
        
        JLabel lbltime = new JLabel("Check - in Time");
        lbltime.setBounds(35, 360, 150, 30);
        lbltime.setFont(new Font("Releway", Font.PLAIN,20));
        add(lbltime);
        
        // Making a obj of date class so that system can fetch its own date
        Date  date = new Date();
        
        checkintime = new JLabel(""+date); // Concatenated with empty string as we cannot pass Date class obj in JLabel after concatanation it acts as string
        checkintime.setBounds(200, 360, 220, 30);
        checkintime.setFont(new Font("Releway", Font.PLAIN,16));
        add(checkintime);
        
        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit.setBounds(35, 400, 120, 30);
        lbldeposit.setFont(new Font("Releway", Font.PLAIN,20));
        add(lbldeposit);
        
        tfdepo = new JTextField();
        tfdepo.setBounds(200, 400, 150, 25);
        add(tfdepo);
        
        add = new JButton("Add");
        add.setForeground(Color.white);
        add.setBackground(Color.BLACK);
        add.setBounds(50, 450, 120, 30);
        add(add);
        add.addActionListener(this);
        
        cancel = new JButton("Exit");
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(210, 450, 120, 30);
        add(cancel);
        cancel.addActionListener(this);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 50, 300, 400);
        add(image);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            String id = (String)cbid.getSelectedItem();    
            String number = tfid.getText();
            String name = tfname.getText();
            String gender = null;
            
            if(rdmale.isSelected()){
                gender = "Male";
            }
            else if(rdfemale.isSelected()){
                gender = "Female";
            }
            
            String country = tfcon.getText();
            String roomn = croom.getSelectedItem();
            String checkin = checkintime.getText();
            String deposit = tfdepo.getText();
            String num = tfnum.getText();
            
            
            // Validations
            if(number.equals("")){
               JOptionPane.showMessageDialog(null, "Customer ID Number is required");
               return;
            }
            
            if(name.equals("")){
               JOptionPane.showMessageDialog(null, "Customer name cannot be empty");
               return;
            }
            int x = 0;
            for(int i = 0; i<num.length();i++){
                if(Character.isLetter(num.charAt(i))|| !Character.isDigit(num.charAt(i)) || Character.isWhitespace(num.charAt(i))){
                    x++;
                }
            }
            if(num.length()<10 || x>0 || num.length()>10){
                JOptionPane.showMessageDialog(null, "Invalid Mobile Number");
                return;
            }
            
            if(gender == null){
               JOptionPane.showMessageDialog(null, "Please select the gender");
               return;
            }
            
            if(country.equals("")){
               JOptionPane.showMessageDialog(null, "Country name cannot be empty");
               return;
            }
            
            
            
            
            try{
               
               Conn conn = new Conn();  // Creating the Connection 
               
               String query = "insert into custinfo values ('"+id+"', '"+number+"', '"+name+"', '"+gender+"', '"+country+"', '"+roomn+"', '"+checkin+"', '"+deposit+"', '"+num+"', 'Staying')";
               // Once the room is Allocated it should be updated to occupied so to do that
               String query2 = "update roominfo set Room_Status = 'Occupied' where Room_Number = '"+roomn+"'";
               
               // Storing in MySQL
               conn.s.executeUpdate(query);
               conn.s.executeUpdate(query2);
               JOptionPane.showMessageDialog(null, "New Customer added succesfully");
               setVisible(false);
               
               
           }
           catch(Exception e){
               e.printStackTrace();
           }
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
            
        }
        
    }
    
    public static void main(String args[]){
        
        new AddCustomerS();
    }
    
}
