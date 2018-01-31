package urjc.ist.firstClasses;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EjemploNIO {
	public static void main(String[] args) {
		
		Path ruta1 = Paths.get("/", "home", "guille");
		Path ruta2 = Paths.get("data", "files");
		System.out.format("toString: %s%n", ruta1.toString());
		System.out.format("toString: %s%n", ruta2.toString());
		
	}
}
