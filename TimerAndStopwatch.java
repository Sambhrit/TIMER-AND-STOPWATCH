import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TimerAndStopwatch {
    private static Timer timer;
    private static int timerSeconds;
    private static int stopwatchSeconds;

    private static JFrame frame;
    private static JLabel timerLabel;
    private static JLabel stopwatchLabel;
    private static JButton startButton;
    private static JButton stopButton;
    private static JButton resetButton;

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Timer & Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timerLabel = new JLabel("Timer: 0 seconds", SwingConstants.CENTER);
        stopwatchLabel = new JLabel("Stopwatch: 0 seconds", SwingConstants.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer();
                startStopwatch();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                stopStopwatch();
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetTimer();
                resetStopwatch();
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(timerLabel);
        panel.add(stopwatchLabel);
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(resetButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void startTimer() {
        timer = new Timer();
        timerSeconds = 0;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timerSeconds++;
                timerLabel.setText("Timer: " + timerSeconds + " seconds");
            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    private static void stopTimer() {
        timer.cancel();
    }

    private static void resetTimer() {
        timerLabel.setText("Timer: 0 seconds");
    }

    private static void startStopwatch() {
        stopwatchSeconds = 0;

        TimerTask stopwatchTask = new TimerTask() {
            @Override
            public void run() {
                stopwatchSeconds++;
                stopwatchLabel.setText("Stopwatch: " + stopwatchSeconds + " seconds");
            }
        };

        timer.scheduleAtFixedRate(stopwatchTask, 1000, 1000);
    }

    private static void stopStopwatch() {
        // Cancel the stopwatch task
        for (TimerTask task : timer.purge()) {
            task.cancel();
        }
    }

    private static void resetStopwatch() {
        stopwatchLabel.setText("Stopwatch: 0 seconds");
    }
}
