import physics.*;

ParticleSystem sys;

Particle[][] nodes;
int xnum = 30;
int ynum = 20;
int xspacing = 10;
int yspacing = 10;
float k = 0.01;

void setup(){
  size(500,400);
  nodes = new Particle[ynum][xnum];
  sys = new ParticleSystem(this);
  sys.setTimeStep(0.5);
  for(int i = 0; i < xnum; i++){
    for(int j = 0; j < ynum; j++){
      Particle p = new Particle(0.05, new PVector(10 + i*xspacing,10 + j*yspacing), new PVector(0,0) );
      nodes[j][i] = p;
      sys.addParticle(p);
    }
  }
  for(int i = 0; i < xnum; i++){
    for(int j = 0; j < ynum; j++){
      if(i!=0) nodes[j][i].addForce(new LinearForce(nodes[j][i-1], k, xspacing));
      if(j!=0) nodes[j][i].addForce(new LinearForce(nodes[j-1][i], k, yspacing));
      if(i!=xnum-1) nodes[j][i].addForce(new LinearForce(nodes[j][i+1], k, xspacing));
      if(j!=ynum-1) nodes[j][i].addForce(new LinearForce(nodes[j+1][i], k, yspacing));
      nodes[j][i].addForce(new Force() {
        //Gravity
        public PVector act(PVector pos, PVector vel){
          return new PVector(0,0.002,0);
        }
      });
      nodes[j][i].addForce(new Force() {
        //Drag
        public PVector act(PVector pos, PVector vel){
          return PVector.mult(vel, -0.001);
        }
      });
      nodes[j][i].addForce(new Force() {
        //Random horizontal wind to the right
        public PVector act(PVector pos, PVector vel){
          return new PVector(random(0,0.005)*constrain(sin(frameCount/100),0,1),0);
        }
      });
    }
  }
  nodes[0][0].setMobile(false);
  nodes[0][1*xnum/5].setMobile(false);
  nodes[0][2*xnum/5].setMobile(false);
  nodes[0][3*xnum/5].setMobile(false);
  nodes[0][4*xnum/5].setMobile(false);
  nodes[0][xnum-1].setMobile(false);
}


void draw(){
  background(255);
  for(int i = 0; i < xnum; i++){
    for(int j = 0; j < ynum; j++){
      float x = nodes[j][i].getPos().x;
      float y = nodes[j][i].getPos().y;
      //don't double-draw the lines
      //if(i!=0) line(x,y,nodes[j][i-1].getPos().x, nodes[j][i-1].getPos().y);
      //if(j!=0) line(x,y,nodes[j-1][i].getPos().x, nodes[j-1][i].getPos().y);
      if(i!=xnum-1) line(x,y,nodes[j][i+1].getPos().x, nodes[j][i+1].getPos().y);
      if(j!=ynum-1) line(x,y,nodes[j+1][i].getPos().x, nodes[j+1][i].getPos().y);
    }
  }
}

