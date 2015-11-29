package algoritmos;

import java.util.Random;

public class Viajero {

	public final static int MAP_SIZE = 200;
		
	private int[][] coordenadas;
	
	private double[][] costo;
	
	public int n;

	boolean[] visitado;

	private static int[][] crearCoordenadasRandom(int n, Random random) {
		int[][] coordenadas = new int[n][2];
		
		for (int i = 0; i < coordenadas.length; i++) {

			//we ignore the case that two cities have the same coordenadas - it should work anyway?
			
			coordenadas[i][0] =Math.abs(random.nextInt()%MAP_SIZE); 
			coordenadas[i][1] =Math.abs(random.nextInt()%MAP_SIZE); 
		}
		
		return coordenadas;
	}
	
	public Viajero(int[][] coordenadas){
		this(coordenadas, coordenadas.length);
	}

	public Viajero(int n, Random random){
		this(crearCoordenadasRandom(n, random), n);
	}
	
	public Viajero(int[][] coordenadas, int n){
		this.n = n;
		
		visitado = new boolean[n];
		costo = new double[n][n];
		
		this.coordenadas = coordenadas;

		costosPorCoordenadas();
	}
	
		
	/*	
	 * create costo matrix by creating coordenadas for cities and using flight distance as cost
	 * however, the algorithm is more general than that, arbitrary cost matrices should work, even unsymmetric ones
	 */
	private void costosPorCoordenadas() {
		for(int i = 0;i<coordenadas.length;i++){
			for(int j = 0;j<coordenadas.length;j++){
				costo[i][j] = calcularCostodeViajeentreCiudades(i,j);
			}
		}
	}

	private double calcularCostodeViajeentreCiudades(int i, int j){
		int dx = coordenadas[i][0]-coordenadas[j][0];
		int dy = coordenadas[i][1]-coordenadas[j][1];
		
		return Math.sqrt(dx*dx+dy*dy);//pythagoras
	}

	public double calcularCosto(int[] ruta){
		return calcularCosto(ruta, false);
	}
	
	public double calcularCosto(int[] ruta, boolean isVerbose){
		
		double CostodeViaje = 0;
		for (int i = 1; i < ruta.length; i++) {
			CostodeViaje += costo[ruta[i-1]][ruta[i]]; 
			
			if(isVerbose){
				System.out.println("costo desde "+ruta[i-1]+" hasta "+ruta[i]+": "+costo[ruta[i-1]][ruta[i]]);
			}
			
		}

		//return to starting city
		CostodeViaje += costo[ruta[n-1]][ruta[0]];
		if(isVerbose){
			System.out.println("costo desde "+ruta[n-1]+" hasta "+ruta[0]+": "+costo[ruta[n-1]][ruta[0]]);
		}
		return CostodeViaje;
	}
	
	public void printRuta(int[] ruta){
		for(int i = 0;i<ruta.length;i++){
			System.out.print(ruta[i]+" ");
		}
	}
	
	public void printCosto(){
		System.out.println("matriz de costo para el problema del viajante de comercio :");
		for(int i = 0;i<costo.length;i++){
			for(int j = 0;j<costo[i].length;j++){
				System.out.print(costo[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
}
