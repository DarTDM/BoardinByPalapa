package boardin;

import java.sql.Date;

public class penghuni {

    private int idSC;
    private String namaSC;
    private String nikSC;
    private String nomorhpSC;
    private String nomorwaliSC;
    private String kotaSC;
    private String nomorkamarSC;
    private Date tanggalSC;

    public penghuni(String namaSC, String nikSC, String nomorhpSC,
                    String nomorwaliSC, String kotaSC, String nomorkamarSC, Date tanggalSC, int idSC) {
        this.namaSC = namaSC;
        this.nikSC = nikSC;
        this.nomorhpSC = nomorhpSC;
        this.nomorwaliSC = nomorwaliSC;
        this.kotaSC = kotaSC;
        this.nomorkamarSC = nomorkamarSC;
        this.tanggalSC = tanggalSC;
        this.idSC = idSC;
    }

    public int getIdSC() {
        return idSC;
    }

    public void setIdSC(int idSC) {
        this.idSC = idSC;
    }

    public String getNamaSC() {
        return namaSC;
    }

    public void setNamaSC(String namaSC) {
        this.namaSC = namaSC;
    }

    public String getNikSC() {
        return nikSC;
    }

    public void setNikSC(String nikSC) {
        this.nikSC = nikSC;
    }

    public String getNomorhpSC() {
        return nomorhpSC;
    }

    public void setNomorhpSC(String nomorhpSC) {
        this.nomorhpSC = nomorhpSC;
    }

    public String getNomorwaliSC() {
        return nomorwaliSC;
    }

    public void setNomorwaliSC(String nomorwaliSC) {
        this.nomorwaliSC = nomorwaliSC;
    }

    public String getKotaSC() {
        return kotaSC;
    }

    public void setKotaSC(String kotaSC) {
        this.kotaSC = kotaSC;
    }

    public String getNomorkamarSC() {
        return nomorkamarSC;
    }

    public void setNomorkamarSC(String nomorkamarSC) {
        this.nomorkamarSC = nomorkamarSC;
    }

    public Date getTanggalSC() {
        return tanggalSC;
    }

    public void setTanggalSC(Date tanggalSC) {
        this.tanggalSC = tanggalSC;
    }

}