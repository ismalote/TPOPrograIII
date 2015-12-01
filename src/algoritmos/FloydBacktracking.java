package algoritmos;

import TDA.*;

public final class FloydBacktracking {

	public static int INF = 99999;
	
	public static int FloydBT(GrafoDirTDA<Integer> grafo, int origen, int destino, int escala) {
		int res = INF;		
		if (escala == 0){			
			if (grafo.ExisteArista(origen, destino))
				res = grafo.PesoArista(origen, destino);
		}
		else 
		{			
			res = FloydBT(grafo, origen, destino, escala-1);
			ConjuntoTDA<Integer> vert = grafo.Vertices();
			int medio;			
			while (!vert.conjuntoVacio()) {
				medio = vert.elegir();				
				if(medio != origen && medio != destino){			
					res = Math.min(res, FloydBT(grafo, origen, medio, escala - 1) + FloydBT(grafo, medio, destino, escala - 1));					
				}
				vert.sacar(medio);
			}
		}		
		return res;
	}		
}