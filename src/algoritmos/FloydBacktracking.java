package algoritmos;

import TDA.ConjuntoTDA;
import TDA.GrafoTDA;
import Implementaciones.*;

public final class FloydBacktracking {

	public static int INF = 99999;
	
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