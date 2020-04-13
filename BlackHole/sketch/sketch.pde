float G = 6.67;
int c = 30;

BlackHole m87; 
void setup()
{
  size(600,600);
  m87 = new BlackHole(100, 320, 3000);
  //background(255);
}

void draw()
{
  background(255);
  m87.Draw();
}
