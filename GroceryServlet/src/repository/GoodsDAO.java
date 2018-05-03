package repository;

public interface GoodsDAO {

	public String addGoods(String goodsId, String goodsName, int goodsQuantity,
			double goodsPrice, String supplierId);

	public String removeGoods(String goodsId);

	public String updateGoods(String goodsId);
}
