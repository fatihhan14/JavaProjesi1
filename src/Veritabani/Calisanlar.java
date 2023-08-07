package Veritabani;

import Modeller.Calisanlar.Calisan;
import Modeller.Departmanlar.Departman;

import java.util.ArrayList;
import java.util.Scanner;

public class Calisanlar {


    // Buradaki calisanList static cünkü proje calismaya basladiği anda oluşması lazım. Bunu bir veritabani
    // gibi görebiliriz. Calisanlarimizin hepsi bu liste icerisinde yer alacak.
    private static ArrayList<Calisan> calisanList = new ArrayList<>();

    // Calisanlari almak icin basit bir getter method
    public static ArrayList<Calisan> getCalisanList() {
        return calisanList;
    }

    // Bir çalışan eklemek için gerekli method.
    public static void addACalisan(Calisan calisan) {
        calisanList.add(calisan);
    }


    // Bir çalışan silmek için gerekli method.
    public static int deleteACalisanWithId(String calisanId, int secim1) {

        boolean devam = true;
        Scanner oku = new Scanner(System.in);
        Scanner oku2 = new Scanner(System.in);
        secim1 = 0;
        while (devam) {
            for (int i = 0; i < calisanList.size(); i++) {
                Calisan arananCalisan = calisanList.get(i);
                if (arananCalisan.getCalisanId().equalsIgnoreCase(calisanId)) {
                    calisanList.remove(i);
                    System.out.println(calisanId.toUpperCase() + " Başarı ile silindi. Onceki Menuye aktariliyorsunuz....\n");
                    return secim1;
                }
            }
            System.out.print("\033[1;31m" + "ÇALIŞAN BULUNAMADI!" + "\033[0m\t" + "\nBİR ÖNCEKİ MENÜ İÇİN" + "\033[1;34m" + " 'M'"
                    + "\033[0m\t" + "\nTEKRAR ID GİRMEK İÇİN" + "\033[1;34m" + " 'herhangi bir harf'" + "\033[0m\t" + "giriniz : ");
            String secim = oku2.next();
            if (secim.equalsIgnoreCase("M")) {
                System.out.println("Önceki Menuye aktariliyorsunuz.... \n");
                return secim1 = 1;
            } else {
                System.out.print("Tekrar ID giriniz : ");
                calisanId = oku.nextLine();
            }

        }
        return secim1;
    }

    // Departman kodu verilerek, konsola sadece o departmanda calisanlari yazdirmak için
    public static void printDepartmandakiCalisanlar(String departmanKodu) {
        boolean kodKontrol = false;
        if (departmanKodu.equalsIgnoreCase("yd"))
            kodKontrol = true;
        else if (departmanKodu.equalsIgnoreCase("btd")) {
            kodKontrol = true;
        } else if (departmanKodu.equalsIgnoreCase("ikd")) {
            kodKontrol = true;
        }

        if (kodKontrol) {
            for (Calisan X : calisanList) {
                if (X.getDepartman().getDepartmanKodu().equalsIgnoreCase(departmanKodu)) {
                    for (int i = 0; i <1; i++) {
                        if (X.getDepartman().getDepartmanKodu().equalsIgnoreCase(departmanKodu)) {
                            System.out.println(X.getDepartmanAdi() + " Çalışanları \n");
                            break;
                        }
                    }
                    for (Calisan X3 : calisanList) {
                        if (X3.getDepartman().getDepartmanKodu().equalsIgnoreCase(departmanKodu)) {
                            System.out.println(X3);
                            System.out.println("=------------------------------------------=");
                        }
                    }
                    return;
                }
            }
            System.out.println("\033[1;31m" + "DEPARTMANDA ÇALIŞAN MEVCUT DEĞİLDİR" + "\033[0m\t");

        } else {
            System.out.println("\033[1;31m" + "HATALI DEPARTMAN KODU GİRDİNİZ!" + "\033[0m\t");
        }
    }


    // Calisanlari konsola yazdirmak için
    public static void printCalisanlar() {
        System.out.println("\t\tTÜM ÇALIŞANLAR\n");
        for (Calisan X : calisanList) {
            System.out.println(X);
            System.out.println("=------------------------------------------=");
        }
    }


}
