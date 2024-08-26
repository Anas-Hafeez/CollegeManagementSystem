package CMS.counselor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CMS.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class Student extends JFrame implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel txtaddress;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
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
	//code to populate the combo box
	
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
	
	
	
	private JTextArea textArea;
	private JComboBox<String> cmbcourse;
	private JTextField txt_course;
	public Student() {
		setTitle("Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		txtaddress = new JPanel();
		txtaddress.setBackground(new Color(82, 163, 67));
		txtaddress.setForeground(new Color(83, 179, 66));
		txtaddress.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setLocationRelativeTo(null);
		setContentPane(txtaddress);
		txtaddress.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(64, 0, 64));
		lblNewLabel.setBounds(92, 36, 74, 29);
		txtaddress.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-MAIL");
		lblNewLabel_1.setForeground(new Color(64, 0, 64));
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_1.setBounds(92, 76, 58, 35);
		txtaddress.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PHONE");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_2.setForeground(new Color(64, 0, 64));
		lblNewLabel_2.setBounds(92, 122, 58, 29);
		txtaddress.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("COURSE NAME");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_3.setForeground(new Color(64, 0, 64));
		lblNewLabel_3.setBounds(35, 162, 115, 24);
		txtaddress.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ADDRESS");
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_4.setForeground(new Color(64, 0, 64));
		lblNewLabel_4.setBounds(76, 197, 74, 24);
		txtaddress.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		btnNewButton.addActionListener(this);
		btnNewButton.setForeground(new Color(64, 0, 64));
		btnNewButton.setBounds(104, 232, 136, 23);
		txtaddress.add(btnNewButton);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.addActionListener(this);
		txtname.setBounds(168, 43, 86, 20);
		txtaddress.add(txtname);
		txtname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(168, 86, 86, 20);
		txtaddress.add(txtemail);
		txtemail.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setBounds(168, 129, 86, 20);
		txtaddress.add(txtphone);
		txtphone.setColumns(10);
		
		 cmbcourse = new JComboBox();
		 cmbcourse.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		 cmbcourse.setModel(new DefaultComboBoxModel(new String[] {"Select course"}));
		cmbcourse.setToolTipText("Courses");
		cmbcourse.setBounds(168, 166, 129, 22);
		fillcombo();  //method calling for filling up the combo box
		txtaddress.add(cmbcourse);
		
		 textArea = new JTextArea();
		textArea.setBounds(168, 200, 86, 22);
		txtaddress.add(textArea);
		
		txt_course = new JTextField();
		txt_course.addKeyListener(this);
		txt_course.setBounds(324, 167, 86, 20);
		txt_course.setVisible(false);
		txtaddress.add(txt_course);
		txt_course.setColumns(10);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String caption_text=e.getActionCommand();
		String name=txtname.getText();
		String email=txtemail.getText();
		String phoneno=txtphone.getText();
		String adress=textArea.getText();
		//String course_name=txt_course.getText();
		String coursename=(String)cmbcourse.getSelectedItem();    //to fetch the selected item of the combo box
		
		
		if(name.isEmpty()||email.isEmpty()||phoneno.isEmpty()||adress.isEmpty()||coursename.equalsIgnoreCase("select course"))
		{
			
			JOptionPane.showMessageDialog(this, "DATA REQUIRED");
		}
		
		else
		{
			/*System.out.println("student name is "+name);
			System.out.println("student email is "+email);
			System.out.println("student phone no is "+phoneno);
			System.out.println("student address is "+adress);
			System.out.println("course name is "+course_name);*/
			
			Connection con=DBConnection.createConnection();  //static method is being called with its class name
                                                               //createconnection is a static method of dbconnection class
            String insertQuery="insert into student_details(name, email, phone, course_name, address)values(?,?,?,?,?)";


            PreparedStatement ps=null;
            try {
				ps=con.prepareStatement(insertQuery);
				//passes the query to dbms-->parser parse the query and after parsing the query
				//store the compiled query into  buffer and assign the address of that  buffer to ps
				ps.setString(1,name);
				ps.setString(2,email);
				ps.setString(3,phoneno);
				ps.setString(4,coursename);
				ps.setString(5,adress);
				
				
				int status=ps.executeUpdate(); //it is used to insert data in a database table
				if(status>0)
				System.out.println("student added succesfully");
				JOptionPane.showMessageDialog(this, "student added successfully");
				
				txtname.setText("");     //it is done to erase the data after adding it successfully to 
				txtemail.setText("");      // the database so that the new data can be entered
				txtphone.setText("");
				textArea.setText("");
				txt_course.setText("");
				
			}
            catch(SQLException se)
			{
				se.printStackTrace();
				int code=se.getErrorCode();   //it gives the code of error in int value
				                              //it is used to trace the type of error
				System.out.println("code is "+code);
				if(code==1406)    //checks for the foreign key exception i,e it check for invalid course that does not exist
				    JOptionPane.showMessageDialog(this, "invalid phone no","INVALID PHONE NO",JOptionPane.ERROR_MESSAGE);
				     
				
				if(code==1062)     //to check if the email or phone no is duplicate or not
					JOptionPane.showMessageDialog(this, "email or phone no already exist","DUPLICATE VALUES",JOptionPane.ERROR_MESSAGE);
			     
					
			}
             finally {     //it is been used here to close the connection
				
				try {
					if(ps!=null)
						ps.close();
					
					if(con!=null)
						ps.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c=e.getKeyChar();
		
		if(e.getSource()==txtname)
		{
			if(Character.isAlphabetic(c))
			{
				//do nothing
			}
			
			if(!(Character.isAlphabetic(c)))
			{
				e.consume();
				JOptionPane.showMessageDialog(this, "only alphabets allowed");
				
				
			}
			
			
		}
		if(e.getSource()==txtphone)
		{
			if(Character.isAlphabetic(c))
			{
				e.consume();
				JOptionPane.showMessageDialog(this, "only digits allowed");
			}
			
			
			
		}

		
		
		
		
		
		//		if(e.getSource()==txt_course)
//		{
//			if(Character.isAlphabetic(c))
//			{
//				//do nothing
//			}
//			
//			if(!(Character.isAlphabetic(c)))
//			{
//				e.consume();
//				JOptionPane.showMessageDialog(this, "only alphabets allowed");
//				
//				
//			}
//			
//			
//		}
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

			
			
			
			
			
			
			
			
			
		
		
		
	

