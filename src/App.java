import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

public class App implements Runnable{
    // Instance Fields
    private static String clock;
    private static Timer timer2;
    private static Timer timer3;

    private static int hour = 0;
    private static int minute = 0;
    private static int second = 0;

    private static int countdownHour = 0;
    private static int countdownMinute = 0;
    private static int countdownSecond = 0;

    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        App main = new App();
        Thread threadApp = new Thread(main);
        threadApp.start();
        menuMethod();
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

    public static void clockMethod() {
        JFrame frame = new JFrame();
        Container contentpane = frame.getContentPane();

        frame.setTitle("+-alarmify-+");
        frame.setSize(535, 299);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentpane.setBackground(Color.BLACK);
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

        // title
        JLabel title = new JLabel("alarmify");
        title.setFont(geistMono10px);
        title.setForeground(Color.WHITE);
        title.setBounds(11, 10, 185, 18);

        // Stopwatch icon & button
        ImageIcon menuIcon = new ImageIcon("img/menuicon.png");
        JButton menuButton = new JButton(menuIcon);
        menuButton.setBorderPainted(false);
        menuButton.setBackground(null);
        menuButton.setBounds(508, 8, 17, 17);

        menuButton.addActionListener(e -> {
            frame.setVisible(false);
            menuMethod();
        });

        // Create current time label
        JLabel currentTime = new JLabel("current time:");
        currentTime.setFont(geistMono10px);
        currentTime.setForeground(new Color(235, 235, 235));
        currentTime.setBounds(229, 99, 110, 18);

        // Create clock
        JLabel c = new JLabel(clock);
        c.setFont(geistMono64px);
        c.setForeground(new Color(235, 235, 235));
        c.setBounds(114, 105, 500, 83);

        Timer timer = new Timer(100, e -> {
            c.setText(clock);
        });

        timer.start();

        contentpane.add(title);
        contentpane.add(menuButton);
        contentpane.add(currentTime);
        contentpane.add(c);

        frame.setVisible(true);
    }

    public static void menuMethod() {
        JFrame frame = new JFrame();
        Container contentpane = frame.getContentPane();

        frame.setTitle("+-alarmify-+");
        frame.setSize(535, 299);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        // title label
        JLabel title = new JLabel("alarmify");
        title.setFont(geistMono10px);
        title.setForeground(Color.WHITE);
        title.setBounds(11, 10, 185, 18);
        
        // clock button
        JButton clockButton = new JButton("clock");
        clockButton.setFont(geistMono10px);
        clockButton.setForeground(Color.WHITE);
        clockButton.setBackground(Color.BLACK);
        clockButton.setBorderPainted(false);
        clockButton.setBounds(222, 79, 92, 22);

        // clock action
        clockButton.addActionListener(e -> {
            frame.dispose();
            frame.setVisible(false); // good practice -- UU

            clockMethod();
        });

        // alarm button
        JButton alarmButton = new JButton("alarm");
        alarmButton.setFont(geistMono10px);
        alarmButton.setForeground(Color.WHITE);
        alarmButton.setBackground(Color.BLACK);
        alarmButton.setBorderPainted(false);
        alarmButton.setBounds(222, 119, 92, 22);

        // alarm action
        alarmButton.addActionListener(e -> {
            frame.dispose();
            frame.setVisible(false); // good practice -- UU

            mainApp();
        });

        // stopwatch button
        JButton stopwatchButton = new JButton("stopwatch");
        stopwatchButton.setFont(geistMono10px);
        stopwatchButton.setForeground(Color.WHITE);
        stopwatchButton.setBackground(Color.BLACK);
        stopwatchButton.setBorderPainted(false);
        stopwatchButton.setBounds(222, 159, 92, 22);

        stopwatchButton.addActionListener(e -> {
            frame.dispose();
            frame.setVisible(false); // good practice -- UU

            stopwatch();
        });

        // countdown button
        JButton countdownButton = new JButton("countdown");
        countdownButton.setFont(geistMono10px);
        countdownButton.setForeground(Color.WHITE);
        countdownButton.setBackground(Color.BLACK);
        countdownButton.setBorderPainted(false);
        countdownButton.setBounds(222, 199, 92, 22); 

        countdownButton.addActionListener(e -> {
            frame.dispose();
            frame.setVisible(false); // good practice -- UU

            countdown();
        });

        // logo
        ImageIcon logoImg = new ImageIcon("img/unilogo.png");
        JLabel logo = new JLabel(logoImg);
        logo.setBackground(null);
        logo.setBounds(0, 227, 44, 44);

        contentpane.add(title);
        contentpane.add(clockButton);
        contentpane.add(alarmButton);
        contentpane.add(stopwatchButton);
        contentpane.add(countdownButton);
        contentpane.add(logo);

        frame.setVisible(true);
    }

