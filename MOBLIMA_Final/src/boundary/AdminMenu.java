package boundary;
/*
 * No issues.
 */
import java.util.Scanner;
import interfaces.Menu;
import static control.admin.Authenticator.authenticate;

public class AdminMenu implements Menu{
	
	static boolean loggedIn = false;
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("\n------------------- Staff Menu -------------------");
			if(!loggedIn) {
				System.out.println("(1) ----------------      Login");
			} else {
				System.out.println("(1) ----------------      Logout");
			}
			System.out.println("(2) ----------------      Edit movie listing");
			System.out.println("(3) ----------------      Edit cinema showtimes");
			System.out.println("(4) ----------------      Configure system settings");
			if(!loggedIn) {
				System.out.println("(5) ----------------      Return to main menu");
			}
			System.out.printf("\nOption: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("\nOption: ");
				sc.next();
			}
			
			choice = sc.nextInt();
			
			if(!loggedIn && choice > 1 && choice < 5) {
				System.out.println("Unauthorised access detected! Exiting admin menu...");
				return;
			} else {
				switch (choice) {
					case 1:
						if(!loggedIn) {
							System.out.printf("Enter user ID: ");
							String userid = sc.next();
							System.out.printf("Enter password: ");
							String password = sc.next();			
							loggedIn = authenticate(userid, password);
							
							if(loggedIn) {
								System.out.println("Successfully logged in! Welcome, " + userid + "!");
								break;
							}
							
							System.out.println("Invalid username/password! Please try again!");
							break;
						} else {
							System.out.println("Successfully logged out! Returning to main menu...");
							loggedIn = false;
							return;
						}
					case 2:
						MovieListEditorMenu movieListEditorMenu = new MovieListEditorMenu();
						movieListEditorMenu.start();
						break;
					case 3:
						MovieTimeslotEditorMenu movieTimeslotEditorMenu = new MovieTimeslotEditorMenu();
						movieTimeslotEditorMenu.start();
						break;
					case 4:
						SystemMenu systemMenu = new SystemMenu();
						systemMenu.start();
						break;
					case 5:
						if(loggedIn) {
							System.out.println("Option does not exist! Please input a valid choice!");
							break;
						}
						System.out.println("Returning to main menu...");
						return;
					default:
						System.out.println("Option does not exist! Please input a valid choice!");
				}					
			}									
		} while (true);
	}
}
