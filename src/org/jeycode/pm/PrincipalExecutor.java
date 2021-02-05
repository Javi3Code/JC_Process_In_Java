package org.jeycode.pm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class PrincipalExecutor
{

      private final static String ENTER = "\n";
      static File path = new File("C:\\Users\\JAVIER\\Desktop\\EClipse ACTIV\\GIT_REPOSITORY\\ProcessJavaSample\\target\\classes");
      private final static File FILE = new File("export.txt");

      public static void main(String[] args) throws IOException,InterruptedException
      {

            var processTransf = new ProcessBuilder("java","org.jeycode.pm.StringTransform").redirectErrorStream(true)
                                                                                           .directory(path)
                                                                                           .start();

            var processExp = new ProcessBuilder("java","org.jeycode.pm.ExportToFile",FILE.toString()).redirectErrorStream(true)
                                                                                                     .directory(path)
                                                                                                     .start();

            var osTransfStream = processTransf.getOutputStream();
            var inTransfStream = processTransf.getInputStream();
            var oWriteStream = processExp.getOutputStream();

            System.out.println("Introduce texto a formatear");

            try (var stream = new InputStreamReader(System.in,Charset.forName("UTF-8")); var bffReader = new BufferedReader(stream);
                  var bffTransfReader = new BufferedReader(new InputStreamReader(inTransfStream,Charset.forName("UTF-8"))))
            {
                  String line = "";

                  do
                  {
                        line = bffReader.readLine();
                        osTransfStream.write((line + ENTER).getBytes());
                        osTransfStream.flush();
                        var lineTransf = bffTransfReader.readLine();
                        System.out.println(lineTransf);
                        oWriteStream.write((lineTransf + ENTER).getBytes());
                        oWriteStream.flush();

                  }
                  while (!line.isBlank());

                  if (processExp.waitFor() == 0 && processTransf.waitFor() == 0)
                  {
                        System.out.println("Se terminó la ejecución");
                  }
                  else
                  {
                        System.out.println("Error");
                  }

            }

      }
}