package Modeller.Departmanlar;

public class YonetimDepartmani implements Departman {

    final String departmanKodu = "YD";

    // Zam oranini belirlemeyi unutmayalim. Başka bişeyi değiştirmeye gerek yok.
    final int zamOrani = 30;

    @Override
    public int getZamOrani() {
        return zamOrani;
    }

    @Override
    public String getDepartmanKodu() {
        return departmanKodu;
    }
}
