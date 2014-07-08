package physics;

import java.util.ArrayList;

import processing.core.PVector;

public class Particle {
	private float mass;
	private PVector pos;
	private PVector vel;
	private boolean mobile = true;
	private ArrayList<Force> forces;

	public Particle() {
		setMass(1);
		setPos(new PVector());
		setVel(new PVector());
		forces = new ArrayList<Force>();
	}
	public Particle(float mass, PVector pos, PVector vel){
		setMass(mass);
		setPos(pos);
		setVel(vel);
		forces = new ArrayList<Force>();
	}
	
	public float getMass() {
		return mass;
	}
	public void setMass(float mass) {
		if(mass <= 0) throw new RuntimeException("Mass cannot be less than or equal to zero.");
		this.mass = mass;
	}
	public void move(PVector deltaPos){
		pos.add(deltaPos);
	}
	public void accelerate(PVector deltaVel){
		vel.add(deltaVel);
	}
	public PVector getPos() {
		return pos;
	}
	public void setPos(PVector pos) {
		this.pos = pos.get();
	}
	public PVector getVel() {
		return vel;
	}
	public void setVel(PVector vel) {
		if(!mobile) throw new RuntimeException("Cannot change the velocity of an immobile particle.");
		this.vel = vel.get();
	}
	public boolean isMobile() {
		return mobile;
	}
	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}
	public void addForce(Force force) {
		forces.add(force);
	}
	public PVector netForce(PVector deltaPos, PVector deltaVel) {
		PVector net = new PVector();
		for(Force f: forces){
			net.add(f.act(PVector.add(pos, deltaPos),PVector.add(vel, deltaVel)));
		}
		return net;
	}
}
