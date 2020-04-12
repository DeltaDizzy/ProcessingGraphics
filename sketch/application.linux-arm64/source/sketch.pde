Boundary[] walls;
Particle p;
int wallCount = 5;
int xoff = 0;
int yoff = 10000;

void setup()
{
  walls = new Boundary[wallCount+4];
  size(1000,1000);
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

void draw()
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
  xoff += 0.01;
  yoff += 0.01;
}
