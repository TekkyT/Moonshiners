/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moonshiners;


public class Joint {
    int n;
    String type;
    boolean active;
    
    public Joint(int num, String s) {
        n = num;
        type = s;
    }
    
    void enable(Player p) {
        active = true;
        switch(type) {
            case "s1" -> {
                p.xpMult += 1.5;
            }
            case "s2" -> {
                p.xpMult += 2.0;
            }
            case "s3" -> {
                p.xpMult += 3.0;
            }
        }
        n--;
    }
    
    void disable(Player p) {
        active = false;
        if (n > 0) {
            switch(type) {
                case "s1" -> {
                    p.xpMult -= 1.5;
                }
                case "s2" -> {
                    p.xpMult -= 2.0;
                }
                case "s3" -> {
                    p.xpMult -= 3.0;
                }
            }
        }
    }
}
