package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;
abstract public class GShape implements Serializable {
	private static final long serialVersionUID = 1L;
	private EDrawingType eDrawingType;
	protected AffineTransform affineTransform;
	
	protected boolean selected;
	private EAnchors currentEAnchor;
	protected Shape shape;	
	protected Anchors anchors;
	protected Color lineColor, fillColor;
	// working variables
	protected int px, py;
	// getters & setters
	public EDrawingType geteDrawingType() {	return eDrawingType;}
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	public EAnchors getCurrentEAnchor() {return currentEAnchor;}
	public void setShape(Shape shape) { this.shape = shape;	}
	public Shape getShape() { return shape;	}
	public Anchors getAnchors() {return anchors;}
	public void setAnchors(Anchors anchors) {this.anchors = anchors;}
	
	// constructors
	public GShape(EDrawingType eDrawingType, Shape shape){
		this.eDrawingType = eDrawingType;
		this.selected = false;
		this.currentEAnchor = null;
		this.shape = shape;
		this.anchors = new Anchors();
		affineTransform = new AffineTransform(); //도형을 변경하는 메소드를 가지는 아이.
		this.px = this.py = 0;
	}
	public void setGraphicsAttributes(GShape shape) {
		setLineColor(shape.getLineColor());
		setFillColor(shape.getFillColor());
		setAnchors(shape.getAnchors());
		setSelected(shape.isSelected());
	}
	public GShape clone() {
		try { return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void draw(Graphics2D g2d) {
		g2d.draw(this.shape);
		if (this.fillColor!=null){
			g2d.setColor(this.fillColor);
			g2d.fill(shape);
		}
		if (this.lineColor!=null){
			g2d.setColor(this.lineColor);
			g2d.draw(shape);
		}
		if (this.selected) {
			this.anchors.draw(g2d, this.shape.getBounds());
		}
	}
	public GConstants.EAnchors contnains(int x, int y) {
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
	public void rotate(double theta, double x, double y) {
		affineTransform.setToRotation(theta, x, y);
		shape = affineTransform.createTransformedShape(shape);
	}
	 // 트랜스포머쪽에서 호출함.
	public void moveCoordinate(int x, int y){
		affineTransform.setToTranslation(x, y); // 도형의 평행이동
		shape = affineTransform.createTransformedShape(shape);// 셰이프를 주면 위에서 변경한 거대로 셰이프를 변경해서 리턴해 줌
	}
	public void move(int x, int y) {
		affineTransform.setToTranslation(x, y);
		shape = affineTransform.createTransformedShape(shape);
	}
	public void resizeCoordinate(double d, double e){
		affineTransform.setToScale(d, e);
		shape = affineTransform.createTransformedShape(shape);
	}
	public void moveReverse(int rx, int ry){
		affineTransform.setToTranslation(-rx, -ry);
		shape = affineTransform.createTransformedShape(shape);
	}
	public Rectangle getBounds(){	return shape.getBounds();	}
	public void setLineColor(Color lineColor){	this.lineColor = lineColor;	}
	public Color getLineColor() {	return lineColor;	}
	public void setFillColor(Color fillColor){	this.fillColor = fillColor;	}
	public Color getFillColor() {	return fillColor;	}
	abstract public void setOrigin(int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y); 
	abstract public void resize(int x, int y);
	abstract public GShape deepCopy();
}
