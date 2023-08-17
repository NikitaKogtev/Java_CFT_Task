import java.util.Arrays;
import java.util.List;

public class Main {
    // Метод для инициализации объекта FileMergeSorter на основе аргументов командной строки
    public static FileSorter initializeSorter(boolean ascending, boolean isInteger) {
        return new FileSorter(ascending, isInteger);
    }

    public static void main(String[] args) {
        // Проверяем, что передано не менее трех аргументов (режим сортировки, тип данных, выходной файл)
        if (args.length < 3) {
            System.out.println("Usage: java FileMergeSortApp [-a|-d] [-s|-i] <output-file> <input-files>");
            return;
        }

        boolean ascending = true;
        boolean isInteger = false;

        // Проверяем, был ли указан режим сортировки (-a или -d)
        if (args.length > 0 && args[0].equalsIgnoreCase("-d")) {
            ascending = false;
        }

        // Проверяем, был ли указан тип данных (-s или -i)
        if (args.length > 1 && args[1].equalsIgnoreCase("-i")) {
            isInteger = true;
        }

        // Инициализация сортировщика
        FileSorter fileSorter = initializeSorter(ascending, isInteger);

        // Определение имени выходного файла и списка входных файлов
        String outputFile = args[args.length - 2];
        List<String> inputFiles = Arrays.asList(Arrays.copyOfRange(args, 2, args.length - 2));

        // Выполнение сортировки и слияния файлов
        fileSorter.mergeFiles(inputFiles, outputFile);

        System.out.println("Files sorted and merged successfully.");
    }
}