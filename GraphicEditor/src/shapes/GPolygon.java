package shapes;

import java.awt.Polygon;

import constants.GConstants.EDrawingType;

public class GPolygon extends GShape {
	private Polygon polygon;
	public GPolygon() {
		super(EDrawingType.NP, new Polygon());
		this.polygon = (Polygon)this.getShape();
	}
	@Override
	public void resize(int x, int y) {
		// TODO Auto-generated method stub
		switch (this.getCurrentEAnchor()) {
		case NN:
			for(int i = 1; i < polygon.npoints; i++){
				polygon.ypoints[i] += y - getPy();
			}
			break;
		case NE:
			for(int i = 1; i < polygon.npoints; i++){
				polygon.xpoints[i] += x - getPx();
				polygon.ypoints[i] += y - getPy();
			}
			break;
		case NW:
			for(int i = 1; i < polygon.npoints; i++){
				polygon.xpoints[i] += x - getPx();
				polygon.ypoints[i] += y - getPy();
			}
			break;
		case SS:
			for(int i = 1; i < polygon.npoints; i++){
				polygon.ypoints[i] += y - getPy();
			}
			//rectangle.height = y-rectangle.y;
			break;
		case SE:
			for(int i = 1; i < polygon.npoints; i++){
				polygon.xpoints[i] += x - getPx();
				polygon.ypoints[i] += y - getPy();
			}
			break;
		case SW:
			for(int i = 1; i < polygon.npoints; i++){
				polygon.xpoints[i] += x - getPx();
				polygon.ypoints[i] += y - getPy();
			}
			break;
		case EE:
			for(int i = 1; i < polygon.npoints; i++){
				polygon.xpoints[i] += x - getPx();
			}
			break;
		case WW:
			for(int i = 1; i < polygon.npoints; i++){
				polygon.ypoints[i] += x - getPx();
			}
			break;
		default:
			break;
		}
		this.polygon.invalidate();
	}
	
/*	@Override
	public void resize(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
		this.polygon.invalidate();	//ÁÂÇ¥Àû¿ë.
	}
*/	@Override
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}
	@Override
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	@Override
	public void move(int x, int y) {
		for(int i = 0; i < polygon.npoints; i++){
			this.polygon.xpoints[i] += x - px;
			this.polygon.ypoints[i] += y - py;
		}
		this.polygon.invalidate();	//ÁÂÇ¥Àû¿ë.
		px = x;
		py = y;
	}
	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		px = x;		py = y;
	}
}
