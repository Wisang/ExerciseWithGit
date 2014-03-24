void setup() {
  size(800, 1200);
}


void draw() {
  ellipse(mouseX, mouseY, mouseX-pmouseX, mouseY-pmouseY);
}
