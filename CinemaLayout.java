import java.util.Scanner;
import java.util.Arrays;

public class CinemaLayout {
    private int ROW = 8;
    private int COL = 8;
    public char[][] seatingPlan = new char[ROW][COL];

    public void initialiseSeats() {
        for (char[] row : seatingPlan)
            Arrays.fill(row, ' ');
        }

    public void displaySeats() {
        System.out.println("     A     B     C     D          E     F     G     H  ");
        System.out.println("   _______________________       _______________________");

        // Print couple seats
        for (int i=0; i<2; i++) {
            System.out.println("  |           |           |     |           |           |");
            System.out.print(i+1 + " |");
            for (int j=0; j<COL; j++) {
                if (j%2==1 && j<4)
                    System.out.print("  " + seatingPlan[i][j] + "  |");
                else if (j==4)
                    System.out.print("     |  " + seatingPlan[i][j] + "  ");
                else if (j%2==0 && j>=4)
                    System.out.print("|  " + seatingPlan[i][j] + "  ");
                else
                    System.out.print("   " + seatingPlan[i][j] + "  ");
            }
            System.out.print("|");
            System.out.println(" ");
            System.out.println("  |___________|___________|     |___________|___________|");
        }

        // Print single seats
        for (int i=2; i<ROW; i++) {
            System.out.println("  |     |     |     |     |     |     |     |     |     |");
            System.out.print(i+1 + " ");
            for (int j=0; j<COL; j++) {
                System.out.print("|  " + seatingPlan[i][j] + "  ");
            }
            System.out.print("|     |");
            System.out.println(" ");
            System.out.println("  |_____|_____|_____|_____|     |_____|_____|_____|_____|");
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
            char add = sc.next().charAt(0);
            int addCol = add - 'A';
            System.out.print("Enter row (1-8): ");
            int addRow = sc.nextInt();
            
            if (seatingPlan[addRow-1][addCol]==' ') {
                if (addRow==1 || addRow==2) { // Double seat vacancy
                    seatingPlan[addRow - 1][addCol] = 'X';
                    if (addCol%2 == 0) 
                        seatingPlan[addRow - 1][addCol + 1] = 'X';
                    else 
                        seatingPlan[addRow - 1][addCol - 1] = 'X';
                    System.out.println("Successfully booked seat.");
                }
                else { // Single seat vacancy
                    seatingPlan[addRow - 1][addCol] = 'X';
                    System.out.println("Successfully booked seat.");
                }
            }
            else { System.out.println("Seat has already been taken, please choose another seat."); }
            break;


            case 2:
            System.out.print("Enter column (A-H): ");
            char remove = sc.next().charAt(0);
            int removeCol = remove - 'A';
            System.out.print("Enter row (1-8): ");
            int removeRow = sc.nextInt();

            if (seatingPlan[removeRow-1][removeCol]=='X') {
                if (removeRow==1 || removeRow==2) { // Double seat vacancy
                    seatingPlan[removeRow - 1][removeCol] = ' ';
                    if (removeCol%2 == 0) 
                        seatingPlan[removeRow - 1][removeCol + 1] = ' ';
                    else 
                        seatingPlan[removeRow - 1][removeCol - 1] = ' ';
                    System.out.println("Booking has been successfully cancelled.");
                }
                else { // Single seat vacancy
                    seatingPlan[removeRow - 1][removeCol] = 'X';
                    System.out.println("Booking has been successfully cancelled.");
                }
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
