/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import javax.ejb.Local;

/**
 *
 * @author Vlad
 */
@Local
public interface AccountFacadeLocal {

    public Account find(int id);
    
    public void openAccount(int sum);
    
    public void closeAccount(int id) throws Exception;
    
    public void operateMoney(int id, int sum, ActionType action) throws Exception;
    
    public void transferMoney(int idFrom,int idTo, int sum) throws Exception;
}
