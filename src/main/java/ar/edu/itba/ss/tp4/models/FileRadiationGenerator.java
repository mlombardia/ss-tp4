package ar.edu.itba.ss.tp4.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileRadiationGenerator {
    private FileWriter fw1;
    private final BufferedWriter buffer1;
    private int N = 16;
    private double D = Math.pow(10, -8);

    public FileRadiationGenerator() {
        try {
            FileWriter pw1 = new FileWriter( "RadiationPosition.xyz");
            pw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.fw1 = new FileWriter("RadiationPosition.xyz", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.buffer1 = new BufferedWriter(fw1);

        double initialPosition =  (6*D+(D/2)) + (Math.random() * (2*D));

    }

    public void addParticles(List<Particle> particles) {
        int cantPart = 16*16+1;
        try {
            buffer1.write(particles.size() + "\n");
            buffer1.write("XPosition YPosition Radius Color\n");
            for(int i=0; i< particles.size(); i++){
                buffer1.write(particles.get(i).xPos + " " + particles.get(i).yPos + " " + Math.pow(10, -9) + " " + particles.get(i).color + " " + particles.get(i).positiveCharge + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addToFile(double position, double time) {
        try {
            buffer1.write(position + "," + time + "\n");
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
