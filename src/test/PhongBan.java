package test;


public class PhongBan {
    private String tenPhongBan;
    private byte soNhanVien;
    private static final byte SO_NV_MAX = 100;
    private Stack<NhanVien> dsnv; 

    public PhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
        this.soNhanVien = 0;
        this.dsnv = new Stack<>(); 
    }

    public boolean themNV(NhanVien nv) {
        if (soNhanVien < SO_NV_MAX) {
            dsnv.push(nv); 
            soNhanVien++;
            return true; 
        }
        return false; 
    }

    public NhanVien xoaNV() {
        if (!dsnv.isEmpty()) {
            soNhanVien--; 
            return dsnv.pop(); 
        }
        return null; 
    }

    public double tongLuong() {
        double tong = 0;
        
        for (int i = 0; i < soNhanVien; i++) {
            NhanVien nv = dsnv.pop(); 
            if (nv != null) {
                tong += nv.tinhLuong(); 
                dsnv.push(nv); 
            }
        }
        return tong; 
    }

    public void inTTin() {
        System.out.println("Thông tin phòng: " + tenPhongBan);
        System.out.println("Số nhân viên: " + soNhanVien);
        
        
        for (int i = 0; i < soNhanVien; i++) {
            NhanVien nv = dsnv.pop(); 
            if (nv != null) {
                nv.inTTin(); 
                dsnv.push(nv); 
            }
        }
    }
}

