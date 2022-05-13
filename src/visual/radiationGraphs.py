from cmath import sqrt
from functools import total_ordering
from os import times
import numpy as np
import math
from matplotlib import pyplot as plt
from statistics import mean 
import matplotlib.ticker as mticker


## ENERGIA VS DTS
'''
5 posiciones iniciales fijas 
y0 = L / 2 - D   	
y1 = L / 2 - D/2
y2 = L / 2 		
y3 = L / 2 + D/2	
y4 = L / 2 + D  

velocidad = 5*10^4

'''
file = open("dtsVsEnergy.txt", 'r')
InputLines = file.readlines()

energies0 = []
energies1 = []
energies2 = []
energies3 = []
energies4 = []
dts = []

for line in InputLines:
    str = line.strip().split(' ')
    energies0.append(float(str[0]))
    energies1.append(float(str[1]))
    energies2.append(float(str[2]))
    energies3.append(float(str[3]))
    energies4.append(float(str[4]))
    dts.append(float(str[5]))

aux = [energies0[0],  energies1[0] , energies2[0] , energies3[0], energies4[0]]
aux2 = [energies0[1],  energies1[1] , energies2[1] , energies3[1], energies4[1]]
aux3 = [energies0[2] , energies1[2] , energies2[2] , energies3[2], energies4[2]]
aux4 = [energies0[3] , energies1[3] , energies2[3] , energies3[3], energies4[3]]
aux5 = [energies0[4] , energies1[4] , energies2[4] , energies3[4], energies4[4]]

averageEnergy = [np.average(aux), np.average(aux2), np.average(aux3), np.average(aux4), np.average(aux5)]

fig, ax = plt.subplots()
ax = plt.gca()
ax.scatter(dts, averageEnergy)
plt.xlim( [-0.5e-10, 1.1e-9] )
##plt.ticks(np.arange(dts.min(), dts.max(), 0.005))

plt.ylim(1.24e-19, 0.6e-17)

print ("dts: " , dts)
print("average energy: ", averageEnergy)

ax.set_xlabel('dts (m)')
ax.set_ylabel('Energia (C)')

plt.show()
plt.close(fig)

# ERROR SEGUNDA FORMA 
file = open("../../EnergyCalculator.csv", 'r')
InputLines = file.readlines()

times0 = []
total_energy = 0
cinetic_energy = []
potential_energy = []

count = 0

for line in InputLines:
    if count >= 1:
        str = line.strip().split(',')
        total_energy += float(str[0])
        str = line.strip().split(',')
        cinetic_energy.append(float(str[1]))
        str = line.strip().split(',')
        potential_energy.append(float(str[2]))
        str = line.strip().split(',')
        times0.append(float(str[3]))
    count += 1

diferencial_promedio = total_energy/(len(times0))

print("EL VALOR DEL DIFERENCIAL", diferencial_promedio)



## V0 VS trayectoria en
'''
luego grafico de E vs Dt (deberia dar que a mayor dt mayor energia o a fluctuar. Lo que hay que darse 
cuenta es que la energia no puede depender de deltaT. Habria que encontrar en que valores de deltaT 
(mas chicos) no varia la energia, entonces me quedo con alguno de esos deltaT (el mayor de
 ellos ya que lleva menos tiempo de computo))
5 velocidades iniciales fijas 
v0 = 5*10^3   	
v1 = 7.5*10^3
v2 = 10*10^3
v3 = 2.5*10^4	
v4 = 5*10^4 

dt = 10 ^ -15 ??????

'''
file = open("v0VsTrajectory.txt", 'r')
InputLines = file.readlines()

trajectory0 = []
trajectory1 = []
trajectory2 = []
trajectory3 = []
trajectory4 = []
trajectory5 = []
trajectory6 = []
trajectory7 = []
v0 = []

for line in InputLines:
    str = line.strip().split(' ')
    trajectory0.append(float(str[0]))
    trajectory1.append(float(str[1]))
    trajectory2.append(float(str[2]))
    trajectory3.append(float(str[3]))
    trajectory4.append(float(str[4]))
    trajectory5.append(float(str[5]))
    trajectory6.append(float(str[6]))
    trajectory7.append(float(str[7]))
    v0.append(float(str[8]))

aux = [trajectory0[0],  trajectory1[0] , trajectory2[0] , trajectory3[0], trajectory4[0], trajectory5[0] , trajectory6[0], trajectory7[0]]
stdDev1 = np.std(aux)
aux2 = [trajectory0[1],  trajectory1[1] , trajectory2[1] , trajectory3[1], trajectory4[1], trajectory5[1] , trajectory6[1], trajectory7[1] ]
stdDev2 = np.std(aux2)
aux3 = [trajectory0[2] , trajectory1[2] , trajectory2[2] , trajectory3[2], trajectory4[2], trajectory5[2] , trajectory6[2], trajectory7[2]]
stdDev3 = np.std(aux3)
aux4 = [trajectory0[3] , trajectory1[3] , trajectory2[3] , trajectory3[3], trajectory4[3], trajectory5[3] , trajectory6[3], trajectory7[3]]
stdDev4 = np.std(aux4)
aux5 = [trajectory0[4] , trajectory1[4] , trajectory2[4] , trajectory3[4], trajectory4[4], trajectory5[4] , trajectory6[4], trajectory7[4]]
stdDev5 = np.std(aux5)

averageTrajectory = [np.average(aux), np.average(aux2), np.average(aux3), np.average(aux4), np.average(aux5)]

fig, ax = plt.subplots()

plt.xlim(4000, 52000)
plt.ylim(1.0938081010056657e-09, 4.5026767139714527e-07)

ax.scatter(v0, averageTrajectory)

print()
print ("v0: " , v0)
print("average trajectory: ", averageTrajectory)

##y_error = 20*0.10             ## El 10% de error
y_error = [stdDev1, stdDev2, stdDev3, stdDev4, stdDev5]

plt.errorbar(v0,averageTrajectory, yerr = y_error, capsize = 3)

ax.set_xlabel('Velocidad inicial (m/s)')
ax.set_ylabel('Trayectoria (m)')

plt.ticklabel_format(style='sci', axis='y', scilimits=(0,0))

ax.yaxis.major.formatter._useMathText = True
plt.ticklabel_format(style='sci', axis='x', scilimits=(0,0))
ax.xaxis.major.formatter._useMathText = True

plt.show()
plt.close(fig)




## V0 VS trayectoria que escapan o que son absorvidas

file = open("fpVsV0.txt", 'r')
InputLines = file.readlines()

fp = []
v0 = []

count = 0

for line in InputLines:
    str = line.strip().split(' ')
    fp.append(float(str[0]))
    v0.append(float(str[1]))

fig, ax = plt.subplots()

ax.scatter(v0, fp)

print()
print ("Velocidad: " , v0)
print("Fraccion particulas: ", fp)

ax.set_xlabel('Velocidad inicial (m/s)')
ax.set_ylabel('Fraccion de particulas que escapan')

plt.ticklabel_format(style='sci', axis='x', scilimits=(0,0))
ax.xaxis.major.formatter._useMathText = True

plt.show()
plt.close(fig)
