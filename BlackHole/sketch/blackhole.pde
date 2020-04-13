class BlackHole
{
  PVector pos;
  float mass;
  float rs;
  public BlackHole(float x, float y, float mass)
  {
    this.pos = new PVector(x,y);
    this.mass = mass;
    this.rs = (2 * G * this.mass) / (c*c);
  }
  
  void Draw()
  {
    // event horizon
    fill(0);
    circle(pos.x,pos.y, this.rs*2);
    
    // discc
    noFill();
    stroke(100);
  }
}
