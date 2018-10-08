package com.tangcheng.service;

import org.springframework.stereotype.Service;
import com.tangcheng.pojo.Collection;

@Service
public interface CollectionService {
	
	public void colletion(Collection col,String shopid);
	
	public void delectcol(String col_id, String shopid);

}
