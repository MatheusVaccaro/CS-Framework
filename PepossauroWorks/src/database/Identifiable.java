package database;

import java.util.Map;

public abstract class Identifiable {
	protected Object id;
	
	/**
	 * Identificador do registro no banco de dados.
	 * @return objeto identificador.
	 */
	public Object getId() {	return id; }
	
	/**
	 * Representação textual simples do objeto
	 * a ser apresentada no painel esquerdo da UI. 
	 * @return string simples representando o objeto.
	 */
	public String getSimpleRepresentation() {
		return id.toString();
	}
	
	/**
	 * Representação em dicionário do objeto,
	 * a ser apresentada no painel direito da UI.
	 * @return dicionário representando todos os dados do objeto.
	 */
	public abstract Map<String, String> getMapRepresentation();
	
	/**
	 * Retorna uma cópia do objeto.
	 * @return cópia do objeto.
	 */
	public abstract Identifiable copy();
	
	/**
	 * Preenche os campos do objeto utilizando os valores contidos
	 * no dicionário fornecido. 
	 * 
	 * NOTA: utilizar o método da superclasse (getId(), da classe Identifiable) para popular o campo do id.
	 * @param dictionary - dicionário usado no preenchimento de campos.
	 * @throws Exception - exceção caso algum valor do dicionário seja 
	 * incompatível com o tipo do campo respectivo. 
	 */
	public void updateWithDic(Map<String, String> dictionary) throws Exception {
		this.id = dictionary.get("id");
	}
}
