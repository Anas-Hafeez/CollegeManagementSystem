package CMS.gui;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import CMS.admin.*;
import CMS.counselor.CounselorDashBoard;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpass;
	private final ButtonGroup roles = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	private JRadioButton rdadmin,rdcounselor;    //instance variable of Jradiobutton 
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/CMS/images/img4.jpg")));
		setTitle("LoginFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 406);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		setLocationRelativeTo(null);    //to place the frame in center of the screen
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserId");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel.setBounds(71, 50, 82, 48);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel_1.setBounds(45, 95, 82, 48);
		contentPane.add(lblNewLabel_1);
		
		txtid = new JTextField();
		txtid.setBackground(new Color(255, 255, 255));
		txtid.setBounds(175, 50, 102, 37);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(175, 112, 102, 20);
		contentPane.add(txtpass);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setIcon(new ImageIcon(LoginFrame.class.getResource("/CMS/icons/icons8-enter-32.png")));
	    btnsubmit.addActionListener(this);
		btnsubmit.setForeground(new Color(255, 0, 0));
		btnsubmit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		btnsubmit.setBounds(94, 207, 165, 29);
		contentPane.add(btnsubmit);
		
		 rdadmin = new JRadioButton("Admin");
		roles.add(rdadmin);
		rdadmin.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		rdadmin.setBounds(45, 154, 82, 23);
		contentPane.add(rdadmin);
		
		 rdcounselor = new JRadioButton("Counselor");
		roles.add(rdcounselor);
		rdcounselor.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		rdcounselor.setBounds(175, 154, 109, 23);
		contentPane.add(rdcounselor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("button clicked");
		
		String userid=txtid.getText(); //fetching value from text box
		 char[] pass=txtpass.getPassword();   //fetching data in the form of array of character type 
		 
		 
		 String userpass=String.valueOf(pass);
		 
		 String userrole="";
		 if( rdadmin.isSelected())    //to check whether admin button is clivked or not
		 {
			userrole=rdadmin.getText(); 
		 }
		 if(rdcounselor.isSelected())
		 {
			 userrole=rdcounselor.getText();
		 }
		 
		 //mandatory fields validation check
		 if(userid.isEmpty()||userpass.isEmpty()||userrole.isEmpty())
		 {
			JOptionPane.showMessageDialog(this, "Data Required"); 
			 
		 }
		 else 
		 {
			 if(userid.equalsIgnoreCase("anas")&& userpass.equals("lucknow")&& userrole.equals("Admin"))
			 {
				 AdminDashBoard ad=new AdminDashBoard();
				 ad.setVisible(true);
				 this.dispose();    //login frame is getting closed.
			 }
			 else if(userid.equalsIgnoreCase("precursor")&& userpass.equals("lucknow")&& userrole.equals("Counselor"))
			 {
				 CounselorDashBoard cd=new CounselorDashBoard();
				 cd.setVisible(true);
			 }
			 
			 else
			 {
				 
				 JOptionPane.showMessageDialog(this, "invalid credentials", "Login error", JOptionPane.ERROR_MESSAGE);
			 }
		 }
			 
		
	}
}
