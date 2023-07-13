/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author lucif
 */
public class ChangeFormat {
    
    public String coverToDouble(String a){
        return a.replace(".", "").replace("₫","").replace(" ", "");
        
    }
}
