package urjc.ist.playlist;

import java.util.ArrayList;
import java.util.Objects;

//import urjc.ist.playlist.Cancion;// Si estan en el mismo paquete, no hace falta importarlo
import urjc.ist.playlist.Cancion.Codecs;

public class Album {
	/*
	 *  TODO Se pide completar la definición de la clase Album
	 *  Para ello, se puede consultar la URL sobre la clase de
	 *  utilidad ArrayList, que permite crear arrays de cualquier
	 *  tipo de objetos pero de longitud flexible (se pueden
	 *  extender o acortar en tiempo de ejecución
	 *  
	 *  // https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
	 */
	
	private String titulo;
	private String autor;
	private String grupo;
	private int duracionTotal;
	private ArrayList<Cancion> trackList;//la clase album utiliza la clase cancion

	public Album() {
		titulo = "";
		autor = "";
		grupo = "";
		duracionTotal = 0;
		trackList = new ArrayList<Cancion>();
	}
	
	public Album(String titulo, String autor, String grupo) {//no hay que inicializar arraylist en los construcotes
		this.titulo = titulo;
		this.autor = autor;
		this.grupo = grupo;
		duracionTotal = 0;
		trackList = new ArrayList<Cancion>();
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo= titulo;
	}
	
	public String getAutor(){
		return autor;
	}
	
	public void setAutor(String autor){
		this.autor = autor;
	}
	
	public String getGrupo(){
		return grupo;
	}
	
	public void setGrupo(String grupo){
		this.grupo = grupo;
	}
	
	public int getDuracion(){
		return duracionTotal;
	}
	
	public ArrayList<Cancion> getTrackList() {
		/**
		 * Método que devuelve la lista de canciones actualmente
		 * incluídas en el álbum
		 */
		return trackList;
	}
	
	public Cancion getTrack(int posicion) {
		/**
		 * Método que devuelve la canción que esté en la posición
		 * de la lista del álbum que se indica como argumento
		 */
		return trackList.get(posicion);//Devuelve el nombre de la cancion que esta en esa posicion del array
	}
	
	public void addTrack(Cancion unaCancion) {
		/**
		 * Método que añade una canción a la lista de canciones
		 * del álbum. Además, el método calcula y actualiza
		 * automáticamente la nueva duración total del álbum
		 */
		trackList.add(unaCancion);
		duracionTotal = duracionTotal + unaCancion.getDuracion();
		
	}
	
	public void addTrack(int posicion, Cancion unaCancion) {
		/**
		 * Método que añade una canción al álbum(que pasamos
		 * como segundo argumento) en la posición que indique
		 * el int que recibe como primer argumento
		 */
		
		try{
			trackList.add(posicion, unaCancion);
		} catch (IndexOutOfBoundsException e){
			System.out.println("Captuada la excepcion: " + e);
		}
		duracionTotal = duracionTotal + unaCancion.getDuracion();
		
		
	}
	
	public void deleteLastTrack() {
		/**
		 * Método que borra la última canción de la lista del
		 * álbum
		 */
		int durUltimaCancion;
		
		durUltimaCancion = (trackList.get(trackList.size() - 1)).getDuracion();
		duracionTotal = duracionTotal - durUltimaCancion;
		
		trackList.remove(trackList.size() - 1);
		
		
	}
	
	public void deleteTrack(int posicion) {
		/**
		 * Método que borra la canción en la posición de la lista
		 * que indica el argumento que recibe
		 */
		int durCancion;
		
		durCancion = (trackList.get(posicion)).getDuracion();
		duracionTotal = duracionTotal - durCancion;
		
		trackList.remove(posicion);
	}
	
	public void deleteTrack(Cancion unaCancion){
		int durCancion;
		durCancion = unaCancion.getDuracion();
		duracionTotal = duracionTotal - durCancion;
		trackList.remove(unaCancion);
	}
	
	public void actualizaAlbum1(Cancion unaCancion){
		int durCancion;
		durCancion = unaCancion.getDuracion();
		duracionTotal = duracionTotal - durCancion;
	}
	
	public void actualizaAlbum2(Cancion unaCancion){
		int durCancion;
		durCancion = unaCancion.getDuracion();
		duracionTotal = duracionTotal + durCancion;
	}
	
	public void clearAlbum() {
		/**
		 * Método que borra todas las canciones en la lista de
		 * un álbum
		 */
		trackList.clear();
		duracionTotal = 0; 
	}
	
	public int getNumCanciones(){
		//Devuelve el numero de canciones de la lista
		return trackList.size();
	}
	
	
	
	@Override
	public String toString(){
		
		int hours = duracionTotal / 3600;
		int minutes = (duracionTotal % 3600) / 60;
		int seconds = duracionTotal % 60;
		
		String timeString;
		
		if (hours > 0) {
			timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		} else {
			timeString = String.format("%02d:%02d", minutes, seconds);
		}
		
		return String.join("\n", "----------", 
				"Titulo: " + titulo,
				"Autor: " + autor,
				"Grupo: " + grupo,
				"Duracion Total: " + timeString,
				"Canciones: " + trackList,
				"-----------");		
	}
	
	@Override
	public boolean equals(Object other) {
		/**
		 * Implementación de un método de comparación del contenido
		 * de dos canciones
		 */
		
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Album)) return false;
	    
	    Album otherAlbum = (Album)other;
	    if (this.titulo == otherAlbum.titulo &&
	    		this.autor == otherAlbum.autor &&
	    		this.grupo == otherAlbum.grupo &&
	    		this.trackList == otherAlbum.trackList) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(titulo, autor, grupo, trackList);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cancion track1 = new Cancion("The Song of the Sun", "Mike Olfield", 273, Codecs.MP3);
		Cancion track2 = new Cancion("quetal", "hola", 273, Codecs.OGG);
		Cancion track3 = new Cancion ("Amanece", "EL pescao", 100, Codecs.MP3);
		
		
		Album album1 = new Album();
		
		album1.addTrack(track1);
		album1.addTrack(track2);
		album1.addTrack(5, track3);
		
		System.out.println(album1.getDuracion());
		
		album1.deleteTrack(1);
	
		System.out.println(album1.getDuracion());
		
		System.out.println(album1.equals(album1));

	}

}
