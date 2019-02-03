package e.nasirbashak007.mywallet;

public class DailyPacket {

    String date;
    String totalPackets;
    String deliveredPackets;
    String attemptedPackets;
    String rejectedPackets;


    public DailyPacket() {


    }

    public DailyPacket(String date, String totalPackets, String deliveredPackets, String attemptedPackets, String rejectedPackets) {
        this.date = date;
        this.totalPackets = totalPackets;
        this.deliveredPackets = deliveredPackets;
        this.attemptedPackets = attemptedPackets;
        this.rejectedPackets = rejectedPackets;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalPackets() {
        return totalPackets;
    }

    public void setTotalPackets(String totalPackets) {
        this.totalPackets = totalPackets;
    }

    public String getDeliveredPackets() {
        return deliveredPackets;
    }

    public void setDeliveredPackets(String deliveredPackets) {
        this.deliveredPackets = deliveredPackets;
    }

    public String getAttemptedPackets() {
        return attemptedPackets;
    }

    public void setAttemptedPackets(String attemptedPackets) {
        this.attemptedPackets = attemptedPackets;
    }

    public String getRejectedPackets() {
        return rejectedPackets;
    }

    public void setRejectedPackets(String rejectedPackets) {
        this.rejectedPackets = rejectedPackets;
    }


}
