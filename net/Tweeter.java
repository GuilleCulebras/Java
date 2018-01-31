package urjc.ist.net;

public class Tweeter implements Runnable{

	public void run() {
		for	(int i = 0; i < 10; i++)
			System.out.println("Thread "
					+ Thread.currentThread().getId()
					+  " State "
					+ Thread.currentThread().getState());
		}

	public static void main(String[] args) {
		for	(int i = 0 ; i < 10; i++)
			(new Thread(new Tweeter())).start();
	}

}

