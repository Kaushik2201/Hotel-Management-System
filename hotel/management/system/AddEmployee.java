
package hotel.management.system;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


public class AddEmployee extends JFrame implements ActionListener {
    
    // Global decleration
    JTextField tfname, tfage, tfsalary, tfphone, tfemail, tfaadhar;
    JRadioButton rdmale, rdfemale;
    JComboBox cbjob;
    JButton submit , cancel;
            
    AddEmployee(){
        
        setLayout(null);
        
        setBounds(350, 200, 850, 540);
        
        // Setting frame colour
        getContentPane().setBackground(Color.white);
        
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 30, 120, 30);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200,30,120,30);
        add(tfname);
        
        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60, 80, 120, 30);
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200,80,120,30);
        add(tfage);
        
        JLabel lbgender = new JLabel("GENDER");
        lbgender.setBounds(60, 130, 120, 30);
        lbgender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbgender);
        
        // Setting up radio buttons
        rdmale = new JRadioButton("Male");
        rdmale.setBounds(200, 130, 70, 30);
        rdmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdmale.setBackground(Color.WHITE);
        add(rdmale);
        
        rdfemale = new JRadioButton("Female");
        rdfemale.setBounds(290, 130, 70, 30);
        rdfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdfemale.setBackground(Color.WHITE);
        add(rdfemale);
        
        // Only 1 should be selected in Radio Button
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdmale);
        bg.add(rdfemale);
        
        JLabel lbjob = new JLabel("JOB");
        lbjob.setBounds(60, 180, 120, 30);
        lbjob.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbjob);
        
        // DRopDown
        String str [] = {"Front Desk Clerks", "Porters", "Housekeeping", "Kitchen staff", "Room Service", "Chefs", "Waiter/Waitress", "Manager", "Accountant"};
        cbjob = new JComboBox(str);        // This takes an array of string which shows in the dropdown
        cbjob.setBounds(200, 180, 150, 30);
        cbjob.setBackground(Color.WHITE);
        add(cbjob);
        
        JLabel lblsly = new JLabel("SALARY");
        lblsly.setBounds(60, 230, 120, 30);
        lblsly.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblsly);
        
        tfsalary = new JTextField();
        tfsalary.setBounds(200,230,120,30);
        add(tfsalary);
        
        JLabel lblphone = new JLabel("CONTACT");
        lblphone.setBounds(60, 280, 120, 30);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(200,280,120,30);
        add(tfphone);
        
        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds(60, 330, 120, 30);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200,330,120,30);
        add(tfemail);
        
        JLabel lblaadhar = new JLabel("AADHAR");
        lblaadhar.setBounds(60, 380, 120, 30);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblaadhar);
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(200,380,120,30);
        add(tfaadhar);
        
        // Submit Button
        submit = new JButton("ADD");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.setBounds(60, 430, 150,30);
        add(submit);
        submit.addActionListener(this);
        
        // Cancel Button 
        cancel = new JButton("EXIT");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setBounds(220, 430, 150,30);
        add(cancel);
        cancel.addActionListener(this);
        
        // Adding Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 60, 450, 370);   // 450 to 370 in width this is cropping the image
        add(image);
        
        
        setVisible(true);
        
    }
    
    // Overriding the func
    public void actionPerformed(ActionEvent ce){
        
        if(ce.getSource() == submit){
        String name = tfname.getText();
        String age = tfage.getText();
        String phone = tfphone.getText();
        String salary = tfsalary.getText();
        String email = tfemail.getText();
        String aadhar = tfaadhar.getText();
        
        // Adding Validations 
        if(name.equals("")){
            JOptionPane.showMessageDialog(null, "The name cannot be empty");
            return;
        }
        
        if(age.equals("")){
            JOptionPane.showMessageDialog(null, "The age cannot be empty");
            return;
        }
        
        String gender = null;
        if(rdmale.isSelected()){
           gender = "Male";            
        }
        else if(rdfemale.isSelected()){
            gender = "Female";
        }
        
        if(gender == null){
            JOptionPane.showMessageDialog(null, "Gender not selected");
            return;
        }
        
        if(salary.equals("")){
            JOptionPane.showMessageDialog(null, "The salary cannot be empty");
            return;
        }
        
        int x = 0;
            for(int i = 0; i<phone.length();i++){
                if(Character.isLetter(phone.charAt(i))|| !Character.isDigit(phone.charAt(i)) || Character.isWhitespace(phone.charAt(i))){
                    x++;
                }
            }
            if(phone.length()<10 || x>0 || phone.length()>10){
                JOptionPane.showMessageDialog(null, "Invalid Mobile Number");
                return;
            }
        if((email.contains("@")&& email.contains(".com") && !email.equals("")) || 
                email.contains("@")&& email.contains(".in") && !email.equals("")){
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid Email");
            return;
        }
        
        if(aadhar.length()<14){
            JOptionPane.showMessageDialog(null, "Check Aadhar number again");
            return;
        }
        
        
        String job = (String)cbjob.getSelectedItem();
        
        try{
            Conn conn = new Conn();
            
            String query = "insert into employeeinfo2 values('"+name+"', '"+age+"', '"+gender+"', '"+job+"', '"+salary+"', '"+phone+"', '"+email+"', '"+aadhar+"' )";
            
            // Executing and Updating the query
            conn.s.executeUpdate(query);         // Doesnt return anything just updates / sets data in MySQL
            
            JOptionPane.showMessageDialog(null, "Employee added succesfully");
            setVisible(false);
            new Popup();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
     }
        else if(ce.getSource() == cancel){
            setVisible(false);
        }
        
    }
    
    public static void main(String arga[]){
        
        new AddEmployee();
    }
}
