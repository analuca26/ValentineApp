package ValentineProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ValentineApp extends JFrame {

    private JPanel panel;
    private boolean showImage = false;
    private ImageIcon imageIcon;
    
    public ValentineApp() {
        setTitle("Valentine's Day Surprise");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (showImage && imageIcon != null) {
                    if (imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                        g.drawImage(imageIcon.getImage(), 50, 50, panel.getWidth() - 100, panel.getHeight() - 100, this);
                    } else {
                        g.setColor(Color.BLACK);
                        g.drawString("Image failed to load!", panel.getWidth() / 2 - 60, panel.getHeight() / 2);
                    }
                }
            }
        };

        panel.setBackground(Color.PINK);
        panel.setLayout(null);

        JLabel label = new JLabel("Will you be my Valentine?");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(130, 50, 300, 30);
        panel.add(label);

        JButton yesButton = new JButton("Yes");
        yesButton.setBounds(120, 150, 100, 30);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImageScreen();
            }
        });

        JButton ofCourseButton = new JButton("Of course");
        ofCourseButton.setBounds(240, 150, 120, 30);
        ofCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImageScreen();
            }
        });

        panel.add(yesButton);
        panel.add(ofCourseButton);

        add(panel);
        setVisible(true);
    }

    private void showImageScreen() {
        showImage = true;
        
        // Try loading the image
        String imagePath = "valentine.jpeg"; // Change this if your image is in a different location
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            imageIcon = new ImageIcon(imagePath);
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
        } else {
            System.out.println("Error: Image not found at " + imageFile.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Image not found! Make sure valentine.jpeg is in the correct folder.");
        }
    }

    public static void main(String[] args) {
        new ValentineApp();
    }
}
