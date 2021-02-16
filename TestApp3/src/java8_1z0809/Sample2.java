package java8_1z0809;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sample2 {
	public static void main(String[] args) throws Exception {
		Practice.get("src\\java8_1z0809").run();
	}
}

class Pa {
	public static void main(String[] args) {

	}
}

class P2 {
	public static void main(String[] args) {
		String source = "";
		String target = "";
		byte[] bs = new byte[128];
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(target)) {

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
