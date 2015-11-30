package algoritmos;

public final class FloydDinamica {
	
	public static int INF = 99999;

	public static void floydMatrices(int[][] matrizAdy) {

		int V = matrizAdy[0].length;

		int dist[][] = new int[V][V];
		int i, j, k;

		// Inicializar la matriz solucion igual que la matriz del grafo.
		for (i = 0; i < V; i++) {
			for (j = 0; j < V; j++) {
				dist[i][j] = matrizAdy[i][j];
			}
		}

		// Añadir vertices uno a uno para establecer los vertices intermedios
		// Al inicio de la iteracion: {0, 1, 2, .. k-1} vertices intermedios
		// Al final de la iteracion: se agrega vertice k
		for (k = 0; k < V; k++) {
			
			// Por cada vertice de origen
			for (i = 0; i < V; i++) {
				
				// Por cada vertice destino de cada vertice de origen
				for (j = 0; j < V; j++) {
					
					// Si el vertice k es el camino mas corto de i a j,
					// actualizar el valor de dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		imprimirSolucion(dist);
		
		return;
	}
	
	private static void imprimirSolucion(int dist[][])
    {
		int V = dist[0].length;
		
        System.out.println("La siguiente matriz muestra el camino mas corto entre cada par de vertices");
        
        for (int i = 0; i < V; ++i)
        {
            for (int j = 0; j < V; ++j)
            {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            
            System.out.println();
        }
    }
}