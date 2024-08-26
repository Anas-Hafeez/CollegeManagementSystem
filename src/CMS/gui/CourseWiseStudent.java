package CMS.gui;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import CMS.dbinfo.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class CourseWiseStudent extends JFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox cmbcourse;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public  void fillcombo()
	{
		Connection con=DBConnection.createConnection();
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	String selectQuery="select * from course_details ";//to retrieve the data from the table
	try {
		ps=con.prepareStatement(selectQuery);
		rs=  ps.executeQuery();   //used only in select query
		
		
		   while( rs.next()==true)  //checks if data is present on the row
		   {
			   String cname=rs.getString("course_name");  //it is used to fetch the value from the specified coloumn
		   
			   cmbcourse.addItem(cname);    //to add the item in  the combo box 
			   
			   
		   }
		   
		   
		}
	catch(SQLException se)
	{
	se.printStackTrace();	
	}
	
	finally {
		try {
		if (rs!=null)
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

	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseWiseStudent frame = new CourseWiseStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CourseWiseStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmbcourse = new JComboBox();
		 cmbcourse.addItemListener(this);
		 cmbcourse.setModel(new DefaultComboBoxModel(new String[] {"Select course"}));
		cmbcourse.setBounds(237, 28, 111, 22);
		fillcombo();
		contentPane.add(cmbcourse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 61, 417, 321);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}




	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==1)  //current state is 1
		{
			Connection con=DBConnection.createConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			
			String strselect="select * from student_details where course_name=?";
			
			
			String coursename=(String )e.getItem();  //or cmbcourse.getselected item()
			try {
				if(coursename.equalsIgnoreCase("select course"))
				{
					JOptionPane.showMessageDialog(this, "please enter a valid course");
				}
				else {
				ps=con.prepareStatement(strselect);
				ps.setString(1, coursename);
				rs=ps.executeQuery();
				
				
				
				TableModel model=DbUtils.resultSetToTableModel(rs);
//				table.setModel(model);
				
				
				int count=model.getRowCount();   //to get the no of rows in the table 
				if(count==0)   //means no data or row on selected course
				{
					
					
					JOptionPane.showMessageDialog(this, "no student has been registered with " +coursename+" course");
					//table.setModel(new DefaultTableModel());// this is also used to clear the data from the table
					
					DefaultTableModel dm=(DefaultTableModel)table.getModel();   //to get the data from the table
					dm.getDataVector().removeAllElements();  //to delete the data from the table
					dm.fireTableDataChanged();  //it will refresh the table
				}
				
				else
				{
					table.setModel(model);  //else we will set the data in the table model
				}
					
					
					
				}
				
					
				
				
				
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			
			
		}
			
		
	}
}
