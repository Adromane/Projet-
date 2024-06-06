package sn.ucao.repertoire;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sn.ucao.repertoire.entities.Vehicule;
import sn.ucao.repertoire.service.IVehicule;
import sn.ucao.repertoire.service.VehiculeImpl;
import javafx.collections.FXCollections;
//THIONE BEATRICE
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class HelloController implements Initializable{

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

    @FXML
    private TableColumn<Vehicule, String> marqueTb;

    @FXML
    private TableColumn<Vehicule, String> couleurTb;

    @FXML
    private TableColumn<Vehicule, String> matTb;

    @FXML
    private TableColumn<Vehicule, String> modelTb;

    @FXML
    private TableColumn<Vehicule, String> typeTb;

    @FXML
    private TableColumn<Vehicule, Boolean> locationTb;

    @FXML
    private TableView<Vehicule> tableView;

    @FXML
    private TableColumn<Vehicule, Void> actionsTb;


    @FXML
    private Label welcomeText;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    private ObservableList<Vehicule> vehiculeList;

    private IVehicule vehiculeService;

    @FXML
    void connexionClick(ActionEvent event) throws Exception {
        Vehicule vehicule = new Vehicule();
        IVehicule service = new VehiculeImpl();

        if (marqueTfd.getText().isEmpty() || matTfd.getText().isEmpty() || modelTfd.getText().isEmpty() || couleurTfd.getText().isEmpty() || typeTfd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Informations");
            alert.setTitle("vehicule");
            alert.setContentText("Tous les champs doivent être remplis!");

            alert.showAndWait();
        }else {
            vehicule.setMarque(marqueTfd.getText());
            vehicule.setNumPlaque(matTfd.getText().toUpperCase());
            vehicule.setModele(modelTfd.getText());
            vehicule.setCouleur(couleurTfd.getText());
            vehicule.setTypeTrans(typeTfd.getText());

            boolean saved = service.saveVehicule(vehicule);

            if(saved){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Informations");
                alert.setTitle("vehicule");
                alert.setContentText("Le vehicule "+marqueTfd.getText()+" "+modelTfd.getText()+" a ete ajouter avec succes!");

                alert.showAndWait();

                // Vider les champ de texte
                marqueTfd.clear();
                matTfd.clear();
                modelTfd.clear();
                couleurTfd.clear();
                typeTfd.clear();

            }else{

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Informations");
                alert.setTitle("vehicule");
                alert.setContentText("Erreur !");

                alert.showAndWait();
            }


        }

        // Mettre à jour la liste des véhicules affichée dans la TableView
        updateTableView();

    }

    @FXML
    void deleteClick(ActionEvent event) {
        Vehicule selectedVehicule = tableView.getSelectionModel().getSelectedItem();
        if (selectedVehicule != null) {
            boolean deleted = vehiculeService.deleteVehicule(selectedVehicule);
            if (deleted) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Informations");
                alert.setTitle("Suppression");
                alert.setContentText("Le véhicule "+marqueTfd.getText()+" "+modelTfd.getText()+" a été supprimé avec succès!");

                alert.showAndWait();

                // Mettre à jour la liste des véhicules affichée dans la TableView
                updateTableView();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Informations");
                alert.setTitle("Suppression");
                alert.setContentText("Erreur lors de la suppression du véhicule "+marqueTfd.getText()+" "+modelTfd.getText()+" !");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Informations");
            alert.setTitle("Suppression");
            alert.setContentText("Veuillez sélectionner un véhicule à supprimer!");

            alert.showAndWait();
        }
    }

    @FXML
    void updateClick(ActionEvent event) throws IOException {
        Vehicule selectedVehicule = tableView.getSelectionModel().getSelectedItem();

        if (selectedVehicule != null) {
            // Ouvrir la fenêtre de modification du véhicule
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vehicle_modification.fxml"));
            Parent root = loader.load();
            VehicleModificationController modificationController = loader.getController();
            modificationController.setHelloController(this);


            // Passer le véhicule sélectionné au contrôleur de modification
            modificationController.setVehicule(selectedVehicule);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier le véhicule");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();



            // Mettre à jour la ligne sélectionnée avec les modifications (si des modifications ont été effectuées)
            tableView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Informations");
            alert.setTitle("Mise à jour");
            alert.setContentText("Veuillez sélectionner un véhicule à mettre à jour!");

            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehiculeService = new VehiculeImpl();


        // Associer les colonnes de la TableView aux propriétés du modèle Vehicule
        marqueTb.setCellValueFactory(cellData -> cellData.getValue().marqueProperty());
        couleurTb.setCellValueFactory(cellData -> cellData.getValue().couleurProperty());
        matTb.setCellValueFactory(cellData -> cellData.getValue().numPlaqueProperty());
        modelTb.setCellValueFactory(cellData -> cellData.getValue().modeleProperty());
        typeTb.setCellValueFactory(cellData -> cellData.getValue().typeTransProperty());
        locationTb.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        configureActionsColumn(actionsTb);

        // Mettre à jour la liste des véhicules affichée dans la TableView
        updateTableView();
    }


    protected void updateTableView() {
        try {
            List<Vehicule> vehicules = vehiculeService.getListVehicule();
            vehiculeList = FXCollections.observableArrayList(vehicules);
            tableView.setItems(vehiculeList);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur");
            alert.setTitle("Chargement des données");
            alert.setContentText("Erreur lors du chargement des données de la base de données!");

            alert.showAndWait();

            e.printStackTrace();
        }
    }

    private void configureActionsColumn(TableColumn<Vehicule, Void> column) {
        column.setCellFactory(param -> new TableCell<>() {
            private final Button louerButton = new Button("Louer");
            private final Button reprendreButton = new Button("Reprendre");

            {
                louerButton.setOnAction(event -> {
                    Vehicule vehicule = getTableRow().getItem();
                    vehicule.setLocation(true);
                    vehiculeService.updateVehiculeLocation(vehicule); // Mettre à jour la valeur "location" dans la base de données
                    tableView.refresh(); // Mettre à jour la TableView
                });

                reprendreButton.setOnAction(event -> {
                    Vehicule vehicule = getTableRow().getItem();
                    vehicule.setLocation(false);
                    vehiculeService.updateVehiculeLocation(vehicule); // Mettre à jour la valeur "location" dans la base de données
                    tableView.refresh(); // Mettre à jour la TableView
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Vehicule vehicule = getTableRow().getItem();
                    if (vehicule != null) {
                        boolean isLoue = vehicule.isLocation();
                        louerButton.setDisable(isLoue);
                        reprendreButton.setDisable(!isLoue);
                        setGraphic(new HBox(louerButton, reprendreButton));
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
    }


}