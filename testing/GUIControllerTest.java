import static org.junit.Assert.fail;
import gui.FileExplorerGUI;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kerem
 *
 */
public class GUIControllerTest {
	private FileExplorerGUI gui;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gui = new FileExplorerGUI();
	}

	/**
	 * Test method for {@link gui.GUIController#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
		try {
			gui.setVisible(true);
		} catch(IllegalArgumentException e) {
			fail("IllegalArgumentException is thrown");
		}
	}
}
