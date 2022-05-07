package ar.edu.itba.ss.tp4.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

        Particle aux2 = new Particle(0, initialPosition, 0, 0, Math.pow(10, -27), 1.0, true);
        addParticles(aux2);
    }

    public void addParticles(Particle particle) {
        int cantPart = 16*16+1;
        double color = 0.5;
        boolean electricCharge = false;
        try {

            buffer1.write(cantPart + "\n");
            buffer1.write("XPosition YPosition Radius Color\n");
            buffer1.write(particle.xPos + " " + particle.yPos + " " + Math.pow(10, -9) + " " + particle.color + "\n");
            for(int i=0; i<N; i++){
                color = ((color == 0.5)? 1.0 : 0.5);
                electricCharge = !electricCharge;
                for(int j=0; j<N; j++){
                    Particle aux = new Particle(D+(i*D), 0+(j*D), 0, 0, 1, color, electricCharge);
                    buffer1.write(aux.xPos + " " + aux.yPos + " " + Math.pow(10, -9) + " " + aux.color + "\n");
                    color = ((color == 0.5)? 1.0 : 0.5);
                    electricCharge = !electricCharge;
                }
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
