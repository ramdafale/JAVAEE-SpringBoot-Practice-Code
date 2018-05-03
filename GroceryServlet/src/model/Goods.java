package model;

public class Goods {

	private String goodsId;
	private String goodsName;
	private int goodsQuantity;
	private double goodsPrice;
	private String supplierId;

	/**
	 * @param goodsId
	 * @param goodsName
	 * @param goodsQuantity
	 * @param goodsPrice
	 * @param supplierId;
	 */
	public Goods(String goodsId, String goodsName, int goodsQuantity,
			double goodsPrice, String supplierId) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsQuantity = goodsQuantity;
		this.goodsPrice = goodsPrice;
		this.supplierId = supplierId;
	}

	/**
	 * @return the goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId
	 *            the goodsId to set
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return the goodsQuantity
	 */
	public int getGoodsQuantity() {
		return goodsQuantity;
	}

	/**
	 * @param goodsQuantity
	 *            the goodsQuantity to set
	 */
	public void setGoodsQuantity(int goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	/**
	 * @return the goodsPrice
	 */
	public double getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * @param goodsPrice
	 *            the goodsPrice to set
	 */
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

}
