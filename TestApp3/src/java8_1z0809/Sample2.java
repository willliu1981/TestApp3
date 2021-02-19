package java8_1z0809;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Sample2 {
	public static void main(String[] args) throws Exception {
		Practice.get("src\\java8_1z0809").run();
	}
}

class Pa {
	public static void main(String[] args) {
		
	}
}
class P13 {
	public static void main(String[] args) {
		Path p1=Paths.get("ccc/ddd/rrr");
		S.o.l(p1);
		p1=p1.toAbsolutePath();
		S.o.l(p1.toAbsolutePath());
		
		Path p2=Paths.get("/ggg/xxx");
		S.o.l(p2);
		p2=p2.toAbsolutePath();
		S.o.l(p2.toAbsolutePath());
		p1=p1.relativize(p2);
		S.o.l(p1);
		S.o.l(p1.toAbsolutePath());

	}
}
class P12 {
	public static void main(String[] args) {
		Path p1=Paths.get("/home/./ttt/xxx/cc/ccc/fff");
		S.o.l(p1);
		p1=p1.normalize();
		S.o.l(p1);
		p1=p1.subpath(1, 3);
		S.o.l(p1);
		p1=p1.resolve("cccc");
		S.o.l(p1);
		Path pp=Paths.get("rrr");
		p1=p1.relativize(pp);
		S.o.l(p1);
		
		
	}
}
class P11 {
	public static void main(String[] args) {
		//Path p1=Paths.get("C:\\Users\\KuanWei\\git\\TestApp3\\.gitignore");
		//Path p1=Paths.get("\\TestApp3\\.gitignore");
		Path p1=Paths.get("");
		S.o.l(p1.getFileName());
		S.o.l(p1.getParent());
		S.o.l(p1.getNameCount());
		S.o.l(p1.getRoot());
		S.o.l(p1.isAbsolute());
		S.o.l(p1.toAbsolutePath());
		S.o.l(p1.toUri());
		
		
	}
}

class P10 {
	public static void main(String[] args) {
		String fname=System.getProperty("user.dir")+"\\src\\java8_1z0809\\data\\book.sav";
		output(fname);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input(fname).forEach(System.out::println);
	}
	
	static void output(String fname) {
		Book b1=new Book("Java",1200d,new Author("David",10),new Author("Helen",3));
		Book b2=new Book("PHP",900d,new Author("David",10));
		Book b3=new Book("C#",760d,new Author("Kevin",8),new Author("Helen",3));



		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fname))){
			oos.writeObject(b1);
			oos.writeObject(b2);
			oos.writeObject(b3);
			
			S.o.l("output\n",b1);
			S.o.l(b2);
			S.o.l(b3+"\ninput");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static List<Book> input(String fname) {
		List<Book> list=new ArrayList<>();
		Book b=null;
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fname)) ){
			while((b=(Book)ois.readObject())!=null) {
				b.setDiscount(0.9);
				list.add(b);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(EOFException e) {
			S.o.l("EOF...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	static class Book implements Serializable{
		static String publisher="NewBook";
		List<Author> authors = new ArrayList<>();
		String name;
		transient Double discount;
		Double price;
		Double discountPrice;
		transient int publish_year;

		Book(String name, Double price, Author... authors) {
			this.name = name;
			this.price = price;
			for (Author a : authors) {
				this.authors.add(a);
			}
			this.discount=0.7;
			
		}
		
		private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
			ois.defaultReadObject();
			Manager m=(Manager)ois.readObject();
			this.publish_year=ois.readInt();
			S.o.l(m);
			S.o.l("get time:"+new Date());
		}
		
		private void writeObject(ObjectOutputStream oos) throws IOException {
			oos.defaultWriteObject();
			Manager m=new Manager("Paul");
			oos.writeObject(m);
			
			this.publish_year=2021;
			oos.writeInt(publish_year);
			S.o.l("save data:"+m);
		}
		
		public void setDiscount(double discount) {
			this.discount=discount;
		}
		
		public Double getDiscountPrice() {
			return this.price*this.discount;
		}
		
		public String toString() {
			StringBuilder sb=new StringBuilder();
			for(Author a:this.authors) {
				sb.append(a);
				sb.append("\n\t");
			}
			return String.format("publisher:%s\n"
					+ "publish year:%d\n"
					+ "book name:%s\n"
					+ "price:%.2f\n"
					+ "discount:%f\n"
					+ "discountprice:%f\n"
					+ "aruthors:%s\n",
					this.publisher,
					this.publish_year,
					this.name,
					this.price,
					this.discount,
					this.getDiscountPrice(),
					sb); 
		}
	}

	static class Author implements Serializable {
		String name;
		Integer work_for_age;
		
		Author(String name,int age){
			this.name=name;
			this.work_for_age=age;
		}
		
		public String toString() {
			return String.format("name:%s work for age:%d",this.name,this.work_for_age);
		}
	}
	
	static class Manager implements Serializable{
		String name;
		Date date;
		
		Manager(String name){
			this.name=name;
			this.date=new Date();
		}
		
		public String toString() {
			return String.format("name:%s\n time:%s\n",this.name,this.date);
		}
	}
}

class P9 {
	public static void main(String[] args) {
		String output = System.getProperty("user.dir") + "\\src\\java8_1z0809\\data\\order.ser";
		serializtion(output);
		S.o.l("--------------------");
		deserializtion(output);
	}

	static void serializtion(String output) {
		Shirt s1 = new Shirt("Brand1", 100, 100);
		Shirt s2 = new Shirt("Brand2", 100, 200);
		Shirt s3 = new Shirt("Brand3", 100, 300);
		Order o = new Order(s1, s2, s3);

		Order.staticField = 22;
		try (FileOutputStream fos = new FileOutputStream(output);
				ObjectOutputStream out = new ObjectOutputStream(fos)) {
			out.writeObject(o);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		S.o.l(o);
	}

	static void deserializtion(String output) {
		Order o = null;
		try (FileInputStream fos = new FileInputStream(output); ObjectInputStream in = new ObjectInputStream(fos)) {
			o = (Order) in.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		S.o.l(o);
	}

	public static class Shirt implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String brand;
		private int quantity;
		private double cost;
		private transient double price;

		public Shirt(String brand, int quantity, double cost) {
			this.brand = brand;
			this.quantity = quantity;
			this.cost = cost;
		}

		private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
			ois.defaultReadObject();
			this.price = this.cost + 50;
		}

		public String toString() {
			return String.format("brand=%s quantity=%d cost=%.2f price=%.2f\n", this.brand, this.quantity, this.cost,
					this.price);
		}
	}

	static class Order implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<Shirt> shirts = new ArrayList<>();
		static int staticField = 100;
		transient int transientField = 100;

		public Order(Shirt... shirts) {
			for (Shirt s : shirts) {
				this.shirts.add(s);
			}
			staticField = 99;
			transientField = 99;
		}

		private void writeObject(ObjectOutputStream oos) throws IOException {
			oos.defaultWriteObject();
			Date now = new Date();
			oos.writeObject(now);
			S.o.l("serialized at " + now);
		}

		private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
			ois.defaultReadObject();
			S.o.l("restored form data: " + (Date) ois.readObject());
			S.o.l("restored at: " + new Date());
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (Shirt s : shirts) {
				sb.append(s);
			}
			sb.append("staticField=" + staticField);
			sb.append("\ntransientField=" + transientField);
			return sb.toString();
		}

	}
}

