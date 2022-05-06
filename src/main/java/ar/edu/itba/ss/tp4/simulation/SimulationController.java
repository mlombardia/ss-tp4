package ar.edu.itba.ss.tp4.simulation;

import ar.edu.itba.ss.tp4.integrators.GP5;
import ar.edu.itba.ss.tp4.integrators.Integrator;
import ar.edu.itba.ss.tp4.models.FileOscillatorGenerator;
import ar.edu.itba.ss.tp4.models.Particle;

public class SimulationController {
  private double acceleration;
  private double deltaT;
  private Particle particle;
  private Integrator integrator;
  private FileOscillatorGenerator fileGenerator;
  private double time = 0;

  public SimulationController(double deltaT, Integrator integrator, FileOscillatorGenerator fileGenerator) {
    this.acceleration = 0;
    this.deltaT = deltaT;
    this.particle = new Particle(1,0,0,0,70, 0);
    this.integrator = integrator;
    this.fileGenerator = fileGenerator;
    if(integrator instanceof GP5){
      ((GP5) integrator).calculatePrevious(particle);
    }
  }

  public void simulate() {
    fileGenerator.addToFile(this.particle.xPos, this.particle.xVel, time);
    double timeAux = System.currentTimeMillis() / 1000.0;
    while(time < 10000) {
      System.out.println("velocidad: " + this.particle.xVel);
      System.out.println("posicion: " + this.particle.xPos);
      this.integrator.updateData(deltaT, particle);
      time += ((System.currentTimeMillis()/1000.0 - timeAux));
      fileGenerator.addToFile(this.particle.xPos, this.particle.xVel, time);
    }
    fileGenerator.closeFiles();
  }
}