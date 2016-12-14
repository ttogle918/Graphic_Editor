package shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import constants.GConstants.EDrawingType;

public class GRectangle extends GShape {
	private Rectangle rectangle;
	public GRectangle() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
	}
	public void setOrigin(int x, int y) {
		this.rectangle.setLocation(x, y);
	}
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void move(int x, int y) {
		this.rectangle.x += x - px;
		this.rectangle.y += y - py;
		this.setPoint(x, y);
	}
	public void addPoint(int x, int y) {
	}
	
	public void resize(int x, int y) {
		if (this.getCurrentEAnchor() == null) {
			this.rectangle.width = x - this.rectangle.x;
			this.rectangle.height = y - this.rectangle.y;
			return;
		}
		switch (this.getCurrentEAnchor()) {
		case NN:
			rectangle.height -= y- this.py;
			rectangle.y = y;
			break;
		case NE:
			rectangle.setBounds(rectangle.x, y, rectangle.width+x-this.px, -y+this.py+rectangle.height);	
			
			break;
		case NW:
			rectangle.setBounds(x, y, rectangle.width - x + this.px, -y + this.py + rectangle.height);
			
			break;
		case SS:
			rectangle.height += y-this.py;
			
			break;
		case SE:
			this.rectangle.width = x - this.rectangle.x;
			this.rectangle.height = y - this.rectangle.y;				
			break;
		case SW:
			rectangle.setBounds(x, rectangle.y, rectangle.width-x+px,
					rectangle.height+y-this.py);
			break;
		case EE:
			rectangle.width = x - rectangle.x;
			break;
		case WW:
			rectangle.width -= x-rectangle.x;
			rectangle.x = x;
			break;
		default:
			break;
		}
		this.setPoint(x, y);
	}
	@Override
	public GShape deepCopy() {
		AffineTransform affineTransform = new AffineTransform();
		Shape newShape = affineTransform.createTransformedShape(shape);
		GRectangle shape = new GRectangle();
		shape.setShape(newShape);
		shape.setGraphicsAttributes(this);
		return shape;
	}
	
}
