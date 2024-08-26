package CMS.counselor;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CMS.dbinfo.DBConnection;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class searchstudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchstudent frame = new searchstudent();
					frame.setVisible(true);
					frame.inputrollnumber();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	String rollno;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphno;
	private JTextField txtcourse;
	private JTextArea txtadd; 
	public void inputrollnumber()
	{
		rollno=JOptionPane.showInputDialog(this,"enter roll no to secrch other details ");
		System.out.println(rollno);
		
		Connection con=DBConnection.createConnection();
		String strselect="select*from Student_details where roll_number=?";
		PreparedStatement ps=null;   //used to prepare the query
		ResultSet rs=null;
		
		try {
			ps=con.prepareStatement(strselect);
			
			ps.setString(1, rollno);
			
			
			rs=ps.executeQuery();// it will return a single row so while loop will not be implemented
			if(rs.next())
			{
			
			String name3=rs.getString("name"); // fetching fees in string bco no calculation is to be done on it
			String email3=rs.getString("email");
			String phone2=rs.getString("phone");
			String course2=rs.getString("course_name");
			String address2=rs.getString("address");
			
			txtname.setText(name3);
			txtemail.setText(email3);            
			txtphno.setText(phone2);
			txtcourse.setText(course2);
			txtadd.setText(address2);
			}
			else {
				JOptionPane.showMessageDialog(this, "no such roll no exist");
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		
	}
	public searchstudent() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtname = new JTextField();
		txtname.setBounds(287, 57, 86, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(287, 109, 86, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		txtphno = new JTextField();
		txtphno.setBounds(287, 166, 86, 20);
		contentPane.add(txtphno);
		txtphno.setColumns(10);
		
		txtcourse = new JTextField();
		txtcourse.setBounds(287, 221, 86, 20);
		contentPane.add(txtcourse);
		txtcourse.setColumns(10);
		
	    txtadd = new JTextArea();
		txtadd.setBounds(287, 275, 86, 22);
		contentPane.add(txtadd);
		
		JLabel lblNewLabel = new JLabel("name");
		lblNewLabel.setBounds(178, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("email");
		lblNewLabel_1.setBounds(178, 112, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("phone no");
		lblNewLabel_2.setBounds(178, 169, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("course name");
		lblNewLabel_3.setBounds(178, 224, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("address");
		lblNewLabel_4.setBounds(178, 280, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		inputrollnumber();
		
		
	}
}
