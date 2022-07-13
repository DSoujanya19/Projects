package pbL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pbL.delete.Action;

class UpdateResearch extends JFrame implements ActionListener{
	JPanel p[]=new JPanel[3];
	JButton b[]=new JButton[4];
	JButton close =new JButton("Close");
	JButton back =new JButton("Back");
	UpdateResearch(){
		//allocating memory for panels
		for(int i=0;i<3;i++) {
			p[i]=new JPanel();
		}
		//panel 0
		p[0]=new JPanel();
		p[0].add(new JLabel());
		p[0].setLayout(new GridLayout(3,1));
		p[0].setBackground(new Color(153, 153, 255));
		JLabel insert=new JLabel("Update Research");
		insert.setFont(new Font("Arial",1,18));
		p[0].add(insert);
		insert.setHorizontalAlignment(JLabel.CENTER);
	
		//panel 1
		p[1].setLayout(new GridLayout(10,1));
		  //allocating memory for buttons
		for(int i=0;i<4;i++) {
					b[i]=new JButton();
					 b[i].setFont(new Font("Arial",1,18));
					 p[1].add(b[i]);
					 p[1].add(new JLabel());
					 b[i].addActionListener(this);
			}
		b[0].setText("Topic");
		b[1].setText("Date Of Publish");
		b[2].setText("Description");
		b[3].setText("Published in");
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
		setTitle("Research update");
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
				String s1[]= new String[] {"UPDATING RESEARCH TOPIC  ","RESEARCH OLD TOPIC ","NEW TOPIC","FACULTY ID"};
				String s2[]= new String[] {"researchPaper","topic","topic"};
				new R(s1,s2);
		}
		else if(ae.getSource()==b[1]) {
			 f.dispose();
			String s1[]= new String[] {"UPDATING RESEARCH DATE","RESEARCH TOPIC"," NEW DATE","FACULTY ID"};
			String s2[]= new String[] {"researchPaper","DateOfPublish","topic"};
			new R(s1,s2);
		}
		else if(ae.getSource()==b[2]) {
			 f.dispose();
				String s1[]= new String[] {"UPDATING RESEARCH DESCRIPTION","RESEARCH TOPIC","NEW DESCRIPTION","FACULTY ID"};
				String s2[]= new String[] {"researchPaper","description","topic"};
				new R(s1,s2); 
		}
		else if(ae.getSource()==b[3]) {
			 f.dispose();
			 String s1[]={"UPDATING RESEARCH PUBLISHED IN  ","TOPIC"," NEW PUBLISHED IN","FACULTY ID"};
			 String s2[]={"researchPaper","publishedin","topic"};
			 new R(s1,s2);
		}
	}
}