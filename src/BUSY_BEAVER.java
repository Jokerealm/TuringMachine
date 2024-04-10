package utm;

public class BUSY_BEAVER extends ExtendedTuringMachine {
    @Override
    public void execute(boolean flag) {
        loadInput("00000000000000000000");
        //Head预先向右移动10位
        for (int i = 0; i < 10; i++) {
            getTuringMachine().getHead().moveRight();
        }
        super.execute(flag);
    }
}
