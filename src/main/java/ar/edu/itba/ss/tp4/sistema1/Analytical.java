package ar.edu.itba.ss.tp4.sistema1;

import ar.edu.itba.ss.tp4.models.Particle;

public class Analytical implements Integrator {

    public double amplitude;
    public double gamma;
    public double k;
    public double t;

    public Analytical(double amplitude, double gamma, double k) {
        this.amplitude = amplitude;
        this.gamma = gamma;
        this.k = k;
        this.t = 0;
    }

    @Override
    public void updateData(double deltaT, Particle particle) {
        this.t += deltaT;
        double T1 = Math.exp(-(this.gamma / (2 * particle.weight)) * this.t);
        double T3 = Math.pow((this.k / particle.weight) - ((Math.pow(this.gamma, 2) / (Math.pow(2 * particle.weight, 2)))), 0.5);
        double T2 = Math.cos( T3 * this.t);
        particle.xPos = amplitude * T1 * T2;
    }
}