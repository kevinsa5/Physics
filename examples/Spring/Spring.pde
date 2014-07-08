import physics.*;

ParticleSystem sys;
Particle one;
Particle two;


void setup(){
  size(400,400);
  sys = new ParticleSystem(this);
  
  one = new Particle(1, new PVector(275,200), new PVector(0, 0.98));
  sys.addParticle(one);
  
  two = new Particle(1, new PVector(125,200), new PVector(0,-0.98));
  sys.addParticle(two);
  
  two.addForce(new LinearForce(one, 0.001, 200));
  one.addForce(new LinearForce(two, 0.001, 200));
}


void draw(){
  ellipse(one.getPos().x, one.getPos().y, 30,30);
  ellipse(two.getPos().x, two.getPos().y, 30,30);
}