public class Driver {
    public static void main(String[] args) 
    {
        // Use singleton to get instance of window class
        Window window = Window.getInstance();

        //set the window to visible
        window.setVisible(true);
    }
}
