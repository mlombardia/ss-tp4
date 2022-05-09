package ar.edu.itba.ss.tp4.simulation;

import ar.edu.itba.ss.tp4.models.FileRadiationGenerator;
import ar.edu.itba.ss.tp4.models.Particle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadiationSimulator {
    public static double k = Math.pow(10, 10);
    public static double Q = Math.pow(10, -19);
    public double M = Math.pow(10, -27);
    public double D = Math.pow(10, -8);

    public double N = 256;
    public double L = Math.sqrt(N) * D;

    public List<Particle> particles = new ArrayList<>();

    FileRadiationGenerator fileRadiationGenerator = new FileRadiationGenerator();

    public RadiationSimulator() {
        boolean isPositive = false;
        boolean lastRow;
        for (double i = 0; i < L; i += D) {
            lastRow = isPositive;
            for (double j = 0; j < L; j += D) {
                particles.add(new Particle(i, j, 0, 0, M, isPositive ? 0.5 : 1, isPositive));
                isPositive = !isPositive;
            }
            isPositive = !lastRow;

        }
    }

    public void simulate() {
        /*while (cutCondition()){

        }*/
        fileRadiationGenerator.closeFiles();
    }

    public boolean cutCondition() {
        //si particula alcana algun borde o alguna de las particulas
        //return false

        //else
        return true;
    }
}
