package common.jeycode.process;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public final class ProcessOutputSample
{

      private static final String DELIMITER = "\n";
      private static final String IBM850 = "IBM850";
      private static final String PWS = "powershell ";
      private static final String COMMAND_HELP = "help";
      private static final String COMMAND_TREE = "tree";

      public static void main(String[] args) throws IOException
      {

            var frame = new JFrame();
            frame.setSize(800,600);
            var areaTxt = new JTextArea();
            areaTxt.setBackground(Color.black);
            areaTxt.setForeground(Color.green);
            var scrollPane = new JScrollPane(areaTxt);
            scrollPane.setSize(frame.getWidth(),frame.getHeight());
            areaTxt.setSize(scrollPane.getSize());
            areaTxt.setEditable(true);
            areaTxt.setFont(new Font("Arial",Font.BOLD,frame.getHeight() >> 5));
            frame.add(scrollPane);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            var proc = new ProcessBuilder(PWS,COMMAND_TREE).redirectErrorStream(true)
                                                           .start();

            var inputStream = proc.getInputStream();

            try (var bfReader = new BufferedReader(new InputStreamReader(inputStream,IBM850)))
            {
                  var allLines = bfReader.lines();

                  areaTxt.append(allLines.collect(Collectors.joining(DELIMITER)));

                  proc.onExit()
                      .defaultExecutor()
                      .execute(()-> frame.setVisible(true));
            }

      }
}
