package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;

abstract public class GShape implements Serializable{
	// attributes
		private EDrawingType eDrawingType;
		protected boolean selected;
		private EAnchors currentEAnchor;
		// components
		private Shape shape;	
		protected Anchors anchors;
		// working variables
		protected int px, py;
		// getters & setters
		public EDrawingType geteDrawingType() {	return eDrawingType;}
		public boolean isSelected() { return selected; }
		public void setSelected(boolean selected) { this.selected = selected; }
		public EAnchors getCurrentEAnchor() {return currentEAnchor;}
		
		public Shape getShape() { return shape;	}
		public Anchors getAnchors() {return anchors;}
		public void setAnchors(Anchors anchors) {this.anchors = anchors;}
		
		public int getPx() {return px; }
		public void setPx(int x) { this.px = x;}
		public int getPy() { return py; }
		public void setPy(int y) { this.py = y; }
		
		// constructors
		public GShape(EDrawingType eDrawingType, Shape shape){
			this.eDrawingType = eDrawingType;
			this.selected = false;
			this.shape = shape;
			this.anchors = new Anchors();
			this.currentEAnchor = null;
			this.px = 0;
			this.py = 0;
		}
		public GShape clone() {
			try { return this.getClass().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}
		// methods
		public void draw(Graphics2D g2D) {
			g2D.draw(this.shape);
			if (this.selected) {
				this.anchors.draw(g2D, this.shape.getBounds());
			}
		}
		public GConstants.EAnchors contains(int x, int y) {
			this.currentEAnchor = null;
			if (this.selected) {
				this.currentEAnchor = this.anchors.contains(x, y);
				if (this.currentEAnchor != null)
					return this.currentEAnchor;
			}
			if (this.shape.contains(x, y)) {
				this.currentEAnchor = EAnchors.MM;
			}
			return this.currentEAnchor;
		}
		
		abstract public void resize(int x, int y);
		abstract public void addPoint(int x, int y) ;
		abstract public void setOrigin(int x, int y);
		abstract public void move(int x, int y);
		abstract public void setPoint(int x, int y);
		abstract public void continueTransforming(int x, int y, Graphics2D g2d);
		
}
