package pbL;
//"insert into table patents "+"("+"'"+title+"'"+","+"'"+patentdate+"'"+","+"'"+patenttype+"'"+","+"'"+fid+"'"+"'"+technicalfield+"'"+")"
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
class Insert3 extends JFrame implements ActionListener{
	static Connection cn;
	static ResultSet rs;
	static Statement st;
	static Scanner sc;
	JLabel l[]=new JLabel[6];
	JTextField t[]=new JTextField[6];
	JPanel[] p=new JPanel[3];
	JButton b;
	JButton back=new JButton("Back");
	Insert3(){
		//allocation of memory for panels
		 for(int i=0;i<3;i++) {
			  p[i]=new JPanel();
		  }
		 add(p[0],BorderLayout.NORTH);
		 add(p[1]);
		 
		 //panel 0
		 p[0].setLayout(new GridLayout(3,1));
		 p[0].add(new JLabel());
		 JLabel faculty=new JLabel("Patents Data");
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
	 
		setTitle("  INSERTING INTO PATENT");
		l[0].setText("  PATENT TITLE");
		l[1].setText("  PATENT TYPE");
		l[2].setText("  FACULTY ID");
		l[3].setText("  TECHNICAL FIELD");
		l[4].setText(" DATE OF APPLICATION");
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
			String title;
			int n=0;
			title=t[0].getText().trim();
			String patenttype;
			patenttype=t[1].getText().trim();
			String fid;
			fid=t[2].getText().toUpperCase().trim();
			String technicalfield;
			technicalfield=t[3].getText().trim();
			String patentdate;
			patentdate=t[4].getText();
			
			try {
				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
				st=cn.createStatement();
				if(fid.equals("")|patentdate.equals("")|patenttype.equals("")|title.equals("")|technicalfield.equals("")) {
					JOptionPane.showMessageDialog(this, "Enter complete data");
					return;
				}
				n=st.executeUpdate("insert into patents values"+"('"+fid+"',"+"'"+title+"','"+patentdate+"',"+"'"+patenttype+"','"+technicalfield+"')");
				
				}
			
			catch(Exception e) {
				System.out.println(e.getMessage());
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
