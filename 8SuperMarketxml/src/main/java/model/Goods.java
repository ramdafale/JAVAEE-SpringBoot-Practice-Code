package model;

public class Goods {

	private int goodsId;
	private String goodsName;
	private int goodsQuantity;
	private double goodsPrice;

	/**
	 * @param goodsId
	 * @param goodsName
	 * @param goodsQuantity
	 * @param goodsPrice
	 */
	public Goods(int goodsId, String goodsName, int goodsQuantity,
			double goodsPrice) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsQuantity = goodsQuantity;
		this.goodsPrice = goodsPrice;
	}

	public Goods() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the goodsId
	 */
	public int getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId
	 *            the goodsId to set
	 */
	public void setGoodsId(int goodsId) {
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
