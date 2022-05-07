package ar.edu.itba.ss.tp4.models;

public class Particle {
  public double xPos;
  public double yPos;
  public double xVel;
  public double yVel;
  public double weight;
  public double acceleration;
  public double color;
  public boolean electricCharge;

  public Particle(double xPos, double yPos, double xVel, double yVel, double weight){
    this.xPos = xPos;
    this.yPos = yPos;
    this.xVel = xVel;
    this.yVel = yVel;
    this.weight = weight;
    this.acceleration = 0;
  }

  public Particle(double xPos, double yPos, double xVel, double yVel, double weight, double color, boolean electricCharge){
    this.xPos = xPos;
    this.yPos = yPos;
    this.xVel = xVel;
    this.yVel = yVel;
    this.weight = weight;
    this.acceleration = 0;
    this.color = color;
    this.electricCharge = electricCharge;
  }

  public void udpateVel(double xVel, double yVel){
    this.xVel += xVel;
    this.yVel += yVel;
  }
}