package service;

public interface GoodsService {

	public int addGoods( int goodsId,String goodsName,int goodsQuantity,double goodsPrice);
	public String removeGoods(int goodsId);
	public String updateGoods(int goodsId);
	//public String orderGoods(int goodsId);
		
}
