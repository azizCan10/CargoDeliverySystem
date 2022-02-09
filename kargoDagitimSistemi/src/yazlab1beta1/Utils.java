package yazlab1beta1;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Utils {

	private static Cursor imlec;
	
	public static void setHandCursor(JButton button) {		
		imlec = new Cursor(Cursor.HAND_CURSOR);
		button.setCursor(imlec);
	}
	
	public static void setHandCursor(JLabel label) {		
		imlec = new Cursor(Cursor.HAND_CURSOR);
		label.setCursor(imlec);
	}
	
}
