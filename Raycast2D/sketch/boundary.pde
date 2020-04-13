class Boundary
{
  PVector a,b;
  public Boundary(int x1,int y1,int x2,int y2)
  {
    this.a = new PVector(x1,y1);
    this.b = new PVector(x2,y2);
  }
  public Boundary(float x1,float y1,float x2,float y2)
  {
    this.a = new PVector(x1,y1);
    this.b = new PVector(x2,y2);
  }
  
  public void show()
  {
    stroke(255);
    line(a.x,a.y,b.x,b.y);
  }

}
