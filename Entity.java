public abstract class Entity{
  
  public static double TIME_SCALE = 50;// time scaling factor     
  protected char symbol;//character representation of this entity
  protected double x; //xcoordinate
  protected double y; //ycoordiante
  protected double speedX; //horizontal speed
  protected double speedY; //vertical speed
  public static final int MAX_SPEED = 10;
 
  protected boolean left; //is entity on left half of field
  public boolean jail; //is entity in jail
 
  //creates an entity
  public Entity(char symbol, int x, int y){
    this.symbol = symbol;
    this.x = x;
    this.y = y;
  }
 
 
  // gets symbol from entity
  public char getSymbol(){ return this.symbol; }
 
  //gets xdirection speed from entity
  public double getSpeedX(){ return this.speedX; }
 
  //gets ydirection speed from entity
  public double getSpeedY(){ return this.speedY; }
 
  //gets xcoordinate from entity
  public int getX() { return (int) this.x; }
 
  //gets ycoordinate from entity
  public int getY() { return (int) this.y; }
 

  public final void updatePosition(long time, Field field) throws EntityOutOfBoundsException
  {
    this.x += (time * this.speedX) / TIME_SCALE;
    this.y += (time * this.speedY) / TIME_SCALE;
    
    if (this.x > field.maxX /2)
    {
      this.left = true;
    }
    else
    {
      this.left = false;
    }
    checkCoordinates(field);
  }
 
  protected void checkCoordinates(Field field) throws EntityOutOfBoundsException
  {
    if (this.getX() > field.maxX-16 || this.getY() < field.minY  ||  this.getX() < field.minX ||  this.getY() > field.maxY-16)
    {
      throw new EntityOutOfBoundsException(this.getX() + " " + this.getY());
    }
  }

  public abstract void play(Field field);
 
  public abstract void update(Field field);
 
  @Override
  public boolean equals(Object o){
    throw new UnsupportedOperationException("Not supported yet.");    
  }
 
  public final void setX(Field.Umpire umpire, double x){
    // check that an actual Umpire was passed in
    if( umpire == null ){
      throw new SecurityException("Changing x coordinate of Entity is only permitted by Field.Umpire objects");
    }
    this.x = x;
  }
  
  public final void setY(Field.Umpire umpire, double y){
    // check that an actual Umpire was passed in
    if( umpire == null ){
      throw new SecurityException("Changing y coordinate of Entity is only permitted by Field.Umpire objects");
    }
    this.y = y;
  }

  public final void setSpeedX(Field.Umpire umpire, double speedX){
    // check that an actual Umpire was passed in
    if( umpire == null ){
      throw new SecurityException("Changing speedX of Entity is only permitted by Field.Umpire objects");
    }
    this.speedX = speedX;
  }
 
  public final void setSpeedY(Field.Umpire umpire, double speedY){
    // check that an actual Umpire was passed in
    if( umpire == null ){
      throw new SecurityException("Changing speedY of Entity is only permitted by Field.Umpire objects");
    }
    this.speedY = speedY;
  }
 
  // this is used for graphical representations
  protected Sprite sprite;  
 
  public Sprite getSprite(){ return this.sprite; }
 
  public void setSprite(Field.Umpire umpire, String ref){
    if( umpire == null ){
      throw new SecurityException("Changing sprite is only permitted by Field.Umpire objects");
    }
    this.sprite = SpriteStore.get().getSprite(ref);
  }
 
 
 
}


