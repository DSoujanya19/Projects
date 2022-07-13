package pbL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class delete extends JFrame {
	JButton b[]=new JButton[4];
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JButton close =new JButton("Close");
	JButton back =new JButton("Back");
	JLabel l1=new JLabel();
	JLabel l2=new JLabel("DELETE");
	delete(){
		setTitle("Delete");
		setLocation(650,300);
		setVisible(true);
		setSize(500,500);
		p1.setLayout(new GridLayout(10,1));
		for(int i=0;i<4;i++) {
			  b[i]=new JButton();
			  b[i].setFont(new Font("Arial",1,18));
			  p1.add(b[i]);
			  p1.add(new JLabel());
			  b[i].addActionListener(new Action());
		  }
		p2.add(new JLabel());
		p1.add(new JLabel());
		add(p1);add(p2,BorderLayout.NORTH);p1.add(p3);
		p2.setBackground(new Color(153, 153, 255));
		p2.setLayout(new GridLayout(4,1));
		l2.setFont(new Font("Arial",1,20));
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setForeground(Color.white);
		p2.add(l2);
		  b[0].setText("Faculty information");
		  b[1].setText("Project information");
		  b[2].setText("Patents information");
		  b[3].setText("Research information");
		  back.setBackground(Color.green);
		  close.setBackground(Color.red);
		  close.setForeground(Color.white);
		  p3.add(back);p3.add(close);
		  back.addActionListener(new Action());
		  close.addActionListener(new Action());
	}
	class Action implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			JFrame f=(JFrame)SwingUtilities.getRoot(close);
			 if(ae.getActionCommand().equals("Close")) {
				  f.dispose();
			  }else if(ae.getActionCommand().equals("Back")) {
				  f.dispose();
				  new Start();
			  }else if(ae.getActionCommand().equals("Faculty information")){
				  new DeleteDataVisual("faculty");
				  f.dispose();
			  }else if(ae.getActionCommand().equals("Project information")){
				  new DeleteDataVisual("projects");
				  f.dispose();
			  }else if(ae.getActionCommand().equals("Patents information")){
				  new DeleteDataVisual("patents");
				  f.dispose();
			  }else if(ae.getActionCommand().equals("Research information")){
				  new DeleteDataVisual("researchPaper");
				  f.dispose();
			  }
		}
	}
	
}
