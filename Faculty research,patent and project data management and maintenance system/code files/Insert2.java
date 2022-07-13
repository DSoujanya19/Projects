package pbL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
class Insert2 extends JFrame implements ActionListener{
	static Connection cn;
	static ResultSet rs;
	static Statement st;
	static Scanner sc;
	JLabel l[]=new JLabel[4];
	JTextField t[]=new JTextField[4];
	JPanel[] p=new JPanel[3];
	JButton b;
	JButton back=new JButton("Back");
	Insert2(){
		//allocation of memory for panels
		 for(int i=0;i<3;i++) {
			  p[i]=new JPanel();
		  }
		 add(p[0],BorderLayout.NORTH);
		 add(p[1]);
		 
		 //panel 0
		 p[0].setLayout(new GridLayout(3,1));
		 p[0].add(new JLabel());
		 JLabel faculty=new JLabel("Faculty Data");
		 faculty.setFont(new Font("Arial",1,20));
		 faculty.setHorizontalAlignment(JLabel.CENTER);
		 faculty.setForeground(Color.white);
		 p[0].add(faculty);
		 p[0].setBackground(Color.pink);
		 
		 //panel 1
		 p[1].setLayout(new GridLayout(5,2));
		//allocation memory for Labels and text fields
	  for(int i=0;i<4;i++) {
		  l[i]=new JLabel();
		  t[i]=new JTextField(10);
		  l[i].setFont(new Font("Arial",1,15));
		  p[1].add(l[i]);p[1].add(t[i]);  
	  }
	 
		setTitle("INSERTING INTO FACULTY");
		l[0].setText("FACULTY ID");
		l[1].setText("FACULTY NAME");
		l[2].setText("FACULTY BRANCH");
		l[3].setText("FACULTY GENDER");
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
			JFrame f= (JFrame)SwingUtilities.getRoot(b);
			if(ae.getSource()==b) {
			String fid;
			int n=0;
			fid=t[0].getText().trim();
			String fname;
			fname=t[1].getText().trim();
			String branch;
			branch=t[2].getText().toUpperCase().trim();
			String gender;
			gender=t[3].getText().toUpperCase().trim();
			
			try {
				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
				st=cn.createStatement();
				if(fid.equals("")|fname.equals("")|branch.equals("")|gender.equals("")) {
					JOptionPane.showMessageDialog(this, "Enter complete data");
					return;
				}
				n=st.executeUpdate("insert into  faculty values"+"("+"'"+fid+"'"+","+"'"+fname+"'"+","+"'"+branch+"'"+","+"'"+gender+"'"+")");
			
			}
			
			catch(Exception e) {
				if(e.getMessage().indexOf("Duplicate entry")!=-1){
					JOptionPane.showMessageDialog(this, "Duplicate data cannot be stored");
					return;
					}else if(e.getMessage().equals("a foreign key constraint fails ")){
						JOptionPane.showMessageDialog(this, "No staff with id "+fid);
						return;
					}else {
					}
			}
			if(n>0) {
				JOptionPane.showMessageDialog(this, "Data inserted successfully");
			}
			f.dispose();
			new Insert1();
			}else if(ae.getSource()==back) {
				new Insert1();
				f.dispose();
			}
		}

	}