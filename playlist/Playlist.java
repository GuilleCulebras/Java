package urjc.ist.playlist;

import java.util.ArrayList;
import java.util.Objects;


import urjc.ist.playlist.Cancion.Codecs;

public class Playlist {
	
	private String nombre;
	private int numAlbumes;
	private int numCanciones;
	private int duracionTotal;
	private ArrayList<Album> albumList;

	public Playlist() {
		nombre = "";
		numAlbumes = 0;
		numCanciones = 0;
		duracionTotal = 0;
		albumList = new ArrayList<Album>();
	}
	
	public Playlist(String nombre){
		this.nombre = nombre;
		numAlbumes = 0;
		numCanciones = 0;
		duracionTotal = 0;
		albumList = new ArrayList<Album>();
	}

	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public ArrayList<Album> getAlbumList(){
		return albumList;
	}
	
	public Album getAlbum(int posicion){
		return albumList.get(posicion);
	}
	
	public void addAlbum(Album unAlbum){
		albumList.add(unAlbum);
		
		numAlbumes++;
		numCanciones = numCanciones + unAlbum.getNumCanciones();
		duracionTotal = duracionTotal + unAlbum.getDuracion();
	}
	//Llamamos a estos metodos cuando añada o borre una cancion en gestionPLaylist
	public void actualizaPlaylist1(Album unAlbum){
		numCanciones++;
		
		int durCancion = unAlbum.getTrackList().get(unAlbum.getTrackList().size()-1).getDuracion();
		duracionTotal = duracionTotal + durCancion;
	}
	
	public void actualizaPlaylist2(Album unAlbum, int pos){
		numCanciones--;
		int durCancion = unAlbum.getTrackList().get(pos).getDuracion();
		duracionTotal = duracionTotal - durCancion;

	}
	
	public void actualizaPlaylist3(Album unAlbum, int pos){
		numCanciones++;
		int durCancion = unAlbum.getTrackList().get(pos).getDuracion();
		duracionTotal = duracionTotal + durCancion;	
	}
	
	public void addAlbum(int posicion, Album unAlbum){
		albumList.add(posicion, unAlbum);
		
		numAlbumes++;
		numCanciones = numCanciones + unAlbum.getNumCanciones();
		duracionTotal = duracionTotal + unAlbum.getDuracion();		
	}
	
	public void removeAlbum(){
		int cancionesLastAlbum;
		int durLastAlbum;
		
		cancionesLastAlbum = (albumList.get(albumList.size() - 1)).getNumCanciones();
		durLastAlbum = (albumList.get(albumList.size() - 1)).getDuracion();
		
		numAlbumes--;
		numCanciones = numCanciones - cancionesLastAlbum;
		duracionTotal = duracionTotal - durLastAlbum;
		
		albumList.remove(albumList.size() - 1);
	}
	
	public void removeAlbum(int posicion){
		int cancionesAlbum;
		int durAlbum;
		
		cancionesAlbum = (albumList.get(posicion)).getNumCanciones();
		durAlbum = (albumList.get(posicion)).getDuracion();
		
		numAlbumes--;
		numCanciones = numCanciones - cancionesAlbum;
		duracionTotal = duracionTotal - durAlbum;
		
		albumList.remove(posicion);		
	}
	
	public void removeAlbum(Album unAlbum){
		int cancionesAlbum;
		int durAlbum;
		
		cancionesAlbum = unAlbum.getNumCanciones();
		durAlbum = unAlbum.getDuracion();
		
		numAlbumes--;
		numCanciones = numCanciones - cancionesAlbum;
		duracionTotal = duracionTotal - durAlbum;
		
		albumList.remove(unAlbum);
	}
	
	public void clearPlaylist(){
		albumList.clear();
		
		numAlbumes = 0;
		numCanciones = 0;
		duracionTotal = 0;
	}
	
	public int getNumAlbumes(){
		return numAlbumes;
	}
	
	public int getNumCanciones(){
		return numCanciones;
	}
	
	public int getDuracionTotal(){
		return duracionTotal;
	}
	
	@Override
	public String toString(){
		
		int hours = duracionTotal / 3600;
		int minutes = (duracionTotal % 3600) / 60;
		int seconds = duracionTotal % 60;
		
		String timeString;
		String numAlbumesString;
		String numCancionesString;
		
		if (hours > 0) {
			timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		} else {
			timeString = String.format("%02d:%02d", minutes, seconds);
		}
		
		numAlbumesString = String.valueOf(numAlbumes);
		numCancionesString = String.valueOf(numCanciones);
		
		return String.join("\n", "----------", 
				"Nombre: " + nombre,
				"Numero de albumes: " + numAlbumesString,
				"Numero de canciones: " + numCancionesString,
				"Duracion Total: " + timeString,
				"Albumes: " + albumList,
				"----------");
	}
	
	
	@Override
	public boolean equals(Object other) {
		
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Album)) return false;
	    
	    Playlist otherPlaylist = (Playlist)other;
	    if (this.nombre == otherPlaylist.nombre &&
	    		this.numAlbumes == otherPlaylist.numAlbumes &&
	    		this.numCanciones == otherPlaylist.numCanciones &&
	    		this.albumList == otherPlaylist.albumList) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(nombre, numAlbumes, numCanciones, albumList);
	}
	
	
	public static void main(String[] args) {
		Cancion track1 = new Cancion("The Song of the Sun", "Mike Olfield", 273, Codecs.MP3);
		Cancion track2 = new Cancion("quetal", "hola", 250, Codecs.OGG);
		Cancion track3 = new Cancion("Amanece", "EL pescao", 100, Codecs.MP3);
		Cancion track4 = new Cancion("Payphone", "Maroon5", 200, Codecs.MP3);
		Cancion track5 = new Cancion("Historia que tu hiciste","Sergio Ramos", 93, Codecs.MP3);
		Cancion track6 = new Cancion("Crazy", "Aerosmith", 300, Codecs.OGG);
		
		Album album1 = new Album("Greatest hits","Adam Levine","Maroon5");
		album1.addTrack(track1);
		album1.addTrack(track2);
		album1.addTrack(track3);
		
		Album album2 = new Album("UnDecima","Sergio Ramos","Real Madrid");
		album2.addTrack(track4);
		album2.addTrack(track5);
		album2.addTrack(track6);
		
		Album album3 = new Album("Believer", "Imagine Dragons", "Imagine Dragons");
		album3.addTrack(track3);
		album3.addTrack(track6);
		
		
		Playlist playlist1 = new Playlist("Favoritas");
		
		playlist1.addAlbum(album1);
		playlist1.addAlbum(album2);
		playlist1.addAlbum(album3);

		System.out.println(playlist1);
		
		playlist1.removeAlbum();
				
		
		System.out.println(playlist1);
	}
}