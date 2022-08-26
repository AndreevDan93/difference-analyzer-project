package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
import static hexlet.code.Differ.generate;

@Command(name = "getDiff", mixinStandardHelpOptions = true, version = "getDiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {

    @Parameters(index = "0", description = "path to first file.")
    private  String filepath1;

    @Parameters(index = "1", description = "path to second file.")
    private  String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format \"stylish\" or \"plain\" or \"json\" "
            + "[default: stylish]")
    private String format = "stylish";

    //  Define your business logic in the run or call method of your class. This method is called after parsing
//  is successfully completed.
    @Override
    public final String call() throws Exception { // your business logic goes here...
        System.out.println(generate(filepath1, filepath2, format));
        return "call is working";
    }

    public static void main(String[] args) {
        try {
            System.exit(new CommandLine(new App()).execute(args));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}