class P8 {
	public static void main(String[] args) {
		String out = System.getProperty("user.home");
		String out2 = System.getProperty("user.dir");
		S.o.l(out);
		S.o.l(out2);
	}
}

class P7 {
	public static void main(String[] args) {
		String source = "src\\java8_1z0809\\data\\w1.txt", target = "src\\java8_1z0809\\data\\wx1.txt";
		try (FileChannel in = new FileInputStream(source).getChannel();
				FileChannel out = new FileOutputStream(target).getChannel()) {
			ByteBuffer buff = ByteBuffer.allocate(100);
			in.read(buff);
			buff.position(0);
			out.write(buff);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P6 {
	public static void main(String[] args) {
		System.out.println("Input user and password");
		Console cons = System.console();
		boolean r = false;
		if (cons != null) {
			String user;
			String password;
			do {
				user = cons.readLine("user: ");
				password = new String(cons.readPassword("password: "));
				if (user.equals("root") && password.equals("1234")) {
					r = true;
					System.out.println("Correct!");
				} else {
					System.out.println("Wrong!");
				}
			} while (!r);
		}
	}
}

class P5 {
	public static void main(String[] args) {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
			String s = "";
			while (s != null) {
				System.out.print("Input: ");
				s = in.readLine();
				s.trim();
				System.out.println("s= " + s);

				if (s.equalsIgnoreCase("exit")) {
					System.exit(0);
				}
			}
		} catch (Exception e) {

		}
	}
}

class P4 {
	public static void main(String[] args) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File f = new File("src\\java8_1z0809\\data\\w1.txt");
			is = new FileInputStream(f);
			S.o.l("is: ", is.markSupported());
			isr = new InputStreamReader(is);
			S.o.l("isr: ", isr.markSupported());
			br = new BufferedReader(isr);
			S.o.l("br: ", br.markSupported());

			S.o.l(br.readLine());
			S.o.l(br.readLine());
			br.mark(1);
			S.o.l(br.readLine());
			S.o.l(br.readLine());
			S.o.l(br.readLine());
			br.reset();
			S.o.l(br.readLine());

		} catch (Exception e) {

		}

	}
}

class P3 {
	public static void main(String[] args) {
		String source = "src\\java8_1z0809\\data\\w1.txt";
		String target = "src\\java8_1z0809\\data\\w2.txt";
		char[] bs = new char[12];
		try (BufferedReader fis = new BufferedReader(new FileReader(source));
				BufferedWriter fos = new BufferedWriter(new FileWriter(target))) {
//			try (FileReader fis = new FileReader(source);
//					FileWriter fos =new FileWriter(target)) {
			int read = 0;
			long t1 = new Date().getTime(), t2;

//			while ((read = fis.read(bs)) != -1) {
//				if (read < bs.length) {
//					fos.write(bs, 0, read);
//				} else {
//					fos.write(bs, 0, read);
//				} ///
//				fos.write(bs, 0, read);
//			}

			String line = "";
			while ((line = fis.readLine()) != null) {
				fos.write(line);
				fos.newLine();
			}

//			while ((read = fis.read()) != -1) {
//				fos.write(read);
//			}

			t2 = new Date().getTime();
			S.o.l(t2 - t1);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class P2 {
	public static void main(String[] args) {
		String source = "src\\java8_1z0809\\data\\n1.png";
		String target = "src\\java8_1z0809\\data\\n5.png";
		byte[] bs = new byte[128];
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(target)) {
			S.o.l("Will copy bytes: " + fis.available());
			int read = 0;
			long t1 = new Date().getTime(), t2;

			/*
			 * while ((read = fis.read(bs)) != -1) { /* if(read<bs.length) {
			 * fos.write(bs,0,read); }else { fos.write(bs,0,read); } ///
			 * fos.write(bs,0,read); }
			 *///

			while ((read = fis.read()) != -1) {
				fos.write(read);
			}

			t2 = new Date().getTime();
			S.o.l(t2 - t1);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
