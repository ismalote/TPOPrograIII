package algoritmos;

import Implementaciones.*;
import TDA.*;

public final class FloydDinamica {
	
	public static GrafoDirTDA <Integer> floyd(GrafoDirTDA <Integer> g) {
		ConjuntoTDA <Integer> conjuntoI, conjuntoJ, conjuntoK;
		int i, j, k;
		GrafoDirTDA <Integer> r = new GrafoDir < Integer > ();
		r.InicializarGrafo();
		// Copio el grafo original
		conjuntoK = g.Vertices();
		while (!conjuntoK.conjuntoVacio()) {
			k = conjuntoK.elegir();
			conjuntoK.sacar(k);
			r.AgregarVertice(k);
		}

		conjuntoK = g.Vertices();
		while (!conjuntoK.conjuntoVacio()) {
			k = conjuntoK.elegir();
			conjuntoK.sacar(k);
			conjuntoI = g.Adyacentes(k);
			while(!conjuntoI.conjuntoVacio()) {
				i = conjuntoI.elegir();
				conjuntoI.sacar(i);
				r.AgregarArista(k, i, g.PesoArista(k, i));
			}
		}

		conjuntoK = g.Vertices();
		while (!conjuntoK.conjuntoVacio()) {
			k = conjuntoK.elegir();
			conjuntoK.sacar(k);
			conjuntoI = g.Vertices();
			conjuntoI.sacar(k);
			while (!conjuntoI.conjuntoVacio()) {
				i = conjuntoI.elegir();
				conjuntoI.sacar(i);
				if (r.ExisteArista(i, k)) {
					conjuntoJ = r.Adyacentes(k);
					conjuntoJ.sacar(i);
					while (!conjuntoJ.conjuntoVacio()) {
						j = conjuntoJ.elegir();
						conjuntoJ.sacar(j);
						if (r.ExisteArista(i, j)) {
							if (r.PesoArista(i, k) + r.PesoArista(k, j) < r.PesoArista(i, j)) {
								r.AgregarArista(i, j, r.PesoArista(i, k) + r.PesoArista(k, j));
								}
						} else {
							r.AgregarArista(i, j, r.PesoArista(i, k) + r.PesoArista(k, j));
						}
					}
				}
			}
		}
		return r;
	}
}
