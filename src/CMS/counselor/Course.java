package CMS.counselor;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CMS.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Frame;

public class Course extends JFrame implements ActionListener,KeyListener {
//key listener is responsible for event generating by key
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtfees;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Course frame = new Course();
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
	public Course() {
		setResizable(false);
		setTitle("Course.java");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 159, 148));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COURSE NAME\r\n");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(75, 33, 121, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("COURSE FEES");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_1.setBounds(85, 70, 111, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("COURSE DURATION\r\n");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_2.setBounds(45, 107, 151, 26);
		contentPane.add(lblNewLabel_2);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);   //registering the listener with the text fields
		txtname.setBounds(206, 39, 86, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtfees = new JTextField();
		txtfees.addKeyListener(this);
		txtfees.setBounds(206, 76, 86, 20);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		txtduration = new JTextField();
		txtduration.setBounds(206, 113, 86, 20);
		contentPane.add(txtduration);
		txtduration.setColumns(10);
		
		JButton btnNewButton = new JButton("submit");
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		btnNewButton.setBounds(148, 166, 89, 23);
		contentPane.add(btnNewButton);
		
		
		

		ImageIcon ic=new ImageIcon(Course.class.getResource("/CMS/images/img6.jpg"));
		Image i2=ic.getImage().getScaledInstance(441, 343, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		
		JLabel lblbgimage = new JLabel("");
		lblbgimage.setIcon(ic1);
		lblbgimage.setBounds(0, 0, 441, 343);
		contentPane.add(lblbgimage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String caption_text=e.getActionCommand();
		String name=txtname.getText();
		String fees=txtfees.getText();
		String duration=txtduration.getText();
		
		
		if(name.isEmpty()||fees.isEmpty()||duration.isEmpty())
		{
			
			JOptionPane.showMessageDialog(this, "DATA REQUIRED");
		}
		
		else
		{
			/*System.out.println("course name is "+name);
			System.out.println("course fees is "+fees);
			System.out.println("course duration is "+duration);*/
			
			Connection con=DBConnection.createConnection();  //static method is being called with its class name
			                                                 //createconnection is a static method of dbconnection class
			String insertQuery="insert into course_details(course_name, course_fee, course_duration)values(?,?,?)";
			
			
			PreparedStatement ps=null;      //insert the data in database
			try {
				ps=con.prepareStatement(insertQuery);
				//passes the query to dbms-->parser parse the query and after parsing the query
				//store the compiled query into  buffer and assign the address of that  buffer to ps
				ps.setString(1,name);
				ps.setInt(2, Integer.parseInt(fees));
				ps.setString(3, duration);
				
				
				int status=ps.executeUpdate(); //it is used to insert data in a database table
				if(status>0)
				System.out.println("course added succesfully");
				JOptionPane.showMessageDialog(this, "course added successfully");
				
				txtname.setText("");     //it is done to erase the data after adding it successfully to 
				txtfees.setText("");      // the database so that the new data can be entered
				txtduration.setText("");
				
			}
			catch(SQLException se)
			{
				//se.printStackTrace();
				JOptionPane.showMessageDialog(this, name+" already exist","DUPLICATE VALUE",JOptionPane.ERROR_MESSAGE);
				      //it is used when duplicate name of course is been used
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
//		System.out.println("key typed");
//		int code=e.getKeyCode();   //gives the ascii code of the key pressed
		char c=e.getKeyChar();   //gives the ascii code of the character pressed
//		System.out.println("char is "+code1);
		
		if(e.getSource()==txtname)    //to find which text field is used
		{
//			if(Character.isAlphabetic(c))
//			{
//				//do nothing
//			}
			if(!(Character.isAlphabetic(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_SPACE))
			{
				e.consume();    //it will consume the typed character
				JOptionPane.showMessageDialog(this, "only alphabets allowed");
			}
			
			
		}
		
		if(e.getSource()==txtfees)   //get source will return the source that is generating the event
		{
			if((Character.isAlphabetic(c)|| c==KeyEvent.VK_SPACE))
			{
				e.consume();    //it will consume the typed character
				JOptionPane.showMessageDialog(this, "only digits allowed");
			}
			
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("key pressed");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
