package urjc.ist.firstClasses;

public class Par<E1, E2> {//contenedor generico, no se tiene en cuenta en tiempo de ejecucion
	
	public E1 elem1;
	public E2 elem2;

	Par(E1 elem1, E2 elem2){
		this.elem1 = elem1;
		this.elem2 = elem2;
	}

}
