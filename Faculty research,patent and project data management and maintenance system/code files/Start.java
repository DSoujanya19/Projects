package pbL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start extends JFrame {
	JButton b[]=new JButton[4];
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JLabel l1=new JLabel();
	JLabel l2=new JLabel("CHOOSE");
  Start(){
	  setLocation(700,250);
	  setTitle("Faculty data");
	l1.setFont(new Font("Arial",1,18));
	l1.setText("FACULTY DATA");
	GridLayout layout=new GridLayout(4,1);
	layout.setHgap(25);
	layout.setVgap(10);
	add(p1);
	add(p2,BorderLayout.NORTH);
	l2.setFont(new Font("Arial",1,18));
	p2.setBackground(new Color(255,255,153));
	l2.setHorizontalAlignment(JLabel.CENTER);
	p2.add(new JLabel());
     p2.add(l2);
     for(int i=0;i<4;i++) {
		  b[i]=new JButton();
		  b[i].setFont(new Font("Arial",1,18));
		  p1.add(b[i]);
		 b[i].addActionListener(new ActionStart());
	  }
	  b[0].setText("Insert");
	  b[1].setText("Update");
	  b[2].setText("Delete");
	  b[3].setText("Retrieve");
	p1.setLayout(layout);
	p2.setLayout(new GridLayout(4,1));

	setVisible(true);
	setSize(500,500);
	
  }
  
  class ActionStart implements ActionListener{
	  public void actionPerformed(ActionEvent ae) {
		  JFrame f=(JFrame)SwingUtilities.getRoot(p1);
		  if(ae.getActionCommand().equals("Insert")) {
			  new PasswordVerification("Insert");
			  f.dispose();
		  }else if(ae.getActionCommand().equals("Update")) {
			  new PasswordVerification("Update");
			  f.dispose();
		  }else if(ae.getActionCommand().equals("Delete")){
			  new PasswordVerification("Delete");
			  f.dispose();
		  }else if(ae.getActionCommand().equals("Retrieve")){
			  new RetrieveAll();
			  f.dispose();
		  }
	  }
  }
  public static void main(String args[]) {
	  new Start();
  }
}
