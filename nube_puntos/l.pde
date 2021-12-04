class Linea{
  Punto a;
  Punto b;
  Linea(Punto a, Punto b){
    this.a=a;
    this.b=b;
  }
  void dibuja(){
    line(this.a.x,this.a.y,this.b.x,this.b.y);
    
  }
}
