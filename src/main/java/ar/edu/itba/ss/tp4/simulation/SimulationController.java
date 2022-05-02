package ar.edu.itba.ss.tp4.simulation;

import ar.edu.itba.ss.tp4.integrators.Integrator;
import ar.edu.itba.ss.tp4.models.Particle;

public class SimulationController {
  private double acceleration;
  private double deltaT;
  private Particle particle;
  private Integrator integrator;

  public SimulationController(double deltaT, Integrator integrator) {
    this.acceleration = 0;
    this.deltaT = deltaT;
    this.particle = new Particle(1,0,0,0,1);
    this.integrator = integrator;
  }

  public void simulate() {
    while(true) {
      this.integrator.updateData(deltaT, particle);
    }
  }
}