import java.lang.*;
import java.util.*;

class BSTree<T extends Comparable<? super T>> {
   public BSTNode<T> root; // raiz da arvore

   // Construtor
   BSTree() {
      root = null;
   }

   // Getter e Setter para a raiz
   public BSTNode<T> getRoot() {return root;}
   public void setRoot(BSTNode<T> r) {root = r;}

   // Verificar se arvore esta vazia
   public boolean isEmpty() {
      return root == null;
   }

   // Limpa a arvore (torna-a vazia)
   public void clear() {
      root = null;
   }

   // --------------------------------------------------------
   // Numero de nos da arvore
   public int numberNodes() {
      return numberNodes(root);
   }

   private int numberNodes(BSTNode<T> n) {
      if (n == null) return 0;
      return 1 + numberNodes(n.getLeft()) + numberNodes(n.getRight());
   }

   // --------------------------------------------------------
   // O elemento value esta contido na arvore?
   public boolean contains(T value) {
      return contains(root, value);
   }

   private boolean contains(BSTNode<T> n, T value) {
      if (n==null) return false;
      if (value.compareTo(n.getValue()) < 0) // menor? sub-arvore esquerda
         return contains(n.getLeft(), value);
      if (value.compareTo(n.getValue()) >= 0) // maior? sub-arvore direita
         return contains(n.getRight(), value);
      return true; // se nao e menor ou maior, e porque e igual
   }

   // --------------------------------------------------------
   // Adicionar elemento a uma arvore de pesquisa
   // Devolve true se conseguiu inserir, false caso contrario
   public boolean insert(T value, int x, int y, int retangulo) {
       //if (contains(value)) return false;
       root = insert(root, value, x, y, retangulo);
       return true;
    }
    private BSTNode<T> insert(BSTNode<T> n, T value, int x, int y,int retangulo) {
       int vec[] = { retangulo, 0, 0, };
       if (n==null)
          return new BSTNode<T>(value, null, null,x,y,vec);
       else if (value.compareTo(n.getValue()) < 0)
          n.setLeft(insert(n.getLeft(), value,x,y,retangulo));
       else if (value.compareTo(n.getValue()) >= 0)
          n.setRight(insert(n.getRight(), value,x,y,retangulo));
       return n;
    }

   // --------------------------------------------------------
   // Remover elemento de uma arvore de pesquisa
   // Devolve true se conseguiu remover, false caso contrario
   public boolean remove(T value) {
      if (!contains(value)) return false;
      root = remove(root, value);
      return true;
   }

   // Assume-se que elemento existe (foi verificado antes)
   private BSTNode<T> remove(BSTNode<T> n, T value) {
      if (value.compareTo(n.getValue()) < 0)
         n.setLeft(remove(n.getLeft(), value));
      else if (value.compareTo(n.getValue()) > 0)
         n.setRight(remove(n.getRight(), value));
      else if (n.getLeft() == null) // Nao tem filho esquerdo
         n = n.getRight();
      else if (n.getRight() == null) // Nao tem filho direito
         n = n.getLeft();
      else { // Dois fihos: ir buscar maximo do lado esquerdo
         BSTNode<T> max = n.getLeft();
         while (max.getRight() != null) max = max.getRight();
         n.setValue(max.getValue()); // Substituir valor removido
         // Apagar valor que foi para lugar do removido
         n.setLeft(remove(n.getLeft(), max.getValue()));
      }
      return n;
   }

   // --------------------------------------------------------
   // Altura da arvore
   public int depth() {
      return depth(root);
   }

   private int depth(BSTNode<T> n) {
      if (n == null) return -1;
      return 1 + Math.max(depth(n.getLeft()), depth(n.getRight()));
   }
    //--------------------------------------------------------

    public T minValue()
    {
	return minValue(root);
    }

    private T minValue(BSTNode<T> n)
    {
	if(n.getLeft()!=null)
	    {
		return minValue(n.getLeft());
	    }
	return n.getValue();
    }

