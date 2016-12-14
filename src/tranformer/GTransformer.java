package tranformer;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import constants.GConstants;
import shapes.GShape;

abstract public class GTransformer {
	protected GShape shape;
	protected BasicStroke dashedLineStroke;
	protected int px, py;
	protected GShape getShape() { return this.shape; }
	
	public GTransformer(GShape shape) {
		this.shape = shape;
		float dashes[] = {GConstants.DEFAULT_DASH_OFFSET};
		dashedLineStroke = new BasicStroke(
				GConstants.DEFAULT_DASHEDLINE_WIDTH,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0);
	}
	abstract public void initTransforming(int x, int y, Graphics2D g2D);
	abstract public void keepTransforming(int x, int y, Graphics2D g2D);
	abstract public void finishTransforming(int x, int y, Graphics2D g2D);
	abstract public void continueTransforming(int x, int y, Graphics2D g2D);
}

