import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Arrays;

public class Field
{
  public static boolean START_FROM_BASE = true; // determines if players start at base or not
  protected static long SCALE = 30; //scaling for moving
  protected static boolean GAME_IS_WON = false; //has there been a winner yet
  public static Double ARMS_LENGTH = 12.0; //distance that is concidered in range
  
  final int maxX; //max x value
  final int maxY; //max y value
  final int minX; //min x value
  final int minY; //min y value
  
  final View view; //view of the game
  
  
  /** static Umpire that will referee the game (and modify entity locations and speeds) */
  private static Umpire umpire = new Umpire();
  
  
  /** Static inner class that is the umpire of the game
    * An Umpire object is needed in order to externally change
    * the x,y coordinates and speed of an entity.
    * The Umpire constructor is private so only a Field can
    * create one.  
    */
  public static class Umpire
  {
    // private constructor so that only Field can create an Umpire
    private Umpire(){}
  }
  
  //team 1
  protected ArrayList<Entity> team1 = new ArrayList<Entity>();
  public ArrayList<Entity> getTeam1(){ return this.team1; }
  protected final Entity flag1 = new Flag('f', 0, 0);
  protected final Entity jail1 = new Jail('j', 0, 0);
  protected final Entity base1 = new Base('b', 0, 0);
  
  //team 2
  protected ArrayList<Entity> team2 = new ArrayList<Entity>();
  public ArrayList<Entity> getTeam2(){ return this.team2; }
  protected final Entity flag2 = new Flag('F', 0, 0);
  protected final Entity jail2 = new Jail('J', 0, 0);
  protected final Entity base2 = new Base('B', 0, 0);
  
  ArrayList<Entity> things = new ArrayList<Entity>();//stored entities
  
  // A look-up table that maps entity's to the team (territory) they belong to
  protected Hashtable<Entity, Integer> getTeam = new Hashtable<Entity, Integer>(); 
  // gets the x and y coordinates of flag 1
  public int[] getFlag1Position(){ return new int[]{ flag1.getX(), flag1.getY() }; }  
  // gets the x and y coordinates of flag 2
  public int[] getFlag2Position(){ return new int[]{ flag2.getX(), flag2.getY() }; }
  // gets the x and y coordinates of jail 1
  public int[] getJail1Position(){ return new int[]{ jail1.getX(), jail1.getY() }; }
  // gets the x and y coordinates of jail 2
  public int[] getJail2Position(){ return new int[]{ jail2.getX(), jail2.getY() }; }
  // gets the x and y coordinates of base 1
  public int[] getBase1Position(){ return new int[]{ base1.getX(), base1.getY() }; }
  // gets the x and y coordinates of base 2
  public int[] getBase2Position(){ return new int[]{ base2.getX(), base2.getY() }; }
  
  // getter for all the "things" in the game: flags, bases and jails
  public ArrayList<Entity> getThings()
  {
    Entity[] allThings = new Entity[]{ flag1, flag2, base1, base2, jail1, jail2 };
    return new ArrayList<Entity>( Arrays.asList(allThings) );
  }
  
  //update the position of all entity's on this field
  public void update()
  {
    for(Entity en: team1)
    {
      Player p = (Player) en;
      if (p.getJail() == false)
      {
        try
        {
          en.updatePosition(SCALE*1, this);
        }catch(EntityOutOfBoundsException e)
        {
          //Checks for players on team 1
          System.err.println("Out of bounds at " + en.getSpeedX() + "," + en.getY() + " From team 1");
          
        }
      }
    }
    for(Entity en: team2)
    {
      Player p = (Player) en;
      if (p.getJail() == false)
      {
        try
        {
          en.updatePosition(SCALE*1, this);
        }catch(EntityOutOfBoundsException e)
        {
          //Checks for the players on team 2
          System.err.println("Out of bounds at " + en.getSpeedX() + "," + en.getY() + " From team 2");
        }
      }
    }
    for(Entity en: things)
    {
      try
      {
        en.updatePosition(SCALE*1, this);
      }catch(EntityOutOfBoundsException e)
      {
        //checks for different items
        System.err.println("The " + en.getSymbol() + " is out of bounds at " + en.getSpeedX() + "," + en.getY());
      }
    }
  }
  
  /** have all entities on this field "play" (perform their own logic) */
  public void play()
  {
    for(Entity e: team1){
      e.play(this);
    }
    for(Entity e: team2){
      e.play(this);
    }
    for(Entity e: things){
      e.play(this);
    }
  }
  
  /** draw the current state of the game */
  public void draw()
  {
    view.update();
    view.draw(team1, team2, things);
  }
  
  
  // Assigns a player to a given team on the field
  public boolean registerPlayer(Player a, int territory){
    if( getTeam.containsKey(a) || territory < 1 || territory > 2){
      return false;
    }
    if( territory == 1 ){
      if (a.getX() > maxX/2){
        return false;
      }
      a.setSprite(umpire, "blue.png");
      if(START_FROM_BASE){
        a.setX(umpire, base1.getX());
        a.setY(umpire, base1.getY());
      }
      team1.add(a);
    }
    else{
      if (a.getX() < maxX/2){
        return false;
      }
      a.setSprite(umpire, "red.png");
      if(START_FROM_BASE){
        a.setX(umpire, base2.getX());
        a.setY(umpire, base2.getY());
      }
      team2.add(a);
    }
    getTeam.put(a, territory);
    return true;
  }
  
