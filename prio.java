import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class prio {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N;
		int entrada;
		int tempoAtual;
		int execucao;
		int idProcessoAtual;
		int qteProcessos;
		ArrayList entrada_1;
		ArrayList burst;
		ArrayList prioridades;
		ArrayList processos;
		ArrayList cpEntrada;
		int[] temposFinais;
		int[] temposIniciais;
		String ordemExecucao;
		int contTeste = 0;
		String formato;
		String saida;
		DecimalFormat decimal = new DecimalFormat("0.00");
		
		System.out.println("Quantos processadores deseja armazenar?");
		N = scanner.nextInt();
		
		while (N != 0) {
			contTeste++;
			processos = new ArrayList();
			entrada_1 = new ArrayList();
			burst = new ArrayList();
			prioridades = new ArrayList();
			
			for(int i = 0; i < N; i++) {
				System.out.println("Entrada do processo P" + (i+1));
				entrada = scanner.nextInt();
				entrada_1.add(entrada);
				System.out.println("Qual o burst do P" + (i+1));
				entrada = scanner.nextInt();
				burst.add(entrada);
				System.out.println("Qual a prioridade do P" + (i+1));
				entrada = scanner.nextInt();
				prioridades.add(entrada);
			}
			temposIniciais = new int[N];
			temposFinais = new int[N];
			cpEntrada = (ArrayList) entrada_1.clone();
			ordemExecucao = "";
			tempoAtual = (int) entrada_1.get(0);
			
			qteProcessos = N;
			while (qteProcessos > 0) {
				processos = new ArrayList();
				for (int i = 0; i < N; i++) {
					if ((int) entrada_1.get(i) != -1 && (int)entrada_1.get(i) <= tempoAtual) {
						processos.add(i);
					}
				}
				
				if (processos.isEmpty()) {
					tempoAtual++;
				}else {
					execucao = (int) processos.get(0);
					for (int i = 0; i < processos.size(); i++) {
						idProcessoAtual = (int)processos.get(i);
						if ((int)prioridades.get(idProcessoAtual) < (int)prioridades.get(execucao)) {
							execucao = (int)processos.get(i);
						}
					}
					temposIniciais[execucao] = tempoAtual;
					tempoAtual += (int) burst.get(execucao);
					temposFinais[execucao] = tempoAtual;
					entrada_1.set(execucao, -1);
					ordemExecucao += "P" + (execucao + 1) + " ";
					qteProcessos--;
				}
			}
			double tempoExecucao = 0;
			double tempoEspera = 0;
			for (int i = 0; i < N; i++) {
				tempoExecucao += temposFinais[i] - (int)cpEntrada.get(i);
				tempoEspera += temposIniciais[i] - (int)cpEntrada.get(i);
			}
			
			tempoExecucao = tempoExecucao / N;
			tempoEspera = tempoEspera / N;
			System.out.println("PROCESSAMENTO - PARTE " + contTeste);
			
			formato = decimal.format(tempoExecucao);
			saida = "Tempo medio de execucao: " + formato + "s";
			saida = saida.replace(".", ",");
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
