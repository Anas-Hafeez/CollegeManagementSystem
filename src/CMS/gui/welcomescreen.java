package CMS.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class welcomescreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {    //eventdiaspatcher thread concept gets added in jdk1.5 to reduce the load on main thread
			public void run() {
				try {
					welcomescreen frame = new welcomescreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println("i am main");      //main is still empty can be ulised
	}

	/**
	 * Create the frame.
	 */
	
	
	public void showloginframe()
	{
		//let us assume the anme of the anonymus class is A which implements runnable
     Thread t=new Thread(new Runnable()//  object of that class which implements runnable interface(not the object of runnable imterface ) 
                                           //bcoz runnable i nterface object cannot be made 
    		 {//inner class body
		
		
		public void run() //overriding interface(runnable method)
		{
			try
			{                                                                 //this is whole inner class concept
				Thread.sleep(4000);
				LoginFrame lf=new  LoginFrame();
				lf.setVisible(true);
				welcomescreen.this.dispose();     //referring outer class object inside inner class ,here welcomescreen is outer class
			}
			catch(InterruptedException i)
			{
				i.printStackTrace();
			}
		
			
		}
	}//inner class closed
     );  //thread class constructor closed
		
	t.start();  //starting of thread(making the thread in runnable state)	
		
	}
	public welcomescreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 605, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setForeground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Automation System WELCOMES You!!");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 22));
		lblNewLabel.setBounds(110, 41, 456, 42);
		contentPane.add(lblNewLabel);
		
		showloginframe();
	}
}
