package CMS.counselor;

import java.awt.EventQueue;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CMS.dbinfo.DBConnection;
import CMS.gui.LoginFrame;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Deletecourse extends JFrame implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtcourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deletecourse frame = new Deletecourse();
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
	public Deletecourse() {
		setTitle("deletecourse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 461);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COURSENAME");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel.setBounds(50, 64, 120, 17);
		contentPane.add(lblNewLabel);
		
		txtcourse = new JTextField();
		txtcourse.addKeyListener(this);
		txtcourse.setBounds(180, 61, 86, 20);
		contentPane.add(txtcourse);
		txtcourse.setColumns(10);
		
		JButton btnNewButton = new JButton("DELETE");
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 18));
		btnNewButton.setBounds(120, 123, 89, 23);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		
		//btnsubmit.setIcon(new ImageIcon(LoginFrame.class.getResource("/CMS/icons/icons8-enter-32.png")));
		
		
		ImageIcon ic=new ImageIcon(Deletecourse.class.getResource("/CMS/images/img3.jpg"));
		Image i2=ic.getImage().getScaledInstance(288, 191, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		JLabel lblimage = new JLabel("");
		lblimage.setIcon(ic1);
		lblimage.setBounds(248, 98, 288, 191);
		contentPane.add(lblimage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("button is being clicked");
		
		String cname= txtcourse.getText();
		if(cname.isEmpty())    //to check whether course is entered by user or not.
			JOptionPane.showMessageDialog(this, "please enter course name ");
		else
		{
		int choice=	JOptionPane.showConfirmDialog(this, "are you sure to delete "+cname+" course ?");
		System.out.println(choice);  //returns an int value for yes.no or cancel
		
		
		if(choice==0)
		{
			//data deletion code will be here as yes means 0
			Connection con=DBConnection.createConnection();
			PreparedStatement ps=null;
			
			String deleteQuery="delete from course_details where course_name=?";
			
			try {
				ps=con.prepareStatement(deleteQuery);
				ps.setString(1,cname);
				
				int status=ps.executeUpdate();
				
				if(status>0)
					JOptionPane.showMessageDialog(this, cname+" course deleted successfully");
				else {
					JOptionPane.showMessageDialog(this, cname+" course does not exist");
				}
				
			}
			
			catch(SQLException se) {
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
				
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				
			}
		}
	}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c =e.getKeyChar();
		
		if(e.getSource()==txtcourse)
		{
			if(!(Character.isAlphabetic(c)))
			{
				e.consume();
				JOptionPane.showMessageDialog(this, "only alphabets allowed");
				
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
