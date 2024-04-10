package utm;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TMReader implements UTMController {
    ExtendedTuringMachine currentUTM;
    Properties prop = new Properties();
    TuringMachineFactory factory;
    boolean isAnimation;

    @Override
    public void loadTuringMachineFrom(String filePath) {
        if (!filePath.endsWith(".desc")) {
            JOptionPane.showMessageDialog(new JFrame(), "ERROR: Not a desc file.");
            return;
        }
        try (FileInputStream input = new FileInputStream(filePath)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String type = prop.getProperty("variant");
        if (type.equals("CLASSICAL")) {
            factory = new CTMFactory();
        } else if (type.equals("LEFT_RESET")) {
            factory = new LRTMFactory();
        } else if (type.equals("BUSY_BEAVER")) {
            factory = new BBTMFactory();
        } else {
            throw new IllegalArgumentException("Invalid name: " + type);
        }
    }

    @Override
    public void runUTM(String input) {
        currentUTM = factory.produceTuringMachine(prop);
        currentUTM.getTuringMachine().setTape(new Tape(input));
        currentUTM.loadInput(input);
        currentUTM.loadRule(currentUTM.rulesGroup);
        isAnimation = true;
        if (isAnimation) {
            currentUTM.display();
        }
        currentUTM.execute(isAnimation);
    }

    public void setIsAnimation(boolean isAnimation) {
        this.isAnimation = isAnimation;
    }

}
