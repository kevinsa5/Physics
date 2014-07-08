package physics;

import processing.core.PVector;

public class GravitationalForce implements Force {

	float G;
	Particle other;
	float hostMass;
	
	public GravitationalForce(float hostMass, Particle other, float G) {
		this.hostMass = hostMass;
		this.other = other;
		this.G = G;
		
	}

	@Override
	public PVector act(PVector pos, PVector vel) {
		PVector r = PVector.sub(other.getPos(), pos);
		float dist = r.mag();
		return PVector.mult(r, G*hostMass*other.getMass()/(dist*dist*dist));
	}

}
