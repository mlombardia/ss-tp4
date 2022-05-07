package ar.edu.itba.ss.tp4.simulation;

import ar.edu.itba.ss.tp4.models.FileRadiationGenerator;

public class RadiationSimulator {
    public static double k = Math.pow(10,10);
    public double Q = Math.pow(10, -19);
    public double M = Math.pow(10, -27);
    public double D = Math.pow(10, -8);

    FileRadiationGenerator fileRadiationGenerator = new FileRadiationGenerator();

    public RadiationSimulator(){

    }

    public void simulate(){
        /*while (cutCondition()){

        }*/
        fileRadiationGenerator.closeFiles();
    }

    public boolean cutCondition(){
        //si particula alcana algun borde o alguna de las particulas
        //return false

        //else
        return true;
    }
}
