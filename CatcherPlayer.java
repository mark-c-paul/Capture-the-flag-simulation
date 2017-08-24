import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CatcherPlayer extends Player
{
  //Declare variables
  private boolean targetted = false;
  private boolean teamOne;
  private int target = -1;
  
  //Play function
  @Override
  public void play(Field field)
  {
    //Declaration of variables
    double y2, x2;
    double speedY2, speedX2;
    Entity e;
    Player p;
    double maxSpeed = 3;
    
    //temp holder to find name of Team1
    p = (Player) field.getTeam1().get(0);
    
    //if no current targets, get a target
    if (this.targetted == false)
    {
      //if Team1
      if(this.getTeam().equals(p.getTeam()))
      {
        teamOne = true;
      } 
      //else Team2
      else
      {
        teamOne = false;
      }
      this.target = changeTargets(field);
    }
    if (this.targetted == true && this.target != -1)
    {
      this.target = verifyTarget(field, this.target);
    }
    
    //if found a target, chase
    if (this.target != -1)
    {
      //gets the x/y info of targetted player and chase
      if (teamOne)
      {
        e = field.getTeam2().get(this.target);
      }
      else
      {
        e = field.getTeam1().get(this.target);
      }
      
      y2 = e.getY();
      x2 = e.getX();
      speedX2 = e.getSpeedX();
      speedY2 = e.getSpeedY();
      
      double m;
      
      //Predicting where the runner will be next refresh
      y2 = y2 + (int) speedY2;
      x2 = x2 + (int) speedX2;
      
      //Slope of the line between chaser and runner
      m = (y2 - this.y) / (x2 - this.x);
      
      //Calculation of X/Y speeds using the slope and a max speed vector
      speedX = 3 / Math.sqrt(Math.pow(m,2) + 1);
      speedY = speedX * m;
      
      //Bounce off walls
      if (x < field.minX+3 && speedX < 0)
      {
        speedX *= -1;
      }
      if (x > field.maxX/2 && speedX > 0)
      {
        speedX *= -1;
      }
      if (y < field.minY+3 && speedY < 0)
      {
        speedY *= -1;
      }
      if (y > field.maxY-19 && speedY > 0)
      {
        speedY *=-1;
      }
      
      if (x > x2)  //if behind runner, compensate
      {
        if (teamOne)
        {
          speedX *= -1;
          speedY *= -1;
        }
        else
        {
          speedY *= -1;
        }
      }
      if (x < x2 && !teamOne)
      {
        speedX *= -1;
      }
      
      //if caught, find a new target
      if (teamOne)
      {
        if (field.catchOpponent(this, (Player)field.getTeam2().get(this.target)) == true)
        {
          this.targetted = false;
        }
      }
      else
      {
        if (field.catchOpponent(this, (Player)field.getTeam1().get(this.target)))
        {
          this.targetted = false;
        }
      }
    }
    //if no target, stop moving
    else
    {
      speedX = 0;
      speedY = 0;
    }
  }
  
  private int changeTargets(Field field)
  {
    ArrayList<Entity> teamCopy;  
    Player p;
    
    if(teamOne)
    {
      //copies arraylist of Team2, and shuffles the order
      teamCopy = field.getTeam2();
      Collections.shuffle(teamCopy);
      
      //goes through the shuffled arraylist to find a player in valid territory
      for (int i = 0; i < teamCopy.size(); i++)
      {
        p = (Player)teamCopy.get(i);
        if (p.getX() < field.maxX / 2 && p.getJail() == false)
        {
          this.targetted = true;
          return i;
        }
      }
      this.targetted = false;
      return -1;
    } 
    else
    {
      teamCopy = field.getTeam1();
      Collections.shuffle(teamCopy);
      for (int i = 0; i < teamCopy.size(); i++)
      {
        p = (Player)teamCopy.get(i);
        if (p.getX() > field.maxX / 2  && p.getJail() == false)
        {
          this.targetted = true;
          return i;
        }
      }
      this.targetted = false;
      return -1;
    }
  }
  
  //verifies if the target being chased is still in the valid territory
  private int verifyTarget(Field field, int verify)
  {
    Player p;
    if(teamOne)
    {
      p = (Player)field.getTeam2().get(verify);
      //target not valid anymore, find a new target
      if (field.getTeam2().get(verify).getX() > field.maxX / 2  || p.getJail() == true)
      {
        return changeTargets(field);
      }
      //target still valid, keep chasing
      else
      {
        return verify;
      }
    }
    else
    {
      p = (Player)field.getTeam1().get(verify);
      if (field.getTeam1().get(verify).getX() < field.maxX / 2  || p.getJail() == true)
      {
        return changeTargets(field);
      }
      else
      {
        return verify;
      }
    }
  }
  
  @Override
  public void update(Field field){}
  
  
  @Override
  public boolean equals(Object o)
  {
    if(o == null)
    { 
      System.err.println("null equals");
      return false;
      
    }
    if(o instanceof Player && this.getName().equals(((Player)o).getName()))
    {
      return true;
    }
    return false;
  }
  
  public CatcherPlayer(String name, int number, String team,char symbol, int x, int y)
  {
    super(name, number, team, symbol, x, y);
    this.speedX = Math.random()*4;
    this.speedY = Math.random()*4;
  }
}