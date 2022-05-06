package ar.edu.itba.ss.tp4;

import ar.edu.itba.ss.tp4.integrators.Beeman;
import ar.edu.itba.ss.tp4.integrators.GP5;
import ar.edu.itba.ss.tp4.integrators.Integrator;
import ar.edu.itba.ss.tp4.models.FileOscillatorGenerator;
import ar.edu.itba.ss.tp4.simulation.SimulationController;

/**
 * Hello world!
 *
 */
public class App {
  public static void main( String[] args ) {
    //beeman
    FileOscillatorGenerator fileGeneratorBeeman = new FileOscillatorGenerator("OscillatorBeeman");
    Integrator beeman = new Beeman();
    SimulationController controller = new SimulationController(0.0001,beeman, fileGeneratorBeeman);
    controller.simulate();

    //gp5
    FileOscillatorGenerator fileGeneratorGP5 = new FileOscillatorGenerator("OscillatorGP5");
    Integrator gp5 = new GP5();
    SimulationController controller2 = new SimulationController(0.0001, gp5, fileGeneratorGP5);
    controller2.simulate();

    //verlet
    //FileOscillatorGenerator fileGeneratorVerlet = new FileOscillatorGenerator("OscillatorVerlet");
    //Integrator verlet = new Verlet();
    //SimulationController controller3 = new SimulationController(0.0001, verlet, fileGeneratorVerlet);
    //controller3.simulate();

  }
}
