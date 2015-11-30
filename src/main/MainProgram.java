package main;

import java.io.*;
import java.util.*;

import Implementaciones.*;
import TDA.*;
import algoritmos.*;

public class MainProgram {
	
	public final static int RANDOM_SEED = 1234;
	
	public static Random random = new Random(RANDOM_SEED);
	
	public static void main(String[] args) {
		MainProgram main = new MainProgram();
		main.mostrarLogo();
		main.mostrarOpciones();
	}

	private void mostrarLogo() {
	
		File f = new File("resources/barbaGodio.txt");
		
		try (Scanner scanner = new Scanner(f)) {
	        
    		while (scanner.hasNextLine()) {
    			String line = scanner.nextLine();
    			System.out.println(line);
    		}

    		scanner.close();
		}
		catch (Exception e) {
			// DO NOTHING.
			//e.printStackTrace();
		}
	}
	
	private void mostrarOpciones() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//Imprimo menu de opciones
		System.out.println();
		System.out.println("TPO PROGRAMACION III - GRUPO: LA BARBA DE GODIO");
		System.out.println("------------------------------------------------------------");
		System.out.println("1.- Algoritmo de Floyd: implementacion de backtracking");
		System.out.println("2.- Algoritmo de Floyd: implementacion dinamica con matrices");
		System.out.println("3.- Algoritmo del Viajante: implementacion por fuerza bruta (otro)");
		System.out.println("Q.- Salir");
		System.out.println("------------------------------------------------------------");
		System.out.print("Opcion: ");
		try
		{
			char s = (char)reader.read();
			
		  	switch (s)
		  	{
		  		case '1' : {
		  			this.algoritmoFloydBacktracking();
		  			break;
		  		}
			  	case '2' : {
			  		this.algoritmoFloydDinamica();
			  		break;
			  	}
			  	case '3' : {
			  		this.algoritmoViajante();
			  		break;
			  	}
			  	case 'Q': 
			  	case 'q': {
			  		this.salir();
			  		break;
			  	}
			  	default: {
			  		this.mostrarOpciones();
			  		break;
			  	}
		  	}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void algoritmoFloydBacktracking() {
		System.out.println();
		System.out.println("Algoritmo de Floyd: implementacion de backtracking");
		System.out.println("------------------------------------------------------------");
		System.out.println("Grafo de ejemplo:");
		System.out.println("      10");
		System.out.println(" (0)------->(3)");
		System.out.println("  |         /|\\");
		System.out.println("5 |          |");
		System.out.println("  |          | 1");
		System.out.println(" \\|/         |");
		System.out.println(" (1)------->(2)");
		System.out.println("       3");
		System.out.println("------------------------------------------------------------");
				
		GrafoTDA<Integer> grafo = new Grafo<Integer>();
		grafo.InicializarGrafo();
		grafo.AgregarVertice(0);
		grafo.AgregarVertice(1);
		grafo.AgregarVertice(2);
		grafo.AgregarVertice(3);
		grafo.AgregarArista(0, 1, 5);
		grafo.AgregarArista(1, 2, 3);
		grafo.AgregarArista(2, 3, 1);
		grafo.AgregarArista(0, 3, 10);
        
		int origen = 0;
		int destino = 3;
		int escalas = 4;
		
		int caminoMasCorto = FloydBacktracking.floyd(grafo, origen, destino, escalas);
		
		System.out.printf("El camino mas corto entre el vertice %d y el vertice %d es %d.\n", 
							origen, destino, caminoMasCorto);
		
        this.volver();		
	}

	private void algoritmoFloydDinamica() {
		
		System.out.println();
		System.out.println("Algoritmo de Floyd: implementacion dinamica");
		System.out.println("------------------------------------------------------------");
		System.out.println("Grafo de ejemplo:");
		System.out.println("      10");
		System.out.println(" (0)------->(3)");
		System.out.println("  |         /|\\");
		System.out.println("5 |          |");
		System.out.println("  |          | 1");
		System.out.println(" \\|/         |");
		System.out.println(" (1)------->(2)");
		System.out.println("       3");
		System.out.println("------------------------------------------------------------");
		
		
	     int grafo[][] = { {0,   5,  FloydDinamica.INF, 10},
	                       {FloydDinamica.INF, 0,   3, FloydDinamica.INF},
	                       {FloydDinamica.INF, FloydDinamica.INF, 0,   1},
	                       {FloydDinamica.INF, FloydDinamica.INF, FloydDinamica.INF, 0}
	                     };
	     
	     FloydDinamica.floydMatrices(grafo);
	     
	     this.volver();
	}

	private void algoritmoViajante() {
		
		System.out.println();
		System.out.println("Algoritmo del Viajante: implementacion por fuerza bruta");
		System.out.println("------------------------------------------------------------");
		System.out.println("Costos del viaje:");
		System.out.println("------------------------------------------------------------");
		
		Viajero viajero = new Viajero(10, random);
		
		viajero.imprimirCosto();
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Corriendo el algoritmo:");
		System.out.println("------------------------------------------------------------");
		ViajeroFuerzaBruta fuerzaBruta = new ViajeroFuerzaBruta(viajero);
		fuerzaBruta.run();
       
        this.volver();
	}
	
	private void volver()
	{ 
		System.out.println();
        System.out.println("Presione Q para volver al menu principal...");
        
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try
        {
        	System.out.flush();
        	
        	char s = (char)reader.read();
		  	
        	switch (s)
		  	{
		  		case 'Q' :
		  		case 'q' : {
		  			this.mostrarOpciones();
		  			break;
		  		}
		  		default:
		  			this.volver();
		  			break;
		  	}
        }  
        catch(Exception e)
        {
        	// DO NOTHING.
        }  
	}
	
	private void salir() {
		System.exit(0);
	}
}
