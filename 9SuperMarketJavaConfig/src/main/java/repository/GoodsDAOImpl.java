package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Goods;

/**
 * @author Ram
 *
 */
@Repository()
@Qualifier("GoodsDAO")
public class GoodsDAOImpl implements GoodsDAO {

	// private DataSource dataSource;

	// private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * configuring a jdbc template
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate1(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addGoods(int goodsId, String goodsName, int goodsQuantity,
			double goodsPrice) {
		String query = "insert into goods values(" + goodsId + "," + "'" + goodsName + "'" + "," + goodsQuantity+ ","
				+ goodsPrice + ")";
		System.out.println(query);

		return jdbcTemplate.update(query);
	}

	@Override
	public int updateGoods(int goodsId, String goodsName) {
		String query = "update goods set goodsName=" + "'" + goodsName + "'" + " where goodsId=" + goodsId;
		// String query = "update customer set customerName="+name+" where customerId="+
		// customerID;

		System.out.println(query);
		return jdbcTemplate.update(query);
	}

	@Override
	public int removeGoods(int goodsId) {

		String query = "delete from goods where goodsId=" + goodsId;
		System.out.println(query);
		return jdbcTemplate.update(query);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
