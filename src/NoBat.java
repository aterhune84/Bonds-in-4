import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class NoBat{
    public static void main(String[] args) {
        if (args.length == 1) {
            String filename = args[0];
            int takenBalls = 0, takenStrikes = 0;
            int walks = 0, strikeouts = 0, total = 0;
            Count count = new Count();
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
                            if (rand.nextInt(999) < 191){
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
            //Running the simulation 1000 times to produce more accurate numbers and account for variation
            int totalWalks = 0;
            int totalStrikeouts = 0;
            int maxWalks = 0;
            int maxStrikeouts = 0;
            for (int i = 1; i <= 1000; i++){
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
                                if (result == AtBatResult.Walk){
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
            double averageWalks = totalWalks / 1000.0;
            double averageStrikeouts = totalStrikeouts / 1000.0;
            System.out.println("Taken Balls: " + takenBalls);
            System.out.println("Taken Strikes: " + takenStrikes);
            System.out.println("Average Walks: " + averageWalks);
            System.out.println("Average Strikeouts: " + averageStrikeouts);
            System.out.println("Most Walks: " + maxWalks);
            System.out.println("Least Walks: " + (total / 1000 - maxStrikeouts));
            System.out.println("Best OBP: " + maxWalks / ((double)total / 1000));
            System.out.println("Worst OBP: " + (total / 1000 - maxStrikeouts) / ((double)total / 1000));
            System.out.println("Average On Base Percentage: " + averageWalks / (averageWalks + averageStrikeouts));
        }
    }
}
