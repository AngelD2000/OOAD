import time
start_time = time.time()
from HumanFactory import HumanFactory
from Environment import Environment

#100 Years
numDays = 365*100
#Run simulation
environmentCaptive = Environment("Captive")
environmentContinue = Environment("Continue")
environmentAct = Environment("Act")
environments = {environmentCaptive: "if humans have captive pandas and provide them with all resources needed:",
                environmentContinue: "if humans continue their current trend of deforestation and pollution", 
                environmentAct: "if humans cut deforestation in half and reduce pollution"}
for environment in environments:
    for i in range(numDays):
        alive = environment.runDay(verbose=False)
        if not alive:
            break
    print("------------------------------------------------------------------------------------------")
    print("Finished simulation for " + environments[environment])
    print("The pandas survived for " + str(i+1) + " days with a remaining population of " + str(environment.getNumPandas()))
    environment.summary()
print("------------------------------------------------------------------------------------------")
print("Panda simulation took", time.time() - start_time, "seconds to run")