  // Attempt to catch a player
  public boolean catchOpponent(Player a, Player b){
     Entity c = flag1;
     Entity j = jail1;
     Entity base = base1;
     if( getTeam.get(a).equals(new Integer(1))){
       c = flag2;
       j = jail2;
       base = base2;
     }
  
    if(a.getTeam().equals(b.getTeam())){
      return false;
    }
    if( Math.hypot( a.getX() - b.getX(), a.getY() - b.getY() ) <= ARMS_LENGTH ){
       view.message(a.getName() + " from team " + a.getTeam() + " has caught " + b.getName() + " from team " + b.getTeam());
    if (b.hasFlag() == true){
       b.setHasFlag(false);
     if (c == flag1){
       if (b.getX() <= this.maxX/2){
         b.setX(umpire, b.getX());
         b.setY(umpire, b.getY());
         return true;
       }
       else{
         b.setX(umpire, j.getX());
         b.setX(umpire, j.getY());
         placeInJail(umpire, b);
         return true;
       }
     }
     else{
       if (b.getX() >= this.maxX/2){
         b.setX(umpire, b.getX());
         b.setY(umpire, b.getY());
         return true;
       }
       else{
         b.setX(umpire, j.getX());
         b.setX(umpire, j.getY());
         placeInJail(umpire, b);
         return true;
       }
     }
   }
   placeInJail(umpire, b);
   return true;
  }
  return false;
 }
  
  private void placeInJail (Umpire umpire, Player a){
    if( umpire == null ){
      throw new SecurityException("Jailing a player is only permitted by Field.Umpire objects");
    }
    
    int[] jailPosition;
    Player p = (Player) team1.get(0);
    //if team1
    if (a.getTeam().equals(p.getTeam()) == true)
    {
      jailPosition = getJail2Position();
      a.setJail(true);
      a.setX(umpire, jailPosition[0]);
      a.setY(umpire, jailPosition[1]);
    }
    else{
      jailPosition = getJail1Position();
      a.setJail(true);
      a.setX(umpire, jailPosition[0]);
      a.setY(umpire, jailPosition[1]);
    }
  }
  
  // attempt to free a player from jail
  public boolean freeTeammate(Player a){
    Player p = (Player)team1.get(0);
    Entity j;
    boolean teamOne;
    
    if (a.getTeam().equals(p.getTeam()) == true){
      j = jail2;
      teamOne = true;
    }
    else{
      j = jail1;
      teamOne = false;
    }
    if( Math.hypot( a.getX() - j.getX(), a.getY() - j.getY() ) <= ARMS_LENGTH && p.getJail() == false){
      if (teamOne){
        for (Entity e : team1){
          if (((Player)e).getJail() == true){
            ((Player)e).setJail(false);
            e.setX(umpire, base1.getX());
            e.setY(umpire, base1.getY());
          }
        }
      }
      else{
        for (Entity e : team2){
          if (((Player)e).getJail() == true){
            ((Player)e).setJail(false);
            e.setX(umpire, base2.getX());
            e.setY(umpire, base2.getY());
          }
        }
      }
      returnToBase(a);
      return true;
    }
    return false;
  }
  
  // return to base   
  public void returnToBase(Player a){
    Player p = (Player)team1.get(0);
    
    if (a.getTeam().equals(p.getTeam())){
      a.setX(umpire,base1.getX());
      a.setY(umpire,base1.getY());
    }
    else{
      a.setX(umpire,base2.getX());
      a.setY(umpire,base2.getY());
    }
  }
  
  // attempt to pick up a flag
  public boolean pickUpFlag(Player a){
    Entity b = flag1;
    if( getTeam.get(a).equals(new Integer(1))){
      b = flag2;
    }
    if( Math.hypot( a.getX() - b.getX(), a.getY() - b.getY() ) <= ARMS_LENGTH ){
      b.setX(umpire, a.getX());
      b.setY(umpire, a.getY());
      return true;
    }
    return false;
  }
  
  //attempt to win the game
  public boolean winGame(Player a){
    Entity b;
    Entity f;
    Player p = (Player)team1.get(0);
    
    if (a.getTeam().equals(p.getTeam()) == true){
      b = base1;
      f = flag2;
    }
    else{
      b = base2;
      f = flag1;
    }
    if( Math.hypot( f.getX() - b.getX(), f.getY() - b.getY() ) <= ARMS_LENGTH){
      GAME_IS_WON = true;
      return true;
    }
    return false;
  }
  
  //checks if game is still underway
  public boolean gameStillRunning(){
    return !GAME_IS_WON;
  }
  
  //makes the field
  public Field()
  {
    // initialize field dimensions
    maxX = 811; minX = 10;
    maxY = 610; minY = 10;
    
    // initialize all the "things" in the game
    base1.setX(umpire, 29+minX);  base1.setY(umpire, 199+minY);   base1.setSprite(umpire, "blueBase.png");
    flag1.setX(umpire, 34+minX);  flag1.setY(umpire, 191+minY);   flag1.setSprite(umpire, "blueFlag.png");
    jail1.setX(umpire, 29+minX);  jail1.setY(umpire, 399+minY);  jail1.setSprite(umpire, "jail.png");
    
    base2.setX(umpire, 749+minX);  base2.setY(umpire, 399+minY);  base2.setSprite(umpire, "redBase.png");
    flag2.setX(umpire, 754+minX);  flag2.setY(umpire, 391+minY);  flag2.setSprite(umpire, "redFlag.png");
    jail2.setX(umpire, 749+minX);  jail2.setY(umpire, 199+minY);   jail2.setSprite(umpire, "jail.png");
    
    getTeam.put(base1, 1); getTeam.put(jail1,1); getTeam.put(flag1, 1);
    getTeam.put(base2, 2); getTeam.put(jail2,2); getTeam.put(flag2, 2);
    
    things.add(base1); things.add(jail1); things.add(flag1);
    things.add(base2); things.add(jail2); things.add(flag2);
    
    // initialize the view
    view = new View(minX, minY, maxX, maxY);
  }
}
