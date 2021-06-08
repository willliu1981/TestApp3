package digitgame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class DigitGame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_input;
	private JTextArea textArea_output;
	private String question;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigitGame frame = new DigitGame();
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
	public DigitGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		textField_input = new JTextField();
		textField_input.setFont(new Font("DialogInput", Font.PLAIN, 18));
		textField_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					if (textField_input.getText().equals("restart")) {
						setText("遊戲重新開始,請輸入數字");
						question = String.format("%04d", (int) (Math.random() * 10000));
						System.out.println("debug ** " + question);
					} else {
						int guess = 0;
						try {
							guess = Integer.valueOf(textField_input.getText());
							if (guess < 0 || guess > 9999) {
								throw new NumberFormatException();
							}
						} catch (NumberFormatException ex) {
							setText("不是正確的數字");
						}

						int a = 0, b = 0;
						StringBuilder sb = new StringBuilder(String.format("%04d", guess));
						for (int i = 0; i < 4; i++) {
							char ch = question.charAt(i);
							if (ch == sb.charAt(i)) {
								sb = sb.replace(i, i + 1, "-");
								a++;
							} else {
								for (int j = 0; j < 4; j++) {
									if (ch == sb.charAt(j)) {
										b++;
										sb = sb.replace(j, j + 1, "-");
									}
								}
							}
						}

						if (a == 4) {
							setText("恭喜答對了");
						} else {
							setText(String.format("%d A %d B", a, b));
						}
						System.out.println("debug ** " + String.format("a=%d , b=%d ,sb=%s", a, b, sb));
					}
				}
			}
		});
		textField_input.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(textField_input, BorderLayout.NORTH);
		textField_input.setColumns(10);

		textArea_output = new JTextArea();
		textArea_output.setFont(new Font("DialogInput", Font.PLAIN, 18));
		textArea_output.setDisabledTextColor(Color.BLACK);
		textArea_output.setEditable(false);
		textArea_output.setLineWrap(true);
		textArea_output.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.textArea_output);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	private void setText(String msg) {
		textArea_output.setText(textField_input.getText() + " : " + msg + "\n" + textArea_output.getText());
		textField_input.setText("");
	}

}
