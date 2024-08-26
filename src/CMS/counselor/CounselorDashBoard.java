package CMS.counselor;
import java.awt.event.*;
import CMS.gui.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CMS.gui.AllCourses;
import CMS.gui.LoginFrame;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CounselorDashBoard extends JFrame implements ActionListener ,WindowListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CounselorDashBoard frame = new CounselorDashBoard();
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
	public CounselorDashBoard() {
		
		
		this.addWindowListener(this);  //register the frame with the window listener
		 
		setTitle("CounselorDashBoard\r\n");
		setBackground(new Color(255, 255, 255));
		setExtendedState(Frame.MAXIMIZED_BOTH);     //to get frame in full screen
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(202, 169, 164));
		setJMenuBar(menuBar);
		
		JMenu mnCourse = new JMenu("Course");
		mnCourse.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-edit.gif")));
		mnCourse.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnCourse);
		
		JMenuItem mi_addcourse = new JMenuItem("Add");
		mi_addcourse.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-add-32.png")));
		mi_addcourse.addActionListener(this);
		mnCourse.add(mi_addcourse);
		
		JMenuItem mi_updatecourse = new JMenuItem("update");
		mi_updatecourse.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-management-24.png")));
		mi_updatecourse.addActionListener(this);
		mnCourse.add(mi_updatecourse);
		
		JMenuItem mi_deletecourse = new JMenuItem("Delete");
		mi_deletecourse.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-delete-24.png")));
		mi_deletecourse.addActionListener(this);
		mnCourse.add(mi_deletecourse);
		
		JMenu mnstudent = new JMenu("Student");
		mnstudent.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-student-24.png")));
		menuBar.add(mnstudent);
		
		JMenuItem mi_addstudent = new JMenuItem("Add Student");
		mi_addstudent.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-add-32.png")));
		mi_addstudent.addActionListener(this);
		mnstudent.add(mi_addstudent);
		
		JMenuItem mi_updatestudent = new JMenuItem("Update Student");
		mi_updatestudent.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-management-24.png")));
		mi_updatestudent.addActionListener(this);
		mnstudent.add(mi_updatestudent);
		
		JMenuItem mi_deletestudent = new JMenuItem("Delete Student");
		mi_deletestudent.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-delete-24.png")));
		mi_deletestudent.addActionListener(this);
		mnstudent.add(mi_deletestudent);
		
		JMenu mnNewMenu = new JMenu("Reports");
		mnNewMenu.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-paint-bucket-with-label-24.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("All Students");
		mntmNewMenuItem.addActionListener(this);
		mntmNewMenuItem.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-staff-24.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("All Courses");
		mntmNewMenuItem_1.addActionListener(this);
		mntmNewMenuItem_1.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-books-32.png")));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Search Student");
		mntmNewMenuItem_2.addActionListener(this);
		mntmNewMenuItem_2.setIcon(new ImageIcon(CounselorDashBoard.class.getResource("/CMS/icons/icons8-appointment-scheduling-24.png")));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 64, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon ic=new ImageIcon(CounselorDashBoard.class.getResource("/CMS/images/imgcouns.jpg"));
		Image i2=ic.getImage().getScaledInstance(1365, 668, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		JLabel lblimage2 = new JLabel("");
		lblimage2.setIcon(ic1);
		lblimage2.setBounds(0, 0, 1365, 668);
		contentPane.add(lblimage2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String menu_text=e.getActionCommand();
		if(menu_text.equalsIgnoreCase("Add"))
		{
			Course course=new Course();
			course.setVisible(true);
		}
		
		if(menu_text.equalsIgnoreCase("Update"))
		{
		  updateCourse uc=new updateCourse();
		  uc.setVisible(true);
		}
		if(menu_text.equalsIgnoreCase("Delete"))
		{
		Deletecourse dc=new Deletecourse();
		  dc.setVisible(true);
		}
		
		
		if(menu_text.equalsIgnoreCase("All Students"))
		{
		AllStudents as=new AllStudents();
		  as.setVisible(true);
		}
		
		if(menu_text.equalsIgnoreCase("Search Student"))
		{
		searchstudent as=new searchstudent();
		  as.setVisible(true);
		}
		
		
		
		if(menu_text.equalsIgnoreCase("Add Student"))
		 {
			Student course=new Student();
			course.setVisible(true);
		 }
		if(menu_text.equalsIgnoreCase("Update Student"))
		{
			UpdateStudent up=new UpdateStudent();
			up.setVisible(true);
		}
		if(menu_text.equalsIgnoreCase("Delete Student"))
		{
			DeleteStudent ds=new DeleteStudent();
			ds.setVisible(true);
		}
		
		if(menu_text.equalsIgnoreCase("All Courses"))
		 {
			AllCourses cou=new AllCourses();
			cou.setVisible(true);
		 }
		
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		LoginFrame login1=new LoginFrame();
		login1.setVisible(true);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
