import random

class Bolsa:
    w=0
    objetos=[]
    def __init__(self, w,objetos):
        self.w=w
        self.objetos=objetos

def objetosEnBolsas(objetos,W):
    bolsas=[Bolsa] 
    noIngresados=[]
    objetos=sorted(objetos, reverse=True)
    for o in objetos:
        i=0
        ingresado=False
        while i<len(bolsas) and ingresado==False:
            if bolsas[i].w+o<W:
                bolsas[i].objetos.append(o)
                bolsas[i].w+=o
                ingresado=True
            i+=1
        if(ingresado==False and o<=W):
            bolsas.append(Bolsa(o,[o]))
        if(ingresado==False and o>W):
            noIngresados.append(o)
    return bolsas, noIngresados

objetos=[]
for i in range(20):
    objetos.append(random.randint(0,50))
W=100

bolsas, noIngresados=objetosEnBolsas(objetos,W)

print("Para")
print(objetos)
print(f"Se usaron {len(bolsas)} bolsas, con un peso maximo de {W}")
for i in range(len(bolsas)):
    print(f"bolsa {i}: {bolsas[i].objetos}")
print("no se pudieron ingresar")
print(noIngresados)
