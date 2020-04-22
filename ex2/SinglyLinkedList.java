public class SinglyLinkedList<T> {

	private Node<T> first;			// Primeiro nó da lista
	private int size;				// tamanho da lista

	// Construtor
	SinglyLinkedList(){
		first = null;
		size = 0;
	}


	// MÉTOODOS


	// Retorna o tamanho da lista
	public int size() {
		return size;
	}

	// Retorna se está vazio ou nao
	public boolean isEmpty(){
		return (size==0);
	}

	// adiciona um elemento ao inicio da lista
	public void addFirst(T value){

		Node<T> newNode = new Node<T>(value, first);
		first = newNode;
		size++;

	}

	public void addLast(T value){

		Node<T> newNode = new Node<T>(value, null);

		if(isEmpty()){
			first = newNode;
		}
		else {
		Node<T> cur = first;

		while(cur.getNext() != null){
			cur = cur.getNext();
		}
		cur.setNext(newNode);
		}
		size++;
	}	

	// Obter o primeiro valor
	public T getFirst(){

		if(isEmpty())
			return null;
		return first.getValue();
	}

	public T getLast(){

		if(isEmpty())
			return null;

		Node<T> cur = first;

		while(cur.getNext() != null)
			cur = cur.getNext();
		return cur.getValue();
	}


	public void removeFirst(){

		if(isEmpty())
			return;

		first = first.getNext();
		size--;
	}

	public void removeLast(){

		if (isEmpty())
			return;

		Node<T> cur = first;
 		while(cur.getNext().getNext() != null){
 			cur = cur.getNext();
 		}
 		cur.setNext(cur.getNext().getNext());
 		size--;
	}

	// Converte a lista para uma String
	public String toString () {
		String str = "{";
		Node <T> cur = first;
		while (cur != null) {
			str += cur.getValue ();
			cur = cur.getNext ();
			if (cur != null) str += ",";
		}
		str += "}";
		return str;
	}

	// MOOSHAK

	// ED188

	public T get(int pos){

		if(pos<0 || pos>= size)
			return null;

		Node<T> cur = first;

		for(int i=0; i<pos;i++)
			cur = cur.getNext();

		return cur.getValue();
	}


	// ED 189

	public T remove(int pos){

		if(pos<0 || pos >= size)
			return null;

		if(pos == 0){
			T no = first.getValue();
			first = first.getNext();
			size--;
			return no;
		}

		Node<T> cur = first;


		for(int i=0;i<pos-1;i++){
			cur = cur.getNext();
		}
		T no = cur.getNext().getValue();
		cur.setNext(cur.getNext().getNext());
		size--;
		return no;
	}


	// ED 190

	public SinglyLinkedList<T> copy () {

		SinglyLinkedList<T> lista = new SinglyLinkedList<T>();
		Node<T> cur = first;

		while(cur != null){
			lista.addLast(cur.getValue());
			cur = cur.getNext();
		}
		return lista;
	}


	// ED191

	public void duplicate() {

		if(isEmpty())
			return;

		Node<T> cur = first;

		while(cur != null){
			Node<T> duplicado = new Node<T>(cur.getValue(),cur.getNext());
			cur.setNext(duplicado);
			cur = cur.getNext().getNext();
			size++;
		}
	}


	// ED192

	public int count(T value){

		int contador = 0;
		Node<T> cur = first;

		while(cur != null){
			if(cur.getValue().equals(value))
				contador++;
			cur = cur.getNext();
		}
		return contador;
	}

	// ED193

	public int find (T value){

		Node<T> cur = first;
		for(int i=0;i<size;i++){
			if(cur.getValue().equals(value)){
				return i;
			}
			cur = cur.getNext();
		}
		return -1;
	}

	public void removeAll(T value){
		while(count(value) != 0)
			remove(find(value));
	}

}