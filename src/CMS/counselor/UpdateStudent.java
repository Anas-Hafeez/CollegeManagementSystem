package CMS.counselor;

import java.awt.EventQueue;
import java.sql.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CMS.dbinfo.DBConnection;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class UpdateStudent extends JFrame implements ActionListener,ItemListener,KeyListener 
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtname1;
	private JTextField txtemail1;
	private JTextField txtphone1;
	private JComboBox comboBox ;
	JTextArea txtaddress1;
	
	
	public  void fillcombo()
	{
		Connection con=DBConnection.createConnection();
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	String selectQuery="select * from student_details ";//to retrieve the data from the table
	try {
		ps=con.prepareStatement(selectQuery);
		rs=  ps.executeQuery();   //used only in select query
		
		
		   while( rs.next()==true)  //checks if data is present on the row
		   {
			   String roll=rs.getString("roll_number");  //it is used to fetch the value from the specified coloumn
		       
			   comboBox.addItem(roll);    //to add the item in  the combo box 
			   
			   
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
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent();
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
	public UpdateStudent() {
		setTitle("updatestudent");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateStudent.class.getResource("/CMS/images/img3.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 188, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setLocationRelativeTo(null);      //to place the frame of update course on the center of the screen
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    comboBox = new JComboBox();
	    comboBox.addItemListener(this);
	    comboBox.setModel(new DefaultComboBoxModel(new String[] {"Roll no"}));
	    comboBox.addItemListener(this);  //binding of item listener
		comboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		comboBox.setBounds(162, 25, 89, 22);
		fillcombo();
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel.setBounds(125, 75, 82, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-MAIL");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_1.setBounds(125, 100, 82, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PHONE");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_2.setBounds(125, 139, 82, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ADDRESS");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_3.setBounds(125, 172, 82, 22);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(162, 216, 89, 23);
		contentPane.add(btnNewButton);
		
		txtname1 = new JTextField();
		txtname1.addKeyListener(this);
		txtname1.setBounds(218, 76, 86, 20);
		contentPane.add(txtname1);
		txtname1.setColumns(10);
		
		txtemail1 = new JTextField();
		txtemail1.setBounds(218, 108, 86, 20);
		contentPane.add(txtemail1);
		txtemail1.setColumns(10);
		
		txtphone1 = new JTextField();
		txtphone1.addKeyListener(this);
		txtphone1.setBounds(218, 140, 86, 20);
		contentPane.add(txtphone1);
		txtphone1.setColumns(10);
		
		 txtaddress1 = new JTextArea();
		txtaddress1.setBounds(217, 171, 87, 22);
		contentPane.add(txtaddress1);
		
		
		
		ImageIcon ic=new ImageIcon(UpdateStudent.class.getResource("/CMS/images/img5.jpg"));
		Image i2=ic.getImage().getScaledInstance(434, 261, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(ic1);
		lblNewLabel_4.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel_4);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String name1=txtname1.getText();
		String email1=txtemail1.getText();
		//int phone1=Integer.parseInt(txtphone1.getText());
		String phone1=txtphone1.getText();
		String add1=txtaddress1.getText();
		String roll=(String)comboBox.getSelectedItem();
		
		Connection con=DBConnection.createConnection();
		PreparedStatement ps=null;
		String strupdate="update student_details set name=?,email=?,phone=?,address=? where roll_number=?";
		
		
		try
		{
			
			
			ps=con.prepareStatement(strupdate);
			ps.setString(1, name1);
			ps.setString(2, email1);
			ps.setString(3, phone1);
			ps.setString(4, add1);
			ps.setString(5, roll);
			
			int status=ps.executeUpdate();
			if(status>0)
			{
				JOptionPane.showMessageDialog(this, "information updated successfully");
			}
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try {
			if(ps!=null)
				ps.close();
			if(con!=null)
				con.close();
			}	
			catch(SQLException s)
			{
				s.printStackTrace();
			}
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int state=e.getStateChange();  //give the current and previous state of cmbbox ie 1 or 2
		String roll=(String)e.getItem();  //or cmbcourse1.getSelected item()
		 
//		System.out.println(state+" "+cname);
		
		if(state==1)  //current state
		{
			if(roll=="Roll no")
			{
				JOptionPane.showMessageDialog(this, "selection of roll no is mandatory");
			}
//			
			else
			{
				int rollnum=Integer.parseInt(roll);	
			Connection con=DBConnection.createConnection();
			String strselect="select*from student_details where roll_number=?";
			
			
			PreparedStatement ps=null;   //used to prepare the query
			ResultSet rs=null;
			
			try {
				ps=con.prepareStatement(strselect);
				
				ps.setInt(1, rollnum);
				
				
				rs=ps.executeQuery();// it will return a single row so while loop will not be implemented
				rs.next();  //it will put the pointer on the row or dataset
//				int fees=rs.getint("course_fees");    to fetch fees in integer
				String name3=rs.getString("name"); // fetching fees in string bco no calculation is to be done on it
				String email3=rs.getString("email");
				String phone2=rs.getString("phone");
				String address2=rs.getString("address");
				
				
				
				
				txtname1.setText(name3);
				txtemail1.setText(email3);
				txtphone1.setText(phone2);
				txtaddress1.setText(address2);
				
				
				
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




	@Override
	public void keyTyped(KeyEvent e) {
		char c=e.getKeyChar(); 
		if(e.getSource()==txtname1)
		{
			if(!(Character.isAlphabetic(c)|| c==KeyEvent.VK_BACK_SPACE|| c==KeyEvent.VK_SPACE|| c==KeyEvent.VK_DELETE))
			{
				e.consume();
				JOptionPane.showMessageDialog(this, "only alphabets allowed");
			}
			
		}
		if(e.getSource()==txtphone1)
		{
			if(Character.isAlphabetic(c))
			{
				e.consume();
				JOptionPane.showMessageDialog(this, "only digits allowed");
			}
			
		}
		
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




	

