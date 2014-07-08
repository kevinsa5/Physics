package physics;

import processing.core.PVector;

public class RK2Integrator extends Integrator {
	
	PVector zero;

	public RK2Integrator(ParticleSystem sys) {
		super(sys);
		zero = new PVector();
	}

	@Override
	public void step(float dt) {
		PVector[] k2array = new PVector[sys.numParticles()];
		for(int i = 0; i < sys.numParticles(); i++){
			Particle p = sys.getParticle(i);
			if(!p.isMobile()) continue;
			
			PVector a1 = PVector.div(p.netForce(zero,zero), p.getMass());
			PVector k1 = PVector.mult(a1,dt);
			PVector a2 = PVector.div(p.netForce(PVector.mult(k1, (float)0.5), zero), p.getMass());
			PVector k2 = PVector.mult(a2,dt);
			k2array[i] = k2;
		}
		for(int i = 0; i < sys.numParticles(); i++){
			// ignore static particles
			if(k2array[i] == null) continue;
			Particle p = sys.getParticle(i);
			p.accelerate(k2array[i]);
			p.move(PVector.mult(p.getVel(),dt));
		}
	}
}
