package one.main.jfx.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import one.main.model.Sale;

/**
 * <p> WARNING this package will be move to maven module </p>
 * @author storm
 *
 */
public class SaleFX {

	private LongProperty id;
	
	private StringProperty client;
	
	private StringProperty saler;
	
	private List<SaleItemFX> items;
	
	private ObjectProperty<LocalDateTime> startDate;
	
	private ObjectProperty<LocalDateTime> endDate;
	
	private DoubleProperty amount;

	
	public SaleFX(Sale sale) {
		
	}
	
	public SaleFX(Long id, String client, String saler, LocalDateTime startDate,LocalDateTime endDate, BigDecimal amount) {
		this.id = new SimpleLongProperty(id);
//		this.client= new SimpleStringProperty(client);
//		this.saler = new SimpleStringProperty();
		this.startDate= new SimpleObjectProperty<>(startDate);
		this.endDate= new SimpleObjectProperty<>(endDate);
		this.amount= new SimpleDoubleProperty(amount.doubleValue());
	}
	public StringProperty getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client.set(client);
	}

	public String getSaler() {
		return saler.get();
	}

	public void setSaler(String saler) {
		this.saler.set(saler);
	}

	public LocalDateTime getStartDate() {
		return startDate.get();
	}

	public void setStartDate(LocalDateTime time) {
		this.startDate.set(time);
	}

	public Double getAmount() {
		return amount.get();
	}

	public void setAmount(Double amount) {
		this.amount.set(amount);
	}
	
	public LongProperty getIdProperty() {
		return this.id;
	}
	
	public StringProperty getClientProperty() {
		return this.client;
	}
	
	public StringProperty getSalerProperty() {
		return this.saler;
	}
	
	public ObjectProperty<LocalDateTime> getStartDateProperty(){
		return this.startDate;
	}
	
	public DoubleProperty getAmoutProperty() {
		return this.amount;
	}
	
	public LocalDateTime getEndDate() {
		return endDate.get();
	}

	public void setEndDate(LocalDateTime time) {
		this.endDate.set(time);
	}
	
	public ObjectProperty<LocalDateTime> getEndDateProperty(){
		return this.startDate;
	}
	
	
}
