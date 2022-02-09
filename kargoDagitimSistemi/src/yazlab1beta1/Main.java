package yazlab1beta1;

import java.sql.SQLException;

import yazlab1beta1.ui.FrmLogin;
import yazlab1beta1.ui.FrmMenu;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		
		new FrmLogin().setVisible(true);
		new FrmMenu().setVisible(true);
	}
	
}