package com.tangcheng.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tangcheng.dao.CollectionMapper;
import com.tangcheng.dao.ShopMapper;
import com.tangcheng.pojo.Collection;
import com.tangcheng.service.CollectionService;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {
	
	@Autowired
	private ShopMapper shopMapper;
	
	@Autowired 
	private CollectionMapper collectionMapper;

	@Override
	public void colletion(Collection col, String shopid) {
		collectionMapper.save(col);
		shopMapper.updateCol(shopid);

	}

	@Override
	public void delectcol(String col_id, String shopid) {
		collectionMapper.delete(col_id);
		shopMapper.deleteCol(shopid);
	}

}
