package algoritmos;

import java.util.Random;

public class Viajero {

	public final static int MAP_SIZE = 200;		
	private int[][] coordenadas;	
	private int[][] costo;	
	public int cantCiudades;
	boolean[] visitado;

	private static int[][] crearCoordenadasRandom(int n, Random random) {
		int[][] coordenadas = new int[n][2];		
		for (int i = 0; i < coordenadas.length; i++) {
			// Se ignora el caso de 2 ciudades con las mismas coordenadas	
			coordenadas[i][0] = Math.abs(random.nextInt() % MAP_SIZE); 
			coordenadas[i][1] = Math.abs(random.nextInt() % MAP_SIZE); 
		}		
		return coordenadas;
	}

	public Viajero(int n, Random random){
		this(crearCoordenadasRandom(n, random), n);
	}
	
	public Viajero(int[][] coordenadas, int n){
		this.cantCiudades = n;		
		visitado = new boolean[n];
		costo = new int[n][n];		
		this.coordenadas = coordenadas;
		costosPorCoordenadas();
	}	

	// Se crea la matriz de costo al crear coordenadas para las ciudades y usando la distancia de vuelo como costo.
	// Sin embargo, el algoritmo es mas general; matrices de costos arbitrarios deberian funcionar, incluso las asimetricas
	private void costosPorCoordenadas() {
		for(int i = 0; i < coordenadas.length; i++) {
			for(int j = 0; j < coordenadas.length; j++) {
				costo[i][j] = calcularCostoDeViajeEntreCiudades(i, j);
			}
		}
	}

	private int calcularCostoDeViajeEntreCiudades(int i, int j) {
		int dx = coordenadas[i][0] - coordenadas[j][0];
		int dy = coordenadas[i][1] - coordenadas[j][1];		
		return (int)Math.sqrt(dx * dx + dy * dy);    //Pitagoras
	}
	
	public double calcularCostoRutaActual(int[] ruta, boolean detallado) {		
		double costoDeViaje = 0;
		for (int i = 1; i < ruta.length; i++) {
			costoDeViaje += costo[ruta[i-1]][ruta[i]]; 			
			if (detallado) {
				System.out.println("Costo desde Ciudad '" + ruta[i - 1] + "' hasta Ciudad '" + ruta[i] + "': " + costo[ruta[i-1]][ruta[i]]);
			}			
		}
		// volver a la ciudad de origen
		costoDeViaje += costo[ruta[cantCiudades-1]][ruta[0]];
		if(detallado){
			System.out.println("Costo desde Ciudad '" + ruta[cantCiudades - 1] + "' hasta Ciudad '" + ruta[0] + "': " + costo[ruta[cantCiudades-1]][ruta[0]]);
		}
		return costoDeViaje;
	}
	
	public void imprimirRutaCostoMinimo(int[] ruta) {
		for(int i = 0; i < ruta.length; i++){
			System.out.print(ruta[i] + " -> ");
		}
		System.out.println(ruta[0]);
	}
	
	public void imprimirMatrizDeCostosEntreCiudades() {
		System.out.println("Matriz de costo para el problema del viajante de comercio: ");
		System.out.print("<-> ");		
		for(int i = 0; i < costo.length; i++){			
			System.out.print(i + "    ");			
		}
		System.out.println();
		for(int i = 0; i < costo.length; i++) {			
			for(int j = 0; j < costo[i].length; j++) {				
				String espacios = "    ";
				espacios = (costo[i][j] > 9) ? "   " : espacios;
				espacios = (costo[i][j] > 99) ? "  " : espacios; 
				if(j==0)
					System.out.print(i + "   ");
				System.out.print(costo[i][j] + espacios);
			}
			System.out.print("\n");
		}
	}
}