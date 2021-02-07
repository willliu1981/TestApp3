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

public class Sample2 {
	public static void main(String[] args) throws Exception {
		Practice.get("src\\java9").sufnameMax(10).run();
	}
}

class P {
	public static void main(String[] args) {

	}
}

class P6 {
	public static void main(String[] args) {
		P6 p = new P6();
		/*
		 * Granary gr = p.new Granary("穀倉", 9, 600); Rice rice = p.new Rice();
		 * rice.setQuantity(1700); Wheat wheat = p.new Wheat(); wheat.setQuantity(700);
		 * Pork pork = p.new Pork(); pork.setQuantity(1000);
		 * 
		 * Supplier farmer1 = p.new Supplier("農夫", rice); Supplier farmer2 = p.new
		 * Supplier("農夫", wheat); Supplier farmer3 = p.new Supplier("養豬戶", pork);
		 * S.o.l("供給者1 ", farmer1); S.o.l("供給者2 ", farmer2); S.o.l("供給者3 ", farmer3);
		 * 
		 * // * gr.push(farmer1); gr.push(farmer2); gr.push(farmer3); ///
		 * 
		 * S.o.l("供給->穀倉 ", gr);
		 * 
		 * Consumer consumer = p.new Consumer("食物攤販", 4); Consumer consumer2 = p.new
		 * Consumer("食物攤販", 4); consumer.setStack(p.new Rice(100, 200), p.new Rice(50,
		 * 200), p.new Wheat(50, 200));
		 * 
		 * gr.pop(consumer); S.o.l("穀倉->需求 ", gr); // S.o.l("需求-攤販1 ",consumer);
		 * 
		 * consumer2.setStack(p.new Rice(200), p.new Rice(200), p.new Wheat(200));
		 * gr.pop(consumer2); S.o.l("穀倉->需求 ", gr);
		 */
		run(p);
	}

