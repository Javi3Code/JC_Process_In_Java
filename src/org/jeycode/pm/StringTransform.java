package org.jeycode.pm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public final class StringTransform
{

      public static void main(String[] args)
      {
            
            try (var stream = new InputStreamReader(System.in,Charset.forName("UTF-8"));
                 var bffReader = new BufferedReader(stream))
            {
                  String line = "";
                  do
                  {
                        line = bffReader.readLine();
                        System.out.println(line.toUpperCase());
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
