package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EEditMenuItem;
import frames.GDrawingPanel;
import shapes.GGroup;
import shapes.GShape;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	// association
	private GDrawingPanel drawingPanel;
	private Vector<GShape> copyList;
	public GEditMenu() {
		super(GConstants.EDITMENU_TITLE);
		for (GConstants.EEditMenuItem eMenuItem: GConstants.EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(eMenuItem.toString());
			this.add(menuItem);
		}
		copyList = new Vector<GShape>();
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	private void unGroup() {
		drawingPanel.unGroup();
	}
	private void group() {
		drawingPanel.group(new GGroup());
	}
	private void copy() {
		drawingPanel.copy();
	}
	private void paste(){
		drawingPanel.paste();
	}
	private void cut() {
		drawingPanel.cut();
	}
	private void delete(){
		drawingPanel.delete();
	}
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (EEditMenuItem.valueOf(e.getActionCommand())) {
			case group: group(); break;
			case unGorup: unGroup(); break;
			case redo: break;
			case undo: break;
			case copy: copy(); break;
			case paste: paste(); break;
			case delete: delete(); break;
			case cut: cut(); break;
			}
		}
	}
}
