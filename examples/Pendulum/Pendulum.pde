import physics.*;

ParticleSystem sys;

float initialTheta = 150*PI/180;
float rodLength = 200;

Particle anchor;
Particle bob;

Particle bob2;

void setup(){
  size(500,400);
  sys = new ParticleSystem(this);
  sys.setIntegrator(new RK4Integrator(sys));
  sys.setTimeStep(1);
  anchor = new Particle(1, new PVector(width/2, height/2), new PVector(0,0));
  bob = new Particle(1, new PVector(anchor.getPos().x + rodLength*sin(initialTheta), anchor.getPos().y + rodLength*cos(initialTheta)), new PVector(0,0));
  bob2 = new Particle(1, new PVector(anchor.getPos().x + 0.25*rodLength*sin(initialTheta), anchor.getPos().y + 0.25*rodLength*cos(initialTheta)), new PVector(0,0));
  bob.addForce(new Force() {
    //Gravity
    public PVector act(PVector pos, PVector vel){
      return new PVector(0,0.1,0);
    }
  });
  bob2.addForce(new Force() {
    //Gravity
    public PVector act(PVector pos, PVector vel){
      return new PVector(0,0.1,0);
    }
  });

  
  anchor.setMobile(false);
  sys.addParticle(anchor);
  sys.addParticle(bob);
  sys.addParticle(bob2);
  RigidBody rod = new RigidBody(anchor, bob);
  RigidBody rod2 = new RigidBody(anchor, bob2);
  sys.addRigidBody(rod);
  sys.addRigidBody(rod2);
}


void draw(){
  background(255);
  line(anchor.getPos().x, anchor.getPos().y, bob.getPos().x, bob.getPos().y);
  ellipse(bob.getPos().x, bob.getPos().y, 10, 10);
  line(anchor.getPos().x, anchor.getPos().y, bob2.getPos().x, bob2.getPos().y);
  ellipse(bob2.getPos().x, bob2.getPos().y, 10, 10);
}
