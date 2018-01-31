package urjc.ist.firstClasses;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class EjemploIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataOutputStream odata = null;
		
		try{
			FileOutputStream sumidero = new FileOutputStream("/tmp/a");
			BufferedOutputStream bio = new BufferedOutputStream(sumidero);
			odata = new DataOutputStream(bio);
			odata.writeBoolean(true);
			odata.writeLong(1234L);
			odata.writeDouble(3.1416);
		}catch (IOException ex){
			ex.printStackTrace();
		}finally{
			try {
				if (odata != null){
					odata.close();
				}
			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		// try - catch with resources
		
		try (DataOutputStream odata2 = 
				new DataOutputStream(
						new BufferedOutputStream(
								new FileOutputStream("/tmp/a2")))
				){
			odata2.writeBoolean(true);
			odata2.writeLong(1234L);
			odata2.writeDouble(3.1416);
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		//-----------------------------------------
		boolean b;
		long l;
		double d;
		
		try{
			FileInputStream fuente = new FileInputStream("/tmp/a");
			BufferedInputStream ibs = new BufferedInputStream(fuente);
			DataInputStream idata = new DataInputStream(ibs);
			b = idata.readBoolean();
			l = idata.readLong();
			d = idata.readDouble();
			idata.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		Path ruta1 = Paths.get("/", "home", "guille");
		Path ruta2 = Paths.get("data", "files");
		System.out.format("toString: %s%n", ruta1.toString());
		System.out.format("toString: %s%n", ruta2.toString());

	}

}
