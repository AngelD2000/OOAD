import sys
class HumanActivity:
    #How many units of bamboo are consumed a day
    deforestation = 0
    #How much warmer global warming makes the planet each day
    temperatureAddition = 0
    """Creates a HumanActivity instance"""
    def __init__(self, deforestation, climateChange):
        print(deforestation)
        self.deforestation = deforestation
        self.temperatureAddition = climateChange

    """Returns how bamboo humans will deforest"""
    def harvestBamboo(self, verbose=False):
        if (self.deforestation != 0):
            if verbose:
                print("Humans cut down " + str(self.deforestation) + " pounds of bamboo.")
            return self.deforestation
        else:
            return self.deforestation
    """Returns how much warmer humans have made planet"""
    def changeTemp(self, verbose=False):
        if verbose:
            if(self.temperatureAddition != 0):
                print("Human actions have increased the temperature by " + str(self.temperatureAddition))
            else:
                print("The humans keep the panda enclosure at the perfect temperature.")
        return self.temperatureAddition