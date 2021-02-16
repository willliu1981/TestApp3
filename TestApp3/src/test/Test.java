package test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.xml.transform.stream.StreamSource;

import practice.S;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=-2;
		S.o.l(Integer.toHexString(a));
		
		TestFrame tf=new TestFrame();
		tf.setVisible(true);
	}

}

class TestFrame extends JFrame{
	public TestFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,200,200);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.setColor(new Color(-1));
		System.out.println(g.getColor().getRGB());
		System.out.format("a::%d r:%d g:%d b:%d\n",g.getColor().getAlpha(),g.getColor().getRed(),g.getColor().getGreen(),g.getColor().getBlue());
		g.fillRect(0, 0, 200, 200);
	
	}
	
	
}
