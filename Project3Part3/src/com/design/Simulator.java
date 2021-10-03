package com.design;
import java.util.*;

public class Simulator {
    private Store store;

    /**
     * Run simulation for Util.simDays
     */
    public void runSim() {
        for(int i = 0; i < Util.simDays; i++){
            store.runDay(i + 1);
        }
        store.finalSummary();
        Util.print("END OF SIMULATION");
    }

}
