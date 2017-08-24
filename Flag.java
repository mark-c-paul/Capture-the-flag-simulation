public class Flag extends Entity{
  
  @Override
  public void play(Field field){}
  
  @Override
  public void update(Field field){}
  
  @Override
  public boolean equals(Object o){
    if(o == null){ 
      System.err.println("null equals");
      return false;  
    }
    if(o instanceof Flag && this.getSymbol() == ((Flag)o).getSymbol()){
      return true;
    }
    return false;
  }

  public Flag(char symbol, int x, int y){
    super(symbol, x, y);
    speedX = speedY = 0;
  }
}