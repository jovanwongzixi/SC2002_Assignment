import boundary.HomeMenu;
/*
 * driver to start MOBLIMA
 */
public class MOBLIMA {
	public static void main(String[] args) {
		HomeMenu homeMenu = new HomeMenu();		//open new instance of HomeMenu
		homeMenu.start();
	}
}

/*
  			Admin functions 
  				O - Full admin functionality. 
  				X - Partial exception handling implemented.
  				X - Partial bogus input checking confirmed.
  				
  			Customer functions
  				O - Show movie list + View movie detail full functionality
  				O - Top 5 movie list full functionality
  				O - Enter movie review full functionality
  				X - displayMovieDetails can belong to a separate class
  			
*/
