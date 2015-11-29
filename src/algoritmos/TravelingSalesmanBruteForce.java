package algoritmos;

public class TravelingSalesmanBruteForce implements Runnable {

	private Viajero salesman;
	
	private double minCosts;
	
	private int[] minRuta;
	
	private long contador;
	
	public TravelingSalesmanBruteForce(Viajero salesman){
		this.salesman = salesman;
	}

	public void run() {
		int[] ruta = new int[salesman.n];
		minRuta = new int[salesman.n];
		
		minCosts = -1;
		
		contador = 0;
		
		ruta[0] = 0;//first city always zero
		
		for(int i = 1;i < salesman.n;i++){
			ruta[1] = i;
			checkRuta(ruta,2);
		}
		
		System.out.println("Resultado de Fuerza Bruta contador: "+contador+", Costo Minimo: "+minCosts+", ruta: ");
		salesman.printRuta(minRuta);		
	}
	
	private void checkRuta(int[] ruta, int offset){
		
		if(offset == salesman.n){
			contador++;
			
			if(contador%100000 == 0){
				System.out.println("Ruta Chequeada "+contador);
			}
			
			double cost = salesman.calcularCosto(ruta);
			if(minCosts < 0 || cost < minCosts){
				minCosts = cost;
				System.arraycopy(ruta,0,minRuta,0,ruta.length);
			}
			
			return;
		}
		
		loop: for(int i = 1;i<salesman.n;i++){
			for(int j = 0;j<offset;j++){
				if(ruta[j] == i){
					continue loop;
				}
			}
			
			ruta[offset] = i;
			checkRuta(ruta,offset+1);
		}
	}
}
