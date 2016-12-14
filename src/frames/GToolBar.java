package frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import constants.GConstants.EToolBarButton;
public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	// association	
	private GDrawingPanel drawingPanel;
	private boolean shiftDown; // shift 유무 전달용
	private JTextField tField; // 각도 입력
	
	public GToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		ActionHandler actionHandler = new ActionHandler();
		for (EToolBarButton eToolBarButton: EToolBarButton.values()) {
			JRadioButton button = new JRadioButton();
			// attributes of components
			button.setIcon(new ImageIcon(eToolBarButton.getIconName()));
			button.setSelectedIcon(new ImageIcon(eToolBarButton.getSelectedIconName()));
			button.addActionListener(actionHandler);
			button.setActionCommand(eToolBarButton.toString());					
			this.add(button);
			buttonGroup.add(button);
		}		
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
		JRadioButton button = (JRadioButton) this.getComponentAtIndex(EToolBarButton.rectangle.ordinal());
		button.doClick();
	}
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawingPanel.setSelectedShape(
					EToolBarButton.valueOf(e.getActionCommand()).getShape());
			System.out.println(e.getActionCommand());
		}
	}
}
