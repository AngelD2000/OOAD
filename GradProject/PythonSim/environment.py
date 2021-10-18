#Coding Time: 40:00
from HumanFactory import HumanFactory

class Environment:
    #Pandas eat ~30 pounds of bamboo a day
            #https://www.worldwildlife.org/stories/what-do-pandas-eat-and-other-giant-panda-facts
    pandaConsumption = 30
    """Creates and Environment object"""
    def __init__(self, humanBehaviour):
        #Default values
        #Amount of bamboo available in pounds
        self.bamboo_growing = []
        self.plant = []
        #Current temperature of the envrinment in degrees celcius
        self.temperature = 21
        #bool if running a captive panda environment
        self.captive = False
        self.humanFactory = HumanFactory()
        self.humanAction = self.humanFactory.createActivity(humanBehaviour)
        if(humanBehaviour != "Captive"):
            #Currently 1864 pandas in wild
                #https://wwf.panda.org/discover/knowledge_hub/endangered_species/giant_panda/panda/how_many_are_left_in_the_wild_population/
            self.pandas = [0]*1864
            #China currently has 4 million ha of bamboo
                #https://www.bioversityinternational.org/fileadmin/bioversity/publications/Web_version/572/ch10.htm#:~:text=The%20bamboo%20forest%20area%20in,%2C%20in%20man%2Dmade%20forests.
            #13.5 tonnes of bamboo per acre
                #https://www.agrifarming.in/bamboo-farming-project-report-cost-profit
            #
            self.bamboo_capacity = int(4*(10**6)*2.47*13.5*2205)
        else:
            self.captive = True
            #Currently 500 pandas in captivity
                #https://www.pandasinternational.org/reserves-zoos/#:~:text=Between%20Panda%20Centers%20in%20China,approximately%20500%20Pandas%20in%20captivity%20.&text=In%20the%201940s%2C%20the%20Chinese,to%20protect%20the%20Giant%20Pandas.
            self.pandas = [0]*500
            #Assume captive pandas always get bamboo
            self.bamboo_capacity = float('inf')
        self.bamboo = self.bamboo_capacity
    def bambooGrow(self, verbose=False):
        if(self.captive):
            self.bamboo = self.bamboo_capacity
            if verbose:
                print("Humans have restocked all the bamboo the pandas need")
        else:
            #Bamboo will be assumed to immediately start growing to replace deforested bamboo
            to_grow = self.bamboo_capacity-self.bamboo-len(self.bamboo_growing)
            self.bamboo_growing.append([0, to_grow])
            #Bamboo has to reach certain age before it can be eaten by pandas or harvested by people
            for index in range(len(self.bamboo_growing)):
                self.bamboo_growing[index][0] += 1
                #Bamboo reaches full height after 7-18 years. We'll assume 10
                if(self.bamboo_growing[index][0] >= 10*365):
                    self.bamboo += self.bamboo_growing[index][1]
                    self.bamboo_growing.pop(index)
                    break
            if verbose:
                print("After growing, there is " + str(self.bamboo) +" available out of the max" + str(self.bamboo_capacity))
    
    def runDay(self, verbose=False):
        self.bamboo -= int(self.humanAction.harvestBamboo(verbose))
        self.temperature += self.humanAction.changeTemp(verbose)
        #In the full simulation, this would instead iterate over all of the pandas and call the "eat" function for each
        for i in range(len(self.pandas)):
            if (self.bamboo > self.pandaConsumption):
                self.bamboo -= self.pandaConsumption
            #Not enough food and panda starved
            else:
                self.pandas.pop()
        self.bambooGrow(verbose)
        if(len(self.pandas) == 0):
            return False
        else:
            return True
    def getNumPandas(self):
        return len(self.pandas)
    def summary(self):
        print("The environment temperature was " + str(self.temperature) + " and there was " + str(self.bamboo) + " bamboo available")
            