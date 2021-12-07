/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moonshiners;

import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static moonshiners.Moonshiners.input;
import static moonshiners.Moonshiners.username;

public class Player {
    static double cash;
    static int barrels;
    static int sugar;
    static int yeast;
    static int nutrients;
    static int stills;
    static int water;
    static double batches;
    static int debt;
    //user's progression level (LVL) system
    static int lvl;
    static double xp; static double xpMult; static double xpReq; static double xpReqMult;
    
    static boolean tutorial = true;
    
    static Joint s1 = new Joint(0,"s1");
    static Joint s2 = new Joint(0,"s2");
    static Joint s3 = new Joint(0,"s3");
    
    public Player(int c, int su, int y, int n, int w) {
        cash = c;
        barrels = 0;
        sugar = su;
        yeast = y;
        nutrients = n;
        stills = 0;
        water = w;
        batches = 0;
        debt = 0;
        //set player level and required xp to lvl up
        lvl = 0;
        xpReq = 200;
        xpMult = 1.0;
    }
    
    public static void saveGame() {
        try {
        File file = new File("save.txt");
        
        // if the file doesn't exist then create a new one, if it does exist set tutorial to false.
        if (!file.exists()) {
            file.createNewFile();
        }
        else tutorial = false;
        
        PrintWriter pw = new PrintWriter(file);
        pw.println(cash);
        pw.println(barrels);
        pw.println(sugar);
        pw.println(yeast);
        pw.println(nutrients);
        pw.println(stills);
        pw.println(water);
        pw.println(batches);
        pw.println(debt);
        
        
        pw.close();
        System.out.println("Your game has been saved as \"save.txt\"");
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void load() {
        tutorial = false;
        
        File file = new File("save.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cash = scan.nextInt();
        barrels = scan.nextInt();
        sugar = scan.nextInt();
        nutrients = scan.nextInt();
        stills = scan.nextInt();
        water = scan.nextInt();
        batches = scan.nextInt();
        debt = scan.nextInt();
        
        scan.close();
    }
    
    public static void giveBatch(int n) {
        for (int i = 0; i < n; i++) {
            barrels     += 1;
            sugar       += 5;
            yeast       += 1;
            nutrients   += 1;
            stills      += 1;
            water       += 3;
        }
    }
    
    public void deliver() {
        int salePrice;
        double j = batches;
        int police = 0;
        
        System.out.print("\nHow many batches are you delivering? You have " + batches
            + " batches.\n\n> ");
        double k = input.nextDouble();
        
        Random num = new Random();
        
        if (batches == 0) System.out.println("How do you expect to sell moonshine when you DONT HAVE ANY DAMN MOONSHINE!!!??");
        
        for (int i=0; i < k; i++) {
            int rand = num.nextInt(4);
            
            if (tutorial == false) police = num.nextInt(5);
            if (tutorial == true) police = 1;
            
            
            if (i == 0) System.out.println("Off to our first delivery! It's a bit of a drive though...");
            else System.out.println("Driving to the next delivery...");
            
            try {Thread.sleep(1500);}
            catch(InterruptedException ex) {Thread.currentThread().interrupt();}
            
            if (police == 0) {
                System.out.println("OH SHIT! ITS THE FUZZ, RUN!!!\n*"
                        + username + " is running from the police*"
                        + "\nLet's hope he doesn't get caught...");
                
                try {Thread.sleep(1500);}
                catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                
                if (num.nextInt(4) == 0) System.out.println("*" + username + " escaped!*\nCongrats man");
                else {
                    System.out.println("*" + username + " was caught...BUMMER! Your inventory was wiped by PD.\nGod damn shitlickers.....");
                    clearInv();
                    break;
                }
            }
            
            int x = num.nextInt(3);
            switch(x) { // RANDOM DELIVERY TIMER
                case 0 -> {
                    System.out.println("\nShould be there in 5 minutes...");
                    try {Thread.sleep(5000);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                }
                case 1 -> {
                    System.out.println("\nShould be there in 3 minutes...");
                    try {Thread.sleep(3500);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                }
                case 2 -> {
                    System.out.println("\nShould be there in 7 minutes...");
                    try {Thread.sleep(7500);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                }
            }
            
            System.out.println("Here we are, let's give the customer their product.");
            
            salePrice = 1000;
            switch(rand) {
                case 0 -> {
                    salePrice *= 1.728 * xpMult;
                    try {Thread.sleep(1500);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                    System.out.println("This batch sold for $" + salePrice);
                }
                case 1 -> {
                    salePrice *= 1.455 * xpMult;
                    try {Thread.sleep(1500);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                    System.out.println("This batch sold for $" + salePrice);
                }
                case 2 -> {
                    salePrice *= 1.912 * xpMult;
                    try {Thread.sleep(1500);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                    System.out.println("This batch sold for $" + salePrice);
                }
                case 3 -> {
                    salePrice *= 2.452 * xpMult;
                    try {Thread.sleep(1500);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                    System.out.println("This batch sold for $" + salePrice);
                }
            } 
            
            if (batches >= 1) {
                batches--;
            }
            if (batches < 1) {
                salePrice *= batches;
                batches -= batches;
            }
            
            cash += salePrice;
            
            //XP system implementation
            xp += xpMult*100;
            if (didLvl()) {
                System.out.println("\nWOOHOO!! Level " + ++lvl + " baby! Keep progressing to unlock more opportunities...");
                if (lvl < 5) System.out.println("At level 5, there is a \"secretive\" market that will be unlocked for you.");
                
                lvlUp();
            }
        }
        s1.disable(this);
        s2.disable(this);
        s3.disable(this);
    }
    
    public static boolean didLvl() {
        return xp>xpReq;
    }
    
    public static void lvlUp() {
        lvl++; xpReq *= 2;
    }
    
    public static void process() {
        Random rand = new Random();
        if (barrels > 0 && sugar > 4 && stills > 0 && water > 2 && yeast > 0 && nutrients > 0) {
                    System.out.println("\nOkay, now that we have the materials, we can start making shine. Lets drive home...");
                    try {Thread.sleep(7500);} // WAIT 7.5 SECONDS
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}

                    System.out.println("\nNow that we are home, lets put this still somewhere.\n"
                            + "Putting still in place...");
                    try {Thread.sleep(3000);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                    
                    System.out.println("\nNow we add all of our water, sugar, yeast and nutrients.\nHow many batches are we making?");
                    int num = input.nextInt();
                    
                    System.out.println("Okay, now to put our mash in the still(s)..\nNow we are ready to cook up some shine. This shouldn't take longer than 10 minutes...");
                    try {Thread.sleep(10000);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                    
                    int x = rand.nextInt(10); // 1-10 chance to lose half of your stills
                    if (x == 0) System.out.println("BOOOOOM!!!\n\nHoly shit man...one of these stills just fuckin' exploded!\n"
                                + "Half of your stills were destroyed by the explosion, oh well...");
                    
                    // RUN ALL BATCHES WITH THIS 
                    for (int i=0; i<num; i++) {
                        if (barrels >= 0 && sugar > 4 && stills > 0 && water > 2 && yeast > 0 && nutrients > 0) {
                            batches++;
                            barrels--; sugar-=5; stills--; water-=3; yeast--; nutrients--;
                        }
                        else break;
                    }
                   
                    System.out.println("You made " + batches + " batches of shine.\nNow we need to let them steep in barrels."
                            + "\nSteeping...(15-20 second process");
                    
                    int ind = rand.nextInt(6);
                    
                    switch(ind) {
                        case 0: try {Thread.sleep(15000);}
                                catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                            break;
                        case 1: try {Thread.sleep(16000);}
                                catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                            break;
                        case 2: try {Thread.sleep(17000);}
                                catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                            break;
                        case 3: try {Thread.sleep(18000);}
                                catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                            break;
                        case 4: try {Thread.sleep(19000);}
                                catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                            break;
                        case 5: try {Thread.sleep(20000);}
                                catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                            break;
                        default: 
                    }
                    
                    System.out.println("\n Sweet! Now we have " + batches + " batches of white lightning baby!!!");
                    System.out.println("We need to deliver this moonshine...\n");
                    try {Thread.sleep(5000);}
                    catch(InterruptedException ex) {Thread.currentThread().interrupt();}
                }
                else {
                    System.out.println("Hey fucko! You don't have all the ingredients."
                            + "\nLet me remind you since you cannt fuckin remember..."
                            + "\nYou need 3 gallons of water, 5 bags of sugar, 1 barrel, 1 still, 1 pack of yeast and 1 pack of nutrients PER BATCH!");
                }
    }
    
    public static void getInv() {
        System.out.println("Inventory:\n"
                + "\nSugar: " + sugar
                + "\nWater: " + water
                + "\nYeast: " + yeast
                + "\nNutrients: " + nutrients
                + "\nBarrels: " + barrels
                + "\nStills: " + stills
                + "\nBatches: " + batches);
    }
    
    public static void clearInv() {
        batches=0; 
        water=0; 
        barrels=0; 
        sugar=0; 
        yeast=0;
        nutrients=0; 
        stills = 0; 
        cash-=1000;
    }
    
    public static void getCash() {
        System.out.print("You have $");
        System.out.format("%.2f\n", cash);
    } 
    
    public static void cheatCodes(String s) {
        if ("devMode".equals(s)) {
            cash += 100000;          yeast += 999999;     water += 999999;
            barrels += 100000;       nutrients += 99999;  batches += 99999;
            sugar += 999999;         stills += 999999;    debt += -99999;
            
            s1.n = 1000; s2.n = 1000; s3.n = 1000;
            
            lvl = 10000;
        }
        
        
    }
}