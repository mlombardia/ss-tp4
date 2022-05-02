package ar.edu.itba.ss.tp4.integrators;

import ar.edu.itba.ss.tp4.integrators.Integrator;
import ar.edu.itba.ss.tp4.models.Particle;

public class Beeman implements Integrator {

  private double calculateForce(double pos, double vel) {
    return -Math.pow(10,4)*pos -100*vel;
  }

  @Override
  public void updateData(double deltaT, Particle particle) {
    double force = calculateForce(particle.xPos, particle.xVel);
    double acceleration = force/particle.weight;
    particle.xPos += (particle.xVel*deltaT + (2.0/3.0)*(acceleration*Math.pow(deltaT,2))-particle.acceleration*Math.pow(deltaT,2)/6);

    double vPredicted = particle.xVel + (3.0/2.0)*(acceleration*Math.pow(deltaT,2)) - particle.acceleration*deltaT/2;
    double nextForce = calculateForce(particle.xPos, particle.xVel);
    double nextAcceleration = nextForce/particle.weight;
    particle.xVel += nextAcceleration*deltaT/3 + (5.0/6.0)*(acceleration*deltaT)- particle.acceleration*deltaT/2;

    particle.acceleration = acceleration;
  }
}