    public T maxValue()
    {
	return maxValue(root);
    }

    private T maxValue(BSTNode<T> n )
    {
	if(n.getRight()!=null)
	    {
		return maxValue(n.getRight());
	    }
	return n.getValue();
    }

   // --------------------------------------------------------

   // Imprimir arvore em PreOrder
   public void printPreOrder() {
      System.out.print("PreOrder:");
      printPreOrder(root);
      System.out.println();
   }

   private void printPreOrder(BSTNode<T> n) {
      if (n==null) return;
      System.out.print(" " + n.getValue() );
      printPreOrder(n.getLeft());
      printPreOrder(n.getRight());
   }

   // --------------------------------------------------------

   // Imprimir arvore em InOrder
   public void printInOrder() {
      System.out.print("InOrder:");
      printInOrder(root);
      System.out.println();
   }

   private void printInOrder(BSTNode<T> n) {
      if (n==null) return;
      printInOrder(n.getLeft());
      System.out.print(" " + n.getValue());
      printInOrder(n.getRight());
   }

   // --------------------------------------------------------

   // Imprimir arvore em PostOrder
   public void printPostOrder() {
      System.out.print("PostOrder:");
      printPostOrder(root);
      System.out.println();
   }

   private void printPostOrder(BSTNode<T> n) {
      if (n==null) return;
      printPostOrder(n.getLeft());
      printPostOrder(n.getRight());
      System.out.print(" " + n.getValue());
   }

   // --------------------------------------------------------

   // Imprimir arvore numa visita em largura (usando TAD Fila)
   public void printBFS() {
      System.out.print("BFS:");

      MyQueue<BSTNode<T>> q = new LinkedListQueue<BSTNode<T>>();
      q.enqueue(root);
      while (!q.isEmpty()) {
         BSTNode<T> cur = q.dequeue();
         if (cur != null) {
            System.out.print(" " + cur.getValue());
            q.enqueue(cur.getLeft());
            q.enqueue(cur.getRight());
         }
      }
      System.out.println();
   }

   // --------------------------------------------------------

   // Imprimir arvore numa visita em largura (usando TAD Pilha)
   public void printDFS() {
      System.out.print("DFS:");

      MyStack<BSTNode<T>> q = new LinkedListStack<BSTNode<T>>();
      q.push(root);
      while (!q.isEmpty()) {
         BSTNode<T> cur = q.pop();
         if (cur != null) {
            System.out.print(" " + cur.getValue());
            q.push(cur.getLeft());
            q.push(cur.getRight());
         }
      }
      System.out.println();
   }

}


public class guardas_BFS
{
  public static void printar_arvore(BSTree<Integer> arvore)
  {
    MyQueue<BSTNode<Integer>> q = new LinkedListQueue<BSTNode<Integer>>();
    q.enqueue(arvore.getRoot());
    while (!q.isEmpty()) {
       BSTNode<Integer> cur = q.dequeue();
       if (cur != null) {
          System.out.println("x: "+cur.getx()+" y:"+cur.gety());
          System.out.println("vec[0]:"+cur.vec[0]+" vec[1]:"+cur.vec[1]+" vec[2]"+cur.vec[2]);
          q.enqueue(cur.getLeft());
          q.enqueue(cur.getRight());
       }
    }
    System.out.println();
  }

  public static void inserir(int x, int y, int retangulo, BSTree<Integer> arvore)
  {
    int flag=0;
    MyQueue<BSTNode<Integer>> q = new LinkedListQueue<BSTNode<Integer>>();
    q.enqueue(arvore.root);
    while (!q.isEmpty()) {
       BSTNode<Integer> cur = q.dequeue();
       if (cur != null) {
         if(cur.getx()==x && cur.gety()==y)
         {
           flag=1;
           cur.insert_cobertura(retangulo);
         }
          q.enqueue(cur.getLeft());
          q.enqueue(cur.getRight());
       }
    }
    if(flag==0)
    {
    arvore.insert(1,x,y,retangulo);
   }

  }

