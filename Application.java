import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Application extends JDialog {
    private static final String PATHNAME = "palavras.txt";
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField input;
    private JList list;
    private TrieTree trieTree;
    private DefaultListModel<String> model;
    private List<String> suggestions;

    public Application() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        trieTree = new TrieTree();
        model = new DefaultListModel<>();
        fillTree();

        input.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {}

            public void removeUpdate(DocumentEvent e) {
                search();
            }
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            public void search() {
                model.removeAllElements();
                if (input.getText().length() > 0) {
                    suggestions = trieTree.getSuggestions(input.getText());
                    for (String suggestion : suggestions) {
                        model.addElement(suggestion);
                    }
                    list.setModel(model);
                }
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void fillTree() {
        try {
            Scanner scanner = new Scanner(new File(PATHNAME));

            while (scanner.hasNextLine()) {
                trieTree.insert(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application dialog = new Application();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
