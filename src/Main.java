package utm;

public class Main {

    public  static  boolean isAnimation(String isAnimation){
        if(isAnimation.equals("--animation")){
            return true;
            // System.out.println("Input --animation \n");
        }
        else if(!isAnimation.equals("--noanimation")){
            System.out.println("Input --animation or --noanimation\n");
        }
        return false;
    }//isAnimation


    public static void main(String[] args) {

        String filePath;
        UTMEditor myUTME = new UTMEditor();
        TMReader myReader = new TMReader();
        myUTME.setUTMController(myReader);

//        String inputString = "000111";
//        filePath = "D:\\Advanced Programming\\P1\\Guan\\TuringMachine\\lr-convert.desc";
//        myReader.loadTuringMachineFrom(filePath);
//        myReader.runUTM(inputString);

        // 确保有三个参数被传递
//        if (args.length == 3) {
//            if (args[1].length() > 20) {
//                System.out.println("ERROR: The input is too long.");
//                return;
//            }
//            if (args[2].equals("--animation")) {
//                myReader.setIsAnimation(true);
//            } else if (args[2].equals("--noanimation")) {
//                myReader.setIsAnimation(false);
//            }
//            myReader.runUTM(args[1]);
//        }else {
//            System.out.println("Usage: java -jar practical1-ID.jar <desc_absPath> <input> <--animation or --noanimation>\n");
//            return;
//        }

    }//main
}//class
