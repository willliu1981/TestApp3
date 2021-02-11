package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TransparencyTest extends JFrame {

	private JPanel contentPane;
	public static int color_range = 210;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransparencyTest frame = new TransparencyTest();
					frame.setVisible(true);
					String sfname="src\\data\\horserun.jpg";
					String dfname="src\\data\\horserun.png";
					transparentize(dfname,sfname);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TransparencyTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public static void transparentize(String destination,String source) throws IOException {
		BufferedImage image = ImageIO.read(new File(source));
		// 高度和宽度
		int height = image.getHeight();
		int width = image.getWidth();

		// 生产背景透明和内容透明的图片
		ImageIcon imageIcon = new ImageIcon(image);
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics(); // 获取画笔
		g2D.drawImage(imageIcon.getImage(), 0, 0, null); // 绘制Image的图片

		int alpha = 0; // 图片透明度
		// 外层遍历是Y轴的像素
		for (int y = bufferedImage.getMinY(); y < bufferedImage.getHeight(); y++) {
			// 内层遍历是X轴的像素
			for (int x = bufferedImage.getMinX(); x < bufferedImage.getWidth(); x++) {
				int rgb = bufferedImage.getRGB(x, y);
				// 对当前颜色判断是否在指定区间内
				if (colorInRange(rgb)) {
					alpha = 0;
				} else {
					// 设置为不透明
					alpha = 255;
				}
				// #AARRGGBB 最前两位为透明度
				rgb = (alpha << 24) | (rgb & 0x00ffffff);
				bufferedImage.setRGB(x, y, rgb);
			}
		}
		// 绘制设置了RGB的新图片
		g2D.drawImage(bufferedImage, 0, 0, null);

		// 生成图片为PNG
		ImageIO.write(bufferedImage, "png", new File(destination));

		System.out.println("完成画图");
	}

	public static boolean colorInRange(int color) {
		int red = (color & 0xff0000) >> 16;// 获取color(RGB)中R位
		int green = (color & 0x00ff00) >> 8;// 获取color(RGB)中G位
		int blue = (color & 0x0000ff);// 获取color(RGB)中B位
		// 通过RGB三分量来判断当前颜色是否在指定的颜色区间内
		if (red >= color_range && green >= color_range && blue >= color_range) {
			return true;
		}
		;
		return false;
	}

}
