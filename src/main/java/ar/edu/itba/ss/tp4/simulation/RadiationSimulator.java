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
    public static double Delta2 = 10;
    private final double DCut = D * 0.01;

    public static double N = Math.pow(16, 2);
    public static double L = Math.sqrt(N) * D - D;
    public static double rightVertical = Math.sqrt(N) * D;

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
        boolean isPositive = true;
        boolean lastRow;
        int id = 0;
        particles.add(new Particle(id++, 0, initialPos, 5 * Math.pow(10, 4), 0, M, 0.5, true));
        for (int i = 0; i < Math.sqrt(N); i++) {
            lastRow = isPositive;
            for (int j = 0; j < Math.sqrt(N); j++) {
                particles.add(new Particle(id++, D + (i * D), 0 + (j * D), 0, 0, M, isPositive ? 0.5 : 1, isPositive));
                isPositive = !isPositive;
            }
            isPositive = !lastRow;
        }
        fileRadiationGenerator.addParticles(particles);
    }

    public void simulate() {
        double energiaCinetica = 0;
        double energiaPotencial = 0;
        boolean firstStep = true;
        double time = 0;
        double time0 = System.currentTimeMillis();
        while (!cutCondition(firstStep)) {
            verlet.updateData(deltaT);

            firstStep = false;
            if(time > Delta2){
                fileRadiationGenerator.addParticles(particles);
                System.out.printf("%.12f %.12f\n", particles.get(0).xPos, particles.get(0).yPos);
                time = 0;
            }
            time += ((System.currentTimeMillis() -time0)/1000.0);
            energiaCinetica += calculatesCineticEnergy();
            energiaPotencial += calculatesPotentialEnergy();
        }
        fileRadiationGenerator.closeFiles();
        System.out.println("Energia Total = " + (energiaCinetica + energiaPotencial));
        System.out.println("Energia Cinetica = " + energiaCinetica);
        System.out.println("Energia Potencial = " + energiaPotencial);
    }

    public boolean cutCondition(boolean firstStep) {
        Particle particle = particles.get(0);
        double distance;
        if ((!firstStep && particle.xPos <= 0) || particle.xPos >= rightVertical || particle.yPos <= 0 || particle.yPos >= L) {
            System.out.println("a");
            return true;
        }

        for (Particle p : particles) {
            distance = Math.sqrt(Math.pow(particle.xPos - p.xPos, 2) + Math.pow(particle.yPos - p.yPos, 2));
                if (!p.equals(particle) && distance < DCut) {
                    System.out.println('b');
                    return true;
                }
            }


        return false;
    }

    public double calculatesCineticEnergy(){
        double velocity = Math.sqrt(Math.pow(particles.get(0).xVel,2) + Math.pow(particles.get(0).yVel,2));
        return (0.5*particles.get(0).weight*Math.pow(velocity,2));
    }

    public double calculatesPotentialEnergy(){
        Particle movingParticle = particles.get(0);
        double energy = movingParticle.charge * k;
        double sumX = 0, sumY = 0, rx, ry, r, q;
        for (Particle particle : particles) {
            if (!particle.equals(movingParticle)) {
                rx = Math.abs(particle.xPos - movingParticle.xPos);
                ry = Math.abs(particle.yPos - movingParticle.yPos);
                r = Math.sqrt(Math.pow(rx, 2) + Math.pow(ry, 2));
                q = particle.charge / r;
                sumX += q;
                sumY += q;
            }
        }
        return (energy*sumX + energy*sumY);
    }
}
