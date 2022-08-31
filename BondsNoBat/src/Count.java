import java.util.Random;
public class Count {

    private int balls;
    private int strikes;
    private AtBatResult result;
    private int balls00;
    private int strikes00;
    private int balls01;
    private int strikes01;
    private int balls02;
    private int strikes02;
    private int balls10;
    private int strikes10;
    private int balls11;
    private int strikes11;
    private int balls12;
    private int strikes12;
    private int balls20;
    private int strikes20;
    private int balls21;
    private int strikes21;
    private int balls22;
    private int strikes22;
    private int balls30;
    private int strikes30;
    private int balls31;
    private int strikes31;
    private int balls32;
    private int strikes32;
    public Count(){
        balls = 0;
        strikes = 0;
        result = AtBatResult.Unknown;
        balls00 = 0;
        strikes00 = 0;
        balls01 = 0;
        strikes01 = 0;
        balls02 = 0;
        strikes02 = 0;
        balls10 = 0;
        strikes10 = 0;
        balls11 = 0;
        strikes11 = 0;
        balls12 = 0;
        strikes12 = 0;
        balls20 = 0;
        strikes20 = 0;
        balls21 = 0;
        strikes21 = 0;
        balls22 = 0;
        strikes22 = 0;
        balls30 = 0;
        strikes30 = 0;
        balls31 = 0;
        strikes31 = 0;
        balls32 = 0;
        strikes32 = 0;
    }

    public void nextPitch(char c){
        Random rand = new Random();
        if (c == 'B'){
            if (balls == 0){
                if (strikes == 0){
                    balls00++;
                }
                else if (strikes == 1){
                    balls01++;
                }
                else if (strikes == 2){
                    balls02++;
                }
            }
            else if (balls == 1){
                if (strikes == 0){
                    balls10++;
                }
                else if (strikes == 1){
                    balls11++;
                }
                else if (strikes == 2){
                    balls12++;
                }
            }
            else if (balls == 2){
                if (strikes == 0){
                    balls20++;
                }
                else if (strikes == 1){
                    balls21++;
                }
                else if (strikes == 2){
                    balls22++;
                }
            }
            else if (balls == 3){
                if (strikes == 0){
                    balls30++;
                }
                else if (strikes == 1){
                    balls31++;
                }
                else if (strikes == 2){
                    balls32++;
                }
            }
            balls++;
        }
        else if (c == 'I'){
            balls++;
        }
        else if (c == 'C'){
            if (balls == 0){
                if (strikes == 0){
                    strikes00++;
                }
                else if (strikes == 1){
                    strikes01++;
                }
                else if (strikes == 2){
                    strikes02++;
                }
            }
            else if (balls == 1){
                if (strikes == 0){
                    strikes10++;
                }
                else if (strikes == 1){
                    strikes11++;
                }
                else if (strikes == 2){
                    strikes12++;
                }
            }
            else if (balls == 2){
                if (strikes == 0){
                    strikes20++;
                }
                else if (strikes == 1){
                    strikes21++;
                }
                else if (strikes == 2){
                    strikes22++;
                }
            }
            else if (balls == 3){
                if (strikes == 0){
                    strikes30++;
                }
                else if (strikes == 1){
                    strikes31++;
                }
                else if (strikes == 2){
                    strikes32++;
                }
            }
            strikes++;
        }
        else if (c == 'F' || c == 'L' || c == 'S' || c == 'T'){
            if (rand.nextInt(999) < 191){
                if (balls == 0){
                    if (strikes == 0){
                        balls00++;
                    }
                    else if (strikes == 1){
                        balls01++;
                    }
                    else if (strikes == 2){
                        balls02++;
                    }
                }
                else if (balls == 1){
                    if (strikes == 0){
                        balls10++;
                    }
                    else if (strikes == 1){
                        balls11++;
                    }
                    else if (strikes == 2){
                        balls12++;
                    }
                }
                else if (balls == 2){
                    if (strikes == 0){
                        balls20++;
                    }
                    else if (strikes == 1){
                        balls21++;
                    }
                    else if (strikes == 2){
                        balls22++;
                    }
                }
                else if (balls == 3){
                    if (strikes == 0){
                        balls30++;
                    }
                    else if (strikes == 1){
                        balls31++;
                    }
                    else if (strikes == 2){
                        balls32++;
                    }
                }
                balls++;
            }
            else{
                if (balls == 0){
                    if (strikes == 0){
                        strikes00++;
                    }
                    else if (strikes == 1){
                        strikes01++;
                    }
                    else if (strikes == 2){
                        strikes02++;
                    }
                }
                else if (balls == 1){
                    if (strikes == 0){
                        strikes10++;
                    }
                    else if (strikes == 1){
                        strikes11++;
                    }
                    else if (strikes == 2){
                        strikes12++;
                    }
                }
                else if (balls == 2){
                    if (strikes == 0){
                        strikes20++;
                    }
                    else if (strikes == 1){
                        strikes21++;
                    }
                    else if (strikes == 2){
                        strikes22++;
                    }
                }
                else if (balls == 3){
                    if (strikes == 0){
                        strikes30++;
                    }
                    else if (strikes == 1){
                        strikes31++;
                    }
                    else if (strikes == 2){
                        strikes32++;
                    }
                }
                strikes++;
            }
        }
        else if (c == 'H'){
            result = AtBatResult.Walk;
        }
        else if (c == 'X'){
            result = AtBatResult.Hit;
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
