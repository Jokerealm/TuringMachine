package utm;

import java.util.Properties;

public class LRTMFactory implements TuringMachineFactory {
    @Override
    public ExtendedTuringMachine produceTuringMachine(Properties prop) {
        LEFT_RESET lrtm = new LEFT_RESET();
        lrtm.loadTMProp(prop);
        return lrtm;
    }
}
