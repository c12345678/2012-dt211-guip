package ie.dit.dt211.java;


import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Front end for simple infix desktop calculator
 */
public class CalculatorGUI extends JFrame implements ActionListener {

	static final long serialVersionUID = 1L;
	JPanel contentPane;
	Calculator calculator = new Calculator();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorGUI frame = new CalculatorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the main application window.
	 */
	public CalculatorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * You are not required to stick the the following dimensions
		 * Feel free to choose your own
		 */
		setBounds(100, 100, 300, 450);
		
		contentPane = createContentPane();
		setContentPane(contentPane);
		
		/*
		 * TODO Add containers for the keypad and number display. See below.
		 */
	}

	/**
	 * Set up the content pane
	 */
	private JPanel createContentPane() {
		JPanel pane = new JPanel();
		
		/*
		 * TODO Define a layout for the content pane
		 */
		return pane;
	}
	
	/**
	 * Set up the key pad
	 */
	private Container createKeypad() {
		JPanel keypad = new JPanel();
		
		/*
		 * TODO
		 * 		Define a layout for this container
		 * 		Create buttons for the keypad
		 * 		Register an action listener for each button
		 * 		Choose appropriate button font/size
		 */

		return keypad;
	}

	/**
	 * Set up the numeric display
	 */
	private Container createDisplay() {
		JPanel display = new JPanel();
		
		/*
		 * TODO
		 * 		Define a layout for this container
		 * 		Create the number display
		 * 		Choose appropriate display font/size
		 */

		return display;
	}


	/**
	 * Handle keypad input
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		
		/*
		 * TODO Handle button keypad button presses and perform
		 * appropriate calculator actions such as display numbers,
		 * clear the display or evaluate the entered expression
		 */

	}
	
}
