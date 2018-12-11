package com.aco2018.carTailor;

import java.io.File;
import java.util.Scanner;

public class Client implements Observer{
    private boolean isComplete;
    private boolean isValid;
    private ConfiguratorImpl configuratorImpl;
    private ConfigurationImpl configurationImpl;

    public Client() {
        this.configuratorImpl = new ConfiguratorImpl();
        this.configurationImpl = new ConfigurationImpl(configuratorImpl.getIncompatibiblityManager());
    }

    public ConfiguratorImpl getConfiguratorImpl() {
        return configuratorImpl;
    }

    public ConfigurationImpl getConfigurationImpl() {
        return configurationImpl;
    }
    public void afficherMessageClient(){
        System.out.println("Bienvennue dans votre configurateur");
        configuratorImpl.printDescription();
        System.out.println("Choisissez un nombre:");
        System.out.println("1:Ajouter des part à ma configuration");
        System.out.println("2: Supprimer une part de ma configuration");
        System.out.println("3: Afficher ma configuration");
        System.out.println("Appuyer sur -1 pour retourner au menu principal");
    }
    public void afficherMessageAdmin(){
        System.out.println("Bienvennue dans votre configurateur");
        configuratorImpl.printDescription();
        System.out.println("Choisissez un nombre:");
        System.out.println("1:Ajouter un requirement à une part");
        System.out.println("2: Supprimer un requirement d'une part");
        System.out.println("3:Ajouter un incompatibility à une part");
        System.out.println("4: Supprimer un incompatibility d'une part");
        System.out.println("5: Afficher ma configuration");
        System.out.println("Appuyer sur -1 pour retourner au menu principal");
    }
    public static void main(String[] args){

        Client appli=new Client();
        ConfiguratorImpl configuratorImpl=appli.getConfiguratorImpl();
        configuratorImpl.launch(new File("adminConfiguration.json"));
        ConfigurationImpl configurationImpl=appli.getConfigurationImpl();
        configurationImpl.register(appli);
        Scanner lectureClavier = new Scanner(System.in);
        int choix;
        do {
            System.out.println("Choisissez un menu");
            System.out.println("1: Administrateur");
            System.out.println("2:Client");
            System.out.println("Appuyer sur 0 pour terminer");
            choix = lectureClavier.nextInt();
            switch (choix){
                case 1:do {
                    appli.afficherMessageAdmin();
                    String partReference;
                    String partRequirement;
                    choix = lectureClavier.nextInt();
                    switch (choix){
                        case 1:
                            System.out.println("Entrer le part sur lequel vous voulez ajouter un requirement");
                            partReference=lectureClavier.next();
                            System.out.println("Entrer le requirement que vous voulez ajouter");
                            partRequirement=lectureClavier.next();
                            configuratorImpl.addRequirement(partRequirement,partReference);
                            configuratorImpl.printDescription();
                            System.out.println("Appuyer sur une touche pour continuer");
                            lectureClavier.next();
                            break;

                        case 2:System.out.println("Entrer le part sur lequel vous voulez supprimer un requirement");
                            partReference=lectureClavier.next();
                            System.out.println("Entrer le requirement que vous voulez supprimer");
                            partRequirement=lectureClavier.next();
                            configuratorImpl.removeRequirement(partRequirement,partReference);
                            configuratorImpl.printDescription();
                            System.out.println("Appuyer sur une touche pour continuer");
                            lectureClavier.next();
                            break;

                        case 3:System.out.println("Entrer le part sur lequel vous voulez ajouter un incompatibility");
                            partReference=lectureClavier.next();
                            System.out.println("Entrer l' incompatibility que vous voulez ajouter");
                            partRequirement=lectureClavier.next();
                            configuratorImpl.addIncompatibility(partRequirement,partReference);
                            configuratorImpl.printDescription();
                            System.out.println("Appuyer sur une touche pour continuer");
                            lectureClavier.next();
                            break;

                        case 4:System.out.println("Entrer le part sur lequel vous voulez supprimer un incompatibility");
                            partReference=lectureClavier.next();
                            System.out.println("Entrer l'incompatibility que vous voulez supprimer");
                            partRequirement=lectureClavier.next();
                            configuratorImpl.removeIncompatibility(partRequirement,partReference);
                            configuratorImpl.printDescription();
                            System.out.println("Appuyer sur une touche pour continuer");
                            lectureClavier.next();
                            break;
                        case 5:configuratorImpl.printDescription();
                        break;

                    }
                }while (choix!=-1);
                    break;
                case 2:do {
                    appli.afficherMessageClient();
                    choix = lectureClavier.nextInt();
                    switch (choix){
                        case 1:
                            String part;
                            System.out.println("Entrer vos part et terminez par -1");
                            int val=0;
                            while (val!=-1){
                                part=lectureClavier.next();
                                try {
                                    val=Integer.parseInt(part);
                                    if(val==-1)
                                        break;
                                }catch (Exception e){

                                }
                                configurationImpl.addPart(configuratorImpl.getPartByName(part));
                            }//configurationImpl.description();
                            if(appli.isValid){
                                System.out.println("\nVotre configuration est valide");
                            }else{
                                System.out.println("Votre configuration est invalide");
                            }

                            if (appli.isComplete){
                                System.out.println("votre configuration est complete");
                            }else System.out.println("votre configuration est incomplete");
                            System.out.println("appuyer sur une touche pour continuer");
                            lectureClavier.next();
                            break;
                        case 2:
                            System.out.println("entrer le Part à supprimer");
                            part=lectureClavier.next();
                            configurationImpl.deletePart(part);
                            //configurationImpl.description();
                            if(appli.isValid){
                                System.out.println("\nVotre configuration est valide");
                            }else{
                                System.out.println("Votre configuration est invalide");
                            }

                            if (appli.isComplete){
                                System.out.println("votre configuration est complete");
                            }else System.out.println("votre configuration est incomplete");
                            System.out.println("Appuyer sur une touche pour continuer");
                            lectureClavier.next();
                            break;
                        case 3://configurationImpl.description();
                            System.out.println("Appuyer sur une touche pour continuer");
                            lectureClavier.next();
                            break;
                    }

                }while (choix!=-1);
                    break;

            }

        }while (choix!=0);











    }

    @Override
    public void update() {
        isComplete=configurationImpl.isComplete();
        isValid=configurationImpl.isValid();
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean isValid() {
        return isValid;
    }

    public void demarrerSimulation(){

    }

}
