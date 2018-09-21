import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {

		int carros = 1;
		Semaphore sem = new Semaphore(carros);

		for (int i = 1; i <= 4; i++) {

			Thread thread = new ThreadCarros(i, i, sem);
			thread.start();
		}

	}

}
