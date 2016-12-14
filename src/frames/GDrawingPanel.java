package frames;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;
import shapes.GGroup;
import shapes.GSelect;
import shapes.GShape;
import tranformer.GDrawer;
import tranformer.GMover;
import tranformer.GResizer;
import tranformer.GRotator;
import tranformer.GTransformer;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// object states
	private enum EState {idle, drawingTP, drawingNP, transforming};
	private EState eState;
	// components
	private Vector<GShape> shapeVector;
	private GCursorManager cursors;
	private MouseEventHandler mouseEventHandler;
	// associative attributes
	private Color lineColor, fillColor;
	private GShape selectedShape;
	private int cx, cy; // rotater �� ������ �߽���ǥ�� �޾ƿ��� ����. 
	private boolean shiftDown;
	private GClipboard clipBoard;
	
	public void setSelectedShape(GShape selectedShape) {
		this.selectedShape = selectedShape;
	}	
	// working objects;
	private GShape currentShape;
	private GTransformer currentTransformer;
	
	public GDrawingPanel() {
		super();
		// attributes
		this.eState = EState.idle;
		// components
		this.shapeVector = new Vector<GShape>();		
		this.mouseEventHandler = new MouseEventHandler();
		cursors = new GCursorManager();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		// working variables
		this.selectedShape = null;
		this.currentShape = null;
		this.currentTransformer = null;
		shiftDown = false;
		clipBoard = new GClipboard();
		
	}
	public void initialize() {
		lineColor = GConstants.COLOR_LINE_DEFAULT;
		fillColor = GConstants.COLOR_FILL_DEFAULT;
	}
	public void setShapeList(Vector<GShape> shapeList){
		this.shapeVector = shapeList;
		repaint();
	}
	public void clearShapeList() {
		shapeVector.clear();
		repaint();
	}
	public void paint(Graphics g) {
		super.paint(g);
		for (GShape shape: this.shapeVector) {
			shape.draw((Graphics2D)g);
		}
	}
	public void paste(){
		for(GShape shape: this.clipBoard.paste()){
			shapeVector.add(shape.deepCopy());
		}
		repaint();
	}
	public void cut() {
		clipBoard.cut(this.shapeVector);
		repaint();
	}
	public void copy(){
		clipBoard.copy(shapeVector);
	}
	public void delete(){
		for(int i = shapeVector.size(); i > 0; i--){
			GShape shape = shapeVector.get(i-1);
			if(shape.isSelected()){
				shape.setSelected(false);
				shapeVector.remove(shape);
			}
		}
		repaint();
	}
	public void setLineColor(Color lineColor) {
		if (selectedSetColor(true, lineColor)) {
			return;
		}
		this.lineColor = lineColor;
	}
	public void setFillColor(Color fillColor) {
		if (selectedSetColor(false, fillColor)) {
			return;
		}
		this.fillColor = fillColor;
	}
	private void resetSelected() {
		for (GShape shape: this.shapeVector) {
			shape.setSelected(false);
		}
		this.repaint();
	}
	private boolean selectedSetColor(boolean flag, Color color) {
		boolean returnValue = false;
		for(GShape shape: shapeVector){
			if (shape.isSelected()) {
				if (flag) {
					shape.setLineColor(color);
				} else {
					shape.setFillColor(color);
				}
				returnValue = true;
			}
		}
		repaint();
		return returnValue;
	}
	public void group(GGroup group) {
		for (int i = shapeVector.size(); i > 0; i--) {
			GShape shape = shapeVector.get(i - 1);
			if(shape.isSelected()){
				shape.setSelected(false);
				group.addShape(shape);
				shapeVector.remove(shape);
				shapeVector.add(group);
			}
		}
		repaint();
	}
	// �׷� ����
	public void shiftState(boolean shiftState){
		this.shiftDown = shiftState;
	}
	public void unGroup() {
		Vector<GShape> tempList = new Vector<GShape>();
		for (int i = shapeVector.size(); i > 0; i--) {
			GShape shape = shapeVector.get(i - 1);
			// shape ��ü������ GGroupŸ���̰�, ���õǾ��ִٸ� �Ʒ��� �����Ѵ�.
			if(shape instanceof GGroup && shape.isSelected()){
				for(GShape childShape: ((GGroup)shape).getGroupList()){
					childShape.setSelected(true);
					tempList.add(childShape);
				}
				shapeVector.remove(shape);
			}
		}
		shapeVector.addAll(tempList);
		repaint();
	}
	public void clearSelectedShapes() {
		for (GShape shape : shapeVector) {
		shape.setSelected(false);
		}
	}
	public void inputTheta(double theta){
		if(selectedShape == null){ //��ǥ�� ���� ���� �ʱ� ������ ������ selectedShape�� ������ onshape�� ������ ����.
		selectedShape = onShape(this.cx, this.cy); //�����ȿ� Ŭ���� ��ǥ�� ������ �������� ����.
		}
		if(currentShape instanceof GSelect){ // currentShape�� toolbar���� ����.
			if(selectedShape !=null){
				selectedShape.setSelected(true);
				selectedShape.contnains(this.cx, this.cy);
				currentTransformer = new GRotator(selectedShape);
				((GRotator)currentTransformer).setTheata(theta);
				((GRotator)currentTransformer).angleRotate((Graphics2D)getGraphics());
				repaint();
			}
		}
	}	
	private void initTransforming(int x, int y) {
		if (this.currentShape == null) {
			this.currentShape= this.selectedShape.clone();
			this.currentShape.setSelected(true);
			this.currentTransformer = new GDrawer(this.currentShape);
		} else if (this.currentShape.getCurrentEAnchor() == EAnchors.MM) {
			this.currentTransformer = new GMover(this.currentShape);
		} else if (this.currentShape.getCurrentEAnchor() == EAnchors.RR) {
			this.currentTransformer = new GRotator(this.currentShape);
		} else {			
			this.currentTransformer = new GResizer(this.currentShape);
		}
		this.resetSelected();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.initTransforming(x, y, g2D);
	}
	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.keepTransforming(x, y, g2D);
	}
	private void continueTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.continueTransforming(x, y, g2D);
	}
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.finishTransforming(x, y, g2D);
		
		if (this.currentTransformer.getClass().getSimpleName().equals("GDrawer")) {
			//gselect Ŭ�����ϰ���??
			this.shapeVector.add(this.currentShape);
		}
		this.currentShape.setSelected(true);
		this.repaint();
	}
	private GShape onShape(int x, int y) {
		for (GShape shape: this.shapeVector) {
			GConstants.EAnchors eAnchor = shape.contnains(x, y);
			if (eAnchor != null)
				return shape;
		}
		return null;
	}
	private void changeCursor(GShape shape) {
		if (shape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		this.setCursor(shape.getCurrentEAnchor().getCursor());
	}
	public Vector<GShape> getShapeVector() { return this.shapeVector; }
	
	class MouseEventHandler 
		implements MouseInputListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
 		}
		private void mouse1Clicked(MouseEvent e) {
			if (eState == EState.idle) {
				currentShape = onShape(e.getX(), e.getY());
				if (currentShape == null) {
					if (selectedShape.geteDrawingType()==EDrawingType.NP) {
						initTransforming(e.getX(), e.getY());
						eState = EState.drawingNP;
					}
				}
			} else if (eState == EState.drawingNP) {	
				continueTransforming(e.getX(), e.getY());			
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			if (eState == EState.drawingNP) {		
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			}			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if (eState == EState.idle) {
				currentShape = onShape(e.getX(), e.getY());
				if (currentShape == null) {
					if (selectedShape.geteDrawingType()==EDrawingType.TP) {
						initTransforming(e.getX(), e.getY());
						eState = EState.drawingTP;
					}
				} else {
					initTransforming(e.getX(), e.getY());
					eState = EState.transforming;
				}
			}	
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eState == EState.drawingTP) {		
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			} else if (eState == EState.transforming) {
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			} 
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawingNP) {
				keepTransforming(e.getX(), e.getY());
			} else if (eState == EState.idle) {
				GShape shape = onShape(e.getX(), e.getY());
				changeCursor(shape);
			}
		}		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawingTP) {
				keepTransforming(e.getX(), e.getY());
			} else if (eState == EState.transforming) {
				keepTransforming(e.getX(), e.getY());				
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	}
}
