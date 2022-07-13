package pbL;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteDataVisual extends JFrame implements ActionListener{
  String column[][]=new String[2][];
  boolean noData=false;
  JButton back=new JButton("Back");
  String c[]=new String[2];
  static Connection cn;
  static Statement st;
  static ResultSet rs;
  JLabel l[];
  JPanel p[]=new JPanel[4];
  JComboBox <String>cb[]=new JComboBox[2];
  String tableName;
  DeleteDataVisual(String tableName){
	  setSize(600,600);
	  this.tableName=tableName;
	  for(int i=0;i<4;i++) {
			 p[i]=new JPanel();
			 add(p[i]);
			 p[i].setBackground(Color.white);
		 }
	 
	 init();
	 if(noData) {
		 JOptionPane.showMessageDialog(this, "No data in table "+tableName);
		 JFrame f=(JFrame)SwingUtilities.getRoot(p[0]);
		 f.dispose();
		 return;
	 }
	setLocation(600,200);
	  //frame
	  setTitle("Deletion");
	  setVisible(true);
	  setLayout(new GridLayout(4,2));
	  //panel 0
	  p[0].add(new JLabel());
	  p[0].setLayout(new GridLayout(3,1));
	  p[0].setBackground(new Color(153, 153, 255));
	  JLabel choose=new JLabel("Select");
	  choose.setForeground(Color.white);
	  choose.setHorizontalAlignment(JLabel.CENTER);
	  choose.setFont(new Font("Arial",1,20));
	  p[0].add(choose);
	  //panel 1
	  JLabel first=new JLabel("Fid     ");
	  first.setFont(new Font("Arial",1,18));
	  p[1].add(first);
	  p[1].add(cb[0]);
	  //panel 2
	  JLabel second=new JLabel(c[1]+"\t");
	  second.setFont(new Font("Arial",1,18));
	  p[2].add(second);
	  p[2].add(cb[1]);
	  //panel 3
	  JButton submit=new JButton("submit");
	  back.setBackground(Color.green);
	  back.addActionListener(this);
   	  back.setForeground(Color.white);
   	  p[3].add(back);
//   	  back.addActionListener(this);
	  submit.setBackground(Color.orange);
	  submit.setForeground(Color.white);
	  p[3].add(submit);
	  submit.addActionListener(this);
	  submit.setHorizontalAlignment(JButton.CENTER);
	  
  }
 public void actionPerformed(ActionEvent ae) {
	 JFrame f=(JFrame)SwingUtilities.getRoot(back);
	 if(ae.getActionCommand().equals("Back")) {
		 new delete();
		 f.dispose();
	 }else if(ae.getActionCommand().equals("submit")){
		 //If user selects submit 
		
		 String c1=column[0][cb[0].getSelectedIndex()];
		 String c2=column[1][cb[1].getSelectedIndex()];
		 //checking if record exists
		 boolean b=checkAvailable(c1,c2);
		if(b) {
			//confirming deletion 
			int result=JOptionPane.showConfirmDialog(null, "Confirm deletion by clicking on yes. Enter no to cancel deletion");
			if(result==0) {
				deleteData(c1,c2);
			}
			f.dispose();
		}else {
			//if record doesn't exist then no deletion operation is performed
			JOptionPane.showMessageDialog(this, "No record found with "+ c1+"  "+c2);
			
		}
              
		
	 }
 }

 public void deleteData(String c1,String c2) {
	 try {
		 int n=st.executeUpdate("delete from "+tableName+" where fid='"+c1+"'"+" and "+c[1]+" = '"+c2+"'");
		 if(n>0) {
			 JOptionPane.showMessageDialog(this, "Data deleted successfully");
		 }else {
			 JOptionPane.showMessageDialog(this, "Data deleted successfully");
		 }

	 }catch(Exception e) {
		 System.out.println(e.getMessage());
	 }
 }
  void init() {
	  c[0]="Fid";
	 switch(tableName) {
	 case "faculty":c[1]="Fname";
	 break;
	 case "patents":c[1]="Title";
	 break;
	 case "projects": c[1]="Pname";
     break;
	 case "researchPaper":c[1]="Topic";
	 break;
	 case "tp":c[1]="Pname";
	 }
		//obtaining connection
		 try {
		 cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
		 st=cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 getComboData();
		 
		 }catch(Exception e) {
			e.printStackTrace();
		 }
	 }
  void getComboData() {
	  for(int i=0;i<2;i++) {
	  try {
		
		  rs=st.executeQuery("select distinct "+c[i]+" from "+tableName);
		  if(!rs.next()) {
			noData=true;
			 return;
		  }
		  rs.last();
		  int n=rs.getRow();
		  column[i]=new String[n];
		  rs.first();
		  
		 for(int j=0;j<n;j++) {
			 column[i][j]= rs.getString(1);
			rs.next();
		 }
		 
	 cb[i]=new JComboBox(column[i]);
	  
	  }catch(Exception e) {
		 System.out.println("error");
	  }
	  }
  }
  
  public boolean checkAvailable(String col1,String col2) {
	  try {
	  rs=st.executeQuery("select * from "+tableName+" where Fid = '"+col1 +"'"+" and "+c[1]+" = '"+col2+"'");
	  if(rs.next()) {
		  return true;
	  } 
	  }catch(Exception e) {

	  }
	  
	  return false ;
  }

}
