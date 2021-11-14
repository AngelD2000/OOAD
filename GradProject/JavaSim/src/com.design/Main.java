package com.design;

public class Main {
    public static void main(String[] args) {
        //100 Years
        int numDays = 365*100;
        //Run simulation
        Environment environmentCaptive = new Environment("Captive");
        Environment environmentContinue = new Environment("Continue");
        Environment environmentAct = new Environment("Act");
        Environment[] environments = {environmentCaptive, environmentContinue, environmentAct};
        String[] environmentStrings = {"if humans have captive pandas and provide them with all resources needed:",
                "if humans continue their current trend of deforestation and pollution",
                "if humans cut deforestation in half and reduce pollution"};
        int j;
        for(int i =0; i < environments.length; i++){
            for(j = 0; j < numDays; j++){
                //Util.print("" +j);
                boolean dead = environments[i].runDay(false);
                if(dead) {
                    break;
                }
            }
            Util.print("------------------------------------------------------------------------------------------");
            Util.print("Finished simulation for " + environmentStrings[i]);
            Util.print("The pandas survived for " + j+1 + " days with a remaining population of " + environments[i].getNumPandas());
            environments[i].summary();
        }
    }
}
