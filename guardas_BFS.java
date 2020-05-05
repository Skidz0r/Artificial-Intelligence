
// BFS SEARCH
import java.lang.*;
import java.util.*;

class BSTree<T extends Comparable<? super T>> {
   public BSTNode<T> root;
   BSTree() {
      root = null;
   }
   public BSTNode<T> getRoot() {return root;}
   public void setRoot(BSTNode<T> r) {root = r;}
   public boolean isEmpty() {
      return root == null;
   }
   public void clear() {
      root = null;
   }
   public int numberNodes() {
      return numberNodes(root);
   }
   private int numberNodes(BSTNode<T> n) {
      if (n == null) return 0;
      return 1 + numberNodes(n.getLeft()) + numberNodes(n.getRight());
   }
   public boolean contains(T value) {
      return contains(root, value);
   }
   private boolean contains(BSTNode<T> n, T value) {
    // int lados=0;
      if (n==null) return false;
      if (value.compareTo(n.getValue()) < 0)
         return contains(n.getLeft(), value);
      if (value.compareTo(n.getValue()) >= 0)
         return contains(n.getRight(), value);
      return true;
   }
   public boolean insert(T value, int x, int y, int retangulo) {
       root = insert(root, value, x, y, retangulo);
       return true;
    }
    int lados=0;
    int flag_lados=0;
    private BSTNode<T> insert(BSTNode<T> n, T value, int x, int y,int retangulo) {
       int vec[] = { retangulo, 0, 0, };
       if (n==null)
          return new BSTNode<T>(value, null, null,x,y,vec,0);
       else if (value.compareTo(n.getValue()) < 0)
          n.setLeft(insert(n.getLeft(), value,x,y,retangulo));
       else if (value.compareTo(n.getValue()) >= 0)
          n.setRight(insert(n.getRight(), value,x,y,retangulo));
       return n;
    }
   public boolean remove(T value) {
      if (!contains(value)) return false;
      root = remove(root, value);
      return true;
   }
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
   public int depth() {
      return depth(root);
   }

   private int depth(BSTNode<T> n) {
      if (n == null) return -1;
      return 1 + Math.max(depth(n.getLeft()), depth(n.getRight()));
   }
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
}


public class guardas_BFS
{
  public static int numero_retangulos;
  public static int vertices_existentes=0;
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
           cur.setValue(cur.contagem_value());
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
    for(int i=0;i<numero_retangulos;i++)
    {
      if(map[i]==false)
      {
        contagem++;
      }
    }
    return contagem;
  }

  public static int contar_true(boolean[] map,int nretangulos)
  {
    int contagem=0;
    for(int i=0;i<numero_retangulos;i++)
    {
      if(map[i]==true)
      {
        contagem++;
      }
    }
    return contagem;
  }

  public static boolean areAllTrue(boolean[] array,int nretangulos)    // Saber se o array map está totalmente "TRUE"
  {
    for(int i=0;i<numero_retangulos;i++)
    {
      if(array[i]==false)
      {
        return false;
      }
    }
    return true;
  }

  public static void preencher(BSTNode<Integer> n,boolean[] map)
  {
    for(int i=0;i<=2;i++)
    {
      if(n.vec[i]==0)
      {
        map[0]=true;
      }
      else{
        map[n.vec[i]-1]=true;
      }
    }
  }


  public static void remover(int x , int y, BSTree<Integer> arvore)
  {
    MyQueue<BSTNode<Integer>> q = new LinkedListQueue<BSTNode<Integer>>();
    q.enqueue(arvore.root);
    while (!q.isEmpty()) {
       BSTNode<Integer> cur = q.dequeue();
       if (cur != null) {
         if(cur.getx()==x && cur.gety()==y)
         {
           cur.invisivel=1;
           break;
         }
          q.enqueue(cur.getLeft());
          q.enqueue(cur.getRight());
       }
    }
  }

  public static void colocar(int x , int y, BSTree<Integer> arvore)
  {
    MyQueue<BSTNode<Integer>> q = new LinkedListQueue<BSTNode<Integer>>();
    q.enqueue(arvore.root);
    while (!q.isEmpty()) {
       BSTNode<Integer> cur = q.dequeue();
       if (cur != null) {
         if(cur.getx()==x && cur.gety()==y)
         {
           cur.invisivel=0;
           break;
         }
          q.enqueue(cur.getLeft());
          q.enqueue(cur.getRight());
       }
    }
  }

    public static void clear_map(boolean[] map,boolean[] c_map,int nretangulos)
    {
      for(int i=0;i<nretangulos;i++)
      {
        if(c_map[i]==false)
        {
        map[i]=false;
      }
      else if(c_map[i]==true)
      {
        map[i]=true;
      }
    }
  }

    public static int pertence(int x, int y, int[] removidos)
    {
      for(int i=0;i<vertices_existentes*2;i++)
      {
        if(removidos[i]==x && removidos[i+1]==y && removidos[i]>=0 && removidos[i+1]>=0)
        {
          return 1;
        }
        i++;
      }
      return 0;
    }

    public static void array_fill(int[] array,int size,int k)
    {
      for(int i=0;i<size;i++)
      {
        array[i]=k;
      }
    }

    public static void copy_int(int[] solucao, int[] otima)
    {
      for(int i=0;i<vertices_existentes*2;i++)
      {
          otima[i]=solucao[i];
      }
    }

    public static void copy_array(boolean[] map, boolean[] c_map,int nretangulos)
    {
      for(int i=0;i<nretangulos;i++)
      {
        if(map[i]==true)
        {
          c_map[i]=true;
        }
        else if(map[i]=false)
        {
          c_map[i]=false;
        }
      }
    }

    public static int contar(int[] array,int size)
    {
      int contagem=0;
      for(int i=0;i<size;i++)
      {
        if(array[i]>=0 && array[i+1]>=0)
        {
          contagem++;
        }
        i++;
      }
      return contagem;
    }

    public static void Print_Otima(int[] otima,int otimizacao,boolean[] boolean_otima)
    {
      for(int k=0;k<vertices_existentes*2;k++)
       {
         if(otima[k]>=0 && otima[k+1]>=0)
         {
         System.out.println("x:"+otima[k]+" y:"+otima[k+1]);
         }
         k++;
       }
       if(otimizacao==1)
       {
         System.out.println("Solucao nao otima");
       }
    }


