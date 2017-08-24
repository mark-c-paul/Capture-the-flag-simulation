public class CaptureTheFlag{

 public static void main(String[] args){
  Field f = new Field();
  int NUM_PLAYERS = 100; //players per team
  Player A, B;
  
  //Generates teams who are 20% catchers, 10% jail breakers, 30% random walkers and 40% seekers
  for(int i=0; i<NUM_PLAYERS; i+=1){   
    if(i%10==0){
     B = new CatcherPlayer("Catcher", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     A = new CatcherPlayer("Catcher", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     f.registerPlayer(A,1);
     f.registerPlayer(B,2);
    }
    if(i%10==1){
      B = new SeekerPlayer("Seeker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new SeekerPlayer("Seeker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
    if(i%10==2){
      B = new JailBreaker("Jail Breaker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new JailBreaker("Jail Breaker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
     if(i%10==3){
      B = new RandomWalkerPlayer("Random Walker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new RandomWalkerPlayer("Random Walker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
    if(i%10==4){
      B = new SeekerPlayer("Seeker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new SeekerPlayer("Seeker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
    if(i%10==5){
      B = new RandomWalkerPlayer("Random Walker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new RandomWalkerPlayer("Random Walker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
     if(i%10==6){
      B = new CatcherPlayer("Catcher", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new CatcherPlayer("Catcher", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
    if(i%10==7){
      B = new SeekerPlayer("Seeker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new SeekerPlayer("Seeker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
    if(i%10==8){
      B = new SeekerPlayer("Seeker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new SeekerPlayer("Seeker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
    if(i%10==9){
      B = new RandomWalkerPlayer("Random Walker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
      A = new RandomWalkerPlayer("Random Walker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
      f.registerPlayer(A,1);
      f.registerPlayer(B,2);
    }
  } 

  boolean gameRunning = true;

  long iterations = 0;
  while( gameRunning ){
   iterations += 1;

   /* allow players to think about what to do and change directions */
   f.play();

   /* move all players */
   f.update();

   /* redraw all the players in their new positions */
   f.draw();

   // comment out the following line to run at super speed
   try{ Thread.sleep(10); } catch(Exception e) {}

   gameRunning = f.gameStillRunning();
  }
 }
}