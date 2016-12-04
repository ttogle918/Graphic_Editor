package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GConstants;
import constants.GConstants.EFileMenuItem;
import frames.GDrawingPanel;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private GDrawingPanel drawingPanel;

	public GFileMenu() {
		super(GConstants.FILEMENU_TITLE);
		ActionHandler actionhandler = new ActionHandler();
		for (EFileMenuItem emenuItem : EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(emenuItem.getText());
			menuItem.addActionListener(actionhandler);
			menuItem.setActionCommand(emenuItem.name());
			this.add(menuItem);
		}
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	private void open(){
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(this.getParent())==JFileChooser.APPROVE_OPTION) {
		  File file = fileChooser.getSelectedFile();
		  // load from file
		}
	}
	private String ShowDialog(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor"," gps");
		chooser.setFileFilter(filter);
			
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			return  chooser.getSelectedFile().getName();
		}
		return null;
		
	}
	private void save(){
		String fileName = ShowDialog();
		if(fileName == null){
			return;
		}
		try {
			File file = new File(fileName);
			ObjectOutputStream outputStream;
			outputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(drawingPanel.getShapeVector());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.open.name())){
				open();
			}else if(e.getActionCommand().equals(EFileMenuItem.save.name())){
				save();
			}
			
		}
		
	}
}
