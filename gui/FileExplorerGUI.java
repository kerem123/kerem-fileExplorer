import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import supportingClasses.FileSignatureAnalyser;
import supportingClasses.PersistObjects;
import supportingClasses.SystemTreeOperation;
import supportingClasses.TimelineAnalyser;

/**
 * This is the GUI representation of the FileExplorer product.
 * 
 * @author Kerem Baybars
 * @version 1.0
 */
public class FileExplorerGUI extends JFrame {
	private static final long serialVersionUID = 2916810668702060495L;
	
    private JButton addToExplorerButton, attributesButton, removeAllButton, exploreButton, fileContentButton, 
    				refreshButton, removeSingleButton, saveButton, loadButton, signatureButton, timelineButton;
    private JComboBox<File> driverComboBox;
    private JLabel fileExplorerLabel, frameTitle, selectDriveLabel, jLabel2, helpLabel1, helpLabel2;
    private JMenu fileMenu;
    private JMenuItem jMenuItem1, newItem, openFile, quitItem;
    private JScrollPane tableScrollPane, treeScrollPane;
    private JPopupMenu.Separator jSeparator1;
    private JMenuBar menuBar;
    private JPanel optionsPanel;
    private JTable tableList;
    private JTree treeList;
    
  
    /**
     * Creates new form FileExplorerGUI
     */
    public FileExplorerGUI() {
    	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("File Explorer for Forensic Analysis ");
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(new Rectangle(0, 0, 0, 0));
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setName("fileExplorerFrame");
        
        initFrame();
    }

    /**
     * This method collects all the initialiser methods and is called from the main cosntructor.
     */
    private void initFrame() {
    	frameComponents();
    	frameEvents();
    	frameLayout();
    }
    
    /**
    * This method is called from within the constructor to initialise the form.
    * It initialises all the JFrame components.
    */        
    private void frameComponents() {
    	selectDriveLabel = new JLabel();
        driverComboBox = new JComboBox<File>(getSystemDrives());
        tableScrollPane = new JScrollPane();
        tableList = new JTable();
        treeScrollPane = new JScrollPane();
        treeList = new JTree();
        exploreButton = new JButton();
        jLabel2 = new JLabel();
        frameTitle = new JLabel();
        addToExplorerButton = new JButton();
        optionsPanel = new JPanel();
        attributesButton = new JButton();
        signatureButton = new JButton();
        timelineButton = new JButton();
        fileContentButton = new JButton();
        refreshButton = new JButton();
        helpLabel1 = new JLabel();
        loadButton = new JButton();
        saveButton = new JButton();
        removeAllButton = new JButton();
        fileExplorerLabel = new JLabel();
        removeSingleButton = new JButton();
        helpLabel2 = new JLabel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        newItem = new JMenuItem();
        openFile = new JMenuItem();
        jMenuItem1 = new JMenuItem();
        jSeparator1 = new JPopupMenu.Separator();
        quitItem = new JMenuItem();
        new JMenuItem();
        new JPopupMenu.Separator();
        new JMenuItem();
    }                        

    /**
     * This method sets the group layours for certain components.
     */
    private void frameLayout() {
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(896, 896, 896)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(helpLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(treeScrollPane, GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(selectDriveLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(driverComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exploreButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(addToExplorerButton)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(fileExplorerLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(removeSingleButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(removeAllButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(saveButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(loadButton))
                                .addComponent(tableScrollPane)
                                .addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(helpLabel2)
                                .addGap(138, 138, 138)))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(frameTitle)
                .addGap(252, 252, 252))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(frameTitle, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(selectDriveLabel)
                            .addComponent(driverComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(exploreButton)
                            .addComponent(refreshButton)
                            .addComponent(saveButton)
                            .addComponent(loadButton)
                            .addComponent(removeAllButton)
                            .addComponent(removeSingleButton))
                        .addGap(2, 2, 2))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(fileExplorerLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helpLabel2)
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(treeScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addToExplorerButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helpLabel1)
                        .addContainerGap(23, Short.MAX_VALUE))))
        );

        frameTitle.setIcon(new ImageIcon("C:\\Users\\kerem\\mmu\\year3\\project\\DesignStage\\productLogo.png"));
        this.pack();
    }
    