  public static int contar_false(boolean[] map,int nretangulos)
  {
    int contagem=0;
    for(int i=0;i<nretangulos;i++)
    {
      if(map[i]==false)
      {
        contagem++;
      }
    }
    return contagem;
  }

  public static boolean areAllTrue(boolean[] array,int nretangulos)    // Saber se o array map está totalmente "TRUE"
  {
    for(int i=0;i<nretangulos;i++)
    {
      if(array[i]==false)
      {
        return false;
      }
    }
    return true;
  }

  public static void printar_boolean(boolean[] map,int nretangulos) // Função simples para printar array booleano
  {
    for(int i=0;i<nretangulos;i++)
    {
      if(map[i]==false)
      {
        System.out.print("False ");
      }
      else
      System.out.print("True ");
    }
    System.out.println();
  }

  public static void main(String[] args)
  {
     int flag=0;
     int nretangulos;
     Scanner in=new Scanner(System.in);
     BSTree<Integer> arvore = new BSTree<>();
     int instancias=in.nextInt();
      for(int l=0;l<instancias;l++)
      {
        nretangulos=in.nextInt();
         boolean[] map = new boolean[nretangulos];
        Arrays.fill(map,true);
        int nr=in.nextInt();
        for(int k=0;k<nr;k++)
        {
          int x=in.nextInt();
          map[x-1]=false;
        }
        for(int i=1;i<=nretangulos;i++)
        {
          int retangulo=in.nextInt();
          int nvertices=in.nextInt();
          for(int h=1;h<=nvertices;h++)
          {
            int x=in.nextInt();
            int y=in.nextInt();
            if(flag==0)
            {
              flag=1;
              arvore.insert(1,x,y,retangulo);
            }
            else if(flag==1)
            {
              inserir(x,y,retangulo,arvore);
            }
        }
       }
       while(areAllTrue(map,nretangulos)==false)
       {
       MyQueue<BSTNode<Integer>> q = new LinkedListQueue<BSTNode<Integer>>();
       q.enqueue(arvore.getRoot());
       while (!q.isEmpty()) {
          BSTNode<Integer> cur = q.dequeue();
          if (cur != null)
          {
            if(contar_false(map,nretangulos)>=3)
            {
            if(cur.contagem_cobertura(map)==3)
            {
              if(cur.vec[0]>0)
              map[cur.vec[0]-1]=true;
              if(cur.vec[1]>0)
              map[cur.vec[1]-1]=true;
              if(cur.vec[2]>0)
              map[cur.vec[2]-1]=true;
              System.out.println("x: "+cur.getx()+" y:"+cur.gety());
            }
          }
          if(contar_false(map,nretangulos)<=2 )
          {
          if(cur.contagem_cobertura(map)==2)
          {
            if(cur.vec[0]>0)
            map[cur.vec[0]-1]=true;
            if(cur.vec[1]>0)
            map[cur.vec[1]-1]=true;
            if(cur.vec[2]>0)
            map[cur.vec[2]-1]=true;
            System.out.println("x: "+cur.getx()+" y:"+cur.gety());
          }
        }
        if(contar_false(map,nretangulos)==1)
        {
        if(cur.contagem_cobertura(map)==1)
        {
          if(cur.vec[0]>0)
          map[cur.vec[0]-1]=true;
          if(cur.vec[1]>0)
          map[cur.vec[1]-1]=true;
          if(cur.vec[2]>0)
          map[cur.vec[2]-1]=true;
          System.out.println("x: "+cur.getx()+" y:"+cur.gety());
        }
      }
             q.enqueue(cur.getLeft());
             q.enqueue(cur.getRight());
          }
        }
        //printar_boolean(map,nretangulos);
     }
           //printar_arvore(arvore);
   }
    }
  }
