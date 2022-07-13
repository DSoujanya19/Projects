package pbL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class PasswordVerification extends JFrame implements ActionListener{
	JPanel p[]=new JPanel[3];
	 JLabel passwordL=new JLabel("Password");
	 JButton submit=new JButton("submit");
	 JTextField passwordT=new JTextField(15);
	 String tableName=null;
	private final String password="krishna";
	 JButton back=new JButton("Back");
     PasswordVerification(String tableName){
    	 this.tableName=tableName;
    	 setLocation(450,400);
    	 setTitle("Authorization");
    	 setSize(600,300);
    	 setVisible(true);
    	 setLayout(new GridLayout(3,1));
    	 
    	 //panel 0
    	 p[0]=new JPanel();
    	 JLabel statement=new JLabel("Enter password to go ahead");
    	 statement.setFont(new Font("plain",1,18));
    	 p[0].add(statement);
    	 
    	 //panel 1
    	 p[1]=new JPanel();
    	 p[1].add(passwordL); p[1].add(passwordT);
    	 
    	 //panel 2
    	 p[2]=new JPanel();
    	 p[2].add(submit);
    	 submit.addActionListener(this);
    	 back.setBackground(Color.green);
   	  back.setForeground(Color.white);
   	  p[2].add(back);
   	  back.addActionListener(this);
    	 
    	 //adding panels to frame
    	 add(p[0]);add(p[1]);add(p[2]);
    	  
     }
     public void actionPerformed(ActionEvent ae) {
    	 JFrame f=(JFrame)SwingUtilities.getRoot(submit);
    	 if(ae.getActionCommand().equals("submit")) {
    		 if(passwordT.getText().equals(password)) {
    			 f.dispose();
    			 if(tableName.equals("Delete")) {
    				 new delete();
    			 }else if(tableName.equals("Insert")) {
    				 new Insert1();
    			 }else if(tableName.equals("Update")) {
    				 new Update();
    			 }
    			
    		 } else {
    			 JOptionPane.showMessageDialog(this,"Incorrect password entered");
    		 }
    	 } else if(ae.getActionCommand().equals("Back")) {
				  f.dispose();
				  new Start();
    		 }	 
    	 }
     
     
}
