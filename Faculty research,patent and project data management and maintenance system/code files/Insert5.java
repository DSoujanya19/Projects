package pbL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
class Insert5 extends JFrame implements ActionListener{
	static Connection cn;
	static ResultSet rs;
	static Statement st;
	static Scanner sc;
	JPanel r1=new JPanel();
	JPanel r2=new JPanel();
	JRadioButton r[]=new JRadioButton[5];
	ButtonGroup button=new ButtonGroup();
	String s[]= {"Indexing","SCI","ESCI","Scoping","UGC"};
	JLabel l[]=new JLabel[6];
	JTextField t[]=new JTextField[6];
	JPanel[] p=new JPanel[5];
	JButton b;
	JButton back=new JButton("Back");
	Insert5(){
		
		//allocation of memory for panels
		 for(int i=0;i<5;i++) {
			  p[i]=new JPanel();
		  }
		 add(p[0],BorderLayout.NORTH);
		 add(p[1]);
		 
		 //panel 0
		 p[0].setLayout(new GridLayout(3,1));
		 p[0].add(new JLabel());
		 JLabel faculty=new JLabel("Research Paper Data");
		 faculty.setFont(new Font("Arial",1,20));
		 faculty.setHorizontalAlignment(JLabel.CENTER);
		 faculty.setForeground(Color.white);
		 p[0].add(faculty);
		 p[0].setBackground(Color.pink);
		 
		 //panel 1
		 p[1].setLayout(new GridLayout(8,2));
		//allocation memory for Labels and text fields
	  for(int i=0;i<5;i++) {
		  l[i]=new JLabel();
		  t[i]=new JTextField(10);
		  l[i].setFont(new Font("Arial",1,15));
		  p[1].add(l[i]);p[1].add(t[i]);  
	  }
	 
		setTitle("  INSERTING INTO RESEARCH PAPER");
		l[0].setText("  TOPIC");
		l[1].setText("  DATE OF PUBLISH");
		l[2].setText(" PAPER DESCRIPTION");
		l[3].setText(" PUBLISHED IN");
		l[4].setText("  FACULTY ID ");
		//radio button
		 for(int i=0;i<5;i++) {
			 r[i]=new JRadioButton(s[i]);
			 button.add(r[i]);
			 r[i].setActionCommand(s[i]);
			
		 }
		 r1.add(r[0]);r1.add(r[1]);
		 r2.add(r[2]);r2.add(r[3]);r2.add(r[4]);
		 p[1].add(r1); p[1].add(r2);
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
			String topic;
			int n=0;
			topic=t[0].getText().trim();
			String dateofpublish;
			dateofpublish=t[1].getText();
			String description;
			description=t[2].getText().toUpperCase().trim();
			String publishedIn;
			publishedIn=t[3].getText().trim();
			String fid,addDet;
			fid=t[4].getText().trim();
			addDet=button.getSelection().getActionCommand();
			
			try {
				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
				st=cn.createStatement();
				if(fid.equals("")|description.equals("")|topic.equals("")|dateofpublish.equals("")|publishedIn.equals("")) {
					JOptionPane.showMessageDialog(this, "Enter complete data");
					return;
				}
				n=st.executeUpdate("insert into researchPaper values "+"("+"'"+fid+"',"+"'"+topic+"',"+"'"+dateofpublish+"'"+","+"'"+description+"'"+","+"'"+publishedIn+"','"+addDet+"')");		
			
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
