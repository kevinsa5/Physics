package physics;

public abstract class Integrator {
	ParticleSystem sys;
	public Integrator(ParticleSystem sys){
		this.sys = sys;
	}
	
	public abstract void step(float dt);
}
