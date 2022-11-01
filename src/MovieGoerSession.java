import java.util.Scanner;

public class MovieGoerSession {
    private MovieGoer currentUser;
    private MovieGoerAccountHandler handler;
    public MovieGoerSession(){
        handler = new MovieGoerAccountHandler();
    }
    public MovieGoer run(){
        Scanner sc = new Scanner(System.in);
        int option;
        boolean flag=false;
        while(!flag){
            System.out.println("""
                MovieGoer Login Menu
                --------------------
                1) Login
                2) Create account
                3) Guest account
                Enter option:""");
            option = sc.nextInt();
            switch(option){
                case 1 ->flag = login();
                case 2 -> flag = createAccount();
                case 3 ->{
                    flag = true;
                    inputDetails();
                }
                default -> flag = true;
            }
        }
        return currentUser;
    }
    private boolean login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email address:");
        String email = sc.nextLine();
        System.out.println("Enter mobile number:");
        int number = sc.nextInt();
        currentUser = handler.getAccount(email, number);
        if (currentUser!=null){
            System.out.println("Login successful!");
            return true;
        }
        else{
            System.out.println("Login unsuccessful! Please try again!");
            return false;
        }
    }
    private boolean createAccount(){
        inputDetails();
        boolean flag = handler.addAccount(currentUser);
        if(flag) System.out.println("Account created!");
        else System.out.println("Account already exists! Use login function.");
        return flag;
    }
    private void inputDetails(){
        currentUser = new MovieGoer();
        currentUser.setDetails();
    }
}
