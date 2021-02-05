package java9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class Sample {
	public static void main(String[] args) throws Exception {
		Practice.get("src\\java9").sufnameMax(20).run();
	}
}

class P {
	public static void main(String[] args) {

	}
}

class P6 {
	public static void main(String[] args) {
		P6 p = new P6();
		Granary gr = p.new Granary(9, 600);
		Rice rice = p.new Rice();
		rice.setQuantity(2800);
		Wheat wheat = p.new Wheat();
		wheat.setQuantity(2700);
		gr.push(rice);
		gr.push(wheat);
		S.o.l(gr);
		S.o.l(rice);
		S.o.l(wheat);
	}

	class Supplier {

	}

	class Consumer {

	}

	class GranaryManager {

	}

	class Granary {
		private final Food[] stacks;
		private final Integer maxSpace;

		public Granary(int maxStacks, int maxSpace) {
			stacks = new Food[maxStacks];
			this.maxSpace = maxSpace;
		}

		// 供貨者放入倉庫,完全放入回傳true,若供貨者尚有貨物則回傳false
		public boolean push(Food food) {
			int idx = 0;
			while (idx < stacks.length) {
				if (stacks[idx] != null && food.getName().equalsIgnoreCase(stacks[idx].getName())) {
					int space = 0;
					if ((space = anySpace(idx)) > 0) {
						stacks[idx].quantity += food.pop(space);
					}
				}
				idx++;
				if (food.quantity > 0) {
					continue;
				} else {
					break;
				}
			}

			while (true) {
				if ((idx = anyEmptyStack()) != -1) {
					this.stacks[idx] = food.creatNew();
					stacks[idx].quantity += food.pop(this.maxSpace);
					S.o.fn("stack idx:%d , quantity:%d", idx, food.getQuantity());
					if (food.quantity > 0) {
						continue;
					} else {
						break;
					}
				} else {
					break;
				}
			}
			return food.quantity == 0;
		}

		private void fill(int idx) {
			this.stacks[idx].setQuantity(this.maxSpace);
		}

		// 棧位有空間,回傳剩餘容量
		private int anySpace(int idx) {
			return this.stacks[idx] != null ? this.maxSpace - this.stacks[idx].getQuantity() : 0;
		}

		// 有任何棧位,回傳陣列index
		private int anyEmptyStack() {
			int r = -1;
			int idx = 0;
			for (Food d : stacks) {
				if (d == null) {
					r = idx;
					break;
				}
				idx++;
			}
			return r;
		}

		@Override
		public String toString() {
			return String.format("最大棧位:%d ,棧位最大容量:%d 貨物:%s", this.stacks.length, this.maxSpace,
					Arrays.asList(this.stacks));
		}

	}

	abstract class Food implements Cloneable {
		protected Integer quantity;

		abstract String name();

		private void init() {
			this.quantity = 0;
		}

