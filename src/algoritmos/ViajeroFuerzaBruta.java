package algoritmos;

public class ViajeroFuerzaBruta {

	private Viajero viajero;	
	private double minCosto;	
	private int[] minRuta;	
	private long contador;
	
	public ViajeroFuerzaBruta(Viajero viajero){
		this.viajero = viajero;
	}

	public void EjecutarFuerzaBruta() {
		int[] ruta = new int[viajero.cantCiudades];
		minRuta = new int[viajero.cantCiudades];		
		minCosto = -1;		
		contador = 0;		
		ruta[0] = 0;	//la primera ciudad es siempre 0		
		for(int i = 1; i < viajero.cantCiudades; i++){
			ruta[1] = i;
			chequearRuta(ruta, 2);
		}		
		System.out.println("------------------------------------------------------------");
		System.out.println("Resultado de Fuerza Bruta:");
		System.out.println("------------------------------------------------------------");
		System.out.print("Intentos: " + contador + " | Costo Minimo: " + minCosto + " | Detalle Ruta: ");
		viajero.imprimirRutaCostoMinimo(minRuta);		
	}
	
	private void chequearRuta(int[] ruta, int offset) {		
		if(offset == viajero.cantCiudades){
			contador++;			
			if(contador % 100000 == 0){
				System.out.println("Ruta Chequeada " + contador);
			}			
			double costo = viajero.calcularCostoRutaActual(ruta, true);			
			System.out.println();			
			if(minCosto < 0 || costo < minCosto){
				minCosto = costo;
				System.arraycopy(ruta, 0, minRuta, 0, ruta.length);
			}			
			return;
		}		
		loop: for(int i = 1; i < viajero.cantCiudades; i++){
			for(int j = 0; j < offset; j++){
				if(ruta[j] == i){
					continue loop;
				}
			}			
			ruta[offset] = i;
			chequearRuta(ruta, offset + 1);
		}
	}
}