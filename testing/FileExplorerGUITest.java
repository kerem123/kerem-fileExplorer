import gui.FileExplorerGUI;

import org.junit.Before;
import org.junit.Test;

public class FileExplorerGUITest {
	private FileExplorerGUI gui;
	
	@Before
	public void setUp() throws Exception {
		gui = new FileExplorerGUI();
	}

	@Test
	public void testFileExplorerGUI() {
		gui.setVisible(true);
	}
}
