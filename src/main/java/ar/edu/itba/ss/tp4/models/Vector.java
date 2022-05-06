package ar.edu.itba.ss.tp4.models;


public class Vector {
    public double x;
    public double y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector vector){
        return new Vector(this.x + vector.x, this.y + vector.y );
    }

    public Vector multiply(double number){
        return new Vector(this.x *number, this.y*number);
    }

    public Vector multiply(Vector vector){
        return new Vector(this.x *vector.x, this.y*vector.y);
    }

    public Vector divide(double number){
        return new Vector(this.x /number, this.y/number);
    }

    public Vector substract(Vector vector){
        return new Vector(this.x - vector.x, this.y-vector.y);
    }
}
