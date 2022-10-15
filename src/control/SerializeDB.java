package control;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import entity.Admin;

public class SerializeDB {
	public static List readSerializedObject(String filename) {
		List adminDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			adminDetails = (ArrayList)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return adminDetails;
	}

	public static void writeSerializedObject(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		List list;
		try	{
			list = (ArrayList)SerializeDB.readSerializedObject("src/data/admin.dat");
			for (int i = 0 ; i < list.size() ; i++) {
				Admin a = (Admin)list.get(i);
				System.out.println("name is " + a.getUserID() );
				System.out.println("contact is " + a.getPassword() );
			}
			
			Admin a1 = new Admin("bigchungus","qwerty123456");
			Admin a2 = new Admin("chonkyboi","wasdpoi!@");
			Admin a3 = new Admin("epicgamer42069", "M0unT41nD3w_~");
			list.add(a1);
			list.add(a2);
			list.add(a3);

			SerializeDB.writeSerializedObject("src/data/admin.dat", list);

		} catch (Exception e) {
				System.out.println( "Exception >> " + e.getMessage() );
		}
	}
}
