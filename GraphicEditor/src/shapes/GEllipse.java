package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import constants.GConstants.EDrawingType;

public class GEllipse extends GShape {
	private Ellipse2D ellipse;
	public GEllipse() {
		super(EDrawingType.TP, new Ellipse2D.Double(0, 0, 0, 0));
		ellipse = new Ellipse2D.Double();
	}
	public Ellipse2D getShape(){	return ellipse;		}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		this.ellipse.setFrame(x, y, x, y);
		
	}
	@Override
	public void move(int x, int y) {
		this.ellipse.setFrame(x+ellipse.getX()-px, y+ellipse.getY()-py, ellipse.getWidth(), ellipse.getHeight());
		this.setPoint(x, y);
	}
	@Override
	public void setPoint(int x, int y) {
		this.px = x;	this.py = y;
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
					-ellipse.getHeight()+y-getPy());
			break;
		case NE:
			ellipse.setFrame(ellipse.getX(), y, ellipse.getWidth()+x-getPx(),
					ellipse.getHeight()-y+getPy());
			break;
		case NW:
			ellipse.setFrame(x, y, ellipse.getWidth()-x+getPx(), -y+getPy()+ellipse.getHeight());
			break;
		case SS:
			ellipse.setFrame(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight()+y-getPy());
			break;
		case SE:
			ellipse.setFrame(ellipse.getX(), ellipse.getY(),ellipse.getWidth()+x
				-getPx(), ellipse.getHeight()+y-getPy());
			break;
		case SW:
			ellipse.setFrame(x, ellipse.getY(),ellipse.getWidth()- x
					+getPx(), ellipse.getHeight()+y-getPy());
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
/*	public void draw(Graphics2D g2D) {
		if( this.ellipse.getWidth() < 0 && this.ellipse.getHeight() < 0){
			g2D.drawOval((int)(ellipse.getX() + ellipse.getWidth()), (int)(ellipse.getY()+ellipse.getHeight()), 
					-(int)ellipse.getWidth(), -(int)ellipse.getHeight());	
		}else if(this.ellipse.getWidth() < 0){
			g2D.drawOval((int)(ellipse.getX() + ellipse.getWidth()), (int)ellipse.getY(), 
					-(int)ellipse.getWidth(), (int)ellipse.getHeight());	
		}else if(this.ellipse.getHeight() < 0){		
			g2D.drawOval((int)ellipse.getX(), (int)(ellipse.getY()+ellipse.getHeight()), 
					(int)ellipse.getWidth(), -(int)ellipse.getHeight());	
		}
		g2D.draw(ellipse);
		super.draw(g2D);
	}
	public void compareSize(Graphics2D g2D){
		if( this.ellipse.getWidth() < 0 && this.ellipse.getHeight() < 0){
			this.ellipse.setFrame(ellipse.getX() + ellipse.getWidth(), ellipse.getY()+ellipse.getHeight(), 
					-ellipse.getWidth(), -ellipse.getHeight());	
		}else if(this.ellipse.getWidth() < 0){
			this.ellipse.setFrame(ellipse.getX() + ellipse.getWidth(), ellipse.getY(), 
					-ellipse.getWidth(), ellipse.getHeight());	
		}else if(this.ellipse.getHeight() < 0){		
			this.ellipse.setFrame(ellipse.getX(), ellipse.getY()+ellipse.getHeight(), 
					ellipse.getWidth(), -ellipse.getHeight());	
		}
	}
*/	public void AnchorDraw(Graphics2D g2D, Rectangle rectangle){
		Anchors anchors = new Anchors();
		anchors.draw(g2D, rectangle);
	}
	
}