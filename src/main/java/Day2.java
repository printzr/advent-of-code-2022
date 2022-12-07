import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day2 {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = Day2.class.getResourceAsStream("/input/day2_input.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            int totalScore = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String opponentPlay = line.substring(0,1);
                System.out.println("opponentPlay = " + opponentPlay);
                String yourPlay = line.substring(2);
                System.out.println("yourPlay = " + yourPlay);

                //X = Rock (1)
                //Y = Paper (2)
                //Z = Scissors (3)

                //A = Rock
                //B = Paper
                //C = Scissors

                //0 loss
                //3 draw
                //6 win


                if("X".equals(yourPlay)) {
                    //Lose
                    if( "A".equals(opponentPlay)){
                        //Play Z
                        totalScore = totalScore + 3;
                    } else if( "B".equals(opponentPlay)){
                        //Play X
                        totalScore = totalScore + 1;
                    }  else {
                        //Play Y
                        totalScore = totalScore + 2;
                    }
                } else if("Y".equals(yourPlay)) {
                    //Draw
                    totalScore = totalScore + 3;
                    if( "A".equals(opponentPlay)){
                        //Play X
                        totalScore = totalScore + 1;
                    } else if( "B".equals(opponentPlay)){
                        //Play Y
                        totalScore = totalScore + 2;
                    }  else {
                        //Play Z
                        totalScore = totalScore + 3;
                    }
                } else {
                    //WIN
                    totalScore = totalScore + 6;
                    if( "A".equals(opponentPlay)){
                        //Play Y
                        totalScore = totalScore + 2;
                    } else if( "B".equals(opponentPlay)){
                        //Play Z
                        totalScore = totalScore + 3;
                    }  else {
                        //Play X
                        totalScore = totalScore + 1;
                    }
                }

            }
            System.out.println("totalScore = " + totalScore);
        }
    }

}
