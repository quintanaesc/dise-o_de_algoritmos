package mastermind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Juego {

    //Se instancian los HashMaps de respuesta final y su clon.
    public HashMap<Integer, Integer> ans = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> clone = new HashMap<Integer, Integer>();
    ArrayList<Integer> pines = new ArrayList<Integer>();
    ArrayList<Integer> puede = new ArrayList<Integer>();
    ArrayList<Integer> seguros = new ArrayList<Integer>();

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
    public ArrayList comprobar2(HashMap check, int a) {
        boolean[] existen = new boolean[8];
        for (int i = 0; i < existen.length; i++) {
            existen[i] = false;
        }

        if (a <= clone.size()) {
            if (clone.containsValue(check.get(a))) {
                for (int i = 1; i <= clone.size(); i++) {
                    if (clone.get(i) == check.get(i) && existen[(int) check.get(a) - 1] == false) {
                        System.out.print("|  RED  |");
                        clone.replace(i, 0);
                        pines.add(2);
                        existen[(int) check.get(a) - 1] = true;
                        break;
                    }
                }
                for (int i = 1; i <= clone.size(); i++) {
                    if (clone.get(i) == check.get(a) && existen[(int) check.get(a) - 1] == false) {
                        System.out.print("| WHITE |");
                        clone.replace(i, 0);
                        pines.add(1);
                        existen[(int) check.get(a) - 1] = true;
                        break;
                    }
                }
            }

            a++;
            comprobar2(check, a);
        }

        return pines;
    }

    public ArrayList comprobar(HashMap check, int a) {
        boolean[] existen = new boolean[8];
        for (int i = 0; i < existen.length; i++) {
            existen[i] = false;
        }
        while (a <= 4) {
            if (clone.containsValue(check.get(a))) {
                if (clone.get(a) == check.get(a)) {
                    System.out.print("|  RED  |");
                    pines.add(2);
                    existen[(int) check.get(a) - 1] = true;
                } else {
                    System.out.print("| WHITE |");
                    pines.add(1);
                    existen[(int) check.get(a) - 1] = true;
                }
            }
            a++;
        }

        return pines;
    }

    // Este metodo clona la respuesta final para poder comparar la respuesta
    // propuesta por el usuario sin alterar la respuesta final.
    public void newAttempt() {
        clone.put(1, ans.get(1));
        clone.put(2, ans.get(2));
        clone.put(3, ans.get(3));
        clone.put(4, ans.get(4));
        pines.clear();
    }

    public int[] resuelve(int[] anterior, ArrayList<Integer> pines) {
        int[] intento = new int[4];
        int rojos = 0;
        int blancos = 0;
        for (int i = 0; i < pines.size(); i++) {
            if (pines.get(i) == 1) {
                blancos += 1;
            }
            if (pines.get(i) == 2) {
                rojos = 2;
            }
        }
        ArrayList<Integer> noIngre = new ArrayList<Integer>();
        for (int i = 0; i < 8; i++) {
            noIngre.add(i + 1);
        }
        for (int i = 0; i < anterior.length; i++) {
            if (noIngre.contains(i) == false) {
                noIngre.remove((Object) i);
            }
        }

        switch (rojos) {
            case 0:
                switch (blancos) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
                break;
            case 1:
                switch (blancos) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
            case 2:
                switch (blancos) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
                break;
            case 3:
                switch (blancos) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
                break;
            case 4:
                intento = anterior;
                break;
        }

        return intento;
    }
}
