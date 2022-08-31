import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NoBat{
    public static void main(String[] args) {
        if (args.length == 1) {
            String filename = args[0];
            int takenBalls = 0, takenStrikes = 0, swung = 0;
            int walks = 0, strikeouts = 0, hits = 0, total = 0;
            Count count = new Count();
            try {
                File myObj = new File(filename);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    count.resetCount();
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
                            swung++;
                        }
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
                            else if (result == AtBatResult.Hit){
                                hits++;
                                break;
                            }
                        }
                    }
                    total++;
                }
                myReader.close();
                System.out.println("Walks: " + walks);
                System.out.println("Strikeouts: " + strikeouts);
                System.out.println("Hits: " + hits);
                System.out.println("Balls Taken: " + takenBalls);
                System.out.println("Strikes Taken: " + takenStrikes);
                System.out.println("Swung On: " + swung);
                System.out.println("Total: " + total);
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }
    }
}
