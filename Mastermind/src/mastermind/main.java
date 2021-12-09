package mastermind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        int[] codigo = {1, 2, 3, 4};
        int[][] intentos= new int[10][4];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                intentos[i][j]=(int)(Math.random()*8+1);
            }
            
        }

        Scanner scanner = new Scanner(System.in);
        Juego juego = new Juego(codigo); // Se inicializa el juego, la respuesta final y los intentos disponibles.
        int attempts = 1;
        ArrayList<Integer> pines = new ArrayList<Integer>();
        HashMap<Integer, Integer> check = new HashMap<Integer, Integer>(); // HashMap en el que se van guardando las respuestas del jugador.

        while (attempts > 0 && attempts <=10) { // Se itera mientras el jugador aun tenga intentos disponibles.
            System.out.println("\n Intento: " + attempts);
            System.out.println("Escribe 4 numeros del 1 al 8::");
            check.put(1, intentos[attempts-1][0]);
            check.put(2, intentos[attempts-1][1]);
            check.put(3, intentos[attempts-1][2]);
            check.put(4, intentos[attempts-1][3]);

            System.out.println(check);

            pines = juego.comprobar(check, 1); //Llamada al metodo que comprueba la respuesta propuesta
            System.out.println("Pines");
            for (int i = 0; i < pines.size(); i++) {
                System.out.println(pines.get(i));           
            }
            // Comprobacion para terminar el juego
            if (check.get(1) != juego.ans.get(1) || check.get(2) != juego.ans.get(2)
                    || check.get(3) != juego.ans.get(3) || check.get(4) != juego.ans.get(4)) {
                attempts += 1;
                juego.newAttempt();
            } else {
                System.out.println("\nFELICIDADES, GANASTE!!");
                System.exit(0);
            }
        }
        System.out.println("\nPERDISTE :(");
    }

}
