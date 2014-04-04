import ketai.sensors.*;
KetaiSensor sensor;
float accelerometerX, accelerometerY, accelerometerZ;
float r, g, b;
int num = 360;  //(1)
color[] palette = new color[num];  //(2)
int paletteIndex = 0;  //(3)

//int[] angles = { 30, 10, 45, 35, 60, 38, 75, 67 };

void setup() {
  sensor = new KetaiSensor(this);
  sensor.start();
  orientation(PORTRAIT);
  
  //size(640, 360);
  //noStroke();
  //noLoop();  // Run once and stop
}

void draw() {
  background(255);
  r = map(accelerometerX, -10, 10, 0, 255);
  g = map(accelerometerY, -10, 10, 0, 255);
  b = map(accelerometerZ, -10, 10, 0, 255);
  
 // int lastAngle = 0;
  for (int i = 0; i < num; i++) {
    //map(i, 0, data.length, 0, 255);
    fill(palette[i]);
    arc(width/2, height/2, width/2, width/2, i, radians(i+1)); //lastAngle+radians(palette[i]));
    //lastAngle += radians(palette[i]);
  }
 
 // pieChart(width, palette);
  
  fill(255);
  ellipse(width/2, height/2, width/2, width/2);
}

void onAccelerometerEvent(float x, float y, float z) {
  accelerometerX = x;
  //accelerometerY = y;
  //accelerometerZ = z;

  // updating color value, tapping top half of the screen
  //if (mouseY < height/2) {
    palette[paletteIndex] = color(r, 0, 0);  //(8)
    if (paletteIndex < num-1) {
      paletteIndex++;  //(9)
    } 

    else {
      paletteIndex = 0;  //(10)
    }
  //}
}

/*
void pieChart(float diameter, int[] data) {
  float lastAngle = 0;
  for (int i = 0; i < num; i++) {
    //map(i, 0, data.length, 0, 255);
    fill(gray);
    arc(width/2, height/2, diameter, diameter, lastAngle, lastAngle++); //lastAngle+radians(palette[i]));
    //lastAngle += radians(palette[i]);
  }
}
*/
