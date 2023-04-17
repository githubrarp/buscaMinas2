import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int[][] board = new int[5][5];
    public static ArrayList userMoves = new ArrayList();

    public static void main(String[] args) {

        ArrayList minedCoords = minedCoords();

        createBoard();
        mineBoard(board, minedCoords);

        int x; int y;

        Scanner scanner = new Scanner(System.in);
        String userInputCoords;

        do{
            System.out.println("Please enter two digit coordinate (ie:32) or type EXIT to quit: ");
            while (!scanner.hasNext()){
                scanner.next();
            }
            userInputCoords = scanner.nextLine();
            if (userInputCoords.equalsIgnoreCase("exit")){
                System.exit(0);
            }else {
                try{
                    x = Integer.valueOf(userInputCoords.toString().substring(0,1));
                    y = Integer.valueOf(userInputCoords.toString().substring(1,2));
                    if((x >= 0 && x <= 4) && (y >= 0 && y <= 4 )){
                        play(minedCoords, userInputCoords);
                    }
              }catch (NumberFormatException e){
                    System.out.println("Invalid entry, you must enter digits between 0 and 4 or type EXIT to quit.");
                }
            }
        }while (true);
    }

    /*
    public static int choseLevel(){
        int level = 0;

        System.out.println("Chose a level");
        Scanner scanner = new Scanner(System.in);

        while ( !scanner.hasNextInt()){
            scanner.nextInt();
        }

        level = scanner.nextInt();

        do{
            if(level == 1){
                System.out.println("Hay " + level);
                break;
            }else if (level == 2){
                System.out.println("Hay " + level);
                break;
            }
            System.out.print("You must choose 1 or 2. Please try again: ");
            level = scanner.nextInt();
        }while (level != 1 || level != 2);

        return level;
    }
*/
    public static void play(ArrayList minedCoords, String userInput) {

        for (Object mine: minedCoords
             ) {
            if (mine.equals(userInput)){
                System.out.println("YOU DIED!...");
                System.out.println("GAME OVER! GAME OVER! GAME OVER! ");
                System.exit(0);
            }
        }

        userMoves.add(userInput);
        drawboard(userInput);
    }

    public static void drawboard(String userInput){

        int x; int y;
        x = Integer.valueOf(userInput.toString().substring(0,1));
        y = Integer.valueOf(userInput.toString().substring(1,2));

        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print("| ");
            for (int j = 0; j < 5; j++) {
                if(userMoves.contains(userInput)) {
                    board[x][y] = 1;
                    System.out.print(board[i][j]);
                }else{
                    userMoves.add(userInput);
                    if(i==x && j==y){
                        board[x][y] = -1;
                        System.out.print(board[x][y]);
                    }else{
                        board[i][j] = 0;
                        System.out.print(board[i][j]);
                    }
                }
            }
            System.out.println(" |");
        }
    }

    public static int[][] mineBoard(int[][] board, ArrayList minedCoords){

        int x_mined;
        int y_mined;

        System.out.println(minedCoords);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (Object mine: minedCoords
                ) {
                    x_mined = Integer.valueOf(mine.toString().substring(0,1));
                    y_mined = Integer.valueOf(mine.toString().substring(1,2));
                    if (i == y_mined && j == x_mined) {
                        board[x_mined][y_mined] = 0;
                    }
                }
            }
        }

        return board;
    }
    
    public static int[][] createBoard(){
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print("| ");
            for (int j = 0; j < 5; j++) {
                board[i][j] = 0;
                System.out.print(board[i][j]);
            }
            System.out.println(" |");
        }
        return board;
    }

    public static ArrayList minedCoords() {

        ArrayList coords = new ArrayList();

        while (coords.size()<5){
            String generatedMinedCoord = generateCoords();
            if (!coords.contains(generatedMinedCoord)) {
                coords.add(generatedMinedCoord);
            }
        }
        return coords;
    }

    public static String generateCoords(){
        String x="";
        String y="";
        String coord="";

        for (int i = 0; i < 5; i++) {
            x = String.valueOf(generateRandom());
            for (int j = 0; j < 5; j++) {
                y = String.valueOf(generateRandom());
            }
            coord = x+y;
        }
        return coord;
    }

    public static int generateRandom(){

        Random random = new Random();
        int randomNumber = random.nextInt(0,5);
        return randomNumber;
    }
}