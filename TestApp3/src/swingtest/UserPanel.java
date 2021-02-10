package swingtest;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import practice.S;

public class UserPanel extends JPanel {
	private int px, py;
	private JLabel label;

	public UserPanel(JLabel label) {
		this.setPreferredSize(new Dimension(200, 150));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				px = evt.getX();
				py = evt.getY();
				repaint();
			}
		});
		this.label = label; 
	}

	//*
	@Override
	protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		Insets ins = this.getInsets();
		int w = this.getWidth() - (ins.left + ins.right);
		int h = this.getHeight() - (ins.top + ins.bottom);
		int x = ins.left;
		int y = ins.right;
		g.setColor(Color.yellow);
		g.drawRect(x, y, w - 1, h - 1);
		System.out.println("xxx: "+w);
		label.setText(String.format("pos= %d , %d", this.px, this.py));
		
		
	}
	///



}
