package com.aco2018.carTailor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Client implements Observer {

    private boolean isValid = false;
    private boolean isComplete = false;

    private ConfigurationImpl configuration = new ConfigurationImpl(IncompatibiblityManagerImpl.getInstance());
    private ConfiguratorImpl configurator = new ConfiguratorImpl();



    @Override
    public void update() {
        isValid = configuration.isValid();
        isComplete = configuration.isComplete();
    }




    public ConfigurationImpl getConfiguration() {
        return configuration;
    }

    public ConfiguratorImpl getConfigurator() {
        return configurator;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
