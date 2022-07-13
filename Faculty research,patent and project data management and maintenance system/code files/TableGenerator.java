package pbL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TableGenerator extends JFrame implements ActionListener{
	JPanel p=new JPanel();
	JComboBox<String> cb;
	String tablename;
	String Scols[];
	TableGenerator(String[][] data,String cols[],String tableName,boolean canSort){
		tablename=tableName;
		Scols=cols;
		setLayout(new GridLayout(2,1));
		cb=new JComboBox(cols);
		cb.addActionListener(this);
		if(data==null) {
			JOptionPane.showMessageDialog(this,"No data stored");
			return;
		}
		if(!canSort) {
	p.add(new JLabel("Sortby"));
	p.add(cb);
		}
		p.setBackground(Color.white);
		setTitle(tableName.toUpperCase());
		setLocation(250,150);
	    	JTable t=new JTable(data,cols);
	    	t.setPreferredScrollableViewportSize(new Dimension(50,50));
	    	t.setFillsViewportHeight(true);
	    	t.setFont(new Font("plain",0,18));
	    	t.getTableHeader().setFont(new Font("Arial",1,17));
	    	t.getTableHeader().setBackground(new Color(125, 157, 221));
	    	t.getTableHeader().setForeground(Color.white);	  
	    	JScrollPane s=new JScrollPane(t);
	    	t.setSize(1000,500);
            t.setRowHeight(50);
	    	add(s);
	    	add(p);
	    	setVisible(true);
	    	setSize(1000,600);
		}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==cb) {
			  GetResultset ans=new GetResultset();
			  int n=cb.getSelectedIndex();
			  ans.retrieveFrom(tablename,Scols[n],null,null);	
			  JFrame frame=(JFrame)SwingUtilities.getRoot(cb);
			  frame.dispose();
		}
	}
}
