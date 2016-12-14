package menus;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GConstants;
import constants.GConstants.EFileMenuItem;
import frames.GDrawingPanel;
import shapes.GShape;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	// association
	private GDrawingPanel drawingPanel;

	public GFileMenu() {
		super(GConstants.FILEMENU_TITLE);
		ActionHandler actionHandler = new ActionHandler();
		for (EFileMenuItem eMenuItem: EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	private void newFile() {
		drawingPanel.clearShapeList();
	}
	private void open() {
		JFileChooser fileDialog = new JFileChooser(new File("."));
		fileDialog.showOpenDialog(null);
		File file = fileDialog.getSelectedFile();
		ObjectInputStream in = null;
		if (file != null) {
			try {
				in = new ObjectInputStream(new BufferedInputStream(
						new FileInputStream(file)));
				Object obj = in.readObject();
				drawingPanel.setShapeList((Vector<GShape>) obj);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null) in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void save() {
		JFileChooser fileDialog = new JFileChooser(new File("."));
		fileDialog.showSaveDialog(null);
		File file = fileDialog.getSelectedFile();
		ObjectOutputStream out = null;
		if (file != null) {
			try {
				out = new ObjectOutputStream(new BufferedOutputStream(
						new FileOutputStream(file)));
				out.writeObject(drawingPanel.getShapeVector());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void close() {
		System.exit(0);
	}
	private String showDialog() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Graphics Editor", "gps");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	return chooser.getSelectedFile().getName();
	    }
	    return null;		
	}
	

	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(EFileMenuItem.valueOf(e.getActionCommand())){
				case nnew: newFile(); break;
				case open: open(); break;
				case save: save(); break;
				case saveAs: save(); break;
				case close: close(); break;
			}
		}		
	}
}
