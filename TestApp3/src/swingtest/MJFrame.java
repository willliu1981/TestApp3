package swingtest;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class MJFrame extends JFrame {
	private JPanel contentPane;
	private JLabel lblNewLabel = new JLabel("text");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MJFrame frame = new MJFrame();
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
	public MJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 274);
		

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		//contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		UserPanel panel_1 = new UserPanel(lblNewLabel);
		panel_1.setBounds(33, 10, 273, 174);
		
		contentPane.add(panel_1);

		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(127, 202, 106, 23);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);

	}

	/*
	@Override
	public void paint(Graphics g) {
		// *
		Insets ins = this.getInsets();
		int w = this.getWidth() - (ins.left + ins.right);
		int h = this.getHeight() - (ins.top + ins.bottom);
		System.out.printf("l=%s r=%s t=%s b=%s", ins.left, ins.right, ins.top, ins.bottom);
		g.setColor(Color.yellow);
		g.fillRect(ins.left, ins.top, w, h);
		g.setColor(Color.red);
		g.drawRect(ins.left, ins.top, w - 1, h - 1);
		///
		// super.paint(g);
	}
	*///

}
