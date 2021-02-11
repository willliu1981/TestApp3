package swingtest;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationTest extends JFrame {
	Image[] imgs;
	ParseImage pi;
	JPanel contentPanel;
	AnimationPanel animPanel;

	public static void main(String[] a) {
		AnimationTest an = new AnimationTest();
		an.setVisible(true);

		Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				an.animPanel.move();
				an.repaint();
				// System.out.println("xx: "+an.animPanel.getTop());
			}
		});

		timer.start();
	}

	public AnimationTest() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 200, 800, 600);

		this.contentPanel = new JPanel();
		this.contentPanel.setLayout(null);
		this.add(contentPanel);

		String fname = "src\\data\\horserun.png";
		Toolkit kit = this.getToolkit();
		Image img = kit.getImage(fname);
		ParseImage pa = new ParseImage(img, this.contentPanel);
		pa.setFrameInfo(4,3, 12);
		animPanel = new AnimationPanel();
		animPanel.setParse(pa);
		animPanel.parse();
		animPanel.setBounds(50, 50, 700, 400);
		this.contentPanel.add(animPanel);
	}

}

class ParseImage {
	public static Image iii;
	Component compt;
	Image img;
	int wlen, hlen, frameLength;

	public ParseImage(Image img, Component compt) {
		this(img, 1, 1, 1, compt);
	}

	public ParseImage(Image img, int w, int h, int length, Component compt) {
		this.img = img;
		this.wlen = w;
		this.hlen = h;
		this.frameLength = length;
		this.compt = compt;
	}

	public void setFrameInfo(int w, int h, int length) {
		this.wlen = w;
		this.hlen = h;
		this.frameLength = length;
	}

	public Image[] parse() {
		BufferedImage[] imgs = new BufferedImage[this.frameLength];

		waitLoadingImage();

		int w = this.img.getWidth(null);
		int h = this.img.getHeight(null);
		int chunkw = w / this.wlen;
		int chunkh = h / this.hlen;

		outfor: for (int y = 0, idx = 0; y < this.hlen; y++) {
			for (int x = 0; x < this.wlen; x++, idx++) {
				if (idx >= this.frameLength) {
					break outfor;
				}
				// BufferedImage img = new BufferedImage(chunkw, chunkh, imgSource.getType());
				BufferedImage bi = new BufferedImage(chunkw, chunkh, BufferedImage.TYPE_INT_ARGB);
				imgs[idx] = bi;
				this.iii = bi;
				Graphics2D gr = imgs[idx].createGraphics();
				gr.drawImage(this.img, 0, 0, chunkw, chunkh, x * chunkw, y * chunkh, (x + 1) * chunkw, (y + 1) * chunkh,
						null);
				System.out.format("idx=%d dw=%d dh=%d sx=%d sy=%d sw=%d sh=%d\n", idx, chunkw, chunkh, x * chunkw,
						y * chunkh, (x + 1) * chunkw, (y + 1) * chunkh);
				gr.dispose();
			}

		}
		return imgs;
	}

	private void waitLoadingImage() {
		MediaTracker tracker = new MediaTracker(compt);
		tracker.addImage(img, 0);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class AnimationPanel extends JPanel {
	private ParseImage parse;
	private int offset = 12;
	private int animLeft = -50;
	private int animTop = 150;
	private int frameIdx = 0;
	Image[] frames;

	public int getTop() {
		return animTop;
	}

	public void setTop(int top) {
		this.animTop = top;
	}

	public int getLeft() {
		return animLeft;
	}

	public void setLeft(int left) {
		this.animLeft = left;
	}

	public AnimationPanel() {

	}

	public void parse() {
		frames = this.parse.parse();
	}

	public ParseImage getParse() {
		return parse;
	}

	public void setParse(ParseImage parse) {
		this.parse = parse;
	}

	public void move() {
		this.move(this.offset);
	}

	public void move(int offset) {
		this.animLeft += offset;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	private int moveIdx() {
		int idx = this.frameIdx++;
		if (this.frameIdx >= this.frames.length) {
			this.frameIdx = 0;
		}
		return idx;
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
		g.drawImage(this.frames[this.moveIdx()],
				this.animLeft % (w + this.frames[0].getWidth(null)) - this.frames[0].getWidth(null), this.animTop,
				null);

	}

}
