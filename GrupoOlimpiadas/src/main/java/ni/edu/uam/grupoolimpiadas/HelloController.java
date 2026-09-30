package ni.edu.uam.grupoolimpiadas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML private TextField txtNombre;
    @FXML private TextField txtCalificacion;


    @FXML private CheckBox chkGrupo1;
    @FXML private CheckBox chkGrupo2;

    @FXML private TableView<Participante> tablaParticipantes;
    @FXML private TableColumn<Participante, String> colNombre;
    @FXML private TableColumn<Participante, Double> colCalificacion;
    @FXML private TableColumn<Participante, String> colGrupo;
    @FXML private Label lblMensaje;


    private final ObservableList<Participante> listaParticipantes = FXCollections.observableArrayList();

    private FilteredList<Participante> listaFiltrada;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
        colGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));


        listaFiltrada = new FilteredList<>(listaParticipantes, p -> true);


        tablaParticipantes.setItems(listaFiltrada);
    }

    @FXML
    protected void onRegistrarClick() {
        String nombre = txtNombre.getText().trim();
        String calificacionStr = txtCalificacion.getText().trim();

        if (nombre.isEmpty() || calificacionStr.isEmpty()) {
            mostrarMensaje("Error: Todos los campos son obligatorios.", Color.RED);
            return;
        }

        try {
            double calificacion = Double.parseDouble(calificacionStr);

            if (calificacion < 0 || calificacion > 100) {
                mostrarMensaje("Error: Ingresa una calificación válida (0 - 100).", Color.RED);
                return;
            }

            Participante nuevoParticipante = new Participante(nombre, calificacion);

            listaParticipantes.add(nuevoParticipante);

            txtNombre.clear();
            txtCalificacion.clear();
            mostrarMensaje("Participante registrado exitosamente.", Color.GREEN);

        } catch (NumberFormatException e) {
            mostrarMensaje("Error: La calificación debe ser un número válido.", Color.RED);
        }
    }

    @FXML
    protected void onEliminarClick() {
        Participante participanteSeleccionado = tablaParticipantes.getSelectionModel().getSelectedItem();

        if (participanteSeleccionado != null) {

            listaParticipantes.remove(participanteSeleccionado);
            mostrarMensaje("Participante eliminado correctamente.", Color.BLUE);
        } else {
            mostrarMensaje("Error: Selecciona un participante de la tabla para eliminar.", Color.RED);
        }
    }

    @FXML
    protected void filtrarTabla() {
        listaFiltrada.setPredicate(participante -> {
            boolean verGrupo1 = chkGrupo1.isSelected();
            boolean verGrupo2 = chkGrupo2.isSelected();

            String grupo = participante.getGrupo();


            if (verGrupo1 && grupo.contains("Grupo 1")) {
                return true;
            }
            if (verGrupo2 && grupo.contains("Grupo 2")) {
                return true;
            }

            // Si ninguno coincide, se oculta de la tabla
            return false;
        });
    }

    private void mostrarMensaje(String mensaje, Color color) {
        lblMensaje.setText(mensaje);
        lblMensaje.setTextFill(color);
    }
}