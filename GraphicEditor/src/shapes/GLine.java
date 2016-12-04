package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import constants.GConstants.EDrawingType;

public class GLine extends GShape {
	private Line2D line;
	public GLine() {
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D.Double)this.getShape();
	}
	@Override
	public void resize(int x, int y) {
		
		switch (this.getCurrentEAnchor()) {
		case NN:
			line.setLine(line.getX1(), y, line.getX2(), line.getY2());
			break;
		case NE:
			line.setLine(line.getX1(), line.getY1(), x, y);
			break;
		case NW:
			line.setLine(x, y, line.getX2(), line.getY2());
			break;
		case SS:
			line.setLine(line.getX1(), line.getY1(), line.getX2(), y);
			break;
		case SE:
			this.line.setLine(line.getX1(), line.getY1(), x, y);
			break;
		case SW:
			this.line.setLine(x, y, line.getX2(), line.getY2());
			break;
		case EE:
			this.line.setLine(line.getX1(), line.getY1(), x, line.getY2());
			break;
		case WW:
			this.line.setLine(x, line.getY1(), line.getX2(), line.getY2());
			break;
		default:
			break;
		}

	}
	
	@Override
	public void addPoint(int x, int y) {	
	}
	@Override
	public void setOrigin(int x, int y) {
		this.line.setLine(x, y, x, y);
	}
	@Override
	public void move(int x, int y) {
		line.setLine(x+line.getX1()-px, y+line.getY1()-py, x+line.getX2()-px, y-line.getY2()-py);
		this.setPoint(x, y);
	}
	@Override
	public void setPoint(int x, int y) {
		px = x;		py = y;
	}
}
