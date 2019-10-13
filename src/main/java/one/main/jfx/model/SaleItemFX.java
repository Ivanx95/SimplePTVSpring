package one.main.jfx.model;

import java.math.BigDecimal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import one.main.model.SaleItem;

public class SaleItemFX {

	private LongProperty id;
	
	private StringProperty description;
	
	private DoubleProperty quantity;
	
	private DoubleProperty unitPrice;
	
	private DoubleProperty amount;
	
	private DoubleProperty discount;
	
	public SaleItemFX(SaleItem saleItem) {
		this(saleItem.getId(), saleItem.getDescription(), saleItem.getQuantity(), saleItem.getUnitPrice(), saleItem.getAmount(), saleItem.getDiscount());
	}
			

	public SaleItemFX(Long id, String description, BigDecimal quantity, BigDecimal unitPrice,
			BigDecimal amount, BigDecimal discount) {
		super();
		
		this.id = new SimpleLongProperty(id);
		this.description = new SimpleStringProperty(description);
		this.quantity = new SimpleDoubleProperty(quantity.doubleValue());
		this.unitPrice = new SimpleDoubleProperty(unitPrice.doubleValue());
		this.amount = new SimpleDoubleProperty(amount.doubleValue());
		this.discount = new SimpleDoubleProperty(discount.doubleValue());
		
		this.amount.bind(this.quantity.multiply(this.unitPrice));
	}

	public String getDescription() {
		return description.get();
	}
	
	public StringProperty getDescriptionProperty() {
		return description;
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public Double getQuantity() {
		return quantity.get();
	}

	public void setQuantity(Double quantity) {
		this.quantity.set(quantity);
	}
	
	public DoubleProperty getQuantityProperty() {
		return quantity;
	}
	

	public Double getUnitPrice() {
		return unitPrice.get();
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice.set(unitPrice);
	}

	public DoubleProperty getUnitPriceProperty() {
		return unitPrice;
	}
	
	public Double getAmount() {
		return amount.get();
	}

	public void setAmount(Double amount) {
		this.amount.set(amount);
	}

	public DoubleProperty getAmountProperty() {
		return amount;
	}
	
	public Double getDiscount() {
		return discount.get();
	}

	public void setDiscount(Double discount) {
		this.discount.set(discount);
	}
	
	public DoubleProperty getDiscountProperty() {
		return discount;
	}

	public Long getId() {
		return id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}
	
	
	public LongProperty getIdProperty() {
		return id;
	}
	
	
}
