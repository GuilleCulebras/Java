package urjc.ist.playlist;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import urjc.ist.playlist.Cancion.Codecs;
import java.util.Scanner;





public class GestionPlayList {

	private ArrayList<Playlist> lista;
	
	public GestionPlayList() {
		lista = new ArrayList<Playlist>();
	}
	
	public void addPlaylist(Playlist unaPlaylist){
		lista.add(unaPlaylist);
	}
	
	public void deletePlaylist(Playlist unaPlaylist){
		lista.remove(unaPlaylist);
	}
	
	public int numPlayList(){
		return lista.size();
	}
	
	public Playlist getPlaylist(int num){
		return lista.get(num);
	}
	
	public ArrayList<Playlist> getLista(){
		return lista;
	}
	
	public void writeData(String path, ArrayList<String> datos){
		try{
			FileOutputStream sumidero = new FileOutputStream(path);
			BufferedOutputStream bio = 	new BufferedOutputStream(sumidero);
			DataOutputStream odata = new DataOutputStream(bio);
			for (String elem: datos){
				odata.writeBytes(elem);
				odata.flush();
			}
			odata.close();
		}
		catch (IOException e){
			System.out.println(e);
		}
	}

	public void deleteFolder(File folder){
		
		while(folder.list().length > 0){		
			File[] listFiles = folder.listFiles();
			for (File f: listFiles){
				if(!f.isDirectory()){ 
					f.delete();
				}
				else{ 
					if(f.list().length == 0){ 
						f.delete();
					}
					else{
						deleteFolder(f);
					}
				}
			} 

		}
		folder.delete();
	}
	
	public void writeFile(){
		File datos = new File("/home/guille/datos");
		
		int numPlay = 0;
		int numAlbum = 0;
		int numCancion = 0;
		
		if (datos.exists()){
			deleteFolder(datos);
		}
		
		if(lista.size() != 0){
			File folder = new File("/home/guille/datos");
			folder.mkdir();	
			
			for (Playlist playlist : lista){
				folder = new File("/home/guille/datos/playlist" + (numPlay + 1));
				folder.mkdir();
				try{
					File archivo = new File("/home/guille/datos/playlist" + (numPlay + 1) + "/PL" + (numPlay + 1) + ".txt");
					archivo.createNewFile();
				}
				catch (IOException e){
					System.err.println(e);
				}
				
				ArrayList<String> datosPlay = new ArrayList<String>();
				datosPlay.add(playlist.getNombre() + "\n");
				
				writeData("/home/guille/datos/playlist" + (numPlay + 1) + "/PL" + (numPlay + 1) + ".txt",datosPlay);
				
				if(playlist.getAlbumList().size() != 0){
					for(Album album : playlist.getAlbumList()){
						folder = new File("/home/guille/datos/playlist" + (numPlay + 1) + "/album" + (numAlbum + 1));
						folder.mkdir();
						try{
							File archivo = new File("/home/guille/datos/playlist" + (numPlay + 1) + "/album" + (numAlbum + 1) + "/alb" + (numAlbum + 1) + ".txt");
							archivo.createNewFile();
						}
						catch (IOException e){
							System.err.println(e);
						}
						
						ArrayList<String> datosAlbum = new ArrayList<String>();
						
						datosAlbum.add(album.getTitulo() + "\n");
						datosAlbum.add(album.getAutor() + "\n");
						datosAlbum.add(album.getGrupo() + "\n");
						
						writeData("/home/guille/datos/playlist" + (numPlay + 1) + "/album" + (numAlbum + 1) + "/alb" + (numAlbum + 1) + ".txt", datosAlbum);
						
						if(album.getTrackList().size() != 0){
							for(Cancion cancion : album.getTrackList()){
								try{
									File archivo = new File("/home/guille/datos/playlist" + (numPlay + 1) + "/album" + (numAlbum + 1) + "/cancion" + (numCancion + 1) + ".txt");
									archivo.createNewFile();
								}
								catch (IOException e){
									System.err.println(e);
								}
								ArrayList<String> datosCancion = new ArrayList<String>();
								datosCancion.add(cancion.getTitulo() + "\n");
								datosCancion.add(cancion.getAutor() + "\n");
								datosCancion.add(cancion.getDuracion() + "\n");
								datosCancion.add(cancion.getFormato() + "\n");
								
								writeData("/home/guille/datos/playlist" + (numPlay + 1) + "/album" + (numAlbum + 1) + "/cancion" + (numCancion + 1) + ".txt",datosCancion);
								numCancion++;
							}
						}
						numAlbum++;
					}
				}
				numPlay++;
				numCancion = 0;
				numAlbum = 0;
			}
		}
		
	}
	
