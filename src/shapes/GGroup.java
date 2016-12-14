package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import constants.GConstants;
import constants.GConstants.EDrawingType;

public class GGroup extends GShape {
	private Vector<GShape> shapeList;
	private BasicStroke dashedLineStroke;
	public GGroup() {
		super(EDrawingType.NP, new Rectangle());
		shapeList = new Vector<GShape>();
		float dashes[] = {GConstants.DEFAULT_DASH_OFFSET};
		dashedLineStroke = new BasicStroke(
		GConstants.DEFAULT_DASHEDLINE_WIDTH,
		BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND, 10, dashes, 0);
	}

	@Override
	public void setOrigin(int x, int y) {
		for(GShape shape : shapeList){
			shape.setOrigin(x, y);
		}
	}
	@Override
	public void setPoint(int x, int y) {
		
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public GShape deepCopy() {
		GGroup returnShape = new GGroup();
		for(GShape shape : shapeList){
			returnShape.addShape(shape.deepCopy());
		}
		return returnShape;
	}
	public void addShape(GShape shape){
		shapeList.add(0,shape);
		if(shapeList.size() == 1){
			this.shape = shape.getBounds();
		}else {
			this.shape = shape.getBounds().createUnion(shape.getBounds());
		}
	}
	public Vector<GShape> getGroupList(){
		return shapeList;
	}
	public void setLineColor(Color lineColor){
		for(GShape shape : shapeList){
		shape.setLineColor(lineColor);
		}
	}
	public void setFillColor(Color fillColor){
		for(GShape shape : shapeList){
			shape.setFillColor(fillColor);
		}
	}
	public boolean isSelected(){
		return selected;
	}
	public void setSelected(boolean selected){
		this.selected = selected;
		if (selected){
			anchors = new Anchors();
	//		anchors.draw(g2D, shape.getBounds());
		}else{
			anchors = null;
		}
	}
	public boolean onShape(int x, int y){
		for(GShape shape : shapeList){
			if(shape.contnains(x, y)!= null){
				return true;
			}
		}
		return false;
	}
	public void draw(Graphics2D g2D){
		for(GShape shape : shapeList){
			shape.draw(g2D);
		}
		if(this.isSelected()){
			g2D.setColor(GConstants.COLOR_LINE_DEFAULT);
			g2D.setStroke(dashedLineStroke);
			g2D.draw(shape);
			g2D.setStroke(new BasicStroke());
			this.getAnchors().draw(g2D, shape.getBounds());
		}
	}
	
	public void move(int x, int y){
		for(GShape shape : shapeList){
			shape.move(x, y);
		}
	}

	@Override
	public void resize(int x, int y) {
		for(GShape shape : shapeList){
			shape.resize(x, y);
		}
	}
	public void rotate(int x, int y){
		for(GShape shape : shapeList){
			shape.rotate(shape.px-x, x, y);
		}
	}
}
