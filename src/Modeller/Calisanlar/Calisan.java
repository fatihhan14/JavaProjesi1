package Modeller.Calisanlar;

import Modeller.Departmanlar.Departman;
import Veritabani.Calisanlar;
import Veritabani.Departmanlar;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Calisan {

    private String calisanId;
    private String adSoyad;
    private int maas;
    private Departman Departman;
    private String isimKodu = "";

    public Calisan(String adSoyad, int maas, String departmanKodu) {
       // setAdSoyad(adSoyad);
        this.adSoyad=adSoyad;
        this.maas = maas;
        setDepartman(departmanKodu);
        this.setCalisanId();  // constructor çalıştığında, aşağıda tanımlayacağınız bu metod vasıtasıyla tekil bi ID alacak...
        // Örn: Şirkette 257 calisan var, Bilişim teklonojileri departmaninda Mehmet Ali Bulut kaydedilecek olsun,
        // Mehmet Ali icin ID 'BTD258MAB' olmalıdır.
        Calisanlar.addACalisan(this);
    }

    public void setAdSoyad(String adSoyad) {
        Scanner oku = new Scanner(System.in);
        do {
            if (!KontrolAdSoyad(adSoyad)) {
                System.out.print("\033[1;32m" + "İSİM VE SOYİSİM UYGUNSUZ KARAKTER İÇEREMEZ!" + "\033[0m\t" +
                        "\nTekrar isim ve soyisim giriniz : ");
                adSoyad = oku.nextLine();
            }
        } while (!KontrolAdSoyad(adSoyad));

        this.adSoyad = adSoyad;

    }

    private boolean KontrolAdSoyad(String adSoyad) {
        String regex = "^[a-zA-ZçÇğĞıİöÖşŞüÜ]+ [a-zA-ZçÇğĞıİöÖşŞüÜ]+$";
        return Pattern.matches(regex, adSoyad);
    }

    // Terminalden girilen calisanin departman koduna göre, gerekli departman set edilmelidir.
    // Çalışan sınıfının Constructor 'ı main'de tanımlı. Program çalıştığında, terminalden gireceğimiz
    // departman kodu, buradaki metod (setDepartman) vasıtasıyla departman listesinin tümünü dolaşıp (foreach)
    // getDepartmanKodu() ile bulunan sonuçlardan biriyle aynı mı? Diye bakıyoruz...
    // Aynıysa, "terminalden girilen çalışanı bu departmana set et" demektir...
    private void setDepartman(String departman) {
        /*
            İpucu: Departman listesinin (Veritabani.Departmanlar.DepartmanList) içerisindeki departmanların kodları var,
        bu kodlari donguye tutmak ise yarayabilir.
       */
        boolean devam = true;
        Scanner oku = new Scanner(System.in);

        while (devam) {
            for (Departman D : Departmanlar.getDepartmanList()) {
                if (D.getDepartmanKodu().equalsIgnoreCase(departman)) {
                    this.Departman = D;
                    devam = false;
                    return;
                }
            }
            System.out.print("\033[1;31m" + "GEÇERSİZ DEPARTMAN KODU!" + "\033[0m\t" + "\nTekrar kod giriniz : ");
            departman = oku.nextLine();
        }


    }

    // Calisanin ID sinin kendisine özel olduğundan bahsetmistik, ID nin nasil kaydedileceği CalisanKaydetmeProjesiTanıtım.txt
    // içerisinde yer aliyor.
    private void setCalisanId() {
        /*
            İpucu: Detayli anlatim CalisanKaydetmeProjesiTanıtım.txt içerisinde.
         */
        String departmanKodu = Departman.getDepartmanKodu();
        String isimKod = getCalisanIsimKodu();
        int calisanSayi = Calisanlar.getCalisanList().size() + 1;
        this.calisanId = departmanKodu + calisanSayi + isimKod;

    }

    // Calisanin ID sinin sonuna isim kodu eklenmesi için, ismi parçalayan bir method.
    private String getCalisanIsimKodu() {
        /*

            Basit string metodlari ise isinize cok yarayacaktir fakat dinamik olmasina dikkat edelim... Mesela 2 isim bir
            soyisim girildiğinde hata vermesin.

         */
        String[] adlar = adSoyad.split(" ");
        for (String X : adlar) {
            isimKodu += X.charAt(0);
        }
        return isimKodu.toUpperCase();
    }

    // Calisanin id sini almak icin basit getter method
    public String getCalisanId() {
        return this.calisanId;
    }

    // Calisanin departmanini almak icin basit getter method
    public Departman getDepartman() {
        return this.Departman;
    }

    // Departman adini verebilmek için bir method
    public String getDepartmanAdi() {
        /*
                İpucu: Departman Kodu YD ise departman adi Yonetim Departmani olarak kaydedilmelidir.
         */
        if (Departman.getDepartmanKodu().equalsIgnoreCase("YD")) {
            return "Yönetim Departmanı";
        } else if (Departman.getDepartmanKodu().equalsIgnoreCase("IKD")) {
            return "İnsan Kaynakları Departmanı";
        } else
            return "Bilişim Teknolojileri Departmanı";
    }

    // Calisana zam yapilmasi için gerekli bir method
    public static int zamYap(String calisanId, int secim1) {
        /*

            İpucu: Calisan ID si kullanilarak yapilmalidir, diğer attributelarin aynilarindan 1 er tane daha
            olabilirdi.

         */

        boolean devam = true;
        Scanner oku = new Scanner(System.in);
        Scanner oku2 = new Scanner(System.in);
        secim1 = 0;
        while (devam) {
            for (Calisan a : Calisanlar.getCalisanList()) {
                if (a.getCalisanId().equalsIgnoreCase(calisanId)) {
                    a.maas = ((a.maas * a.Departman.getZamOrani()) / 100) + a.maas;
                    System.out.println("İşleminiz başarı ile gerçekleştirilmiştir.");
                    return secim1;
                }
            }

            System.out.print("\033[1;31m" + "ÇALIŞAN BULUNAMADI!" + "\033[0m\t" + "\nBİR ÖNCEKİ MENÜ İÇİN" + "\033[1;34m" + " 'M'"
                    + "\033[0m\t" + "\nTEKRAR ID GİRMEK İÇİN" + "\033[1;34m" + " 'herhangi bir harf'" + "\033[0m\t" + "giriniz : ");
            String secim = oku2.next();
            if (secim.equalsIgnoreCase("M")) {
                System.out.println("Onceki Menuye aktariliyorsunuz.... ");
                return secim1 = 1;
            } else {
                System.out.print("Tekrar ID giriniz : ");
                calisanId = oku.nextLine();
            }
        }
        return secim1;
    }

    // Calisanlari yazdırmak için gerekli bir override
    @Override
    public String toString() {

        /*

            İpucu: Detayli anlatim CalisanKaydetmeProjesiTanıtım.txt içerisinde.

         */
        String departmanAdi = getDepartmanAdi();
        return "\033[1;36m" + "Çalışan ID    " + "\033[0m\t" + ": " + "\033[1m" + calisanId + "\033[0m\t" + "\n"
                + "\033[1;36m" + "Ad Soyad      " + "\033[0m\t" + ": " + "\033[1m" + adSoyad + "\033[0m\t" + "\n"
                + "\033[1;36m" + "Çalışan Maaşı " + "\033[0m\t" + ": " + "\033[1m" + maas + " TL" + "\033[0m\t" + "\n"
                + "\033[1;36m" + "Departmanı    " + "\033[0m\t" + ": " + "\033[1m" + departmanAdi + "\033[0m\t";
    }
}
