import java.util.*;
public class Group extends NewEnt  {
    private ArrayList<NewEnt> list;
    private static int group;
    private String name;
    private static int count;

    public Group(String name){
    this.name = name;
    list = new ArrayList<NewEnt>();
    count++;
    }
    public void add(NewEnt user){
        //Adds any NewEnt
        if(!list.contains(user)){
          list.add(user);
        }
    }
    public static int getCount(){
        //getter method
        return count;
    }
    public String getDisplayName(){
        //Getter method for name
        return name;
    }
    public int accept(Visitor visitor){
        //Visitor Pattern
        return visitor.visit(this);
    }
    public boolean isGroup(){
        //Checks if Group or not
        return true;
    }
    public boolean isUser(){
        //Returns if User or not
        return false;
    }
}   