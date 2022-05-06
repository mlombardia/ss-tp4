package ar.edu.itba.ss.tp4.simulation;

import ar.edu.itba.ss.tp4.integrators.Integrator;
import ar.edu.itba.ss.tp4.models.Particle;

public class SimulationController {
  private double acceleration;
  private double deltaT;
  private Particle particle;
  private Integrator integrator;

  public static double k = Math.pow(10, 4);

  public SimulationController(double deltaT, Integrator integrator) {
    this.acceleration = 0;
    this.deltaT = deltaT;
    this.particle = new Particle(1,0,0,0,1);
    this.integrator = integrator;
  }

  public void simulate() {
    while(true) {
      System.out.println(this.particle.xVel);
      System.out.println(this.particle.xPos);
      this.integrator.updateData(deltaT, particle);
    }
  }
}