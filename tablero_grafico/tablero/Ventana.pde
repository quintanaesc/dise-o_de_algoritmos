Tablero t;


void setup() {
  size(400, 400);
  t = new Tablero(8, 4, 4);
}


void draw() {
 t.pintaTablero(color(255, 255, 255), color(0, 0, 0));
}
