package physics;

import processing.core.PVector;

public class RigidForce implements Force{

	PVector force;
	public RigidForce(){
		force = null;
	}
	@Override
	public PVector act(PVector pos, PVector vel) {
		return force;
	}
	public void set(PVector force){
		this.force = force;
	}

}
