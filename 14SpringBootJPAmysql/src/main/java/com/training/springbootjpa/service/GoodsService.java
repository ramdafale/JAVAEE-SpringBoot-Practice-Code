package com.training.springbootjpa.service;

import java.util.List;
import com.training.springbootjpa.model.Goods;

public interface GoodsService {

	Goods addGoods(Goods goods);

	public List<Goods> deleteGoodsById(long deleteById);

	public Goods updateGoodsById(long updateById);

}
