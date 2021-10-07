import sys
class HumanActivity:
    #How many units of bamboo are consumed a day
    deforestation = 0
    #How much warmer global warming makes the planet each day
    temperatureAddition = 0
    """Creates a HumanActivity instance"""
    def HumanActivity(self, deforestation, climateChange):
        self.deforestation = deforestation
        self.temperatureAddition = climateChange

    def harvestBamboo(self, bamboo):
        if (self.deforestation != 0):
            print("Humans cut down " + self.deforestation + "pounds of bamboo.")
            return bamboo-self.deforestation
        else:
            print("Humans provide the pandas with all the bamboo they need.")
            return bamboo-self.deforestation
    def changeTemp(self, temp):
        if(self.temperatureAddition != 0):
            print("Human actions have increased the temperature by " + self.temperatureAddition)
        else:
            print("The humans keep the panda enclosure at the perfect temperature.")