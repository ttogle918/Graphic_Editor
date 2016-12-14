package frames;

import java.util.ArrayList;
import java.util.Vector;

import shapes.GShape;

public class GClipboard {
	private Vector<GShape> clipBoard;
	
	public GClipboard() {
		clipBoard = new Vector<GShape>();
	}

	public ArrayList<GShape> paste(){
		return (ArrayList<GShape>) clipBoard.clone();
	}
	
	public void copy(Vector<GShape> shapes){
		clipBoard.clear();
		GShape shape;
		for(int i = shapes.size(); i>0; i--){
			shape = shapes.get(i-1);
			if(shape.isSelected()){
				clipBoard.add(shape.deepCopy());
			}
		}
	}
	
	public void cut(Vector<GShape> shapes){
		clipBoard.clear();
		GShape shape;
		
		for(int i = shapes.size(); i>0; i--){
			shape = shapes.get(i-1);
			if(shape.isSelected()){
				clipBoard.add(shape.deepCopy());
				shapes.remove(shape);
			}
		}
	}
}