	public int numDirectorios(File directorio){

		int num = 0;
		try{
			File[] filelist = directorio.listFiles();
			for(int i = 0; i < filelist.length; i++){
				if (filelist[i].isDirectory()){
					num++;
				}
			}
		}
		catch(java.lang.NullPointerException e){
			num = -1;
		}
		return num;
	}
	
	private ArrayList<String> readData(String path){

		String linea;
		ArrayList<String> contenido;
		try{	
			contenido = new ArrayList<String>();
			InputStream stream = new FileInputStream(path);
			Reader reader = new InputStreamReader(stream);
			BufferedReader bio = new BufferedReader(reader);
			while ((linea = bio.readLine()) != null){
				contenido.add(linea);
			}
			bio.close();
		}
		catch (IOException e){
			contenido = new ArrayList<String>();
		}
		return contenido;
	}
	public void readFile(){
		int cont1=0, cont2=0, cont3=0, numPlay=0, numAlbum=0, numCancion=0;
		Album album = new Album();
		
		lista.clear();
		
		File folder = new File("/home/guille/datos");
		if(folder.exists()){
			numPlay = numDirectorios(folder);
			if(numPlay > 0){
				for(int i = 0; i < numPlay; i ++){
					ArrayList<String> datosPlay = readData("/home/guille/datos/playlist" + (cont1 + 1) + "/PL" +(cont1 + 1) + ".txt");
					if (lista.size() >0){
						Playlist playlist = new Playlist(datosPlay.get(0));
						lista.add(playlist);
					}
					
					File folderAlbum = new File("/home/guille/datos/playlist" + (cont1+1));
					numAlbum = numDirectorios(folderAlbum);
					if(numAlbum > 0){
						for(int j = 0; j < numAlbum; j++){
							ArrayList<String> datosAlbum = readData("/home/guille/datos/playlist" + (cont1+1) + "/album" + (cont2+1) + "/alb" + (cont2+1) + ".txt");
							if(datosAlbum.size() > 0){
								album = new Album(datosAlbum.get(0), datosAlbum.get(1), datosAlbum.get(2));
							}
							File folderCancion = new File("/home/guille/datos/playlist" + (cont1+1) + "/album" + (cont2+1));
							
							numCancion = folderCancion.list().length - 1;
							
							if (numCancion > 0){
								for(int n = 0; n < numCancion; n++){
									ArrayList<String> datosCancion = readData("/home/guille/datos/playlist" + (cont1+1) + "/album" + (cont2+1) + "/cancion" + (cont3+1) + ".txt");
									if (datosCancion.size() > 0){
										String formato = datosCancion.get(3);
										Cancion cancion = new Cancion();
										switch(formato){
											case "MP3":
												cancion = new Cancion(datosCancion.get(0), datosCancion.get(1), Integer.parseInt(datosCancion.get(2)),Codecs.MP3);
											case "FLAC":
												cancion = new Cancion(datosCancion.get(0), datosCancion.get(1), Integer.parseInt(datosCancion.get(2)),Codecs.FLAC);
											case "OGG":
												cancion = new Cancion(datosCancion.get(0), datosCancion.get(1), Integer.parseInt(datosCancion.get(2)),Codecs.OGG);											
										}
										album.addTrack(cancion);
									}
									cont3++;
								}
							}
							lista.get(i).addAlbum(album);
							cont2++;
							cont3 = 0;
						}
					}
					cont1++;
					cont2 = 0;
				}
			}
		}else{
			System.out.println("No hay contenido guardado");
		}
	}
	

