
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    /* Defining the usename , password , login and signup globally to use it in
     in action performed asnow all thses are locally defined and doesnt have 
     Scope outside the block but we need the to use outside the block */
    JTextField username;
    JPasswordField password;
    JButton login;
    JButton cancel;
    JButton clear;
    public static String usern;
    
    
    Login(){
        getContentPane().setBackground(Color.WHITE);
        
        setBounds(500,200,600,300);
        
        setLayout(null);        // We want out layout
        
        // Setting USername
        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30);
        add(user);
        
        // Setting up text field so that user can enter the data
        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);
        
        // Setting Password
        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 30);
        add(pass);
        
        // Setting up text field so that use can input data
        password = new JPasswordField();
        password.setBounds(150,70,150,30);
        add(password);
        
        // Setting up Both Buttons 
        // Login Button
        login = new JButton("Login");
        login.setBounds(40,150,120,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        // Adding action listener to see is there is any action 
        login.addActionListener(this);
        add(login);
        
        // Cancel Button
        cancel = new JButton("Exit");
        cancel.setBounds(180,150,120,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
        // Setting up claer Button
        clear = new JButton("Clear");
        clear.setBounds(120,185,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        // Setting Up Image 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        // since the image is too big resizing it
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);  // We need to conver i2 as we cannot use Image class obj on JLabel
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);
        
        
        
        setVisible(true);      // To see Frame 
        
    }
    
    // Overriding action performed method
    public void actionPerformed(ActionEvent ae){
        // Checking which button is pressed 
        if(ae.getSource() == login){
            // Removing the values wht user has entered to check if they are correct
             usern = username.getText();
            // getText returns wht user have entered
            String passw = password.getText();
            
            // Matching it with data in MySQL 
            try{
                Conn c = new Conn();
                // Hitting MySQL query with JAVA
                String query = "select * from login where username = '" + usern + "' and password = '" + passw +"'";
                // Usern and passw are variables so + is used to avoid concetenatin
                // Executing the query using statement s
                // Storing it into a ResultSet what ever is return by the executed query
                ResultSet rs = c.s.executeQuery(query);
                
                // Checking wht is returned by re
                if(rs.next()){
                    // If password and username are correct
                    setVisible(false);       // Close current frame
                    new Dashboard();          // Open next frame Dashboard frame
                }
                else{
                    // If password or username is incorrect
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    password.setText("");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == cancel){
            // Turns off the frame 
            setVisible(false);
            
        }
        else if(ae.getSource() == clear){
            username.setText("");
                    password.setText("");
        }
        
    }
    
   public static void main (String args[]){
       new Login();
   }
    
}
