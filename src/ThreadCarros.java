import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCarros extends Thread {

	private int sentido;
	private int num_carro;
	private int passagem;;
	private static int pos_chegada, pos_saida;
	private int dist_perc, dist_total;

	// 4 sentidos: Norte, Sul, Leste, Oeste

	private Semaphore semaforo;

	public ThreadCarros(int num_carro, int sentido, Semaphore semaforo) {

		this.sentido = sentido;
		this.num_carro = num_carro;
		this.semaforo = semaforo;
	}

	public void run() {

		Direcao();
		Cruzamento();

		try {
			
			semaforo.acquire();
			//Espera();
			saida();
			
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		finally {
			
			semaforo.release();
		}

	}

	public void Direcao() {

		switch (sentido) {

		case 1:
			System.out.println("Carro " + num_carro + " Sentido: Norte");
			break;

		case 2:
			System.out.println("Carro " + num_carro + " Sentido: Oeste ");
			break;

		case 3:
			System.out.println("Carro " + num_carro + " Sentido: Leste");
			break;

		case 4:
			System.out.println("Carro " + num_carro + " Sentido: Sul ");
			break;

		}

	}

	public void Cruzamento() {

		Random r = new Random();
		int intervalo = 1000;
		dist_perc = 0;
		dist_total = r.nextInt(201) + 100;

		while (dist_perc < dist_total) {

			passagem = r.nextInt(30);
			dist_perc = dist_perc + passagem;
			System.out.println("Carro " + num_carro + " percorreu " + dist_perc + "km/h");

			try {
				Thread.sleep(intervalo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pos_chegada++;
		System.out.println("O Carro " + num_carro + " foi o " + pos_chegada + "º a Chegar ");
	}

	public void Espera() {

		Random r = new Random();
		int tepo_espera = r.nextInt(1000);
		System.out.println("O Carro " + num_carro + " Está Aguardando...");
		try {
			Thread.sleep(tepo_espera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void saida() {
		
		pos_saida++;
		System.out.println("O Carro " + num_carro + " foi o " + pos_saida + " a sair" );
	}

}
