public class SimulationController {
  private double acceleration;
  private double deltaT;
  private Particle particle;

  public SimulationController(double deltaT, Integrator integrator) {
    this.acceleration = 0;
    this.deltaT = deltaT;
    this.particle = new Particle(1,0,0,0,1);
  }

  public void simulate() {
    while(true) {
      integrator.updateData(deltaT, particle);
    }
  }
}