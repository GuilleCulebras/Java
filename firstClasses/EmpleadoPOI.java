package urjc.ist.firstClasses;

public class EmpleadoPOI extends Empleado {//TEnemos una clase empleado arriba, empleado es la superclase de empleadoPOI

	public EmpleadoPOI() {//Constructor
		// TODO Auto-generated constructor stub
	}

	public EmpleadoPOI(String unNombre, String unosApellidos, String unDNI, double unSalario) {//Constructor
		super(unNombre, unosApellidos, unDNI, unSalario);//es lo mimo que llamar al constructor empleado de la super clase. Principio de encapsulacion
		//Ya estan los 4 atributos inicializados
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
