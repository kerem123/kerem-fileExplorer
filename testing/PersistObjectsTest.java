import static org.junit.Assert.*;

import javax.swing.JFileChooser;

import org.apache.commons.lang.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

import supportingClasses.PersistObjects;

public class PersistObjectsTest {
	private PersistObjects test;
	
	@Before
	public void setUp() throws Exception {
		test = PersistObjects.getSingletonPersistObjects();
		new JFileChooser();
	}

	@Test
	public void testGetSingletonPersistObjects() {
		PersistObjects test2 = PersistObjects.getSingletonPersistObjects();
		assertSame(test2, test);
	}

	@Test
	public void testSerialisation() {
		Object[][] testArray = { 
				{"test","test"}
		};
		
		byte[] serializedObject = SerializationUtils.serialize(testArray);
		Object[][] deserializedObject = (Object[][]) SerializationUtils.deserialize(serializedObject);
		
		assertArrayEquals(testArray, deserializedObject);
	}
}
