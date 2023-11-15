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
            tweets = 0;
            goodTweets = 0;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

   

    public int getTweets() {
        return tweets;
    }

    public double getGoodTweetsPercentage() {
        if (tweets == 0) {
            return 0.0;
        }
        return 100 * (double) goodTweets / tweets;
    }

    public void receive(String str) {
        // Method to receive a message (update from an observable)
        if (userView != null) {
            System.err.println("user receive is working");
            userView.receive(str);
        }
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<Observer> getFollowing() {
        return following;
    }

    public String getDisplayName(){
        return userID;
    }

     //Return whether or not object is Group object
     public boolean isGroup(){
        return false;
    }
        //Return whether or not object is User object
    public boolean isUser(){
        return true;
    }
    
    public static boolean contains(String userID) {
        // This method should probably not be static and should refer to a user's followers or following
        // However, without the complete context, it's hard to say for sure.
        return usersList.contains(userID);
    }

    public ArrayList<Observer> getFollowers() {
        return followers;
    }

    

    public void post(String tweet) {
        // Notifies all followers about the new tweet
        System.out.println("beginning of post is working");
        for (Observer follower : followers) {
            System.out.println("important part of post is working");
            follower.update(this, tweet);
        }
        if (tweet.contains("Nice") || tweet.contains("Good")) {
            goodTweets++;
        }
        tweets++;
    }

    public void updateFollower(Observer follower) {
        followers.add(follower);
    }

    public void follow(Observer follow) {
        following.add(follow);
        if (follow instanceof User) {
            ((User) follow).updateFollower(this);
        }
    }

    // Overriding the Observer update method
    @Override
public void update(Observable o, Object arg) {
    if (arg instanceof String) {
        String tweet = (String) arg;
        // Handle the received tweet
        receive(tweet);
    }
}


    private List<Observer> observers = new ArrayList<>();

    // Observable methods
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this, null); // null can be replaced with a relevant message or data object.
        }
    }

     public void acceptObserver(Observer observer) {
        // Add the observer to your list of observers
        followers.add(observer);
    }

    public int accept(Visitor visitor){
        return visitor.visit(this);
    }
    
}













