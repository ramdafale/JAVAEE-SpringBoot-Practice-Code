/**
 * 
 */
package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import model.Goods;

/**
 * @author trainee
 *
 */
public class RetailerDAOImpl implements RetailerDAO {

	private JdbcTemplate jdbcTemplate;

	/*@Override
	public int viewGoods() {
		// TODO Auto-generated method stub
		String query = "select * from goods";
		System.out.println(query);
		return jdbcTemplate.update(query);

	}*/

	
	public List<Goods> getAllGoods(){  
		 return jdbcTemplate.query("select * from goods",new ResultSetExtractor<List<Goods>>(){  
		    @Override  
		     public List<Goods> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<Goods> list=new ArrayList<Goods>();  
		        while(rs.next()){  
		        Goods e=new Goods();  
		        e.setGoodsId(rs.getInt(1));  
		        e.setGoodsName(rs.getString(2));  
		        e.setGoodsQuantity(rs.getInt(3));
		        e.setGoodsPrice(rs.getInt(4));
		        list.add(e); 
		        System.out.println(e.getGoodsName());
		        System.out.println(e.getGoodsPrice());
		        System.out.println(e.getGoodsQuantity());
		       
		        
		        }  
		        
		        return list;  
		        }  
		    });  
		  }  

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int viewCustomer(int customerId) {
		// TODO Auto-generated method stub
		String query = "select * from goods where customerId=" + customerId;
		System.out.println(query);
		return jdbcTemplate.update(query);

	}

	@Override
	public int viewSupplier(int supplierId) {
		// TODO Auto-generated method stub
		String query = "select * from goods where supplierId=" + supplierId;
		System.out.println(query);
		return jdbcTemplate.update(query);
	}

	@Override
	public List<Goods> viewGoods() {
		// TODO Auto-generated method stub
		return null;
	}



}
