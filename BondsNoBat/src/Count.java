import java.util.Random;
public class Count {

    private int balls;
    private int strikes;
    private AtBatResult result;
    public Count(){
        balls = 0;
        strikes = 0;
        result = AtBatResult.Unknown;
    }

    public void nextPitch(char c){
        Random rand = new Random();
        if (c == 'B'){
            balls++;
        }
        else if (c == 'I'){
            balls++;
        }
        else if (c == 'C'){
            strikes++;
        }
        else if (c == 'F' || c == 'L' || c == 'S' || c == 'T' || c == 'X'){
            if (rand.nextInt(999) < 191){
                balls++;
            }
            else{
                strikes++;
            }
        }
        else if (c == 'H'){
            result = AtBatResult.Walk;
        }
    }

    public void createPitch(int balls, int strikes){
        Random rand = new Random();
        if (rand.nextInt(balls+strikes-1) < balls){
            this.balls++;
        }
        else{
            this.strikes++;
        }
    }

    private void updateOutcome(){
        if (strikes == 3){
            result = AtBatResult.Strikeout;
        }
        if (balls == 4){
            result = AtBatResult.Walk;
        }
    }

    public AtBatResult getResult() {
        return result;
    }

    public void resetCount(){
        balls = 0;
        strikes = 0;
        result = AtBatResult.Unknown;
    }

    public AtBatResult resultCheck(){
        this.updateOutcome();
        return result;
    }
}
