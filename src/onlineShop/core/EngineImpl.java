package onlineShop.core;


import onlineShop.common.enums.CommandType;
import onlineShop.core.interfaces.Controller;
import onlineShop.core.interfaces.Engine;
import onlineShop.io.ConsoleReader;
import onlineShop.io.ConsoleWriter;
import onlineShop.io.interfaces.InputReader;
import onlineShop.io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Controller controller;

    public EngineImpl() {
        this.controller = new ControllerImpl();
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (close().equals(result)) {
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            this.writer.writeLine(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s");

        CommandType command = CommandType.valueOf(tokens[0]);
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

         switch (command) {
            case AddComputer:
                return addComputer(data);
             case AddPeripheral:
                 return addPeripheral(data);
             case RemovePeripheral:
                 return removePeripheral(data);
             case AddComponent:
                 return addComponent(data);
             case RemoveComponent:
                 return removeComponent(data);
             case BuyComputer:
                 return buyComputer(data[0]);
             case BuyBestComputer:
                 return buyBestComputer(data[0]);
             case GetComputerData:
                 return getComputerData(data[0]);
             case Close:
                 return close();
         }
        return null;
    }

    private String close() {
        return "Close";
    }

    private String getComputerData(String datum) {
        return controller.getComputerData(Integer.parseInt(datum));
    }

    private String buyBestComputer(String datum) {
        return this.controller.BuyBestComputer(Double.parseDouble(datum));
    }

    private String buyComputer(String datum) {
        return this.controller.buyComputer(Integer.parseInt(datum));
    }

    private String removeComponent(String[] data) {
        return this.controller.removeComponent(data[0], Integer.parseInt(data[1]));
    }

    private String addComponent(String[] data) {
        return this.controller.addComponent(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3], data[4], Double.parseDouble(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]));
    }

    private String removePeripheral(String[] data) {
        return this.controller.removePeripheral(data[0], Integer.parseInt(data[1]));
    }

    private String addPeripheral(String[] data) {
        return this.controller.addPeripheral(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3], data[4], Double.parseDouble(data[5]), Double.parseDouble(data[6]), data[7]);
    }

    private String addComputer(String[] data) {
        return this.controller.addComputer(data[0], Integer.parseInt(data[1]), data[2], data[3], Double.parseDouble(data[4]));
    }

}
