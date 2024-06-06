package sn.ucao.repertoire.service;

import sn.ucao.repertoire.entities.Vehicule;

import java.util.List;

public interface IVehicule {


    boolean saveVehicule(Vehicule vehicule) throws Exception;
    List<Vehicule> getListVehicule() throws Exception;
    boolean deleteVehicule(Vehicule vehicule);
    boolean updateVehicule(Vehicule vehicule);
    boolean updateVehiculeLocation(Vehicule vehicule);
    boolean existe(String numPlaque);

}

