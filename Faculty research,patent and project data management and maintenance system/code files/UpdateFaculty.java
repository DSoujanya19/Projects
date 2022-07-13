package pbL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pbL.delete.Action;

class UpdateFaculty extends JFrame implements ActionListener{
	JPanel p[]=new JPanel[3];
	JButton b[]=new JButton[3];
	JButton close =new JButton("Close");
	JButton back =new JButton("Back");
	UpdateFaculty(){
		//allocating memory for panels
		for(int i=0;i<3;i++) {
			p[i]=new JPanel();
		}
		//panel 0
		p[0]=new JPanel();
		p[0].add(new JLabel());
		p[0].setLayout(new GridLayout(3,1));
		p[0].setBackground(new Color(153, 153, 255));
		JLabel insert=new JLabel("Update Faculty");
		insert.setFont(new Font("Arial",1,18));
		p[0].add(insert);
		insert.setHorizontalAlignment(JLabel.CENTER);
	
		//panel 1
		p[1].setLayout(new GridLayout(9,1));
		  //allocating memory for buttons
		for(int i=0;i<3;i++) {
					b[i]=new JButton();
					 b[i].setFont(new Font("Arial",1,18));
					 p[1].add(b[i]);
					 p[1].add(new JLabel());
					 b[i].addActionListener(this);
			}
		b[0].setText("Faculty Name");
		b[1].setText("Faculty branch");
		b[2].setText("Gender");
		//panel 2
		 back.setBackground(Color.green);
		  close.setBackground(Color.red);
		  close.setForeground(Color.white);
		  p[2].add(back);p[2].add(close);
		  back.addActionListener(this);
		  close.addActionListener(this);
		setVisible(true);
		setSize(500,500);
         add(p[0],BorderLayout.NORTH);add(p[1]);
         p[1].add(p[2]);
		setTitle("Faculty data update");
		setLocation(600,300);
	}
	public void actionPerformed(ActionEvent ae) {
		JFrame f=(JFrame)SwingUtilities.getRoot(close);
		 if(ae.getActionCommand().equals("Close")) {
			  f.dispose();
		  }else if(ae.getActionCommand().equals("Back")) {
			  f.dispose();
			  new Update();
		  }else if(ae.getSource()==b[0]) {
			  f.dispose();
				String s1[]= new String[] {"UPDATING FACULTY NAME ","FACULTY OLD NAME","FACULTY NEW NAME","FACULTY ID"};
				String s2[]= new String[] {"fname"};
				new RF(s1,s2);
		}
		else if(ae.getSource()==b[1]) {
			 f.dispose();
			String s1[]= new String[] {"UPDATING FACULTY BRANCH","FACULTY OLD BRANCH","FACULTY NEW BRANCH","FACULTY ID"};
			String s2[]= new String[] {"branch"};
			new RF(s1,s2);
		}
		else if(ae.getSource()==b[2]) {
			 f.dispose();
				String s1[]= new String[] {"UPDATING FACULTY GENDER","FACULTY NAME","GENDER","FACULTY ID"};
				String s2[]= new String[] {"gender"};
				new RF(s1,s2); 
		}
	}
}