package java9;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Sample3 {
	public static void main(String[] args) throws Exception {
		Practice.get("src\\java9").sufnameMax(30).run();
	}
}

class Pb {
	public static void main(String[] args) {

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
			//pos2.connect(pis);


			Send send = p.new Send(pos, 5);
			Receive rc =p. new Receive(pis);
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
