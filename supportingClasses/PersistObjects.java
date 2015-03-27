import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This Singleton class is responsible for Serializing objects to be persisteed and de-serialized.
 * 
 * @author Kerem Baybars
 * @version 1.0
 */
public class PersistObjects {
	private static PersistObjects singletonPersistObjects;
	private static JFileChooser fc;
	
	/**
	 * Initialises the JFileChooser component
	 */
	private PersistObjects() {
		fc = new JFileChooser();
	}
	
	/**
	 * This is a standard Singleton Pattern function where it returns the same instance of it self.
	 * It is also protected against problems which may be raised by multithreading.
	 * 
	 * @return singletonObject
	 */
	public static synchronized PersistObjects getSingletonPersistObjects() {
		if(singletonPersistObjects == null) {
			singletonPersistObjects = new PersistObjects();
		}
		return singletonPersistObjects;
	}
	
	/**
	 * This method prevents other classes from cloning this class.
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * This class serializes a 2D array containing files which may be stored in the PC for persistence.
	 * 
	 * @param objArray - Array containing files.
	 */
	public static synchronized void serialiseAndSave(Object[][] objArray) {
		fc.setFileFilter(new FileNameExtensionFilter(".data", "dat", "data"));
		fc.setAcceptAllFileFilterUsed(false);
		fc.setDialogTitle("Choose file destination...");
		
		int choice = fc.showSaveDialog(null);
		if(choice == JFileChooser.APPROVE_OPTION) {			
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile().getAbsolutePath()+".data"))) {
				oos.writeObject(objArray);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Failed to save!", "Error occurred", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "Saved successfully!", "Saved successfully", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * This method de-serializes objects from the PC and loads it back to the GUI.
	 * 
	 * @return - 2d array
	 */
	public static synchronized Object[][] deSerialiseAndLoad() {
		fc.setFileFilter(new FileNameExtensionFilter(".data", "dat", "data"));
		fc.setAcceptAllFileFilterUsed(false);
		fc.setDialogTitle("Choose file(s) to load...");
		
		Object[][] objLoadArray = null;
		int choice = fc.showOpenDialog(null);
		if(choice == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if(file.canRead() && file.exists()) {
				try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
					objLoadArray = (Object[][]) ois.readObject(); 
				} catch (IOException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Cannot load content!", "Error occurred", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "File cannot be read or does not exist!", "Error occurred", JOptionPane.ERROR_MESSAGE);
			} 
		} else if(choice == JFileChooser.CANCEL_OPTION) {}
		
		return objLoadArray;
	}
}
