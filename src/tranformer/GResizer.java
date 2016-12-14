package tranformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import constants.GConstants.EAnchors;
import shapes.GShape;

public class GResizer extends GTransformer {
	
	public GResizer(GShape shape) {
		super(shape);
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.shape.setPoint(x, y);
		this.shape.draw(g2d);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		//Point2D resizeFactor = computeResizeFactor(this.px, this.py, x, y);
		//AffineTransform tempAffine = g2d.getTransform();
		//g2d.translate(x, y);
		
		this.shape.draw(g2d);
		this.shape.resize(x, y);
		this.shape.draw(g2d);
		
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		shape.move(x, y);
	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
	}
	
}
