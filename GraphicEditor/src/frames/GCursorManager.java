package frames;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import constants.GConstants.EAnchors;
import shapes.GShape;

public class GCursorManager {
	
	public GCursorManager () {
	}
	public Cursor getCursor(EAnchors eAnchor) {
		switch (eAnchor) { 
		case NN: return new Cursor(Cursor.N_RESIZE_CURSOR);
		case NE: return new Cursor(Cursor.NE_RESIZE_CURSOR);
		case NW: return new Cursor(Cursor.NW_RESIZE_CURSOR);
		case SS: return new Cursor(Cursor.S_RESIZE_CURSOR);
		case SE: return new Cursor(Cursor.SE_RESIZE_CURSOR);
		case SW: return new Cursor(Cursor.SW_RESIZE_CURSOR);
		case EE: return new Cursor(Cursor.E_RESIZE_CURSOR);
		case WW: return new Cursor(Cursor.W_RESIZE_CURSOR);
		case RR: return new Cursor(Cursor.HAND_CURSOR);
		case MM: return new Cursor(Cursor.MOVE_CURSOR);
		//default: new Cursor(Cursor.DEFAULT_CURSOR);
		}
		return new Cursor(Cursor.DEFAULT_CURSOR);
	}
}