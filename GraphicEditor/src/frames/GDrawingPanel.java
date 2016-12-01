package frames;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;
import shapes.Anchors;

import shapes.GPolygon;
import shapes.GShape;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GRotater;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// object states
	private enum EState {idle, drawingTP, drawingNP, transforming};
	private EState eState;
	// components
	private Vector<GShape> shapeVector;	
	MouseEventHandler mouseEventHandler; //상태를가지지않기때문에.. 딱히..
	// associative attributes
	private GShape selectedShape;
	private GShape onshape;
	
	// working objects;
	private GShape currentShape;
	private GCursorManager cursorManager;
	private GTransformer currentTransformer;

		
	public GDrawingPanel() {
		mouseEventHandler = new MouseEventHandler();
		// attributes
		eState = EState.idle;
		//components
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		this.shapeVector = new Vector<GShape>();
		this.setOpaque(false);
		//working variables
		this.selectedShape = null;
		this.currentShape = null;
		this.currentTransformer = null;
	}
	public void initialize() {
	}
	public void setSelectedShape(GShape selectedShape) {
		this.selectedShape = selectedShape;
		eState = EState.idle;
	}
	public void paint(Graphics g) {
		for (GShape shape: this.shapeVector) {
			shape.draw((Graphics2D)g);
		}
	}
	private void resetSelected() {
		for (GShape shape: this.shapeVector) {
			shape.setSelected(false);
		}
		this.repaint();
	}

	private void initDrawing(int x, int y) {
		if(this.currentShape == null){
			
		}
		this.resetSelected();
		this.currentShape= this.selectedShape.clone();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.initTransforming(x, y, g2D);
	}
	
	private void keepDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.keepTransforming(x, y, g2D);
	}
	private void continueDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.continueTransforming(x, y, g2D);
	}
	private void finishDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.finishTransforming(x, y, g2D);
		this.shapeVector.add(this.currentShape);
		this.currentShape.setSelected(true);
		this.repaint();
	}
	private void initTransforming(int x, int y) {
		if(this.currentShape == null){
			this.currentShape = this.selectedShape.clone();
			this.currentTransformer = new GDrawer(this.currentShape);
		}else if(this.currentShape.getCurrentEAnchor() == EAnchors.MM){
			this.currentTransformer = new GMover(this.currentShape);		
		}else if(this.currentShape.getCurrentEAnchor() == EAnchors.RR){
			this.currentTransformer = new GRotater(this.currentShape);	
		}
		this.resetSelected();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
	}
	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
	//	this.currentTransformer.
	}
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.finishTransforming(x, y, g2d);
		this.currentShape.setSelected(true);
		if(this.currentTransformer.getClass().getSimpleName().equals("GDrawer")){
			
		}
		this.repaint();
	}
	private GShape onShape(int x, int y) {
		for (GShape shape: this.shapeVector) {
			GConstants.EAnchors eAnchor = shape.contains(x, y);
			if (eAnchor != null)
				return shape;
		}
		return null;
	}
	private void changeCursor(GShape shape) {
		if(shape == null){
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		this.setCursor(shape.getCurrentEAnchor().getCursor());
	}
	// eventhandler는 교통정리만. 내용이 있으면 안됨.
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
		// polygon일때
		private void mouse1Clicked(MouseEvent e) {
			if (eState == EState.idle) {
				currentShape = onShape(e.getX(), e.getY());
				if (currentShape == null) {
					if(selectedShape.geteDrawingType() == EDrawingType.NP){
						initDrawing(e.getX(), e.getY());
						eState = EState.drawingNP;
					}else if (eState == EState.drawingNP) {	
						continueDrawing(e.getX(), e.getY());			
					}
				}
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			if (eState == EState.drawingNP) {		
				finishDrawing(e.getX(), e.getY());
				eState = EState.idle;
			}	
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if (eState == EState.idle) {
				currentShape = onShape(e.getX(), e.getY());
				if (currentShape == null) {
					if(selectedShape.geteDrawingType() == EDrawingType.TP){
						initDrawing(e.getX(), e.getY());
						eState = EState.drawingTP;
					}else{
						initTransforming(e.getX(), e.getY());
						eState = EState.transforming;
					}
				}
			}			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eState == EState.drawingTP) {		
				finishDrawing(e.getX(), e.getY());
				eState = EState.idle;
			}else if (eState == EState.transforming) {
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawingNP) {
				keepDrawing(e.getX(), e.getY());
			}else if(eState == EState.idle){
				GShape shape = onShape(e.getX(), e.getY());
				changeCursor(shape);
			}
		}	
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawingTP) {
				keepDrawing(e.getX(), e.getY());
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
