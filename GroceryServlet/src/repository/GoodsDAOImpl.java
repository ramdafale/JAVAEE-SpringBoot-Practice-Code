package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Sumit
 */
@Repository
public class GoodsDAOImpl implements GoodsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 */
/*	public GoodsDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
*/
	@Override
	public String addGoods(final String goodsId, final String goodsName, final int goodsQuantity,
			final double goodsPrice, final String supplierId) {
		try {
			jdbcTemplate.update("INSERT INTO Goods values('" + goodsId + "','"
					+ goodsName + "','" + goodsQuantity + "','" + goodsPrice
					+ "','" + supplierId + "')");
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Goods added";
	}

	public String removeGoods(final String goodsId) {
		try {
			String sql = "DELETE FROM Goods where goodsId=?";
			jdbcTemplate.update(sql, goodsId);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Goods deleted";
	}

	public String updateGoods(final String goodsId) {
		try {
			String sql = "UPDATE Goods set goodsQuantity=1 where goodsId=?";
			jdbcTemplate.update(sql, goodsId);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Goods updated";
	}
}
