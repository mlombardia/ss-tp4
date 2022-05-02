public class Particle {
  public double xPos;
  public double yPos;
  public double xVel;
  public double yVel;
  public double weight;
  public double acceleration;

  public Particle(double xPos, double yPos, double xVel, double yVel, double weight){
    this.xPos = xPos;
    this.yPos = yPos;
    this.xVel = xVel;
    this.yVel = yVel;
    this.weight = weight;
    this.acceleration = 0;
  }

  public void udpateVel(double xVel, double yVel){
    this.xVel += xVel;
    this.yVel += yVel;
  }
}