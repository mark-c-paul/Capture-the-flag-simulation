public abstract class Player extends Entity
{
  /** this player's team name */
  protected String team;
 
  /** this player's name */
  protected String name;
 
  /** this player's number */
  protected int number;

  /** if this player has the flag */
  protected boolean hasFlag;


  //Player is in jail or not
  protected boolean jail;
   
  /** gets this player's team name
    *
    * @return the team name that this player is on
    */
  public final String getTeam(){ return this.team; }
 
  /** gets this player's jail status
    *
    * @return true/false
    */
  public final boolean getJail(){ return this.jail; }
  
  public void setJail(boolean b) { 
        this.jail = b;
    }

  /** gets this player's name
    *
    * @return the name of this player
    */
  public final String getName(){ return this.name; }

  /** gets this player's number
    *
    * @return the number of this player
    */
  public final int getNumber(){ return this.number; }

  /** if this player has a flag
    *
    * @returns true/false
    */
  public boolean hasFlag () { return this.hasFlag; }

public void setHasFlag(boolean b) { 
        this.hasFlag = b;
    }


 
  /** creates a player with specified symbol at specified position
    *
    * @param name is this name of the player
    * @param number is this player's number
    * @param team is this player's team name
    * @param symbol is a character (char) representation of this player
    * @param x is the x-coodinate of this player
    * @param y is the y-coordinate of this player
    */
  public Player(String name, int number, String team, char symbol, int x, int y)
  {
    super(symbol, x, y);
    this.name = name;
    this.number = number;
    this.team = team;
      this.hasFlag = false;

  }
 
  /** attemp to catch an opponent player
    *
    * @param opponent a player on the opponent's team that you are trying to catch
    * @param field is the field the game is being played on
    * @return true if this player successfully catches the oppenent player, false otherwise
    */
  public final boolean catchOpponent(Player opponent, Field field)
  {
    return field.catchOpponent(this, opponent);
  }
 
  /** Informs this player that they have been caught by another player.
    * <p>
    * This method should only be called from with the Field class.  
    *
    * @param umpire is the umpire of the game and is needed for this method to be called; umpire cannot be null.
    * @param opponent is the player that caught this player  
    **/
  public void hasBeenCaught(Field.Umpire umpire, Player opponent)
  {
    if( umpire == null )
    {
      throw new SecurityException("Informing a player that they are caught is only permitted by Field.Umpire objects");
    }
    
  }
    
  /** attempt to free a teammate from jail
    *
    * @param teammate is another player on this player's team
    * @param field is the field the game is being played on
    * @return true if the <code>teammate</code> is successfully freed from jail, false otherwise
    */
  public final boolean freeTeammate(Player p, Field field)
  {
    return field.freeTeammate(p);
  }
    
  /** Informs this player that they have been freed by a teammate
    * <p>
    * This method should only be called from with the Field class.  
    *
    * @param umpire is the umpire of the game and is needed for this method to be called; umpire cannot be null.
    * @param teammate is the player that caught this player  
    */
  public final boolean hasBeenFreed(Field.Umpire umpire, Player teammate)
  {
    if( umpire == null )
    {
      throw new SecurityException("Informing a player that they are freed from jail is only permitted by Field.Umpire objects");
    }
    
    return false;
  }
 
 
 
  /** attempt to pick up the opponent's flag
    *
    * @param field is the field the game is being played on
    * @return true this player successfully picked up the opponent's flag, false otherwise
    */
  public final boolean pickUpFlag(Field field)
  {
    return field.pickUpFlag(this);
  }
 
 
  /** Informs this player that they have picked up the flag
    * <p>
    * This method should only be called from with the Field class.  
    *
    * @param umpire is the umpire of the game and is needed for this method to be called; umpire cannot be null.
    */
  public void hasPickedUpFlag(Field.Umpire umpire)
  {
    if( umpire == null )
    {
      throw new SecurityException("Informing a player that they have picked up a flag is only permitted by Field.Umpire objects");
    }
    
  }
 
  /** Informs this player that they have dropped the flag
    * <p>
    * This method should only be called from with the Field class.  
    *
    * @param umpire is the umpire of the game and is needed for this method to be called; umpire cannot be null.
    */
  public void hasDroppedFlag(Field.Umpire umpire)
  {
    if( umpire == null )
    {
      throw new SecurityException("Informing a player that they have picked up a flag is only permitted by Field.Umpire objects");
    }
    
  }
 
 
  /** attempt to win the game
    *
    * @param field is the field the game is being played on
    * @return true this player successfully brings the opponent's flag back to this player's base, false otherwise
    */
  public final boolean winGame(Field field)
  {
    return field.winGame(this);
  }
 
}





