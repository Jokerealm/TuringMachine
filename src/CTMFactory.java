package utm;

import java.util.Properties;

public class CTMFactory implements TuringMachineFactory {
    @Override
    public ExtendedTuringMachine produceTuringMachine(Properties prop) {
        CLASSICAL ctm = new CLASSICAL();
        ctm.loadTMProp(prop);
        return ctm;
    }
}
