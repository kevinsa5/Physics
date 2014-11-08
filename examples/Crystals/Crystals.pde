import physics.*;

class Atom extends Particle
{
  float charge;
  Atom(float charge, PVector pos) {
    super(1, pos, new PVector(0, 0));
    this.charge = charge;
  }
}

class EForce implements Force
{
  Atom a, b;
  EForce(Atom a, Atom b) {
    this.a = a;
    this.b = b;
  }
  public PVector act(PVector pos, PVector vel) {
    PVector r = PVector.sub(b.getPos(), a.getPos());
    float dist = r.mag();
    if (dist < 10) {
      PVector rnorm = r.get();
      rnorm.normalize();
      a.setPos(PVector.add(a.getPos(), PVector.mult(rnorm, -0.95*(10-dist))));
      b.setPos(PVector.add(b.getPos(), PVector.mult(rnorm, 0.95*(10-dist))));
    }
    return PVector.mult(r, -K*a.charge*b.charge/(dist*dist*dist));
  }
}

ParticleSystem sys;
ArrayList<Atom> atoms;
float K = 100;
float D = -0.55;

void setup() {
  size(400, 400);
  sys = new ParticleSystem(this);
  atoms = new ArrayList<Atom>();
  for (int i = 0; i < 500; i++) {
    Atom a = new Atom((i%2==0)?1:-1, new PVector((float)Math.random()*width, (float)Math.random()*height));
    a.addForce(new Force() {
      //Drag
      public PVector act(PVector pos, PVector vel) {
        return PVector.mult(vel, D);
      }
    }
    );
    sys.addParticle(a);
    atoms.add(a);
  }
  for (int i = 0; i < atoms.size (); i++) {
    for (int j = 0; j < atoms.size (); j++) {
      if (i == j) continue;
      atoms.get(i).addForce(new EForce(atoms.get(i), atoms.get(j)));
    }
  }
}

void draw() {
  background(255);
  for (int i = 0; i < sys.numParticles (); i++) {
    Atom p = (Atom)sys.getParticle(i);
    fill(p.charge>0 ? color(255, 0, 0):color(0, 0, 255));
    ellipse(p.getPos().x, p.getPos().y, 10, 10);
    PVector pos = p.getPos();
    if (pos.x < 10 || pos.x > width-10) {
      p.setVel(new PVector(- p.getVel().x, p.getVel().y));
      p.setPos(new PVector(constrain(pos.x, 10, width-10), pos.y));
    }
    if (pos.y < 10 || pos.y > height-10) {
      p.setVel(new PVector(p.getVel().x, -p.getVel().y));
      p.setPos(new PVector(pos.x, constrain(pos.y, 10, height-10)));
    }
  }
}
