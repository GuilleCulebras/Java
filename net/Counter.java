package urjc.ist.net;

public class Counter {
	private int	c;
	public synchronized int	incr() {
		return ++c;
	}
}

public class Tweeter implements Runnable {
	private Counter counter;
	public Tweeter(Counter c){
		counter = c;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			System.out.println("Thread "
								+ Thread.currentThread().getId()
								+ " says tweeeeet #"
								+ counter.incr() + " !!!");
		}
	}
}
