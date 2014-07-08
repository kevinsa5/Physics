/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package physics;

import java.util.ArrayList;

import processing.core.*;

/**
 * A particle system abstraction. This class keeps track of a list of particles 
 * and their interactions over time.  
 * @example Spring
 * 
 */

public class ParticleSystem {
	
	private PApplet host;
	private final static String VERSION = "##library.prettyVersion##";
	
	private ArrayList<Particle> particles;
	private ArrayList<Force> forces;
	private Integrator integrator;
	private float dt;
	

	/**
	 * Creates a new ParticleSystem. It will be have initially no particles 
	 * and configured to use the Verlet integrator. 
	 * 
	 * @example Hello
	 * @param	hostPApplet The parent PApplet 	
	 */
	public ParticleSystem(PApplet hostPApplet) {
		host = hostPApplet;
		host.registerMethod("pre", this);
	    host.registerMethod("dispose", this);
	    
	    particles = new ArrayList<Particle>();
	    this.setIntegrator(new RK2Integrator(this));
	    dt = 1;
	}
	
	public void addForce(Force f){
		forces.add(f);
	}
	
	public void addParticle(Particle p){
		particles.add(p);
	}
	/**
	 * Called before draw() is run -- should not be called by the user. 
	 */
	public void pre(){
		integrator.step(dt);
	}

	public ArrayList<Particle> getParticles(){
		return particles;
	}
	
	/**
	 * Called when the application exits -- should not be called by the user. 
	 */
	public void dispose(){
		
	}
	
	/**
	 * Changes the numerical integration method.
	 * 
	 * @param integrator The new integrator to use
	 */
	public void setIntegrator(Integrator integrator){
		this.integrator = integrator;
	}
	
	public void setTimeStep(float dt){
		this.dt = dt;
	}
	
	/**
	 * Returns the number of particles currently in the system.
	 * @return The particle count
	 */
	public int numParticles(){
		return particles.size();
	}
	/**
	 * Returns the "ith" particle in the system. Particles are index by the order they were added.
	 * @return the ith particle
	 */
	public Particle getParticle(int i){
		return particles.get(i);
	}
	public Particle removeParticle(int i){
		return particles.remove(i);
	}

	/**
	 * Returns the version of the library.
	 * 
	 * @return The version number string
	 */
	public static String version() {
		return VERSION;
	}
}

