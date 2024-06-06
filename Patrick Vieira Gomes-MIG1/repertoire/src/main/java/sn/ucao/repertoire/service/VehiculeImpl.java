package sn.ucao.repertoire.service;

import sn.ucao.repertoire.entities.Vehicule;
import sn.ucao.repertoire.utils.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehiculeImpl implements IVehicule{
    DBHelper db = DBHelper.getInstance();
    @Override
    public boolean saveVehicule(Vehicule vehicule) throws Exception {
        try {
            String requete = "INSERT INTO vehicule VALUES(?,?,?,?,?,?)";
            db.myPrepareStatement(requete);


            db.addParametres(1, vehicule.getNumPlaque());
            db.addParametres(2, vehicule.getMarque());
            db.addParametres(3, vehicule.getModele());
            db.addParametres(4, vehicule.getCouleur());
            db.addParametres(5, vehicule.getTypeTrans());
            db.addParametres(6, vehicule.isLocation());

            db.myExecutePrepareUpdate();

            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Vehicule> getListVehicule(){
        try {
            String requete = "SELECT * FROM vehicule";
            ResultSet result = db.myExecuteQuery(requete);
            List<Vehicule> vehicules = new ArrayList<>();
            while (result.next()){
                Vehicule v = new Vehicule();
                v.setNumPlaque((result.getString(1)));
                v.setMarque((result.getString(2)));
                v.setModele((result.getString(3)));
                v.setCouleur((result.getString(4)));
                v.setTypeTrans((result.getString(5)));
                v.setLocation((result.getBoolean(6)));
                vehicules.add(v);
            }
            return vehicules;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteVehicule(Vehicule vehicule) {
        try {
            String requete = "DELETE FROM vehicule WHERE numPlaque = ?";
            db.myPrepareStatement(requete);

            db.addParametres(1, vehicule.getNumPlaque());

            db.myExecutePrepareUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateVehicule(Vehicule vehicule){
        try {
            String requete = "UPDATE vehicule SET marque = ?, modele = ?, couleur = ?, typeTrans = ? WHERE numPlaque = ?";
            db.myPrepareStatement(requete);

            db.addParametres(1,vehicule.getMarque());
            db.addParametres(2,vehicule.getModele());
            db.addParametres(3,vehicule.getCouleur());
            db.addParametres(4,vehicule.getTypeTrans());
            db.addParametres(5,vehicule.getNumPlaque());


            db.myExecutePrepareUpdate();

            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateVehiculeLocation(Vehicule vehicule){
        try {
            String requete = "UPDATE vehicule SET location = ? WHERE numPlaque = ?";
            db.myPrepareStatement(requete);

            db.addParametres(1,vehicule.isLocation());
            db.addParametres(2,vehicule.getNumPlaque());


            db.myExecutePrepareUpdate();

            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existe(String numPlaque) {

        List<Vehicule> vehicules = getListVehicule();

        for (Vehicule vehicule : vehicules) {
            if (vehicule.getNumPlaque().equals(numPlaque)){
                return true; // Le numéro de matricule existe déjà
            }
        }
        return false;//Le numero de matricule n'existe pas
    }
}
