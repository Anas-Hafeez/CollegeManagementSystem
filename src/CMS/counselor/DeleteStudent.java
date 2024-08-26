package CMS.counselor;

import java.awt.EventQueue;
import java.sql.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CMS.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DeleteStudent extends JFrame implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStudent frame = new DeleteStudent();
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
	public DeleteStudent() {
		setTitle("deletestudent");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(121, 62, 109));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Roll no");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		lblNewLabel.setBounds(137, 56, 65, 23);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(this);
		textField.setBounds(219, 60, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
		btnNewButton.setBounds(165, 119, 89, 23);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
	  String roll=textField.getText();
	  
	  if(roll.isEmpty())
	  {
		  JOptionPane.showMessageDialog(this, "ROLL NO REQUIRED");
	  }
	  else
	  {
		  int choice=	JOptionPane.showConfirmDialog(this, "Are you sure to delete roll no "+roll+" ?");
			System.out.println(choice);  
		 
		  
			if(choice==0)
			{
				//data deletion code will be here as yes means 0
				Connection con=DBConnection.createConnection();
				PreparedStatement ps=null;
				
				String deleteQuery="delete from student_details where roll_number=?";
				
				try {
					ps=con.prepareStatement(deleteQuery);
					ps.setString(1,roll);
					
					int status=ps.executeUpdate();
					
					if(status>0)
						JOptionPane.showMessageDialog(this, "Roll no "+roll+" deleted successfully");
					else {
						JOptionPane.showMessageDialog(this,"Roll no "+roll+" does not exist" );
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
		char c=e.getKeyChar();
		
		if(e.getSource()==textField)
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
