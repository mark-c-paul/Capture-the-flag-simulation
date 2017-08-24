public class RandomWalkerPlayer extends Player{
 
  @Override
  public void play(Field field){

    if(this.x < field.minX+3||this.x > field.maxX-19){
    this.speedX *= -1;
    }
    if(this.y < field.minY+3||this.y > field.maxY-19){
    this.speedY *=-1;
    }
  }
    
  
  @Override
  public void update(Field field){}
  
  @Override
  public boolean equals(Object o){
    if(o == null){ 
      System.err.println("null equals");
      return false;
      
    }
    if(o instanceof Player && this.getName().equals(((Player)o).getName())){
      return true;
    }
    return false;
  }
  
  public RandomWalkerPlayer(String name, int number, String team,char symbol, int x, int y){
    super(name, number, team, symbol, x, y);
    this.speedX = Math.random()*4;
    this.speedY = Math.random()*4;
  }
  
}