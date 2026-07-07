import java.awt.*;
import java.util.Scanner;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class App implements Runnable{
    static String clock;
    static Timer timer2;
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        App main = new App();
        Thread threadApp = new Thread(main);
        threadApp.start();
        mainApp();



        Scanner scan = new Scanner(System.in);
        System.out.println("CLI Alarm Clock // 24 Hour Based");

        try {
            System.out.print("Enter the hour you desire: ");
            int h = scan.nextInt();
            System.out.print("Enter the minute you desire: ");
            int m = scan.nextInt();
            scan.close();

            clockThread clock = new clockThread(h, m);
            Thread thread = new Thread(clock);
            thread.start();


        } catch (Exception e) {
            System.out.println("The input is supposed to be an integer only.");
        }
    }

    public void run() {
        while (true) {
            try {
                clockTime clockReturn = new clockTime();
                clock = clockReturn.currentTime();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }    
    }
    public static void alarmScreen() {
        JFrame alarmFrame = new JFrame();
        Container contentpane = alarmFrame.getContentPane();

        alarmFrame.setSize(535, 299);
        alarmFrame.setResizable(false);
        alarmFrame.setLocationRelativeTo(null);
        alarmFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentpane.setBackground(Color.BLACK);
        contentpane.setLayout(null);

        // Custom Fonts
        Font geistMono10px = null;
        Font geistMono13px = null;

        try {
            geistMono10px = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(10f);
            geistMono13px = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(13f);

            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(geistMono10px);
            graphicsEnvironment.registerFont(geistMono13px);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Create title label
        JLabel title = new JLabel("alarmify");
        title.setFont(geistMono10px);
        title.setForeground(new Color(235, 235, 235));
        title.setBounds(11, 10, 185, 18);

        // Alarm label
        JLabel alarmMessage = new JLabel("*alarm sound*");
        alarmMessage.setFont(geistMono10px);
        alarmMessage.setForeground(Color.WHITE);
        alarmMessage.setBounds(229, 143, 78, 13);

        // Continue button
        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(geistMono10px);
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(Color.BLACK);
        continueButton.setBounds(440, 240, 90, 23);

        continueButton.addActionListener(e -> {
            mainApp();
        });

        contentpane.add(title);
        contentpane.add(alarmMessage);
        contentpane.add(continueButton);

        alarmFrame.setVisible(true);
    }

    public static void mainApp() {
        // Frame & Container => Container keeps all the elements within the frame
        JFrame frame = new JFrame();
        Container contentpane = frame.getContentPane();

        frame.setSize(535, 299);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentpane.setBackground(new Color(0, 0, 0));
        contentpane.setLayout(null);

        // Custom Fonts
        Font geistMono10px = null;
        Font geistMono13px = null;
        Font geistMono64px = null;

        try {
            geistMono10px = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(10f);
            geistMono13px = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(13f);
            geistMono64px = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(64f);

            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(geistMono10px);
            graphicsEnvironment.registerFont(geistMono13px);
            graphicsEnvironment.registerFont(geistMono64px);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Create title label
        JLabel title = new JLabel("alarmify");
        title.setFont(geistMono10px);
        title.setForeground(new Color(235, 235, 235));
        title.setBounds(11, 10, 185, 13);

        // Create current time label
        JLabel currentTime = new JLabel("current time:");
        currentTime.setFont(geistMono10px);
        currentTime.setForeground(new Color(235, 235, 235));
        currentTime.setBounds(229, 103, 110, 18);

        // Create clock
        JLabel c = new JLabel(clock);
        c.setFont(geistMono64px);
        c.setForeground(new Color(235, 235, 235));
        c.setBounds(172, 105, 500, 83);

        Timer timer = new Timer(1000, e -> {
            c.setText(clock);
        });

        timer.start();

        // Create Label
        JLabel setAlarm = new JLabel("set alarm:");
        setAlarm.setFont(geistMono10px);
        setAlarm.setForeground(new Color(235, 235, 235));
        setAlarm.setBounds(11, 218, 60, 13);

        // Create text field
        JTextField input = new JTextField();
        input.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52), 1));
        input.setBackground(Color.BLACK);
        input.setForeground(Color.WHITE);
        input.setBounds(11, 235, 206, 23);

        // Create start button
        JButton startAlarm = new JButton("START");
        startAlarm.setFont(geistMono10px);
        startAlarm.setForeground(Color.BLACK);
        startAlarm.setBackground(Color.WHITE);
        startAlarm.setBounds(224, 235, 90, 23);

        // startAlarm event => Button functionality
        startAlarm.addActionListener(e -> {
            String userInput = input.getText();
            String[] splitInput = userInput.split(":");
            try {
                // Convert to integer
                int HH = Integer.parseInt(splitInput[0]);
                int MM = Integer.parseInt(splitInput[1]);

                Font geistMono10 = null;
                Font geistMono13 = null;
                Font geistMono64 = null;

                try {
                    geistMono10 = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(10f);
                    geistMono13 = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(23f);
                    geistMono64 = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(64f);

                    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    graphicsEnvironment.registerFont(geistMono10);
                    graphicsEnvironment.registerFont(geistMono13);
                    graphicsEnvironment.registerFont(geistMono64);
                } catch (IOException | FontFormatException ex2) {
                    ex2.printStackTrace();
                }

                setAlarm.setVisible(false);
                input.setVisible(false);
                startAlarm.setVisible(false);
                
                JLabel alarmSetFor = new JLabel("alarm set for:");
                alarmSetFor.setFont(geistMono10);
                alarmSetFor.setForeground(Color.WHITE);
                alarmSetFor.setBounds(342, 103, 118, 13);
                
                JLabel alarmTime = new JLabel(HH + ":" + MM);
                alarmTime.setFont(geistMono64);
                alarmTime.setForeground(Color.WHITE);
                alarmTime.setBounds(288, 105, 500, 83);

                JButton cancelAlarm = new JButton("CANCEL");
                cancelAlarm.setFont(geistMono10);
                cancelAlarm.setBackground(Color.WHITE);
                cancelAlarm.setForeground(Color.BLACK);
                cancelAlarm.setBounds(440, 240, 90, 23);

                cancelAlarm.addActionListener(k -> {
                    mainApp();
                });

                timer2 = new Timer(1000, l -> {
                    String[] clockSplit = clock.split(":");
                    int cH = Integer.parseInt(clockSplit[0]);
                    int cM = Integer.parseInt(clockSplit[1]);

                    if (cH == HH && cM == MM) {
                        alarmScreen();
                        timer2.stop();
                    }
                });

                timer2.start();
                
                currentTime.setBounds(115, 103, 110, 18);
                c.setBounds(58, 105, 500, 83);

                contentpane.add(alarmSetFor);
                contentpane.add(alarmTime);
                contentpane.add(cancelAlarm);

                contentpane.repaint();
                contentpane.revalidate();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        contentpane.add(title);
        contentpane.add(currentTime);
        contentpane.add(c);
        contentpane.add(setAlarm);
        contentpane.add(input);
        contentpane.add(startAlarm);

        frame.setVisible(true);
    }
}