    public static void countdown() {
        JFrame frame = new JFrame();
        Container contentpane = frame.getContentPane();

        frame.setTitle("+-alarmify-+");
        frame.setSize(535, 299);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentpane.setBackground(Color.BLACK);
        contentpane.setLayout(null);

        // Custom fonts
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
        title.setForeground(Color.WHITE);
        title.setBounds(11, 10, 185, 18);

        // Stopwatch icon & button
        ImageIcon menuIcon = new ImageIcon("img/menuicon.png");
        JButton menuButton = new JButton(menuIcon);
        menuButton.setBorderPainted(false);
        menuButton.setBackground(null);
        menuButton.setBounds(508, 8, 17, 17);

        menuButton.addActionListener(e -> {
            frame.setVisible(false);
            menuMethod();
        });

        JLabel clockLabel = new JLabel("00:00:00");
        clockLabel.setFont(geistMono64px);
        clockLabel.setForeground(Color.WHITE);
        clockLabel.setBounds(114, 105, 500, 83);

        JButton oneMinute = new JButton("1 min");
        oneMinute.setFont(geistMono13px);
        oneMinute.setForeground(Color.WHITE);
        oneMinute.setBackground(Color.BLACK);
        oneMinute.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52), 1));
        oneMinute.setBounds(114, 200, 90, 23);

        oneMinute.addActionListener(e -> {
            countdownMinute = 1;
            countdownSecond = 0;

            clockLabel.setText(String.format("%02d", countdownHour) + ":" + String.format("%02d", countdownMinute) + ":" + String.format("%02d", countdownSecond));
        });

        JButton fiveMinute = new JButton("5 min");
        fiveMinute.setFont(geistMono13px);
        fiveMinute.setForeground(Color.WHITE);
        fiveMinute.setBackground(Color.BLACK);
        fiveMinute.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52), 1));
        fiveMinute.setBounds(223, 200, 90, 23);

        fiveMinute.addActionListener(e -> {
            countdownMinute = 5;
            countdownSecond = 0;

            clockLabel.setText(String.format("%02d", countdownHour) + ":" + String.format("%02d", countdownMinute) + ":" + String.format("%02d", countdownSecond));
        });

        JButton tenMinute = new JButton("10 min");
        tenMinute.setFont(geistMono13px);
        tenMinute.setForeground(Color.WHITE);
        tenMinute.setBackground(Color.BLACK);
        tenMinute.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52),1 ));
        tenMinute.setBounds(332, 200, 90, 23);

        tenMinute.addActionListener(e -> {
            countdownMinute = 10;
            countdownSecond = 0;

            clockLabel.setText(String.format("%02d", countdownHour) + ":" + String.format("%02d", countdownMinute) + ":" + String.format("%02d", countdownSecond));
        });

        JButton startButton = new JButton("start");
        startButton.setFont(geistMono13px);
        startButton.setForeground(Color.BLACK);
        startButton.setBackground(Color.WHITE);
        startButton.setBounds(223, 235, 90, 23);

        startButton.addActionListener(e -> {
            startButton.setVisible(false);
            menuButton.setVisible(false);
            oneMinute.setVisible(false);
            fiveMinute.setVisible(false);
            tenMinute.setVisible(false);

            // Custom Fonts
            Font lambdaMono = null;

            try {
                lambdaMono = Font.createFont(Font.TRUETYPE_FONT, new File("font/geistmono.ttf")).deriveFont(13f);

                GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                graphicsEnvironment.registerFont(lambdaMono);
            } catch (IOException | FontFormatException ek) {
                ek.printStackTrace();
            }
            
            JButton cancelButton = new JButton("cancel");
            cancelButton.setFont(lambdaMono);
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.BLACK);
            cancelButton.setBorder(BorderFactory.createLineBorder(new Color(52, 52,52 ), 1));
            cancelButton.setBounds(223, 235, 90, 23);

            cancelButton.addActionListener(k -> {
                timer3.stop();
                countdown();
            });

            timer3 = new Timer(1000, k -> {
                if (countdownMinute >= 1) {
                    if (countdownSecond == 0) {
                        countdownMinute--;
                        countdownSecond = 59;
                    } else {
                        countdownSecond--;
                    }
                } else if (countdownMinute == 0 && countdownSecond == 0) {
                    timer3.stop();
                }
                clockLabel.setText(String.format("%02d", countdownHour) + ":" + String.format("%02d", countdownMinute) + ":" + String.format("%02d", countdownSecond));
            });
            timer3.start();

            contentpane.add(cancelButton);
            contentpane.repaint();
            contentpane.revalidate();
        });


        contentpane.add(title);
        contentpane.add(menuButton);
        contentpane.add(clockLabel);
        contentpane.add(oneMinute);
        contentpane.add(fiveMinute);
        contentpane.add(tenMinute);
        contentpane.add(startButton);

        frame.setVisible(true);
    }

    public static void alarmScreen() {
        JFrame alarmFrame = new JFrame();
        Container contentpane = alarmFrame.getContentPane();

        alarmFrame.setTitle("+-alarmify-+");
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
            alarmFrame.setVisible(false);
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
        
        frame.setTitle("+-alarmify-+");
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

        // Stopwatch icon & button
        ImageIcon menuIcon = new ImageIcon("img/menuicon.png");
        JButton menuButton = new JButton(menuIcon);
        menuButton.setBorderPainted(false);
        menuButton.setBackground(null);
        menuButton.setBounds(508, 8, 17, 17);

        menuButton.addActionListener(e -> {
            frame.setVisible(false);
            menuMethod();
        });

        // Create current time label
        JLabel currentTime = new JLabel("current time:");
        currentTime.setFont(geistMono10px);
        currentTime.setForeground(new Color(235, 235, 235));
        currentTime.setBounds(229, 99, 110, 18);

        // Create clock
        JLabel c = new JLabel(clock);
        c.setFont(geistMono64px);
        c.setForeground(new Color(235, 235, 235));
        c.setBounds(114, 105, 500, 83);

        Timer timer = new Timer(100, e -> {
            c.setText(clock);
        });

        timer.start();

        // Create Label
        JLabel setAlarm = new JLabel("set alarm:");
        setAlarm.setFont(geistMono10px);
        setAlarm.setForeground(new Color(235, 235, 235));
        setAlarm.setBounds(11, 217, 60, 13);

        // Create input text field
        JTextField input1 = new JTextField();
        input1.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52), 1));
        input1.setBackground(Color.BLACK);
        input1.setForeground(Color.WHITE);
        input1.setBounds(11, 235, 34, 23);

        JLabel separator = new JLabel(":");
        separator.setFont(geistMono10px);
        separator.setForeground(new Color(235, 235, 235));
        separator.setBounds(48, 235, 6, 23);

        // Create second input text field
        JTextField input2 = new JTextField();
        input2.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52), 1));
        input2.setBackground(Color.BLACK);
        input2.setForeground(Color.WHITE);
        input2.setBounds(57, 235, 34, 23);

        // Create start button
        JButton startAlarm = new JButton("START");
        startAlarm.setFont(geistMono10px);
        startAlarm.setForeground(Color.BLACK);
        startAlarm.setBackground(Color.WHITE);
        startAlarm.setBounds(103, 235, 90, 23);

        // startAlarm event => Button functionality
        startAlarm.addActionListener(e -> {
            try {
                menuButton.setVisible(false);
                // Convert to integer
                int HH = Integer.parseInt(input1.getText());
                int MM = Integer.parseInt(input2.getText());

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
                input1.setVisible(false);
                separator.setVisible(false);
                input2.setVisible(false);
                startAlarm.setVisible(false);
                
                JLabel alarmSetFor = new JLabel("alarm set for:");
                alarmSetFor.setFont(geistMono10);
                alarmSetFor.setForeground(Color.WHITE);
                alarmSetFor.setBounds(223, 154, 118, 13);
                
                JLabel alarmTime = new JLabel(String.format("%02d", HH) + ":" + String.format("%02d", MM) + ":00");
                alarmTime.setFont(geistMono64);
                alarmTime.setForeground(Color.WHITE);
                alarmTime.setBounds(114, 159, 500, 83);

                JButton cancelAlarm = new JButton("CANCEL");
                cancelAlarm.setFont(geistMono10);
                cancelAlarm.setBackground(Color.WHITE);
                cancelAlarm.setForeground(Color.BLACK);
                cancelAlarm.setBounds(440, 240, 90, 23);
                
                cancelAlarm.addActionListener(k -> {
                    menuButton.setVisible(true);
                    timer2.stop();
                    mainApp();
                });

                timer2 = new Timer(100, l -> {
                    String[] clockSplit = clock.split(":");
                    int cH = Integer.parseInt(clockSplit[0]);
                    int cM = Integer.parseInt(clockSplit[1]);

                    if (cH == HH && cM == MM) {
                        timer2.stop();
                        frame.setVisible(false);
                        alarmScreen();
                    }
                });

                timer2.start();
                
                currentTime.setBounds(229, 45, 110, 18);
                c.setBounds(114, 50, 500, 83);

                contentpane.add(alarmSetFor);
                contentpane.add(alarmTime);
                contentpane.add(cancelAlarm);

                contentpane.repaint();
                contentpane.revalidate();

            } catch (Exception ex) {
                ex.printStackTrace();
                mainApp();
            }
        });

        contentpane.add(title);
        contentpane.add(currentTime);
        contentpane.add(c);
        contentpane.add(setAlarm);
        contentpane.add(input1);
        contentpane.add(separator);
        contentpane.add(input2);
        contentpane.add(startAlarm);
        contentpane.add(menuButton);

        frame.setVisible(true);
    }

    public static void stopwatch() {
        // Frame & Container => Container keeps all the elements within the frame
        JFrame frame = new JFrame();
        Container contentpane = frame.getContentPane();

        frame.setTitle("+-alarmify-+");
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
        JLabel stopwatch = new JLabel("stopwatch:");
        stopwatch.setFont(geistMono10px);
        stopwatch.setForeground(new Color(235, 235, 235));
        stopwatch.setBounds(229, 108, 110, 18);

        // Stopwatch icon & button
        ImageIcon menuIcon = new ImageIcon("img/menuicon.png");
        JButton menuButton = new JButton(menuIcon);
        menuButton.setBorderPainted(false);
        menuButton.setBackground(null);
        menuButton.setBounds(508, 8, 17, 17);

        menuButton.addActionListener(e -> {
            frame.setVisible(false);
            menuMethod();
        });

        // stopwatch hour
        JLabel sH = new JLabel("00");
        sH.setFont(geistMono64px);
        sH.setForeground(new Color(235, 235, 235));
        sH.setBounds(114, 106, 77, 83);

        // stopwatch separator ":"
        JLabel separator1 = new JLabel(":");
        separator1.setFont(geistMono64px);
        separator1.setForeground(new Color(235, 235, 235));
        separator1.setBounds(191, 108, 39, 83);

        // stopwatch minute
        JLabel sM = new JLabel("00");
        sM.setFont(geistMono64px);
        sM.setForeground(new Color(235, 235, 235));
        sM.setBounds(229, 108, 77, 83);

        // stopwatch separator 2 ":"
        JLabel separator2 = new JLabel(":");
        separator2.setFont(geistMono64px);
        separator2.setForeground(new Color(235, 235, 235));
        separator2.setBounds(306, 108, 39, 83);

        // stopwatch seconds
        JLabel sS = new JLabel("00");
        sS.setFont(geistMono64px);
        sS.setForeground(new Color(235, 235, 235));
        sS.setBounds(344, 108, 77, 83);

        // start button
        JButton startStopwatch = new JButton("START");
        startStopwatch.setFont(geistMono10px);
        startStopwatch.setForeground(Color.BLACK);
        startStopwatch.setBackground(Color.WHITE);
        startStopwatch.setBounds(335, 235, 90, 23);

        Timer timer = new Timer(1000, e -> {
            if (minute == 59) {
                hour++;
                minute = 0;
                second = 0;
                sH.setText(String.format("%02d", hour));
                sM.setText(String.format("%02d", minute));
                sS.setText(String.format("%02d", second));
            } else if (second == 59) {
                minute++;
                second = 0;
                sM.setText(String.format("%02d", minute));
                sS.setText(String.format("%02d", second));
            } else {
                second++;
                sS.setText(String.format("%02d", second));
            }
        });

        startStopwatch.addActionListener(e -> {
            menuButton.setVisible(false);
            hour = 0;
            minute = 0;
            second = 0;
            timer.start();
        });

        // stop button
        JButton stopStopwatch = new JButton("STOP");
        stopStopwatch.setFont(geistMono10px);
        stopStopwatch.setForeground(Color.WHITE);
        stopStopwatch.setBackground(Color.BLACK);
        stopStopwatch.setBorder(BorderFactory.createLineBorder(new Color(52, 52, 52), 1));
        stopStopwatch.setBounds(434, 235, 90, 23);

        stopStopwatch.addActionListener(e -> {
            menuButton.setVisible(true);
            timer.stop();
        });

        contentpane.add(title);
        contentpane.add(stopwatch);
        contentpane.add(menuButton);
        contentpane.add(sH);
        contentpane.add(separator1);
        contentpane.add(sM);
        contentpane.add(separator2);
        contentpane.add(sS);
        contentpane.add(startStopwatch);
        contentpane.add(stopStopwatch);

        frame.setVisible(true);
    }
}