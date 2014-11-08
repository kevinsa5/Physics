package physics;

import processing.core.PVector;

public class RigidBody {
	Particle one, two;
	float separation;
	RigidForce f1, f2;
	
	public RigidBody(Particle one, Particle two){
		this.one = one;
		this.two = two;
		f1 = new RigidForce();
		f1.set(new PVector(0,0));
		f2 = new RigidForce();
		f2.set(new PVector(0,0));
		one.addForce(f1);
		two.addForce(f2);
		separation = PVector.sub(two.getPos(), one.getPos()).mag();
	}

	public void rigidify(float deltaT){
		if(!one.isMobile() && !two.isMobile()){
			throw new RuntimeException("Rigid body can't have two immobile particles");
		}
		
		PVector r = PVector.sub(two.getPos(), one.getPos());
		float dist = r.mag();

		PVector rhat= r.get();
		rhat.normalize();
		float ratio = 0.5f;
		if(!one.isMobile() || !two.isMobile()){
			ratio = 1;
		}
		if(one.isMobile()) {
			PVector deltaPos = PVector.mult(rhat, -1*ratio*(separation-dist));
			one.setVel(PVector.sub(one.getVel(), PVector.mult(rhat, -1*PVector.dot(one.getVel(), rhat))));
			one.setPos(PVector.add(one.getPos(), deltaPos));
		}
		if(two.isMobile()) {
			PVector deltaPos = PVector.mult(rhat, ratio*(separation-dist));
			two.setVel(PVector.sub(two.getVel(), PVector.mult(rhat, PVector.dot(two.getVel(), rhat))));
			two.setPos(PVector.add(two.getPos(), deltaPos));
		}
	}
}

