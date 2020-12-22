import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringBaiHai {
    public static String inputCo = "input_co.txt";
    public static String outputCo = "output_co.txt";

    public static void main(String[] args) {
        String[] days = {"20151106", "20151107", "20151108"};
        for (String day : days) {
            String folderInputURL = "/home/dinhngocdinh/Downloads/String/input/";
            StringBuffer input = readFileByDate(folderInputURL, day);
            String folderOutputURL = "/home/dinhngocdinh/Downloads/String/output/";
            StringBuffer output = readFileByDate(folderOutputURL, day);
            System.out.println("Kiem tra ngay " + day);
            StringBuffer soThueBaoInputCoOutputKhongCo = checkExistLeftInRight(input, output);
            writeFile(inputCo, soThueBaoInputCoOutputKhongCo.toString());
            StringBuffer soThueBaoOutputCoInPutKhongCo = checkExistLeftInRight(output, input);
            writeFile(outputCo, soThueBaoOutputCoInPutKhongCo.toString());
        }
    }

    public static void writeFile(String outPutFileName, String content) {
        try {
            FileWriter fileWriter = new FileWriter(outPutFileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Ghi vao file thanh cong! ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ghi that bai!");
        }
    }

    public static StringBuffer checkExistLeftInRight(StringBuffer left, StringBuffer right) {
        StringBuffer result = new StringBuffer();
        for (String string : left.toString().split(" ")) {
            if (!right.toString().contains(string)) {
                result.append(string);
                result.append("\n");
            }
        }
        return result;
    }

    public static StringBuffer readFileByDate(String folderURL, String day) {
        File folderInput = new File(folderURL);
        File[] folderInputArray = folderInput.listFiles();
        List<File> fileByDate = new ArrayList<>();
        for (File subFolderInput : folderInputArray) {
            File fileSubFolder = new File(String.valueOf(subFolderInput));
            File[] fileArray = fileSubFolder.listFiles();
            for (File files : fileArray) {
                if (files.toString().contains(day)) {
                    fileByDate.add(files);
                }
            }
        }
        StringBuffer result = new StringBuffer();
        for (File file : fileByDate) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    Pattern pattern = Pattern.compile("^[8][4][9][0-9]{7}$");
                    if (pattern.matcher(line).find()) {
                        result.append(line);
                        result.append(" ");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
