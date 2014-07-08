package physics;

import processing.core.PVector;

public class RK4Integrator extends Integrator {
	
	PVector zero;

	public RK4Integrator(ParticleSystem sys) {
		super(sys);
		zero = new PVector();
	}

	@Override
	public void step(float dt) {
		PVector[] karray = new PVector[sys.numParticles()];
		for(int i = 0; i < sys.numParticles(); i++){
			Particle p = sys.getParticle(i);
			if(!p.isMobile()) continue;
			
			PVector a1 = PVector.div(p.netForce(zero,zero), p.getMass());
			PVector k1 = PVector.mult(a1,dt);
			PVector a2 = PVector.div(p.netForce(PVector.mult(k1, (float)0.5), zero), p.getMass());
			PVector k2 = PVector.mult(a2,dt);
			PVector a3 = PVector.div(p.netForce(PVector.mult(k2, (float)0.5), zero), p.getMass());
			PVector k3 = PVector.mult(a3,dt);
			PVector a4 = PVector.div(p.netForce(k3, zero), p.getMass());
			PVector k4 = PVector.mult(a4,dt);
			karray[i] = PVector.add(PVector.div(PVector.add(k1,k4),6),
									PVector.div(PVector.add(k2,k3),3));
		}
		for(int i = 0; i < sys.numParticles(); i++){
			// ignore static particles
			if(karray[i] == null) continue;
			Particle p = sys.getParticle(i);
			p.accelerate(karray[i]);
			p.move(PVector.mult(p.getVel(),dt));
		}
	}
}
