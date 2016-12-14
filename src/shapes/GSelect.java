package shapes;

import java.awt.Rectangle;
import java.awt.Shape;

import constants.GConstants.EDrawingType;

public class GSelect extends GShape {
	private Rectangle rectangle;
	public GSelect() {
		super(EDrawingType.TP, new Rectangle());
		this.rectangle = (Rectangle)this.getShape();
	}

	@Override
	public void setOrigin(int x, int y) {
		rectangle.setFrameFromDiagonal(x, y, x, y);
		
//		this.rectangle.setLocation(x, y);
	}

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void setCoordinate(int x, int y){
		Rectangle tempRectangle = (Rectangle)shape;
		tempRectangle.setFrameFromDiagonal(px, py, x, y);
	}
	@Override
	public void addPoint(int x, int y) {

	}

	@Override
	public void resize(int x, int y) {
		this.rectangle.width = x - this.rectangle.x;
		this.rectangle.height = y - this.rectangle.y;
	}

	public void move(int x, int y) {
	}

	@Override
	public GShape deepCopy() {
		return null;
	}
	public GShape clone(){
		return new GSelect();
	}
}
