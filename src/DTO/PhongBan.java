/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ACER
 */
public class PhongBan {
    private String _id;
    private String _ten;
    private String _matkhau;

    public PhongBan() {
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public void setTen(String _ten) {
        this._ten = _ten;
    }

    public void setMatkhau(String _matkhau) {
        this._matkhau = _matkhau;
    }

    public PhongBan(String _id, String _ten, String _matkhau) {
        this._id = _id;
        this._ten = _ten;
        this._matkhau = _matkhau;
    }

    public String getId() {
        return _id;
    }

    public String getTen() {
        return _ten;
    }

    public String getMatkhau() {
        return _matkhau;
    }
     
    
}
