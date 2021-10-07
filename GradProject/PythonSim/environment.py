#Coding Time: 
from PythonSim.HumanFactory import HumanFactory


class Environment:
    #
    bamboo_capacity = bamboo
    #Amount of bamboo available in pounds
    bamboo = 0
    #Current temperature of the envrinment in degrees celcius
    temperature = 70
    #How the humans change the environment
    humanAction = None
    #The current pandas alive
    pandas =[]
    """Creates and Environment object"""
    def Environment(self, humanBehaviour):
        self.humanAction = HumanFactory.createActivity()
        if(humanBehaviour != "Captive"):
            #Currently 1864 pandas in wild
                #https://wwf.panda.org/discover/knowledge_hub/endangered_species/giant_panda/panda/how_many_are_left_in_the_wild_population/
            pandas = []*1864
        else:
            #Currently 500 pandas in captivity
                #https://www.pandasinternational.org/reserves-zoos/#:~:text=Between%20Panda%20Centers%20in%20China,approximately%20500%20Pandas%20in%20captivity%20.&text=In%20the%201940s%2C%20the%20Chinese,to%20protect%20the%20Giant%20Pandas.
            pandas = []*500
    def bambooGrow(self):
        return self.bamboo
    
    def runDay(self):
        self.bamboo = self.humanAction.harvestBamboo(self.bamboo)
        self.temperature = self.humanAction.changeTemp(self.temperature)
        #Pandas eat ~55 pounds of bamboo a day
            #https://www.worldwildlife.org/stories/what-do-pandas-eat-and-other-giant-panda-facts
        #In the full simulation, this would instead iterate over all of the pandas and call the "eat" function for each
        for i in range(len(self.pandas)):
            if (self.bamboo > 55):
                self.bamboo -= 55
            #Not enough food and panda starved
            else:
                self.pandas.pop()
        self.bambooGrow()
            