package com.training.springbootjpa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.springbootjpa.model.Goods;
import com.training.springbootjpa.repository.GoodsDAO;

/**
 * @author  RamDAfale
 *
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDAO goodsDAO;

	@Override
	public Goods addGoods(Goods goods) {
		Goods goodsData = goodsDAO.save(goods);
		return goodsData;
	}

	@Override
	public List<Goods> deleteGoodsById(long deleteById) {
		List<Goods> goodsList = goodsDAO.findAll();
		Iterator iterator = goodsList.iterator();
		while (iterator.hasNext()) {
			Goods goods = (Goods) iterator.next();
			if (goods.getGoodsId() == deleteById) {
				goodsDAO.deleteById(deleteById);
			}
		}
		return goodsList;
	}

	@Override
	public Goods updateGoodsById(long updateById) {
		Optional<Goods> goods = goodsDAO.findById(updateById);
		Goods goodsDummy = goods.get();
		goodsDummy.setGoodsPrice(1021.12);
		return goodsDummy;
	}

}
