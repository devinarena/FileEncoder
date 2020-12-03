package edu.fgcu.djarena3577.fileencoder;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * A simple program to encode and decode files securely, using base64 encoding.
 * 
 * @author Devin
 * @since 4/27/2020
 * @version 1.0
 */
public class FileEncoder {

	private JFrame frame;
	private JTextField field;
	private JButton encode, decode;
	private int border = 20;

	public FileEncoder() {
		frame = new JFrame("FileEncoder by Devin Arena");

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initWindow();

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Initializes the components of the window.
	 */
	private void initWindow() {
		JPanel p = new JPanel();

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		field = new JTextField("C:/FilePath");
		p.add(field);

		JPanel buttons = new JPanel();
		encode = new JButton("Encode");
		encode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = field.getText();
				try {
					Encoder.encodeFile(new File(path));
					showDialog("Success!", "The file was encoded, the extention has been converted to .fe.");
					field.setText(field.getText() + ".fe");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		decode = new JButton("Decode");
		decode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = field.getText();
				try {
					Encoder.decodeFile(new File(path));
					showDialog("Success!", "The file was decoded, the extention has been converted back (if applicable).");
					field.setText(field.getText().endsWith(".fe") ? field.getText().substring(0, field.getText().length() - 3) : field.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttons.add(encode);
		buttons.add(decode);

		p.add(buttons);

		p.setBorder(new EmptyBorder(new Insets(border, border, border, border)));

		frame.add(p);
	}

	/**
	 * Helper method to display an info dialog.
	 * 
	 * @param title   of the dialog
	 * @param message content of the dialog
	 */
	private void showDialog(String title, String message) {
		JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		new FileEncoder();
	}

}
