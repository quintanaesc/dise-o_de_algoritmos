class Punto{
  float x;
  float y;

  Punto(float a, float b){
    x=a;
    y=b;  
  }
  void dibuja(int a, int b, int c){
    fill(a,b,c);
    circle(x,y,10);
  }
}
