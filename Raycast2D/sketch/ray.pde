class Ray
{
  PVector pos,dir;
  public Ray(PVector c, float angle)
  {
    this.pos = c;
    this.dir = PVector.fromAngle(angle);
  }
  
  public void show()
  {
    stroke(255);
    push();
    translate(this.pos.x, this.pos.y);
    //line(0,0,dir.x * 1,dir.y * 1);
    pop();
  }
  
  public void setDir(float x, float y)
  {
    this.dir.x = x - this.pos.x;
    this.dir.y = y - this.pos.y;
    this.dir.normalize();
  }
  
  public PVector cast(Boundary data)
  { 
    float t;
    double u;
    // boundary endpoint coords
    float x1 = data.a.x;
    float y1 = data.a.y;
    float x2 = data.b.x;
    float y2 = data.b.y;
    
    // ray origin and "endpoint" coords
    float x3 = this.pos.x;
    float y3 = this.pos.y;
    float x4 = this.pos.x + this.dir.x;
    float y4 = this.pos.y + this.dir.y;
    
    // get u and t
    final float den = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4);
    if (den==0)
    {
      return null;
    }
    
    t = ((x1-x3) * (y3-y4) - (y1-y3) * (x3-x4)) / den;
    u = -((x1-x2) * (y1-y3) - (y1-y2) * (x1-x3)) / den;
    
    if (t > 0 && t < 1 && u > 0)
    {
      final PVector pt = new PVector();
      pt.x = x1 + t * (x2-x1);
      pt.y = y1 + t * (y2-y1);
      return pt;
    }
    else
    {
      return null;
    }
  }
}
