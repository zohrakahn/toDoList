import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false; }
    public String getDescription() {
        return description; }
    public boolean isDone() {
        return isDone; }
    public void markAsDone() {
        isDone = true; } }
class ToDoList {
    private ArrayList<Task> tasks;
    public ToDoList() {
        tasks = new ArrayList<>(); }
    public void addTask(String description) {
        Task task = new Task(description); tasks.add(task); }
    public void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index); task.markAsDone(); } }
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) { tasks.remove(index); } }
    public Object[][] getTaskData() { Object[][] data = new Object[tasks.size()][2];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            data[i][0] = task.getDescription();
            data[i][1] = task.isDone() ? "Done" : "Not Done"; }
        return data; } }
public class ToDoListApp { private JFrame frame; private ToDoList toDoList; private JTable table; public ToDoListApp() { frame = new JFrame("To-Do List"); toDoList = new ToDoList(); initialize(); }
    private void initialize() { frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setSize(400, 300); JPanel panel = new JPanel(new BorderLayout()); String[] columnNames = {"Description", "Status"}; Object[][] data = {}; table = new JTable(data, columnNames); JScrollPane scrollPane = new JScrollPane(table); panel.add(scrollPane, BorderLayout.CENTER); JPanel buttonPanel = new JPanel(new FlowLayout()); JButton addButton = new JButton("Add Task"); JButton doneButton = new JButton("Mark as Done"); JButton removeButton = new JButton("Remove Task"); addButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { String description = JOptionPane.showInputDialog("Enter task description:"); toDoList.addTask(description); refreshTable(); } }); doneButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { int selectedRow = table.getSelectedRow(); if (selectedRow >= 0) { toDoList.markTaskAsDone(selectedRow); refreshTable(); } else { JOptionPane.showMessageDialog(frame, "Select a task to mark as done."); } } }); removeButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {

    int selectedRow = table.getSelectedRow(); if (selectedRow >= 0) { toDoList.removeTask(selectedRow); refreshTable(); } else { JOptionPane.showMessageDialog(frame, "Select a task to remove."); } } }); buttonPanel.add(addButton); buttonPanel.add(doneButton); buttonPanel.add(removeButton); panel.add(buttonPanel, BorderLayout.SOUTH); frame.getContentPane().add(panel); frame.setVisible(true); } private void refreshTable() { table.setModel(new DefaultTableModel(toDoList.getTaskData(), new String[]{"Description", "Status"})); } public static void main(String[] args) { SwingUtilities.invokeLater(new Runnable() { public void run() { new ToDoListApp(); } }); } }
