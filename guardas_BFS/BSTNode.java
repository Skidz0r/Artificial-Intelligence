// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// No de uma arvore binaria de pesquisa
// Ultima alteracao: 13/05/2018
// -----------------------------------------------------------

// O tipo T tem de implementar o interface Comparable
// (ou te-lo herdado de uma super classe).
public class BSTNode<T extends Comparable<? super T>> {
   private T value;          // Valor guardado no no
   private BSTNode<T> left;  // Filho esquerdo
   private BSTNode<T> right; // Filho direito
   private T x;
   private T y;
   private int[] vec;
   // Construtor
   BSTNode(T v, BSTNode<T> l, BSTNode<T> r,T cordx,T cordy,int[] i) {
      value = v;
      left = l;
      right = r;
      x = cordx;
      y= cordy;
      vec=i;
   }

   // Getters e Setters
   public T getValue() {return value;}
   public T getx() {return x;}
   public T gety() {return y;}
   public BSTNode<T> getLeft() {return left;}
   public BSTNode<T> getRight() {return right;}
   public void setx(T cordx) {x=cordx;}
   public void sety(T cordy) {y=cordy;}
   public void setValue(T v) {value = v;}
   public void insert_cobertura(int x) {
     int i=0;
     while(vec[i]!=0)
     {
       i++;
     }
     if(i<=2)
     {
       vec[i]=x;
     }
   }
   public void show_cobertura()
   {
     for(int i=0;i<3;i++)
     {
       System.out.println(vec[i]);
     }
   }
   public void setLeft(BSTNode<T> l) {left = l;}
   public void setRight(BSTNode<T> r) {right = r;}
}
