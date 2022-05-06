package ar.edu.itba.ss.tp4.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOscillatorGenerator {
    private FileWriter fw1;
    private final BufferedWriter buffer1;

    public FileOscillatorGenerator(String name) {
        try {
            FileWriter pw1 = new FileWriter(name + ".csv");
            pw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.fw1 = new FileWriter(name + ".csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.buffer1 = new BufferedWriter(fw1);

        try {
            buffer1.write("position, velocity, time\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToFile(double position, double velocity, double time) {
        try {
            buffer1.write(position + "," + velocity + "," + time + "\n");
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