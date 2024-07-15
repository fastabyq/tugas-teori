
public class Mhs {
    private String nim;
    private String nama;
    private double uts;
    private double uas;
    private double tugas;

    public Mhs(String nim, String nama, double uts, double uas, double tugas) {
        this.nim = nim;
        this.nama = nama;
        this.uts = uts;
        this.uas = uas;
        this.tugas = tugas;
    }

    public double uts() {
        return uts;
    }

    public double uas() {
        return uas;
    }

    public double tugas() {
        return tugas;
    }

    public double nilaiAkhir() {
        return (uts + uas + tugas) / 3;
    }

    public String getNilHuruf(double nilai) {
        if (nilai >= 80) return "A";
        else if (nilai >= 70) return "B";
        else if (nilai >= 60) return "C";
        else if (nilai >= 50) return "D";
        else return "E";
    }

    public String getPredikat(String nilHuruf) {
        switch (nilHuruf) {
            case "A": return "Wapik";
            case "B": return "Apik";
            case "C": return "Bolehh";
            case "D": return "Kurang";
            default: return "Gagal";
        }
    }
}

