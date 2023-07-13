/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.Theloai;
import ViewModels.TheLoaiModel;
import java.util.ArrayList;
import java.util.List;
import repositories.ImplBangTheLoai;

/**
 *
 * @author LuongQuocBao
 */
public class TheLoaiService implements ITheLoaiService{

     private final ImplBangTheLoai _daotl;
    private List<TheLoaiModel> _lstTheLoaiMod = new ArrayList<TheLoaiModel>();
    private List<Theloai> _lstTheLoai = new ArrayList<Theloai>();

    public TheLoaiService() {
        _daotl = new ImplBangTheLoai();
    }
    
    @Override
    public List<TheLoaiModel> getTheLoai(int position, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TheLoaiModel getTheLoaiById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TheLoaiModel createNewTheLoai(TheLoaiModel theLoaiModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TheLoaiModel updateTheLoaiById(TheLoaiModel theLoaiModel) {
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
