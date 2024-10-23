package test;

public class NhanVien {
    private String tenNhanVien;
    private double heSoLuong;
    private static final double LUONG_CO_BAN = 750.000;
    private static final double LUONG_MAX = 20000.000;

    public NhanVien(String tenNhanVien, double heSoLuong) {
        this.tenNhanVien = tenNhanVien;
        this.heSoLuong = heSoLuong;
    }
    
    public boolean tangLuong(double heSoLuong) {
    	if (heSoLuong <= (LUONG_MAX / LUONG_CO_BAN)) {
    		this.heSoLuong = heSoLuong;
    		return true;
    	}
    	return false;
    }

    public double tinhLuong() {
        return LUONG_CO_BAN * heSoLuong; 
    }

    public void inTTin() {
        System.out.println("Tên nhân viên: " + tenNhanVien + ", Lương: " + tinhLuong());
    }
}

