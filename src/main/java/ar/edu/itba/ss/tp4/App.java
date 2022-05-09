package ar.edu.itba.ss.tp4;

import ar.edu.itba.ss.tp4.sistema1.Beeman;
import ar.edu.itba.ss.tp4.sistema1.GP5;

import ar.edu.itba.ss.tp4.sistema1.Integrator;
import ar.edu.itba.ss.tp4.models.FileOscillatorGenerator;
import ar.edu.itba.ss.tp4.sistema1.VelocityVerlet;
import ar.edu.itba.ss.tp4.sistema1.Analytical;
import ar.edu.itba.ss.tp4.simulation.RadiationSimulator;
import ar.edu.itba.ss.tp4.simulation.SimulationController;

/**
 * Hello world!
 *
 */
public class App {

  public static void main( String[] args ) {
    double deltaT = 0.0001;
    double time = 0;
    //beeman
    FileOscillatorGenerator fileGeneratorBeeman = new FileOscillatorGenerator("OscillatorBeeman");
    Integrator beeman = new Beeman();
    SimulationController controller = new SimulationController(deltaT,beeman, fileGeneratorBeeman);


    //analytical
    FileOscillatorGenerator fileGeneratorAnalytical = new FileOscillatorGenerator("OscillatorAnalytical");
    Integrator analytical = new Analytical(1, 100, Math.pow(10,4));
    SimulationController controller4 = new SimulationController(deltaT,analytical, fileGeneratorAnalytical);


    //gp5
    FileOscillatorGenerator fileGeneratorGP5 = new FileOscillatorGenerator("OscillatorGP5");
    Integrator gp5 = new GP5();
    SimulationController controller2 = new SimulationController(deltaT, gp5, fileGeneratorGP5);


    //verlet
    FileOscillatorGenerator fileGeneratorVerlet = new FileOscillatorGenerator("OscillatorVerlet");
    Integrator verlet = new VelocityVerlet();
    SimulationController controller3 = new SimulationController(deltaT, verlet, fileGeneratorVerlet);

    double timeAux = System.currentTimeMillis()/1000.0;
    while(time < 10000) {
      controller.simulate(time);
      controller4.simulate(time);
      controller2.simulate(time);
      controller3.simulate(time);
      time += ((System.currentTimeMillis()/1000.0 - timeAux));
    }
    controller.closeFiles();
    controller2.closeFiles();
    controller3.closeFiles();
    controller4.closeFiles();


    //radiation particles
    RadiationSimulator radiationSimulator = new RadiationSimulator();
    radiationSimulator.simulate();
  }
}
