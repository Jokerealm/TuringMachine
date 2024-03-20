import utm.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String initialState;
        String acceptState;
        String rejectState;
        String currentState;
        char currentSymbol;
        String newState;
        char newSymbol;
        Move move;
        HaltState haltState = null;

//        Scanner input = new Scanner(System.in);
//        String path = input.next();
//        String inputString = input.next();
//        boolean flag= input.next().equals("--animation");

        Scanner input;
        String path = null;
        String inputString;
        boolean flag = false;
        UniversalTuringMachine myUTM = new UniversalTuringMachine();
        TuringMachine myTM;

        if (args.length == 3) {
            path = args[0];
            if (args[1].length() > 20) {
                System.out.println("ERROR: The input is too long.");
                return;
            }
            inputString = args[1];
            if (args[2].equals("--animation")) {
                flag = true;
            } else {
                if (!args[2].equals("--noanimation")) {
                    System.out.println("ERROR: The available options are --animation or --noanimation.");
                    return;
                }
                flag = false;
            }
            myUTM.loadInput(inputString);
        } else if (args.length != 0) {
            System.out.println("ERROR: The input is not available.[0]: the absolute path of desc file.\n[1]: the input.\n[2]: animation or not.\nEX: java -jar practical4-37847244.jar D:\\Advance\\practical4-37847244\\src\\simple-tm.desc 10101 --animation");
            return;
        }
        if (flag){
            try {
                String[] inputInfo = new String[5];
//                input = new Scanner(System.in);
//                for (int i = 0; i < 4; i++){
//                    inputInfo[i] = input.nextLine();
//                }

                File file = new File(path);
                try {
                    Scanner fileScanner = new Scanner(file);
                    int infoIndex = 0;
                    while (fileScanner.hasNextLine() && infoIndex < 4) {
                        String line = fileScanner.nextLine();
                        // Skip comments and empty lines
                        if (!line.startsWith("#") && !line.trim().isEmpty()) {
                            inputInfo[infoIndex++] = line.trim();
                        }
                    }
                    fileScanner.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 4; i++){
                    System.out.println(inputInfo[i]);
                }
                initialState = inputInfo[0].split("=")[1];
                acceptState = inputInfo[1].split("=")[1];
                rejectState = inputInfo[2].split("=")[1];
                String[] rule = inputInfo[3].split("=")[1].split("<>");

                myTM = new TuringMachine(inputInfo.length,initialState,acceptState,rejectState);
                myUTM.loadTuringMachine(myTM);
                myUTM.display();
                for (int i = 0; i < rule.length; i++){
                        currentState = rule[i].split(",")[0];
                        currentSymbol = rule[i].split(",")[1].charAt(0);
                        newState = rule[i].split(",")[2];
                        newSymbol = rule[i].split(",")[3].charAt(0);
                        if (rule[i].split(",")[4].equals("RIGHT")) move = MoveClassical.RIGHT;
                        else move = MoveClassical.LEFT;
                        myTM.addRule(currentState, currentSymbol, newState, newSymbol, move);
                        if (newState.equals("qa")) {
                            haltState = HaltState.ACCEPTED;
                            myUTM.moveHead(move, flag);
                        }
                        else if (newState.equals("qr")){
                            haltState = HaltState.REJECTED;
                        }
                        myUTM.writeOnCurrentCell(newSymbol);
                        myUTM.updateHeadState(currentState);
                        myUTM.displayHaltState(haltState);
                        myUTM.repaint();
                    }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else{

        }
    }
}



