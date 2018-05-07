package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Goods;
import repository.GoodsDAO;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	public GoodsServiceImpl() {

	}

	@Autowired
	private GoodsDAO goodsdao;

	public GoodsServiceImpl(final GoodsDAO goodsdao) {
		super();
		this.goodsdao = goodsdao;
	}

	@Override
	public int addGoods(int goodsId, String goodsName, int goodsQuantity,
			double goodsPrice) {
		
		int addData = goodsdao.addGoods(goodsId,goodsName,goodsQuantity,goodsPrice);
		return addData;
	}

	@Override
	public int removeGoods(int goodsId) {
		// GoodsDAO goodsDAO = null;
		int remmoveData = goodsdao.removeGoods(goodsId);
		return remmoveData;
	}

	@Override
	public int updateGoods(int goodsId, String goodsName) {
		// GoodsDAO goodsDAO = null;
		int updateData = goodsdao.updateGoods(goodsId, goodsName);
		return updateData;
	}

}
