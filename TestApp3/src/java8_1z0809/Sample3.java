package java8_1z0809;

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

		Car car = new MyCar("Pui");
		car.start();
		car.start();

		System.out.println("ICar...");
		MyRunnable runcar = new MyRunnable();
		Car icar = new Car(runcar,"IPui");
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
