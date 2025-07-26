import java.util.Scanner;

public class PlayerScore {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        int[][] playerRuns = new int[numPlayers][];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the number of matches for player " + (i + 1) + ": ");
            int numMatches = scanner.nextInt();
            playerRuns[i] = new int[numMatches];

            System.out.println("Enter the runs scored in each match for player " + (i + 1) + ": ");
            for (int j = 0; j < numMatches; j++) {
                playerRuns[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < numPlayers; i++) {
            int totalRuns = 0;
            for (int run : playerRuns[i]) {
                totalRuns += run;
            }
            double battingAverage = playerRuns[i].length > 0 ? (double) totalRuns / playerRuns[i].length : 0;

            System.out.println("Player " + (i + 1) + ":");
            System.out.println("Runs scored in all matches: " + totalRuns);
            System.out.println("Batting average: " + battingAverage);
        }

        scanner.close();
    }
}
