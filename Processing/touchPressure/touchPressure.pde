float maxPressure = 3;

void setup() {
  noStroke();
  background(0);
}

void draw() {
  float motionPressure = event.getPressure();
  fill(motionPressure/maxPressure * 255);
  ellipse(mouseX, mouseY, mouseX-pmouseX, mouseY-pmouseY);
  println(motionPressure);
  if (motionPressure > maxPressure)
    maxPressure = motionPressure;
}

