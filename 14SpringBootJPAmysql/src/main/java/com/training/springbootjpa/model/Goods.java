package com.training.springbootjpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
public class Goods {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long goodsId;
	private String goodsName;
	private Integer goodsQuantity;
	private Double goodsPrice;
	
	
	
	/**
	 * 
	 */
	public Goods() {
		super();
	}

	/**
	 * @param goodsId
	 * @param goodsName
	 * @param goodsQuantity
	 * @param goodsPrice
	 * @param supplierId
	 */
	public Goods(Long goodsId, String goodsName, Integer goodsQuantity, Double goodsPrice, Long supplierId) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsQuantity = goodsQuantity;
		this.goodsPrice = goodsPrice;
		
	}
	
	/**
	 * @return the goodsId
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * @return the goodsQuantity
	 */
	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}
	/**
	 * @param goodsQuantity the goodsQuantity to set
	 */
	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	/**
	 * @return the goodsPrice
	 */
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	/**
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsQuantity=" + goodsQuantity
				+ ", goodsPrice=" + goodsPrice + "]";
	}

}
