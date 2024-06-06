package sn.ucao.repertoire.entities;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehicule {
    /*
    Les attributs de la classe Vehicule sont définis comme des StringProperty.
    Cela permet d'utiliser la liaison de données avec les contrôles graphiques de JavaFX.
    */
    private StringProperty numPlaque = new SimpleStringProperty();
    private StringProperty marque = new SimpleStringProperty();
    private StringProperty modele = new SimpleStringProperty();
    private StringProperty couleur = new SimpleStringProperty();
    private StringProperty typeTrans = new SimpleStringProperty();

    public Vehicule(){}

    public Vehicule(StringProperty numPlaque, StringProperty marque, StringProperty modele, StringProperty couleur, StringProperty typeTrans) {
        this.numPlaque = numPlaque;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.typeTrans = typeTrans;
    }

    public StringProperty numPlaqueProperty() {
        return numPlaque;
    }

    public StringProperty marqueProperty() {
        return marque;
    }

    public StringProperty modeleProperty() {
        return modele;
    }

    public StringProperty couleurProperty() {
        return couleur;
    }

    public StringProperty typeTransProperty() {
        return typeTrans;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "numPlaque=" + numPlaque +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", couleur='" + couleur + '\'' +
                ", typeTrans='" + typeTrans + '\'' +
                '}';
    }

    public void setMarque(String marque) {
        this.marque.set(marque);
    }

    public void setNumPlaque(String numPlaque) {
        this.numPlaque.set(numPlaque);
    }

    public void setModele(String modele) {
        this.modele.set(modele);
    }

    public void setCouleur(String couleur) {
        this.couleur.set(couleur);
    }

    public void setTypeTrans(String typeTrans) {
        this.typeTrans.set(typeTrans);
    }

    public String getNumPlaque() {
        return numPlaque.get();
    }

    public String getMarque() {
        return marque.get();
    }

    public String getModele() {
        return modele.get();
    }

    public String getCouleur() {
        return couleur.get();
    }

    public String getTypeTrans() {
        return typeTrans.get();
    }

    //champs boolean location
    private final BooleanProperty location = new SimpleBooleanProperty();

    public boolean isLocation() {
        return location.get();
    }

    public BooleanProperty locationProperty() {
        return location;
    }

    public void setLocation(boolean location) {
        this.location.set(location);
    }



}
