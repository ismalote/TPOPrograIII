package algoritmos;

import TDA.GrafoTDA;

public final class FloydBacktracking {

	public static int INF = 99999;
	
	public static int floyd(GrafoTDA<Integer> grafo, int origen, int destino, int escala) {
		int res = 0;
		
		if (escala == 0 && grafo.ExisteArista(origen, destino)) {
			res = grafo.PesoArista(origen, destino);
		}
		else if (escala == 0 && !grafo.ExisteArista(origen, destino)) {
			res = INF;
		}
		else {
			while (!grafo.Vertices().conjuntoVacio()) {
		
				int medio;
				
				do {
					medio = grafo.Vertices().elegir();
					grafo.Vertices().sacar(medio);
					grafo.EliminarVertice(medio);
				}
				while (medio == origen || medio == destino);
				
				res = Math.min(floyd(grafo, origen, destino, escala - 1), 
					floyd(grafo, origen, medio, escala - 1) + floyd(grafo, medio, destino, escala - 1));
			}
		}
		
		return res;
	}
}
