package onlineShop.models.products.computers;

import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.List;

public class DesktopComputer extends BaseComputer {
    private static final int INIT_OVERALLPERFORMANCE = 15;

    public DesktopComputer(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, INIT_OVERALLPERFORMANCE);
    }


    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setManufacturer(String manufacturer) {
        super.setManufacturer(manufacturer);
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public void setOverallPerformance(double overallPerformance) {
        super.setOverallPerformance(overallPerformance);
    }

    @Override
    public List<Component> getComponents() {
        return super.getComponents();
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return super.getPeripherals();
    }

    @Override
    public void addComponent(Component component) {
        super.addComponent(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        return super.removeComponent(componentType);
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        super.addPeripheral(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        return super.removePeripheral(peripheralType);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance();
    }
}
