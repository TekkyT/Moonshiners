/*         Game: MOONSHINERS        Author: Jailbait/TekkyT/Brendan (individual)
**      A very basic text based game with minor intriquacies such as randomly timed random events,
**  basic math, a Player object definition, etc.
**  
**              TODO LIST:
** Start the runGame() process :) this should be fucking atrocious :D:D:D:D
** add a bunch of cooler shit to make it somewhat interesting

- Add progression system (ADDED AND IMPLEMENTED TO DELIVERY)
- Add black market at level 5


- convert Player's inventory items to individual classes
    - Sugar: make it so additives can be mixed such as cocaine to increase amount of batches




*/




package moonshiners;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author tekky
 */

public class Moonshiners {
    static Player user = new Player(4000,10,2,2,6);
    
    //random global strings
    static String m = "multiply";
    static String a = "add";
    static String d = "divide";
    static String s = "subtract";
    static String username;
    
    //global input scanner
    static Scanner input = new Scanner(System.in);
    
    static String shopMenu = 
            "- Gallon of Water = $5\n" + 
            "- 5lb Bag of Sugar = $10\n" + 
            "- Yeast Packet = $5\n" + 
            "- Pack of Nutrients = $3\n" + 
            "- Moonshine Still = $1000\n" + 
            "- Barrel = $50\n"
            + "- EVERYTHING (one batch) - 'batch' = $1100";
    
    public static void getHelp() {
        System.out.println("\n\nHere is a list of commands as of right now:");
        
        System.out.println( "- buy materials / shop\n"
                + "- make moonshine / make / process / distill\n"
                + "- deliver moonshine / deliver / sell\n"
                + "- check wallet / wallet / cash\n"
                + "- inventory / inv / i\n"
                + "- help / ?\n"
                + "- quit / end\n"
                + "- save / save game\n"
                + "- level / lvl / xp\n"
                + "- black market / bm (required to be level 5)\n"
                + "There are a few cheat codes, those are hidden though...");
    }
    
    public static void main(String[] args) {
        System.out.println("Have you played moonshiners before? (Y/N)");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            user.load();
            System.out.print("Welcome back! What's your name again?\n>");
            username = input.nextLine();
            System.out.println("Oh that's right. " + username + " we have some shine to make! You are level " + user.lvl + "."
                    + "\nAt level 5 there will be a bit of an expansion to your business...");
        }
        
        if (user.tutorial == true) {
            System.out.println("Welcome to moonshiners!\nWhat is your name?");

            username = input.nextLine();
            System.out.println("\nWell " + username + ", let's go over your backstory.");

            //CHARACTER BACKSTORY
            System.out.println("\n"+username+" was born in the heart of 'shine country, the Northern Mountains of Georgia.\n"
                            + "He was no stranger to the life of moonshiners, even as a child; raised by moonshine veteran "
                            + "Marvin \"Popcorn\" Sutton.");
            try {Thread.sleep(5000);}
            catch(InterruptedException ex) {Thread.currentThread().interrupt();}
            System.out.println("Now that we are up to speed on your upbringing, let's refresh your memory on the basics.");
            //TUTORIAL
        
            System.out.println(
                                "Here's enough money for a few batches, spend it wisely.\n" + 
                                "   +$4,000 Cash\n" + 
                                "You also have enough materials to make 2 batches of shine.\n" + 
                                "Let's get started by making these 2 batches and selling them.\n");
        }
        
        
        boolean delivered = false;
        
        while (user.tutorial == true) {
            System.out.println(
                "What do you want to do?\n" +
                "\"- Check Wallet\"\n" + 
                "\"- Buy Materials\"\n" + 
                "\"- Make Moonshine\"\n" + 
                "\"- Deliver Moonshine\"\n" + 
                "\"- Inventory\"\n" +
                "\"- help / ?\"\n");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("Buy Materials") || choice.equalsIgnoreCase("shop")) { openShop(); continue; }
            else if (choice.equalsIgnoreCase("Inventory") || choice.equalsIgnoreCase("inv")) user.getInv();
            else if (choice.equalsIgnoreCase("make moonshine") || choice.equalsIgnoreCase("process") || choice.equalsIgnoreCase("make") || choice.equalsIgnoreCase("distill")) user.process();
            else if (choice.equalsIgnoreCase("deliver moonshine") || choice.equalsIgnoreCase("deliver") || choice.equalsIgnoreCase("sell")) { user.deliver(); delivered = true; }
            else if (choice.equalsIgnoreCase("check wallet") || choice.equalsIgnoreCase("wallet") || choice.equalsIgnoreCase("cash")) user.getCash();
            else if (choice.equalsIgnoreCase("help") || choice.equalsIgnoreCase("?")) getHelp();
            else if (choice.equalsIgnoreCase("save") || choice.equalsIgnoreCase("Save Game")) user.saveGame();
            else if ("devMode".equals(choice)) user.cheatCodes("devMode");
            else if ("revertNormal".equals(choice)) user.cheatCodes("revert");
            else if ("skip".equals(choice))user.tutorial = false;
            
