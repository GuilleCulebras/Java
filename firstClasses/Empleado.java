package urjc.ist.firstClasses;


/*
 *  Aquí importamos los paquetes o clases externas que necesitemos usar dentro
 *  de nuestra clase
 */

public class Empleado {

	/*
	 *  Tras la definición de clase declaramos constantes, si son necesarias
	 */
	
	private final double MAX_SALARIO = 90000.;
	
	/* 
	 * Después van los atributos o campos (fields) que tendrán
	 * cada uno de los objetos de la clase Empleado 
	 */
	
	private String nombre;
	private String apellidos;
	private String DNI;
	private double salario;
	
	/*
	 * A continuación van los métodos constructores de objetos de esa clase.
	 * El nombre es igual que el de la clase, pero se pueden definir diferentes
	 * versiones que acepten distintos argumentos
	 * 
	 * El primero es el constructor por defecto, que no recibe ningún argumento
	 * e inicializa los atributos del objeto a valores iniciales por defecto.
	 * 
	 * Si no implementamos **ningún constructor** en nuestras clases, entonces
	 * se hereda automáticamente el constructor por defecto de java.lang.Object.
	 * 
	 * Sin embargo, basta con que tan solo implementemos un método constructor de
	 * nuestra clase (el por defecto u otra versión que reciba argumentos) para que
	 * ya no sea visible el constructor por defecto heredado de Object.
	 */
	
	public Empleado() {
		/**
		 * Este es un ejemplo de constructor por defecto de mi clase
		 */
		
		nombre = "";
		apellidos = "";
		DNI = "";
		salario = 0;
	}
	
	public Empleado(String unNombre, String unosApellidos, String unDNI, double unSalario) {
		/**
		 * Este es un ejemplo de constructor que acepta argumentos
		 */
		nombre = unNombre;
		apellidos = unosApellidos;
		DNI = unDNI;
		salario = unSalario;
	}
	
	/*
	 * Después de los constructores se suelen poner los métodos de acceso y modificación de
	 * los atributos, llamados getters y setters (respectivamente). No obstante, hay
	 * desarrolladores que prefieren poner estos métodos al final de la clase.
	 */
	
	// INSERTA AQUÍ EL MÉTODO DE ACCESO AL NOMBRE
	
	public String getName(){
		
		return nombre;
	}
	
	// INSERTA AQUÍ EL MÉTODO DE MODIFICACIÓN DEL NOMBRE	
	public String setName(String nombre){
		
		this.nombre= nombre;
		return nombre;		
	}
	// INSERTA AQUÍ EL MÉTODO DE ACCESO AL APELLIDO
	
	public String getSurname(){
		
		return apellidos;
	}
	// INSERTA AQUÍ EL MÉTODO DE MODIFICACIÓN DEL APELLIDO
	public String setSurname(String apellidos){
		
		this.apellidos= apellidos;
		return apellidos;
	}
	
	public String getDNI() {
		/**
		 * Método de acceso al DNI
		 */
		
		return DNI;
	}
	
	public boolean setDNI(String newDNI) {
		/**
		 * El método debe hacer una mínima comprobación sobre la validez
		 * del nuevo ID: verificar que el últio caracter no es numérico y
		 * que la longitud total es de 9 caracteres (con 0 a la izquierda de
		 * relleno si es necesario) 
		 */
	
		
		if(newDNI.length()==9 && newDNI.matches(".*[A-Z]")){
			
			DNI = newDNI;
			return true;
			
		}else if(newDNI.length()<9 && newDNI.matches(".*[A-Z]")){
			
			while(newDNI.length()<9){
				newDNI= "0" + newDNI;
			}
			DNI = newDNI;
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean subirSalario(double cantidad) {
		/**
		 * No se podrá tener un salario superior a 90.000 euros
		 */
		
		if (salario + cantidad <= MAX_SALARIO) {
			salario += cantidad;
			return true;
		}
		else {
			// Más adelante veremos cómo lanzar errores (llamados excepciones)
			return false;
		}
	}
	
	public String toString() {
		/**
		 * Sobreescribimos el método genérico toString de java.lang.Object
		 * para mostrar información útil sobre el empleado cada vez que
		 * nos soliciten representar el objeto como una cadena de caracteres
		 */
		return String.join(" ", "Nombre y apellidos: ", nombre, apellidos,
			   "DNI: ", DNI,
			   "Salario: ", String.valueOf(salario));		
	}
	
	public static void main(String[] args) {
		
		// Vamos a probar nuestra clase Empleado
		
		Empleado empleado1 = new Empleado("Jason","Bourne","99888777X",85000);
		Empleado empleado2 = new Empleado("John D.", "Rockefeller",
										  "00000001A", 90000);
		System.out.println(empleado1);
	
		System.out.println(empleado1.setDNI("123"));
		
		System.out.println(empleado1);
		
		/*
		 * A continuación, modificar los atributos del objeto empleado1 de la siguiente
		 * forma:
		 * 		- nombre: Jason;
		 * 		- apellido: Bourne;
		 * 		- DNI: 99888777X;
		 * 		- salario: 85.000;
		 */
		
		
	}
}
