package onlineShop.models.products.components;

public class CentralProcessingUnit extends BaseComponent {
    private static final double MULTIPLIER = 1.25;
    public CentralProcessingUnit(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * MULTIPLIER, generation);
    }


    @Override
    public int getGeneration() {
        return super.getGeneration();
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
}
