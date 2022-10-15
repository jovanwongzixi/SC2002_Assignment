package control;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import entity.Admin;

public class CreateAdminData {

	public static void createAdminData(String file, Admin a) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(a);
			
			oos.close();
			fos.close();
			
		} catch (IOException e) {
			System.out.println("Oops");
		}
	}
	
	public static void main(String[] args) {
		
	}
}
