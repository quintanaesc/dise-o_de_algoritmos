package mastermind;

import java.util.ArrayList;
import java.util.HashMap;

public class Juego {
    //Se instancian los HashMaps de respuesta final y su clon.
    HashMap<Integer, Integer> ans = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> clone = new HashMap<Integer, Integer>();
    ArrayList<Integer> pines = new ArrayList<Integer>();
    
    public Juego(int[] codigo) {
        // Se crea la respuesta final y su clon.
        ans.put(1, codigo[0]);
        ans.put(2, codigo[1]);
        ans.put(3, codigo[2]);
        ans.put(4, codigo[3]);
        System.out.println(ans); // Se imprime la respuesta a la que se quiere llegar. Solo en desarollo, eliminar al terminar.
        newAttempt();
    }
    
    // En este metodo se recibe la respusta del usuario en un HashMap y la llave
    // por la cual se comienza a comprobar, que siempre debe ser '1'. Este
    // metodo incrementa el valor de 'a' y se llama a si mismo para comprobar
    // la siguiente llave.
    public ArrayList comprobar(HashMap check, int a) {
        if (a <= clone.size()) {
            if (clone.containsValue(check.get(a))) {
              if (clone.get(a) == check.get(a)) {
                System.out.print("|  RED  |");
                clone.replace(a, 0);
                pines.add(2);
                } else {
                    for (int i = 1; i <= clone.size(); i++) {
                        if (clone.get(i) == check.get(a)) {
                            System.out.print("| WHITE |");
                            clone.replace(i, 0);
                            pines.add(0, 1);
                            break;
                        }
                    }
                }               
            } 

            a++;
            comprobar(check, a);
        } return pines;
    }
    
    // Este metodo clona la respuesta final para poder comparar la respuesta
    // propuesta por el usuario sin alterar la respuesta final.
    public void newAttempt () {
        clone.put(1, ans.get(1));
        clone.put(2, ans.get(2));
        clone.put(3, ans.get(3));
        clone.put(4, ans.get(4));
    }
}