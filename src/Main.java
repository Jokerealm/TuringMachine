package utm;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

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
        String initialState = "";
        String acceptState = "";
        String rejectState = "";
        String isAnimation;
        String filePath;
        String inputString = "000111";
        boolean flag;
        Properties prop = new Properties();
        TuringMachine myTM;
        String[] rulesGroup;

/*
        // 确保至少有三个参数被传递
        if (args.length < 3) {
            System.out.println("Usage: java -jar practical1-ID.jar <desc_absPath> <input> <--animation or --noanimation>\n");
            return;
        }

        // 命令行args参数读取
        filePath = args[0];
        inputString = args[1];
        isAnimation = args[2];
*/

        filePath = "C:\\Users\\lenovo\\Desktop\\TuringMachine\\classical-2.desc";
        try (FileInputStream input = new FileInputStream(filePath)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initialState = prop.getProperty("initialState");
        acceptState = prop.getProperty("acceptState");
        rejectState = prop.getProperty("rejectState");
        rulesGroup = prop.getProperty("rules").split("<>");
        myTM = new TuringMachine(rulesGroup.length, initialState, acceptState, rejectState);
        String type = "Classical";
//        反射机制 (我想用下面这段代码去直接进行三种图灵机的实例化，但是失败了)
//        try{
//            Class<?> myTM = Class.forName(type);
//            Object myUTM = myTM.getDeclaredConstructor().newInstance();
//            // 获取方法对象
//            Method loadMethod = myTM.getMethod("loadTuringMachine", UniversalTuringMachine.class);
//            // 调用方法
//            loadMethod.invoke(myUTM, myTM);
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//            e.printStackTrace();
//        }

        if(type.equals("Classical")){
            Classical_TuringMachine testUTM = new Classical_TuringMachine();
            testUTM.loadTuringMachine(myTM);
            //设置是否有动画
            isAnimation = "--animation";
            flag = isAnimation(isAnimation);
            if(flag) testUTM.display();
            //Set tape
            testUTM.getTuringMachine().setTape(new Tape(inputString));
            testUTM.loadInput(inputString);
            testUTM.loadRule(rulesGroup);
            testUTM.execute(flag);
        }//if classical
        else if(type.equals("LRTM")){
            LRTM_TuringMachine myUTM = new LRTM_TuringMachine();
            myUTM.loadTuringMachine(myTM);
            //设置是否有动画
            isAnimation = "--animation";
            flag = isAnimation(isAnimation);
            if(flag) myUTM.display();
            //Set tape
            myUTM.getTuringMachine().setTape(new Tape(inputString));
            myUTM.loadInput(inputString);
            myUTM.loadRule(rulesGroup);
            myUTM.execute(flag);
        }//if LRTM
        else if(type.equals("BBTM")){
            BBTM_TuringMachine testUTM = new BBTM_TuringMachine();
            testUTM.loadTuringMachine(myTM);
            testUTM.loadInput("00000000000000000000");
            //Head预先向右移动10位
            for(int i=0;i<10;i++){
                testUTM.getTuringMachine().getHead().moveRight();
            }//for
            //设置是否有动画
            isAnimation = "--animation";
            flag = isAnimation(isAnimation);
            if(flag) testUTM.display();
            //Set tape
            //testUTM.getTuringMachine().setTape(new Tape("00000000000000000000"));
            System.out.println(testUTM.getTuringMachine().getTape());
            testUTM.loadRule(rulesGroup);
            testUTM.execute(flag);
        }//if BBTM


    }//main

}//class
