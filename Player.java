public abstract class Player extends Entity{
  protected String team;//team name
  protected String name;//player's name
  protected int number;//player's number
  protected boolean hasFlag;//does player have flag
  protected boolean jail;//is player in jail

  public final String getTeam(){ return this.team; }//returns team name
 
  public final boolean getJail(){ return this.jail; }//returns jailed status
  
  public void setJail(boolean b){ //jails or frees player
        this.jail = b;
    }

  public final String getName(){ return this.name; }//returnss player name

  public final int getNumber(){ return this.number; }//returns player number

  public boolean hasFlag () { return this.hasFlag; }//checks if player has flag

  public void setHasFlag(boolean b){ //picks up or drops flag
        this.hasFlag = b;
    }

  public Player(String name, int number, String team, char symbol, int x, int y){//creates player
    super(symbol, x, y);
    this.name = name;
    this.number = number;
    this.team = team;
    this.hasFlag = false;
  }
 
  public final boolean catchOpponent(Player opponent, Field field){//checks if player is caught
    return field.catchOpponent(this, opponent);
  }

  public void hasBeenCaught(Field.Umpire umpire, Player opponent){//informs player they are caught
    if( umpire == null ){
      throw new SecurityException("Informing a player that they are caught is only permitted by Field.Umpire objects");
    }
  }
    
  public final boolean freeTeammate(Player p, Field field){//frees teammates from jail
    return field.freeTeammate(p);
  }
    
  public final boolean hasBeenFreed(Field.Umpire umpire, Player teammate){ //informs player they are free
    if( umpire == null ){
      throw new SecurityException("Informing a player that they are freed from jail is only permitted by Field.Umpire objects");
    }
    return false;
  }
 
  public final boolean pickUpFlag(Field field){//player has picked up oppentent flag
    return field.pickUpFlag(this);
  }
 
  public void hasPickedUpFlag(Field.Umpire umpire){//informs player they have the flag
    if( umpire == null ){
      throw new SecurityException("Informing a player that they have picked up a flag is only permitted by Field.Umpire objects");
    }
  }
 
  public void hasDroppedFlag(Field.Umpire umpire){//informs player they dropped the flag
    if( umpire == null ){
      throw new SecurityException("Informing a player that they have picked up a flag is only permitted by Field.Umpire objects");
    }  
  }

  public final boolean winGame(Field field){//checks if game is won
    return field.winGame(this);
  }
}





