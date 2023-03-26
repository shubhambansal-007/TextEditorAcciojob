import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener
{
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor()
    {
        frame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        newFile = new JMenuItem("New Window");
        newFile.addActionListener(this);
        openFile = new JMenuItem("Open File");
        openFile.addActionListener(this);
        saveFile = new JMenuItem("Save File");
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        close = new JMenuItem("Close");
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);
        frame.setBounds(100, 100, 400, 400);
        frame.setTitle("TextEditor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == cut) textArea.cut();

        if(actionEvent.getSource() == copy)textArea.copy();

        if(actionEvent.getSource() == paste)textArea.paste();

        if(actionEvent.getSource() == selectAll)textArea.selectAll();

        if(actionEvent.getSource() == close)System.exit(0);

        if(actionEvent.getSource() == openFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";

                    while((intermediate = bufferedReader.readLine()) != null)
                    {
                        output += intermediate + "\n";
                    }

                    textArea.setText(output);
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == saveFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                String filePath = file.getPath();

                try{
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == newFile)
        {
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}