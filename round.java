import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class round {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int quantum;
		int N;
		int entrada;
		int tempoAtual;
		int execucao;
		int q;
		int temposFinais[];
		int quantidadeProcessos;
		int burstNovo;
		int temposExecucao[];
		ArrayList chegada;
		ArrayList burst;
		ArrayList processos;
		ArrayList cpchegada;
		ArrayList cpburst;
		String ordem;
		double tempoMedioExecucao;
		double tempoMedioEspera;
		double turnaround;
		int contTeste = 0;
		String formato;
		String saida;
		DecimalFormat decimal = new DecimalFormat("0.00");
		
		System.out.println("Quantos processadores deseja armazenar?");
		N = scanner.nextInt();
		
		System.out.println("Qual o valor do quantun?");
		quantum = scanner.nextInt();
		
		while (N != 0) {
			contTeste++;
			processos = new ArrayList();
			chegada = new ArrayList();
			burst = new ArrayList();
			ordem = "";
			quantidadeProcessos = N;
			temposFinais = new int[N];
			temposExecucao = new int[N];
			
			for(int i = 0; i < N; i++) {
				System.out.println("Qual o tempo de chegado do P" + (i+1));
				entrada = scanner.nextInt();
				chegada.add(entrada);
				System.out.println("Qual o burst do P" + (i+1));
				entrada = scanner.nextInt();
				burst.add(entrada);
			}
			
			cpchegada = (ArrayList) chegada.clone();
			cpburst = (ArrayList) burst.clone();
			tempoAtual = (int) chegada.get(0);
			processos = new ArrayList();
			processos = new ArrayList();
			
			while (quantidadeProcessos > 0) {
				for (int i = 0; i < N; i++) {
					if ((int) chegada.get(i) != -1 && (int) chegada.get(i) <= tempoAtual) {
						processos.add(i);
						chegada.set(i, -1);
					}
				}
				if (processos.isEmpty()) {
					tempoAtual++;
				} else {
					execucao = (int) processos.remove(0);
					ordem += "P" + (execucao + 1) + " ";
					q = quantum;
					
					while (q > 0 && (int) burst.get(execucao) > 0) {
						tempoAtual++;
						q--;
						burstNovo = (int) burst.get(execucao) -1;
						burst.set(execucao, burstNovo);
					}
					
					if ((int) burst.get(execucao) > 0) {
						for (int i = 0; i < N; i++) {
							if ((int) chegada.get(i) != -1 && (int) chegada.get(i) <= tempoAtual) {
								processos.add(i);
								chegada.set(i, -1);
							}
						}
						processos.add(execucao);
					} else {
						temposFinais[execucao] = tempoAtual;
						quantidadeProcessos--;
					}
				}
			}
			tempoMedioExecucao = 0;
			tempoMedioEspera = 0;
			for (int i = 0; i < N; i++) {
				temposExecucao[i] = temposFinais[i] - (int) cpchegada.get(i);
				tempoMedioExecucao += temposExecucao[i];
				tempoMedioEspera += temposExecucao[i] - (int) cpburst.get(i);
			}
			
			tempoMedioExecucao = tempoMedioExecucao / N;
			tempoMedioEspera = tempoMedioEspera / N;
			System.out.println("PROCESSAMENTO - PARTE " + contTeste);
			
			for (int i = 0; i < N; i++) {
				turnaround = (int) temposFinais[i] - (int) cpchegada.get(i);
				formato = decimal.format(turnaround);
				saida = "|Turnaround | P" + i + ": " + formato + "ms";
				saida = saida.replace(".", ",");
				System.out.println(saida);
			}
			
			formato = decimal.format(tempoMedioExecucao);
			saida = "Tempo medio de execucao: " + formato + "s";
			saida = saida.replace(".", ",");
			System.out.println(saida);
			
			formato = decimal.format(tempoMedioEspera);
			saida = "Tempo medio de espera: " + formato + "s";
			saida = saida.replace(".", ",");
			System.out.println(saida);
			
			System.out.println(ordem);
			System.out.println();
			System.out.println("Deseja armazenar quantos processos?");
			N = scanner.nextInt();
			
			
		}

	}

}
