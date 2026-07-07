import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class App implements Runnable{
    static String clock;
    static Timer timer2;
    public static void main(String[] args) throws Exception {
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

    public static void mainApp() {
        // Frame & Container => Container keeps all the elements within the frame
        JFrame frame = new JFrame();
        Container contentpane = frame.getContentPane();

        frame.setSize(477, 849);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentpane.setBackground(new Color(0, 0, 0));
        contentpane.setLayout(null);

        // Custom Fonts
        Font geistMono14px = null;
        Font geistMono24px = null;
        Font geistMono128px = null;

        try {
            geistMono14px = Font.createFont(Font.TRUETYPE_FONT, new File("alarm/font/geistmono.ttf")).deriveFont(14f);
            geistMono24px = Font.createFont(Font.TRUETYPE_FONT, new File("alarm/font/geistmono.ttf")).deriveFont(24f);
            geistMono128px = Font.createFont(Font.TRUETYPE_FONT, new File("alarm/font/geistmono.ttf")).deriveFont(128f);

            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(geistMono14px);
            graphicsEnvironment.registerFont(geistMono24px);
            graphicsEnvironment.registerFont(geistMono128px);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Create title label
        JLabel title = new JLabel("minimalistic prototype");
        title.setFont(geistMono14px);
        title.setForeground(new Color(235, 235, 235));
        title.setBounds(11, 10, 185, 18);

        // Create current time label
        JLabel currentTime = new JLabel("CURRENT TIME:");
        currentTime.setFont(geistMono14px);
        currentTime.setForeground(new Color(235, 235, 235));
        currentTime.setBounds(184, 66, 110, 18);

        // Create clock
        JLabel c = new JLabel(clock);
        c.setFont(geistMono128px);
        c.setForeground(new Color(235, 235, 235));
        c.setBounds(47, 58, 500, 166);

        Timer timer = new Timer(1000, e -> {
            c.setText(clock);
        });

        timer.start();

        // Create Label
        JLabel setAlarm = new JLabel("SET ALARM:");
        setAlarm.setFont(geistMono14px);
        setAlarm.setForeground(new Color(235, 235, 235));
        setAlarm.setBounds(197, 635, 84, 18);

        // Create text field
        JTextField input = new JTextField();
        input.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52), 1));
        input.setBackground(Color.BLACK);
        input.setForeground(Color.WHITE);
        input.setBounds(30, 675, 417, 46);

        // Create start button
        JButton startAlarm = new JButton("start");
        startAlarm.setFont(geistMono24px);
        startAlarm.setForeground(Color.BLACK);
        startAlarm.setBackground(Color.WHITE);
        startAlarm.setBounds(26, 745, 179, 46);

        // startAlarm event => Button functionality
        startAlarm.addActionListener(e -> {
            String userInput = input.getText();
            String[] splitInput = userInput.split(":");
            try {
                // Convert to integer
                int HH = Integer.parseInt(splitInput[0]);
                int MM = Integer.parseInt(splitInput[1]);

                Font geistMono14 = null;
                Font geistMono24 = null;
                Font geistMono128 = null;

                try {
                    geistMono14 = Font.createFont(Font.TRUETYPE_FONT, new File("alarm/font/geistmono.ttf")).deriveFont(14f);
                    geistMono24 = Font.createFont(Font.TRUETYPE_FONT, new File("alarm/font/geistmono.ttf")).deriveFont(24f);
                    geistMono128 = Font.createFont(Font.TRUETYPE_FONT, new File("alarm/font/geistmono.ttf")).deriveFont(128f);

                    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    graphicsEnvironment.registerFont(geistMono14);
                    graphicsEnvironment.registerFont(geistMono24);
                    graphicsEnvironment.registerFont(geistMono128);
                } catch (IOException | FontFormatException ex2) {
                    ex2.printStackTrace();
                }

                setAlarm.setVisible(false);
                input.setVisible(false);
                startAlarm.setVisible(false);
                
                JLabel alarmSetFor = new JLabel("ALARM SET FOR:");
                alarmSetFor.setFont(geistMono14);
                alarmSetFor.setForeground(Color.WHITE);
                alarmSetFor.setBounds(197, 604, 118, 18);
                
                JLabel alarmTime = new JLabel(HH + ":" + MM);
                alarmTime.setFont(geistMono128);
                alarmTime.setForeground(Color.WHITE);
                alarmTime.setBounds(47, 598, 500, 166);

                JButton cancelAlarm = new JButton("cancel");
                cancelAlarm.setFont(geistMono24);
                cancelAlarm.setBackground(Color.WHITE);
                cancelAlarm.setForeground(Color.BLACK);
                cancelAlarm.setBounds(149, 745, 179, 46);

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

    public static void alarmScreen() {
        JFrame alarmFrame = new JFrame();
        Container contentpane = alarmFrame.getContentPane();

        alarmFrame.setSize(477, 849);
        alarmFrame.setResizable(false);
        alarmFrame.setLocationRelativeTo(null);
        alarmFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentpane.setBackground(Color.BLACK);
        contentpane.setLayout(null);

        // Custom Fonts
        Font geistMono14px = null;
        Font geistMono24px = null;

        try {
            geistMono14px = Font.createFont(Font.TRUETYPE_FONT, new File("alarm/font/geistmono.ttf")).deriveFont(14f);
            geistMono24px = Font.createFont(Font.TRUETYPE_FONT, new File("alarm/font/geistmono.ttf")).deriveFont(24f);

            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(geistMono14px);
            graphicsEnvironment.registerFont(geistMono24px);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Create title label
        JLabel title = new JLabel("minimalistic prototype");
        title.setFont(geistMono14px);
        title.setForeground(new Color(235, 235, 235));
        title.setBounds(11, 10, 185, 18);

        // Alarm label
        JLabel alarmMessage = new JLabel("ALARM");
        alarmMessage.setFont(geistMono14px);
        alarmMessage.setForeground(Color.WHITE);
        alarmMessage.setBounds(218, 438, 42, 18);

        // Continue button
        JButton continueButton = new JButton("continue");
        continueButton.setFont(geistMono24px);
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(Color.BLACK);
        continueButton.setBounds(149, 745, 179, 46);

        continueButton.addActionListener(e -> {
            mainApp();
        });

        contentpane.add(title);
        contentpane.add(alarmMessage);
        contentpane.add(continueButton);

        alarmFrame.setVisible(true);
    }

}