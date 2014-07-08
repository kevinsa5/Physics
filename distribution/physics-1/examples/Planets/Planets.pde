import physics.*;

ParticleSystem sys;
Particle sun;
Particle[] planets;
int numPlanets = 10000;
final float G = 0.1;

void setup(){
  size(500,500);
  sys = new ParticleSystem(this);
  sun = new Particle(1000, new PVector(width/2, height/2), new PVector());
  sys.addParticle(sun);
  planets = new Particle[numPlanets];
  
  for(int i = 0; i < planets.length; i++){
  	float r = (float) Math.random() * (width/2);
  	float v = (float) Math.random();
  	float theta = (float) (Math.random() * (2 * Math.PI));
  	planets[i] = new Particle(1, new PVector((width/2 + r*cos(theta)), (height/2 + r*sin(theta))), 
  	                             new PVector((-v*cos(PI/2 - theta)), (v*sin(PI/2-theta))));
  	planets[i].addForce(new GravitationalForce(1, sun, G));
  	sys.addParticle(planets[i]);
  }
}


void draw(){
  background(255);
  for(int i = 0; i < sys.numParticles(); i++){
    Particle p = sys.getParticle(i);
    point(p.getPos().x, p.getPos().y);
  }
  if(frameCount % 10 == 0){
    for(int i = 0; i < sys.numParticles(); i++){
      Particle p = sys.getParticle(i);
      if(p.getPos().x < 0 || p.getPos().y < 0 || p.getPos().x > width || p.getPos().y > height){
        sys.removeParticle(i);
        i--;
      }
    }
  }
}