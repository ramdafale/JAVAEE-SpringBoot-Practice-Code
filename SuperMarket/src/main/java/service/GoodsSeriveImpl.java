package service;

import repository.GoodsDAO;

public class GoodsSeriveImpl implements GoodsService {

	@Override
	public int addGoods(int goodsId, String goodsName, int goodsQuantity,
			double goodsPrice) {
		GoodsDAO goodsDAO = null;
		int addData = goodsDAO.addGoods(goodsId, goodsName, goodsQuantity,
				goodsPrice);
		return addData;
	}

	@Override
	public String removeGoods(int goodsId) {
		GoodsDAO goodsDAO = null;
		String remmoveData = goodsDAO.removeGoods(goodsId);
		return remmoveData;
	}

	@Override
	public String updateGoods(int goodsId) {
		GoodsDAO goodsDAO = null;
		String updateData = goodsDAO.updateGoods(goodsId);
		return updateData;
	}

}
