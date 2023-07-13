/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.Theloai;
import ViewModels.TheloaiView;
import java.util.ArrayList;
import java.util.List;
import repositories.ImplBangTheLoai;

/**
 *
 * @author LuongQuocBao
 */
public class ServiceTheLoai implements IServiceTheLoai{

     private final ImplBangTheLoai _daotl;
    private List<TheloaiView> _lstTheLoaiMod = new ArrayList<TheloaiView>();
    private List<Theloai> _lstTheLoai = new ArrayList<Theloai>();

    public ServiceTheLoai() {
        _daotl = new ImplBangTheLoai();
    }
    
    @Override
    public List<TheloaiView> getTheLoai(int position, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TheloaiView getTheLoaiById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TheloaiView createNewTheLoai(TheloaiView theLoaiModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TheloaiView updateTheLoaiById(TheloaiView theLoaiModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteTheLoaiById(Theloai sp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Theloai> getlst() {
        return _daotl.findAll();
    }
}
