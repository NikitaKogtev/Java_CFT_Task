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

    public void mergeFiles(List<String> inputFiles, String outputFile) {
        List<String> data = new ArrayList<>();
        for (String filename : inputFiles) {
            data.addAll(readFile(filename)); // Чтение данных из каждого файла
        }

        List<String> sortedData = mergeSort(data); // Сортировка данных слиянием
        writeFile(outputFile, sortedData); // Запись отсортированных данных в выходной файл
    }

    private List<String> mergeSort(List<String> arr) {
        if (arr.size() <= 1) {
            return arr; // Базовый случай: если массив содержит 1 элемент или меньше, он уже отсортирован
        }

        int mid = arr.size() / 2;
        List<String> left = mergeSort(arr.subList(0, mid)); // Сортировка левой половины
        List<String> right = mergeSort(arr.subList(mid, arr.size())); // Сортировка правой половины

        return merge(left, right); // Объединение и слияние отсортированных половин
    }

    private List<String> merge(List<String> left, List<String> right) {
        List<String> result = new ArrayList<>();
        int i = 0, j = 0;

        // Сравнение для слияния в нужном порядке (возрастание или убывание)
        while (i < left.size() && j < right.size()) {
            if (compare(left.get(i), right.get(j))) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));
        return result;
    }

    private boolean compare(String a, String b) {
        if (isInteger) {
            int intA = Integer.parseInt(a);
            int intB = Integer.parseInt(b);
            return ascending ? intA <= intB : intA >= intB;
        } else {
            return ascending ? a.compareTo(b) <= 0 : a.compareTo(b) >= 0;
        }
    }

    private List<String> readFile(String filename) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    data.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void writeFile(String filename, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String item : data) {
                bw.write(item);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}