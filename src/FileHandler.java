/**
*
* @author YOUNES RAHEBI / younes.rahebi@ogr.sakarya.edu.tr
* @since 03/04/2024
* <p>
* Dosya işleme işlemlerini gerçekleştiren sınıf
* </p>
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileHandler {
	
	// Klonlama işlemini gerçekleştiren metot
	public static File cloneRepository(String repoURL) throws IOException, InterruptedException {
        File clone = new File("cloned_repo_" + System.currentTimeMillis());
        Process process = Runtime.getRuntime().exec("git clone " + repoURL + " " + clone.getAbsolutePath());
        int exitValue = process.waitFor();
        
        if (exitValue != 0) {
            throw new IOException("Hata: Geçersiz URL veya klonlama başarısız");
        }
        
        return clone;
    }
	
    // Java dosyalarını açığa çıkarma
	public static void openJavaFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Alt dizinler için tekrarlayan çağrı
                	openJavaFiles(file);
                } else if (file.isFile() && file.getName().endsWith(".java") && isClass(file)) {
                    // Java dosyası bulunduğunda işle
                    System.out.println("Sınıf: " + file.getName());
                    System.out.println("Javadoc Yorum Satırı Sayısı: " + CodeMetrics.countJavadocComments(file));
                    System.out.println("Javadoc Olmayan Yorum Satırı Sayısı: " + CodeMetrics.countNonJavadocComments(file));
                    System.out.println("Kod Satırı Sayısı: " + CodeMetrics.countCodeLines(file));
                    System.out.println("LOC: " + CodeMetrics.LOC(file));
                    System.out.println("Fonksiyon Sayısı: " + CodeMetrics.countFunctions(file));
                    System.out.println("Yorum Sapma Yüzdesi: " + DeviationCalculator.deviationPercentage(file));
                    System.out.println("-----------------------------------------");
                }
            }
        }
    }

	// Verilen dosyanın bir sınıf tanımı içerip içermediğini kontrol eden fonksiyon
	public static boolean isClass(File file) {
	    try (Scanner scanner = new Scanner(file)) {
	        String regex = "\\bclass\\s+\\w+\\s*";
	        Pattern pattern = Pattern.compile(regex);
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            if (pattern.matcher(line).find()) {
	                return true;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
}