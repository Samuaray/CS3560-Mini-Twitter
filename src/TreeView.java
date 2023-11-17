 import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.*;
public class TreeView {
    private HashMap<String, NewEnt> users;
    private HashMap<String, NewEnt> groups;
    private HashMap<String, UserView> userViews; // Map to track UserView instances
    private ArrayList<String> groupsID;
    private ArrayList<String> usersID;
    private String temp;
    private JTree tree;
    private DefaultMutableTreeNode nd;
    private DefaultMutableTreeNode root;
    

    
    public TreeView(){
        groupsID = new ArrayList<>();
        usersID = new ArrayList<>();
        users = new HashMap<>();
        userViews = new HashMap<>(); // Initialize the map
        groups = new HashMap<>();
        root = new DefaultMutableTreeNode("Root");
        tree = new JTree(root); 
        tree.setBounds(0,0,500,500);
     
       
        //add mouse click listner 
        tree.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me){
                nd = jtreeMouseClicked(me);   
                if(jtreeMouseClicked(me)!= null)
                { 
                temp = jtreeMouseClicked(me).toString();
                }
            }
        });
    }
    

    //Return tree reference
   public JTree getTree()
   {
    return tree;
   }


   //Find the User within the tree
   public User findUser(String str)
   {
    return (User)users.get(str);
   }


    //Adding NewEnt into the tree with factors like if it's group or not, and the node user clicked on
     public void addNewEnt(NewEnt part)
     {
        if(!usersID.contains(part.getDisplayName()) && !groupsID.contains(part.getDisplayName()))
        { //checks if newuser/newgroup already exists 
            if(part.isGroup() && (nd==null || nd==root))
            {  //checks if the selected node is a user or the root, if it is then the new group is added as a child to the root
                groups.put(part.getDisplayName(), part);
                groupsID.add(part.getDisplayName());
                DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
                root = (DefaultMutableTreeNode)model.getRoot();
                root.add(new DefaultMutableTreeNode(part.getDisplayName()));
                model.reload();
            }

        else if(part.isGroup() && groupsID.contains(nd.toString()) && nd != null)
        { //if the selected node is a group, the new group is added as a child
            groups.put(part.getDisplayName(), part);
            groupsID.add(part.getDisplayName());
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            root = (DefaultMutableTreeNode)model.getRoot();
            nd.add(new DefaultMutableTreeNode(part.getDisplayName()));
            model.reload();
        }

        if (part.isUser() && (nd == null || nd==root))
        { //if the node is a user and the selected node is a user or root, the new user will be a root child
            users.put(part.getDisplayName(), part);
            usersID.add(part.getDisplayName());
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            root = (DefaultMutableTreeNode)model.getRoot();
            root.add(new DefaultMutableTreeNode(part.getDisplayName()));
            model.reload();
        }

        else if (part.isUser() && groupsID.contains(nd.toString()))
        { //if the node is a user and the selected node is a group, the new user will be a group child
            users.put(part.getDisplayName(), part);
            usersID.add(part.getDisplayName());
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            root = (DefaultMutableTreeNode)model.getRoot();
            nd.add(new DefaultMutableTreeNode(part.getDisplayName()));
            model.reload();
        }
    }
 }
    
   public DefaultMutableTreeNode jtreeMouseClicked(MouseEvent evt)
   {
        TreePath tp = tree.getPathForLocation(evt.getX(), evt.getY());
        if(tp==null){
            return null;
        }
        return (DefaultMutableTreeNode)(tp.getLastPathComponent());
   }
   
   //Opens User Interface 
   public void openUser() 
   {
    if (temp != null && users.get(temp) != null) {
        User user = (User) users.get(temp);

        if (!userViews.containsKey(temp))
         {
            // If no UserView exists for this user, or it's closed, create a new one
            UserView userView = new UserView(user);
            userViews.put(temp, userView); // Store the UserView in the map
            userView.setTitle(user.getDisplayName());
            userView.setVisible(true);
        } 
        else
        {
            // If a UserView already exists, bring it to the front
            UserView existingView = userViews.get(temp);
            existingView.setVisible(true);
            existingView.toFront();
            existingView.requestFocus();
        }
    }
}
   
   //Count number of Users
   public int getTotalUsers()
   {
    NewEntVistor visit = new NewEntVistor();
    int total = 0;
    for(int i =0; i<usersID.size(); i++)
    {
        User temp = (User)users.get(usersID.get(i));
        total += temp.accept(visit);
    }
    return total;

   }

   //Return the total amount of groups in tree
   public int getTotalGroups()
   {
    NewEntVistor visit = new NewEntVistor();
    int total = 0;
    for(int i =0; i<groupsID.size(); i++)
    {
        Group temp = (Group)groups.get(groupsID.get(i));
        total += temp.accept(visit);
    }
    return total;

   }
   
}

