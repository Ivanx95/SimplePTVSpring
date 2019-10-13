package one.main.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * TODO: Auditable
 * @author jaisen
 *
 */
@Entity
public class CashOut {

	   @Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cashier_id")
	private User cashier;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "manager_id")
	private User manager_id;
	
	private String turnName;
	
	private LocalDateTime startDate;
	
	
	private LocalDateTime endDate;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getCashier() {
		return cashier;
	}


	public void setCashier(User cashier) {
		this.cashier = cashier;
	}


	public User getManager_id() {
		return manager_id;
	}


	public void setManager_id(User manager_id) {
		this.manager_id = manager_id;
	}


	public String getTurnName() {
		return turnName;
	}


	public void setTurnName(String turnName) {
		this.turnName = turnName;
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
	
	
	
}
