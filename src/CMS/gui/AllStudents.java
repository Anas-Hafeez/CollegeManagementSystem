package CMS.gui;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import CMS.dbinfo.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class AllStudents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllStudents frame = new AllStudents();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
   
	
	public void filltable()
	{
	  Connection con=DBConnection.createConnection();
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	  
	  String strselect="Select *from student_details";
	  try {
		  ps=con.prepareStatement(strselect);
		  rs=ps.executeQuery();         //return the reference(address) of all the rows of course_details table 
		  
//		  rs.next();   //puts the courser on the not null rows one by one
		  
		  TableModel model=DbUtils.resultSetToTableModel(rs);   //RESULT SET WILL BE WHOLELY CHANGD TO TABLE
		  //CLASS OF JAR FILE RS2XML
		  
		 table.setModel(model);
		 
		 
		 TableColumnModel tcm=table.getColumnModel();    //gets the model of the name of all column of the table 
		 tcm.getColumn(0).setHeaderValue("STUDENT ROLL NO");    //COLOUMN index starts from 0
		 tcm.getColumn(1).setHeaderValue("STUDENT NAME");
		 tcm.getColumn(2).setHeaderValue("STUDENT EMAIL");
		 tcm.getColumn(3).setHeaderValue("STUDENT PHONE NO");
		 tcm.getColumn(4).setHeaderValue("COURSE ENROLLED IN ");
		 tcm.getColumn(5).setHeaderValue("STUDENT ADDRESS");
		 
		  
	  }
		catch(SQLException se)
	  {
			se.printStackTrace();
	  }
	  finally
	  {
		  try
		  {
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			if(con!=null)
				con.close();
			  
		  }
		  catch(SQLException se)
		  {
			  se.printStackTrace();
		  }
		  
		  
		  
	  }
		
		
		
	}
	/**
	 * Create the frame.
	 */
	public AllStudents() {
		
		
		
		setTitle("allcourses");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DETAILS OF ALL STUDENTS");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 24));
		lblNewLabel.setBounds(200, 34, 271, 38);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 579, 137);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(255, 0, 0));
		table.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		
		
		//formatting for table headings
		JTableHeader hd=table.getTableHeader();   //return jtable header class all data 
		hd.setBackground(Color.LIGHT_GRAY);
		hd.setForeground(Color.MAGENTA);
		hd.setFont(new Font("Constantia", Font.BOLD, 10));
		
		
		
		
		filltable();   //calling fill table method
		scrollPane.setViewportView(table);
	}
}
