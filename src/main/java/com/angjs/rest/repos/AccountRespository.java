package com.angjs.rest.repos;

import java.util.ArrayList;
import java.util.List;

import com.angjs.rest.entity.Account;

public class AccountRespository {
	
	List<Account> actList = null;
	
	public AccountRespository() {
		
		actList = new ArrayList<Account>();
		Account act1 = new Account();
		act1.setActId(001);
		act1.setActStatus("open");
		act1.setBalance("24500");
		act1.setDob("06-08-1983");
		act1.setHolderName("kuladeep parella");
		
		Account act2 = new Account();
		act2.setActId(002);
		act2.setActStatus("inactive");
		act2.setBalance("37000");
		act2.setDob("25-02-1986");
		act2.setHolderName("sunny");
		
		Account act3 = new Account();
		act3.setActId(003);
		act3.setActStatus("open");
		act3.setBalance("3000");
		act3.setDob("26-05-1988");
		act3.setHolderName("Buny");
		
		actList.add(act1);
		actList.add(act2);
		actList.add(act3);
	}
			
		
	
public List<Account> fetchAll() {
	return actList;	
}

public Account fetchById(int id) {
	Account retAct = new Account();
	for(Account act: actList) {
		if(act.getActId() == id) {
			retAct = act;
			break;
		}
	}
	return retAct;
}


public String addNewAct(Account act) {
	String ret = null;
	if(act !=null){
		actList.add(act);
		ret = "Act added succesfully !";
	}else {
		ret =  "Act not added. please check the input !!";
	}
	return ret;
}




}
