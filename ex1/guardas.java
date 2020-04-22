
/*-------------------------------------------------------------------*\
|  Definicao de GRAFOS SEM PESOS                                      |
|     Assume-se que os vertices sao numerados de 1 a |V|.             |
|                                                                     |
|   A.P.Tomas, CC2001 (material para prova pratica), DCC-FCUP, 2017   |
|   Last modified: 2017.12.18                                         |
\--------------------------------------------------------------------*/

import java.lang.*;
import java.util.*;

class Qnode {
    int vert;
    int vertkey;
    int pos;                               // Heap modificada para identificar qual vertices corresponde

    Qnode(int v, int key,int p) {
  pos=p;
	vert = v;
	vertkey = key;
    }
}

class Heapmax {
    private static int posinvalida = 0;
    int sizeMax,size;
    int pos;
    Qnode[] a;
    int[] pos_a;

    Heapmax(int vec[], int n) {
	a = new Qnode[n + 1];
	pos_a = new int[n + 1];
	sizeMax = n;
	size = n;
	for (int i = 1; i <= n; i++) {
      pos=i;
	    a[i] = new Qnode(i,vec[i],pos);
	    pos_a[i] = i;
	}

	for (int i = n/2; i >= 1; i--)
	    heapify(i);
    }

    boolean isEmpty() {
	if (size == 0) return true;
	return false;
    }

    int extractMax() {
	int vertv = a[1].vert;
	swap(1,size);
	pos_a[vertv] = posinvalida;  // assinala vertv como removido
	size--;
	heapify(1);
	return vertv;
    }

    void increaseKey(int vertv, int newkey) {

	int i = pos_a[vertv];
	a[i].vertkey = newkey;

	while (i > 1 && compare(i, parent(i)) > 0) {
	    swap(i, parent(i));
	    i = parent(i);
	}
    }


    void insert(int vertv, int key)
    {
	if (sizeMax == size)
	    new Error("Heap is full\n");

	size++;
	a[size].vert = vertv;
	pos_a[vertv] = size;   // supondo 1 <= vertv <= n
	increaseKey(vertv,key);   // aumenta a chave e corrige posicao se necessario
    }

    void write_heap(){
	System.out.printf("Max size: %d\n",sizeMax);
	System.out.printf("Current size: %d\n",size);
	System.out.printf("(Vert,Key,Pos)\n---------\n");
	for(int i=1; i <= size; i++)
	    System.out.printf("(%d,%d,%d)\n",a[i].vert,a[i].vertkey,a[i].pos);

	System.out.printf("-------\n(Vert,PosVert)\n---------\n");

	for(int i=1; i <= sizeMax; i++)
	    if (pos_valida(pos_a[i]))
		System.out.printf("(%d,%d)\n",i,pos_a[i]);
    }

    private int parent(int i){
	return i/2;
    }
    private int left(int i){
	return 2*i;
    }
    private int right(int i){
	return 2*i+1;
    }

    private int compare(int i, int j) {
	if (a[i].vertkey < a[j].vertkey)
	    return -1;
	if (a[i].vertkey == a[j].vertkey)
	    return 0;
	return 1;
    }


    private void heapify(int i) {
	int l, r, largest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	largest = i;
	if (compare(l,largest) > 0)
	    largest = l;
	if (compare(r,largest) > 0)
	    largest = r;

	if (i != largest) {
	    swap(i, largest);
	    heapify(largest);
	}

    }

    private void swap(int i, int j) {
	Qnode aux;
	pos_a[a[i].vert] = j;
	pos_a[a[j].vert] = i;
	aux = a[i];
	a[i] = a[j];
	a[j] = aux;
    }

    private boolean pos_valida(int i) {
	return (i >= 1 && i <= size);
    }
}


public class guardas
{
  static int nretangulos;
  static int posx=0;
  static int[][] vertices=new int[1000][3];

  public static int contagem(int i,int x, int y)     // Contar quantos vertices são iguais a x y
  {
    int contagem=0;
    for(int k=i+1;k<posx;k++)
    {
      if(vertices[k][0]==x && vertices[k][1]==y)
      {
        contagem++;
      }
    }
    return contagem;
  }

