package control;

import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList;
import entity.Admin;

//Initializer for admin.dat
public class SerializeAdminArray {
	
	public static void serializeAdminArray() {
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		
		Admin a1 = new Admin("bigchungus","qwerty123456");
		Admin a2 = new Admin("chonkyboi","wasdpoi!@");
		Admin a3 = new Admin("epicgamer42069", "M0unT41nD3w_~");
		
		adminList.add(a1);
		adminList.add(a2);
		adminList.add(a3);
		
		try {
			FileOutputStream fos = new FileOutputStream("src/data/admin.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(adminList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException { 
        serializeAdminArray(); 
    } 
}
