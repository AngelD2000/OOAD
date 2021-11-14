package com.design;

public class HumanFactory {
    HumanFactory(){

    }
    public HumanActivity createActivity(String type){
        if(type.equals("Continue")){
            return new HumanActivity(17534246, .00016438356);
        }
        else if(type.equals("Act")){
            return new HumanActivity(8767123, 0.00005479452);
        }
        else{
            return new HumanActivity(0, 0.0);
        }
    }
}
