public class SeekerPlayer extends Player {
  @Override
  public void play(Field field) {
    double m;
    int[] flagPosition;
    int[] yourFlag;
    boolean teamOne;
    Player p = (Player) field.getTeam1().get(0);
    
    
    //If Team1
    if (this.getTeam().equals(p.getTeam())) {
      teamOne = true;
      flagPosition = field.getFlag2Position();
      yourFlag = field.getBase1Position();
    }
    //else Team2
    else {
      teamOne = false;
      flagPosition = field.getFlag1Position();
      yourFlag = field.getBase2Position();
    }
    
    if(field.pickUpFlag(this) == true)
    {
      field.view.message(this.getTeam()+" have stolen their opponent's flag");
      
      flagPosition = yourFlag;
    }
    if (field.winGame(this) == true)
    {
      field.view.message(this.getTeam()+ " have won!");
    }
    
    int x2 = flagPosition[0];
    int y2 = flagPosition[1];
    
    m = (y2 - this.y) / (x2 - this.x);
    //Calculation of X/Y speeds using the slope and a max speed vector
    speedX = 3 / Math.sqrt(Math.pow(m,2) + 1);
    speedY = speedX * m;
    
    if (x > x2)  //if behind runner, compensate
    {
      speedX *= -1;
      speedY *= -1;
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
  
  public SeekerPlayer(String name, int number, String team,char symbol, int x, int y){
    super(name, number, team, symbol, x, y);
    this.speedX = Math.random()*4;
    this.speedY = Math.random()*4;
  }
}