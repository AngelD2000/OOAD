package com.design;

import java.util.Arrays;

//Time: 1min:23
public class Environment {
    //Pandas eat ~30 pounds of bamboo a day
    //https://www.worldwildlife.org/stories/what-do-pandas-eat-and-other-giant-panda-facts
    int pandaConsumption = 30;
    //Bamboo reaches full height after 7-18 years. We'll assume 10
    Long[] bamboo_growing = new Long[365*10];
    Long total_growing = 0L;
    int growing_index = 0;
    Long bamboo;
    Long bamboo_capacity = 0L;
    //Current temperature of the environment in degrees celsius
    double temperature = 21;
    //bool if running a captive panda environment
    boolean captive = false;
    Integer pandas = 0;
    HumanActivity humanActivity = null;
    Environment(String humanBehaviour){
        Arrays.fill(bamboo_growing, 0L);
        //Default values
        HumanFactory humanFactory = new HumanFactory();
        humanActivity = humanFactory.createActivity(humanBehaviour);
        if(humanBehaviour != "Captive"){
            //Currently 1864 pandas in wild
            //https://wwf.panda.org/discover/knowledge_hub/endangered_species/giant_panda/panda/how_many_are_left_in_the_wild_population/
            pandas = 1864;
            //China currently has 4 million ha of bamboo
                //https://www.bioversityinternational.org/fileadmin/bioversity/publications/Web_version/572/ch10.htm#:~:text=The%20bamboo%20forest%20area%20in,%2C%20in%20man%2Dmade%20forests.
            //13.5 tonnes of bamboo per acre
                //https://www.agrifarming.in/bamboo-farming-project-report-cost-profit
            bamboo_capacity = 294102900000L;
        }
        else{
            captive = true;
            //Currently 500 pandas in captivity
                //https://www.pandasinternational.org/reserves-zoos/#:~:text=Between%20Panda%20Centers%20in%20China,approximately%20500%20Pandas%20in%20captivity%20.&text=In%20the%201940s%2C%20the%20Chinese,to%20protect%20the%20Giant%20Pandas.
            pandas = 500;
            //Assume captive pandas always get bamboo
            bamboo_capacity = 10000000000L;
        }
        bamboo = bamboo_capacity;
    }

    public void bambooGrow(){
        bambooGrow(false);
    }
    public void bambooGrow(Boolean verbose){
        if(captive){
            bamboo = bamboo_capacity;
            if (verbose){
                Util.print("Humans have restocked all the bamboo the pandas need");
            }
        }
        else{
            //Bamboo will be assumed to immediately start growing to replace deforested bamboo
            Long to_grow = bamboo_capacity-bamboo-total_growing;
            //Util.print(""+to_grow);
            //Util.print("Total growing: "+total_growing);
            bamboo_growing[growing_index] = to_grow;
            total_growing += to_grow;
            //Bamboo has to reach certain age before it can be eaten by pandas or harvested by people
            growing_index+=1;
            if(growing_index >= bamboo_growing.length){
                growing_index = 0;
            }
            if(bamboo_growing[growing_index] > 0){
                bamboo += bamboo_growing[growing_index];
                total_growing -= bamboo_growing[growing_index];
                if (verbose){
                    Util.print("Grew some bamboo!");
                }
            }
            if (verbose) {
                Util.print("After growing, there is " + bamboo + " available out of the max" + bamboo_capacity);
            }
            //Util.print(""+ total_growing);
        }

    }

    public void runDay(){
        runDay(false);
    }
    public boolean runDay(Boolean verbose){
        bamboo -= (int) (humanActivity.harvestBamboo(verbose));
        temperature += humanActivity.changeTemp(verbose);
        //In the full simulation, this would instead iterate over all of the pandas and call the "eat" function for each
        Integer i;
        for (i=0; i < pandas; i++){
            if (bamboo > pandaConsumption){
                bamboo -= pandaConsumption;
            }
            //Not enough food and panda starved
            else{
                pandas-=1;
            }
        }
        bambooGrow(verbose);
        if(pandas <= 0){
            return true;
        }
        else{
            return false;
        }

    }

    public Integer getNumPandas(){
        return pandas;
    }

    public void summary(){
        Util.print("The environment temperature was " + temperature + " and there was " + bamboo + " bamboo available");
    }


}