    /**
     * This method contains components and their respective events handlers. 
     * This is practically the main point of operations for this class.
     */
    private void frameEvents() {
    	selectDriveLabel.setFont(new Font("Tahoma", 1, 12)); 
        selectDriveLabel.setText("Please select a drive: ");

        tableList.setAutoCreateRowSorter(true);
        tableList.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"Name:", "Last Accessed:", "Type:", "Size:", "Full Path:"}
        ) {
			private static final long serialVersionUID = -2872560278830387957L;
        	
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
        });
        tableList.setBackground(Color.WHITE);
        tableList.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        tableScrollPane.setViewportView(tableList);

        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode();
        treeList.setModel(new DefaultTreeModel(treeNode1));
        treeList.setEditable(false);
        treeScrollPane.setViewportView(treeList);

        exploreButton.setBackground(new Color(255, 0, 51));
        exploreButton.setFont(new Font("Tahoma", 1, 11)); 
        exploreButton.setForeground(new Color(255, 255, 255));
        exploreButton.setText("Explore");
        exploreButton.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent evt) {
        		exploreButton.setBackground(Color.BLUE);
        	}
        	
        	public void mouseReleased(MouseEvent evt) {
        		exploreButton.setBackground(Color.RED);
        	}
        });
        exploreButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		if(driverComboBox.getSelectedItem() != null) {
                    File rootFile = (File) driverComboBox.getSelectedItem();
                    treeList.setModel(new SystemTreeOperation(rootFile));
                }
        	}
        });

        addToExplorerButton.setBackground(new Color(255, 0, 0));
        addToExplorerButton.setFont(new Font("Tahoma", 1, 12)); 
        addToExplorerButton.setForeground(new Color(255, 255, 255));
        addToExplorerButton.setText("Add to File Explorer");
        addToExplorerButton.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent evt) {
        		addToExplorerButton.setBackground(Color.BLUE);
        	}
        	public void mouseReleased(MouseEvent evt) {
        		addToExplorerButton.setBackground(Color.RED);
        	}
        });
        addToExplorerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		try {
        			DefaultTableModel tableModel = (DefaultTableModel) tableList.getModel();
        			File currentFile = null;
        			
        			if(treeList.getLastSelectedPathComponent() instanceof File) {
        				currentFile = (File) treeList.getLastSelectedPathComponent();
        			}
        			
        			if(currentFile == null) {
        				JOptionPane.showMessageDialog(FileExplorerGUI.this, "Please choose a file first!", "Error occurred", JOptionPane.ERROR_MESSAGE);
        			} else if(!currentFile.exists()) {
        				JOptionPane.showMessageDialog(FileExplorerGUI.this, "File or folder does not exist!", "Error occurred", JOptionPane.ERROR_MESSAGE);
        			} else if(currentFile.isDirectory()) {
        				JOptionPane.showMessageDialog(FileExplorerGUI.this, "You cannot add folders!", "Error occurred", JOptionPane.ERROR_MESSAGE);
        			} else if(currentFile.isFile()) {
        				Path path = currentFile.toPath();
            			BasicFileAttributes fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
                    
            			tableModel.addRow(new Object[]{currentFile.getName(), fileAttributes.lastModifiedTime(),
                    						com.google.common.io.Files.getFileExtension(currentFile.getName()).toUpperCase(), fileAttributes.size(), 
                    						currentFile.getAbsolutePath()});
        			} 
        		} catch (IOException ex) {
        			Logger.getLogger(FileExplorerGUI.class.getName()).log(Level.SEVERE, null, ex);
        		}
        	}
        });

        optionsPanel.setBorder(BorderFactory.createTitledBorder(null, "Choose an option:", TitledBorder.DEFAULT_JUSTIFICATION, 
        						TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 12)));

        fileContentButton.setBackground(new Color(0, 0, 255));
        fileContentButton.setFont(new Font("Tahoma", 1, 11)); 
        fileContentButton.setForeground(new Color(255, 255, 255));
        fileContentButton.setText("View File Content");
        fileContentButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
            	fileContentButton.setBackground(Color.RED);
            }
            public void mouseReleased(MouseEvent evt) {
            	fileContentButton.setBackground(Color.BLUE);
            }
        });
        fileContentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableList.getSelectedRow() != -1) {
					File file = new File(new String((String) tableList.getValueAt(tableList.getSelectedRow(), 4)));
					openContent(file);
		        } else {
		        	JOptionPane.showMessageDialog(FileExplorerGUI.this, "Please select a file!", "Error occurred", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
        
        attributesButton.setBackground(new Color(0, 0, 255));
        attributesButton.setFont(new Font("Tahoma", 1, 11)); 
        attributesButton.setForeground(new Color(255, 255, 255));
        attributesButton.setText("View File Attributes");
        attributesButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
            	attributesButton.setBackground(Color.RED);
            }
            public void mouseReleased(MouseEvent evt) {
            	attributesButton.setBackground(Color.BLUE);
            }
        });
        attributesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableList.getSelectedRow() != -1) {
					File file = new File(new String((String) tableList.getValueAt(tableList.getSelectedRow(), 4)));
					viewFileAttributes(file);
		        } else {
		        	JOptionPane.showMessageDialog(FileExplorerGUI.this, "Please select a file!", "Error occurred", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});

        signatureButton.setBackground(new Color(0, 0, 255));
        signatureButton.setFont(new Font("Tahoma", 1, 11));
        signatureButton.setForeground(new Color(255, 255, 255));
        signatureButton.setText("Signature Analysis");
        signatureButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
            	signatureButton.setBackground(Color.RED);
            }
            public void mouseReleased(MouseEvent evt) {
            	signatureButton.setBackground(Color.BLUE);
            }
        });
        signatureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableList.getSelectedRow() != -1) {
					File file = new File(new String((String) tableList.getValueAt(tableList.getSelectedRow(), 4)));
					
					getSignatureAnalysis(file);
				} else {
		        	JOptionPane.showMessageDialog(FileExplorerGUI.this, "Please select a file!", "Error occurred", JOptionPane.ERROR_MESSAGE);
		        }
				
			}
		});

        timelineButton.setBackground(new Color(0, 0, 255));
        timelineButton.setFont(new Font("Tahoma", 1, 11));
        timelineButton.setForeground(new Color(255, 255, 255));
        timelineButton.setText("Timeline Analysis");
        timelineButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
            	timelineButton.setBackground(Color.RED);
            }
            public void mouseReleased(MouseEvent evt) {
            	timelineButton.setBackground(Color.BLUE);
            }
        });
        timelineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableList.getRowCount() > 0) {
					getTimelineAnalysis();
				} else {
		        	JOptionPane.showMessageDialog(FileExplorerGUI.this, "Make sure there are enough files!", "Error occurred", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});

        GroupLayout optionsPanelLayout = new GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, optionsPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fileContentButton)
                .addGap(18, 18, 18)
                .addComponent(attributesButton)
                .addGap(18, 18, 18)
                .addComponent(signatureButton)
                .addGap(18, 18, 18)
                .addComponent(timelineButton)
                .addContainerGap())
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(attributesButton)
                .addComponent(signatureButton)
                .addComponent(timelineButton)
                .addComponent(fileContentButton))
        );

        helpLabel1.setFont(new Font("Tahoma", 0, 9)); 
        helpLabel1.setText("Click this to add chosen file to File Explorer!");

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				driverComboBox.removeAllItems();
				
				File[] driverFiles = File.listRoots();
				for(int i=0; i<driverFiles.length; i++) {
					driverComboBox.addItem(driverFiles[i]);
				}
			}
		});
        
        saveButton.setText("Save");
        saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersistObjects.getSingletonPersistObjects();
				
				if(tableList.getRowCount() > 0) {
					int row = tableList.getRowCount();
					int columns = tableList.getColumnCount();
					
					Object[][] objArray = new Object[row][columns];
					for(int i=0; i<row; i++) {
						for(int j=0; j<columns; j++) {
							objArray[i][j] = tableList.getValueAt(i,j);
						}
					}
					
					PersistObjects.serialiseAndSave(objArray);
				} else {
					JOptionPane.showMessageDialog(FileExplorerGUI.this, "There is nothing to save!", "Error occurred", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
        
        loadButton.setText("Load");
        loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersistObjects.getSingletonPersistObjects();
				DefaultTableModel tableModel = (DefaultTableModel) tableList.getModel();
				
				if(tableList.getRowCount() > 0) {
					int choice = JOptionPane.showConfirmDialog(FileExplorerGUI.this, "Would you like to save your progress first?", "Before loading.", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION) {
						saveButton.doClick();
						tableModel.setRowCount(0);
						
						Object[][] objList = PersistObjects.deSerialiseAndLoad();
						if(objList != null) {
							for(int j=0; j<objList.length; j++) {
								tableModel.addRow(objList[j]);
							}
						}
					} else if(choice == JOptionPane.NO_OPTION) {
						tableModel.setRowCount(0);
						
						Object[][] objList = PersistObjects.deSerialiseAndLoad();
						if(objList != null) {
							for(int j=0; j<objList.length; j++) {
								tableModel.addRow(objList[j]);
							}
						}
					}
				} else {
					Object[][] objList = PersistObjects.deSerialiseAndLoad();
					if(objList != null) {
						for(int j=0; j<objList.length; j++) {
							tableModel.addRow(objList[j]);
						}
					}
				}
			}
		});
        
        removeSingleButton.setText("Remove");
        removeSingleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) tableList.getModel();
				
				if (tableList.getSelectedRow() != -1) {
					tableModel.removeRow(tableList.getSelectedRow());
		        } else {
		        	JOptionPane.showMessageDialog(FileExplorerGUI.this, "You havent selected a file!", "Error occurred", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
        
        removeAllButton.setText("Remove All");
        removeAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	if(tableList.getRowCount() > 0) {
            		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove all elements?","You decided to remove all elements!",JOptionPane.OK_CANCEL_OPTION);
                    
                    if(option == JOptionPane.OK_OPTION) {
                    	DefaultTableModel tableModel = (DefaultTableModel) tableList.getModel();
                        tableModel.setRowCount(0);
                    } else if(option == JOptionPane.CANCEL_OPTION) {} // Do nothing and return to application
            	} else {}
            }
        });
        
        fileExplorerLabel.setFont(new Font("Tahoma", 1, 12)); 
        fileExplorerLabel.setText("File Explorer:");

        helpLabel2.setFont(new Font("Tahoma", 0, 9)); 
        helpLabel2.setText("Choose one of the four options above to analyse each selected file in the File Explorer!");

        menuBar.setForeground(new Color(255, 0, 51));

        fileMenu.setText("File");

        newItem.setText("New");
        newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to open a new window?","You decided to leave!",JOptionPane.OK_CANCEL_OPTION);
                
                if(option == JOptionPane.OK_OPTION) {
                	new FileExplorerGUI().setVisible(true);
    				FileExplorerGUI.this.setVisible(false);
                } else if(option == JOptionPane.CANCEL_OPTION) {} // Do nothing and return to application
			}
		});
        fileMenu.add(newItem);

        openFile.setText("Load");
        openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadButton.doClick();
			}
		});
        fileMenu.add(openFile);

        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveButton.doClick();
			}
		});
        fileMenu.add(jMenuItem1);
        fileMenu.add(jSeparator1);

        quitItem.setText("Quit");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?","You decided to leave!",JOptionPane.OK_CANCEL_OPTION);
                
                if(option == JOptionPane.OK_OPTION) {
                    System.exit(0);
                } else if(option == JOptionPane.CANCEL_OPTION) {} // Do nothing and return to application
            }
        });
        fileMenu.add(quitItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    /**
     * The getSystemDrives() compiles a list of all available drivers on the current system
     * and places them in a File array then returns it's reference.
     * 
     * @return file array containing system drives.
     */
    private File[] getSystemDrives() {
    	return File.listRoots();
    }
    
    private void openContent(File file) {
    	JTextArea contentArea = new JTextArea(20, 50);
    	contentArea.setEditable(false);
    	
    	JScrollPane contentScrollPane = new JScrollPane(contentArea);
    	try {
			contentArea.setText(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))));
			JOptionPane.showMessageDialog(this, contentScrollPane, "Reading: '"+file.getName()+"'", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(FileExplorerGUI.this, "Cannot read file!", "Error occurred", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    /**
     * This method views file attributes from a selected file.
     * 
     * @param file - The chosen file
     */
    private void viewFileAttributes(File file) {
    	JTable attributesTable;
    	
    	Path path = file.toPath();
		BasicFileAttributes fileAttributes;
		try {
			fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
			SimpleDateFormat dates = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
			
			String[] columnHeadings = {"Attribute ID:", "Values:"};
			Object[][] rows = {
					{"Name: ", file.getName()},
					{"Type: ", com.google.common.io.Files.getFileExtension(file.getName()).toUpperCase()},
					{"Full Path: ", file.getAbsolutePath()},
					{"Size :", fileAttributes.size()+" bytes"},
					{"Date Created: ", dates.format(fileAttributes.creationTime().toMillis()).toString()},
					{"Last Accessed: ", dates.format(fileAttributes.lastAccessTime().toMillis()).toString()},
					{"Last Modified: ", dates.format(fileAttributes.lastModifiedTime().toMillis()).toString()},
					{"Is Hidden: ", Files.isHidden(path)},
					{"Exists: ", file.isFile()},
					{"Is Directory: ", file.isDirectory()},
					{"Is Ordinary: ", fileAttributes.isRegularFile()},
					{"Is Symbolic Link: ", fileAttributes.isSymbolicLink()}
			};
			
			attributesTable = new JTable(rows, columnHeadings);
			attributesTable.setPreferredScrollableViewportSize(new Dimension(350, 160));
			attributesTable.setAutoCreateRowSorter(true);
			attributesTable.setFillsViewportHeight(true);
	        
	        JScrollPane attScrollPane = new JScrollPane(attributesTable);
	        attScrollPane.setBorder(null);

	        JOptionPane.showMessageDialog(this, attScrollPane, "Reading attributes for : '"+file.getName()+"'", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * This method carries out file signature detection.
     * 
     * @param file
     */
    private void getSignatureAnalysis(File file) {
    	FileSignatureAnalyser.getSingletonFileSignatureAnalyser();
			
		JFrame signatureFrame = new JFrame();
		signatureFrame.setSize(new Dimension(700,500));
		signatureFrame.setVisible(true);
			
		JTextArea contentArea = new JTextArea(20, 50);
	    contentArea.setEditable(false);
	    	
	    JScrollPane signatureScrollPane = new JScrollPane(contentArea);
	    signatureScrollPane.setBounds(200, 200, 100, 100);
	    signatureFrame.add(signatureScrollPane);
	    try {
			contentArea.setText(new String(FileSignatureAnalyser.analyseFileSignature(file)));
			JOptionPane.showMessageDialog(signatureFrame, FileSignatureAnalyser.getSignatureResult(file, FileSignatureAnalyser.analyseFileSignature(file)), 
											"Your File Signature results...", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(FileExplorerGUI.this, "Cannot read file!", "Error occurred", JOptionPane.ERROR_MESSAGE);
		} 
    }
    
    /**
     * This method carries out timeline analysis
     */
    private void getTimelineAnalysis() {
    	TimelineAnalyser.getSingletonTimelineAnalyser();
    	
    	tableList.getRowSorter().toggleSortOrder(1);
    	
    	int row = tableList.getRowCount();
		int columns = 2;
		Object[][] objArray = new Object[row][columns];
		for(int i=0; i<row; i++) {
			for(int j=0; j<columns; j++) {
				objArray[i][j] = tableList.getValueAt(i,j);
			}
		}
		
		TimelineAnalyser.doTimelineAnalysis(objArray);
    }
}
