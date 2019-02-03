/**
 * Name and ID: Xue Han 40063347 <br>
 * COMP249 <br>
 * Assignment # 1 <br>
 * Due Date: July 15, 2018 <br>
 * Purpose: Defining the Customer class
 */

// -----------------------------------------------------
// Assignment 1
// Question: Part 1
// Written by: Xue Han 40063347
// 


public class Customer
{
    private String name;
    private int streetNumber;
    private String streetName;
    private String city;
    private static int createdCustomer = 0;

    /**
     * To create a Customer object.
     * All the attributes are set to "null" or 0.
     */
    public Customer()
    {
        name = "null";
        streetNumber = 0;
        streetName = "null";
        city = "null";
        createdCustomer++;
    }
    /**
     * To create a Customer object.
     * @param name the name of the customer
     * @param streetNumber the street number of the customer
     * @param streetName the street name where the customer resides on
     * @param city the city where the customer resides in
     */
    public Customer(String name, int streetNumber, String streetName, String city)
    {
        this.name = name;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        createdCustomer++;
    }

    /**
     * copy constructor.
     * @param anotherCustomer another Customer object, the same as the customer we want to create
     */
    public Customer(Customer anotherCustomer)
    {
       name = anotherCustomer.name;
       streetNumber =anotherCustomer.streetNumber;
       streetName = anotherCustomer.streetName;
       city = anotherCustomer.city;
       createdCustomer++;
    }

    /**
     * Mutator method.
     * @param name the updated name of the customer
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Accessor method.
     * @return the name of the customer
     */
    public String getName()
    {
        return name;
    }

    /**
     * Mutator method.
     * @param streetNumber the updated street number of the customer
     */
    public void setStreetNumber(int streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    /**
     * Accessor method.
     * @return the street number of the customer
     */
    public int getStreetNumber()
    {
        return streetNumber;
    }

    /**
     * Mutator method.
     * @param streetName the updated street name of the customer
     */
    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    /**
     * Accessor method
     * @return the street name of the customer
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * Mutator method
     * @param city the updated city where the customer resides in
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Accessor method.
     * @return the city where the customer resides in
     */
    public String getCity()
    {
        return city;
    }

    /**
     * To display the information of the customer
     */
    public String toString()
    {
        return "Name: " + name
                + "\nStreetNum: " + streetNumber
                + "\nStreetName: " + streetName
                + "\nCity: " + city;
    }

    /**
     * Two customers are considered equal if they have the same street name and city.
     * @param customer another customer that we want to compare to 
     * @return return true if two customers have the same name and city
     */
    public boolean equals(Customer customer)
    {
        return name.equals(customer.name) && city.equals(customer.city);
    }

    /**
     * Display the number of the customers that have been created.
     * @return return the number of customers have been created
     */
    public static int findNumberOfCreatedCustomers()
    {
        return createdCustomer;
    }


}

