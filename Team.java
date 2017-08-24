
public class Team {

 Player oneA, twoA, threeA, fourA, fiveA, sixA, sevenA, eightA, nineA, tenA, p, oneB, twoB, threeB, fourB, fiveB, sixB, sevenB, eightB, nineB, tenB;

//in the event of no teamNumber specified, a team of randomwalkers will be created on team 1
 public Team(Field theField, int numberOfPlayers) {
  for(int i=0; i<numberOfPlayers; i+=1){   

   if(i%1==0){
    oneA = new CatcherPlayer("Random walker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
    theField.registerPlayer(oneA,1);
    
    oneB = new CatcherPlayer("Catcher", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(oneB,2);
   }
   
  }
 }
//creates the ratios of team players on team 2
 public Team(Field theField, int numberOfPlayers, int teamNumber) {
  for(int i=0; i<numberOfPlayers; i+=1){   
   if (teamNumber==2){
    if(i%10==0){
     oneB = new CatcherPlayer("Catcher", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(oneB,2);
    }
    if(i%10==1){
     twoB = new SeekerPlayer("Seeker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(twoB, 2);
    }
    if(i%10==2){
     threeB = new JailBreaker("Jail Breaker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(threeB, 2);
    }
     if(i%10==3){
     fourB = new RandomWalkerPlayer("RandomWalker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(fourB,2);
    }
    if(i%10==4){
     fiveB = new SeekerPlayer("Seeker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(fiveB, 2);
    }
    if(i%10==5){
     sixB = new RandomWalkerPlayer("RandomWalker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(sixB, 2);
    }
     if(i%10==6){
     sevenB = new CatcherPlayer("Catcher", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(sevenB,2);
    }
    if(i%10==7){
     eightB = new SeekerPlayer("Seeker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(eightB, 2);
    }
    if(i%10==8){
     nineB = new SeekerPlayer("Seeker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(nineB, 2);
    }
     if(i%10==9){
     tenB = new RandomWalkerPlayer("RandomWalker", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(tenB,2);
    }

     
     //creates an all Catcher player team 
 
   if (teamNumber==3){
     
     if(i%1==0){
     oneB = new CatcherPlayer("Catcher", 7, "reds", 'r', (int) (Math.random()*400)+410,(int) (Math.random()*500+10));
     theField.registerPlayer(oneB,2);
     }
   }
 
   }

   else { 

    if(i%10==0){
     oneA = new CatcherPlayer("Catcher", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(oneA,1);
    }
    if(i%10==1){
     twoA = new SeekerPlayer("Seeker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(twoA, 1);
    }
    if(i%10==2){
     threeA = new JailBreaker("Jail Breaker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(threeA, 1);
    }
    if(i%10==3){
     fourA = new CatcherPlayer("Catcher", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(fourA,1);
    }
    if(i%10==4){
     fiveA = new SeekerPlayer("Seeker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(fiveA, 1);
    }
    if(i%10==5){
     sixA = new CatcherPlayer("Catcher", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(sixA,1);
    }
    if(i%10==6){
     sevenA = new SeekerPlayer("Seeker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(sevenA, 1);
    }
    if(i%10==7){
     eightA = new JailBreaker("Jail Breaker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(eightA, 1);
    }
    if(i%10==8){
     nineA = new SeekerPlayer("Seeker", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(nineA,1);
    }
    if(i%10==9){
     tenA = new CatcherPlayer("Catcher", 12, "blues", 'b', (int) (Math.random()*400)+10,(int) (Math.random()*500+10));
     theField.registerPlayer(tenA, 1);
    }
   }
  }
 }
}

