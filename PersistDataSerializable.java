package persistentdata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
// Eric McCreath - 2015
// Example of saving and loading info using serializable objects
public class PersistDataSerializable implements Serializable {
	String name;
	int age;
	public PersistDataSerializable(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public PersistDataSerializable() {
	}
	static public PersistDataSerializable load(String filename) {
		PersistDataSerializable res = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					filename));
			res = (PersistDataSerializable) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	public void save(String filename) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(filename));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void show() {
		System.out.println("Name : " + name);
		System.out.println("Age : " + age);
	}
	public static void main(String[] args) {
		PersistDataSerializable data = new PersistDataSerializable("Hugh", 10);
		data.save("data.ser");
		PersistDataSerializable dataload = load("data.ser");
		dataload.show();
	}
}
