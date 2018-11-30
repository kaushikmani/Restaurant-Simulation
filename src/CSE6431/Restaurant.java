/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE6431;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author kaushikmani
 */
public class Restaurant {

    //Variables for the three machines
    public static boolean isBurgerFree, isFriesFree, isCokeFree;
    public static Object burgerMachine, friesMachine, cokeMachine;
    
    //Variables for maintaining counts
    public static int dinerCount, tableCount, cookCount;
    public static long startTime;

    //Collections for diners
    public static PriorityQueue<Diner> diners;
    public static ArrayList<Diner> hungryDiners;
    public static ArrayList<Integer> freeTables;

    public static Integer dinerCompletedCount = 0;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        int dinerId, arrivalTime;
        
        //Variables to maintain count of burger, fries and coke
        int burgerCount, friesCount, cokeCount;
        
        //Initialization for various machine availabilities
        isBurgerFree = true;
        isFriesFree = true;
        isCokeFree = true;

        //Initialization for various machines
        burgerMachine = new Object();
        friesMachine = new Object();
        cokeMachine = new Object();

        String input = args[0];
        try (FileReader fileReader = new FileReader(input); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            dinerCount = Integer.parseInt(bufferedReader.readLine().trim());
            if (dinerCount == 0) {
                System.out.println("Number of diners cannot be 0.");
                System.out.println("Exiting system!");
                System.exit(0);
            }
            
            tableCount = Integer.parseInt(bufferedReader.readLine().trim());
            if (tableCount == 0) {
                System.out.println("Number of tables cannot be 0.");
                System.out.println("Exiting system!");
                System.exit(0);
            }
            
            cookCount = Integer.parseInt(bufferedReader.readLine().trim());
            if (cookCount == 0) {
                System.out.println("Number of cooks cannot be 0.");
                System.out.println("Exiting system!");
                System.exit(0);
            }
            
            //Start the timer
            startTime = System.currentTimeMillis();
            
            //Initializing diner collections/data structures
            diners = new PriorityQueue<>();
            hungryDiners = new ArrayList<>();
            
            //Initializing free tables collection/data structure
            freeTables = new ArrayList<>();
            
            for (int i = 1; i <= tableCount; i++) {
                freeTables.add(i);
            }
            
            //Taking the input
            for (int i = 0; i < dinerCount; i++) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                
                String dinerLine = line;
                String[] dinerInfo = dinerLine.split(",");
                dinerId = i + 1;
                arrivalTime = Integer.parseInt(dinerInfo[0]);
                if (arrivalTime > 120) {
                    continue;
                }

                burgerCount = Integer.parseInt(dinerInfo[1]);
                friesCount = Integer.parseInt(dinerInfo[2]);
                cokeCount = Integer.parseInt(dinerInfo[3]);

                Order order = new Order(burgerCount, friesCount, cokeCount);

                Diner diner = new Diner(dinerId, arrivalTime, order);
                diners.add(diner);
            }
            
            if (dinerCount != diners.size()) {
                System.out.println("Number of diners and number of diner inputs is not same");
                System.out.println("System is exiting!");
                System.exit(0);
            }
            
            int i = 0;
            //Starting new thread for every cook
            while (i < cookCount) {
                Cook cook = new Cook();
                cook.CookId = i + 1;
                Thread cookThread = new Thread(cook,"Cook" + cook.CookId);
                cookThread.start();
                i = i + 1;
            }
            
            i = 0;
            //To ensure that diner thread starts only when diner arrives.
            int previousTime = 0;
            //Starting new thread for every diner
            while (i < dinerCount) {
                Diner currentDiner = diners.poll();
                int waitTime = currentDiner.arrivalTime - previousTime;
                Thread dinerThread = new Thread(currentDiner,"Diner" + currentDiner.dinerId);
                Thread.sleep(waitTime*100);
                dinerThread.start();
                previousTime = currentDiner.arrivalTime;
                i = i + 1;
            }
        }
    }

}
