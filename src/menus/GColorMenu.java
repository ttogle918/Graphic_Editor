package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EColorMenuItems;
import frames.GDrawingPanel;

public class GColorMenu extends JMenu {
	private GDrawingPanel drawingPanel;
	private ActionHandler actionHandler;
	public GColorMenu() {
		super(GConstants.COLOTMENU_TITLE);
		actionHandler = new ActionHandler();
		for (EColorMenuItems items : EColorMenuItems.values()) {
			JMenuItem menuItem = new JMenuItem(items.name());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(items.name());
			this.add(menuItem);
		}
	}
	public void initialize(GDrawingPanel drawingPanel) {
		// TODO Auto-generated method stub
		this.drawingPanel = drawingPanel;
	}
	private void setLineColor() {
		Color lineColor = JColorChooser.showDialog(null, GConstants
				.LINECOLOR_TITLE, null);
		if (lineColor != null) {
			drawingPanel.setLineColor(lineColor);
		}
	}
	private void setFillColor() {
		Color fillColor = JColorChooser.showDialog(null,
				GConstants.FILLCOLOR_TITLE, null);
		if (fillColor != null) {
			drawingPanel.setFillColor(fillColor);
		}
	}
	private void delLineColor() {
		drawingPanel.setLineColor(GConstants.COLOR_LINE_DEFAULT);
	}
	private void delFillColor() {
		drawingPanel.setFillColor(GConstants.COLOR_FILL_DEFAULT);
	}
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (EColorMenuItems.valueOf(e.getActionCommand())) {
			case SetLineColor:
				setLineColor(); break;
			case SetFillColor:
				setFillColor(); break;
			case ClearLineColor:
				delLineColor(); break;
			case ClearFillColor:
				delFillColor(); break;
			}
		}
	}
}
