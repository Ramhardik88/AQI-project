package io.javabrains.AQITracker.model;

public class ComponentStats {

    private String Month;
    private String GMT;
    private String Nitric_Oxide;
    private String  Nitrogen_Dioxide;
    private String Nitrogen;
    private String Ozone;
    private String  PM10;
    private String    PM2_5;
    private String Sulphur_Dioxide;

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getGMT() {
        return GMT;
    }

    public void setGMT(String GMT) {
        this.GMT = GMT;
    }

    public String getNitric_Oxide() {
        return Nitric_Oxide;
    }

    public void setNitric_Oxide(String nitric_Oxide) {
        Nitric_Oxide = nitric_Oxide;
    }

    public String getNitrogen_Dioxide() {
        return Nitrogen_Dioxide;
    }

    public void setNitrogen_Dioxide(String nitrogen_Dioxide) {
        Nitrogen_Dioxide = nitrogen_Dioxide;
    }

    public String getNitrogen() {
        return Nitrogen;
    }

    public void setNitrogen(String nitrogen) {
        Nitrogen = nitrogen;
    }

    public String getOzone() {
        return Ozone;
    }

    public void setOzone(String ozone) {
        Ozone = ozone;
    }

    public String getPM10() {
        return PM10;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public String getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(String PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public String getSulphur_Dioxide() {
        return Sulphur_Dioxide;
    }

    public void setSulphur_Dioxide(String sulphur_Dioxide) {
        Sulphur_Dioxide = sulphur_Dioxide;
    }

    @Override
    public String toString() {
        return "ComponentStats{" +
                "Month='" + Month + '\'' +
                ", GMT='" + GMT + '\'' +
                ", Nitric_Oxide='" + Nitric_Oxide + '\'' +
                ", Nitrogen_Dioxide='" + Nitrogen_Dioxide + '\'' +
                ", Nitrogen='" + Nitrogen + '\'' +
                ", Ozone='" + Ozone + '\'' +
                ", PM10='" + PM10 + '\'' +
                ", PM2_5='" + PM2_5 + '\'' +
                ", Sulphur_Dioxide='" + Sulphur_Dioxide + '\'' +
                '}';
    }
}
