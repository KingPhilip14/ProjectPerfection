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
    private LinkedQueue<String> completedTasks = new LinkedQueue<>();
    private LinkedQueue<String> unfinsihedTasks = new LinkedQueue<>();
    
    public Objective()
    {
        populateTasks();
        currentTask = unfinsihedTasks.dequeue();
    }
    
    private void populateTasks()
    {
        unfinsihedTasks.enqueue(("Go to Opicon Forest (Required Level: 6)"));
        unfinsihedTasks.enqueue(("Go to the Water Village (Required Level: 7)"));
        unfinsihedTasks.enqueue(("Talk to Merda."));
        unfinsihedTasks.enqueue(("Go to the Earth Village (Required Level: 9)"));
        unfinsihedTasks.enqueue(("Talk to Fleur."));
        unfinsihedTasks.enqueue(("Go to Zoni Village (Required Level: 10)"));
        unfinsihedTasks.enqueue(("Enjoy the festival and talk to everyone!"));
        unfinsihedTasks.enqueue(("Go to the Wind Village (Required Level: 12)"));
        unfinsihedTasks.enqueue(("Talk to Elder Nu."));
        unfinsihedTasks.enqueue(("Go to Tempest Tower."));
        unfinsihedTasks.enqueue(("Find Ninlil in Tempest Tower (Required Level: 14)"));
        unfinsihedTasks.enqueue(("Go to the Fire Village (Required Level: 15)"));
        unfinsihedTasks.enqueue(("Talk to Elder Vulca."));
        unfinsihedTasks.enqueue(("Go to Mount Volcan (Required Level: 16)"));
        unfinsihedTasks.enqueue(("Go to Mount Zoni (Required Level: 18)"));
        unfinsihedTasks.enqueue(("Go to the Ice Village (Required Level: 19)"));
        unfinsihedTasks.enqueue(("Talk to Elder Zeno."));
        unfinsihedTasks.enqueue(("Go to the Forlorn Desert (Required Level: 22)"));
        unfinsihedTasks.enqueue(("Go to the Electric Village (Required Level: 23)"));
        unfinsihedTasks.enqueue(("Talk to Elder Clairdra."));
        unfinsihedTasks.enqueue(("Return to Zoni Village (Required Level: 25)"));
        unfinsihedTasks.enqueue(("Defeat Irwin."));
    }
    
    public void update()
    {
        completedTasks.enqueue(currentTask);
        currentTask = unfinsihedTasks.dequeue();
    }
    
    public void printCurrentObjective()
    {
        MainGame.println("Current Objective: " + currentTask, 10);
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
