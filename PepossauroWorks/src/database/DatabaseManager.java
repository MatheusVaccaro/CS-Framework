package database;

import java.util.Collection;

public abstract class DatabaseManager<T extends Identifiable> {
	
	/**
	 * Retorna todos os objetos do banco de dados
	 * @return Collection de objetos Identifiable
	 */
	public abstract Collection<T> retrieveAll();
	
	/**
	 * Cria um registro no banco de dados baseado no objeto fornecido.
	 * @param data - objeto Identifiable
	 * @return objeto atualizado com o identificador fornecido pelo banco,
	 * ou null caso a inserção não tenha ocorrido com sucesso.
	 */
	public abstract T create(T data);
	
	/**
	 * Cria um objeto a partir de um registro no banco de dados através de seu identificador.
	 * @param id - identificador do registro a ser recuperado no banco de dados.
	 * @return objeto representando o registro recuperado,
	 * ou null caso o registro não tenha sido encontrado.
	 */
	public abstract T retrieve(Object id);
	public T retrieve(T data) {	
		return retrieve(data.getId()); 
	}
	
	/**
	 * Atualiza um registro no banco de dados 
	 * @param data - representação do objeto a ser atualizado.
	 * @return objeto representando o registro atualizado, 
	 * ou null caso a atualização tenha dado errado.
	 */
	public abstract T update(T data);
	
	/**
	 * Deleta um registro no banco de dados através de seu identificador.
	 * @param id - identificador do registro a ser deletado no banco de dados.
	 * @return objeto representando o registro deletado,
	 * ou null caso o registro a ser deletado não tenha sido encontrado.
	 */
	public abstract T delete(Object id);
	public T delete(T data) {
		return delete(data.getId());
	}
	
}
