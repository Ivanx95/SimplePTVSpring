package one.main.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <p> WARNING this package will be move to maven module </p>
 * @author storm
 *
 */
public class Sale {

	private LongProperty id;
	
	private StringProperty client;
	
	private StringProperty saler;
	
	private ObjectProperty<LocalDateTime> time;
	
	private DoubleProperty amount;

	
	public Sale(Long id, String client, String saler, LocalDateTime time, BigDecimal amount) {
		this.id = new SimpleLongProperty(id);
		this.client= new SimpleStringProperty(client);
		this.saler = new SimpleStringProperty();
		this.time= new SimpleObjectProperty<>(time);
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

	public LocalDateTime getTime() {
		return time.get();
	}

	public void setTime(LocalDateTime time) {
		this.time.set(time);
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
	
	public ObjectProperty<LocalDateTime> getTimeProperty(){
		return this.time;
	}
	
	public DoubleProperty getAmoutProperty() {
		return this.amount;
	}
	
}
