package common.jeycode.process;

import java.io.IOException;

public final class ProcessSample
{

      private static final String CHROME = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
      private static final String PWS = "powershell ";

      public static void main(String[] args) throws IOException,InterruptedException
      {

//            var process = new ProcessBuilder(CHROME).start();

            Thread.sleep(3000);

            var process = ProcessHandle.of(15156)
                                       .get();

            process.destroyForcibly();

//            process.destroy();

            process.onExit()
                   .defaultExecutor()
                   .execute(()-> System.out.println("Hemos matado el proceso de Chrome"));

//            var process = new ProcessBuilder(PWS," sadada").redirectError(new File("Error.txt"))
//                                    .start();

//            var process = Runtime.getRuntime()
//                                 .exec(CHROME);

//            System.out.println(process.info()
//                                      .totalCpuDuration()
//                                      .get()
//                                      .getNano()
//                                    + " ns");

//            System.out.println(process.isAlive());
//            System.out.println(process.info()
//                                      .startInstant()
//                                      .get());
//            System.out.println(process.pid());

      }

}
