
public class threads2 {

	public static void main(String[] args) {
		new Thread(thread).start();
			
	}
	
	private static Runnable thread = new Runnable() {
		public void run() {
			int[][] matriz = new int[3][3];
			int[][] container = new int[matriz.length][matriz.length];
			
			matriz[0][0] = 1;
			matriz[0][1] = 2;
			matriz[0][2] = 3;
			
			matriz[1][0] = 4;
			matriz[1][1] = 5;
			matriz[1][2] = 6;
			
			matriz[2][0] = 7;
			matriz[2][1] = 8;
			matriz[2][2] = 9;
			
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					System.out.print(matriz[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("|  Matriz Invertida   |");
			System.out.println();
			
			// Matriz inversa
			/* Não consegui implementar o código da inversao de acordo com a sáida do problema proposto.
			 * Ele está invertendo todos os elementos
			 * da matriz, e não linha por linha, igual do problema proposto:
			 * 	|1 2 3|		|9 8 7|
			 *  |4 5 6| ->  |6 5 4|
			 *  |7 8 9|		|3 2 1|
			 */  
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					container[i][j] = matriz[(matriz.length - i) - 1][(matriz.length - j) - 1];
					System.out.print(container[i][j] + " ");
				}
				System.out.println();
			}
		}
	};

}
