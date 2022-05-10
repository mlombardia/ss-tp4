package ar.edu.itba.ss.tp4.simulation;

import ar.edu.itba.ss.tp4.models.FileRadiationGenerator;
import ar.edu.itba.ss.tp4.models.Particle;
import ar.edu.itba.ss.tp4.sistema2.VelocityVerlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadiationSimulator {
    public static double k = Math.pow(10, 10);
    public static double Q = Math.pow(10, -19);
    public double M = Math.pow(10, -27);
    public double D = Math.pow(10, -8);

    private final double DCut = D * 0.01;

    public double N = 256;
    public double L = Math.sqrt(N) * D;

    public static List<Particle> particles = new ArrayList<>();

    public double deltaT;

    public VelocityVerlet verlet;

    FileRadiationGenerator fileRadiationGenerator = new FileRadiationGenerator();

    public RadiationSimulator(double deltaT) {
        generateParticles();
        this.deltaT = deltaT;
        verlet = new VelocityVerlet();
    }

    private void generateParticles() {
        double max = L / 2 + D, min = L / 2 - D;
        double initialPos = Math.random() * (max - min) + min;
        boolean isPositive = false;
        boolean lastRow;
        int id = 0;
        particles.add(new Particle(id++, 0, initialPos, 0, 0, M, 0, true));
        for (double i = 0; i < L; i += D) {
            lastRow = isPositive;
            for (double j = 0; j < L; j += D) {
                particles.add(new Particle(id++, i, j, 0, 0, M, isPositive ? 0.5 : 1, isPositive));
                isPositive = !isPositive;
            }
            isPositive = !lastRow;
        }
    }

    public void simulate() {
        while (cutCondition()) {
            verlet.updateData(deltaT);
        }
        fileRadiationGenerator.closeFiles();
    }

    public boolean cutCondition() {
        Particle particle = particles.get(0);
        double distance;
        if (particle.xPos == 0 || particle.xPos == L || particle.yPos == 0 || particle.yPos == L)
            return true;
        else {
            for (Particle p : particles) {
                distance = Math.sqrt(Math.pow(particle.xPos - p.xPos, 2) + Math.pow(particle.yPos - p.yPos, 2));
                if (distance < DCut) {
                    return true;
                }
            }

        }
        return false;
    }
}
