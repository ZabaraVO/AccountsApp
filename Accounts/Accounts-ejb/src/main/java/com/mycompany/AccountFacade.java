/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vlad
 */
@Stateless
public class AccountFacade implements AccountFacadeLocal {
    
    @PersistenceContext(unitName = "com.mycompany_Accounts-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    private Class<Account> entityClass=Account.class;
    
    public Account find(int id){
        return em.find(entityClass, id);
    }
    
    public void openAccount(int sum){
        Account acc=new Account(sum);
        em.persist(acc);
    }    
    
    public void closeAccount(int id) throws Exception {
        Account accToClose=em.find(entityClass, id);
        lockElement(accToClose);
        em.remove(em.merge(accToClose));
    }  
    
    public void operateMoney(int id, int sum, ActionType action) throws Exception {
        Account accToChange=em.find(entityClass, id);
        lockElement(accToChange);
        switch(action){
            case PUT:
                accToChange.putMoney(sum);
                break;
            case TAKE:
                accToChange.takeMoney(sum);
        }
    }
    
    public void transferMoney(int idFrom,int idTo, int sum) throws Exception{
        operateMoney(idFrom,sum,ActionType.TAKE);
        operateMoney(idTo,sum,ActionType.PUT);
    }
    
    private void lockElement(Account accToLock) throws Exception {
        if(accToLock==null){
            throw new AccountNotFoundException();
        }
        try{
            em.lock(accToLock, LockModeType.PESSIMISTIC_WRITE);
        }
        catch(Exception e){
            throw e;
        }
    }
}
