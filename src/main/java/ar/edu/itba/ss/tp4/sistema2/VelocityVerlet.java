package ar.edu.itba.ss.tp4.sistema2;

import ar.edu.itba.ss.tp4.models.Particle;

import static ar.edu.itba.ss.tp4.simulation.RadiationSimulator.*;

public class VelocityVerlet {
    private double getForce() {
        Particle movingParticle = particles.get(0);
        double force = movingParticle.charge * k;
        double sum = 0, distance;
        for (Particle particle : particles) {
            if (!particle.equals(movingParticle)) {
                distance = Math.sqrt(Math.pow(particle.xPos - movingParticle.xPos, 2) + Math.pow(particle.yPos - movingParticle.yPos, 2));
                sum += particle.charge / Math.pow(distance, 2);
            }
        }
        return force * sum;
    }

    public void getNewPosition(double delta, double force) {
        Particle particle = particles.get(0);
//        TODO proyectar fuerzas
        particle.xPos = particle.xPos + delta * particle.xVel + (Math.pow(delta, 2) / particle.weight) * force;
        particle.yPos = particle.yPos + delta * particle.yVel + (Math.pow(delta, 2) / particle.weight) * force;

    }

    public void getNewVelocity(double delta, double prevForce, double newForce) {
        Particle particle = particles.get(0);
//        TODO proyectar fuerzas
        particle.xVel = particle.xVel + (delta / (2 * particle.weight)) * (prevForce + newForce);
        particle.yVel = particle.yVel + (delta / (2 * particle.weight)) * (prevForce + newForce);
    }


    public void updateData(double deltaT) {
        double force = getForce();
        getNewPosition(deltaT, force);
        double newForce = getForce();
        getNewVelocity(deltaT, force, newForce);
    }
}
