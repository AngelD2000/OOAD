package com.design;

public class HumanActivity {
    //How many units of bamboo are consumed a day
    int deforestation = 0;
    //How much warmer global warming makes the planet each day
    double temperatureAddition = 0.0;
    public HumanActivity(int deforestation, double warming){
        this.deforestation = deforestation;
        this.temperatureAddition = warming;
    }

    //Returns how bamboo humans will deforest
    public double harvestBamboo(boolean verbose) {
        if (deforestation != 0) {
            if (verbose) {
                Util.print("Humans cut down " + deforestation + " pounds of bamboo.");
            }
        }
        return deforestation;
    }

   //Returns how much warmer humans have made planet
    public double changeTemp(boolean verbose){
        if (verbose){
            if(temperatureAddition == 0){
                Util.print("The humans keep the panda enclosure at the perfect temperature.");
            }
            else{
                Util.print("Human actions have increased the temperature by " + temperatureAddition);
            }
        }
        return temperatureAddition;
    }
}
