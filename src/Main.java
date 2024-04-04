/**
*
* @author YOUNES RAHEBI / younes.rahebi@ogr.sakarya.edu.tr
* @since 03/04/2024
* <p>
* Main Sınıfı
* </p>
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean isSuccessful = false;

        while (!isSuccessful) {
            // Kullanıcıdan GitHub deposu URL'sini alma
            System.out.print("GitHub Depo Linkini (Repository URL) Giriniz: ");
            String repoURL = scanner.nextLine().trim();

            try {
                // Depoyu klonlama
                File clone = FileHandler.cloneRepository(repoURL);
                FileHandler.openJavaFiles(clone);
                isSuccessful = true;
            } catch (IOException | InterruptedException e) {
                // Hata durumunda istisnaları yazdır
                e.printStackTrace();
                Thread.sleep(1);
            }
        }
        scanner.close();
    }
}