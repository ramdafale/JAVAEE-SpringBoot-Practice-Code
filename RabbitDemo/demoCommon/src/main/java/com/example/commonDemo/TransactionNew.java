/**
 * 
 */
package com.example.commonDemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * @author trainee
 *
 */

public class TransactionNew implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7197575110101065396L;
	int transactionId;
	int customerId;
	int accountId;
	int amount;
	String transactionType;
	
	public TransactionNew() {
		// TODO Auto-generated constructor stub
	}
	
	public TransactionNew(int transactionId, int customerId, int accountId, int amount, String transactionType) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.accountId = accountId;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	
	@Override
	public String toString() {
		return "TransactionNew [transactionId=" + transactionId + ", customerId=" + customerId + ", accountId="
				+ accountId + ", amount=" + amount + ", transactionType=" + transactionType + "]";
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public byte[] getBytes() {
		 byte[]bytes;
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        try{
	            ObjectOutputStream oos = new ObjectOutputStream(baos);
	            oos.writeObject(this);
	            oos.flush();
	            oos.reset();
	            bytes = baos.toByteArray();
	            oos.close();
	            baos.close();
	        } catch(IOException e){
	            bytes = new byte[] {};
	            Logger.getLogger("bsdlog").log(Level.ALL, "unable to write to output stream" + e);
	        }
	        return bytes;
	}
	
	 public static TransactionNew fromBytes(byte[] body) {
		 TransactionNew obj = null;
	        try {
	            ByteArrayInputStream bis = new ByteArrayInputStream(body);
	            ObjectInputStream ois = new ObjectInputStream(bis);
	            obj = (TransactionNew) ois.readObject();
	            ois.close();
	            bis.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
	        return obj;
	    }

	
}


