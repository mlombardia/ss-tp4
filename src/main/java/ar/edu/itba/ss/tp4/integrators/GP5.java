package ar.edu.itba.ss.tp4.integrators;

import ar.edu.itba.ss.tp4.integrators.Integrator;
import ar.edu.itba.ss.tp4.models.Particle;
import ar.edu.itba.ss.tp4.models.Vector;

public class GP5 implements Integrator {
  Vector[] previous = new Vector[6];
  double k = Math.pow(10,4);
  double viscocity = 100;

  public void calculatePrevious(Particle particle){
    previous[0] = new Vector(particle.xPos, particle.yPos);;
    previous[1] = new Vector(particle.xVel, particle.yVel);
    previous[2] = previous[0].multiply(-k/particle.weight);
    previous[3] = previous[1].multiply(-k/particle.weight);
    previous[4] = previous[2].multiply(-k/particle.weight);
    previous[5] = previous[3].multiply(-k/particle.weight);

    //previous[2] = previous[0].multiply(new Vector((k*previous[0].x + viscocity*previous[1].x)/particle.weight, 0));
    //previous[3] = previous[1].multiply(new Vector((k*previous[0].x + viscocity*previous[1].x)/particle.weight, 0));
    //previous[4] = previous[0].multiply(new Vector((-k*previous[0].x - viscocity*previous[1].x)/particle.weight, 0).multiply(new Vector((-k*previous[0].x - viscocity*previous[1].x)/particle.weight, 0)));
    //previous[5] = previous[1].multiply(new Vector((-k*previous[0].x - viscocity*previous[1].x)/particle.weight, 0).multiply(new Vector((-k*previous[0].x - viscocity*previous[1].x)/particle.weight, 0)));

  }

  @Override
  public void updateData(double deltaT, Particle particle) {
    Vector [] actual = new Vector[6];

    double secondCoef = Math.pow(deltaT, 2) / 2;
    double thirdCoef = Math.pow(deltaT, 3) / 6;
    double fourthCoef = Math.pow(deltaT, 4) / 24;
    double fifthCoef = Math.pow(deltaT, 5) / 120;

    //prediccion
    actual[0] = previous[0].add(previous[1].multiply(deltaT)).add(previous[2].multiply(secondCoef)).add(previous[3].multiply(thirdCoef)).add(previous[4].multiply(fourthCoef)).add(previous[5].multiply(fifthCoef));
    actual[1] = previous[1].add(previous[2].multiply(deltaT)).add(previous[3].multiply(secondCoef)).add(previous[4].multiply(thirdCoef)).add(previous[5].multiply(fourthCoef));
    actual[2] = previous[2].add(previous[3].multiply(deltaT)).add(previous[4].multiply(secondCoef)).add(previous[5].multiply(thirdCoef));
    actual[3] = previous[3].add(previous[4].multiply(deltaT)).add(previous[5].multiply(secondCoef));
    actual[4] = previous[4].add(previous[5].multiply(deltaT));
    actual[5] = previous[5];

    //evaluacion
    Vector deltaA = new Vector((-k*previous[0].x - viscocity*previous[1].x)/particle.weight, 0).substract(actual[2]);
    Vector deltaR2 = deltaA.multiply( Math.pow(deltaT,2)).divide(2);

    //correccion
    previous[0] = actual[0].add( deltaR2.multiply(((3.0/20))));
    previous[1] = actual[1].add( deltaR2.multiply(((251.0/360)) / (deltaT)));
    previous[2] = actual[2].add( deltaR2.multiply((2 / Math.pow(deltaT, 2))));
    previous[3] = actual[3].add( deltaR2.multiply(((11.0/18)*6) / Math.pow(deltaT, 3)));
    previous[4] = actual[4].add( deltaR2.multiply(((1.0/6)*24) / Math.pow(deltaT, 4)));
    previous[5] = actual[5].add( deltaR2.multiply(((1.0/60)*120) / Math.pow(deltaT, 5)));

    //setea posiciones-velocidades
    particle.xPos = previous[0].x;
    particle.yPos = previous[0].y;
    particle.xVel = previous[1].x;
    particle.yVel = previous[1].y;
  }
}