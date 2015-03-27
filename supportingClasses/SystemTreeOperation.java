import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * This is a table model class which allows JTree components to be operational.
 * 
 * @author Kerem Baybars
 * @version 1.0
 */
public class SystemTreeOperation implements TreeModel {
    private final File fileRoot;
    private final ArrayList<TreeModelListener> treeListeners;
    private Iterator<TreeModelListener> treeIterator;
    private TreeModelListener treeModelListener;
	
    /**
     * Initialises TreeModelListener and Arraylist
     * 
     * @param file - Chosen file
     */
    public SystemTreeOperation(File file) {
	this.fileRoot = file;
	treeListeners = new ArrayList<TreeModelListener>();
	treeModelListener = null;
    }

    /**
     * returns the root object.
     */
    public Object getRoot() {
        return fileRoot;
    }

    /**
     * Returns the child node
     */
    public Object getChild(Object parent, int index) {
        File childNode = (File) parent;
        String[] childNodes = childNode.list();
		
        return new GetSelectedNode(childNode, childNodes[index]);
    }

    /**
     * retrieves the node count
     */
    public int getChildCount(Object parent) {
    	if(((File) parent).isDirectory()) {
    		if(((File)parent).list() != null) {
    			return ((File)parent).list().length;
    		}
    	}
        return 0;
    }

    /**
     * returns true if the node is a leaf node
     */
    public boolean isLeaf(Object leafNode) {
        return ((File) leafNode).isFile();
    }

    /**
     * Fires an event handler when a path for value is changed.
     */
    public void valueForPathChanged(TreePath path, Object newValue) {
        File previousFile = (File) path.getLastPathComponent();
        String currentFile = previousFile.getParent();
        File nextFile = new File(currentFile, (String) newValue);
        previousFile.renameTo(nextFile);
		
        int[] numberOfChildNodesChanged = {getIndexOfChild(new File(currentFile), nextFile)};
        Object[] nodesChanged = {nextFile};
        fireTreeNodesChanged(path.getParentPath(), numberOfChildNodesChanged, nodesChanged);
    }

    /**
     * Gets the child node position offset.
     */
    public int getIndexOfChild(Object parent, Object child) {
        String[] parentNodes = ((File) parent).list();
        File childNode = (File) child;
        
        for(int j = 0; j < childNode.length(); j++) {
            if(childNode.getName().equals(parentNodes[j])) {
            	return j;
            }
        }
        return -1;
    }

    /**
     * Returns the following parameters
     * 
     * @param treeRoot - root node
     * @param indexes - tree index
     * @param childNodes - child nodes
     */
    private void fireTreeNodesChanged(TreePath treeRoot, int[] indexes, Object[] childNodes) {
        TreeModelEvent event = new TreeModelEvent(SystemTreeOperation.this, treeRoot, indexes, childNodes);
        treeIterator = treeListeners.iterator();
		
        while (treeIterator.hasNext()) {
            treeModelListener = (TreeModelListener) treeIterator.next();
            treeModelListener.treeNodesChanged(event);
        }
    }
	
    /**
     * Adds TreeModelListeners
     */
    public void addTreeModelListener(TreeModelListener l) {
    	treeListeners.add(l);
    }

    /**
     * Removes tree model listeners
     */
    public void removeTreeModelListener(TreeModelListener l) {
    	treeListeners.remove(l);
    }
	
    /**
     * This is a nested File class.
     * 
     * @author Kerem Baybars
     * @version 1.0
     */
    private class GetSelectedNode extends File {
		private static final long serialVersionUID = -7496230650446667467L;

		/**
		 * Gets the selected node
		 * 
		 * @param parentFile - parent node
		 * @param childNode - child node
		 */
		public GetSelectedNode(File parentFile, String childNode) {
			super(parentFile, childNode);
		}
		
		/**
		 * Returns a string representation of the file.
		 */
        public String toString() {
            return (this.getName());
        }
    }
}
