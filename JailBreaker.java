public class JailBreaker extends Player {
  @Override
  public void play(Field field) {
    double m;
    int[] jailPosition;
    int[] basePosition;
    boolean teamOne;
    boolean rush = false;
    Player p = (Player) field.getTeam1().get(0);
    
    if (this.getJail() == false)
    {
      if (this.getTeam().equals(p.getTeam())) {
        teamOne = true;
        jailPosition = field.getJail2Position();
        basePosition = field.getBase1Position();
      }
      //else Team2
      else {
        teamOne = false;
        jailPosition = field.getJail1Position();
        basePosition = field.getBase2Position();
      }
      
      int x2 = jailPosition[0];
      int y2 = jailPosition[1];
      
      if (this.getJail() == false)
      {
        //Do a check for team 1
        if (teamOne) {
          for (int i = 0; i < field.getTeam1().size(); i++){
            if(((Player)field.getTeam1().get(i)).getJail() == true && this.getJail() == false){ //Comrade in jail detected
              rush = true;
              break; 
            }
          }
        } 
        else
        {
          for (int i = 0; i < field.getTeam2().size(); i++){
            if(((Player)field.getTeam2().get(i)).getJail() == true && this.getJail() == false){ //Comrade in jail detected
              rush = true;
              break;
            }
          }
        }
      }
      
      if (rush && this.getJail() == false)
      {
        m = (y2 - this.y) / (x2 - this.x);
        
        //Calculation of X/Y speeds using the slope and a max speed vector
        speedX = 2 / Math.sqrt(Math.pow(m,2) + 1);
        speedY = speedX * m;
      }
      else
      {
        speedX = 0;
        speedY = 0;
      }
      if (this.getJail() == false);
      {
        freeTeammate(this, field);
      }
      
      if (x > x2)
      {
        speedX *= -1;
        speedY *= -1;
      }
    }
    else
    {
      speedX = 0;
      speedY = 0;
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
  
  public JailBreaker(String name, int number, String team,char symbol, int x, int y){
    super(name, number, team, symbol, x, y);
    this.speedX = Math.random()*4;
    this.speedY = Math.random()*4;
  }
}