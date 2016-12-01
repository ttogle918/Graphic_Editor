package constants;

import shapes.GShape;

import java.awt.Cursor;

import shapes.GEllipse;
import shapes.GLine;
import shapes.GPolygon;
import shapes.GRectangle;

public class GConstants {
	// JFrame attributes
	public final static String MAINFRAME_TITLE = "GraphicsEditor";
	public final static String FILEMENU_TITLE = "File";
	public final static String EDITMENU_TITLE = "Edit";
	public enum EAnchors {
		NN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		NE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		NW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		SS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		SE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
		SW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
		EE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		WW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		RR(new Cursor(Cursor.HAND_CURSOR)),
		MM(new Cursor(Cursor.MOVE_CURSOR));
		private Cursor cursor;
		private EAnchors(Cursor cursor){
			this.cursor = cursor;
		}
		public Cursor getCursor(){	return cursor;	}
	};

	public static enum EMainFrame {
		X(100), Y(100), W(400), H(600);
		private int value;
		private EMainFrame(int value) {
			this.value = value;
		}
		public int getValue() { return this.value; }
	}
	public static enum EFileMenuItem {
		nnew("new"), 
		open("open"), 
		close("close"), 
		save("save"), 
		saveAs("saveAs"),
		print("print"),
		exit("exit");
		private String text;
		private EFileMenuItem(String text) {
			this.text = text;
		}
		public String getText() { return this.text; }
	}
	public static enum EEditMenuItem {
		cut("cut"), 
		copy("copy"), 
		paste("paste"), 
		delete("delete"), 
		ddo("do"),
		undo("undo"),
		group("group"),
		unGorup("unGroup");
		private String text;
		private EEditMenuItem(String text) {
			this.text = text;
		}
		public String getText() { return this.text; }
	}
	public static enum EDrawingType {
		TP, NP;
	}
	public static enum EToolBarButton {
		rectangle("rsc/rectangle.gif", "rsc/rectangleSLT.gif", new GRectangle()),
		ellipse("rsc/ellipse.gif", "rsc/ellipseSLT.gif", new GEllipse()),
		line("rsc/line.gif", "rsc/lineSLT.gif", new GLine()),
		polygon("rsc/polygon.gif", "rsc/polygonSLT.gif", new GPolygon());
		
		private String iconName;
		private String selectedIconName;
		private GShape shape;
		
		private EToolBarButton(
				String iconName, String selectedIconName, 
				GShape shape) {
			this.iconName = iconName;
			this.selectedIconName = selectedIconName;
			this.shape = shape;
		}
		public String getIconName() { return this.iconName; }
		public String getSelectedIconName() { return this.selectedIconName; }
		public GShape getShape() { return this.shape; }
	}
}
