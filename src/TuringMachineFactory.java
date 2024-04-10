package utm;

import java.util.Properties;

public interface TuringMachineFactory {
    ExtendedTuringMachine produceTuringMachine(Properties prop);
}