  public static boolean areAllTrue(boolean[] array)    // Saber se o array map está totalmente "TRUE"
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

  public static int contagem_ciclo(boolean[] array)         // Saber quantos FALSE o array ainda tem
  {
    int contagem=0;
    for(int i=0;i<nretangulos;i++)
    {
      if(array[i]==false)
      {
        contagem++;
      }
    }
    return contagem;
  }

  public static int contagem_vertices(int x, int y, boolean[] map)   // Saber se ao colocar o vigia no vertice x y quantos retangulos ira cobrir que estjeam FALSE
  {
    int contagem=0;
    for(int k=0;k<posx;k++)
    {
      if(vertices[k][0]==x && vertices[k][1]==y && map[vertices[k][2]-1]==false)
      {
        contagem++;
      }
    }
    return contagem;
  }


  public static void preencher(int x, int y, boolean[] map)   // Preencher o array map com true nos casos que o vertice cubra o retangulo
  {
    for(int k=0;k<posx;k++)
    {
      if(vertices[k][0]==x && vertices[k][1]==y)
      {
        map[vertices[k][2]-1]=true;
      }
    }
  }

  public static void printar_boolean(boolean[] map) // Função simples para printar array booleano
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
    Scanner in = new Scanner(System.in);
    int instancias=in.nextInt();
    int flag=0;
    for(int l=1;l<=instancias;l++)
    {
      System.out.println("Retangulo "+l);
     nretangulos=in.nextInt();
     boolean[] map = new boolean[nretangulos];
     Arrays.fill(map,true);
     int nr=in.nextInt();
     for(int p=0;p<nr;p++)
     {
       int x=in.nextInt();
       map[x-1]=false;
     }
     //printar_boolean(map);
      for(int k=1;k<=nretangulos;k++)
      {
        int retangulo=in.nextInt();
        int nvertices=in.nextInt();
        for(int h=1;h<=nvertices;h++)
        {
          int x=in.nextInt();
          int y=in.nextInt();
          vertices[posx][0]=x;
          vertices[posx][1]=y;
          vertices[posx][2]=retangulo;
          posx++;
        }
      }

    int[] contagem=new int[posx+1];
    for(int i=0;i<posx;i++)
    {
        contagem[i]=contagem(i,vertices[i][0],vertices[i][1]);
    }
    Heapmax heap=new Heapmax(contagem,posx);
    while(areAllTrue(map)==false)
    {
      flag=0;
      int x=vertices[heap.a[1].pos][0];
      int y=vertices[heap.a[1].pos][1];
      if(contagem_ciclo(map)==3 && flag==0)
      {
        flag=0;
        for(int i=0;i<contagem[heap.a[1].pos];i++)
        {
          for(int k=0;k<posx;k++)
          {
            if(vertices[k][0]==x && vertices[k][1]==y && contagem_vertices(x,y,map)==3)
            {
              preencher(x,y,map);
              flag=1;
            }
          }
        }
      }

      if(contagem_ciclo(map)==2 || flag==0)
      {
        flag=0;
        for(int i=0;i<contagem[heap.a[1].pos];i++)
        {
          for(int k=0;k<posx;k++)
          {
            if(vertices[k][0]==x && vertices[k][1]==y && contagem_vertices(x,y,map)==2)
            {
              preencher(x,y,map);
              flag=1;
            }
          }
        }
      }

      if(contagem_ciclo(map)==1 || flag==0)
      {
        flag=0;
        for(int i=0;i<contagem[heap.a[1].pos];i++)
        {
          for(int k=0;k<posx;k++)
          {
            if(vertices[k][0]==x && vertices[k][1]==y && map[vertices[k][2]-1]==false)
            {
              flag=1;
              map[vertices[k][2]-1]=true;
            }
          }
        }
      }

      if(flag==1)
      {
        System.out.println("x:"+x+" y:"+y);
        flag=0;
      }
      heap.extractMax();
     //printar_boolean(map);

    }
    System.out.println("Vigias todos colocados");
    System.out.println();

      posx=0;
      for(int i=0;i<1000;i++)
      {
        for(int k=0;k<3;k++)
        {
          vertices[i][k]=0;
        }
      }
      Arrays.fill(map,false);

    }
  }
}
