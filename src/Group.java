import java.util.*;
public class Group extends NewEnt  {
    private ArrayList<NewEnt> list;
    private static int group;
    private String name;
    private static int count;
    private long time; //Assignment 3
    public Group(String name)
    {
    this.name = name;
    list = new ArrayList<NewEnt>();
    count++;
    time = System.currentTimeMillis(); // Assignment 3
    System.out.println(name + " created - " + time);
    }

    public void add(NewEnt user)
    {
        //Adds any NewEnt
        if(!list.contains(user)){
          list.add(user);
        }
    }

    public static int getCount()
    {
        //getter method number of groups
        return count;
    }
    public String getDisplayName()
    {
        //Getter method for name
        return name;
    }

    public int accept(Visitor visitor)
    {
        //Visitor Pattern
        return visitor.visit(this);
    }

    public boolean isGroup()
    {
        //Checks if Group or not
        return true;
    }

    public boolean isUser()
    {
        //Returns if User or not
        return false;
    }
}   