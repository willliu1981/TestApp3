package swingtest;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnimationTest extends JFrame {
	Image[] imgs;
	ParseImage pi;
	JPanel contentPanel;

	public static void main(String[] a) {
		AnimationTest an = new AnimationTest();
		an.setVisible(true);

	}

	public AnimationTest() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 200, 800, 600);

		this.contentPanel = new JPanel();
		this.contentPanel.setLayout(null);
		this.add(contentPanel);

		String fname = "src\\data\\horsewalk.jpg";
		Toolkit kit = this.getToolkit();
		Image img = kit.getImage(fname);
		AnimalPanel panel = new AnimalPanel(img);
		panel.setBounds(50, 50, 700, 400);
		this.contentPanel.add(panel);
	}

}

class ParseImage {
	Component compt;
	Image img;
	int wlen, hlen, frameLength;

	public ParseImage(Image img,Component compt) {
		this(img,0,0,0,compt);
	}

	public ParseImage(Image img, int w, int h, int length,Component compt) {
		this.img = img;
		this.wlen = w;
		this.hlen = h;
		this.frameLength = length;
		this.compt=compt;
	}

	public void set(int w, int h, int length) {
		this.wlen = w;
		this.hlen = h;
		this.frameLength = length;
	}

	public Image[] parse() {
		Image[] anims = new Image[this.frameLength];
		
		waitLoadingImage();
		
		int w=this.img.getWidth(null);
		int h=this.img.getHeight(null);
		int tinyw=w/this.wlen;
		int tinyy=h/this.hlen;
		
		BufferedImage bi=(BufferedImage)img;
		for(int i=0;i<this.frameLength;i++) {
			Image image;
			
			
		}
		
		System.out.format("xxx w=%d , h=%d , img=",w,h);
		return anims;
	}
	
	private void waitLoadingImage() {
		MediaTracker tracker=new MediaTracker(compt);
		tracker.addImage(img, 0);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class AnimalPanel extends JPanel {

	Image[] anims;

	public AnimalPanel() {

	}

	public AnimalPanel(Image img) {
		anims = new ParseImage(img,this).parse();
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(128, 255, 128));
		Insets ins = this.getInsets();
		int x = ins.left;
		int y = ins.top;
		int w = this.getWidth() - (ins.left + ins.right);
		int h = this.getHeight() - (ins.top + ins.bottom);
		
		g.fill3DRect(x, y, w, h, false);
		
	}

}
