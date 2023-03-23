package Battle;

import java.util.ArrayList;

/**
 * A class for creating Class types for player characters/
 * @author Ian King
 */
public class PlayerClass 
{
    private String className;
    private String primaryRole;
    private String secondaryRole;
    private static ArrayList<PlayerClass> listOfClasses = new ArrayList<>();
    private String statSpread;
    
    private PlayerClass(String className, String primaryRole, String secondaryRole)
    {
        this.className = className;
        this.primaryRole = primaryRole;
        this.secondaryRole = secondaryRole;
    }
    
    public String getSpecificClassName()
    {
        return className;
    }
    
    public void setClassName(String value) {className = value;}
    
    public String getPrimaryRole()
    {
        return primaryRole;
    }
    
    public void setPrimaryRole(String value) {primaryRole = value;} 
    
    public String getSecondaryRole() {return secondaryRole;}

    public void setSecondaryRole(String value) {secondaryRole = value;}
    
    public static String getClassName(String toLookFor)
    {
        String name = "";
        for(PlayerClass pc : listOfClasses)
        {
            if(toLookFor.equals(pc.className))
            {
                name = pc.className;
            }
        }
        
        return name;
    }
    
    public String getStatSpread() {return statSpread;}
    
    public static void createClasses()
    {
        PlayerClass masterClerk = new PlayerClass("Master Clerk", "Clerk", "Clerk");
        masterClerk.statSpread = masterClerk.masterClerkStatSpread();
        
        PlayerClass passiveClerk = new PlayerClass("Passive Clerk", "Clerk", "Tank");
        passiveClerk.statSpread = passiveClerk.passiveClerkStatSpread();
        
        PlayerClass hyperClerk = new PlayerClass("Hyper Clerk", "Clerk", "Striker");
        hyperClerk.statSpread = hyperClerk.hyperClerkStatSpread();
        
        
        PlayerClass masterTank = new PlayerClass("Master Tank", "Tank", "Tank");
        masterTank.statSpread = masterTank.masterTankStatSpread();
        
        PlayerClass wildTank = new PlayerClass("Wild Tank", "Tank", "Striker");
        wildTank.statSpread = wildTank.wildTankStatSpread();
        
        PlayerClass holyTank = new PlayerClass("Holy Tank", "Tank", "Clerk");
        holyTank.statSpread = holyTank.holyTankStatSpread();

        
        PlayerClass masterStriker = new PlayerClass("Master Striker", "Striker", "Striker");
        masterStriker.statSpread = masterStriker.masterStrikerStatSpread();
        
        PlayerClass tranquilStriker = new PlayerClass("Tranquil Striker", "Striker", "Striker");
        tranquilStriker.statSpread = tranquilStriker.tranquilStrikerStatSpread();
        
        PlayerClass guardianStriker = new PlayerClass("Guardian Striker", "Striker", "Striker");
        guardianStriker.statSpread = guardianStriker.guardianStrikerStatSpread();
        
        
        PlayerClass allRounder = new PlayerClass("All-Rounder", "All-Rounder", "All-Rounder");
        allRounder.statSpread = allRounder.allRounderStatSpread();
        
        listOfClasses.add(masterClerk);
        listOfClasses.add(passiveClerk);
        listOfClasses.add(hyperClerk);
        listOfClasses.add(masterTank);
        listOfClasses.add(wildTank);
        listOfClasses.add(holyTank);
        listOfClasses.add(masterStriker);
        listOfClasses.add(tranquilStriker);
        listOfClasses.add(guardianStriker);
        listOfClasses.add(allRounder);
    }
    
    public static PlayerClass getPlayerClass(String className)
    {
        className = className.toLowerCase();
        
        for(PlayerClass pc : listOfClasses)
        {
            if(pc.getSpecificClassName().toLowerCase().equals(className))
            {
                return pc;
            }
        }
        
        return null;
    }
    
    public boolean isPrimaryTank() {return primaryRole.equals("Tank");}
    public boolean isSecondaryTank() {return secondaryRole.equals("Tank");}
    public boolean isMasterTank() {return primaryRole.equals("Tank") && secondaryRole.equals("Tank");}
    
    public boolean isPrimaryClerk() {return primaryRole.equals("Clerk");}
    public boolean isSecondaryClerk() {return secondaryRole.equals("Clerk");}
    public boolean isMasterClerk() {return primaryRole.equals("Clerk") && secondaryRole.equals("Clerk");}
    
    public boolean isPrimaryStriker() {return primaryRole.equals("Striker");}
    public boolean isSecondaryStriker() {return secondaryRole.equals("Striker");}
    public boolean isMasterStriker() {return primaryRole.equals("Striker") && secondaryRole.equals("Striker");}
    
    
    private String masterClerkStatSpread()
    {
        return className + ":\n\tHP: Low\n\tAttack: Very Low\n\tDefense: Very Low\n\tRanged Attack: Very High\n\tRanged Defense: Very High\n\tSpeed: Low";
    }
    
    private String hyperClerkStatSpread()
    {
        return className + ":\n\tHP: Low\n\tAttack: Low\n\tDefense: Very Low\n\tRanged Attack: Very High\n\tRanged Defense: Average\n\tSpeed: Average";
    }
    
    private String passiveClerkStatSpread()
    {
        return className + ":\n\tHP: Average\n\tAttack: Average\n\tDefense: Average\n\tRanged Attack: Average\n\tRanged Defense: Average\n\tSpeed: Very Low";
    }
    
    private String masterTankStatSpread()
    {
        return className + ":\n\tHP: Very High\n\tAttack: Average\n\tDefense: Very High\n\tRanged Attack: Very Low\n\tRanged Defense: Very Low\n\tSpeed: Very Low";
    }
    
    private String wildTankStatSpread()
    {
        return className + ":\n\tHP: Very High\n\tAttack: High\n\tDefense: High\n\tRanged Attack: Very Low\n\tRanged Defense: Very Low\n\tSpeed: Very Low";
    }
    
    private String holyTankStatSpread()
    {
        return className + ":\n\tHP: Average\n\tAttack: Average\n\tDefense: High\n\tRanged Attack: Low\n\tRanged Defense: Low\n\tSpeed: Low";
    }
    
    private String masterStrikerStatSpread()
    {
        return className + ":\n\tHP: Average\n\tAttack: Very High\n\tDefense: Very Low\n\tRanged Attack: Average\n\tRanged Defense: Very Low\n\tSpeed: Very High";
    }
    
    private String tranquilStrikerStatSpread()
    {
        return className + ":\n\tHP: Low\n\tAttack: High\n\tDefense: Low\n\tRanged Attack: Average\n\tRanged Defense: Low\n\tSpeed: Very High";
    }
    
    private String guardianStrikerStatSpread()
    {
        return className + ":\n\tHP: Average\n\tAttack: Average\n\tDefense: Average\n\tRanged Attack: High\n\tRanged Defense: Average\n\tSpeed: High";
    }
    
    private String allRounderStatSpread()
    {
        return className + ":\n\tHP: Average\n\tAttack: Average\n\tDefense: Average\n\tRanged Attack: Average\n\tRanged Defense: Average\n\tSpeed: Average";
    }
    
    /*
            < 35 is very low
            >= 35 and <= 50 is low 
            >= 50 and <= 65 is average
            > 65 and <= 75 is high
            > 75 is very high
    */
}
