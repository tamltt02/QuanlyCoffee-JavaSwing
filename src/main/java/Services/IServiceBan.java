/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModel.Ban;
import DomainModel.Hoadoinchitiet;
import ViewModels.BanView;
import ViewModels.SelectedItems;
import java.util.List;

/**
 *
 * @author lucif
 */
public interface IServiceBan {
    
    List<BanView> findByStatus(int status);
    
    List<BanView> findBanGop(int idBanGop);
    
    List<Hoadoinchitiet> showSelectedItems(int ID_ban, int trangthai);
    
    void chuyenBan (int idBanDi, int idBanToi);
    
    void gopBan ( int idBanDi , int idBanToi);
}
