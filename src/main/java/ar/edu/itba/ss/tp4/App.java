package ar.edu.itba.ss.tp4;

import ar.edu.itba.ss.tp4.integrators.Beeman;
import ar.edu.itba.ss.tp4.integrators.Integrator;
import ar.edu.itba.ss.tp4.integrators.VelocityVerlet;
import ar.edu.itba.ss.tp4.simulation.SimulationController;

/**
 * Hello world!
 *
 */
public class App {
  public static void main( String[] args ) {
    Integrator beeman = new Beeman();
    Integrator verlet = new VelocityVerlet();
//    SimulationController controller = new SimulationController(0.0001,beeman);
    SimulationController verletController = new SimulationController(0.0001,beeman);
//    controller.simulate();
    verletController.simulate();
  }
}
