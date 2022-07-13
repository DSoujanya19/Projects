package pbL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
class R extends JFrame implements ActionListener{
	static Connection cn;
	static ResultSet rs;
	static Statement st;
	static Scanner sc;
	String tt[];
	String c[];
	JLabel l[]=new JLabel[3];
	JTextField t[]=new JTextField[3];
	JPanel[] p=new JPanel[3];
	JButton b;
	JButton back=new JButton("Back");
	R(String tt[],String c[]){
		this.tt=tt;
		this.c=c;
		//allocation of memory for panels
		 for(int i=0;i<3;i++) {
			  p[i]=new JPanel();
		  }
		 add(p[0],BorderLayout.NORTH);
		 add(p[1]);
		 
		 //panel 0
		 p[0].setLayout(new GridLayout(3,1));
		 p[0].add(new JLabel());
		 JLabel faculty=new JLabel(tt[0]);
		 faculty.setFont(new Font("Arial",1,20));
		 faculty.setHorizontalAlignment(JLabel.CENTER);
		 faculty.setForeground(Color.white);
		 p[0].add(faculty);
		 p[0].setBackground(Color.pink);
		 
		 //panel 1
		 p[1].setLayout(new GridLayout(4,2));
		//allocation memory for Labels and text fields
	  for(int i=0;i<3;i++) {
		  l[i]=new JLabel();
		  t[i]=new JTextField(10);
		  l[i].setFont(new Font("Arial",1,15));
		  p[1].add(l[i]);p[1].add(t[i]);  
	  }
	 
		setTitle(tt[0]);
		l[0].setText(tt[1]);
		l[1].setText(tt[2]);
		l[2].setText(tt[3]);
		//panel 2
		b=new JButton("ok");
		b.addActionListener(this);
		back.addActionListener(this);
		p[2].add(b);
		p[2].add(back);
		p[1].add(p[2],BorderLayout.SOUTH);
		setLocation(600,300);
		setVisible(true);
		setSize(500,500);
	}
		public void actionPerformed(ActionEvent ae) {
			JFrame f= (JFrame)SwingUtilities.getRoot(b);//to get reference of frame
			if(ae.getSource()==b) {
			String fid,topic;
			int n=0;
			topic=t[0].getText();
			fid=t[2].getText();
			String fname;
			fname=t[1].getText();
			
			try {
				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
				st=cn.createStatement();
				System.out.println(""+fid);
				if(fid.equals("")|fname.equals("")) {
					JOptionPane.showMessageDialog(this, "Enter complete data");
					return;
				}
				
				 n=st.executeUpdate("update "+c[0]+" set "+c[1]+" ='"+fname+"' where fid ='"+fid+"' and "+c[2]+" ='"+topic+"'");
			}
			
			catch(Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				return;
			}
			if(n>0) {
				JOptionPane.showMessageDialog(this, "Data updated successfully");
			}else {
				JOptionPane.showMessageDialog(this, "No such data found to update");
			}
			f.dispose();
			openFrame();
			}else if(ae.getSource()==back) {
				openFrame();
				f.dispose();
			}
		}
		 void openFrame() {
			if(tt[0].indexOf("RESEARCH")!=-1) {
				new UpdateResearch();
			}else if(tt[0].indexOf("PATENT")!=-1) {
				new UpdatePatent();
			}else if(tt[0].indexOf("PROJECT")!=-1) {
				new UpdateProject();
			}
		}
	}
	
