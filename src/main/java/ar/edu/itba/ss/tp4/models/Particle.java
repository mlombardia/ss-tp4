public class Particle {
  public double xPos;
  public double yPos;
  public double xVel;
  public double yVel;

  public Particle(double xPos, double yPos, double xVel, double yVel){
    this.xPos = xPos;
    this.yPos = yPos;
    this.xVel = xVel;
    this.yVel = yVel;
  }

  public void udpateVel(double xVel, double yVel){
    this.xVel += xVel;
    this.yVel += yVel;
  }
}