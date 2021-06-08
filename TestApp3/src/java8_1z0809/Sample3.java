package java8_1z0809;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import static java8_1z0809.S.o;

public class Sample3 {
	public static void main(String[] args) throws Exception {
		Practice.get("src\\java8_1z0809").run();
	}
}

class Pxxx {
	public static void main(String[] args) {

	}
}

class P30 {
	public static void main(String[] args) {
	}
}

class P39 {
	public static void main(String[] args) {
		String a="123";
		String b=a;
		
		o.l(b);
		
	}
}

class P38 {
	public static void main(String[] args) {
		int e[][] = { { 1, 2, 3 }, { 2, 4, 6 } };
		MyClass myClass1 = new MyClass();
		MyClass myClass2 = new MyClass();
		MyClass myClass3 = myClass1;

		System.out.println("e= " + e);
		System.out.println("myClass1= " + myClass1);

		myClass1.i1 = 1;
		System.out.println("myClass2= " + myClass2);
		System.out.println("myClass1.s1= " + myClass1.i1);
		System.out.println("myClass2.s1= " + myClass2.i1);

		System.out.println("myClass1 id=" + System.identityHashCode(myClass1));
		System.out.println("myClass2 id=" + System.identityHashCode(myClass2));
		System.out.println("myClass3 id=" + System.identityHashCode(myClass3));
	}

	static class MyClass {
		public int i1;

		@Override
		public int hashCode() {

			return 1;
		}

		@Override
		public boolean equals(Object arg0) {
			return true;
		}

	}

}

class P37 {
	public static void main(String[] args) {
		String aa = "66";
		String bb = new String("66");
		String cc = "66";
		test(aa);
		o.l(aa);
		o.l(aa == bb);
		o.l(cc == aa);

	}

	static void test(String a) {
		a = new String("yy");
	}
}

class P36 {
	public static void main(String[] args) {
		int e[][] = { { 1, 2, 3 }, { 2, 4, 6 } };
		int rr = 2, ss = 3;
		f(e, rr, ss);
		for (int i = 0; i <= rr - 1; i++) {
			for (int j = 0; j <= ss - 1; j++)
				System.out.print(e[i][j] + " ");
			System.out.println();
		}
	}

	static void f(int b[][], int r, int s) {
		for (int i = 0; i <= r - 1; i++)
			for (int j = 0; j <= s - 1; j++)
				b[i][j] = b[i][j] * 2;
	}

}

class P35 {
	public static void main(String[] args) throws InterruptedException {
		int p = 3, q = 4;
		swap(p, q);
		o.l(p);
		o.l(q);
	}

	static void swap(int a, int b) {
		int t;
		t = a;
		a = b;
		b = t;
		System.out.println(a);
		System.out.println(b);
	}
}

class P34 {
	public static void main(String[] args) {
		Clock c;
		new Thread(c = new Clock()).start();

		JFrame frame = new JFrame("xxx");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);

		JButton button = new JButton();
		frame.getContentPane().add(button);

		while (true) {
			button.setText(c.time);
		}
	}

	static class Clock implements Runnable {
		static public String time;

		@Override
		public void run() {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			while (true) {
				System.out.println(time = sdf.format(new Date()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class P33 {
	public static void main(String[] args) throws InterruptedException {
		VisibilityProblem.test();
	}

	static class VisibilityProblem {
		static volatile int num;
		static int numx;
		static int numx2;

		public static void test() throws InterruptedException {

			Thread readerThread = new Thread(() -> {
				int temp = 0;
				while (true) {
					// System.out.println("reader: value of num = " + num);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					numx++;
					if (numx > 2000000000) {
						numx2++;
						numx = 0;
					}
					if (temp != num) {
						temp = num;
						System.out.println("reader: value of num = " + num);
					}
				}
			});

			Thread writerThread = new Thread(() -> {
				for (int i = 0; i < 5; i++) {
					num++;
					System.out.println("writer: changed value to = " + num);

					// 進入睡眠，以讓readerThread有足夠時間讀到int
					// num的改變(因為num++非具原子性的操作，readerThread仍有一定機會讀到錯誤的值)
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 離開程式，否則readerThread會一直等待變數int num的值改變
				// System.exit(0);
			});

			readerThread.start();
			writerThread.start();

//			readerThread.join();
			writerThread.join();

			if (!writerThread.isAlive()) {
				System.out.println("xxx2:" + numx2);
				System.out.println("xxx:" + numx);
				System.exit(0);
			}
		}
	}

}

class P32 {
	public static void main(String[] args) throws InterruptedException {
		MyRun run = new MyRun();
		Thread t = new Thread(run);
		t.start();

		Thread.sleep(1);
		run.stop();
	}

	static class MyRun implements Runnable {
		private volatile boolean stop = false;
		public static int num;

		public void stop() {
			this.stop = true;
		}

		@Override
		public void run() {
			while (!stop) {
				System.out.println(num++);
			}
		}

	}
}

class P31 {
	public static void main(String[] args) {

		Car car = new MyCar("Pui");
		car.start();
		car.start();

		System.out.println("ICar...");
		MyRunnable runcar = new MyRunnable();
		Car icar = new Car(runcar, "IPui");
		icar.start();
		icar.start();

	}

	interface CarRunnable {
		int run();
	}

	static class MyRunnable implements CarRunnable {

		@Override
		public int run() {
			int mileage = 3;
			return mileage;
		}
	}

	static class MyCar extends Car {

		public MyCar() {
		}

		public MyCar(String name) {
			super(name);
		}

		@Override
		public int run() {
			int mileage = 2;
			return mileage;
		}

	}

	static class Car {
		private static Integer name_nums = 0;
		private Integer mileage;
		private String name;
		private CarRunnable runnable;

		public Car() {
			this("Car-" + name_nums++);
		}

		public Car(String name) {
			this.name = name;
			this.mileage = 0;
			this.runnable = new CarRunnable() {
				@Override
				public int run() {
					return 0;
				}
			};
		}

		public Car(CarRunnable runnable) {
			this();
			this.runnable = runnable;
		}

		public Car(CarRunnable runnable, String name) {
			this(name);
			this.runnable = runnable;
		}

		public int run() {
			return runnable.run();
		}

		public void start() {
			int mileage = this.run();
			this.mileage += mileage;
			System.out.println(
					String.format("car(%s) is run %d mileage , and total is %d mileage", this, mileage, this.mileage));

		}

		public Integer getMileage() {
			return mileage;
		}

		public void setMileage(Integer mileage) {
			this.mileage = mileage;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String toString() {
			return String.format("name=%s , mileage=%d", this.name, this.mileage);
		}

	}

	static class T extends Thread {
		public T(String name) {
			super(name);

		}

		public T() {

		}
	}

	static class MyT implements Runnable {

		@Override
		public void run() {

		}

	}
}
