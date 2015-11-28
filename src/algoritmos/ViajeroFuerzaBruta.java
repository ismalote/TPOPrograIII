package algoritmos;

import java.util.*;

public final class ViajeroFuerzaBruta {
	
    private static ArrayList<Integer> mejorRuta;

    public static void encontrarMejorRuta
        (ArrayList<Integer> r, ArrayList<Integer> ciudadesFueraDeRuta)
    {
        if(!ciudadesFueraDeRuta.isEmpty())
        {
            for(int i = 0; i < ciudadesFueraDeRuta.size(); i++)
            {
                Integer removida = (Integer) ciudadesFueraDeRuta.remove(0);
                @SuppressWarnings("unchecked")
				ArrayList<Integer> nuevaRuta = (ArrayList<Integer>) r.clone();
                nuevaRuta.add(removida);

                encontrarMejorRuta(nuevaRuta, ciudadesFueraDeRuta);
                ciudadesFueraDeRuta.add(removida);
            }
        }
        else 
        {
            if(esMejorRuta(r))
                mejorRuta = r;
        }

    }

    private static boolean esMejorRuta(ArrayList<Integer> r) {
        System.out.println(r.toString());
        return false;
    }
}



