import javax.swing.*;
void main() {
    JFrame frame = new JFrame("Software Project Registration Form");
    frame.setSize(500, 450);
    frame.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE);

    frame.add(new ProjectFormPanel());
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
