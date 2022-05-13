package ar.edu.itba.ss.tp4.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileEnergyGenerator {
    private FileWriter fw1;
    private final BufferedWriter buffer1;

    public FileEnergyGenerator() {
        try {
            FileWriter pw1 = new FileWriter("EnergyCalculator.csv");
            pw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.fw1 = new FileWriter("EnergyCalculator.csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.buffer1 = new BufferedWriter(fw1);

        try {
            buffer1.write("totalEnergy, cineticEnergy, potentialEnergy, time\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToFile(double initialEnergy, double totalEnergy, double cineticEnergy, double potentialEnergy, double time) {
        try {
            buffer1.write((Math.abs( initialEnergy -totalEnergy)/ initialEnergy) + "," + cineticEnergy + "," + potentialEnergy + "," + time + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFiles() {
        try {
            buffer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
