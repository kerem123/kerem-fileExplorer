import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * This is the main controller class to start and execute the FileExplorerGUI class.
 * 
 * @author Kerem Baybars
 * @version 1.0
 */
public class GUIController {
	
	/**
	 * The main method is the first point of execution by the Java Virtual
	 * Machine. This method executed the FileExplorerGUI class. It also 
	 * implements the Nimbus look and Feel from the UIManager class.
	 *
	 * @param args the command line arguments
    */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FileExplorerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display FileExplorerGUI */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileExplorerGUI().setVisible(true);
            }
        });
    }
}
