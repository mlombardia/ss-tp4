package ar.edu.itba.ss.tp4.sistema2;

import ar.edu.itba.ss.tp4.models.Particle;

import static ar.edu.itba.ss.tp4.simulation.RadiationSimulator.*;

public class VelocityVerlet {
    private double[] getForce() {
        Particle movingParticle = particles.get(0);
        double force = movingParticle.charge * k;
        double sumX = 0, sumY = 0, rx, ry, r;
        for (Particle particle : particles) {
            if (!particle.equals(movingParticle)) {
                rx = Math.abs(particle.xPos - movingParticle.xPos);
                ry = Math.abs(particle.yPos - movingParticle.yPos);
                r = Math.sqrt(Math.pow(rx, 2) + Math.pow(ry, 2));
                sumX += (particle.charge / Math.pow(r, 2)) * rx / r;
                sumY += (particle.charge / Math.pow(r, 2)) * ry / r;
            }
        }
        return new double[]{force * sumX, force * sumY};
    }

    public void getNewPosition(double delta, double[] force) {
        Particle particle = particles.get(0);
        particle.xPos = particle.xPos + delta * particle.xVel + (Math.pow(delta, 2) / particle.weight) * force[0];
        particle.yPos = particle.yPos + delta * particle.yVel + (Math.pow(delta, 2) / particle.weight) * force[1];

    }

    public void getNewVelocity(double delta, double[] prevForce, double[] newForce) {
        Particle particle = particles.get(0);
        particle.xVel = particle.xVel + (delta / (2 * particle.weight)) * (prevForce[0] + newForce[0]);
        particle.yVel = particle.yVel + (delta / (2 * particle.weight)) * (prevForce[1] + newForce[1]);
    }


    public void updateData(double deltaT) {
        double[] forces = getForce();
        getNewPosition(deltaT, forces);
        double[] newForce = getForce();
        getNewVelocity(deltaT, forces, newForce);
    }
}
