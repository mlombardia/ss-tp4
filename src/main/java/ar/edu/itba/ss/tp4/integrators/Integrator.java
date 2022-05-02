package ar.edu.itba.ss.tp4.integrators;

import ar.edu.itba.ss.tp4.models.Particle;

public interface Integrator {
  void updateData(double deltaT, Particle particle);
}