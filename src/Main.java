import java.util.Arrays;
import java.util.List;

public class Main {

    public static FileSorter initializeSorter(boolean ascending, boolean isInteger) {
        return new FileSorter(ascending, isInteger);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main [-a|-d] [-s|-i] <output-file> <input-files>");
            return;
        }

        boolean ascending = true;
        boolean isInteger = false;

        if (args[0].equalsIgnoreCase("-d")) {
            ascending = false;
        }

        if (args[1].equalsIgnoreCase("-i")) {
            isInteger = true;
        }

        FileSorter fileSorter = initializeSorter(ascending, isInteger);

        String outputFile = args[2];
        List<String> inputFiles = Arrays.asList(Arrays.copyOfRange(args, 3, args.length));

        fileSorter.mergeFiles(inputFiles, outputFile);

        System.out.println("Files sorted and merged successfully.");
    }
}