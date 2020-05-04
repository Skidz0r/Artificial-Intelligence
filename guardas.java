// Greddy search
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

    void decreaseKey(int vertv, int newkey) {
          int i = pos_a[vertv];
          a[i].vertkey = newkey;

          heapify(i);
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

    public void heapify(int i) {
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
  public static int numero_vertices=0;
  static int nretangulos;
  static int posx=0;
  static int[][] vertices=new int[10000][3];

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

  public static void atualizar(boolean[] map,Heapmax heap)
  {
    for(int i=1;i<=heap.size;i++)
    {
      int n=contagem_vertices(vertices[heap.a[i].pos][0],vertices[heap.a[i].pos][1],map);
      if(( contagem_vertices(vertices[heap.a[i].pos][0],vertices[heap.a[i].pos][1],map) ) < heap.a[i].vertkey)
      {
          int v=heap.a[1].vertkey-n;
          heap.decreaseKey(heap.a[i].pos,v);
    }
    heap.heapify(i);

        //  System.out.print(" "+heap.a[i].vertkey+" ");
  }
      //System.out.println("fim");
}

public static void Greedy_Search(boolean[] map, Heapmax heap,int flag)
{
  while(areAllTrue(map)==false)
  {
    atualizar(map,heap);
    int flag2=0;
    int x=vertices[heap.a[1].pos][0];
    int y=vertices[heap.a[1].pos][1];
    if(heap.a[1].vertkey==2)
    flag2=3;
    if(heap.a[1].vertkey==1)
    flag2=2;
    if(heap.a[1].vertkey==0)
    flag2=1;
    if(flag2==3)
    {
          if(vertices[heap.a[1].pos][0]==x && vertices[heap.a[1].pos][1]==y && contagem_vertices(x,y,map)==3)
          {
            preencher(x,y,map);
            flag=1;
          }
    }

    if(flag2==2)
    {
          if(vertices[heap.a[1].pos][0]==x && vertices[heap.a[1].pos][1]==y && contagem_vertices(x,y,map)==2 )
          {
            preencher(x,y,map);
            flag=1;
          }
    }

    if(flag2==1)
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

    if(flag==1)
    {
      System.out.println("x:"+x+" y:"+y);
      flag=0;
    }
    heap.extractMax();
  }
}

public static void reset()
{
  posx=0;
  for(int i=0;i<numero_vertices;i++)
  {
    for(int k=0;k<3;k++)
    {
      vertices[i][k]=0;
    }
  }
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
        numero_vertices+=nvertices;
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
    Greedy_Search(map,heap,flag);
    reset();
    }
  }
}
