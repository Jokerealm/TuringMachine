package utm;

import java.util.Properties;

public class BBTMFactory implements TuringMachineFactory {
    @Override
    public ExtendedTuringMachine produceTuringMachine(Properties prop) {
        BUSY_BEAVER bbtm = new BUSY_BEAVER();
        bbtm.loadTMProp(prop);
        return bbtm;
    }
}
