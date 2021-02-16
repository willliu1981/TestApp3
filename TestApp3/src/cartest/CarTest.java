package cartest;

public class CarTest {
	public static void main(String[] args) {
		Car car = new Car();// 新車子
		car.dashboard.setEnable();// 嚐試註解這一行
		car.dashboard.accelerate();
		// car.dashboard.accelerate();//第二次摧油
		System.out.println(car.getMileageMsg());
	}
}

//車子
class Car {
	private static final int Type_Auto = 0;// 自動
	private static final int Type_Manual = 1;// 手排
	Dashboard dashboard;// 儀表板
	Engine engine;// 引擎
	Transmission transmission;// 轉動裝置

	// 創建匿名類繼承Transmission的子類陣列
	private static final Transmission[] transmissiontypes = { new Transmission() {
		@Override
		public void run(boolean getPower) {
			super.run(getPower);
			if (getPower) {
				System.out.println("  -自動換擋,持續加速,並行駛了1公里  (匿名子類 Transmission.run)");
			}
		}
	}, new Transmission() {
		@Override
		public void run(boolean getPower) {
			super.run(getPower);
			if (getPower) {
				System.out.println("  -手動連續換擋加速,並行駛了1公里  (匿名子類 Transmission.run)");
			}
		}
	} };

	// Car建構式,創建各部零件
	public Car() {
		/*
		 * 臨時創建一個匿名類繼承原Car的子類, 覆寫了setEnable方法,使它能在Car類的Engine物件啟動引擎
		 * 覆寫了accelerate方法,使它能在Car類的Engine物件加速
		 */
		dashboard = new Dashboard() {
			@Override
			public void setEnable() {
				super.setEnable();
				System.out.println("  -點燃引擎...  (匿名子類 Dashboard.setEnable)");
				engine.setEnable();
			}

			@Override
			public void accelerate() {
				super.accelerate();
				System.out.println(
						"  -引擎" + (engine.getEnable() ? "加足了馬力..." : "...沒有動靜") + "  (匿名子類 Dashboard.accelerate)");
				engine.output();
			}
		};

		/*
		 * 臨時創建一個匿名類繼承Engine的子類
		 */
		engine = new Engine() {
			@Override
			public boolean output() {
				boolean res = super.output();// 取得父類的引擎是否工作
				System.out.println("  -傳遞能量..." + (res ? "正常傳輸中..." : "沒有能量!") + "  (匿名子類 Dashboard.output)");
				transmission.run(res);
				return res;
			}
		};

		/*
		 * 多型方法創建匿名類繼承Transmission的子類 嚐試切換註解以下兩行
		 */
		transmission = transmissiontypes[Type_Auto];
		// transmission = transmissiontypes[Type_Manual];

	}

	// 獲取里程數資訊
	public String getMileageMsg() {
		return "車子共跑了 " + this.transmission.getMileage() + " 公里  (Car.getMileageMsg)";
	}

	// 顯示車子狀態
	public void showStatus() {
		System.out.format("鑰匙開關:%s 引擎運轉:%s 里程數:%d", this.dashboard.getEnable(), this.engine.getEnable(),
				this.transmission.getMileage());
	}
}

//儀表板,包含操控油門,方向盤等等
class Dashboard {
	private boolean enable = false;

	public boolean getEnable() {
		return this.enable;
	}

	public void setEnable() {
		System.out.println("轉動開關! (Dashboard.setEnable)");
		enable = true;
	}

	// 摧油門
	public void accelerate() {
		System.out.println("油門催落去! (Dashboard.accelerate)");
		// doSomething...
	}
}

//引擎
class Engine {
	private boolean enable = false;

	public boolean getEnable() {
		return this.enable;
	}

	public void setEnable() {
		System.out.println("  -引擎開始運轉!  (Engine.setEnable)");
		enable = true;
	}

	// 輸出動力
	public boolean output() {
		System.out.println("  -輸出動力..." + (this.enable == true ? "動力充足!" : "沒有動力!") + "  (Engine.output)");
		return this.enable;
	}
}

//傳動裝置
class Transmission {
	private Counter counter = new Counter();

	// 獲得動力,開始奔馳
	public void run(boolean getPower) {
		if (getPower) {
			System.out.println("  -接收動力...車子向前移動...  (Transmission.run)");
			counter.add();
		}
	}

	// 獲取里程數
	public int getMileage() {
		return this.counter.getMileage();
	}
}

//計數器
class Counter {
	private int trip;// 我家的摩拕車多了這一個
	private int mileage;

	public void add() {
		this.trip++;
		this.mileage++;
	}

	// 獲取里程數
	public int getMileage() {
		return this.mileage;
	}
}
