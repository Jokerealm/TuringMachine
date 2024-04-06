package utm;

public abstract class ExtendedTuringMachine extends UniversalTuringMachine{
     String stateCurrent;
     String stateNew;
     HaltState stateHalt;
     char symbolCurrent;
     char symbolNew;
     Move move;
    public ExtendedTuringMachine(){ super(); }
    public abstract void loadRule(String[] rulesInput);

    public void execute(boolean flag){

    }
}
