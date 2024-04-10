package utm;

import java.util.Properties;

public abstract class ExtendedTuringMachine extends UniversalTuringMachine {
    String stateCurrent;
    String stateNew;
    HaltState stateHalt;
    char symbolCurrent;
    char symbolNew;
    Move move;
    String[] rulesGroup;


    public ExtendedTuringMachine() {
        super();
    }

    public void loadRule(String[] rulesInput) {
        for (String s : rulesInput) {
            stateCurrent = s.split(",")[0];
            symbolCurrent = s.split(",")[1].charAt(0);
            stateNew = s.split(",")[2];
            symbolNew = s.split(",")[3].charAt(0);
            if (s.split(",")[4].equals("RIGHT")) this.move = MoveClassical.RIGHT;
            else if (s.split(",")[4].equals("LEFT")) this.move = MoveClassical.LEFT;
            else this.move = MoveLRTM.RESET;
            this.getTuringMachine().addRule(stateCurrent, symbolCurrent, stateNew, symbolNew, this.move);
        }
    }

    public void loadTMProp(Properties prop) {
        String initialState = prop.getProperty("initialState");
        String acceptState = prop.getProperty("acceptState");
        String rejectState = prop.getProperty("rejectState");
        rulesGroup = prop.getProperty("rules").split("<>");
        this.loadTuringMachine(new TuringMachine(rulesGroup.length, initialState, acceptState, rejectState));
    }

    public void execute(boolean flag) {
        TuringMachine tm = this.getTuringMachine();
        stateCurrent = tm.getInitialState();
        symbolCurrent = tm.getTape().get(tm.getHead().getCurrentCell());

        while (!stateCurrent.equals(tm.getAcceptState()) && !stateCurrent.equals(tm.getRejectState())) {
            System.out.println(this.getTuringMachine().getHead().getCurrentState());

            symbolCurrent = tm.getTape().get(tm.getHead().getCurrentCell());
            boolean ruleApplied = false;

            for (String[] rule : tm.getRules()) {
                if (rule[0].equals(stateCurrent) && rule[1].charAt(0) == symbolCurrent) {
                    //Write on current cell
                    this.writeOnCurrentCell(rule[3].charAt(0));
                    ruleApplied = this.moveRule(flag, rule);
                    if (ruleApplied) {
                        break;
                    }
                }//if Rule fits
            }//for

            if (!ruleApplied) {
                System.out.println("No applied rule. Halt\n");
                break;
            }//if

        }//while

        if (stateCurrent.equals("qa")) {
            stateHalt = HaltState.ACCEPTED;
            this.updateHeadState(stateCurrent);
            //this.writeOnCurrentCell(symbolCurrent);
            this.displayHaltState(stateHalt);
            this.repaint();
        }//if

        else if (stateCurrent.equals("qr")) {
            stateHalt = HaltState.REJECTED;
            this.updateHeadState(stateCurrent);
            //this.writeOnCurrentCell(symbolCurrent);
            this.displayHaltState(stateHalt);
            this.repaint();
        }
    }

    public boolean moveRule(boolean flag, String[] rule) {
        String direction = rule[4];
        if (direction.equals("LEFT")) {
            this.moveHead(MoveClassical.LEFT, flag);
        } else if (direction.equals("RIGHT")) {
            this.moveHead(MoveClassical.RIGHT, flag);
        } else if (direction.equals("RESET")) {
            getTuringMachine().getHead().reset();
        } else {
            return false;
        }
        updateHeadState(rule[2]);
        stateCurrent = rule[2];
        return true;
    }
}
