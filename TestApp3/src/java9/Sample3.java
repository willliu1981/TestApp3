package java9;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;

import practice.Practice;
import practice.S;

public class Sample3 {
	public static void main(String[] args) throws Exception {
		Practice.get("src\\java9").sufnameMax(30).run();
	}
}

class Pb {
	public static void main(String[] args) {

	}
}

class P24 {
	public static void main(String[] args) {
		P24 p = new P24();
		MyJ j = p.new MyJ();
		j.setVisible(true);
	}

	public class MyJ extends JFrame {
		public MyJ() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 344, 274);

		}


		
		
	}
}

class P23 {
	public static void main(String[] args) {
		S.o.l("test jdbc...");
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/gamedb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "1234";
		try {
			conn = DriverManager.getConnection(url, user, password);
			S.o.l(conn);

			String sql = "insert into userdata (id,userid,password,username,age,email) values (?,?,?,?,?,?);";
			PreparedStatement ps = null;

			ps = conn.prepareStatement(sql);
			ps.setInt(1, 100);
			ps.setString(2, "22");
			ps.setString(3, "1234");
			ps.setString(4, "root");
			ps.setInt(5, 24);
			ps.setString(6, "test@gmail.com");
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P22 {

	public static void main(String[] args) {

		new Thread(() -> P21.main(new String[] {})).start();

		try {
			DatagramSocket ds = new DatagramSocket(4125);
			byte[] msg = new byte[1024];
			DatagramPacket dp = new DatagramPacket(msg, 1024);
			ds.receive(dp);
			String str = new String(dp.getData(), 0, dp.getLength());
			System.out.println(str);
			ds.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P21 {
	public static void main(String[] args) {
		try {
			System.out.println("send ...");
			DatagramSocket ds = new DatagramSocket();
			InetAddress ip = InetAddress.getByName("localhost");
			String str = "Hello " + new Date();
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 4125);
			ds.send(dp);
			ds.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P20 {
	static final int port = 5100;

	public static void main(String[] args) {
		Runnable r = () -> P19.main(new String[] {});
		Thread t = new Thread(r);
		t.start();

		String param;
		try {
			if (args.length != 0) {
				param = args[0];
			} else {
				param = "localhost";
			}
			Socket socket = new Socket(param, port);
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			while (true) {
				int ch = in.read();
				if (ch == -1 || ch == '\n' || ch == '\r') {
					break;
				}
				System.out.print((char) ch);
			}
			System.out.println();
			in.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P19 {
	static final int port = 5100;
	static final String msg = "Hello";

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("port= " + port);
			while (true) {
				Socket socket = server.accept();
				send(socket);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void send(Socket client) {
		System.out.println("addr= " + client.getInetAddress());
		Date now = new Date();
		try {
			PrintWriter out = new PrintWriter(client.getOutputStream());
			out.println(msg + " , time= " + now);
			out.flush();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class P18 {
	public static void main(String[] args) {
		String host = "time.nist.gov";
		try {
			Socket socket = new Socket(host, 13);
			InputStream stream = socket.getInputStream();
			StringBuilder sb = new StringBuilder();
			int c;
			while ((c = stream.read()) != -1) {
				sb.append((char) c);
			}
			S.o.l(sb);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class P17 {
	public static void main(String[] args) {
		InetAddress server = null;
		Socket socket = null;
		try {
			server = InetAddress.getByName("whois.twnic.net.tw");
			socket = new Socket(server, 43);
			Writer out = new OutputStreamWriter(socket.getOutputStream(), "8859_1");
			out.write("http://books.gotop.com.tw\r\n");
			out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			String output = "";
			String str;
			while ((str = in.readLine()) != null) {
				output += str + "\n";
			}
			S.o.l("output: " + output);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P16 {
	public static void main(String[] args) {
		String url = "https://www.codota.com/code/java/methods/java.net.HttpURLConnection/setUseCaches";
		try {
			URL u = new URL(url);
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn;
			conn = (HttpURLConnection) u.openConnection();
			S.o.l(conn);
			conn.setRequestMethod("HEAD");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				S.o.l("successful");
				S.o.l(new Date(conn.getLastModified()));
				S.o.l(conn.getContentType());
				S.o.l(conn.getRequestMethod());

				S.o.l(conn.getContent());

				conn.setUseCaches(true);
				Object response = conn.getContent();
				if (response instanceof String) {
					String str = (String) response;
					S.o.l(response);

				}

				InetAddress ip = InetAddress.getByName(u.getHost());
				S.o.l("host: " + ip.getHostName());
				S.o.l("host: " + ip.getHostAddress());
				InetAddress iplocal = InetAddress.getLocalHost();
				S.o.l("local: " + iplocal.getHostName());
				S.o.l("local: " + iplocal.getHostAddress());

			} else {
				S.o.l("failure");
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P15 {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://calendar.google.com/calendar/u/0/r/month?pli=1");
			URLConnection uconn = url.openConnection();
			HttpURLConnection conn = null;
			if (uconn instanceof HttpURLConnection) {
				conn = (HttpURLConnection) uconn;
			} else {
				S.o.l("not http url");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String content = "";
			String read;
			while ((read = br.readLine()) != null) {
				content += read + "\n";
			}
			S.o.l(content);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class P14 {
	public static void main(String[] args) {
		DataInputStream in;
		RandomAccessFile out;
		try {
			URL url = new URL(
					"https://webpac.ilccb.gov.tw/bookSearchList.do?searchtype=simplesearch&execodeHidden=true&execode=&authoriz=1&search_field=FullText&search_input=java&searchsymbol=hyLibCore.webpac.search.common_symbol&keepsitelimit=&csrfToken=624005FE4F87B09611951CBF0B358CB2#searchtype=simplesearch&execodeHidden=true&execode=&authoriz=1&search_field=FullText&search_input=java&searchsymbol=hyLibCore.webpac.search.common_symbol&keepsitelimit=&csrfToken=624005FE4F87B09611951CBF0B358CB2&resid=188841955&nowpage=1");
			// URL url = new URL(
			// "https://vimsky.com/zh-tw/examples/detail/java-method-java.io.RandomAccessFile.seek.html");
			S.o.l(url.toString());
			Path p = Paths.get(url.getPath());
			S.o.l(url.getPath());
			p = p.getFileName();
			S.o.l(p);
			// assert false:"xxx: "+url.
			// URL url2 = new URL( url.getFile());
			// S.o.l(url.toString());
			// S.o.l(url.getPath());
			// *
			in = new DataInputStream(url.openStream());
			String fname = "src\\data\\" + p.toString();
			out = new RandomAccessFile(fname, "rw");
			byte data;
			int readPos = 0;
			while ((data = (byte) in.readByte()) != -1) {
				out.write(data);
			}
			in.close();
			out.close();
			///

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class P13 {
	public static void main(String[] args) {
		MyMath m = (x, y) -> x + y;
		int n = m.test(2, 3, m);
		S.o.l(n);

		int n2 = MyMath.test2(3, 4, (x, y) -> x * y);
		S.o.l(n2);

		String s = MyMath.test3(6, 2, (x, y) -> x / y);
		S.o.l(s);

	}

	interface MyMath {
		int add(int x, int y);

		default int test(int x, int y, MyMath m) {
			return m.add(x, y) * 2;
		}

		static int test2(int x, int y, MyMath m) {
			return m.add(x, y) * 3;
		}

		static String test3(int x, int y, MyMath m) {
			return "xxx=" + m.test(x, y, m) + MyMath.test2(x, y, m);
		}
	}
}

class P12 {
	public static void main(String[] args) {
		Runnable r = () -> S.o.l("Hello");
		new Thread(r).start();
		new Thread(() -> S.o.l("Java")).start();
	}
}

class P11 {
	public static void main(String[] args) {
		P11 p = new P11();
		S.o.l("Thread: " + Thread.currentThread());
		try {
			PipedOutputStream pos = new PipedOutputStream();
			PipedInputStream pis = new PipedInputStream();
			pis.connect(pos);
			// pos2.connect(pis);

			Send send = p.new Send(pos, 5);
			Receive rc = p.new Receive(pis);
			send.start();
			rc.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class Send extends Thread {
		private PipedOutputStream pos;
		private int len;

		public Send(PipedOutputStream pos, int len) {
			this.pos = pos;
			this.len = len;
		}

		public void run() {
			S.o.l(Thread.currentThread() + " start send...");
			try {
				for (int i = 0; i <= len; i++) {
					pos.write(i);
					S.o.l("send " + i);
					this.sleep((int) (Math.random() * 500 + 500));
				}
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			S.o.l("finish send");
		}

	}

	class Receive extends Thread {
		private PipedInputStream pis;
		int sum, data;

		public Receive(PipedInputStream pis) {
			this.pis = pis;
		}

		public void run() {
			S.o.l(Thread.currentThread() + " start receive...");
			sum = 0;
			data = 0;
			try {
				while ((data = pis.read()) != -1) {
					sum += data;
					S.o.l("receive " + data);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			S.o.l("finish receive , and sum =" + sum);
		}
	}
}
