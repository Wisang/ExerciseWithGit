void setup() {
  size(640, 480);
}


void draw() {
  ellipse(mouseX, mouseY, mouseX-pmouseX, mouseY-pmouseY);
}
