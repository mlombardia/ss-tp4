# Simulación de Sistemas
Trabajo Práctico Nro. 4: Dinámica Molecular regida por el paso temporal

## Integrantes
* [Paula Andrea Domingues](https://github.com/pdomins), 60148
* [Maximiliano Lombardia](https://github.com/mlombardia), 56276
* [Mariano Leonel Perez Mosquera](https://github.com/marianopm), 56285

## Ejecución
Ubicandose en el directorio ss-tp2, ejecutar
```sh
mvn package
```
Luego, dirigirse a la nueva carpeta creada `target`, en donde se encontrará el archivo `ss-tp4-1.0-SNAPSHOT.jar`.
Una vez alli, ejecutar
```sh
java -jar ss-tp4-1.0-SNAPSHOT.jar
```

Para ver los graficos producidos con la informacion generada, dirigirse a la carpeta `visual`. Dentro de la misma se pueden ejecutar
```sh
python oscillatorGraphs.py
```
para ver la comparacion de los diferentes algoritmos para el inciso del Oscilador Puntual Amortiguado.

Luego, ejecutar
```sh
python radiationGraphs.py
```
para poder observar la evolucion de la energia total del sistema del inciso Interacción de la radiación con la materia, asi como las longitudes promedio de estas simulaciones y la proporcion de particulas que no son absorbidas por el material.

Por ultimo, se puede ejecutar
```sh
python histogram.py
```
para ver la distribución de probabilidades o pdf de las longitudes de las trayectorias de partículas que son absorbidas en el material. 