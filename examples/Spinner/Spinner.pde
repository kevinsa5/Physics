import physics.*;

ParticleSystem sys;

float initialOmega = 0.3;
float rodLength = 200;

Particle alice;
Particle bob;

void setup(){
  size(500,400);
  sys = new ParticleSystem(this);
  sys.setTimeStep(0.5);
  alice = new Particle(1, new PVector(width/2 - rodLength/2, height/2), new PVector(0, rodLength/2*initialOmega));
  bob   = new Particle(1, new PVector(width/2 + rodLength/2, height/2), new PVector(0,-rodLength/2*initialOmega));

  sys.addParticle(alice);
  sys.addParticle(bob);
  RigidBody rod = new RigidBody(alice, bob);
  sys.addRigidBody(rod);

}


void draw(){
  background(255);
  line(alice.getPos().x, alice.getPos().y, bob.getPos().x, bob.getPos().y);
  ellipse(alice.getPos().x, alice.getPos().y, 10, 10);
  ellipse(bob.getPos().x, bob.getPos().y, 10, 10);
}

