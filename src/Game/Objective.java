package Game;

import Utilites.LinkedQueue;

/**
 * A class for managing boolean values and keeping track of the player's progress. It will also force the player to 
 * complete the given objective before they can move on.
 * @author Ian King
 */
public class Objective 
{
    private String currentTask;
//    private Task nextObjective;
    private LinkedQueue<String> unfinsihedTasks = new LinkedQueue<>();
    
    public Objective()
    {
        populateTasks();
        currentTask = unfinsihedTasks.dequeue();
    }
    
    private void populateTasks()
    {
        unfinsihedTasks.enqueue(("Go to Opicon Forest (Required Level: 6)"));
        unfinsihedTasks.enqueue(("Go to Aquammoda (Required Level: 7)"));
        unfinsihedTasks.enqueue(("Talk to Merda."));
        unfinsihedTasks.enqueue(("Go to Degon (Required Level: 9)"));
        unfinsihedTasks.enqueue(("Talk to Fleur."));
        unfinsihedTasks.enqueue(("Go to Zoni City (Required Level: 10)"));
        unfinsihedTasks.enqueue(("Enjoy the festival and talk to everyone!"));
        unfinsihedTasks.enqueue(("Go to Aerogan (Required Level: 12)"));
        unfinsihedTasks.enqueue(("Talk to Elder Nu."));
        unfinsihedTasks.enqueue(("Go to Tempest Tower."));
        unfinsihedTasks.enqueue(("Find Ninlil in Tempest Tower (Required Level: 14)"));
        unfinsihedTasks.enqueue(("Go to Infol(Required Level: 16)"));
        unfinsihedTasks.enqueue(("Talk to Elder Vulca."));
        unfinsihedTasks.enqueue(("Go to Mount Volcan."));
        unfinsihedTasks.enqueue(("Find the mineral for Elder Vulca (Required Level: 17)"));
        unfinsihedTasks.enqueue(("Talk to Lyra."));
        unfinsihedTasks.enqueue(("Go to Mount Zoni."));
        unfinsihedTasks.enqueue(("Go to Solice (Required Level: 20)"));
        unfinsihedTasks.enqueue(("Talk to Elder Zeno."));
        unfinsihedTasks.enqueue(("Go to Mount Zoni Summit."));
        unfinsihedTasks.enqueue(("Find Frigs (Required Level: 21)"));
        unfinsihedTasks.enqueue(("Go to Forlorn Cave (Required Level: 23)"));
        unfinsihedTasks.enqueue(("Go to Elerric (Required Level: 25)"));
        unfinsihedTasks.enqueue(("Talk to Elder Clairdra."));
        unfinsihedTasks.enqueue(("Return to Zoni City."));
        unfinsihedTasks.enqueue(("Search for Irwin (Required Level: 27)"));
        unfinsihedTasks.enqueue(("Find Irwin again (Required Level: 28)"));
        unfinsihedTasks.enqueue(("Restore Pulchra's peace (Required Level: 30)"));
    }
    
    public void update()
    {
        currentTask = unfinsihedTasks.dequeue();
    }
    
    public void printCurrentObjective()
    {
        MainGame.println("Current Objective: " + currentTask, 10);
    }
    
    @Override
    public String toString()
    {
        return currentTask;
    }
    
    /* 
    Make an instantiations method like in the game class to create all the Booleans that'll be checked for the game.
    Add them in order to the notCompleted ArrayList and when this Object is created, add the first not completed Boolean to 
    be the currentObjective. 
    */
    
//    private class Task
//    {
//        private boolean completed;
//        private final String TASK_DESCRIPTION;
//        
//        private Task(String taskDescription)
//        {
//            this.TASK_DESCRIPTION = taskDescription;
//            completed = false;
//        }
//        
//        private String getTaskDesc() {return TASK_DESCRIPTION;}
//        
//        private boolean isCompleted() {return completed;}
//        
//        private void completeTask() {completed = true;}
//        
//        @Override
//        public String toString()
//        {
//            return TASK_DESCRIPTION;
//        }
//    }
}
