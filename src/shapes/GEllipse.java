package shapes;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import constants.GConstants.EDrawingType;

public class GEllipse extends GShape {
	private final Ellipse2D ellipse;
	public GEllipse() {
		super(EDrawingType.TP, new Ellipse2D.Double(0,0,0,0));
		//this.ellipse = new Ellipse2D.Double();
		this.ellipse = (Ellipse2D)this.getShape();
	}
	public Ellipse2D getShape(){	return ellipse;		}
	
	@Override
	public void setOrigin(int x, int y) {
		this.ellipse.setFrame(x, y, x, y);
	}
	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	@Override
	public void addPoint(int x, int y) {
	}

	@Override
	public void resize(int x, int y) {
		if (this.getCurrentEAnchor() == null) {
			this.ellipse.setFrame(ellipse.getX(), ellipse.getY(), x-ellipse.getX(), y-ellipse.getY());
			return;
		}
		switch (this.getCurrentEAnchor()) {
		case NN://
			ellipse.setFrame(ellipse.getX(), y, ellipse.getWidth(),
					-ellipse.getHeight()+y-this.py);
			break;
		case NE:
			ellipse.setFrame(ellipse.getX(), y, ellipse.getWidth()+x-this.px,
					ellipse.getHeight()-y+this.py);
			break;
		case NW:
			ellipse.setFrame(x, y, ellipse.getWidth()-x+this.px, -y+this.py+ellipse.getHeight());
			break;
		case SS:
			ellipse.setFrame(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight()+y-this.py);
			break;
		case SE:
			ellipse.setFrame(ellipse.getX(), ellipse.getY(),ellipse.getWidth()+x
				-this.px, ellipse.getHeight()+y-this.py);
			break;
		case SW:
			ellipse.setFrame(x, ellipse.getY(),ellipse.getWidth()- x
					+this.px, ellipse.getHeight()+y-this.py);
			break;
		case EE:
			ellipse.setFrame(ellipse.getX(), ellipse.getY(), x-ellipse.getX(), ellipse.getHeight());
			break;
		case WW:
			ellipse.setFrame(x, ellipse.getY(), -ellipse.getWidth()+x-ellipse.getX(),
					ellipse.getHeight());
			return;
		default:
			break;
		}

	}

	@Override
	public void move(int x, int y) {
		this.ellipse.setFrame(x+ellipse.getX()-px, y+ellipse.getY()-py, ellipse.getWidth(), ellipse.getHeight());
		this.setPoint(x, y);
	}

	@Override
	public GShape deepCopy() {
		AffineTransform affineTransform = new AffineTransform();
		Shape newShape = affineTransform.createTransformedShape(shape);
		GEllipse shape = new GEllipse();
		shape.setShape(newShape);
		shape.setGraphicsAttributes(this);
		return shape;
	}

}
