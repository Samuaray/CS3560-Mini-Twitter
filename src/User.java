import java.util.*;
public class User extends NewEnt implements Observer, Observable, Visitable
{
    private String userID;
    private static int users;
    private static ArrayList<String> usersList = new ArrayList<>();
    private ArrayList<Observer> followers;
    private ArrayList<Observer> following;
    private static int tweets;
    private static int goodTweets;
    private UserView userView;


    public User(String userID)
    {
            this.userID = userID;
            users++;
            usersList.add(userID);
            followers = new ArrayList<>();
            following = new ArrayList<>();
            //tweets = 0;
            goodTweets = 0;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

   

    public static int getTweets() {
        return tweets;
    }

    public static double getGoodTweetsPercentage() {
        if (tweets == 0) {
            return 0.0;
        }
        return 100 * (double) goodTweets / tweets;
    }

    public void receive(String str)
     {
        // Method to receive a message (update from an observable)
        if (userView != null)
         {
            System.err.println("user receive is working");
            userView.receive(str);
        }
    }

    public String getUserID()
    {
        return userID;
    }

    public ArrayList<Observer> getFollowing() 
    {
        return following;
    }

    public String getDisplayName()
    {
        return userID;
    }

     //Return whether or not object is Group object
    public boolean isGroup()
     {
        return false;
    }

    //Return whether or not object is User object
    public boolean isUser()
    {
        return true;
    }
    
    public static boolean contains(String userID) 
    {
        return usersList.contains(userID);
    }

    public ArrayList<Observer> getFollowers() 
    {
        return followers;
    }

    
    

     public void updateFollower(Observer follower) {
        followers.add(follower);
    }

    /* 
    public void follow(Observer follow) {
        following.add(follow);
       // if (follow instanceof User) {
            ((User) follow).updateFollower(this);  //issue found here!!!!, follow becomes null, followers receiving news update not working
       // }
       // follow.updateFollower(follow);
    }

*/
public void follow(Observer follow) {
    if (follow != null) {
        following.add(follow);
        ((User) follow).updateFollower(this);
    } else {
        System.err.println("Attempted to follow a null observer.");
    }
}

    //post method 
    public void post(String tweet) {
        // Notifies all followers about the new tweet
        System.out.println("beginning of post is working");
        for(int x =0; x<followers.size(); x++){
            followers.get(x).receive(this.getDisplayName()+ ": "+ tweet);
        }
        if (tweet.contains("Nice") || tweet.contains("nice") || tweet.contains("Good") || tweet.contains("godd")) { //Conditon for positive tweet is that "Good" or "Nice" need to be present
            goodTweets++;
        }
         tweets++;
    }

 

    //implementation of visitor
    public int accept(Visitor visitor){
        return visitor.visit(this);
    }
    
}













