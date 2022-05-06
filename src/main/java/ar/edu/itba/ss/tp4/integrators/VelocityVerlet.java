package ar.edu.itba.ss.tp4.integrators;

import ar.edu.itba.ss.tp4.models.Particle;

import static ar.edu.itba.ss.tp4.simulation.SimulationController.k;

public class VelocityVerlet implements Integrator {

    public void getNewPosition(double delta, Particle particle, double force) {
        particle.xPos = particle.xPos + delta * particle.xVel + (Math.pow(delta, 2) / particle.weight) * force;
    }

    public void getNewVelocity(double delta, Particle particle, double prevForce, double newForce) {
        particle.xVel = particle.xVel + (delta / (2 * particle.weight)) * (prevForce + newForce);
    }

    @Override
    public void updateData(double deltaT, Particle particle) {
        double force = -k * particle.xPos - 100 * particle.xVel;
        getNewPosition(deltaT, particle, force);
        double newForce = -k * particle.xPos;
        getNewVelocity(deltaT, particle, force, newForce);
    }

}
