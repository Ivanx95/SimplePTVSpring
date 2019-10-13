package one.main.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Sale {

	   @Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	   private Long id;
	   
	   
	   @ManyToOne(optional = false)
	   @JoinColumn(name = "cashout_id")
	   private CashOut cashOut;
	   
	   @ManyToOne(optional = false)
	   @JoinColumn(name = "client_id")
	   private User client;
		
	   /**
	    * Potentially move to cashout
	    */
	 
//	   private User saler;
		
	   @Column
	   private LocalDateTime startDate;
		
	   @Column
	   private BigDecimal amount;
	  
	   @Column
	   private BigDecimal discount;

	   
	   @Column
	   private LocalDateTime endDate;
	   


	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	

	public Sale(CashOut cashOut, User client, LocalDateTime startDate, BigDecimal amount, BigDecimal discount){
		super();
		this.cashOut = cashOut;
		this.client = client;
		this.startDate = startDate;
		this.amount = amount;
		this.discount = discount;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	  
	
	  /**
	   * TODO: Make taxes decouple
	   */
//	  private BigDecimal taxes;
	   
	   
}
