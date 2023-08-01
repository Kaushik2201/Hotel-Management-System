
package hotel.management.system;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener  {
    Dashboard(){
        // Setting the frame 
        setBounds(-7,0,1920,1080);
        
        //Making layout null
        setLayout(null);
        
        // Putting image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        // Scalling it 
        Image i2 = i1.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        // Making it to ImageIcon to use it on JLabel
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(-7,0,1920,1080);
        add(image);
        
        // Adding Text over the image
        JLabel text = new JLabel("The Taj Group Wecomes You");
        text.setBounds(450, 80, 1000, 50);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Tahoma", Font.BOLD, 46));
        image.add(text);
        
        // Setting the Menu Bar
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 0, 1920, 30);
        image.add(mb);
        
        // Setting the components on the menu bar
        JMenu hotel = new JMenu("   Royal Archid");
        hotel.setFont(new Font("Arial", Font.PLAIN,25));
        hotel.setForeground(Color.red);
        mb.add(hotel);
        
        // Setting for hotel Admin to add details of the hotel
        String l = Login.usern;  // To get the username from Login poage
        JMenu admin = new JMenu("  Hello "+l);
        admin.setFont(new Font("Arial", Font.PLAIN,25));
        admin.setForeground(Color.BLUE);
        mb.add(admin);
        
        // Adding Reception 
        JMenuItem reception = new JMenuItem("RECEPTION");
        reception.setFont(new Font("Arial", Font.PLAIN,22));
        hotel.add(reception);
        reception.addActionListener(this);
        
        // Adding Rooms , Drivers and Employees in Admin 
        JMenuItem addemployee = new JMenuItem("ADD EMPLOYEES");
        addemployee.setFont(new Font("Arial", Font.PLAIN,22));
        admin.add(addemployee);
        addemployee.addActionListener(this);
        
        JMenuItem addroom = new JMenuItem("ADD ROOMS");
        addroom.setFont(new Font("Arial", Font.PLAIN,22));
        admin.add(addroom);
        addroom.addActionListener(this);
        
        JMenuItem adddriver = new JMenuItem("ADD DRIVERS");
        adddriver.setFont(new Font("Arial", Font.PLAIN,22));
        admin.add(adddriver);
        adddriver.addActionListener(this);
        
        
        // Making it visible
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("ADD EMPLOYEES")){
            /* We have get action command method because it is a menu item not a button and this returns a string
               and we have equated that string with the text if its equal the open that frame */
            new AddEmployee();
        }
        else if (ae.getActionCommand().equals("ADD ROOMS")){
            
            new AddRooms();
        }
        else if(ae.getActionCommand().equals("ADD DRIVERS")){
            new AddDriver();
        }
        else if(ae.getActionCommand().equals("RECEPTION")){
            new Reception();
        }
    }
    
    public static void main (String args[]){
        //Dashboard d = new Dashboard();
        new Dashboard();
    }
}
