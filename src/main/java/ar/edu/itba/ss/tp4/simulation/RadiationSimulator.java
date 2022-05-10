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
    public static double D = Math.pow(10, -8);

    private final double DCut = D * 0.01;

    public static double N = Math.pow(16, 2);
    public static double L = Math.sqrt(N) * D;

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
        particles.add(new Particle(id++, 0, initialPos, 5 * Math.pow(10, 3), 5 * Math.pow(10, 4), M, 0, true));
        for (int i = 0; i < N; i++) {
            lastRow = isPositive;
            for (int j = 0; j < N; j++) {
                particles.add(new Particle(id++, D + (i * D), 0 + (j * D), 0, 0, M, isPositive ? 0.5 : 1, isPositive));
                isPositive = !isPositive;
            }
            isPositive = !lastRow;
        }
    }

    public void simulate() {
        boolean firstStep = true;
        while (!cutCondition(firstStep)) {
            verlet.updateData(deltaT);
            System.out.printf("%f %f\n", particles.get(0).xPos, particles.get(0).yPos);
            firstStep = false;
        }
        fileRadiationGenerator.closeFiles();
    }

    public boolean cutCondition(boolean firstStep) {
        Particle particle = particles.get(0);
        double distance;
        if ((!firstStep && particle.xPos <= 0) || particle.xPos >= L || particle.yPos <= 0 || particle.yPos >= L) {
            System.out.println("a");
            return true;
        } else {
            for (Particle p : particles) {
                distance = Math.sqrt(Math.pow(particle.xPos - p.xPos, 2) + Math.pow(particle.yPos - p.yPos, 2));
                if (!p.equals(particle) && distance < DCut) {
                    System.out.println('b');
                    return true;
                }
            }

        }
        return false;
    }
}
