public class NewEnt {
    public String getDisplayName(){
        return "";
    }
    public boolean isGroup(){
        return true;
    }  
    
    public boolean isUser(){
        return false;
    }  
    
    public boolean addNewEntry(NewEnt newEntry){
        throw new SecurityException ("Error: Not Possible");
    }
}

