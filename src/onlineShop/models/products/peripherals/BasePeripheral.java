package onlineShop.models.products.peripherals;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;

public abstract class BasePeripheral extends BaseProduct implements Peripheral {
    private String connectionType;

    protected BasePeripheral(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public void setManufacturer(String manufacturer) {
        super.setManufacturer(manufacturer);
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance();
    }

    @Override
    public void setOverallPerformance(double overallPerformance) {
        super.setOverallPerformance(overallPerformance);
    }

    @Override
    public String getConnectionType() {
        return this.connectionType;
    }

    @Override
    public String toString() {
        return String.format("  Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d) Connection Type: %s"
                , this.getOverallPerformance()
                , this.getPrice()
                , this.getClass().getSimpleName()
                , this.getManufacturer()
                , this.getModel()
                , this.getId()
                , this.getConnectionType());
    }
}
