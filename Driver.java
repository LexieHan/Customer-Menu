/**
 * Name and ID: Xue Han 40063347 <br>
 * COMP249 <br>
 * Assignment # 1<br>
 * Due Date: July 15, 2018 <br>
 * Purpose: To help a telephone company keep track of the customers they have.
 *
 */

// -----------------------------------------------------
// Assignment 1
// Question: Part 2
// Written by: Xue Han 40063347
// -----------------------------------------------------

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        Scanner keyIn = new Scanner(System.in);
        System.out.println("Hello! Welcome to the company!");
        int maxNumOfCustomer;   // stores the capacity of our customer database
        System.out.print("Please input the maximum number of customers the company can handle: ");
        maxNumOfCustomer = keyIn.nextInt();
        while(maxNumOfCustomer <= 0)    // makes sure the capacity of the database is larger than 0
        {
            System.out.print("Number of customers must be larger than 0. Try again: ");
            maxNumOfCustomer = keyIn.nextInt();
        }

        Customer[] userDataBase = new Customer[maxNumOfCustomer];
        int repeatedTime=0;

        menu(userDataBase,repeatedTime);

    }

    /**
     * Main menu of the software application.
     * @param database the customer database of the company
     * @param repeatedTime the repeated times of the option 1 that is caused by wrongly entered password
     */
    public static void menu(Customer[] database, int repeatedTime)
    {
        Scanner keyIn = new Scanner(System.in);

        int option = 0; // stores the option that user chooses

        while (option<1 || option >5 )  // makes sure the value of the option is between 1 and 5 inclusive
        {
            System.out.println("\nWhat do you want to do?");
            System.out.println("\t1. Enter new customer (password required)");
            System.out.println("\t2. Change information of a customer (password required)");
            System.out.println("\t3. Display all customers residing on the same street");
            System.out.println("\t4. Display all customers residing in the same city");
            System.out.println("\t5. Quit");
            System.out.print("Please enter your choice >");
            option = keyIn.nextInt();
        }

        choose(option, database, repeatedTime);
    }

    /**
     * Switch to the option that user choose
     * @param option the option the user choose
     * @param database the customer database of the company
     * @param repeatedTime  the repeated times of the option 1 that is caused by wrongly entered password
     */
    public static void choose(int option, Customer[] database, int repeatedTime)
    {
        Scanner keyIn = new Scanner(System.in);
        final String passpord = "password";

        switch (option)
        {
            // option 1: Enter new customer
            case 1:
                while(repeatedTime<4)
                {
                    int attemptedTime=0;    // attempt counter of wrongly entered password

                    while(attemptedTime<3)
                    {
                        System.out.print("Please enter your password: ");
                        String input = keyIn.next();

                        if (input.equals(passpord)) // the entered password is correct
                        {
                            System.out.print("How many customers you want to enter? ");

                            int newCus = keyIn.nextInt();
                            int existedCus = Customer.findNumberOfCreatedCustomers();   // the number of customers that have been created
                            int remainingSpace = database.length - existedCus;  // the remaining space of the customer database

                            if (newCus <= remainingSpace)   // remaining space is enough
                            {
                                for (int i=existedCus; i<(existedCus + newCus); i++)
                                {
                                    database[i] = new Customer();

                                    // set the information of the new customer
                                    System.out.println("\nPlease start entering the information of number " + i + " customer");
                                    System.out.print("Name: ");
                                    database[i].setName(keyIn.next());
                                    System.out.print("Street Number: ");
                                    database[i].setStreetNumber(keyIn.nextInt());
                                    System.out.print("Street Name: ");
                                    database[i].setStreetName(keyIn.next());
                                    System.out.print("City: ");
                                    database[i].setCity(keyIn.next());

                                }

                                menu(database, repeatedTime);

                            }

                            else    // remaining space is not enough
                            {
                                System.out.println("Your can only add " + remainingSpace + " customers");
                                menu(database,repeatedTime);

                            }
                        }

                        else // the entered password is wrong
                        {
                            System.out.println("Wrong password. Try again.");
                            attemptedTime++;
                        }

                    }

                    repeatedTime++;

                    if(repeatedTime<4)
                        menu(database,repeatedTime);

                }

                // option 1 is repeated 4 times that is caused by wrongly entered password
                System.out.println("\nProgram detected suspicious activities and will terminate immediately!");
                System.exit(0);

            // option 2: Change information of a customer
            case 2: int attemptedTime=0;    //attempt counter of wrongly entered password
                while(attemptedTime<3)
                {
                    System.out.print("Enter your password: ");
                    String input = keyIn.next();

                    if(input.equals(passpord))  // the password entered is correct
                    {
                        boolean reEnter = true;
                        do
                        {

                            System.out.print("Which customer you wish to update? ");
                            int update = keyIn.nextInt();
                            // the customer the exists. Display his/her information and choose the attribute the user wants to update
                            if (update < Customer.findNumberOfCreatedCustomers() && update >= 0)
                            {
                                System.out.println("Customer: # " + update + "\n" + database[update]);
                                chooseInformation(database, database[update], repeatedTime);
                            }
                            // the customer the user wants to update does not exist. Ask the user either re-enter or go back to the main menu
                            else
                            {
                                System.out.print("No this customer. Do you want to re-enter another customer? ('Yes' for yes, otherwise for no): ");
                                if (keyIn.next().equalsIgnoreCase("yes"))
                                    reEnter = true;
                                else
                                {
                                    reEnter = false;
                                    menu(database, repeatedTime);
                                }
                            }
                        }while(reEnter);

                    }

                    else // the password entered is incorrect
                    {
                        System.out.println("Wrong password. Try again.");
                        attemptedTime++;
                    }
                }
                // go back to the main menu after 3 times of wrongly entered password
                menu(database,repeatedTime);
                break;

            // option 3: Display all customers residing on the same street
            case 3: System.out.print("Enter a street name: ");
                findCustomerByStreet(database, keyIn.next(), repeatedTime);
                break;

            // option 4: Display all customers residing in the same city
            case 4: System.out.print("Enter a city: ");
                findCustomerFromCity(database, keyIn.next(), repeatedTime);
                break;

            // option 5: Quit and end the driver
            case 5: System.out.println("Thanks for using this software application. The driver is going to end!");
                System.exit(0);

        }
    }

    /**
     * Display all customers residing on the specified street
     * @param database  the customer database of the company
     * @param streetName    the street name the user entered
     * @param repeatedTime the repeated times of the option 1 that is caused by wrongly entered password
     */
    public static void findCustomerByStreet(Customer[] database, String streetName, int repeatedTime)
    {
        int numOfCustomer = Customer.findNumberOfCreatedCustomers();
        int count=0;
        for(int i=0; i<numOfCustomer; i++)
        {
            if(database[i].getStreetName().equalsIgnoreCase(streetName))
            {
                System.out.println(database[i] + "\n");
                count++;
            }
        }

        if(count==0)
            System.out.println("No customer resides on this street.");

        menu(database,repeatedTime);    // go back to the main menu
    }

    /**
     * Display all customers residing in the specified city
     * @param database  the customer database of the company
     * @param city  the city the user entered
     * @param repeatedTime  the repeated times of the option 1 that is caused by wrongly entered password
     */
    public static void findCustomerFromCity(Customer[] database, String city, int repeatedTime)
    {
        int numOfCustomer = Customer.findNumberOfCreatedCustomers();
        int count=0;
        for(int i=0; i<numOfCustomer; i++)
        {
            if(database[i].getCity().equalsIgnoreCase(city))
            {
                System.out.println(database[i] + "\n");
                count++;
            }

        }

        if (count==0)
            System.out.println("No customer is form this city.");

        menu(database,repeatedTime);    // go back to the main menu
    }

    /**
     * Choose the information the user wants to update
     * @param database the customer database of the company
     * @param x the customer of which the user wants to update the information
     * @param repeatedTime  the repeated times of the option 1 that is caused by wrongly entered password
     */
    public static void chooseInformation(Customer[] database, Customer x, int repeatedTime)
    {
        Scanner keyIn = new Scanner(System.in);
        int choice = 0;
        while (choice <1 || choice >5)
        {
            System.out.print("What information would you like to change?\n"
                    + "   1. Customer name\n"
                    + "   2. Street number\n"
                    + "   3. Street name\n"
                    + "   4. City\n"
                    + "   5. Quit\n"
                    + "Please enter your choice> ");

            choice = keyIn.nextInt();
        }

        if(choice==5) //quit updating and go back to the main menu
            menu(database, repeatedTime);

        else
            updateInformation(database, x, choice, repeatedTime);

    }

    /**
     * Update the information of the customer the user choosed
     * @param database  the customer database of the company
     * @param x the customer of which the user wants to update the information
     * @param choice the option the user selected
     * @param repeatedTime the repeated times of the option 1 that is caused by wrongly entered password
     */
    public static void updateInformation(Customer[] database, Customer x, int choice, int repeatedTime)
    {
        Scanner keyIn = new Scanner(System.in);

        switch (choice)
        {
            // update the customer name
            case 1: System.out.print("Enter the new customer name: ");
                x.setName(keyIn.next());
                System.out.println("The attribute has been updated.\n" + x );
                chooseInformation(database, x, repeatedTime);
                break;

            // update the street number
            case 2: System.out.print("Enter the new street number: ");
                x.setStreetNumber(keyIn.nextInt());
                System.out.println("The attribute has been updated.\n" + x );
                chooseInformation(database, x, repeatedTime);
                break;

            // update the street name
            case 3: System.out.print("Enter the new street name: ");
                x.setStreetName(keyIn.next());
                System.out.println("The attribute has been updated.\n" + x );
                chooseInformation(database, x, repeatedTime);
                break;

            // update the city
            case 4: System.out.print("Enter the new city: ");
                x.setCity(keyIn.next());
                System.out.println("The attribute has been updated.\n" + x );
                chooseInformation(database, x, repeatedTime);
                break;

        }
    }


}
