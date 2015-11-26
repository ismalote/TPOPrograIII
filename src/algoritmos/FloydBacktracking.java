package algoritmos;

import TDA.ConjuntoTDA;
import TDA.GrafoTDA;
import Implementaciones.*;

public final class FloydBacktracking {

	public static int INF = 99999;
	
//	public static int floyd(GrafoTDA<Integer> grafo, int origen, int destino, int escala) {
//		int res = 0;
//		
//		if (escala == 0 && grafo.ExisteArista(origen, destino)) {
//			res = grafo.PesoArista(origen, destino);
//		}
//		else if (escala == 0 && !grafo.ExisteArista(origen, destino)) {
//			res = INF;
//		}
//		else {
//			for (int e = escala; e > 0; e--) {
//						
//				// METODO 1: SIN COPIAR EL CONJUNTO.
//				int medio;
//				
//				do {
//					medio = grafo.Vertices().elegir();
//					grafo.Vertices().sacar(medio);
//				} while (!grafo.Vertices().conjuntoVacio() && (medio == origen || medio == destino));
//				
//				
//				res = Math.min(floyd(grafo, origen, destino, e - 1), 
//						floyd(grafo, origen, medio, e - 1) + floyd(grafo, medio, destino, e - 1));
//								
//				// METODO 2: COPIANDO EL CONJUNTO.
//				/*ConjuntoTDA<Integer> vert = new Conjunto<Integer>();
//				vert.inicializarConjunto();
//				CopiarConjunto(grafo.Vertices(), vert);
//				
//				int medio;
//				
//				do {
//					medio = vert.elegir();
//					vert.sacar(medio);
//				} while (!vert.conjuntoVacio() && (medio == origen || medio == destino));
//				
//				
//				// Separe las llamadas para que sea mas facil al debuggear.
//				int llam1 = floyd(grafo, origen, destino, e - 1); 
//				int llam2 =	floyd(grafo, origen, medio, e - 1) + floyd(grafo, medio, destino, e - 1);
//				
//				res = Math.min(llam1, llam2);*/
//			}
//		}
//		
//		return res;
//	}
	
	public static int floyd(GrafoTDA<Integer> grafo, int origen, int destino, int escala) {
		int res = INF;
		
		if (escala == 0 && grafo.ExisteArista(origen, destino)) {
			res = grafo.PesoArista(origen, destino);
		}
		else if (escala == 0 && !grafo.ExisteArista(origen, destino)) {
			res = INF;
		}
		else {
			
			ConjuntoTDA<Integer> vert = new Conjunto<Integer>();
			vert.inicializarConjunto();
			CopiarConjunto(grafo.Vertices(), vert);
			int parcial = INF;
			int medio;
			
			while (!vert.conjuntoVacio()) {
				medio = vert.elegir();
				
				if(medio != origen && medio != destino){			
					parcial = Math.min(floyd(grafo, origen, destino, escala - 1), (floyd(grafo, origen, medio, escala - 1) + floyd(grafo, medio, destino, escala - 1)));
					
					if(parcial < res){
						res = parcial;
					}
				}
				vert.sacar(medio);

			}
		}
		
		return res;
	}

	private static void CopiarConjunto(ConjuntoTDA<Integer> orig, ConjuntoTDA<Integer> dest){
		int x;
		
		ConjuntoTDA<Integer> aux = new Conjunto<Integer>();
		aux.inicializarConjunto();
		
		while(!orig.conjuntoVacio()){
			x = orig.elegir();
			orig.sacar(x);
			aux.agregar(x);
		}
		
		while(!aux.conjuntoVacio()){
			x = aux.elegir();
			aux.sacar(x);
			orig.agregar(x);
			dest.agregar(x);
		}
	}
}




/*
public final class FloydBacktracking{
    public FloydBacktracking() { }
    public static GrafoTDA<Integer> calcularFloyd(GrafoTDA<Integer> G){
        GrafoTDA<Integer> grafoSalida = new Grafo<Integer>();
        grafoSalida.InicializarGrafo();
        ConjuntoTDA<Integer> vertices1 = G.Vertices();
        ConjuntoTDA<Integer> vertices2 = G.Vertices();
        ConjuntoTDA<Integer> vertices3 = G.Vertices();
        while (!vertices1.conjuntoVacio()){
            Integer Ori = vertices1.elegir();
            grafoSalida.AgregarVertice(Ori);
            while (!vertices2.conjuntoVacio()){
                Integer Dest = vertices2.elegir();
                if (Ori != Dest){
                    ConjuntoTDA<Integer> escala = new Conjunto<Integer>();
                    escala.inicializarConjunto();
                    while (!vertices3.conjuntoVacio()){
                        Integer vertice3 = vertices3.elegir();
                        if (vertice3 != Ori && vertice3 != Dest){
                            escala.agregar(vertice3);
                        }
                        vertices3.sacar(vertice3);
                    }
                    grafoSalida.AgregarArista(Ori, Dest, (int)caminoMin(Ori, Dest, escala, G));
                } vertices2.sacar(Dest);
            } vertices1.sacar(Ori);
        }
        return grafoSalida;
    }

    private static Integer caminoMin(Integer Ori, Integer Dest, ConjuntoTDA<Integer> escala, GrafoTDA<Integer> G){
        Integer peso;
        if (escala.conjuntoVacio()){
            peso = Integer.MAX_VALUE;
            if (G.ExisteArista(Ori, Dest) && peso > G.PesoArista(Ori, Dest)){
                peso = G.PesoArista(Ori, Dest);
            }
        } else {
            int valor = escala.elegir();
            escala.sacar(valor);
            peso = Math.min(caminoMin(Ori, Dest, escala, G), caminoMin(Ori, valor, escala, G) + caminoMin(valor, Dest, escala, G));
        }
        return peso;
    }
}
*/