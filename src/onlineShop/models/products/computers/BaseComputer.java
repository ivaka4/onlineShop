package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
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
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public void setOverallPerformance(double overallPerformance) {
        super.setOverallPerformance(overallPerformance);
    }

    @Override
    public double getPrice() {

        return super.getPrice() + components.stream().mapToDouble(x -> x.getPrice()).sum()
                + peripherals.stream().mapToDouble(x -> x.getPrice()).sum();
    }

    @Override
    public double getOverallPerformance() {
        if (components.size() == 0) {
            return super.getOverallPerformance();
        }
        return components.stream().mapToDouble(x -> x.getOverallPerformance()).average().getAsDouble() + super.getOverallPerformance();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if (components.stream().anyMatch(x -> x.getClass().getSimpleName().equals(component.getClass().getSimpleName()))) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT
                    , component.getClass().getSimpleName()
                    , this.getClass().getSimpleName()
                    , this.getId()));
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        if (components.size() == 0 || components.stream().noneMatch(x -> x.getClass().getSimpleName().equals(componentType))) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT
                    , componentType
                    , this.getClass().getSimpleName()
                    , this.getId()));
        }
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                components.remove(component);
                return component;
            }
        }
        return null;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.stream().anyMatch(x -> x.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName()))) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL
                    , peripheral.getClass().getSimpleName()
                    , this.getClass().getSimpleName()
                    , this.getId()));
        }
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (peripherals.size() == 0 || peripherals.stream().noneMatch(x -> x.getClass().getSimpleName().equals(peripheralType))) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL
                    , peripheralType
                    , this.getClass().getSimpleName()
                    , this.getId()));
        }
        for (Peripheral peripheral : peripherals) {
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                peripherals.remove(peripheral);
                return peripheral;
            }

        }
        return null;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(System.lineSeparator());
        sb.append(String.format(" Components (%d):", components.size()));
        sb.append(System.lineSeparator());
        components.forEach(x -> {
            sb.append(x.toString());
            sb.append(System.lineSeparator());
        });
        Double avg = peripherals.isEmpty() ? 0 : peripherals.stream().mapToDouble( x -> x.getOverallPerformance()).average().getAsDouble();

        sb.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f):", peripherals.size(),avg));
        sb.append(System.lineSeparator());
        peripherals.forEach(x ->{
            sb.append(x.toString());
            sb.append(System.lineSeparator());
        });
        return sb.toString().trim();



    }
}
