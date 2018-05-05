package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GoodsDAO;

/**
 * @author Ram
 *
 */
@Service("goodsService")
public class GoodsSeriveImpl implements GoodsService {

	@Autowired
	private GoodsDAO goodsDAO;
	
	/**
	 * @param goodsDAO
	 */
	public GoodsSeriveImpl(GoodsDAO goodsDAO) {
		super();
		this.goodsDAO = goodsDAO;
	}

	@Override
	public int addGoods(int goodsId, String goodsName, int goodsQuantity,
			double goodsPrice) {
		int addData = goodsDAO.addGoods(goodsId, goodsName, goodsQuantity,
				goodsPrice);
		return addData;
	}

	@Override
	public int removeGoods(int goodsId) {
		int remmoveData = goodsDAO.removeGoods(goodsId);
		return remmoveData;
	}

	@Override
	public int updateGoods(int goodsId, String goodsName) {
		int updateData = goodsDAO.updateGoods(goodsId,goodsName);
		return updateData;
	}





}
