package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import constants.GConstants.EDrawingType;

public class GRectangle extends GShape {
	private Rectangle rectangle;
	public GRectangle() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
	}
	@Override
	public void setOrigin(int x, int y) {
		this.rectangle.setLocation(x, y);
	}
	
	@Override
	public void addPoint(int x, int y) {

	}
	
	public void move(int x, int y){
		this.rectangle.x += x - px;
		this.rectangle.y += y - py;
		this.setPoint(x, y);
	}
	public void setPoint(int x, int y) {
		this.px = x;	this.py = y;
	}
	@Override
	public void resize(int x, int y) {
		if(this.getCurrentEAnchor() == null){
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
			//rectangle.height = y-rectangle.y;
			break;
		case SE:
			rectangle.width += x - getPx();
			rectangle.height += y - getPy();				
			break;
		case SW:
			rectangle.setBounds(x, rectangle.y, rectangle.width-x+getPx(),
							rectangle.height+y-getPy());
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
	}
	
/*	public void draw(Graphics2D g2D) {
		if( this.rectangle.width < 0 && this.rectangle.height < 0){
			g2D.drawRect(this.rectangle.width+this.rectangle.x, this.rectangle.y
					+this.rectangle.height, -this.rectangle.width, -this.rectangle.height);
		}else if(this.rectangle.width < 0){
			g2D.drawRect(this.rectangle.width+this.rectangle.x, this.rectangle.y,
					-this.rectangle.width, this.rectangle.height);			
		}else if(this.rectangle.height < 0){		
			g2D.drawRect(this.rectangle.x, this.rectangle.y+this.rectangle.height, 
					this.rectangle.width, -this.rectangle.height);
		}
		g2D.draw(rectangle);
		super.draw(g2D);
	}
	public void compareSize(Graphics2D g2D){
		if( this.rectangle.width < 0 && this.rectangle.height < 0){
			this.rectangle.setBounds(rectangle.width+rectangle.x, rectangle.y
					+rectangle.height, -rectangle.width, -rectangle.height);
		}else if(this.rectangle.width < 0){
			this.rectangle.setBounds(rectangle.width+rectangle.x, rectangle.y,
					-rectangle.width, rectangle.height);			
		}else if(this.rectangle.height < 0){		
			this.rectangle.setBounds(rectangle.x, rectangle.y+rectangle.height, 
					rectangle.width, -rectangle.height);
		}
	}
*/	public void AnchorDraw(Graphics2D g2D, Rectangle rectangle){
		Anchors anchors = new Anchors();
		anchors.draw(g2D, rectangle);
	}

}