package view;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableViewController {
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn labelColumn;
	@FXML
	private TableColumn dataColumn;

	public TableViewController() { }

	@FXML
	private void initialize() {
		List pessoas = Arrays.asList(
                new Pessoa("William", 32),
                new Pessoa("Luana", 17),
                new Pessoa("Maria", 12),
                new Pessoa("João", 15),
                new Pessoa("Antônio", 28),
                new Pessoa("Teles", 17),
                new Pessoa("Eduan", 30),
                new Pessoa("Gabu", 22)
        );
		
		labelColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		dataColumn.setCellValueFactory(new PropertyValueFactory<>("idade"));
		tableView.setItems(FXCollections.observableArrayList(pessoas));
	}

	public static class Pessoa {

        private String nome;
        private int idade;

        public Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            this.idade = idade;
        }
    }
}
