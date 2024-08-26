package CMS.counselor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CMS.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.*;
class updateCourse extends JFrame implements ActionListener,ItemListener{
	                                                //item listener is used to handle the click of combobox
	
	
	
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
		   
			   cmbcourse1.addItem(cname);    //to add the item in  the combo box 
			   
			   
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

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtfees;
	private JTextField txtduration;
	private JComboBox cmbcourse1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateCourse frame = new updateCourse();
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
	public updateCourse() {
		setTitle("updatecourse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(121, 236, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		setLocationRelativeTo(null);      //to place the frame of update course on the center of the screen
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmbcourse1 = new JComboBox();
		 cmbcourse1.addItemListener(this);    //binding listener with the object.
		cmbcourse1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 18));
		cmbcourse1.setModel(new DefaultComboBoxModel(new String[] {"Select Course"}));
		cmbcourse1.setName("");
		cmbcourse1.setBounds(151, 23, 123, 26);
		fillcombo();
		contentPane.add(cmbcourse1);
		
		JLabel lblNewLabel = new JLabel("Fees");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 18));
		lblNewLabel.setBounds(151, 81, 46, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Duration");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 18));
		lblNewLabel_1.setBounds(151, 118, 71, 26);
		contentPane.add(lblNewLabel_1);
		
		txtfees = new JTextField();
		txtfees.setBounds(231, 86, 86, 20);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		txtduration = new JTextField();
		txtduration.setBounds(232, 123, 86, 20);
		contentPane.add(txtduration);
		txtduration.setColumns(10);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 18));
		btnNewButton.setBounds(185, 180, 89, 23);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String fees=txtfees.getText();
		String duration=txtduration.getText();
		String coursename1=(String)cmbcourse1.getSelectedItem();  //to fetch the value from combo box
		
		
		
		
		if(fees.isEmpty()||duration.isEmpty()||coursename1.equalsIgnoreCase("Select Course"))
		{
			JOptionPane.showMessageDialog(this, "MANDATORY FIELDS" );
		}
		
		else
		{
			Connection con=DBConnection.createConnection();
			PreparedStatement ps=null;
			String srtupdate="update course_details set course_duration=?,course_fee=? where course_name=?";
			
			try {
				ps=con.prepareStatement(srtupdate);
				ps.setString(1, duration);
				ps.setInt(2, Integer.parseInt(fees));
				ps.setString(3, coursename1);
				
				System.out.println(ps);
				int status=ps.executeUpdate();
				  if(status>0)
				  {
					  JOptionPane.showMessageDialog(this, coursename1+" course updated successfully");
					  
				  }
			}
			catch(SQLException sc)
			{
				sc.printStackTrace();
			}
			finally
			{
				try {
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
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		int state=e.getStateChange();  //give the current and previous state of cmbbox ie 1 or 2
		String cname=(String)e.getItem();  //or cmbcourse1.getSelected item()
//		System.out.println(state+" "+cname);
		
		if(state==1)  //current state
		{
			
			if(cname.equalsIgnoreCase("Select Course"))
			{
				
				JOptionPane.showMessageDialog(this, "please enter a valid course");
				txtduration.setText("");
				txtfees.setText("");
			}
			
			
			else
			{
			Connection con=DBConnection.createConnection();
			String strselect="select*from course_details where course_name=?";
			
			PreparedStatement ps=null;   //used to prepare the query
			ResultSet rs=null;
			
			try {
				ps=con.prepareStatement(strselect);
				ps.setString(1,cname);
				
				
				rs=ps.executeQuery();// it will return a single row so while loop will not be implemented
				rs.next();  //it will put the pointer on the row or dataset
//				int fees=rs.getint("course_fees");    to fetch fees in integer
				String fees=rs.getString("course_fee"); // fetching fees in string bco no calculation is to be done on it
				String dura=rs.getString("course_duration");
				
				
				txtfees.setText(fees);
				txtduration.setText(dura);
				
				
				
			}
			catch(SQLException s)
			{
				s.printStackTrace();
				
			}
			finally
			{
				
				try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
				}
				catch(SQLException s)
				{
					s.printStackTrace();
				}
				
				
				
			}  //finally closed
			}  //else closed
			
			
		}
		
		
		
		
		
		
		
	}
}
