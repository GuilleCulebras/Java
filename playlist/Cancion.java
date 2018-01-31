package urjc.ist.playlist;

import java.util.Objects;

public class Cancion {
	
	public enum Codecs { //Tipo enumerado
		MP3, FLAC, OGG //MP3=0,FLAC=1
	}

	private String titulo;  // Título de la canción
	private String autor;  // Autor(a) de la canción
	private int duracion;  // Duración en segundos
	private Codecs formato;  // Codificación de la canción
	
	public Cancion() {
		// TODO Auto-generated constructor stub
		titulo = "";
		autor = "";
		duracion = 0;
		formato = null;
	}
	
	public Cancion(String titulo, String autor, int duracion, Codecs format) {//Llamamos a los argumentos del constructor igual que los atributos
		this.titulo = titulo;
		this.autor = autor;
		this.duracion = duracion;
		this.formato = format;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public Codecs getFormato() {
		return formato;
	}
	
	public void setFormato(Codecs formato) {
		this.formato = formato;
	}
	
	@Override //Forma de indicar al compilador que el metodo que va a continuacion pretende sobreescribir al metodo de la superclase
	//Decorator: pasa informacion al compilador cuando esta leyendo codigo
	public String toString() {//El metodo toString nunca recibe argumentos
		/*
		 * Creación de una representación del contenido de la
		 * Canción en formato String
		 */
		int hours = duracion / 3600;
		int minutes = (duracion % 3600) / 60;
		int seconds = duracion % 60;

		String timeString;
		if (hours > 0) {
			timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);//Crea una plantilla de valores entero de dos difras d=entero 02=dos cifras y rellena con 0 a la izq
		} else {
			timeString = String.format("%02d:%02d", minutes, seconds);
		}
		
		String formatoString;
		switch(formato){
		case MP3:
			formatoString="MP3";
			break;
		case FLAC:
			formatoString="FLAC";
			break;
		case OGG:
			formatoString="OGG";
			break;
		default:
			formatoString="??";
			break;
		}
		
		return String.join("\n", "----------", 
				"Título: " + titulo,
				"Autor: " + autor,
				"Duración: " + timeString,
				"Formato: " + formatoString,//Si tuvieramos mas de dos codecs podriamos hacerlo con un switch case
				"----------");
	}
	
	@Override
	public boolean equals(Object other) {//Me dice si dos objetos son iguales, tambien hay que sobreescribarlo
		/**
		 * Implementación de un método de comparación del contenido
		 * de dos canciones
		 */
		//En el metodo equals siempre hay que poner estas tres primeras lineas
		if (other == null) return false;//Si no hay otro objeto devuelve false
	    if (other == this) return true;//Si se compara consigo mismo,son iguales, develve true
	    if (!(other instanceof Cancion)) return false;//Si el otro objeto no es una instancia de la clase cancion, devuelve falso, si no es de tipo cancion, es false
	    
	    Cancion otherCancion = (Cancion)other;//Casting, para pasar de tipo object a tipo cancion
	    if (this.titulo == otherCancion.titulo &&
	    		this.autor == otherCancion.autor &&
	    		this.duracion == otherCancion.duracion &&
	    		this.formato == otherCancion.formato) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(titulo, autor, duracion, formato);
	}
	
	public static void main(String[] args) {
		Cancion track1 = new Cancion("The Song of the Sun", "Mike Olfield", 273, Codecs.MP3);
		Cancion track2 = new Cancion("The Song of the Sun", "Mike Olfield", 273, Codecs.OGG);
		System.out.println(track1);
		System.out.println(track1.equals(track2));
		
	
		System.out.println(track1.getDuracion());

		track1.setFormato(Codecs.OGG);
		System.out.println(track1);
	}

}
