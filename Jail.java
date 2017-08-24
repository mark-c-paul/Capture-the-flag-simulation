public class Jail extends Entity{
  
  @Override
  public void play(Field field){}
  
  @Override
  public void update(Field field){}
  
  @Override
  public boolean equals(Object o){
    if(o == null) return false;
    if(o instanceof Jail && this.getSymbol() == ((Jail)o).getSymbol()){
      return true;
    }
    return false;
  }

  public Jail(char symbol, int x, int y){
    super(symbol, x, y);
    speedX = speedY = 0;
  }
}