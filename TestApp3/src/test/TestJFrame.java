package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import practice.S;

public class TestJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestJFrame frame = new TestJFrame();
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
	public TestJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}

	@Override
	public void paint(Graphics g) {

		File f = new File("src\\data\\p0a.png");

		
		try {
			//g.setColor(Color.cyan);
			//g.fillRect(0,0,this.getWidth(),this.getHeight());
			BufferedImage img = ImageIO.read(f);
			BufferedImage bi = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);

			Graphics2D g2d = bi.createGraphics();
			//*
			bi = g2d.getDeviceConfiguration().createCompatibleImage(img.getWidth(), img.getHeight(),
					Transparency.TRANSLUCENT);
			g2d = bi.createGraphics();
			g2d.drawImage(img, 0, 0, null);
			///
			g2d.setColor(Color.black);
			g2d.drawString("xxxxx", 50,50);
			
			ImageIO.write(bi, "png", new File("src\\data\\ppp.png"));
			g.drawImage(bi, this.getInsets().left, this.getInsets().top, null);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
