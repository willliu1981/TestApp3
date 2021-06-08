package designpattern.strategy;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		System.out.println("BigLotto:");
		Lottory biglotto = new BigLotto();
		biglotto.start();

		System.out.println();
		System.out.println("SuperLotto:");
		Lottory superlotto = new Lottory();
		SuperLottoDrawable mydrawable = new SuperLottoDrawable(superlotto);
		superlotto.setDrawable(mydrawable);
		superlotto.start();

	}

}

class BigLotto extends Lottory {

	@Override
	public void draw() {
		int number = 0;
		for (int i = 0; i < 6; i++) {
			while (true) {
				number = this.drawOne(49);
				if (!this.numbers1.contains(number)) {// 影片第25分勘誤處
					this.numbers1.add(number);
					break;
				} else {
					continue;
				}
			}
		}

		while (true) {
			number = this.drawOne(49);
			if (!this.numbers1.contains(number)) {
				this.numbers2.add(number);
				break;
			} else {
				continue;
			}
		}
	}

}

class Lottory {
	public List<Integer> numbers1 = new ArrayList<>();
	public List<Integer> numbers2 = new ArrayList<>();
	private Drawable drawable = new Drawable() {
		@Override
		public void draw() {
			System.out.println("nothing...");
		}
	};

	public Lottory() {
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	protected Integer drawOne(int max_limit) {
		return (int) (Math.random() * max_limit) + 1;
	}

	public void draw() {
		drawable.draw();
	}

	protected String getDrewInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Numbers1: ");
		this.numbers1.stream().sorted().forEach(x -> {
			sb.append(String.format("%d ", x));
		});

		if (this.numbers2.size() > 0) {
			sb.append("\nNumbers2: ");
			this.numbers2.forEach(x -> {
				sb.append(String.format("%d ", x));
			});
		}
		return sb.toString();
	}

	public void start() {
		this.draw();
		System.out.format("%s \n", this.getDrewInfo());
	}

	public void test() {
		System.out.println(this.drawOne(49));

	}

}

class SuperLottoDrawable implements Drawable {
	Lottory lotto;

	/*
	 * 修改為private 或 移除此空建構式,以保證 lotto 不為null 或在 draw 新增條件式,為null 即 return
	 */
	private SuperLottoDrawable() {

	}

	public SuperLottoDrawable(Lottory lotto) {
		this.lotto = lotto;
	}

	@Override
	public void draw() {
		if (this.lotto == null) {
			return;
		}

		int number = 0;
		for (int i = 0; i < 6; i++) {
			while (true) {
				number = lotto.drawOne(38);
				if (!lotto.numbers1.contains(number)) {
					lotto.numbers1.add(number);
					break;
				} else {
					continue;
				}
			}
		}

		number = lotto.drawOne(8);
		lotto.numbers2.add(number);
	}

}

interface Drawable {
	public void draw();
}
