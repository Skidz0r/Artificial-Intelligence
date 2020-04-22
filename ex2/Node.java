public class Node<T> {

	private T value;			// valor guardado no nó
	private Node<T> next;		// referencia para o prozimo nó da lista


	// Construtor
	Node(T v, Node<T> n){
		value = v;
		next = n;
	}


	// Getters e Setters
	public T getValue() { return value; }
	public Node<T> getNext() { return next; }
	public void setValue(T v) { value = v;}
	public void setNext(Node<T> n) { next = n; }

}