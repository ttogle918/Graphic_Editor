package frames;
import javax.swing.JMenuBar;

import menus.GEditMenu;
import menus.GFileMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private GDrawingPanel drawingpanel;
	public GFileMenu fileMenu;
	public GEditMenu editMenu;
	GMenuBar() {
		fileMenu = new GFileMenu();
		this.add(fileMenu);
		editMenu = new GEditMenu();
		this.add(editMenu);
		drawingpanel = new GDrawingPanel();
	}

	public void initialize(GDrawingPanel drawingpanel) {
		// TODO Auto-generated method stub
		this.drawingpanel = drawingpanel;
		fileMenu.initialize(drawingpanel);
	}
}
