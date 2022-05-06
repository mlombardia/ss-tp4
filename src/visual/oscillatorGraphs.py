from cmath import sqrt
from os import times
from turtle import position
import numpy as np
import math
from matplotlib import pyplot as plt
from statistics import mean 

# Read Analytic input
'''
file = open("../../OscillatorAnalytic.csv", 'r')
InputLines = file.readlines()

positionAnalytic = []
velocityAnalytic = []
timeAnalytic = []

count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(',')
        positionAnalytic.append(float(str[0]))
        str = line.strip().split(',')
        velocityAnalytic.append(float(str[1]))
        str = line.strip().split(',')
        timeAnalytic.append(float(str[2]))
    count += 1
'''

# Read GP5 input
file = open("../../OscillatorGP5.csv", 'r')
InputLines = file.readlines()

positionGP5 = []
velocityGP5 = []
timeGP5 = []

count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(',')
        positionGP5.append(float(str[0]))
        str = line.strip().split(',')
        velocityGP5.append(float(str[1]))
        str = line.strip().split(',')
        timeGP5.append(float(str[2]))
    count += 1

# Read Beeman input

file = open("../../OscillatorBeeman.csv", 'r')
InputLines = file.readlines()

positionBeeman = []
velocityBeeman = []
timeBeeman = []

count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(',')
        positionBeeman.append(float(str[0]))
        str = line.strip().split(',')
        velocityBeeman.append(float(str[1]))
        str = line.strip().split(',')
        timeBeeman.append(float(str[2]))
    count += 1


# Read Verlet input
'''
file = open("../../OscillatorVerlet.csv", 'r')
InputLines = file.readlines()

positionVerlet = []
velocityVerlet = []
timeVerlet = []

count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(',')
        positionVerlet.append(float(str[0]))
        str = line.strip().split(',')
        velocityVerlet.append(float(str[1]))
        str = line.strip().split(',')
        timeVerlet.append(float(str[2]))
    count += 1
'''

fig, ax = plt.subplots()

ax.scatter(timeGP5, positionGP5)
ax.scatter(timeBeeman, positionBeeman)
'''ax.scatter(timeVerlet, positionVerlet)'''

##ax.set_title("Comparacion de integradores en oscilador")
ax.set_xlabel('Tiempo (s)')
ax.set_ylabel('Posicion')

plt.ticklabel_format(style='sci', axis='x', scilimits=(0,0))
ax.xaxis.major.formatter._useMathText = True
plt.show()
plt.close(fig)



## ERROR CUADRATICO
'''errors  = []
dts = ['0.0001', '0.0002', '0.0003', '0.0004', '0.0005', '0.0006', '0.0007', '0.0008', '0.0009', '0.0010']

for n in dts.__len__:
    errors.append(mean_squared_error(positionAnalytic, positionGP5))
    errors.append(np.square(np.subtract(positionAnalytic,positionGP5)).mean())


fig, ax = plt.subplots()

ax.scatter(errors, dts)

##y_error = 20*0.10             ## El 10% de error
#y_error = [stdDev1, stdDev2, stdDev3, stdDev4, stdDev5, stdDev6, stdDev7]



#plt.errorbar(n,averageTime, yerr = y_error, capsize = 3)
##ax.set_title("Fraccion de particulas en los recinto traves del tiempo con N=??")
ax.set_xlabel('Dts (s)')
ax.set_ylabel('Error cuadratico medio')

plt.ticklabel_format(style='sci', axis='x', scilimits=(0,0))
ax.xaxis.major.formatter._useMathText = True
plt.show()
plt.close(fig)
'''