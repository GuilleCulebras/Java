package urjc.ist.firstClasses;

public abstract class Vehiculo implements Acondicionado { 
	//Tinene sentido que una clase abstracta implemente una interfaz cuando todas la s clases derivadas de esa clase contengan esa interfaz
	//No se pueden hacer objetos de una clase abstracta
	//no hay que hacer un constructor
	
	private int numRuedas;
	private int potencia;
	private String color;
	
	public abstract boolean arrancar(); //Hay que poner abstract, si no definimos el metodo
	
	public abstract boolean parar();
	
	public String toString(){
		
		String numRuedasString = String.valueOf(numRuedas);
		String potenciaString = String.valueOf(potencia);
		
		return String.join("\n", "----------", 
				"Numero de ruedas: " + numRuedasString,
				"Potencia: " + potenciaString,
				"Color:" + color,
				"----------");
	}
	
}
