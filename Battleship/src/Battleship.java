
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//Author: Waleed Talib
//Date: 18/12/2022
//Objective: To create the Battleship game.
//ref: MP1 Assignment available in IU8, LMS
//https://lithanlms.sambaash.com/courses/course-v1:Lithan+PFS+PFS-1022A-29Nov2022/courseware/5af6554c75a841e0904348f07458933e/44213d34710b4a8fac874516fb299113/?activate_block_id=block-v1%3ALithan%2BPFS%2BPFS-1022A-29Nov2022%2Btype%40sequential%2Bblock%4044213d34710b4a8fac874516fb299113

public class Battleship {
    static int playerShip = 0;
    static int enemyShip = 0;

    //Game logic.
    //To create Ocean
    //To print Ocean
    //To Display Ocean
    //To deploy player Ships
    //Enemy to deploy Ships
    //Battle it out until one team has 0 Ships.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = 10;
        int cols = 10;

        char[][] ocean = new char [rows][cols];
        System.out.println("* Welcome TO BattleShip Game *");
        System.out.println("Right now Sea is Empty");
        createOcean(ocean);
        printOcean(ocean);
        deployPlayerShip(ocean);
        deployEnemyShip(ocean);
        printOcean(ocean);
        do{
            playerBattle(ocean);
            printOcean(ocean);
            computerBattle(ocean);
            printOcean(ocean);
        }while(playerShip !=0 && enemyShip!=0);

        if(playerShip == 0)
        System.out.println("Game Over You Lose :,(");
        else
            System.out.println("Congratulations! You Win!");
    }
//Get computer coordinates by using random
    private static void computerBattle(char[][]ocean) {
        Random rand = new Random();
        System.out.println("Enemy is attacking");
        int row = rand.nextInt(10);
        int col = rand.nextInt(10);
        char target = ocean[row][col];
//if statement to check if computer hit our ship or not.
        if(ocean[row][col] == '@'){
            System.out.println("BOOM! Enemy Sunk Your Ship");
            ocean[row][col]= 'X';
            playerShip--;

        }else if (target == ' '){
            System.out.println("phew.. Enemy misses");
            ocean[row][col] = 'O';
        }else {
            System.out.println("Already Hit Target");
        }
    }
// Function is use to get player to input coordinates in the system.
    // After coordinate has been given, will check if it is hit or miss.
    private static void playerBattle(char[][]ocean ) {
        System.out.println("Enter X Coordinate to attack:");
        int row = new Scanner(System.in).nextInt();
        if(row < 0 || row > 9){
            System.out.println("Please Enter a Valid coordinate");
            row = new Scanner(System.in).nextInt();
        }
        System.out.println("Enter Y coordinate to attack:");
        int col = new Scanner(System.in).nextInt();
        if(col < 0 || col > 9){
            System.out.println("Please Enter a Valid coordinate");
            row = new Scanner(System.in).nextInt();
        }
        char target = ocean[row][col];
// if statement to check if coordinates given hit an enemy ship or not. if hit. ocean will display char X if not
        // ocean will display O
        if(ocean[row][col] == 'C'){
            System.out.println("BOOM! you Sunk an Enemy Ship");
            ocean[row][col]= 'X';
            enemyShip--;

        }else if (target == ' '){
            System.out.println("Awwww... just inches away from the enemy");
            ocean[row][col] = 'O';
        }else {
            System.out.println("Already Hit Target");
        }
    }
//Function is used to deploy enemy ships using random util.
    private static void deployEnemyShip(char[][] ocean) {
        enemyShip = 0;

        while (enemyShip < 5) {
            System.out.println(enemyShip + 1 + " enemy Ships Deployed");
            Random rand = new Random();
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);

            // Add this two line to check enemy location.
            // Comment it out to play the game
            //System.out.println(x + ", " + y);
            //System.out.println("");

            //If statement below to ensure that random values is within ocean and computer ship is placed
            //on the empty ocean char
            if ((x >= 0 && x <=9) && (y >= 0 && y <=9)) {
                if (ocean[x][y] == ' ') {
                    ocean[x][y] = 'C';
                    enemyShip++;
                }
            }
        }
    }

// Deploying of player Ships. Using a while loop.
    // while player ship is less than 5, function will loop to deploy player ship.
    private static void deployPlayerShip(char[][] ocean) {
        playerShip = 0;

        while(playerShip < 5) {
            System.out.print("Enter X coordinate of ship no." +playerShip);
            int x = new Scanner(System.in).nextInt();
            //This if statement is to ensure that the values applied are within the ocean.
            if( x < 0 || x > 9){
                System.out.println("Enter a Valid Coordinate");
                x = new Scanner(System.in).nextInt();
            }
            System.out.println("Enter Y coordinate of ship no."+ playerShip);
            int y = new Scanner(System.in).nextInt();
            if( y < 0 || y > 9){
                System.out.println("Enter a Valid Coordinate");
                y = new Scanner(System.in).nextInt();
            }
// This if statement is to ensure that player can only place the ships on the blank ocean and not
            //on another ship.
            if(ocean[x][y]==' '){
                ocean[x][y]='@';
                playerShip++;
            }
            else if(ocean[x][y]=='@'||ocean[x][y]=='C') {
                System.out.println("cant place at the same place ");
            }
        }
    }
//This function is used to display the ocean map. I also include the player and enemy count in the function.
    private static void printOcean(char[][] ocean) {
        System.out.println();
        System.out.println(" 0123456789");// top row
        for (int row = 0; row < ocean.length; row++) {

            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++)
                if (ocean[row][col] == 'C') System.out.print(" ");
                else if (ocean[row][col] == 'm') System.out.print(" ");
                else if (ocean[row][col] == '1') System.out.print("@");
                else

                    System.out.print(ocean[row][col]);
            System.out.println("|" + row);
        }
        //End of row
        System.out.println(" 0123456789");
//  Displaying of the player and enemy ships
        System.out.println();
        System.out.println("You currently have " + playerShip + " ships left.");
        System.out.println("Enemy currently have " + enemyShip + " ships left.");
    }

// function is used to create ocean. the ocean is a 2D array.
    private static void createOcean(char[][] ocean) {

        for(char[] row : ocean){
            Arrays.fill(row, ' ');
        }
    }

}