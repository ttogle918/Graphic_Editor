package frames;
import javax.swing.JMenuBar;

import menus.GEditMenu;
import menus.GFileMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	// components
	public GFileMenu fileMenu;
	public GEditMenu editMenu;
	// association
	private GDrawingPanel drawingPanel;
	GMenuBar() {
		fileMenu = new GFileMenu();
		this.add(fileMenu);
		editMenu = new GEditMenu();
		this.add(editMenu);
	}

	public void initialize(GDrawingPanel drawingpanel) {
		// TODO Auto-generated method stub
		this.drawingPanel = drawingpanel;
		this.fileMenu.initialize(drawingpanel);
		this.editMenu.initialize(this.drawingPanel);
	}
}
