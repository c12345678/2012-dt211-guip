package ie.dit.dt211.java;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class CalculatorGUI extends JFrame implements ActionListener {

	static final long serialVersionUID = 1L;
	JPanel contentPane;
	JTextField textField;
	StringBuilder displayText;
	Calculator calculator = new Calculator();
	boolean needsClear = false;
	
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
		setBounds(100, 100, 300, 450);
		
		contentPane = createContentPane();
		setContentPane(contentPane);
		contentPane.add(createKeypad(), BorderLayout.CENTER);
		contentPane.add(createDisplay(), BorderLayout.NORTH);
	}

	/**
	 * Set up the content pane
	 */
	private JPanel createContentPane() {
		JPanel pane = new JPanel();
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pane.setLayout(new BorderLayout(0, 0));
		return pane;
	}
	
	/**
	 * Set up the key pad
	 */
	private Container createKeypad() {
		JPanel keypad = new JPanel();
		keypad.setLayout(new GridLayout(5, 4, 5, 5));
		String[] labels = new String[] {
				"C", "(", ")", "/",
				"7", "8", "9", "x",
				"4", "5", "6", "-",
				"1", "2", "3", "+",
				"0", ".", "+/-",  "="
		};
		Font font = new Font("Dialog", Font.PLAIN, 20);
		for (String label: labels) {
			JButton button = new JButton(label);
			button.setFont(font);
			button.addActionListener(this);
			keypad.add(button);
		}
		return keypad;
	}

	/**
	 * Set up the numeric display
	 */
	private Container createDisplay() {
		JPanel display = new JPanel();
		textField = new JTextField();

		textField.setPreferredSize(new Dimension(290, 70));
		Font font = new Font("Dialog", Font.BOLD, 28);
		textField.setFont(font);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		clear();

		display.add(textField);
		return display;
	}


	/**
	 * Handle keypad input
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (needsClear ) {
			clear();
		}
		String pressed = ((JButton)ev.getSource()).getText();
		if (pressed.compareTo("C") == 0) {
			clear();
		} else if (pressed.compareTo("+/-") == 0) {
			if (calculator.append("~")) {
				display("-");
			}
		} else if (pressed.compareTo("=") == 0) {
			try {
				calculator.finish();
				calculate();
			} catch (IllegalStateException e) {
				// Incomplete expression that would not compute
			}
		} else if ("0123456789+-x/.".contains(pressed)){
			if (calculator.append(pressed)) {
				display(pressed);
			}
		}
	}
	
	/**
	 * Build the display output
	 */
	private void display(String s) {
		boolean isOperator = Calculator.isArithmeticOperator(s);
		if (isOperator) {
			displayText.append(" ");
		}
		displayText.append(s);
		if (isOperator) {
			displayText.append(" ");
		}
		textField.setText(displayText.toString());
	}

	private void clear() {
		textField.setText("0");
		displayText = new StringBuilder();
		needsClear = false;
	}
	
	private void calculate() {
		textField.setText(String.format("%#.2f", calculator.evaluate()));
		calculator.reset();
		needsClear = true;
	}
	
}