		@Override
		public Food clone() {
			Food f = null;
			try {
				f = (Food) super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return f;
		}

		public Food creatNew() {
			Food f = this.clone();
			f.init();
			return f;
		}

		// 取出數量,若小於零,表示多取出多少數量
		public int pop(int quantity) {
			int r = 0;
			if (this.quantity >= quantity) {
				this.quantity -= quantity;
				r = quantity;
			} else {
				r = this.quantity;
				this.quantity = 0;
			}
			return r;
		}

		public String getName() {
			return this.name();
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		@Override
		public String toString() {
			return String.format("名稱:%s , 數量:%d", this.name(), this.quantity);
		}
	}

	class Rice extends Food {

		@Override
		String name() {
			// TODO Auto-generated method stub
			return "稻米";
		}

	}

	class Wheat extends Food {

		@Override
		String name() {
			// TODO Auto-generated method stub
			return "小麥";
		}

	}

	class Pork extends Food {

		@Override
		String name() {
			// TODO Auto-generated method stub
			return "豬肉";
		}

	}

}

class P5 {
	public static void main(String[] args) {
		EBook b = new EBook(5, 2);
		Thread t = new Thread(b, "Java");
		EBook b2 = new EBook(7);
		Thread t2 = new Thread(b2, "PHP");
		t.start();
		t2.start();
		try {
			t.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		S.o.l(b);
		S.o.l(b2);
	}

	static class Book {
		protected int targetPage;
		protected int nowPage = 1;
		protected String name;

		public Book(int targetPage) {
			this(targetPage, 1);
		}

		public Book(int targetPage, int nowpage) {
			this.targetPage = targetPage;
			this.nowPage = nowpage;
		}

		protected boolean flip() {
			boolean r = false;
			try {
				if (this.nowPage != this.targetPage) {
					if (this.nowPage > this.targetPage) {
						this.nowPage--;
					} else if (this.nowPage < this.targetPage) {
						this.nowPage++;
					}
					Thread.sleep((int) (Math.random() * 1000 + 1500));
				} else {
					r = true;
				}
				S.o.f("%s 翻到...第%d頁\n", this.name, this.nowPage);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return r;
		}

	}

	static class EBook extends Book implements Runnable {

		public EBook(int targetpage) {
			super(targetpage);
		}

		public EBook(int targetpage, int nowpage) {
			super(targetpage, nowpage);
		}

		@Override
		public void run() {
			this.name = Thread.currentThread().getName();
			S.o.fn("%s 目前第%d頁,開始翻頁...", this.name, this.nowPage);
			do {
				S.o.fn("%s 等待中...", this.name);
			} while (!this.flip());
			S.o.fn("%s已翻到第%d頁", this.name, this.nowPage);

		}

		@Override
		public String toString() {
			return String.format("書名%s ,目前第%d頁", this.name, this.nowPage);
		}
	}
}

class P4 {
	public static void main(String[] args) {
		String file = "src\\java92\\text.txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			S.o.l("正在寫入檔案...");
			bw.write("java web\n");
			bw.write("php mysql\n");
			bw.close();

			RandomAccessFile in = new RandomAccessFile(file, "rw");
			int ch;
			while ((ch = in.read()) != -1) {
				S.o.p((char) ch);
			}
			long fp = in.getFilePointer();
			in.seek(fp - 4);
			for (int i = 0; i < 3; i++) {
				S.o.p((char) in.read());
			}
			S.o.pln();
			fp = in.getFilePointer();
			in.seek(fp - 3);
			in.write('i');
			in.write('i');
			in.seek(0);
			while ((ch = in.read()) != -1) {
				S.o.p((char) ch);
			}
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P3 {
	public static void main(String[] args) {
		try {
			String file = "src\\java92\\test.txt";
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			// BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			BufferedReader bruser = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String in;
			S.o.p("請輸入:");
			while ((in = bruser.readLine()) != null) {
				if (in.equalsIgnoreCase("exit")) {
					break;
				}
				bw.write(in);
				bw.newLine();
				// bw.flush();
				S.o.p("請輸入:");
			}
			bw.close();

			String read;
			while ((read = br.readLine()) != null) {
				S.o.l(read);
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class P2 {
	public static void main(String[] args) throws Exception {
		Book b = new Book();
		Class<?> cls = Class.forName("java92.Book");
		// Method m = cls.getMethod("msg", new Class[] { String[].class });
		// Method m = cls.getMethod("msg", String[].class);
		Method m = null;
		switch (2) {
		case 1:
			// m = cls.getMethod("msg", new Class[] { String[].class, int[].class });
			m = cls.getMethod("msg", String[].class, int[].class);
			m.invoke(b, new String[] { "1" }, new int[] { 1, 2, 3 });
			break;
		case 2:
			m = cls.getMethod("msg", String[].class);
			String[] sss = new String[] { "1", "2" };
			Object o = sss;
			m.invoke(b, (Object) new String[] { "1", "2", "3" });
			break;
		case 3:
			m = cls.getMethod("msg", int[].class);
			m.invoke(b, new int[] { 1, 2, 3 });

			break;
		default:
			break;
		}

	}
}

class Book {
	public void msg(int... ss) {
		S.o.l("msg ...i: " + ss[0] + " , " + ss[1]);
	}

	public void msg(String... ss) {
		S.o.l("msg ...s: " + ss[0] + " , " + ss[1]);
	}

	public void msg(String[] s1, int... i1) {
		S.o.l("msg s i: " + s1[0] + " , " + i1[0]);
	}
}

class P1 {
	public static void main(String[] args) throws Exception {
		Super[] s = { new SubA(), new SubB() };
		Class cls = s[1].getClass();
		try {
			Method m = cls.getMethod("msg", null);
			m.invoke(s[1], null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

abstract class Super {
	public abstract void show();
}

class SubA extends Super {
	@Override
	public void show() {
		System.out.println("A show...");
	}
}

class SubB extends Super {
	@Override
	public void show() {
		System.out.println("B show...");
	}

	public void msg() {
		System.out.println("B msg...");
	}
}
