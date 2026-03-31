package dataserializationforbackup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserBackup {
	public static void backup(Object obj,String filePath) throws FileNotFoundException, IOException {
		if(obj instanceof BackupSerializable) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
			oos.writeObject(obj);
		}
		System.out.println("User backup successfull at :"+ filePath);
		}
	}
public static Object restore(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
	try(ObjectInputStream ois= new ObjectInputStream(new FileInputStream(filePath))){
		return ois.readObject();
	}
}
}
