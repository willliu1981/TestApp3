package swingtest;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import practice.S;



public class ImageTest {

	public static void main(String[] args) {
		MyImage mi = new MyImage();

	}

}



class MyImage extends JFrame {
	JPanel contentPanel;

	public MyImage() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(100, 100, 550, 500);
		/*
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*///
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		/*
		Insets ins = this.getInsets();
		paint4(g, ins);
		 *///
		Toolkit kit=this.getToolkit();
		Image image=kit.getImage("src\\data\\x.jpg");
		g.drawImage(image,50,50, rootPane);
	}



	private void paint4(Graphics g, Insets ins) {
		g.setColor(Color.cyan);
		g.fillRect(ins.left, ins.top, this.getWidth() - (ins.right + ins.left),
				this.getHeight() - (ins.top + ins.bottom));
		Color color;
		g.setFont(new Font("標楷體", Font.BOLD, 22));

		Toolkit kit = Toolkit.getDefaultToolkit();
		String fname="src\\data\\p0.png"; 
		Image img = kit.getImage(fname);
		
		//File f=new File(fname);
		//S.o.l(f.exists());
		
		//S.o.l(img);
		int x, y, w, h;
		w = img.getWidth(contentPanel);
		h = img.getHeight(contentPanel);
		x = ins.left + 5;
		y = ins.top + 5;
		//g.drawImage(img, x, y, contentPanel);
		//g.drawImage(img, x, y,50,350, contentPanel);
		g.drawImage(img, x, y,w+x,h/4+y, x+w,h/2,0,0, contentPanel);
		
		
		
	}

	private void paint3(Graphics g, Insets ins) {
		g.setColor(Color.cyan);
		g.fillRect(ins.left, ins.top, this.getWidth() - (ins.right + ins.left),
				this.getHeight() - (ins.top + ins.bottom));
		Color color;
		g.setFont(new Font("標楷體", Font.BOLD, 16));
		int x, y, w, h;
		int gapx = 50, gapy = 100;
		x = gapx + ins.left;
		y = gapy + ins.top;
		w = 50;
		h = 50;
		FontMetrics fm = g.getFontMetrics();
		int fh = fm.getAscent();
		int i = 1;
		g.setColor(Color.red);
		g.drawLine(x, y, w + x, h + y);
		g.drawString("line", x, y + h + fh);
		g.drawRect((w + gapx) * i + x, y, w, h);
		g.drawString("rect", (w + gapx) * i++ + x, y + h + fh);
		g.fill3DRect((w + gapx) * i + x, y, w, h, true);
		g.drawString("3Drect", (w + gapx) * i++ + x, y + h + fh);
		g.fillRoundRect((w + gapx) * i + x, y, w, h, 20, 20);
		g.drawString("round", (w + gapx) * i++ + x, y + h + fh);
		i = 0;
		g.fillOval((w + gapx) * i + x, y + gapy, w, h / 2);
		g.drawString("oval", (w + gapx) * i++ + x, y + gapy + h + fh);
		g.fillArc((w + gapx) * i + x, y + gapy, w, h / 2, 0, 90);
		g.drawString("arc", (w + gapx) * i++ + x, y + gapy + h + fh);
		int ix = (w + gapx) * i + x;
		int iy = y + gapy;
		int[] a = { ix, ix, ix + w / 2, ix + w, ix + w }, b = { iy, iy + h, iy, iy + h, iy + h / 2 };
		int nums = 10;
		g.fillPolygon(a, b, a.length);
		g.drawString("polygon", (w + gapx) * i++ + x, y + gapy + h + fh);
		ix = (w + gapx) * i + x;
		iy = y + gapy;
		a = new int[] { ix, ix, ix + w / 2, ix + w, ix + w };
		b = new int[] { iy, iy + h, iy, iy + h, iy };
		g.drawPolyline(a, b, a.length);
		g.drawString("polyline", (w + gapx) * i++ + x, y + gapy + h + fh);

	}

	private void paint2(Graphics g, Insets ins) {
		g.setColor(Color.cyan);
		g.fillRect(ins.left, ins.top, this.getWidth() - (ins.right + ins.left),
				this.getHeight() - (ins.top + ins.bottom));
		Color color;
		g.setFont(new Font("標楷體", Font.BOLD, 16));
		int x, y, w, h;
		int gapx = 50, gapy = 100;
		x = gapx + ins.left;
		y = gapy + ins.top;
		w = 50;
		h = 50;
		FontMetrics fm = g.getFontMetrics();
		int fh = fm.getAscent();
		int i = 1;
		g.setColor(Color.red);
		g.drawLine(x, y, w + x, h + y);
		g.drawString("line", x, y + h + fh);
		g.drawRect((w + gapx) * i + x, y, w, h);
		g.drawString("rect", (w + gapx) * i++ + x, y + h + fh);
		g.draw3DRect((w + gapx) * i + x, y, w, h, true);
		g.drawString("3Drect", (w + gapx) * i++ + x, y + h + fh);
		g.fillRoundRect((w + gapx) * i + x, y, w, h, 20, 20);
		g.drawString("round", (w + gapx) * i++ + x, y + h + fh);
		i = 0;
		g.drawOval((w + gapx) * i + x, y + gapy, w, h / 2);
		g.drawString("oval", (w + gapx) * i++ + x, y + gapy + h + fh);
		g.drawArc((w + gapx) * i + x, y + gapy, w, h / 2, 0, 90);
		g.drawString("arc", (w + gapx) * i++ + x, y + gapy + h + fh);
		int ix = (w + gapx) * i + x;
		int iy = y + gapy;
		int[] a = { ix, ix, ix + w / 2, ix + w, ix + w }, b = { iy, iy + h, iy, iy + h, iy };
		int nums = 10;
		g.drawPolygon(a, b, a.length);
		g.drawString("polygon", (w + gapx) * i++ + x, y + gapy + h + fh);
		ix = (w + gapx) * i + x;
		iy = y + gapy;
		a = new int[] { ix, ix, ix + w / 2, ix + w, ix + w };
		b = new int[] { iy, iy + h, iy, iy + h, iy };
		g.drawPolyline(a, b, a.length);
		g.drawString("polyline", (w + gapx) * i++ + x, y + gapy + h + fh);

	}

	private void paint1(Graphics g, Insets ins) {
		g.setColor(Color.cyan);
		g.fillRect(ins.left, ins.top, this.getWidth() - (ins.right + ins.left),
				this.getHeight() - (ins.top + ins.bottom));
		Color color;
		g.setFont(new Font("標楷體", Font.BOLD, 22));
		int idx = 0;
		String str = "Hello world";
		StringBuilder msg = new StringBuilder(10);
		FontMetrics fm = g.getFontMetrics(g.getFont());
		while (idx < str.length()) {
			color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
			g.setColor(color);
			int w;
			g.drawString(str.substring(idx, idx + 1), 20 + (w = fm.stringWidth(str.substring(1, idx + 1))),
					40 + ins.top);
			g.drawString(str.substring(0, idx + 1) + " " + w, 20, 50 + (idx + 1) * 40 + ins.top);
			idx++;
		}
	}

}