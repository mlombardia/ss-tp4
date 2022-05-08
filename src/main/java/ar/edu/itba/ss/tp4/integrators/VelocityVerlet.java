package ar.edu.itba.ss.tp4.integrators;

import ar.edu.itba.ss.tp4.models.Particle;

import static ar.edu.itba.ss.tp4.simulation.SimulationController.k;

public class VelocityVerlet implements Integrator {

    private double getForce(double position, double velocity) {
        return -k * position - 100 * velocity;
    }

    public void getNewPosition(double delta, Particle particle, double force) {
        particle.xPos = particle.xPos + delta * particle.xVel + (Math.pow(delta, 2) / particle.weight) * force;
    }

    public void getNewVelocity(double delta, Particle particle, double prevForce, double newForce) {
        particle.xVel = particle.xVel + (delta / (2 * particle.weight)) * (prevForce + newForce);
    }

    private double getMiddleVel(double deltaT, Particle particle, double force) {
        double delta = deltaT / 2;
        double middlePosition = particle.xPos + delta * particle.xVel + (Math.pow(delta, 2) / particle.weight) * force;
        double newForce = getForce(middlePosition, particle.xVel);
        return particle.xVel + (delta / (2 * particle.weight)) * (force + newForce);
    }

    @Override
    public void updateData(double deltaT, Particle particle) {
        double force = getForce(particle.xPos, particle.xVel);
        double middleVel = getMiddleVel(deltaT, particle, force);
        getNewPosition(deltaT, particle, force);
        double newForce = getForce(particle.xPos, middleVel);
        getNewVelocity(deltaT, particle, force, newForce);
    }

}
