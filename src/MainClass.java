import Modeller.Calisanlar.Calisan;
import Veritabani.Calisanlar;
import Veritabani.Departmanlar;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {

        // Buradaki kod uzunluğu gözünüzü boyamasin cünkü %80 lik bir kısmı görsel olarak güzel dursun diye var...
        // Buradaki kodlari bitmiş hali ile birakacağım bir şeyi değiştirmenize gerek yok.


        // 2 Scanner kullandim cünkü String icin kullanılan bir scanner, baska bir tip icin kullanildiğinda hata verebiliyor.
        Scanner input = new Scanner(System.in);
        Scanner stringInput = new Scanner(System.in);


        // Basit bir program baslangici, İslem 4 seçilir ise, döngü duracak.
        System.out.println("\033[1;34m" + "\n|******************************************************|" + "\033[0m\t");
        System.out.println("\033[1;32m" + " ŞİRKET PANELİNE HOŞGELDİNİZ. LÜTFEN BİR İŞLEM SEÇİNİZ" + "\033[0m\t");
        System.out.println("\033[1;34m" + "|******************************************************|\n" + "\033[0m\t");
        while (true) {
            System.out.println(">< İŞLEM MENÜSÜ ><");
            System.out.println("\033[1;36m" + "-------------------------------------------" + "\033[0m\t");
            System.out.println("1-| Çalışan İşlemleri\n2-| Tüm Çalışanları Görüntüle\n3-| Departmandaki Tüm Çalışanları Görüntüle\n4-| Çıkış");
            System.out.println("\033[1;36m" + "-------------------------------------------" + "\033[0m\t");
            System.out.print("İşlem : ");
            if (!input.hasNextInt()) { // KULLANICIYI, int DIŞINDA BİR TİP GİRDİĞİNDE, UYARI VEREREK UYGUN DEĞER GİRMEYE ZORLUYOR
                System.out.println("\033[1;31m" + "\nHATALI SEÇİM YAPTINIZ! \nTEKAR SEÇİM YAPINIZ!\n" + "\033[0m\t");
                input.next();
            } else {
                int secim1 = input.nextInt();
                System.out.println("\033[1;33m" + "İşleminiz gerçekleştiriliyor lütfen bekleyiniz...\n" + "\033[0m\t");
                Thread.sleep(1500);

                // Eğer seçim 1 yapilir ise calisan islemleri sekmesine gidilecek.
                if (secim1 == 1) {
                    while (true) {
                        System.out.println("---------------------------------------------");
                        System.out.println("Çalışan İslemleri Paneline Hoşgeldiniz, Lütfen bir işlem seçiniz.\n");
                        System.out.println("1-| Çalışan Ekle\n2-| Çalışan Sil\n3-| Zam Yap\n4-| Önceki Menü\n");
                        System.out.print("İslem : ");
                        if (!input.hasNextInt()) { // KULLANICIYI, int DIŞINDA BİR TİP GİRDİĞİNDE, UYARI VEREREK UYGUN DEĞER GİRMEYE ZORLUYOR
                            System.out.println("\033[1;31m" + "\nHATALI SEÇİM YAPTINIZ! \nTEKAR SEÇİM YAPINIZ!\n" + "\033[0m\t");
                            input.next();
                        } else {
                            int secim2 = input.nextInt();
                            System.out.println("\033[1;33m" + "İşleminiz gerçekleştiriliyor lütfen bekleyiniz...\n" + "\033[0m\t");
                            Thread.sleep(1000);

                            // Seçim 1 için bir calisan eklenecek, sizden constructorın parametresi icin gerekli argümanlari isteyecek
                            if (secim2 == 1) {
                                // Argüman 1
                                System.out.print("Lütfen isim ve soyisim giriniz : ");
                                String calisanAdSoyad = stringInput.nextLine();

                                do {
                                    if (!KontrolAdSoyad(calisanAdSoyad)) {
                                        System.out.print("\033[1;32m" + "İSİM VE SOYİSİM UYGUNSUZ KARAKTER İÇEREMEZ!" + "\033[0m\t" +
                                                "\nTekrar isim ve soyisim giriniz : ");
                                        calisanAdSoyad = stringInput.nextLine();
                                    }
                                } while (!KontrolAdSoyad(calisanAdSoyad));


                                //adSoyadKontrol(calisanAdSoyad);
                                int calisanMaas = 0;
                                // Argüman 2
                                while (true) {
                                    System.out.print("Lütfen maas giriniz : ");
                                    if (!input.hasNextInt()) { // KULLANICIYI, int DIŞINDA BİR TİP GİRDİĞİNDE, UYARI VEREREK UYGUN DEĞER GİRMEYE ZORLUYOR
                                        System.out.println("\033[1;31m" + "SADECE TAMSAYI GİRİŞİ YAPINIZ!" + "\033[0m\t");
                                        input.next();
                                    } else {
                                        calisanMaas = input.nextInt();
                                        break;
                                    }
                                }
                                // Argüman 3
                                System.out.print("Lütfen departman kodunu giriniz : ");
                                String calisanDepartmanKod = stringInput.nextLine();

                                // calisanin constructoru ve argümanlar burada kullaniliyor.
                                new Calisan(calisanAdSoyad.toUpperCase(), calisanMaas, calisanDepartmanKod);
                                System.out.println("Çalışan başarı ile eklendi. Önceki menüye aktarılıyorsunuz...\n");
                                Thread.sleep(1000);
                                break;

                            } else if (secim2 == 2) {

                                // Bir id verilecek ve id üzerinden gerekli işlemler yapiacak, buradaki işlem
                                // bir calisani silmek için
                                if (!Calisanlar.getCalisanList().isEmpty()) { // Eğer şirkek çalışan listesi doluysa işleme geçer
                                    System.out.print("Lütfen silmek istediğiniz çalışanın ID sini giriniz : ");
                                    String calisanId = stringInput.nextLine();
                                    Calisanlar.deleteACalisanWithId(calisanId, secim1);
                                    Thread.sleep(1000);
                                    break;
                                } else { // Liste dolu değilse aşağıdaki uyarıyı verir.
                                    System.out.println("\033[1;31m" + "ŞİRKETTE HİÇ ÇALIŞAN YOK. SİLME İŞLEMİ BAŞLATILAMAZ!\n " + "\033[0m\t");
                                    break;
                                }
                            } else if (secim2 == 3) {

                                // Bir id verilecek ve id üzerinden gerekli işlemler yapiacak, buradaki işlem
                                // bir calisana zam yapmak için
                                if (!Calisanlar.getCalisanList().isEmpty()) { // Eğer şirket çalışan listesi doluysa işleme geçer
                                    System.out.print("Lütfen zam yapmak istediğiniz çalışanın ID sini giriniz : ");
                                    String calisanId = stringInput.nextLine();
                                    Calisan.zamYap(calisanId, secim1);
                                    Thread.sleep(1000);
                                    break;
                                } else { // Liste dolu değilse aşağıdaki uyarıyı verir.
                                    System.out.println("\033[1;31m" + "ŞİRKETTE HİÇ ÇALIŞAN YOK. ZAM YAPMA İŞLEMİ BAŞLATILAMAZ!\n " + "\033[0m\t");
                                }
                            } else if (secim2 == 4) {
                                Thread.sleep(1000);
                                break;
                            } else {// Kullanıcı tarafından secim2'ye 1,2,3 ve 4 tamsayıları dışında bir tam sayı atanırsa
                                // aşağıdaki uyarıyı veriyor ve kullanıcıyı doğru seçime zorluyor
                                System.out.println("\033[1;31m" + "\nHATALI SEÇİM YAPTINIZ! \nTEKAR SEÇİM YAPINIZ!\n" + "\033[0m\t");
                            }
                        }
                    }

                    // Eğer secim 2 yapilir ise Calisanlar listelenecek
                } else if (secim1 == 2) {
                    if (!Calisanlar.getCalisanList().isEmpty()) { // Eğer şirket çalışan listesi doluysa işleme geçer
                        System.out.print("Tüm calisanlar listeleniyor...");
                        Thread.sleep(1000);
                        for (int i = 0; i < 20; i++) {
                            System.out.print('>');
                            Thread.sleep(100);
                        }
                        System.out.println();
                        System.out.println(' ');
                        System.out.println("\n============================================");
                        Calisanlar.printCalisanlar();
                        System.out.println("============================================\n");
                    } else { // Liste dolu değilse aşağıdaki uyarıyı verir.
                        System.out.println("\033[1;31m" + "ŞİRKETTE ÇALIŞAN MEVCUT DEĞİLDİR\n " + "\033[0m\t");
                    }

                    // Secim 3 yapilir ise bir departman kodu istenecek ve o departmanda calisan tüm kisiler konsola
                    // print edilecek.
                } else if (secim1 == 3) {
                    System.out.print("Lütfen departman kodunu giriniz : ");
                    String departmanKodu = stringInput.nextLine();
                    System.out.print("Kontrol ediliyor... ");
                    Thread.sleep(1000);
                    for (int i = 0; i < 20; i++) {
                        System.out.print('>');
                        Thread.sleep(100);
                    }
                    System.out.println();
                    System.out.println(' ');
                    System.out.println("============================================");
                    Calisanlar.printDepartmandakiCalisanlar(departmanKodu);
                    System.out.println("============================================\n");
                } else if (secim1 == 4) {
                    System.out.print("\033[1;34m" + "Başarıyla çıkış yaptınız, iyi günler dileriz." + "\033[0m\t");
                    break;


                } else {// Kullanıcı tarafından secim1'e 1,2,3 ve 4 tamsayıları dışında bir tam sayı atanırsa
                    // aşağıdaki uyarıyı veriyor ve kullanıcıyı doğru seçime zorluyor
                    System.out.println("\033[1;31m" + "HATALI SEÇİM YAPTINIZ! \nTEKAR SEÇİM YAPINIZ!\n" + "\033[0m\t");
                }
            }
        }

    }

    public static void adSoyadKontrol(String calisanAdSoyad) { // BAŞARISIZ METOD

        Scanner oku = new Scanner(System.in);
        boolean kontrol = true;
        String regex = "^[a-zA-ZçÇğĞıİöÖşŞüÜ]+ [a-zA-ZçÇğĞıİöÖşŞüÜ]+$";
        while (true) {
            String calisanAdSoyad2 = calisanAdSoyad.replaceAll(" ", "");
            for (char a : calisanAdSoyad2.toCharArray()) {
                if (!Pattern.matches("^[a-zA-Z,ÜüĞğÇçŞşÖöİı]*$", calisanAdSoyad2)) {
                    System.out.print("\033[1;32m" + "İSİM VE SOYİSİM UYGUNSUZ KARAKTER İÇEREMEZ!" + "\033[0m\t" + "\nTekrar isim ve soyisim giriniz : ");
                    calisanAdSoyad = oku.nextLine();
                    break;

                } else {
                    kontrol = false;
                    break;
                }
            }
            if (!kontrol) {
                return;
            }

        }

    }

    public static String adSoyadKontrol2 (String adSoyad) { // BAŞARISIZ METOD
        Scanner oku = new Scanner(System.in);

        do {
            if (!KontrolAdSoyad(adSoyad)) {
                System.out.print("\033[1;32m" + "İSİM VE SOYİSİM UYGUNSUZ KARAKTER İÇEREMEZ!" + "\033[0m\t" +
                        "\nTekrar isim ve soyisim giriniz : ");
                adSoyad = oku.nextLine();
            }
        } while (!KontrolAdSoyad(adSoyad));
        return adSoyad;
    }

    public static boolean KontrolAdSoyad(String adSoyad) {
        String regex = "^[a-zA-ZçÇğĞıİöÖşŞüÜ]+ [a-zA-ZçÇğĞıİöÖşŞüÜ]+$";
        return Pattern.matches(regex, adSoyad);
    }
}


