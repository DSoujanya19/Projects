package pbL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pbL.delete.Action;

class Update extends JFrame implements ActionListener{
	JPanel p[]=new JPanel[3];
	JButton b[]=new JButton[4];
	JButton close =new JButton("Close");
	JButton back =new JButton("Back");
	Update(){
		//allocating memory for panels
		for(int i=0;i<3;i++) {
			p[i]=new JPanel();
		}
		//panel 0
		p[0]=new JPanel();
		p[0].add(new JLabel());
		p[0].setLayout(new GridLayout(3,1));
		p[0].setBackground(new Color(153, 153, 255));
		JLabel insert=new JLabel("Update from");
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
		b[0].setText("Faculty");
		b[1].setText("Patents");
		b[2].setText("Project");
		b[3].setText("Research Paper");
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
		setTitle("UPDATE");
		setLocation(600,300);
	}
	public void actionPerformed(ActionEvent ae) {
		JFrame f=(JFrame)SwingUtilities.getRoot(close);
		 if(ae.getActionCommand().equals("Close")) {
			  f.dispose();
		  }else if(ae.getActionCommand().equals("Back")) {
			  f.dispose();
			  new Start();
		  }else if(ae.getSource()==b[0]) {
			  f.dispose();
			 new  UpdateFaculty();
		}
		else if(ae.getSource()==b[1]) {
			f.dispose();	
			new  UpdatePatent();
		}
		else if(ae.getSource()==b[2]) {
			f.dispose();
        new UpdateProject();
		}
		else if(ae.getSource()==b[3]) {
		f.dispose();
			new UpdateResearch();
		}
	}
}