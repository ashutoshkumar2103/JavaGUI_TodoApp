package listtodo;
//Import the required libraries for GUI
import javax.swing.*;   // For Swing components like JFrame, JButton, JTextField, etc.
import java.awt.*;      // For layouts like BorderLayout, FlowLayout, etc.
import java.awt.event.*; // For handling button click events

//Main class that extends JFrame (this makes our class a window)
public class ToDoApp extends JFrame {

 // Declare components (like text box, buttons, and list)
 DefaultListModel<String> listModel; // To store tasks (like an ArrayList but for JList)
 JList<String> taskList;             // To display tasks on screen
 JTextField taskInput;               // To type a new task
 JButton addButton, deleteButton;    // Buttons for adding and deleting tasks

 // Constructor — runs automatically when the object is created
 public ToDoApp() {

     // Set window title
     setTitle("Simple To-Do List");

     // Set window size (width x height)
     setSize(400, 400);

     // Close the program when you click 'X'
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // Center the window on the screen
     setLocationRelativeTo(null);

     // Initialize the components
     listModel = new DefaultListModel<>(); // Creates an empty list model to hold tasks
     taskList = new JList<>(listModel);    // Create a task list using the model
     taskInput = new JTextField();         // Text field to type new tasks
     addButton = new JButton("Add");       // Button to add a task
     deleteButton = new JButton("Delete"); // Button to delete selected task

     // Create a panel at the bottom to hold text field and buttons
     JPanel bottomPanel = new JPanel();
     bottomPanel.setLayout(new BorderLayout()); // Use BorderLayout for proper positioning

     // Add the text field to the left (center area of the layout)
     bottomPanel.add(taskInput, BorderLayout.CENTER);

     // Create another small panel just for buttons
     JPanel buttonPanel = new JPanel();
     buttonPanel.add(addButton);   // Add 'Add' button to button panel
     buttonPanel.add(deleteButton); // Add 'Delete' button to button panel

     // Add the button panel to the right side of bottomPanel
     bottomPanel.add(buttonPanel, BorderLayout.EAST);

     // Add scrollable task list to the main window (center area)
     add(new JScrollPane(taskList), BorderLayout.CENTER);

     // Add the bottom panel (which has input and buttons) to the bottom of the window
     add(bottomPanel, BorderLayout.SOUTH);

     // --- ADD BUTTON ACTION ---
     // This part runs when you click "Add"
     addButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             // Get the text typed in the input box
             String task = taskInput.getText().trim();

             // Check if it's not empty
             if (!task.isEmpty()) {
                 // Add task to the list
                 listModel.addElement(task);

                 // Clear the input box
                 taskInput.setText("");
             } else {
                 // Show a message if the input is empty
                 JOptionPane.showMessageDialog(ToDoApp.this, "Please enter a task.");
             }
         }
     });

     // --- DELETE BUTTON ACTION ---
     // This part runs when you click "Delete"
     deleteButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             // Get the selected task index (position)
             int index = taskList.getSelectedIndex();

             // Check if something is selected
             if (index != -1) {
                 // Remove selected task from the list
                 listModel.remove(index);
             } else {
                 // Show message if nothing is selected
                 JOptionPane.showMessageDialog(ToDoApp.this, "Select a task to delete.");
             }
         }
     });
 }

 // --- MAIN METHOD ---
 // This is where the program starts
 public static void main(String[] args) {

     // This ensures GUI runs smoothly on the main Swing thread
     SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
             // Create and show the To-Do List window
             new ToDoApp().setVisible(true);
         }
     });
 }
}
