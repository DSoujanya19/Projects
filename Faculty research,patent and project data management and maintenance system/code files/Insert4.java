package pbL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
class Insert4 extends JFrame implements ActionListener{
	static Connection cn;
	static ResultSet rs;
	static Statement st;
	static Scanner sc;
	JLabel l[]=new JLabel[6];
	JTextField t[]=new JTextField[6];
	JPanel[] p=new JPanel[3];
	JButton b;
	JButton back=new JButton("Back");
	Insert4(){
		//allocation of memory for panels
		 for(int i=0;i<3;i++) {
			  p[i]=new JPanel();
		  }
		 add(p[0],BorderLayout.NORTH);
		 add(p[1]);
		 
		 //panel 0
		 p[0].setLayout(new GridLayout(3,1));
		 p[0].add(new JLabel());
		 JLabel faculty=new JLabel("Project Data");
		 faculty.setFont(new Font("Arial",1,20));
		 faculty.setHorizontalAlignment(JLabel.CENTER);
		 faculty.setForeground(Color.white);
		 p[0].add(faculty);
		 p[0].setBackground(Color.pink);
		 
		 //panel 1
		 p[1].setLayout(new GridLayout(7,2));
		//allocation memory for Labels and text fields
	  for(int i=0;i<5;i++) {
		  l[i]=new JLabel();
		  t[i]=new JTextField(10);
		  l[i].setFont(new Font("Arial",1,15));
		  p[1].add(l[i]);p[1].add(t[i]);  
	  }
	 
		setTitle("INSERTING INTO PROJECT");
		l[0].setText("PROJECT TITLE");
		l[1].setText("PROJECT DATE");
		l[2].setText("TECHNICAL FIELD");
		l[3].setText("DESCRIPTION");
		l[4].setText("PROJECT FACULTY ID");
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
			String pname;
			int n=0;
			pname=t[0].getText();
			String pdate;
			pdate=t[1].getText();
			String fid;
			fid=t[4].getText().toUpperCase();
			String technicalfield;
			technicalfield=t[2].getText();
			String description;
			description=t[3].getText();
			
			try {
				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
				st=cn.createStatement();
				System.out.println("insert into projects values"+"("+"'"+fid+"',"+"'"+pname+"'"+","+"'"+pdate+"'"+","+"'"+technicalfield+"'"+","+"'"+description+"')");
				if(fid.equals("")|description.equals("")|pname.equals("")|pdate.equals("")|technicalfield.equals("")) {
					JOptionPane.showMessageDialog(this, "Enter complete data");
					return;
				}
				n=st.executeUpdate("insert into projects values"+"("+"'"+fid+"',"+"'"+pname+"'"+","+"'"+pdate+"'"+","+"'"+technicalfield+"'"+","+"'"+description+"')");
			
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
						JOptionPane.showMessageDialog(this, e.getMessage());
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
