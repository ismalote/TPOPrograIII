package algoritmos;

public class ViajeroFuerzaBruta implements Runnable {

	private Viajero viajero;	
	private double minCosto;	
	private int[] minRuta;	
	private long contador;
	
	public ViajeroFuerzaBruta(Viajero viajero){
		this.viajero = viajero;
	}

	public void run() {
		int[] ruta = new int[viajero.n];
		minRuta = new int[viajero.n];		
		minCosto = -1;		
		contador = 0;		
		ruta[0] = 0;	//la primera ciudad es siempre 0		
		for(int i = 1; i < viajero.n; i++){
			ruta[1] = i;
			chequearRuta(ruta, 2);
		}		
		System.out.println("Resultado de Fuerza Bruta -> Intentos: " + contador + ", Costo Minimo: " + minCosto + ", ruta: ");
		viajero.imprimirRuta(minRuta);		
	}
	
	private void chequearRuta(int[] ruta, int escala) {		
		if(escala == viajero.n){
			contador++;			
			if(contador % 100000 == 0){
				System.out.println("Ruta Chequeada " + contador);
			}			
			double costo = viajero.calcularCosto(ruta);
			if(minCosto < 0 || costo < minCosto){
				minCosto = costo;
				System.arraycopy(ruta, 0, minRuta, 0, ruta.length);
			}			
			return;
		}		
		loop: for(int i = 1; i < viajero.n; i++){
			for(int j = 0; j < escala; j++){
				if(ruta[j] == i){
					continue loop;
				}
			}			
			ruta[escala] = i;
			chequearRuta(ruta, escala + 1);
		}
	}
}