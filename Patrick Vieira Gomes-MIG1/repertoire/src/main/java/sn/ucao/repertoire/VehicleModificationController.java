package sn.ucao.repertoire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sn.ucao.repertoire.entities.Vehicule;
import javafx.scene.control.TextField;
import sn.ucao.repertoire.service.IVehicule;
import sn.ucao.repertoire.service.VehiculeImpl;

public class VehicleModificationController {

    @FXML
    private TextField couleurTfd;

    @FXML
    private TextField marqueTfd;

    @FXML
    private TextField matTfd;

    @FXML
    private TextField modelTfd;

    @FXML
    private TextField typeTfd;


    private Vehicule vehicule;

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
        // Mettez à jour les champs de texte avec les valeurs du véhicule
        marqueTfd.setText(vehicule.getMarque());
        matTfd.setText(vehicule.getNumPlaque());
        modelTfd.setText(vehicule.getModele());
        couleurTfd.setText(vehicule.getCouleur());
        typeTfd.setText(vehicule.getTypeTrans());
    }

    private HelloController helloController;

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }


    @FXML
    void saveClick(ActionEvent event) throws Exception {
        Vehicule vehicule = new Vehicule();
        IVehicule service = new VehiculeImpl();


        // Récupérez les nouvelles valeurs des champs de texte
        String nouvelleMarque = marqueTfd.getText();
        String nouveauModele = modelTfd.getText();
        String nouveauMatricule = matTfd.getText();
        String nouvelleCouleur = couleurTfd.getText();
        String nouveauType = typeTfd.getText();


        // Effectuez les validations nécessaires sur les nouvelles valeurs...
        if (nouvelleMarque.isEmpty() || nouveauModele.isEmpty() || nouvelleCouleur.isEmpty() || nouveauType.isEmpty() || nouveauMatricule.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Informations");
            alert.setTitle("vehicule");
            alert.setContentText("Tous les champs doivent être remplis!");

            alert.showAndWait();
            return; // Arrêter le traitement car les validations ont échoué
        }

        // Mettez à jour les propriétés du véhicule avec les nouvelles valeurs
        vehicule.setMarque(nouvelleMarque);
        vehicule.setModele(nouveauModele);
        vehicule.setCouleur(nouvelleCouleur);
        vehicule.setNumPlaque(nouveauMatricule);
        vehicule.setTypeTrans(nouveauType);

        boolean saved = service.updateVehicule(vehicule);
        if(saved){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Informations");
            alert.setTitle("vehicule");
            alert.setContentText("Le vehicule "+marqueTfd.getText()+" "+modelTfd.getText()+" a ete mis a jour avec succes!");

            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Informations");
            alert.setTitle("vehicule");
            alert.setContentText("Erreur !");

            alert.showAndWait();
        }

        // Mettez à jour la TableView dans le HelloController
        helloController.updateTableView();


        // Fermez la fenêtre de modification du véhicule
        Stage stage = (Stage) matTfd.getScene().getWindow();
        stage.close();


    }

    @FXML
    void cancelClick(ActionEvent event) {
        // Fermez la fenêtre de modification du véhicule sans effectuer de modification
        Stage stage = (Stage) matTfd.getScene().getWindow();
        stage.close();
    }
}
