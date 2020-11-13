import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class sjf {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N, entrada;
		ArrayList processos, chegada, cpchegada = new ArrayList(), burst;
		int[] temposFinais = new int[1], temposIniciais = new int[1];
		int idProcessoAtual;
		String ordemExecucao = "", formato, saida;
		double tempoEspera, tempoExecucao, turnaround;
		int contTeste = 0;
		DecimalFormat decimal = new DecimalFormat("0.00");
		
		System.out.println("Quantos processos deseja armazenar?");
		N = scanner.nextInt();
		
		while (N != 0) {
			contTeste++;
			
			ordemExecucao = "";
			processos = new ArrayList();
			chegada = new ArrayList();
			burst = new ArrayList();
			temposFinais = new int [N];
			temposIniciais = new int[N];
			
			for(int i = 0; i < N; i++) {
				System.out.println("Tempo de chegada do P" + (i+1));
				entrada = scanner.nextInt();
				chegada.add(entrada);
				System.out.println("Tempo de burst do P" + (i+1));
				entrada = scanner.nextInt();
				burst.add(entrada);
			}
			cpchegada = (ArrayList) burst.clone();
			int execucao;
			int qteprocessos = N;
			int tempoAtual = (int) chegada.get(0);
			while (qteprocessos > 0) {
				processos = new ArrayList();
				for (int i = 0; i < N; i++) {
					if((int) chegada.get(i) != -1 && (int) chegada.get(i) <= tempoAtual) {
						processos.add(i);
					}
				}
				if (processos.isEmpty()) {
					tempoAtual++;
				}else {
					execucao = (int) processos.get(0);
					for (int i = 0; i < processos.size(); i++) {
						idProcessoAtual = (int) processos.get(i);
						if ((int) burst.get(idProcessoAtual) < (int) burst.get(execucao)) {
							execucao = (int) processos.get(i);
						}
					}
					temposIniciais[execucao] = tempoAtual;
					tempoAtual += (int) burst.get(execucao);
					temposFinais[execucao] = tempoAtual;
					chegada.set(execucao, -1);
					ordemExecucao += "P" + (execucao + 1) + " ";
					qteprocessos--;
				}
			}
			tempoExecucao = 0;
			tempoEspera = 0;
			for (int i = 0; i < N; i++) {
				tempoExecucao += temposFinais[i] - (int)cpchegada.get(i);
				tempoEspera += temposIniciais[i] - (int)cpchegada.get(i);
			}
			System.out.println("PROCESSAMENTO - PARTE" + contTeste);
			for (int i = 0; i < N; i++) {
				turnaround = (int)temposFinais[i] - (int)cpchegada.get(i);
				formato = decimal.format(turnaround);
				saida = "|Turnaround| P" + i + ": " + formato + "ms";
				saida = saida.replace(".",",");
				System.out.println(saida);
			}
			
			tempoExecucao = tempoExecucao / N;
			tempoEspera = tempoEspera /N;
			
			formato = decimal.format(tempoExecucao);
			saida = "Tempo medio de execucao: " + formato + "s";
			saida = saida.replace(".",",");
			System.out.println(saida);
			
			formato = decimal.format(tempoEspera);
			saida = "Tempo medio de espera: " + formato + "s";
			saida = saida.replace(".", ",");
			System.out.println(saida);
			
			System.out.println(ordemExecucao);
			System.out.println();
			System.out.println("Deseja armazenar quantos processadores?");
			N = scanner.nextInt();
		}

	}

}