            if (delivered == true) user.tutorial = false;  // Once the delivery is done the tutorial is over, can run game :)
        }
        
        // LAUNCH THIS BITCH UP 
        runGame();
        
    }
    
    public static void runGame() {
        boolean playing = true;
        
        String choice = "";
        System.out.println("You look like you're getting the hang of this."
                + " Now you are off on your own, no more help from me ;)\n"
                + "You can do it, don't worry. If you ever need to look at "
                + "the commands again, type help.");
        while (playing == true) {
            if (user.lvl == 5) {
                System.out.println("Cool, you're level 5. Let's head over to the new Black Market and see what they have.\n"
                        + "(Type \"black market\" or \"bm\"");
            }
            System.out.print("\n> ");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("Buy Materials") || choice.equalsIgnoreCase("shop") || choice.equalsIgnoreCase("buy")) openShop();
            else if (choice.equalsIgnoreCase("Inventory") || choice.equalsIgnoreCase("inv") || choice.equalsIgnoreCase("i")) user.getInv();
            else if (choice.equalsIgnoreCase("make moonshine") || choice.equalsIgnoreCase("process") || choice.equalsIgnoreCase("make") || choice.equalsIgnoreCase("distill")) user.process();
            else if (choice.equalsIgnoreCase("deliver moonshine") || choice.equalsIgnoreCase("deliver") || choice.equalsIgnoreCase("sell")) user.deliver();
            else if (choice.equalsIgnoreCase("check wallet") || choice.equalsIgnoreCase("wallet") || choice.equalsIgnoreCase("cash")) user.getCash();
            else if (choice.equalsIgnoreCase("help") || choice.equalsIgnoreCase("?")) getHelp();
            else if (choice.equalsIgnoreCase("quit") || choice.equalsIgnoreCase("end")) playing = false;
            else if (choice.equalsIgnoreCase("save") || choice.equalsIgnoreCase("Save Game")) user.saveGame();
            else if (choice.equalsIgnoreCase("level") || choice.equalsIgnoreCase("lvl") || choice.equalsIgnoreCase("xp")) 
                System.out.println("You are level " + user.lvl + "You need " + (user.xpReq - user.xp) + " more xp to level up.");
            else if ("devMode".equals(choice)) user.cheatCodes("devMode");
            else if ("revertNormal".equals(choice)) user.cheatCodes("revert");
            else if ("black market".equalsIgnoreCase(choice) || "bm".equalsIgnoreCase(choice)) {
                if (user.lvl >= 5) openBlackMarket();
                else System.out.println("How do you know about that...come back when you're level 5. You are level " + user.lvl);
            }
            else if ("smoke joint".equalsIgnoreCase(choice) || "smoke".equalsIgnoreCase(choice)) smokeJoint();
        }
    } 
    
    public static void smokeJoint() {
        System.out.print("\nWhich one(s1/s2/s3)? You have:\n"
                                + "Blue Dream(s1): " + user.s1.n + "\n"
                                + "Gorilla Glue(s2): " + user.s2.n + "\n"
                                + "Moonrock(s3): " + user.s3.n + "\n\n> ");
        String a = input.nextLine();
        switch (a) {
            case "s1" -> { 
                if (user.s1.n > 0) user.s1.enable(user);
                else System.out.println("You don't have any Blue Dream.\n");
            }
            case "s2" -> {
                if (user.s2.n > 0) user.s2.enable(user);
                else System.out.println("You don't have any Gorilla Glue.\n");
            }
            case "s3" -> { 
                if (user.s3.n > 0) user.s3.enable(user);
                else System.out.println("You don't have any Moonrock.\n");
            }
        }
    }
    
    // STILL NEED TO FINISH BLACK MARKET
    public static void openBlackMarket() { 
        int total = 0, num;
        
        System.out.println("Welcome to the illegal market " + username + ".");
        System.out.println("Here is what we have to offer today:");
        System.out.println( "1. Revolver: S&W K22 - $10,000\n"
                          + "2. Weed Joint: Blue Dream - $200\n"
                          + "3. Body Armor");
        if (user.lvl >= 7)
            System.out.println("4. Weed Joint: Gorilla Glue - $500\n"
                            + "5. Moonrock Joint - $1500\n"
                            + "6. Cocaine (1g) - $150\n");        
        
        System.out.println("Enter the NUMBER of the item you want to buy.\nIf you don't know what you're looking at, type help.");
        System.out.print("\n> ");
        String choice = input.nextLine();
       
        System.out.print("How many do you want?\n> ");
        num = input.nextInt();
        
        switch(choice) {
            case "1" -> { // needs finished
                total += 10000*num;
            }
            case "2" -> {
                user.s1.n += num;
                total += 200*num;
            }
            case "3" -> {
                total += 500*num;
            }
            case "4" -> {
                user.s2.n += num;
                total += 1500*num;
            }
            case "5" -> {
                user.s3.n += num;
                total += 150*num;
            }
            case "6" -> {
                
            }
            case "help" -> {
                System.out.println("A gun is necessary to fight the cops once you're level 5. You have a very SMALL chance to be caught if you use your gun.\n"
                        + "You will only lose the gun if the cops catch you AND you used the gun against them.\n"
                        + "The police will catch you precisely 2% (percent) of the time if you use your gun.\n"
                        + "\nJoints are pretty cool, they will basically make your next delivery run give you more money.\n"
                        + "So if you smoke the cheapest joint, Blue Dream, before you deliver your shine, your entire run (unlimited batches)\n"
                        + "will sell for 1.1x the base sale price, and you'll receive 1.25x the base xp for the sales.\n"
                        + "You gain access to more items after level 7.\n"
                        + "Joint xp and cash mult chart:\n"
                        + "- Blue Dream (s1):   $ x 1.5     XP x 1.5\n"
                        + "- Gorilla Glue (s2): $ x 2.0     XP x 2.0\n"
                        + "- Moonrock (s3):     $ x 3.0     XP x 3.0\n");
            }   
            default -> {}
        }
    }
    
    public static void openShop () {
        String s2;
        int total = 0; boolean buying = true;
        
        while (buying == true) {
            System.out.println("What would you like to purchase? \n(type water, sugar, yeast, nutrients).\n(enter \"nothing\" to close the shop)");
            System.out.println("For one batch of moonshine you need 3 gallons of water, 5 packs of sugar(5lbs), 1 pack of yeast, 1 pack of nutrients, 1 still, and 1 barrel.");
            System.out.print("\nWallet: $");
            System.out.format("%.2f\n", user.cash);
            
            Scanner in = new Scanner(System.in);
            System.out.println(shopMenu);
            System.out.println("Cart Total: $" + total);
            System.out.println("\n> ");
            String s1 = in.nextLine();
            
            if (s1.equalsIgnoreCase("nothing")) {
                System.out.println("\nYour total is $" + total);
                System.out.print("Purchase? ");
                s2 = in.nextLine(); 
                if (s2.equalsIgnoreCase("yes")) {
                    if (user.cash < total) {
                        System.out.println("You broke mothafucka! You don't have enough for that. Redo!");
                        total = 0;
                            try {Thread.sleep(3000);}
                            catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                        continue;
                    } else {
                        user.cash -= total;
                        System.out.println("-$" + total);
                        break;
                    }
                }
            }

            System.out.print("Quantity: ");
            int num = input.nextInt();
            
            System.out.print("Add to cart? ");
            s2 = in.nextLine();

            if (s2.equalsIgnoreCase("yes")) {
                if (s1.equalsIgnoreCase("water"))                                                   {total += 5*num; user.water += num; continue;}
                else if (s1.equalsIgnoreCase("sugar"))                                              {total += 10*num; user.sugar += num; continue;}
                else if (s1.equalsIgnoreCase("yeast"))                                              {total += 5*num; user.yeast += num; continue;}
                else if (s1.equalsIgnoreCase("nutrients"))                                          {total += 3*num; user.nutrients += num; continue;}
                else if (s1.equalsIgnoreCase("still") || s1.equalsIgnoreCase("moonshine still"))    {total += 1000*num; user.stills += num; continue;}
                else if (s1.equalsIgnoreCase("barrel"))                                             {total += 50*num; user.barrels += num; continue;}
                else if (s1.equalsIgnoreCase("batch"))                                              {total += 1100*num; user.giveBatch(num); continue;}
                else {};
            } 
            else continue;
            
            System.out.print("Purchase another product? ");
            s1 = in.nextLine();
            buying = s1.equalsIgnoreCase("yes") || 
                     s1.equalsIgnoreCase("y") ||
                     s1.equalsIgnoreCase("yea");

        }
        System.out.println("Wallet: $" + user.cash);
    }
    
    public static int math(int x, int y, String type) {
        if (type.equalsIgnoreCase("multiply")) return x*y;
        else if (type.equalsIgnoreCase("divide")) return x/y;
        else if (type.equalsIgnoreCase("subtract")) return x-y;
        else if (type.equalsIgnoreCase("add")) return x+y;
        else return 0;
    } 
}