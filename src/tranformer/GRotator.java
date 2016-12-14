package tranformer;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import shapes.GGroup;
import shapes.GShape;

public class GRotator extends GTransformer {
	private Vector<GShape> shapeList;
	private Point2D.Double rOrigin;
	private double theta;
	
	public GRotator(GShape shape) {
		super(shape);
		if(shape instanceof GGroup){
			shapeList = new Vector<GShape>();
			for(GShape groupShape : ((GGroup)shape).getGroupList()){
				shapeList.add(groupShape);
			}
		}
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		rOrigin = new Point2D.Double(shape.getBounds().getCenterX(), shape.getBounds().getCenterY());
		//shape의 중심 좌표를 rOrign에 삽입.
		theta = Math.atan2(rOrigin.y - y, rOrigin.x - x);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		double theta2 = theta - Math.atan2(rOrigin.y- y, x - rOrigin.x);
		// 아크 탄젠트를 통해 라디안 구함.
		if(shape instanceof GGroup){ // 그룹화했을때
			GShape temp;
			for(int i = 0; i< shapeList.size(); i++){
				temp = shapeList.get(i);
				temp.draw(g2d);
				temp.rotate(theta2, rOrigin.x, rOrigin.y);
				temp.draw(g2d);
			}
		}else{ // 그룹화 안했을 때
			shape.draw(g2d);
			System.out.println(rOrigin.x);
			shape.rotate(theta2, rOrigin.x, rOrigin.y);
			shape.draw(g2d);
		}
		theta = Math.atan2(rOrigin.y -y, x - rOrigin.x);
	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
	}
	public void angleRotate(Graphics2D g2d){ // 각도 이용 rotate
		g2d.setXORMode(g2d.getBackground());
//		g2d.setStroke(dashedLineStroke);
		double theta1 = 360-theta;
		double theta2 = Math.toRadians(theta1);
		
		// 각도 계산해서 라디안 구함.
		if(shape instanceof GGroup){ // 그룹화했을때
			GShape temp;
			for(int i = 0; i< shapeList.size(); i++){
				temp = shapeList.get(i);
				temp.draw(g2d);
				temp.rotate(theta2, rOrigin.x, rOrigin.y);
				temp.draw(g2d);
			}
		}else{ // 그룹화 안했을 때
			shape.draw(g2d);
			shape.rotate(theta2, rOrigin.x, rOrigin.y);
			shape.draw(g2d);
		}
	}
	
	public void setTheata(double theta){ // 각도 입력
		this.theta = theta;
		rOrigin = new Point2D.Double(shape.getBounds().getCenterX(), shape.getBounds().getCenterY());
		
	}
}