public static void BFS_Search(boolean[] map, int nretangulos,BSTree<Integer> arvore)
{
  boolean[] c_map=new boolean[nretangulos];
  copy_array(map,c_map,nretangulos);
  double limite2=nretangulos-contar_true(c_map,nretangulos);
  int limite=(int)Math.ceil(limite2/3);
  int flag=0;
  int[] backup=new int[2];
  int[] solucao=new int[vertices_existentes*2];
  int[] removidos=new int[vertices_existentes*2];
  boolean[] boolean_otima=new boolean[nretangulos];
  int[] otima=new int[vertices_existentes*2];
  int otimizacao=0;
  array_fill(removidos,vertices_existentes*2,-1);
  array_fill(solucao,vertices_existentes*2,-1);
  array_fill(otima,vertices_existentes*2,0);
  int i=0;
  int j=0;
  int h=0;
  int f=0;
        while(! (contar(solucao,vertices_existentes*2)==limite && areAllTrue(map,numero_retangulos)==true) )
        {
          MyQueue<BSTNode<Integer>> q = new LinkedListQueue<BSTNode<Integer>>();
          clear_map(map,c_map,nretangulos);
            i=0;
            j=0;
            if(flag==1)
            {
              remover(backup[0],backup[1],arvore);
              removidos[h]=backup[0];
              removidos[h+1]=backup[1];
              h++;h++;
            }
          if(flag>=2)
           {
                  if(flag>=2)colocar(backup[0],backup[1],arvore);
                  while(pertence(solucao[j],solucao[j+1],removidos)==1)
                  {
                    j++;
                    j++;
                  }
                  if(h==(vertices_existentes*2)-2)
                  {otimizacao=1;break;}
                  removidos[h]=solucao[j];
                  removidos[h+1]=solucao[j+1];
                  if(removidos[h]==-1 && removidos[h+1]==-1)
                  {
                    remover(removidos[f],removidos[f+1],arvore);
                    f++;f++;
;                  }
                  backup[0]=solucao[j];
                  backup[1]=solucao[j+1];
                  //System.out.println("removidos x:"+removidos[f]+" y:"+removidos[f+1]);
                  remover(solucao[j],solucao[j+1],arvore);
                  h++;h++;
                  array_fill(solucao,vertices_existentes*2,-1);
           }

           int search_BFS=3;
           while(search_BFS!=0)
           {
                q.enqueue(arvore.root);
                while (!q.isEmpty()) {
                   BSTNode<Integer> cur = q.dequeue();
                 if (cur != null ) {
              if( cur.contagem_cobertura(map)==search_BFS && cur.invisivel!=1)
                     {
                       preencher(cur,map);
                       solucao[i]=cur.getx();solucao[i+1]=cur.gety();
                       i++;
                       i++;
                     }
                     q.enqueue(cur.getLeft());
                     q.enqueue(cur.getRight());
                   }
                }
                search_BFS--;
              }

      if( contar(solucao,vertices_existentes*2)==limite && areAllTrue(map,numero_retangulos)==true ) // Caso encontre solucao otima
      {
        copy_array(map,boolean_otima,nretangulos);
        copy_int(solucao,otima);
        break;
      }

      if( (contar(solucao,vertices_existentes*2)) < (contar(otima,vertices_existentes*2)) )  // Caso nao encontre solucao otima
      {
        if(contar(solucao,vertices_existentes*2)>=limite)
        {
          if(areAllTrue(map,numero_retangulos)==true )
          {
            copy_array(map,boolean_otima,nretangulos);
          copy_int(solucao,otima);
          }
        }
      }

      if(flag==0)
      {
        backup[0]=solucao[0];
        backup[1]=solucao[1];
      }
      flag++;
    }
  Print_Otima(otima,otimizacao,boolean_otima);
}


  public static void main(String[] args)
  {
     int flag=0;
     int nretangulos;
     Scanner in=new Scanner(System.in);
     BSTree<Integer> arvore = new BSTree<>();
     int instancias=in.nextInt();
      for(int l=1;l<=instancias;l++)
      {
        nretangulos=in.nextInt();
        int nr=in.nextInt();
      boolean[] map = new boolean[nretangulos];
       Arrays.fill(map,true);
        numero_retangulos=nretangulos;
        for(int k=0;k<nr;k++)
        {
          int x=in.nextInt();
          map[x-1]=false;
        }
        for(int i=1;i<=nretangulos;i++)
        {
          int retangulo=in.nextInt();
          int nvertices=in.nextInt();
          vertices_existentes+=nvertices;
          for(int h=1;h<=nvertices;h++)
          {
            int x=in.nextInt();
            int y=in.nextInt();
            if(flag==0)
            {
              flag=1;
              arvore.insert(0,x,y,retangulo);
            }
            else if(flag==1)
            {
              inserir(x,y,retangulo,arvore);
            }
          }
       }
       System.out.println("Retangulo: "+l);
       BFS_Search(map,nretangulos,arvore);
       arvore.clear();
        }
  }
}
