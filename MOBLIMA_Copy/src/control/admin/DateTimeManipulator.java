package control.admin;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DateTimeManipulator {
	@SuppressWarnings("resource")
	public LocalDate addDate() {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		do {
			try {
				System.out.println("Input date (dd.MM.yyyy): ");
				String dateInput = sc.nextLine();
				return LocalDate.parse(dateInput, dtf);
			} catch (DateTimeParseException ex) {
				System.out.println("Invalid input. Please use the following format: dd.MM.yyyy!");
			}
		} while(true);
	}
	
	@SuppressWarnings("resource")
	public LocalTime addTime() {
		Scanner sc = new Scanner(System.in);	
		do {
			try {
				System.out.println("Input time (hh:mm): ");
				String timeInput = sc.nextLine();
				timeInput += ":00";
				return LocalTime.parse(timeInput, DateTimeFormatter.ISO_TIME);
			} catch (DateTimeParseException ex) {
				System.out.println("Invalid input. Please use the following format: hh.mm!");
			}
		} while(true);
	}
}
