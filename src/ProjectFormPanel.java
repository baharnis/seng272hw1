import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormPanel extends JPanel {
    private JTextField txtProjectName = new JTextField(30);
    private JTextField txtTeamLeader = new JTextField(30);
    private JComboBox<String> comboTeamSize = new JComboBox<>(new String[]{"Choose", "1-3", "4-6", "7-10", "10+"});
    private JComboBox<String> comboProjectType = new JComboBox<>(new String[]{"Choose", "Web", "Mobile", "Desktop", "API"});
    private JTextField txtStartDate = new JTextField(30);
    private JButton btnSave = new JButton("Save");
    private JButton btnClear = new JButton("Clear");

    public ProjectFormPanel() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(new JLabel("Project Name:"));
        add(txtProjectName);
        add(new JLabel("Team Leader:"));
        add(txtTeamLeader);
        add(new JLabel("Team Size:"));
        add(comboTeamSize);
        add(new JLabel("Project Type:"));
        add(comboProjectType);
        add(new JLabel("Start Date (DD/MM/YYYY):"));
        add(txtStartDate);
        add(btnSave);
        add(btnClear);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        btnClear.addActionListener(e -> clearFields());
    }
        private void saveToFile() {

            if (txtProjectName.getText().isEmpty() || txtTeamLeader.getText().isEmpty() ||
                    txtStartDate.getText().isEmpty() || comboTeamSize.getSelectedIndex() == 0 ||
                    comboProjectType.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true))) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                String now = dtf.format(LocalDateTime.now());

                writer.write("=== Project Entry ===\n");
                writer.write("Project Name : " + txtProjectName.getText() + "\n");
                writer.write("Team Leader  : " + txtTeamLeader.getText() + "\n");
                writer.write("Team Size    : " + comboTeamSize.getSelectedItem() + "\n");
                writer.write("Project Type : " + comboProjectType.getSelectedItem() + "\n");
                writer.write("Start Date   : " + txtStartDate.getText() + "\n");
                writer.write("Record Time  : " + now + "\n");
                writer.write("======\n\n");

                JOptionPane.showMessageDialog(this, "Project saved successfully!");
                clearFields();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "File write error: " + ex.getMessage());
            }
        }

        private void clearFields() {

            txtProjectName.setText("");
            txtTeamLeader.setText("");
            txtStartDate.setText("");
            comboTeamSize.setSelectedIndex(0);
            comboProjectType.setSelectedIndex(0);
        }
    }
