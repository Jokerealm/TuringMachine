package utm;

public class Classical_TuringMachine extends ExtendedTuringMachine {
    @Override
    public void loadRule(String[] rulesInput){
        //读入rule
        for(int i=0; i<rulesInput.length; i++){
            stateCurrent = rulesInput[i].split(",")[0];
            symbolCurrent = rulesInput[i].split(",")[1].charAt(0);
            stateNew = rulesInput[i].split(",")[2];
            symbolNew = rulesInput[i].split(",")[3].charAt(0);
            if(rulesInput[i].split(",")[4].equals("RIGHT")) this.move = MoveClassical.RIGHT;
            else this.move = MoveClassical.LEFT;
            this.getTuringMachine().addRule(stateCurrent, symbolCurrent, stateNew, symbolNew, this.move);
        }
    }
    @Override
    public void execute(boolean flag){
        TuringMachine tm = this.getTuringMachine();
        stateCurrent = tm.getInitialState();
        symbolCurrent=tm.getTape().get(tm.getHead().getCurrentCell());

        while(!stateCurrent.equals(tm.getAcceptState()) && !stateCurrent.equals(tm.getRejectState())){
            System.out.println(this.getTuringMachine().getHead().getCurrentState());

            symbolCurrent=tm.getTape().get(tm.getHead().getCurrentCell());
            boolean ruleApplied = false;

            for(String[] rule:tm.getRules()){
                if(rule[0].equals(stateCurrent) && rule[1].charAt(0)==symbolCurrent){
                    //Write on current cell
                    this.writeOnCurrentCell(rule[3].charAt(0));

                    //Move reset
                    if(rule[4].equals("LEFT")){
                        this.moveHead(MoveClassical.LEFT, flag);
                        //Update head state
                        this.updateHeadState(rule[2]);
                        stateCurrent = rule[2];
                        ruleApplied = true;
                        break;
                    }//if Move Left

                    //Move right
                    else if(rule[4].equals("RIGHT")){
                        this.moveHead(MoveClassical.RIGHT, flag);
                        //Update head state
                        this.updateHeadState(rule[2]);
                        stateCurrent = rule[2];
                        ruleApplied = true;
                        break;
                    }//if Move Right

                }//if Rule fits

            }//for

            if (!ruleApplied){
                System.out.println("No applied rule. Halt\n");
                break;
            }//if

        }//while

        if(stateCurrent.equals("qa")){
            stateHalt = HaltState.ACCEPTED;
            this.updateHeadState(stateCurrent);
            //this.writeOnCurrentCell(symbolCurrent);
            this.displayHaltState(stateHalt);
            this.repaint();
        }//if

        else if(stateCurrent.equals("qr")) {
            stateHalt = HaltState.REJECTED;
            this.updateHeadState(stateCurrent);
            //this.writeOnCurrentCell(symbolCurrent);
            this.displayHaltState(stateHalt);
            this.repaint();
        }//else if
    }
}
