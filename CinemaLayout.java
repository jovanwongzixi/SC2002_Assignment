import java.util.Scanner;
import java.util.Arrays;

public class CinemaLayout {
    private int ROW = 12;
    private int COL = 15;
    char[][] seatingPlan = new char[ROW][COL];

    public void initialiseSeats() {
    for (char[] row : seatingPlan)
        Arrays.fill(row, 'X');
    }


    public void displaySeats() {
        Scanner sc = new Scanner(System.in);

        System.out.println("        " + "A B C D    E F G H I J    K L M N O");
        System.out.println("==============================================");
        for (int i=0; i<ROW; i++){
            int row = i + 1;
            if (row < 10) { System.out.print("  "+ row + "  |  "); }
            else { System.out.print(" "+ row + "  |  "); }
                
            for (int j=0; j<COL; j++){
                if (j == 3 || j == 9) { System.out.print(seatingPlan[i][j] + "    "); }  
                else { System.out.print(seatingPlan[i][j] + " "); }
            }
            System.out.println(" ");
        }
        System.out.println("==============================================");  
    }

    public void getSelection() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 1 - Booking, 2- Cancel, 3 - Exit: ");
        int userChoice = sc.nextInt();

        switch (userChoice) 
        {
            case 1: 
            System.out.print("Enter column (A-O): ");
            char addCol = sc.next().charAt(0);
            System.out.print("Enter row (1-12): ");
            int addRow = sc.nextInt();
            
            if (seatingPlan[addRow - 1][addCol - 'A'] == 'X') { // removeCol - 'A' gets the integer equivalent to the alphabetical col
                seatingPlan[addRow - 1][addCol - 'A'] = '-';
                System.out.println("Successfully booked seat.");
            } 
            else { System.out.println("Seat has already been taken, please choose another seat."); }
            break;

            case 2:
            System.out.print("Enter column (A-O): ");
            char removeCol = sc.next().charAt(0);
            System.out.print("Enter row (1-12): ");
            int removeRow = sc.nextInt();
          
            if (seatingPlan[removeRow - 1][removeCol - 'A'] == '-') { // removeCol - 'A' gets the integer equivalent to the alphabetical col
                seatingPlan[removeRow - 1][removeCol - 'A'] = 'X';
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