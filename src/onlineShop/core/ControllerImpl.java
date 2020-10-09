package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    List<Computer> baseComputerList;

    public ControllerImpl() {
        this.baseComputerList = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (baseComputerList.stream().anyMatch(x -> x.getId() == id)){
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        switch (computerType){
            case "Laptop":
                Computer laptop = new Laptop(id, manufacturer, model, price);
                baseComputerList.add(laptop);
                break;
            case "DesktopComputer":
                Computer desktopComputer = new DesktopComputer(id, manufacturer, model, price);
                baseComputerList.add(desktopComputer);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }

        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        if (baseComputerList.stream().noneMatch(x -> x.getId() == computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Computer computer = getComputer(computerId);
        if (computer.getPeripherals().stream().anyMatch(x -> x.getId() == id)){
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }
        switch (peripheralType){
            case "Headset":
                BasePeripheral headset = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                computer.getPeripherals().add(headset);
                break;
            case "Keyboard":
                BasePeripheral keyboard = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                computer.getPeripherals().add(keyboard);
                break;
            case "Monitor":
                BasePeripheral monitor = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                computer.getPeripherals().add(monitor);
                break;
            case "Mouse":
                BasePeripheral mouse = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                computer.getPeripherals().add(mouse);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }

        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (baseComputerList.stream().noneMatch(x -> x.getId() == computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = getComputer(computerId);

        List<Peripheral> components = computer.getPeripherals().stream().filter(x -> x.getClass().getSimpleName().equals(peripheralType)).collect(Collectors.toList());

        components.forEach(x -> computer.getPeripherals().remove(x));

        if (components.isEmpty()) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,peripheralType
                    , computer.getClass().getSimpleName(),computerId));
        }
        return String.format(REMOVED_COMPONENT, peripheralType, components.stream().findFirst().get().getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        if (baseComputerList.stream().noneMatch(x -> x.getId() == computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = getComputer(computerId);
        if (computer.getComponents().stream().anyMatch(x -> x.getId() == id)){
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }
        switch (componentType){
            case "CentralProcessingUnit":
                Component periferal = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                computer.getComponents().add(periferal);
                break;
            case "Motherboard":
                Component peripheral3 = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                computer.getComponents().add(peripheral3);
                break;
            case "PowerSupply":
                Component peripheral2 = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                computer.getComponents().add(peripheral2);
                break;
            case "RandomAccessMemory":
                Component peripheral = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                computer.getComponents().add(peripheral);
                break;
            case "SolidStateDrive":
                Component peripheral22 = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                computer.getComponents().add(peripheral22);
                break;
            case "VideoCard":
                Component peripheral12 = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                computer.getComponents().add(peripheral12);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        return String.format(ADDED_COMPONENT, componentType, id, computerId);    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        if (baseComputerList.stream().noneMatch(x -> x.getId() == computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = getComputer(computerId);

        List<Component> components = computer.getComponents().stream().filter(x -> x.getClass().getSimpleName().equals(componentType)).collect(Collectors.toList());

        components.forEach(x -> computer.getComponents().remove(x));
        if (components.isEmpty()) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,componentType
                    , computer.getClass().getSimpleName(),computerId));
        }
        return String.format(REMOVED_COMPONENT, componentType, components.stream().findFirst().get().getId());
    }

    @Override
    public String buyComputer(int id) {
        if (baseComputerList.stream().noneMatch(x -> x.getId() == id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = getComputer(id);
        String computerString = computer.toString();
        baseComputerList.remove(computer);

        return computerString;
    }

    @Override
    public String BuyBestComputer(double budget) {
        Optional<Computer> computer = baseComputerList.stream().filter(x -> x.getPrice() <= budget).sorted(Comparator.comparing(Computer::getOverallPerformance).reversed()).findFirst();

        if (!computer.isPresent()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER,budget));
        }

        baseComputerList.remove(computer.get());
        return computer.get().toString();
    }

    @Override
    public String getComputerData(int id) {
        if (baseComputerList.stream().noneMatch(x -> x.getId() == id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = getComputer(id);

        return computer.toString();
    }

    private Computer getComputer(int id) {
        return baseComputerList.stream().filter(x -> x.getId() == id).findFirst().get();
    }
}
