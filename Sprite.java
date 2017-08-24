// Code taken from http://www.cokeandcode.com/info/tut2d.html

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
 private Image image;//sprite's image
 
 public Sprite(Image image) {//create sprite
  this.image = image;
 }
 
 public int getWidth() {//return sprite width
  return image.getWidth(null);
 }

 public int getHeight() {//return sprite height
  return image.getHeight(null);
 }
 
 public void draw(Graphics g,int x,int y) {//draw the sprite
  g.drawImage(image,x,y,null);
 }
}