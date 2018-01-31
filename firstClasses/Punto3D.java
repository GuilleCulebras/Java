package urjc.ist.firstClasses;

import java.util.ArrayList;

public class Punto3D extends Punto {
	
	private int z;

	public Punto3D() {
		z = 0;
	}
	
	public Punto3D(int z){
		this.z = z;
	}
	
	public static void sumarPuntos(ArrayList<? extends Punto> arr){
		
	}

	public static void main(String[] args) {
		ArrayList<Punto3D> coordenadas =  new ArrayList<Punto3D>();
		
		Punto3D punto1 = new Punto3D(3);
		Punto3D punto2 = new Punto3D(4);
		
		coordenadas.add(punto1);
		coordenadas.add(punto2);
		
		Punto3D.sumarPuntos(coordenadas);
		

	}

}
