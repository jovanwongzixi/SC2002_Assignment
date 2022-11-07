package control.customer;

import java.util.Scanner;

public class PaymentHandler {
    public static void main(String[] args) {
        PaymentHandler h = new PaymentHandler();
        System.out.println(h.start());
    }
    public boolean start(){
        Scanner sc = new Scanner(System.in);
        boolean inputPass = false;
        String cardNumber;
        System.out.println("Only Mastercard, Visa and American Express card excepted!");
        do {
            System.out.println("Input credit card number:");
            cardNumber = sc.nextLine();
            inputPass = isNumeric(cardNumber);
        }while(!inputPass);
        return checkDigits(cardNumber) && checkSum(cardNumber);
    }
    private boolean checkSum(String cardNumber){
        int checkSum=0;
        for(int i=cardNumber.length()-1; i>=0; i--){
            int digit = cardNumber.charAt(i)-'0';
            if((cardNumber.length()-i)%2==0) digit *=2;
            checkSum += digit/10;
            checkSum += digit%10;
        }
        return checkSum%10==0;
    }
    private boolean isNumeric(String num){
        if (num==null) return false;
        try{
            Long number = Long.parseLong(num);

        } catch (NumberFormatException e){
            System.out.println("Input is not numeric!");
            return false;
        }
        return true;
    }
    private boolean checkDigits(String cardNumber){
        //Check American Express
        if((cardNumber.startsWith("34") || cardNumber.startsWith("37")) && cardNumber.length() == 15) return true;
        //Check Visa
        else if(cardNumber.startsWith("4") && (cardNumber.length() == 13 || cardNumber.length() == 16)) return true;
        //Check MasterCard
        else return (Integer.parseInt(cardNumber.substring(0,2))>=22 || Integer.parseInt(cardNumber.substring(0,2))<=27 || Integer.parseInt(cardNumber.substring(0,2))>=51 || Integer.parseInt(cardNumber.substring(0,2))<=55)&& cardNumber.length() == 16;
    }
}
