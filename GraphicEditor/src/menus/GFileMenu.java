package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
		File file = new File(fileName);
		
	}
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.open.name())){
				
			}else if(e.getActionCommand().equals(EFileMenuItem.save.name())){
				
			}
			
		}
		
	}
}
