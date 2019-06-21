package com.luv2code.springdemo.mvc;

import java.util.*;

/**
 *
 * @author sufy
 */

public class HelperClass {

    public static void output(HashMap<String, Advertiser> hm) {
        for (Map.Entry<String, Advertiser> entry : hm.entrySet()) {
            System.out.println(entry.getValue().getAdvertisement());

        }
    }

    public static void createAdvertisers(Customers c) {
    	 /* try {
           new Advertiser(c).createAdvertiser("sufy", "Looking for love", new Requirements("M", 19, 25, 50000, 90000), "M", 29, 30000);
            new Advertiser(c).createAdvertiser("fred", "looking for a good time", new Requirements("F", 19, 40, 10000, 50000), "M", 32, 15000);
            new Advertiser(c).createAdvertiser("kelly", "looking for a fun time", new Requirements("M", 19, 38, 10000, 800000), "F", 32, 15000);
            new Responder(c).createResponder("anna", "F", 28, 30000);
            new Responder(c).createResponder("jim", "M", 21, 20000);
            new Responder(c).createResponder("sufy1", "M", 20, 55555);
        } catch (Exception e) {

        } */
    }

    public static void outputAdvertisements(HashMap<String, Advertiser> hm) {
        for (Map.Entry<String, Advertiser> entry : hm.entrySet()) {
            System.out.println("User: " + entry.getValue().getLogin());
            System.out.println("Advertisement: " + entry.getValue().getAdvertisement());

        }
    }
    

    public static void outputReplies(ArrayList<Replies> rep) {
        for (int i = 0; i < rep.size(); i++) {
            System.out.println("Message: " + rep.get(i).getMessage());
            System.out.println("Responder: " + rep.get(i).getResponder().getLogin());
        }
    }

    public static Customer login(String username, Customers c) {
        return c.getCustomer(username);
    }

    public static void outputLoginPassword(HashMap<String, Customer> hm) {

        for (Map.Entry<String, Customer> entry : hm.entrySet()) {
            System.out.println("User: " + entry.getValue().getLogin());
            System.out.println("Password: " + entry.getValue().getPassword());
            System.out.println("Type: " + entry.getValue().getType());
            System.out.println();

        }
    }

    public static void delete(String username, Customers custs) {
        custs.remove(username);
    }

    public static Customer makeNewUser(Customers c, String username) {
        Scanner kb = new Scanner(System.in);
        String input;
        boolean error = false;
        System.out.println("Do you want to make a new account with the given username?Y/N");

        input = kb.nextLine();
        do {
            if (input.equals("Y") || input.equals("y")) {
                System.out.println("Do you want to be an advertiser or a responder?A/R");
                input = kb.nextLine();
                if (input.equals("A") || input.equals("a")) {
                    Advertiser b = new Advertiser(c);
                    String advertisement;
                    String gender;
                    String prefgen;
                    boolean valid = false;
                    int age, minage, maxage, salary, maxsal, minsal;
                    do {

                        System.out.println("Enter your advertisement");
                        advertisement = kb.nextLine();
                        System.out.println("Enter your gender");
                        gender = kb.nextLine();
                        System.out.println("Enter the gender your seeking");
                        prefgen = kb.nextLine();
                        System.out.println("Enter your age");
                        age = Integer.parseInt(kb.nextLine());
                        System.out.println("Age " + age);
                        System.out.println("Enter the minimum age your seeking");
                        minage = Integer.parseInt(kb.nextLine());

                        System.out.println("Enter the max age your seeking");
                        maxage = Integer.parseInt(kb.nextLine());

                        System.out.println("Enter your salary");
                        salary = Integer.parseInt(kb.nextLine());

                        System.out.println("Enter min sought salary");
                        minsal = Integer.parseInt(kb.nextLine());

                        System.out.println("Enter max sought salary");
                        maxsal = Integer.parseInt(kb.nextLine());

                        try {
                            valid = b.createAdvertiser(username, advertisement, new Requirements(prefgen, minage, maxage, minsal, maxsal), gender, age, salary);
                        } catch (Exception e) {
                            System.out.println("Exception" + e);
                        }
                    } while (!valid);
                    System.out.println("User created: Password is: " + b.getPassword());
                    return b;

                } else if (input.equals("R") || input.equals("r")) {
                    Responder b = new Responder(c);

                    String gender;
                    String prefgen;
                    boolean valid = false;
                    int age, minage, maxage, salary, maxsal, minsal;
                    do {

                        System.out.println("Enter your gender");
                        gender = kb.nextLine();

                        System.out.println("Enter your age");
                        age = Integer.parseInt(kb.nextLine());
                        System.out.println(age);

                        System.out.println("Enter your salary");
                        salary = Integer.parseInt(kb.nextLine());

                        try {
                            valid = b.createResponder(username, gender, age, salary);
                        } catch (Exception e) {
                            System.out.println("Exception" + e);
                        }
                    } while (!valid);
                    System.out.println("User created: Password is: " + b.getPassword());
                    return b;
                }

            }
            return null;
        } while (!error);

    }
    public static String getOptions() {
        String option;
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter A to output advertisers, enter R to output responders, enter L to login to account");
        option=kb.nextLine().trim();
        return option;
        
    }
    public static void outputAdvertisers(Customers c) {
        HashMap<String, Customer> customers=c.giveMap();
        for(Map.Entry<String,Customer> hm : customers.entrySet()) {
            if(hm.getValue().getType().equals("Advertiser")) {
                Advertiser a=(Advertiser)hm.getValue();
                System.out.println("Username: " + a.getLogin() + " Password: " + a.getPassword() +
                        " Gender: " + a.getGender() + " Age: " + a.getAge() + " Advertisement " + a.getAdvertisement());
            }
        }
    }
}
