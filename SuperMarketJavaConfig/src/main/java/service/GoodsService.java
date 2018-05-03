package service;

import model.Goods;

public interface GoodsService {

	/**
	 * @method this method is calling a Repository Goods which connect to database
	 *         table Goods and adding goodsID, name,quantity,price
	 * 
	 */
	int addGoods(Goods goods);

	/**
	 * @method this method is calling a Repository Goods which connect to database
	 *         table Goods and removing a perticular Goods from table
	 * 
	 */
	int removeGoods(int goodsId);

	/**
	 * @method this method is calling a Repository Goods which connect to database
	 *         table Goods and updating a perticular Goods
	 * 
	 */
	int updateGoods(int goodsId, String goodsName);
	// public String orderGoods(int goodsId);

}
