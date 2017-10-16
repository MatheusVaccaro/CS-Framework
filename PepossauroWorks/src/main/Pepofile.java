package main;

import database.DatabaseManager;
import database.Identifiable;
import mock.MockDB;
import mock.MockIdentifiable;

public class Pepofile {
	/**
	 * Identifica o arquivo que implementa o banco de dados.
	 * Deve ser atribuído à db uma instância da classe que implementa DatabaseManager.
	 * Se db == null, MockDB será utilizado como banco padrão de teste.
	 */
	public static DatabaseManager<Identifiable> db = new MockDB();
	
	/**
	 * Identifica o arquivo que será manipulado pela aplicação e pelo banco de dados.
	 */
	public static Identifiable identifiable = new MockIdentifiable("Mock");
}
