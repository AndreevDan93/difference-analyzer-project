package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "getDiff", mixinStandardHelpOptions = true, version = "getDiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {

    @Parameters(index = "0", description = "path to first file.")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file.")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format \"stylish\" or \"plain\" or \"json\" "
            + "[default: stylish]")
    private String format = "stylish";

    //  Define your business logic in the run or call method of your class. This method is called after parsing
//  is successfully completed.
    @Override
    public final String call() throws Exception { // your business logic goes here...
        try {
            NotStaticDiffer differ = new NotStaticDiffer(filepath1, filepath2, format);
            System.out.println(differ.generate());
//            System.out.println(StaticDiffer.generate(filepath1, filepath2, format));
            return "call is working";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "call is not working";
        }
    }

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }
}
