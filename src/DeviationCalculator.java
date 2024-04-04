/**
*
* @author YOUNES RAHEBI / younes.rahebi@ogr.sakarya.edu.tr
* @since 03/04/2024
* <p>
* Kod sapma yüzdesini hesaplayan sınıf
* </p>
*/

import java.io.File;

public class DeviationCalculator {

    // Java dosyasındaki yorum sapma yüzdesini hesaplama
    public static String deviationPercentage(File file) {
        double functions = CodeMetrics.countFunctions(file);
        double code = CodeMetrics.countCodeLines(file);
        double javadoc = CodeMetrics.countJavadocComments(file);
        double nonJavadoc = CodeMetrics.countNonJavadocComments(file);
        if (functions == 0 || code == 0) {
            // Sıfıra bölme hatası işle
            return "NaN"; // Tanımsız bir sonucu belirtmek için NaN (Not-a-Number) değerini döndür
        } else {
            double YG = ((javadoc + nonJavadoc) * 0.8) / functions;
            double YH = (code / functions) * 0.3;
            double deviationPercentage = ((100 * YG) / YH) - 100;
            return "% " + String.format("%.2f", deviationPercentage);
        }
    }
}