	public static void run(P6 p) {
		GranaryManager gr = p.new GranaryManager("穀倉", p);
		Supplier[] sups = new Supplier[3];
		Consumer[] sons = new Consumer[10];
		p.new Produce().produce(sons);
		p.new Produce().produce(sups);

		ConsumerController conCtrl = p.new ConsumerController(gr, sons);
		SupplierController suppCtrl = p.new SupplierController(gr, sups);

		conCtrl.start();
		Thread mySuppCtrl = new Thread(suppCtrl);
		mySuppCtrl.start();

		gr.open();
		S.o.l("Granary open...");

		try {
			Thread.sleep(20000);
			gr.close();
			conCtrl.interrupt();
			mySuppCtrl.interrupt();
			S.o.l("Granary close");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class Produce {
		public void produce(Produceable[] produces) {

			switch (produces.getClass().getSimpleName()) {
			case "Consumer[]":
				Consumer[] cons = (Consumer[]) produces;
				for (int i = 0; i < cons.length; i++) {
					int count = (int) (Math.random() * 5 + 2);
					Food[] fs = new Food[count];
					int idx = 0;
					while (idx < fs.length) {
						int rnd = (int) (Math.random() * 5);
						fs[idx] = null;
						switch (rnd) {
						case 0:
							fs[idx] = new Rice(200);
							break;
						case 1:
							fs[idx] = new Wheat(200);
							break;
						case 2:
						case 3:
						case 4:
							fs[idx] = new Pork(100);
							break;
						default:
							break;
						}
						idx++;
					}
					cons[i] = new Consumer("食物攤販#" + (i + 1), fs);
				}
				break;
			case "Supplier[]":
				Supplier[] supps = (Supplier[]) produces;
				for (int i = 0; i < supps.length; i++) {
					int rnd = (int) (Math.random() * 3);
					switch (rnd) {
					case 0:
						supps[i] = new Supplier("農人#" + (i + 1), new Rice());
						supps[i].setQuantity((int) (Math.random() * 1000 + 1000));
						break;
					case 1:
						supps[i] = new Supplier("農人#" + (i + 1), new Wheat());
						supps[i].setQuantity((int) (Math.random() * 1000 + 1000));
						break;
					case 2:
						supps[i] = new Supplier("養豬人#" + (i + 1), new Pork());
						supps[i].setQuantity((int) (Math.random() * 1000 + 1000));
						break;
					default:
						break;
					}
				}
				break;
			default:
				break;
			}
		}

		public void resetQuantity(Produceable[] produces) {

			switch (produces.getClass().getSimpleName()) {
			case "Consumer[]":
				Consumer[] cons = (Consumer[]) produces;
				for (int i = 0; i < cons.length; i++) {
					int idx = 0;
					while (idx < cons[i].getStacks().length) {
						cons[i].getStacks()[i].setQuantity(0);
						idx++;
					}
				}
				break;
			case "Supplier[]":
				Supplier[] supps = (Supplier[]) produces;
				for (int i = 0; i < supps.length; i++) {
					int idx = 0;
					supps[i].setQuantity((int) (Math.random() * 1000 + 1000));
				}
				break;
			default:
				break;
			}
		}
	}

	class ConsumerController extends Thread {
		GranaryManager gr;
		Consumer[] consumers;

		public ConsumerController(GranaryManager gr, Consumer[] consumers) {
			this.gr = gr;
			this.consumers = consumers;
		}

		@Override
		public void run() {
			try {
				while (gr.getEnable()) {
					Thread.sleep((int) (Math.random() * 500 + 500));
					for (int i = 0; i < this.consumers.length; i++) {
						if (gr.getEnable() == false) {
							break;
						}
						this.consumers[i].init();
						if (Math.random() > 0.5) {
							gr.pop(consumers[i]);
						}
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class SupplierController implements Runnable {
		GranaryManager gr;
		Supplier[] suppliers;

		public SupplierController(GranaryManager gr, Supplier[] suppliers) {
			this.gr = gr;
			this.suppliers = suppliers;
		}

		@Override
		public void run() {
			try {
				while (gr.getEnable()) {
					((P6) (gr.getP())).new Produce().resetQuantity(suppliers);
					Thread.sleep((int) (Math.random() * 500 + 500));
					for (int i = 0; i < this.suppliers.length; i++) {
						if (gr.getEnable() == false) {
							break;
						}
						if (Math.random() > 0.5) {
							gr.push(suppliers[i]);
						}
					}

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	class GranaryManager {
		private Granary granary;
		private boolean enable = false;
		private Object p;

		public GranaryManager(String name) {
			this(name, null);
		}

		public GranaryManager(String name, Object p) {
			this.granary = new Granary(name, 9, 600);
			this.p = p;
		}

		public synchronized void push(Food supplier) {
			// assert false:supplier;
			S.o.fn("\n%s -> %s ,%1$s 供應中 (%s)", supplier.getName(), granary.getName(), supplier);
			if (!granary.push(supplier)) {
				try {
					S.o.fn("%s -> %s ,%2$s 已滿,供貨未完成", supplier.getName(), granary.getName());
					S.o.fn("%s -> %s ,倉庫: %s ", supplier.getName(), granary.getName(), this.granary);
					S.o.fn("%s -> %s ,攤販: %s ", supplier.getName(), granary.getName(), supplier);
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				S.o.fn("%s -> %s ,%1$s 已離去", supplier.getName(), granary.getName());
				S.o.fn("%s -> %s ,%s ", supplier.getName(), granary.getName(), this.granary);
			}
			notify();
		}

		public synchronized void pop(Consumer consumer) {
			S.o.fn("\n%s -> %s ,%2$s 提貨中 (%s)", granary.getName(), consumer.getName(), consumer);
			if (this.granary.isEmpty()) {
				try {
					S.o.fn("%s -> %s ,%1$s 已空了,%2$s 因此離開了", granary.getName(), consumer.getName());
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				granary.pop(consumer);
				S.o.fn("%s -> %s ,%2$s 已離去", granary.getName(), consumer.getName());
				S.o.fn("%s -> %s ,倉庫: %s", granary.getName(), consumer.getName(), this.granary);
				S.o.fn("%s -> %s ,攤販: %s", granary.getName(), consumer.getName(), consumer);
			}
			notify();
		}

		public void open() {
			this.enable = true;
		}

		public void close() {
			this.enable = false;
		}

		public boolean getEnable() {
			return this.enable;
		}

		public Object getP() {
			return p;
		}

	}

	class Supplier extends Food implements Produceable {
		private String name;
		private Food food;

		public Supplier(String name, Food food) {
			this.name = name;
			this.food = food;
		}

		@Override
		public Food getFood() {
			return this.food.getFood();
		}

		public void init() {
			this.food.quantity = 0;
		}

		@Override
		public void setQuantity(Integer quantity) {
			this.food.setQuantity(quantity);
		}

		@Override
		String name() {
			return this.name;
		}

		@Override
		public String toString() {
			return String.format("[名稱:%s 食物:%s]", this.name, this.food);
		}

	}

	interface Produceable {

	}

	class Consumer implements Produceable {
		private String name;
		private Food[] stacks;

		public Consumer(String name, Food... stacks) {
			this.name = name;
			this.setStacks(stacks);
		}

		public void init() {
			int idx = 0;
			while (idx < this.stacks.length) {
				this.stacks[idx].quantity = 0;
				idx++;
			}
		}

		public void setStack(Food food, int idx) {
			this.stacks[idx] = food;
		}

		public void setStacks(Food... stacks) {
			this.stacks = stacks;
		}

		public Food[] getStacks() {
			return stacks;
		}

		public String getName() {
			return this.name;
		}

		@Override
		public String toString() {
			return String.format("[名稱:%s 最大棧位:%d 貨物:%s]", this.name, this.stacks.length, Arrays.asList(this.stacks));
		}

	}

	class Granary {
		private String name;
		private final Food[] stacks;
		private final int maxSpace;

		public Granary(String name, int maxStacks, int maxSpace) {
			this.name = name;
			this.stacks = new Food[maxStacks];
			this.maxSpace = maxSpace;
		}

		public void pop(Consumer consumer) {
			int idx = 0;
			if (consumer.stacks != null) {
				while (idx < consumer.getStacks().length) {
					if (consumer.stacks[idx] != null) {
						for (int i = 0; i < this.stacks.length; i++) {
							if (this.stacks[i] != null
									&& consumer.getStacks()[idx].getName().equalsIgnoreCase(this.stacks[i].getName())) {
								int a = this.stacks[i].quantity;
								if (consumer.getStacks()[idx].anySpace()) {
									this.stacks[i].quantity -= consumer.getStacks()[idx].push(this.stacks[i].quantity,
											consumer.getStacks()[idx].getMaxSpace());
									this.refresh(i);
								}
							}
						}
					}
					idx++;
				}
			}
		}

		// 供貨者放入倉庫,完全放入回傳true,若供貨者尚有貨物則回傳false
		public boolean push(Food food) {
			Food myFood = ((Supplier) food).getFood();
			int idx = 0;
			while (idx < stacks.length) {
				if (stacks[idx] != null && myFood.getName().equalsIgnoreCase(stacks[idx].getName())) {
					int space = 0;
					if ((space = anySpace(idx)) > 0) {
						stacks[idx].quantity += myFood.pop(space);
					}
				}
				idx++;
				if (myFood.quantity > 0) {
					continue;
				} else {
					break;
				}
			}

			while (true) {
				if ((idx = anyEmptyStack()) != -1) {
					this.stacks[idx] = myFood.creatNew();
					stacks[idx].quantity += myFood.pop(this.maxSpace);
					if (myFood.quantity > 0) {
						continue;
					} else {
						break;
					}
				} else {
					break;
				}
			}
			return myFood.quantity == 0;
		}

		public boolean isEmpty() {
			int idx = 0;
			boolean r = false;
			while (idx < this.stacks.length) {
				if (this.stacks[idx] != null) {
					r = true;
					break;
				}
				idx++;
			}
			return !r;
		}

		private void refresh() {
			int idx = 0;
			while (idx < this.stacks.length) {
				this.refresh(idx);
				idx++;
			}
		}

		private void refresh(int idx) {
			if (this.stacks[idx] != null) {
				if (this.stacks[idx].getQuantity() == 0) {
					this.stacks[idx] = null;
				}
			}

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

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return String.format("名稱:%s, 最大棧位:%d, 棧位最大容量:%d 貨物:%s", this.name, this.stacks.length, this.maxSpace,
					Arrays.asList(this.stacks));
		}

	}

	abstract class Food implements Cloneable {
		protected int quantity;
		protected int maxSpace;

		public Food() {
		}

		public Food(int maxSpace) {
			this.maxSpace = maxSpace;
		}

		public Food(int quantity, int maxSpace) {
			this.quantity = quantity;
			this.maxSpace = maxSpace;
		}

		public Food getFood() {
			return this;
		}

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

		public boolean anySpace() {
			return this.quantity < this.maxSpace;
		}

		// 取出數量,回傳成功取出數量
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

		// 存入數量,回傳成功存入數量
		public int push(int quantity, int limit) {
			int r = 0;
			if (this.quantity + quantity > limit) {
				r = limit - this.quantity;
				this.quantity = limit;
			} else {
				this.quantity += quantity;
				r = quantity;
			}
			return r;
		}

		public int getMaxSpace() {
			return maxSpace;
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
			return String.format("[名稱:%s , 數量:%d (最大 %d)]", this.name(), this.quantity, this.maxSpace);
		}
	}

	class Rice extends Food {

		public Rice() {
		}

		public Rice(int i) {
			super.maxSpace = i;
		}

		public Rice(int i, int j) {
			super(i, j);
		}

		@Override
		String name() {
			// TODO Auto-generated method stub
			return "稻米";
		}

	}

	class Wheat extends Food {

		public Wheat() {

		}

		public Wheat(int i) {
			super(i);
		}

		public Wheat(int i, int j) {
			super(i, j);
		}

		@Override
		String name() {
			// TODO Auto-generated method stub
			return "小麥";
		}

	}

	class Pork extends Food {
		public Pork() {

		}

		public Pork(int i) {
			super(i);
		}

		public Pork(int i, int j) {
			super(i, j);
		}

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
