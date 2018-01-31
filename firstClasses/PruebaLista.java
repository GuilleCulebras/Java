package urjc.ist.firstClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class PruebaLista {

	public static void main(String[] args) {
		List<Integer> lista = new ArrayList<Integer>();
		Iterator<Integer> iterador;
		int total = 0;
		
		
		
		lista.add(3);
		lista.add(1);
		lista.add(2);
		lista.add(2);
		
		iterador = lista.iterator(); //Siempre hay que ponerla despues de rellenar la lista, no hay que hacer cambios
		
		while(iterador.hasNext())
			total += iterador.next();
			System.out.println(iterador);
//		for(int elem: lista){//Eqivalente al while
//			total += iterador.next();
//		}
		
		System.out.println(lista);
		System.out.println(total);
		
	}

}