	public static void main(String[] args) {
		GestionPlayList coleccion = new GestionPlayList();
		Scanner entrada = new Scanner(System.in);
		
		
		int num, x, y, z;
		String nomPlay;
		String titAlbum, grupAlbum, autAlbum;
		String titSong, autSong;
		int durSong, formato;
		Playlist auxPlaylist;
		Album auxAlbum;
		Cancion auxSong;
		
		for(;;){
			
			System.out.println("¿Qué desea hacer?");
			System.out.println("1. Crear, borrar, modificar PlayList\n"
							+ "2. Crear, borrar, modificar Album\n"
							+ "3. Crear, borrar, modificar Cancion\n"
							+ "4. Mostrar contenido\n"
							+ "5. Guardar contenido\n"
							+ "6. Cargar contenido (No funciona)");

			num = entrada.nextInt();
	
			
			if(num == 1){			
				System.out.println("1. Crear\n"
								+ "2. Borrar\n"
								+ "3. Modificar\n");
				int num1 = entrada.nextInt();
				
				if (num1 == 1){
					
					System.out.println("Introduce un nombre para la playlist: ");
					nomPlay = entrada.next();
					auxPlaylist = new Playlist();
					auxPlaylist.setNombre(nomPlay);
					
					coleccion.addPlaylist(auxPlaylist);
					System.out.println(nomPlay + " añadida a la lista");
					
				//Borramos la playlist segun el nombre, solo se puede si ya existe una	
				}else if(num1 == 2 && coleccion.numPlayList() != 0){
					
					System.out.println("Introduce el nombre de la playlist que desees borrar:");
					nomPlay = entrada.next();
					
					x = 0;
					for(int i = 0; i < coleccion.numPlayList(); i++){
					
						if(coleccion.getPlaylist(i).getNombre().equalsIgnoreCase(nomPlay)){//iteramos dentro de la lista y si encontamos una playlist con el mismo nombre que le hemos dado, la borramos.
							coleccion.deletePlaylist(coleccion.getPlaylist(i));
							System.out.println(nomPlay + " borrada de la lista");
							//iteradorPlay = coleccion.getLista().iterator();
							x++;
						}
					}
					if(x == 0){
						System.out.println("Nombre de playlist no encontrado.");
					}
				//Modificamos la playlist, solo se puede si ya existe una	
				}else if(num1 == 3 && coleccion.numPlayList() != 0){
					
					System.out.println("Introduce el nombre de la playlist que desees modificar:");
					nomPlay = entrada.next();
					x = 0;
					//Buscamos coincidencia en la lista y cambiamos el nombre a uno nuevo
					for(int i = 0; i < coleccion.numPlayList(); i++){
						
						if(coleccion.getPlaylist(i).getNombre().equalsIgnoreCase(nomPlay)){
							System.out.println("Introduce el nuevo nombre de la playlist:");
							String nomPlay2 = entrada.next();
							coleccion.getPlaylist(i).setNombre(nomPlay2);
							System.out.println("Nombre de playlist modificado");
							x++;
						}
					}
					if(x == 0){
						System.out.println("Nombre de playlist no encontrado");
					}
				}else{
					System.out.println("Primero debes crear una playlist");
				}
// ----------------------------------------------------------------------------------			
				
			}else if(num == 2 && coleccion.numPlayList() != 0){
				System.out.println("1. Crear\n"
								+ "2. Borrar\n"
								+ "3. Modificar\n");
				int num2 = entrada.nextInt();
				
				if (num2 == 1){
					System.out.println("Introduce el nombre de la playlist a la que quieres añadir un album.");
					nomPlay = entrada.next();
					System.out.println("Introduce el titulo del album.");
					titAlbum = entrada.next();
					System.out.println("Introduce el grupo del album.");
					grupAlbum = entrada.next();
					System.out.println("Introduce el autor del album.");
					autAlbum = entrada.next();
					
					auxAlbum = new Album();
					auxAlbum.setTitulo(titAlbum);
					auxAlbum.setGrupo(grupAlbum);
					auxAlbum.setAutor(autAlbum);
					
					x = 0;
					for(int i = 0; i < coleccion.numPlayList(); i++){
						if(coleccion.getPlaylist(i).getNombre().equalsIgnoreCase(nomPlay)){
							coleccion.getPlaylist(i).addAlbum(auxAlbum);
							System.out.println(titAlbum + " añadido a la playlist " + nomPlay);
							x++;
						}
					}
					if(x == 0){
						System.out.println("Nombre de playlist no encontrado");
					}
				}else if (num2 == 2){
					System.out.println("Introduce la playlist de la que quieres borrar un album.");
					nomPlay = entrada.next();
					
					x = 0;
					y = 0;
					z = 0;
					for(int i = 0; i < coleccion.numPlayList(); i++){
						//Si se encuentra el nombre de la cancion en la lista
						if(coleccion.getPlaylist(i).getNombre().equalsIgnoreCase(nomPlay)){
							x++;
							//Si la lista a la que intentamos acceder no esta vacia
							if(coleccion.getPlaylist(i).getNumAlbumes() != 0){
								System.out.println("Introduce el titulo del album que deseas borrar");
								titAlbum = entrada.next();
								y++;
								//recorremos los albumes dentro de la playlist
								for(int j = 0; j < coleccion.getPlaylist(i).getNumAlbumes(); j++){
									//Si encontramos album que coincida con el titulo dado, borramos
									if(coleccion.getPlaylist(i).getAlbum(j).getTitulo().equalsIgnoreCase(titAlbum)){
										coleccion.getPlaylist(i).removeAlbum(coleccion.getPlaylist(i).getAlbum(j));
										System.out.println(titAlbum + " borrado de la playlist " + nomPlay);
										z++;
									}									
								}								
							}							
						}
					}
					if(x == 0){
						System.out.println("Nombre de playlist no encontrado.");
					}
					if(y == 0 && x != 0){
						System.out.println("Playlist vacia.");
					}
					if(z == 0 && y != 0){
						System.out.println("Album no encontrado.");
					}
					
				}else if(num2 == 3){
					System.out.println("Introduce el nombre de la playlist de la que quieres modificar un album.");
					nomPlay = entrada.next();
														
					x=0;
					y=0;
					z=0;
					for(int i = 0; i < coleccion.numPlayList(); i++){
						if(coleccion.getPlaylist(i).getNombre().equalsIgnoreCase(nomPlay)){
							x++;
							if(coleccion.getPlaylist(i).getNumAlbumes() != 0){
								System.out.println("Introduce el titulo del album que deseas modificar.");
								titAlbum = entrada.next();
								y++;
								for(int j = 0; j < coleccion.getPlaylist(i).getNumAlbumes(); j++){
									if(coleccion.getPlaylist(i).getAlbum(j).getTitulo().equalsIgnoreCase(titAlbum)){
										System.out.println("Introduce el nuevo titulo del album.");
										String titAlbum2 = entrada.next();
										System.out.println("Introduce el nuevo grupo del album.");
										String grupAlbum2 = entrada.next();
										System.out.println("Introduce el nuevo autor del album.");
										String autAlbum2 = entrada.next();
										
										coleccion.getPlaylist(i).getAlbum(j).setTitulo(titAlbum2);
										coleccion.getPlaylist(i).getAlbum(j).setGrupo(grupAlbum2);
										coleccion.getPlaylist(i).getAlbum(j).setAutor(autAlbum2);
										z++;
										System.out.println("Album modificado.");
									}
								}
							}
						}
					}
					if(x == 0){
						System.out.println("Nombre de playlist no encontrado.");
					}
					if(y == 0 && x != 0){
						System.out.println("Playlist vacia.");
					}
					if(z == 0 && y != 0){
						System.out.println("Album no encontrado.");
					}
				}
		
			
// ----------------------------------------------------------------------------------			
			}else if (num == 3 && coleccion.numPlayList() != 0){
				int numAlbumes = 0;
				int numAlbumesTot = 0;
				
				for(int i = 0; i < coleccion.numPlayList(); i++){
					numAlbumes = coleccion.getPlaylist(i).getNumAlbumes();	
					numAlbumesTot = numAlbumesTot + numAlbumes;
				}
				if(numAlbumesTot != 0){
					System.out.println("1. Crear\n"
							+ "2. Borrar\n"
							+ "3. Modificar\n");
					int num3 = entrada.nextInt();
					//Crear cancion
					if(num3 == 1){
						System.out.println("Intruduce playlist y titulo de album donde quieres añadir la cancion.");
						nomPlay = entrada.next();
						titAlbum = entrada.next();
						x = 0;
						y = 0;
						auxSong = new Cancion();
						
						for(int i = 0; i < coleccion.numPlayList(); i++){
							if(coleccion.getPlaylist(i).getNombre().equalsIgnoreCase(nomPlay)){
								x++;
								for(int j = 0; j < coleccion.getPlaylist(i).getNumAlbumes(); j++){
									if(coleccion.getPlaylist(i).getAlbum(j).getTitulo().equalsIgnoreCase(titAlbum)){
										y++;
										System.out.println("Introduce el titulo de la cancion");
										titSong = entrada.next();
										System.out.println("Introduce el autor de la cancion");
										autSong = entrada.next();
										System.out.println("Introduce la duracion de la cancion");
										durSong = entrada.nextInt();
										System.out.println("Selecciona un formato: 1.MP3 2.FLAC 3.OGG");
										formato = entrada.nextInt();
										switch(formato){
										case 1:
											auxSong.setFormato(Codecs.MP3);
											auxSong.setTitulo(titSong);
											auxSong.setAutor(autSong);
											auxSong.setDuracion(durSong);
											coleccion.getPlaylist(i).getAlbum(j).addTrack(auxSong);
											System.out.println(titSong + " añadida al album " + titAlbum + " de la playlist " + nomPlay); 
											break;
										case 2:
											auxSong.setFormato(Codecs.FLAC);
											auxSong.setTitulo(titSong);
											auxSong.setAutor(autSong);
											auxSong.setDuracion(durSong);
											coleccion.getPlaylist(i).getAlbum(j).addTrack(auxSong);
											System.out.println(titSong + " añadida al album " + titAlbum + " de la playlist " + nomPlay); 
											break;
										case 3:
											auxSong.setFormato(Codecs.OGG);
											auxSong.setTitulo(titSong);
											auxSong.setAutor(autSong);
											auxSong.setDuracion(durSong);
											coleccion.getPlaylist(i).getAlbum(j).addTrack(auxSong);
											System.out.println(titSong + " añadida al album " + titAlbum + " de la playlist " + nomPlay); 
											break;
										default:
											System.out.println("Opcion incorrecta. Cancion no añadida");
											break;
										}
										coleccion.getPlaylist(i).actualizaPlaylist1(coleccion.getPlaylist(i).getAlbum(j));

									}
								}
							}
						}
						if(x == 0){
							System.out.println("Nombre de playlist no encontrado.");
						}
						if(y == 0 && x != 0){
							System.out.println("Titulo de album no encontrado.");
						}
					//Borrar cancion	
					}else if(num3 == 2){
						System.out.println("Intruduce playlist y titulo de album donde se encuentra la cancion.");
						nomPlay = entrada.next();
						titAlbum = entrada.next();
						x = 0;
						y = 0;
						z = 0;
						for(int i = 0; i < coleccion.numPlayList(); i++){
							if(coleccion.getPlaylist(i).getNombre().equalsIgnoreCase(nomPlay)){
								x++;
								for(int j = 0; j < coleccion.getPlaylist(i).getNumAlbumes(); j++){
									if(coleccion.getPlaylist(i).getAlbum(j).getTitulo().equalsIgnoreCase(titAlbum)){
										y++;
										System.out.println("Intruduce el titulo de la cancion que deseas borrar");
										titSong = entrada.next();
														
										for(int n = 0; n < coleccion.getPlaylist(i).getAlbum(j).getNumCanciones(); n++){
											if(coleccion.getPlaylist(i).getAlbum(j).getTrack(n).getTitulo().equalsIgnoreCase(titSong)){
												z++;
												coleccion.getPlaylist(i).actualizaPlaylist2(coleccion.getPlaylist(i).getAlbum(j),n);
												coleccion.getPlaylist(i).getAlbum(j).deleteTrack(coleccion.getPlaylist(i).getAlbum(j).getTrack(n));
												System.out.println(titSong + " borrada del album " + titAlbum);
											}									
										}
									}
								}
							}
						}
						if(x == 0){
							System.out.println("Nombre de playlist no encontrado.");
						}
						if(y == 0 && x != 0){
							System.out.println("Titulo de album no encontrado.");
						}
						if(z == 0 && y != 0){
							System.out.println("Titulo de cancion no encontrado.");
						}
						//Modifica cancion
					}else if(num3 == 3){
						System.out.println("Intruduce playlist y titulo de album donde se encuentra la cancion.");
						nomPlay = entrada.next();
						titAlbum = entrada.next();
						x = 0;
						y = 0;
						z = 0;
						
						for(int i = 0; i < coleccion.numPlayList(); i++){
							if(coleccion.getPlaylist(i).getNombre().equalsIgnoreCase(nomPlay)){
								x++;
								for(int j = 0; j < coleccion.getPlaylist(i).getNumAlbumes(); j++){
									if(coleccion.getPlaylist(i).getAlbum(j).getTitulo().equalsIgnoreCase(titAlbum)){
										y++;
										System.out.println("Intruduce el titulo de la cancion que deseas modificar");
										titSong = entrada.next();
										for(int n = 0; n < coleccion.getPlaylist(i).getAlbum(j).getNumCanciones(); n++){
											if(coleccion.getPlaylist(i).getAlbum(j).getTrack(n).getTitulo().equalsIgnoreCase(titSong)){
												z++;
												System.out.println("Introduce el nuevo titulo de la cancion");
												String titSong2 = entrada.next();
												System.out.println("Introduce el nuevo autor de la cancion");
												String autSong2 = entrada.next();
												System.out.println("Introduce la nueva duracion de la cancion");
												int durSong2 = entrada.nextInt();
												System.out.println("Selecciona un nuevo formato: 1.MP3 2.FLAC 3.OGG");
												int formato2 = entrada.nextInt();
												//Restamos la duracion de la cancion que vamos a modificar, para luego añadirle la nueva duracion
												coleccion.getPlaylist(i).actualizaPlaylist2(coleccion.getPlaylist(i).getAlbum(j),n);
												//Actualizamos album
												coleccion.getPlaylist(i).getAlbum(j).actualizaAlbum1(coleccion.getPlaylist(i).getAlbum(j).getTrack(n));
												
												
												switch(formato2){
												case 1:
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setFormato(Codecs.MP3);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setTitulo(titSong2);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setAutor(autSong2);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setDuracion(durSong2);
													System.out.println("Cancion modificada");
													break;
												case 2:
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setFormato(Codecs.FLAC);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setTitulo(titSong2);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setAutor(autSong2);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setDuracion(durSong2);
													System.out.println("Cancion modificada");
													break;
												case 3:
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setFormato(Codecs.OGG);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setTitulo(titSong2);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setAutor(autSong2);
													coleccion.getPlaylist(i).getAlbum(j).getTrack(n).setDuracion(durSong2);
													System.out.println("Cancion modificada");
													break;
												default:
													System.out.println("Opcion incorrecta.");	
													break;
												}
												coleccion.getPlaylist(i).actualizaPlaylist3(coleccion.getPlaylist(i).getAlbum(j),n);
												coleccion.getPlaylist(i).getAlbum(j).actualizaAlbum2(coleccion.getPlaylist(i).getAlbum(j).getTrack(n));

											}
										}										
									}
								}
							}
						}						
						if(x == 0){
							System.out.println("Nombre de playlist no encontrado.");
						}
						if(y == 0 && x != 0){
							System.out.println("Titulo de album no encontrado.");
						}
						if(z == 0 && y != 0){
							System.out.println("Titulo de cancion no encontrado.");
						}						
					}					
				}else{
					System.out.println("Primero debes crear un album dentro de una playlist");
				}
			}else if(num == 4){
				Iterator<Playlist> iteradorPlay;
				iteradorPlay = coleccion.getLista().iterator();
				
				while(iteradorPlay.hasNext()){
					System.out.println(iteradorPlay.next());
				}
				
			}else if(num == 5){
				coleccion.writeFile();
			
			}else if(num == 6){
				coleccion.readFile();
				
			}else{
				System.out.println("No se puede realizar dicha operacion");
			}
		}
	}
}
