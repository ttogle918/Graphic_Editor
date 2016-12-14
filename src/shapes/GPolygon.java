package shapes;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import constants.GConstants.EDrawingType;

public class GPolygon extends GShape {
	private Polygon polygon;
	public GPolygon() {
		super(EDrawingType.NP, new Polygon());
		this.polygon = (Polygon)this.getShape();
	}
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	public void setPoint(int x, int y) {
		px = x;
		py = y;
	}
	// continueDraw
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}
	public void setCoordinate(int x, int y){
		Polygon tempPolygon = (Polygon)shape;
		tempPolygon.xpoints[((Polygon)shape).npoints-1] = x;
		tempPolygon.ypoints[((Polygon)shape).npoints-1] = y;
		if (anchors!=null){
	//		anchors.setPosition(shape.getBounds());
		}
	}
	public void resize(int x, int y) {
		if (this.getCurrentEAnchor() == null) {
			this.polygon.xpoints[this.polygon.npoints-1] = x;
			this.polygon.ypoints[this.polygon.npoints-1] = y;
			return;
		}
		double deltaW = 0;
		double deltaH = 0;
		
		switch (this.getCurrentEAnchor()) {
		case NN:
			deltaW = 0;
			deltaH = (y - py)/this.polygon.npoints;
			break;
		case NE:
			deltaW = (x - px)/this.polygon.npoints;
			deltaH = (y - py)/this.polygon.npoints;
			break;
		case NW:
			deltaW = (x - px)/this.polygon.npoints;
			deltaH = (y - py)/this.polygon.npoints;
			break;
		case SS:
			deltaW = 0;
			deltaH = (y - py)/this.polygon.npoints;
			break;
		case SE:
			deltaW = (x - px)/this.polygon.npoints;
			deltaH = (y - py)/this.polygon.npoints;
			break;
		case SW:
			deltaW = (x - px)/this.polygon.npoints;
			deltaH = (y - py)/this.polygon.npoints;
			break;
		case EE:
			deltaW = (x - px)/this.polygon.npoints;
			deltaH = 0;
			break;
		case WW:
			deltaW = (x - px)/this.polygon.npoints;
			deltaH = 0;
			break;
		default:
			break;
		}
		double w = shape.getBounds().getWidth();
		double h = shape.getBounds().getHeight();
		double xFactor = 1.0;
		double yFactor = 1.0;
		if(w > 0.0)	xFactor = (1.0 + deltaW / w);
		if(h > 0.0)	yFactor = (1.0 + deltaH / h);
		super.resizeCoordinate(xFactor, yFactor);
		px = x; py = y;
		this.polygon.invalidate();
	}
	public void move(int x, int y) {
		for (int i=0; i<this.polygon.npoints; i++) {
			this.polygon.xpoints[i] = x - px;
			this.polygon.ypoints[i] = y - py;
		}
		this.polygon.invalidate();
		px = x;
		py = y;
	}
	@Override
	public GShape deepCopy() {
		AffineTransform affineTransform = new AffineTransform();
		Shape newShape = affineTransform.createTransformedShape(shape);
		GPolygon shape = new GPolygon();
		shape.setShape(newShape);
		shape.setGraphicsAttributes(this);
		return shape;
	}
	
}
