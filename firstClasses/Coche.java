package urjc.ist.firstClasses;

public class Coche extends Vehiculo {//al ser una subclase de una clase abstracta debemos implementar todos los metodos de la interfaz acondicionado
	
	public boolean arrancado;
	
	
	@Override
	public boolean arrancar(){
		
		arrancado = true;
		return arrancado;
	}

	@Override
	public boolean parar(){
		
		arrancado = false;
		return !arrancado;
	}

}
