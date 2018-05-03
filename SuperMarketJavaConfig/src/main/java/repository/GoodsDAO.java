package repository;

import model.Goods;

public interface GoodsDAO {

	public int addGoods(Goods goods);

	public int removeGoods(int goodsId);

	public int updateGoods(int goodsId,String goodsName);
}
