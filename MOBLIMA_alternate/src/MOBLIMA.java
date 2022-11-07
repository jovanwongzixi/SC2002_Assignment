import boundary.HomeMenu;
import interfaces.Menu;

/*
 * driver to start MOBLIMA
 */
public class MOBLIMA {
	public static void main(String[] args) {
		Menu homeMenu = new HomeMenu();		//open new instance of HomeMenu
		homeMenu.start();
	}
}
