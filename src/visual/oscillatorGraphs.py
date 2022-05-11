from cmath import sqrt
from os import times
from turtle import color, position
import numpy as np
import math
from matplotlib import pyplot as plt
from statistics import mean 

# Read Analytic input
file = open("../../OscillatorAnalytical.csv", 'r')
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



## PLOT 
fig, ax = plt.subplots()

ax.plot(timeAnalytic, positionAnalytic, color="#E63B60")
ax.plot(timeGP5, positionGP5, color="#067FD0", dashes=[2, 2, 5, 2]) # 2pt line, 2pt break, 10pt line, 2pt break
ax.plot(timeBeeman, positionBeeman, color="#4ADEDE", dashes=[2, 2])
ax.plot(timeVerlet, positionVerlet, color="#151A7B", dashes=[1, 2, 3, 2])

plt.xlim(0, 5.1547 * 1)
##Escala para ver con zoom
##plt.xlim(3.1544, 3.1547)
##plt.ylim(0.999806, 1.00064)
ax.set_xlabel('Tiempo (s)')
ax.set_ylabel('Posicion (m)')

plt.ticklabel_format(style='sci', axis='x', scilimits=(0,0))
ax.xaxis.major.formatter._useMathText = True
ax.legend(('Analytic','GP5', 'Beeman', 'Verlet'))
plt.show()
plt.close(fig)



## CALCULO LOS ERRORES
'''errorAuxB = []
errorAuxB = np.square(np.subtract(positionAnalytic,positionBeeman)).mean()
errorAuxG = []
errorAuxG = np.square(np.subtract(positionAnalytic,positionGP5)).mean()
errorAuxV = []
errorAuxV = np.square(np.subtract(positionAnalytic,positionVerlet)).mean()

print(errorAuxB)
print(errorAuxG)
print(errorAuxV)
'''

summation = 0  #variable to store the summation of differences
n = len(positionAnalytic) #finding total number of items in list
for i in range (0,n):  #looping through each element of the list
  difference = positionAnalytic[i] - positionBeeman[i]  #finding the difference between observed and predicted value
  squared_difference = difference**2  #taking square of the differene 
  summation = summation + squared_difference  #taking a sum of all the differences
MSE = summation/n  #dividing summation by total values to obtain average
print ("The Mean Square Error for Beeman is: " , MSE)




## ERROR CUADRATICO PARA BEEMAN
file = open("dtsVsEB.txt", 'r')
InputLines = file.readlines()

errors = []
dts = []
count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(' ')
        errors.append(float(str[0]))
        dts.append(float(str[1]))
    count += 1

fig, ax = plt.subplots()

ax.scatter(dts, errors)

ax.set_xlabel('dts ')
ax.set_ylabel('error (m)')

plt.xlim(3.1544 * 0 , 0.0006 * 1)
plt.ylim(0.9826452 * 0, 0.0007 * 1)

plt.show()
plt.close(fig)



## ERROR CUADRATICO PARA GP5
file = open("dtsVsEG.txt", 'r')
InputLines = file.readlines()

errors = []
dts = []
count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(' ')
        errors.append(float(str[0]))
        dts.append(float(str[1]))
    count += 1

fig, ax = plt.subplots()
ax.scatter(dts, errors)

ax.set_xlabel('dts ')
ax.set_ylabel('error (m)')

plt.xlim(3.1544 * 0 , 0.0006 * 1)
plt.ylim(0.9826452 * 0, 0.0007 * 1)

plt.show()
plt.close(fig)


## ERROR CUADRATICO PARA VERLET
file = open("dtsVsEV.txt", 'r')
InputLines = file.readlines()

errors = []
dts = []
count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(' ')
        errors.append(float(str[0]))
        dts.append(float(str[1]))
    count += 1

fig, ax = plt.subplots()

ax.scatter(dts, errors)

ax.set_xlabel('dts ')
ax.set_ylabel('error (m)')

plt.xlim(3.1544 * 0 , 0.0006 * 1)
plt.ylim(0.9826452 * 0, 0.0007 * 1)

plt.show()
plt.close(fig)