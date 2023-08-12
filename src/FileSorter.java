import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Класс для сортировки слиянием
public class FileSorter {
    private boolean ascending;
    private boolean isInteger;

    public FileSorter(boolean ascending, boolean isInteger) {
        this.ascending = ascending;
        this.isInteger = isInteger;
    }

    // Метод для объединения и слияния отсортированных списков
    private void merge(List<String> result, List<String> left, List<String> right) {
        int i = 0, j = 0, k = 0;

        // Сравнение для слияния в нужном порядке (возрастание или убывание)
        while (i < left.size() && j < right.size()) {
            if (compare(left.get(i), right.get(j))) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }

    // Метод для сравнения двух элементов в зависимости от типа данных и режима сортировки
    private boolean compare(String a, String b) {
        if (isInteger) {
            int intA = Integer.parseInt(a);
            int intB = Integer.parseInt(b);
            return ascending ? intA <= intB : intA >= intB;
        } else {
            return ascending ? a.compareTo(b) <= 0 : a.compareTo(b) >= 0;
        }
    }

    // Метод для чтения данных из файла и возврата списка строк
    private List<String> readFile(String filename) throws IOException {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    data.add(line);
                }
            }
        }
        return data;
    }

    // Метод для записи данных в файл
    private void writeFile(String filename, List<String> data) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (String item : data) {
                pw.println(item);
            }
        }
    }

    // Метод для сортировки слиянием
    public void mergeFiles(List<String> inputFiles, String outputFile) {
        try {
            List<String> data = new ArrayList<>();

            // Чтение всех данных из входных файлов
            for (String filename : inputFiles) {
                data.addAll(readFile(filename));
            }

            // Сортировка слиянием
            mergeSort(data);

            // Запись отсортированных данных в выходной файл
            writeFile(outputFile, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для сортировки слиянием
    private void mergeSort(List<String> arr) {
        if (arr.size() <= 1) {
            return;
        }

        int mid = arr.size() / 2;
        List<String> left = new ArrayList<>(arr.subList(0, mid));
        List<String> right = new ArrayList<>(arr.subList(mid, arr.size()));

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }
}