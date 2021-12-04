int n;
Punto esquinas[]= new Punto[4];
Punto cierre[]= new Punto[0];
Punto p[];
Punto pxm, pxM, pym, pyM;
Linea lineas[]=new Linea[0];

void setup() {
  size(800, 600);
  background(255);
  n=20;
  p=new Punto[n];
  for (int i=0; i<n; i++) {
    p[i]=new Punto(random(800), random(600));
  }
  pxm= new Punto(width, 0);
  pxM= new Punto(0, 0);
  pym= new Punto(0, height);
  pyM= new Punto(0, 0);

  for (int i=0; i<p.length; i++) {
    if (p[i].x<pxm.x) {
      pxm=p[i];
    }
    if (p[i].x>pxM.x) {
      pxM=p[i];
    }
    if (p[i].y<pym.y) {
      pym=p[i];
    }
    if (p[i].y>pyM.y) {
      pyM=p[i];
    }
  }
  esquinas[0]=pxm;
  esquinas[1]=pxM;
  esquinas[2]=pym;
  esquinas[3]=pyM;

  anadeCierre(pxm);
  anadeCierre(pxM);
  
  Punto actual=pxm;
  Punto menorIz;
  while (actual != pym) {
    menorIz=menorIz(actual);
    if (actual != pym) {
      anadeCierre(menorIz);
      añadeLinea(menorIz,actual);
    }
    actual=menorIz;
  }
  
  actual=pxm;
  Punto mayorIz;
  while (actual != pyM) {
    mayorIz=mayorIz(actual);
    if (actual != pyM) {
      anadeCierre(mayorIz);
      añadeLinea(mayorIz,actual);
    }
     actual=mayorIz;
  }
  
  int i=0;
  actual=pxM;
  Punto menorDe;
  while (actual != pym) {
    menorDe=menorDe(actual);
    if (actual != pym) {
      anadeCierre(menorDe);
      añadeLinea(menorDe,actual);
    }
    actual=menorDe;
  }
  
  actual=pxM;
  Punto mayorDe;
  while (actual != pyM) {
    mayorDe=mayorDe(actual);
    if (actual != pyM) {
      anadeCierre(mayorDe);
      añadeLinea(mayorDe,actual);
    }
    actual=mayorDe;
  }
  
}

void anadeCierre(Punto a) {
  Punto aux[]=new Punto[cierre.length+1];
  for (int i=0; i<cierre.length; i++) {
    aux[i]=cierre[i];
  }
  aux[cierre.length]=a;
  cierre=aux;
}

void añadeLinea(Punto a, Punto b){
  Linea aux[]= new Linea[lineas.length+1];
  for(int i=0; i<lineas.length; i++){
    aux[i]=lineas[i];
  }
  Linea nueva=new Linea(a,b);
  aux[lineas.length]=nueva;
  lineas=aux;
}

Punto menorIz(Punto a) {
  Punto menor;
  Punto aux=a;
  float pen=0;
  for (int i=0; i<p.length; i++) {
    if (a.x<p[i].x) {
      if (pendiente(a, p[i])<pen) {
        pen=pendiente(a, p[i]);
        aux=p[i];
      }
    } 
    else if (p[i].y<a.y) {
      aux=p[i];
      pen=99999999;
    }
  }
  menor=aux;
  return menor;
}

Punto mayorIz(Punto a) {
  Punto mayor;
  Punto aux=a;
  float pen=0;
  for (int i=0; i<p.length; i++) {
    if (a.x<p[i].x) {
      if (pendiente(a, p[i])>pen) {
        pen=pendiente(a, p[i]);
        aux=p[i];
      }
    } 
    else if (p[i].y>a.y) {
      aux=p[i];
      pen=99999999;
    }
  }
  mayor=aux;
  return mayor;
}

Punto menorDe(Punto a) {
  Punto menor;
  Punto aux=a;
  float pen=0;
  for (int i=0; i<p.length; i++) {
    if (a.x>p[i].x) {
      if (pendiente(a, p[i])>pen) {
        pen=pendiente(a, p[i]);
        aux=p[i];
      }
    } 
    else if (p[i].y<a.y) {
      aux=p[i];
      pen=99999999;
    }
  }
  menor=aux;
  return menor;
}

Punto mayorDe(Punto a) {
  Punto mayor;
  Punto aux=a;
  float pen=0;
  for (int i=0; i<p.length; i++) {
    if (a.x>p[i].x) {
      if (pendiente(a, p[i])<pen) {
        pen=pendiente(a, p[i]);
        aux=p[i];
      }
    } 
    else if (p[i].y>a.y) {
      aux=p[i];
      pen=99999999;
    }
  }
  mayor=aux;
  return mayor;
}

float pendiente(Punto a, Punto b) {
  float pendiente;
  pendiente=(a.y-b.y)/(a.x-b.x);
  return pendiente;
}

void draw() {
  for (int i=0; i<n; i++) {
    p[i].dibuja(0, 0, 0);
  }
  for (int i=0; i<cierre.length; i++) {
    cierre[i].dibuja(0, 250, 0);
  }

  for (int i=0; i<4; i++) {
    esquinas[i].dibuja(250, 0, 0);
  }
  for (int i=0; i<lineas.length; i++) {
    lineas[i].dibuja();
  }
}
