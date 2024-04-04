/**
*
* @author YOUNES RAHEBI / younes.rahebi@ogr.sakarya.edu.tr
* @since 03/04/2024
* <p>
* Java dosyalarının metriklerini hesaplayan sınıf
* </p>
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeMetrics {

	// Java dosyasındaki Javadoc yorum satırlarını sayma
	public static int countJavadocComments(File file) {
        int javadocCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean inComment = false;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("/**")) {
                    inComment = true;
                } else if (inComment && line.contains("*/")) {
                    inComment = false;
                } else if (inComment) {
                    javadocCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javadocCount;
    }

    // Java dosyasındaki Javadoc olmayan yorum satırlarını sayma
	public static int countNonJavadocComments(File file) {
        int nonJavadocCommentLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean inComment = false;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("/*") && !line.startsWith("/**") && !line.endsWith("*/")) {
                    inComment = true;
                } else if (inComment && line.contains("*/")) {
                    inComment = false;
                } else if (inComment) {
                    nonJavadocCommentLines++;
                }

                // Javadoc olmayan yorum satırlarını kontrol etme
                Pattern pattern = Pattern.compile("//.*|/\\*.*\\*/");
                Matcher commentMatcher = pattern.matcher(line);
                if (commentMatcher.find()) {
                    nonJavadocCommentLines++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nonJavadocCommentLines;
    }

    // Java dosyasındaki kod satırlarını sayma
	public static int countCodeLines(File file) {
        int CodeLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                Pattern pattern = Pattern.compile("^(\\/\\/|\\/\\*|\\*|\\/\\*)");
                Matcher matcher = pattern.matcher(line);
                if (!line.isEmpty() && !matcher.find()) {
                	CodeLines++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CodeLines;
    }

    // Java dosyasındaki toplam satır sayısını sayma (boş satırlar ve yorum satırları dahil)
	public static int LOC(File file) {
        int linesOfCode = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((br.readLine()) != null) {
                linesOfCode++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesOfCode;
    }

    // Java dosyasındaki fonksiyon sayısını sayma
	public static int countFunctions(File file) {
        int functions = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                Pattern pattern = Pattern.compile("(public|private|static|protected|abstract|native|synchronized) *([a-zA-Z0-9<>._?, ]*) +([a-zA-Z0-9_]+) *\\([a-zA-Z0-9<>\\[\\]._?, \n]*\\) *([a-zA-Z0-9_ ,\n]*)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    functions++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return functions;
    }
    
}