import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class NoBat{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please type the name of the file where the pitch data is: ");
        String filename = scanner.nextLine();
        System.out.println("You will now be asked for plate discipline stats.");
        System.out.println("You can get these numbers from FanGraphs.");
        System.out.println("Please put a number out of 100. (Decimal points are allowed)");
        System.out.print("What is the player's Z-Swing%? ");
        double zSwing = scanner.nextDouble();
        System.out.print("What is the player's Swing%? ");
        double swing = scanner.nextDouble();
        System.out.print("What is the player's Zone%? ");
        double zone = scanner.nextDouble();
        System.out.print("\nHow many times would you like to simulate this season? ");
        int sims = scanner.nextInt();
        int takenBalls = 0, takenStrikes = 0;
        int walks, strikeouts, total = 0;
        double recipSwing = 1.0/swing;
        double recipZone = 1.0/zone;
        double swingChance = recipSwing * recipZone * zSwing;
        int swingRand = (int) swingChance * 10000;
        Count count = new Count(swingRand);
        //Go through pitch data and generate balls and strikes
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                char[] chars = data.toCharArray();
                for (char c: chars){
                    if (c == 'B' || c == 'H'){
                        takenBalls++;
                    }
                    else if (c == 'C') {
                        takenStrikes++;
                    }
                    else if (c == 'F' || c == 'L' || c == 'S' || c == 'T' || c == 'X'){
                        Random rand = new Random();
                        if (rand.nextInt(999) > swingRand){
                            takenBalls++;
                        }
                        else{
                            takenStrikes++;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int totalWalks = 0;
        int totalStrikeouts = 0;
        int maxWalks = 0;
        int maxStrikeouts = 0;
        for (int i = 1; i <= sims; i++){
            walks = 0;
            strikeouts = 0;
            // Simulate each plate appearance without a bat
            try {
                File myObj = new File(filename);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    count.resetCount();
                    String data = myReader.nextLine();
                    char[] chars = data.toCharArray();
                    for (char c: chars){
                        count.nextPitch(c);
                        if (count.resultCheck() != AtBatResult.Unknown){
                            AtBatResult result = count.resultCheck();
                            if (result == AtBatResult.Walk) {
                                walks++;
                                break;
                            }
                            else if (result == AtBatResult.Strikeout){
                                strikeouts++;
                                break;
                            }
                        }
                    }
                    if (count.resultCheck() == AtBatResult.Unknown){
                        while (count.resultCheck() == AtBatResult.Unknown){
                            count.createPitch(takenBalls, takenStrikes);
                        }
                        if (count.resultCheck() == AtBatResult.Walk){
                            walks++;
                        }
                        else if (count.resultCheck() == AtBatResult.Strikeout){
                            strikeouts++;
                        }
                    }
                    total++;
                }
                myReader.close();
                totalWalks += walks;
                totalStrikeouts += strikeouts;
                if (walks > maxWalks){
                    maxWalks = walks;
                }
                if (strikeouts > maxStrikeouts){
                    maxStrikeouts = strikeouts;
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }
        double averageWalks = totalWalks / (double) sims;
        double averageStrikeouts = totalStrikeouts / (double) sims;
        System.out.println("Taken Balls: " + takenBalls);
        System.out.println("Taken Strikes: " + takenStrikes);
        System.out.println("Average Walks: " + averageWalks);
        System.out.println("Average Strikeouts: " + averageStrikeouts);
        System.out.println("Most Walks: " + maxWalks);
        System.out.println("Least Walks: " + (total / sims - maxStrikeouts));
        System.out.println("Best OBP: " + maxWalks / ((double) total / sims));
        System.out.println("Worst OBP: " + (total / sims - maxStrikeouts) / ((double) total / sims));
        System.out.println("Average On Base Percentage: " + averageWalks / (averageWalks + averageStrikeouts));
    }
}
