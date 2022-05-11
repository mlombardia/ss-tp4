package ar.edu.itba.ss.tp4.models;

import java.util.Objects;

import static ar.edu.itba.ss.tp4.simulation.RadiationSimulator.Q;

public class Particle {
    public double id;
    public double xPos;
    public double yPos;
    public double xVel;
    public double yVel;
    public double weight;
    public double acceleration;
    public double color;
    public boolean electricCharge;
    public boolean positiveCharge;

    public double charge;

    public Particle(double xPos, double yPos, double xVel, double yVel, double weight) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.weight = weight;
        this.acceleration = 0;
    }

    public Particle(double id, double xPos, double yPos, double xVel, double yVel, double weight, double color, boolean positiveCharge) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.weight = weight;
        this.acceleration = 0;
        this.color = color;
        this.positiveCharge = positiveCharge;
        this.charge = positiveCharge ? Q : -Q;
    }

    public Particle(double xPos, double yPos, double xVel, double yVel, double weight, double color, boolean positiveCharge) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.weight = weight;
        this.acceleration = 0;
        this.color = color;
        this.positiveCharge = positiveCharge;
        this.charge = positiveCharge ? Q : -Q;
    }

    public void udpateVel(double xVel, double yVel) {
        this.xVel += xVel;
        this.yVel += yVel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return Double.compare(particle.id, id) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}