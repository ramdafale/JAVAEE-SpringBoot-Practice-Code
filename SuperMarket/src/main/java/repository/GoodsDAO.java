package repository;

public interface GoodsDAO {

	public int addGoods(int goodsId, String goodsName, int goodsQuantity,
			double goodsPrice);

	public String removeGoods(int goodsId);

	public String updateGoods(int goodsId);
}
