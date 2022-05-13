from cmath import sqrt
from os import times
from turtle import color, position
import numpy as np
import math
from matplotlib import pyplot as plt
from statistics import mean
from numpy import *
import scipy.stats as stats

data1 = loadtxt("data1.txt")
data2 = loadtxt("data2.txt")
data3 = loadtxt("data3.txt")
data4 = loadtxt("data4.txt")
data5 = loadtxt("data5.txt")

bin = 6
n1,x1,_ = plt.hist(data1, bins = bin, histtype=u'step')
bin_centers_1 = 0.5*(x1[1:]+x1[:-1])
n2,x2,_ = plt.hist(data2, bins = bin, histtype=u'step')
bin_centers_2 = 0.2*(x2[1:]+x2[:-1])
n3,x3,_ = plt.hist(data3, bins = bin, histtype=u'step')
bin_centers_3 = 0.3*(x3[1:]+x3[:-1])
n4,x4,_ = plt.hist(data4, bins = bin, histtype=u'step')
bin_centers_4 = 0.4*(x4[1:]+x4[:-1])
n5,x5,_ = plt.hist(data5, bins = bin, histtype=u'step')
bin_centers_5 = 0.5*(x5[1:]+x5[:-1])
plt.show()

plt.plot(bin_centers_1,n1, color="red") ## using bin_centers rather than edges
plt.plot(bin_centers_2,n2, color="orange")
plt.plot(bin_centers_3,n3, color="pink")
plt.plot(bin_centers_4,n4, color="green")
plt.plot(bin_centers_5,n5, color="blue")

plt.ylabel('Cantidad de particulas')
plt.xlabel('Distancia Recorrida (m)')
plt.grid()

plt.legend(('5*10³','7.5*10³', '10*10³', '2.5*10⁴', '5*10⁴'))

plt.show()
