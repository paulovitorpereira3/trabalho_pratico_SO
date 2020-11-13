import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class fcfs {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int n, entrada, tempoAtual;
		double tempoExecucao, tempoEspera, turnaround;
		ArrayList processos, tempoChegada, burst, tempoFinal, tempoInicial;
		int teste = 0;
		String formato, saida;
		DecimalFormat decimal = new DecimalFormat("0.00");
		
		System.out.println("Quatos processos deseja armazenar? ");
		n = scanner.nextInt();
		
		while (n != 0) {
			teste++;
			processos = new ArrayList();
			tempoChegada = new ArrayList();
			burst = new ArrayList();
			tempoFinal = new ArrayList();
			tempoInicial = new ArrayList();
			tempoEspera = 0;
			tempoExecucao = 0;
			turnaround = 0;
			
			for (int i = 1; i <= n; i++) {
				System.out.println("Qual o tempo de chegado do processo P" + i);
				entrada = scanner.nextInt();
				tempoChegada.add(entrada);
				System.out.println("Qual o burst do processo P" + i);
				entrada = scanner.nextInt();
				burst.add(entrada);
				processos.add(i);
			}
			tempoAtual = (int) tempoChegada.get(0);
			
			for (int i = 0; i < n; i++) {
				if ((int) tempoChegada.get(i) > tempoAtual) {
					tempoAtual = (int) tempoChegada.get(i);
				}
				tempoInicial.add(tempoAtual);
				tempoAtual += (int) burst.get(i);
				tempoFinal.add(tempoAtual);
			}
			
			for (int i = 0; i < n; i++) {
				tempoExecucao += (int) tempoFinal.get(i) - (int) tempoChegada.get(i);
				tempoEspera += (int) tempoInicial.get(i) - (int) tempoChegada.get(i);
			}
			System.out.println("PROCESSAMENTO - PARTE" + teste);
			System.out.println("|       ESCALONAMENTO FCFS        |");
			
			for (int i = 0; i < n; i++) {
				turnaround = (int) tempoFinal.get(i) - (int) tempoChegada.get(i);
				formato = decimal.format(turnaround);
				saida = "| Turnaround | P" + i + ": " + formato + " ms";
				saida = saida.replace(". ", ", ");
				System.out.println(saida);
			}
			System.out.println("|----------------------------------");
			tempoExecucao = tempoExecucao / n;
			tempoEspera = tempoEspera / n;
			
			formato = decimal.format(tempoExecucao);
			saida = "Tempo medio de execucao: " + formato + " ms";
			saida = saida.replace(". ", ",");
			System.out.println(saida);
			
			formato = decimal.format(tempoEspera);
			saida = "Tempo medio de espera: " + formato + " ms";
			saida = saida.replace(". ", ",");
			System.out.println(saida);
			
			System.out.println("|----------------GRAFICO DE GANTE -----------");
			for (int i = 0; i < n; i++) {
				System.out.print("P" + processos.get(i) + " ");
			}
			System.out.println();
			System.out.println("Quantos processos dejesa armazenar");
			n = scanner.nextInt();
			
		}	
	}
}
