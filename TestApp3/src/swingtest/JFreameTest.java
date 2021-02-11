package swingtest;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;

public class JFreameTest extends JFrame {

	public static void main(String[] args) {
		JFreameTest frame = new JFreameTest();
	}

	public JFreameTest() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(100, 100, 300, 200);
		this.repaint();

		Toolkit kit = this.getToolkit();
		Image img = kit.getImage("src\\data\\p0.jpg");
		System.out.println("before: " + img.getWidth(this) );
		
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img, 0);
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		BufferedImage bi=new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);

		bi.createGraphics().drawImage(img,0,0,img.getWidth(null)/2,img.getHeight(null),null);
		

		System.out.println("after: " + img.getWidth(this) );
		System.out.println("bi: " + bi.getWidth() );
	}

}
