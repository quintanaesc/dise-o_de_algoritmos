//En caso de tener una configuracion irresoluble el prograna no funcionara
//Se recomiendan tableros de tamaño 6 o superior para evitar configuraciones irresolubles
//las cordenadas del tablero empiezan desde 0 y tienen que ser menores al tamaño del tablero
int n = 8; // Tamanio del tablero.
int x = 1; // Posicion inicial en 'x'.
int y = 3; // Posicion inicial en 'y'.
int velocidad=2; //velocidad de la animacion (frames por segundo) 
int[][] tablero = new int[n][n]; // Creacion del tablero.
int[][] solucion;
int fr=0;

void setup() {
  size(400, 400);
  tablero[y][x] = 1; // Se asigna el primer movimiento en la casilla inicial.
  solucion = resolver(x, y, n, tablero); // Se invoca al metodo resolver pasando como argumentos la posicion inicial del caballo y el tablero.
}

int[][] movPosibles(int x, int y, int[][] tablero) { // Este metodo recibe la posicion actual del caballo y el tablero. Devuelve una lista de movimientos.
  int contadorLista = 0; // Este contador va a indicar cuantos movimientos son validos.
  int[] movX = {2, 1, 2, 1, -2, -1, -2, -1}; // Se generan dos arreglos, que son los movimientos correspondientes del caballo.
  int[] movY = {1, 2, -1, -2, 1, 2, -1, -2};
  int[][] aux1 = new int[8][2]; // Se crea una matriz. Se van a guardar los movimientos que tiene disponibles el caballo en coordenadas.

  for (int i = 0; i < 8; i++) { // Se itera por el maximo de movimientos que puede tener la ficha.
    if ((x + movX[i] >= 0) && (x + movX[i] < n) && (y + movY[i] >= 0) && (y + movY[i] < n) && tablero[x + movX[i]][y + movY[i]] == 0) { // Se validan los movimientos posibles.
      int[] aux2 = {x + movX[i], y + movY[i]}; //Se crea un arreglo auxiliar que guarda las coordenadas del movimiento que se ha comprobado.
      aux1[contadorLista] = aux2; // Se agregan las coordenadas a nuestra matriz.
      contadorLista++;
    }
  }
  int[][] listaMovimientos = new int[contadorLista][2]; // Se crea el arreglo final de movimientos con la longitud exacta de movimientos posibles.
  for (int i = 0; i < contadorLista; i++) {
    listaMovimientos[i] = aux1[i]; // Se vacian los movimientos en el nuevo arreglo.
  }
  return listaMovimientos;
}

int[][] resolver(int x, int y, int n, int[][] tablero) { // Este metodo recibe la posicion del caballo en 'x' y en 'y', y el tablero
  // nota* se modifico este metodo para que devolviera el resultado
  int contador = 2; // Se inicia el contador de los movimientos del caballo. Este valor es 2 ya que se tiene previsto el siguiente movimiento.
  int xActual = x; // Se establecen las posiciones actuales del caballo en 'x' y en 'y'
  int yActual = y;

  for (int i = 0; i < ((n * n) - 1); i++) { // Se va a iterar el numero de casillas que haya en el tablero menos 1, que es la casilla inicial
    int[][] movPos = movPosibles(xActual, yActual, tablero); // Se invoca el metodo movPosibles, que nos devuelve una lista de movimientos.
    int[] camMin = movPos[0]; // Se obtienen las coordenadas del primer movimiento en nuestra lista.
    for (int j = 0; j < movPos.length; j++) {
      if (movPosibles(movPos[j][0], movPos[j][1], tablero).length <= movPosibles(camMin[0], camMin[1], tablero).length)
        camMin = movPos[j]; // Se busca la mejor opcion para que se mueva el caballo.
    }
    xActual = camMin[0]; // Se asignan la nuevas coordenadas del caballo mediante los valores de nuestro arreglo.
    yActual = camMin[1];
    tablero[xActual][yActual] = contador++; // Se pinta el numero del movimiento mediante el contador en la celda.
  }

  historia(tablero); // Al terminar de resolver se invoca el metodo historia para pintar la respuesta.

  return tablero;
}

void historia(int[][] tablero) {

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      System.out.print(tablero[i][j] + "|");
    }
    System.out.println();
  }
}
void pintaTablero(int [][] casillas) {
  color A=color(255, 255, 255);
  color B=color(0, 0, 0);

  float l=(width-20)/n;
  //casillas
  for (int j=0; j<n; j++) {
    for (int i=0; i<n; i++) {
      if (j%2==0) {
        if (i%2==0) {
          fill(A);
        } else {
          fill(B);
        }
      } else {
        if (i%2==0) {
          fill(B);
        } else {
          fill(A);
        }
      }
      rect(10+l*i, 10+l*j, l, l);
    }
  }
  //numeracion
  for (int j=0; j<n; j++) {
    for (int i=0; i<n; i++) {
      if (j%2==0) {
        if (i%2==0) {
          fill(B);
        } else {
          fill(A);
        }
      } else {
        if (i%2==0) {
          fill(A);
        } else {
          fill(B);
        }
      }
      textSize(l/2);
      text(casillas[i][j], (10+l*i)+(l/4), (10+l*j)+((3*l)/4));
    }
  }
  //numeracion 2
  int [][] listacasillas= listarCasillas(casillas);
  fill(250,0,0);
  float radio=l/2;
  for (int i=0; i<listacasillas.length; i++) {
    if (i==fr) {
      fill(250,0,0);
      circle((10+l*listacasillas[i][0])+((2*l)/4),(10+l*listacasillas[i][1])+((2*l)/4),radio);    
    }
  }
}

int [][] listarCasillas(int [][] casillas) { //funcion para ordenar las casillas del tablero dependiendo de su valor
  int[][] listacasillas= new int[n*n][2];
  for (int i=0; i<casillas.length; i++) {
    for (int j=0; j<casillas[i].length; j++) {
      listacasillas[casillas[i][j]-1][0]=i;
      listacasillas[casillas[i][j]-1][1]=j;
    }
  }
  return listacasillas;
}


void draw() {
  frameRate(velocidad);
  pintaTablero(solucion);
  if (fr<n*n){
    fr++;
  }else{
    fr=0;
  }
  
}
