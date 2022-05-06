package ar.edu.itba.ss.tp4.integrators;

import ar.edu.itba.ss.tp4.integrators.Integrator;
import ar.edu.itba.ss.tp4.models.Particle;

public class Analytical implements Integrator {

  public double amplitude;
  public double gamma;
  public double k;
  public double t;

  public Analytical(double amplitude, double gamma, double k){
    this.amplitude = amplitude;
    this.gamma = gamma;
    this.k = k;
    this.t = 0;
  }

  @Override
  public void updateData(double deltaT, Particle particle) {
    this.time += deltaT;
    particle.xPos = amplitude * Math.exp(-(this.gamma/(2*particle.weight))*this.time) * Math.cos(Math.sqrt((this.k/particle.weight)-(Math.pow(this.gamma,2)/(Math.pow(particle.weight,2)*4))* this.time))
  }
}