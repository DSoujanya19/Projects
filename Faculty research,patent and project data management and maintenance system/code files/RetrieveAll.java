package pbL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RetrieveAll extends JFrame{
	JPanel p[]=new JPanel[6];
	JButton b[]=new JButton[8];
	JLabel head1=new JLabel("Retrieve");
	JLabel head2=new JLabel("Enter data in atleast one column to search");
	JLabel l[]=new JLabel[4];
	
	JTextField text[]=new JTextField[4];
	String Name[]= {"Retrieve by faculty","Retrieve from faculty","Retrieve by project","Retrieve from project","Retrieve by patent","Retrieve from patents","Retrieve by researchPaper","Retrieve from researchPaper"};
	JButton search=new JButton("Search");
	JButton close=new JButton("Close");
	JButton back=new JButton("Back");
 RetrieveAll(){
	 setTitle("Retrieve");
	 setSize(900,650);
	 setLocationRelativeTo(null);
	 setVisible(true);
	 setLayout(new GridLayout(6,1));
	 //panel memory allocation
		 for(int i=0;i<6;i++) {
			 p[i]=new JPanel();
			 add(p[i]);
		 }
		 //allocating memory to buttons
	     for(int i=0;i<8;i++) {
	    	 b[i]=new JButton(Name[i]);
	    	 p[2].add(b[i]);
	    	 b[i].setBackground(new Color(255, 246, 153));
	    	 b[i].addActionListener(new Action());
	     }
	 //adding components to panel 0
		 p[0].setBackground(new Color(153, 153, 255));
		 p[0].setLayout(new GridLayout(3,1));
		 p[0].add(new JLabel());
		 head1.setFont(new Font("Arial",1,22));
		 p[0].add(head1);
		 head1.setForeground(Color.white);
		 head1.setHorizontalAlignment(JLabel.CENTER);
	//adding components to panel 1
		 p[1].add(b[0]); p[1].add(b[1]);
		 p[1].setBackground(Color.white);
//adding components to panel 2
p[2].add(b[2]); p[2].add(b[3]);
p[2].setBackground(Color.white);
//adding components to panel 3
p[3].add(b[4]); p[3].add(b[5]);
p[3].setBackground(Color.white);
	//adding components to panel 4
       p[4].add(b[6]); p[4].add(b[7]);
	     p[4].setBackground(Color.white);
	    //adding components to panel 5
	     back.setBackground(Color.green);
		  close.setBackground(Color.red);
		  close.setForeground(Color.white);
		  back.setForeground(Color.white);
		  p[5].add(back);p[5].add(close);
		  p[5].setBackground(Color.white);
		  back.addActionListener(new Action());
		  close.addActionListener(new Action());
		
 }

 class Action implements ActionListener{
	  public void actionPerformed(ActionEvent ae) {
		  GetResultset ans=new GetResultset();
		  if(ae.getActionCommand().equals("Close")) {
			  JFrame f=(JFrame)SwingUtilities.getRoot(close);
			  f.dispose();
		  }else if(ae.getActionCommand().equals("Back")) {
			  JFrame f=(JFrame)SwingUtilities.getRoot(close);
			  f.dispose();
			  new Start();
		  }else if(ae.getSource()==b[0]) {
			 String input= JOptionPane.showInputDialog("Enter fid");
			 if(input!=null) 
			ans.retrieveFrom("faculty","Fid",input,"fid");
		  }else if(ae.getActionCommand().equals("Retrieve from faculty")) {
			  ans.retrieveFrom("faculty","Fid",null,null);	
		  }else if(ae.getSource()==b[2]) {
				 String input= JOptionPane.showInputDialog("Enter project name");
				 if(input!=null) 
				ans.retrieveFrom("projects","Fid",input,"pname");
			  }else if(ae.getActionCommand().equals("Retrieve from project")) {
			  ans.retrieveFrom("projects","Fid",null,null);	  
		  }else if(ae.getActionCommand().equals("Retrieve from patents")) { 
			  ans.retrieveFrom("patents","Fid",null,null);	  
		  }else if(ae.getSource()==b[4]) {
				 String input= JOptionPane.showInputDialog("Enter patent name");
				 if(input!=null) 
				ans.retrieveFrom("patents","Fid",input,"Title");
			  }else if(ae.getActionCommand().equals("Retrieve from researchPaper")) {
			  ans.retrieveFrom("researchPaper","Fid",null,null);	  
		  }else if(ae.getSource()==b[6]) {
				 String input= JOptionPane.showInputDialog("Enter researchpaper title");
				 if(input!=null) 
				ans.retrieveFrom("researchPaper","Fid",input,"Topic");
			  }
	  }
 }
public static void main(String args[]) {
	new RetrieveAll();
}
 
}

