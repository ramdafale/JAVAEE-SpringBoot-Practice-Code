package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GoodsDAO;

/**
 * @author Sumit
 *
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDAO goodsDAO;
	
	/**
	 * @param goodsDAO
	 */
	public GoodsServiceImpl(GoodsDAO goodsDAO) {
		super();
		this.goodsDAO = goodsDAO;
	}

	@Override
	public String addGoods(String goodsId, String goodsName, int goodsQuantity,
			double goodsPrice, String supplierId) {
		String addData = goodsDAO.addGoods(goodsId, goodsName, goodsQuantity,
				goodsPrice, supplierId);
		return addData;
	}

	@Override
	public String removeGoods(String goodsId) {
		String remmoveData = goodsDAO.removeGoods(goodsId);
		return remmoveData;
	}

	@Override
	public String updateGoods(String goodsId) {
		String updateData = goodsDAO.updateGoods(goodsId);
		return updateData;
	}

}
