package tranformer;

import java.awt.Graphics2D;
import java.util.Vector;

import constants.GConstants;
import shapes.GShape;

public class GGrouper extends GTransformer {

	public GGrouper(GShape shape) {
		super(shape);
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		shape.setOrigin(x, y);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		shape.draw(g2d);
//		shape..setCoordinate(x, y);
		shape.draw(g2d);
	}

//	@Override
	public void finishTransforming(Vector<GShape> shapeList, int x, int y, Graphics2D g2d) {
		for (GShape tempShape : shapeList) {
			if (shape.getBounds().contains(tempShape.getBounds())) {
				tempShape.setSelected(true);
			}
		}
	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
	}
}
