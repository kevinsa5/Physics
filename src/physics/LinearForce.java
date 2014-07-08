package physics;

import processing.core.PVector;

public class LinearForce implements Force {

	float k;
	Particle other;
	float restLength;
	
	public LinearForce(Particle other, float k, float restLength) {
		this.other = other;
		this.k = k;
		this.restLength = restLength;
	}

	@Override
	public PVector act(PVector pos, PVector vel) {
		PVector f = PVector.sub(other.getPos(), pos);
		float len = f.mag();
		f.normalize();
		f.mult(k*(len-restLength));
		return f;
	}

}
