package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class calculates the rental cost for a car based on various parameters such as rental days, age, and additional features.
 */
public class RentalCarCaculator {

    static Scanner scanner = new Scanner(System.in);
    final static double BASIC_CAR_RENTAL_PRICE = 29.99;
    final static double UNDER_AGE_CHARGE = 0.3;
    final static double TOLL_TAG_CHARGE = 3.95;
    final static double GPS_CHARGE = 2.95;
    final static double SOS_CHARGE = 3.95;

    /**
     * The main method to run the rental car calculator.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String pickUpDate = promptForPickUpDate();
        int numberOfDayRental = promptForRentalDays();
        boolean tollTag = promptForFeature("TOLL");
        boolean GPS = promptForFeature("GPS");
        boolean SOS = promptForFeature("SOS");
        int age = promptForAge();

        calculator(pickUpDate, numberOfDayRental, tollTag, GPS, SOS, age);
    }

    /**
     * Calculates the total rental cost based on the provided parameters.
     * @param pickUpDate The pick-up date in MM-dd-yyyy format.
     * @param numberOfDayRental The number of days the car is rented.
     * @param tollTag Whether the toll tag feature is selected.
     * @param gps Whether the GPS feature is selected.
     * @param sos Whether the SOS feature is selected.
     * @param age The age of the renter.
     */
    private static void calculator(String pickUpDate, int numberOfDayRental, boolean tollTag, boolean gps, boolean sos, int age) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        double total = 0;

        try {
            LocalDate date = LocalDate.parse(pickUpDate, formatter);
            System.out.println(date);

            if (tollTag) {
                total += numberOfDayRental * TOLL_TAG_CHARGE;
            }

            if (gps) {
                total += numberOfDayRental * GPS_CHARGE;
            }

            if (sos) {
                total += numberOfDayRental * SOS_CHARGE;
            }

            if (age > 25) {
                total += numberOfDayRental * BASIC_CAR_RENTAL_PRICE;
            } else {
                total += (numberOfDayRental * BASIC_CAR_RENTAL_PRICE) + (numberOfDayRental * BASIC_CAR_RENTAL_PRICE * UNDER_AGE_CHARGE);
            }

            System.out.println("Your start day will be: " + pickUpDate);
            System.out.println("Your total cost for renting " + numberOfDayRental + " days is " + total);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prompts the user to enter their age.
     * @return The age of the user.
     */
    private static int promptForAge() {
        System.out.println("Enter your age: ");
        int age = 0;
        try {
            age = scanner.nextInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return age;
    }

    /**
     * Prompts the user to select a feature.
     * @param option The feature option (TOLL, GPS, SOS).
     * @return True if the feature is selected, false otherwise.
     */
    private static boolean promptForFeature(String option) {
        try {
            String flag = scanner.nextLine();

            if (option.toLowerCase().trim().equals("sos")) {
                System.out.println("Would you like the roadside assistant?  for ($3.95/day) Yes or No");
                scanner.nextLine();
            } else if (option.toLowerCase().trim().equals("gps")) {
                System.out.println("Would you like the GPS feature?  for ($2.95/day) Yes or No");
            } else if (option.toLowerCase().trim().equals("toll")) {
                System.out.println("Would you like the E-Toll Tag?  for ($3.95/day) Yes or No");
            } else {
                System.out.println("No features available");
            }
            if (flag.trim().toLowerCase().equals("yes")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Prompts the user to enter the number of rental days.
     * @return The number of rental days.
     */
    private static int promptForRentalDays() {
        System.out.println("How many days would you like to rent? : ");
        int rentDays = 0;
        try {
            rentDays = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentDays;
    }

    /**
     * Prompts the user to enter the pick-up date.
     * @return The pick-up date in MM/dd/yy format.
     */
    private static String promptForPickUpDate() {
        System.out.println("Enter your pick up day (mm/dd/yy): ");
        String date = null;
        try {
            date = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}