import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch extends PApplet {

Boundary[] walls;
Particle p;
int wallCount = 5;
int xoff = 0;
int yoff = 10000;

public void setup()
{
  walls = new Boundary[wallCount+4];
  
  for (int i = 0; i < wallCount; i++)
  {
    float x1 = random(width);
    float x2 = random(width);
    float y1 = random(height);
    float y2 = random(height);
    
    walls[i] = new Boundary(x1,y1,x2,y2);
  }
  walls[walls.length-4] = (new Boundary(0, 0, width, 0));
  walls[walls.length-3] = (new Boundary(width, 0, width, height));
  walls[walls.length-2] = (new Boundary(width, height, 0, height));
  walls[walls.length-1] = (new Boundary(0, height, 0, 0));
   
  p = new Particle(width/2,height/2);
}

public void draw()
{
  background(0);
  
  for (Boundary b : this.walls)
  {
    b.show();
  }
  //p.update(noise(xoff) * width, noise(yoff) * height);
  p.update(mouseX, mouseY);
  p.show();
  p.look(walls);
  xoff += 0.01f;
  yoff += 0.01f;
}
class Particle {
  PVector pos;
  Ray[] rays;
  Particle(float x, float y) {
    this.pos = new PVector(x, y);
    this.rays = new Ray[360];
    for (int a = 0; a < this.rays.length; a += 1) {
      this.rays[a] = new Ray(this.pos, radians(a));
    }
  }

  public void update(float x, float y) {
    this.pos.set(x, y);
  }

  public void look(Boundary[] walls) {
    for (int i = 0; i < this.rays.length; i++) {
      Ray ray = this.rays[i];
      PVector closest = null;
      float record = 500000000;
      for (Boundary wall : walls) {
        PVector pt = ray.cast(wall);
        if (pt != null) {
          float d = PVector.dist(this.pos, pt);
          if (d < record) {
            record = d;
            closest = pt;
          }
        }
      }
      if (closest != null) {
        // colorMode(HSB);
        // stroke((i + frameCount * 2) % 360, 255, 255, 50);
        stroke(255, 100);
        line(this.pos.x, this.pos.y, closest.x, closest.y);
      }
    }
  }

  public void show() {
    fill(255);
    ellipse(this.pos.x, this.pos.y, 4, 4);
    for (Ray ray : this.rays) {
      ray.show();
    }
  }
}
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
  public void settings() {  size(1000,1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
