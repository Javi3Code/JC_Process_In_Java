package org.jeycode.pm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public final class ExportToFile
{

      public static void main(String[] args)
      {
            try (var stream = new InputStreamReader(System.in,Charset.forName("UTF-8")); var bffReader = new BufferedReader(stream);
                  var bffWritter = new BufferedWriter(new FileWriter(new File("ExportText.txt"))))
            {
                  String line = "";
                  do
                  {
                        line = bffReader.readLine();
                        bffWritter.write(line);
                        bffWritter.newLine();
                  }
                  while (!line.isBlank());
            }
            catch (Exception ex)
            {
                  ex.printStackTrace();
                  System.out.println(ex.getMessage());
            }
      }

}
