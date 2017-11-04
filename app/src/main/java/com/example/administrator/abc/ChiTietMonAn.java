package com.example.administrator.abc;

/**
 * Created by Administrator on 04/11/2017.
 */

public class ChiTietMonAn {
    public String TenMonAn;
    public String TomTat;
    public String NguyenLieu;
    public String CachLam;
    public byte[] AnhMA;

    public ChiTietMonAn(String tenMonAn, String tomTat, String nguyenLieu, String cachLam, byte[] anh) {
        TenMonAn = tenMonAn;
        TomTat = tomTat;
        NguyenLieu = nguyenLieu;
        CachLam = cachLam;
        AnhMA = anh;
    }
}
