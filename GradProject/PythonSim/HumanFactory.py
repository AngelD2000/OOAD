from HumanActivity import HumanActivity

"""Creates a HumanActivity instance
   param humanType: String Captive, Continue, or Act representing the enviornment the humans create for the pandas"""
class HumanFactory():
    def createActivity(self, humanType):
        if humanType == "Captive":
            return HumanActivity(0, 0)
        elif humanType == "Continue":
            #China harvests ~3.2 million tons of bamboo a year
                #https://www.bioversityinternational.org/fileadmin/bioversity/publications/Web_version/572/ch10.htm#:~:text=Under%20the%20present%20management%20and,value%20of%205%20billion%20US%24.
            #At current rate, Earth with warm 4-7 degrees in 100 years
                #https://earthobservatory.nasa.gov/features/GlobalWarming/page3.php
            return HumanActivity(3.2*(10**6)/365*2000, 6/365/100)
        elif humanType == "Act":
            #Humans cut back on deforestation by ~50%
            #Humans pull back from global temperature rise to lower bound value (2 degrees a year)
            return HumanActivity(3.2*(10**6)/365*2000/2, 2/365/100)
