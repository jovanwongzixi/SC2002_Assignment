import java.util.Scanner;
import java.util.Arrays;

public class CinemaLayout2 {
    private int ROW = 8;
    private int COL = 8;
    public char[][] seatingPlan = new char[ROW][COL];

    public void initialiseSeats() {
        for (char[] row : seatingPlan)
            Arrays.fill(row, ' ');
        }

    public void displaySeats() {
        System.out.println("     A     B     C     D     E     F     G     H  ");
        System.out.println("   _____ _____ _____ _____ _____ _____ _____ _____");
        for (int i=0; i<ROW; i++) {
            System.out.println("  |     |     |     |     |     |     |     |     |");
            System.out.print(i+1 + " ");
            for (int j=0; j<COL; j++) {
                System.out.print("|  " + seatingPlan[i][j] + "  ");
            }
            System.out.print("|");
            System.out.println(" ");
            System.out.println("  |_____|_____|_____|_____|_____|_____|_____|_____|");
        }
    }

    public void getSelection() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 1 - Booking, 2- Cancel, 3 - Exit: ");
        int userChoice = sc.nextInt();

        switch (userChoice) 
        {
            case 1: 
            System.out.print("Enter column (A-H): ");
            char addCol = sc.next().charAt(0);
            System.out.print("Enter row (1-8): ");
            int addRow = sc.nextInt();
            
            if (seatingPlan[addRow - 1][addCol - 'A'] == ' ') { // removeCol - 'A' gets the integer equivalent to the alphabetical col
                seatingPlan[addRow - 1][addCol - 'A'] = 'X';
                System.out.println("Successfully booked seat.");
            } 
            else { System.out.println("Seat has already been taken, please choose another seat."); }
            break;

            case 2:
            System.out.print("Enter column (A-H): ");
            char removeCol = sc.next().charAt(0);
            System.out.print("Enter row (1-8): ");
            int removeRow = sc.nextInt();
          
            if (seatingPlan[removeRow - 1][removeCol - 'A'] == 'X') { // removeCol - 'A' gets the integer equivalent to the alphabetical col
                seatingPlan[removeRow - 1][removeCol - 'A'] = ' ';
                System.out.println("Booking has been successfully cancelled.");
            }
            else { System.out.println("Seat selected has not been booked, please choose a valid seat."); } 
            break;

            case 3:
            System.out.println("Exiting booking system...");
            break;

            default:
            System.out.println("Not a valid option, please try again.");
            break;
        }
    }
}
