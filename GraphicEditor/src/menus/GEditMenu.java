package menus;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import frames.GDrawingPanel;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private GDrawingPanel drawingPanel;
	
	public GEditMenu() {
		super(GConstants.EDITMENU_TITLE);
		for (GConstants.EEditMenuItem eMenuItem: GConstants.EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		// TODO Auto-generated method stub
		this.drawingPanel = drawingPanel;	
	}

}
