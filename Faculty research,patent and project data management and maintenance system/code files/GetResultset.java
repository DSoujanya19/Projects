package pbL;

import java.sql.*;

import javax.swing.JOptionPane;

public class GetResultset {
	static Connection cn;
	static ResultSet rs;
	static Statement st;
	static String columns[] ;
	static int col=0;
	
	GetResultset(){
		init();	
	}
	void init () {
		//obtaining connection
		 try {
		 cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
		 st=cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 }catch(Exception e) {
			e.printStackTrace();
		 }
	 }
	void retrieveFrom(String tableName,String attribute,String val,String coln) {
		
		String data[][]= null ;
		 try {
			 //getting result set
			
			 if(val==null) {
			 rs=st.executeQuery("select * from "+tableName+ " order by "+attribute); 
			 }else { 
			rs=st.executeQuery("select * from "+tableName+ " where "+coln+" = '"+val+"'");
			 } 
			 //when result set is null, a message box is displayed that says "no record"
			if(!rs.next()) {
				new TableGenerator(data,new String[] {},tableName,true);
				return;
			}
			//getting column names
			getTableColumnDetails();
			//calculating the number of rows in the result set
			rs.last();
		    int nrows=rs.getRow();
		     data=new String[nrows][col];
		    rs.first();
		    int i=-1;
		    //storing the tuples into a string that is passed as data to the table class
		    
		    do{
		    	i++;
		        for(int j=1;j<=col;j++) {
		        	
		    	   data[i][j-1]=rs.getString(j);
		        }
		    }while(rs.next());
		 
	     }catch(Exception e) {	 
	    	e.printStackTrace();;
		 }	
		//data and column names passed to table generator class
			if(val==null) {
				new TableGenerator(data,getTableColumnDetails(),tableName,false);
			}else {
				new TableGenerator(data,getTableColumnDetails(),tableName,true);	
			}
			
			
			
	 }
	public static String[] getTableColumnDetails(){
		String[] columns=null;
		ResultSetMetaData rsmd;
		try {
		 rsmd=rs.getMetaData();
		 col=rsmd.getColumnCount();
		 columns=new String[col];
		 //getting column names and storing them in columns variable
		 for(int i=1;i<=col;i++) {
			 columns[i-1]=rsmd.getColumnLabel(i);
		 }
		}catch(Exception e) {
			
		}
		return columns;
	}
	
}
