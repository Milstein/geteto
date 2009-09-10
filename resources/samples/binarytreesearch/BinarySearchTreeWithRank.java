package binarytreesearch;

// BinarySearchTreeWithRank class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// Comparable findKth( k )--> Return kth smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// IllegalArgumentException thrown if k is out of bounds

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
*/
public class BinarySearchTreeWithRank extends BinarySearchTree
{
    /**
     * Find the kth smallest item in the tree.
     * @param k the desired rank (1 is the smallest item).
     * @return the kth smallest item in the tree.
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the subtree.
     */

    public Comparable findKth( int k )
    {
        return findKth( k, root ).element;
    }

    /**
     * Internal method to find kth smallest item in a subtree.
     * @param k the desired rank (1 is the smallest item).
     * @return the node containing the kth smallest item in the subtree.
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the subtree.
     */
    protected BinaryNode findKth( int k, BinaryNode t )
    {
      int sl;

      if (k > ((BinaryNodeWithSize) t).size || k < 1)
        throw new IllegalArgumentException();

      if (t.left != null)
        sl = ((BinaryNodeWithSize) t.left).size;
      else
        sl = 0;

      //Se k é igual a Sl + 1, o nó raiz é o k-ésimo elemento e a busca pára
      if (k == sl + 1)
        return t;
      else //Se k é menor do que Sl + 1, o k-ésimo elemento deve estar na sub-árvore à esquerda
        {
          if (k < sl + 1)
            return findKth(k, t.left);
          else //Senão, o k-ésimo menor elemento é o (k - Sl - 1)-ésimo menor elemento -> sub-árvore da direita
            return findKth(k-sl-1, t.right);
        }
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param tt the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected BinaryNode insert(Comparable x, BinaryNode tt)
    {
      BinaryNodeWithSize newnode;
      BinaryNode aux, aux1;

      newnode = new BinaryNodeWithSize(x);
      newnode.size = 1;

      if (tt != null) {
      	aux = tt;
        do {          
          ((BinaryNodeWithSize) aux).size++;
          if (aux.element.compareTo(x) == 0)
            throw new DuplicateItemException();
          if (aux.element.compareTo(x) > 0) {
            aux1 = aux;
            aux = aux.left;
          }
          else {
            aux1 = aux;
            aux = aux.right;
          }
        }
        while (aux != null);

        if (aux1.element.compareTo(x) < 0)
          aux1.right = (BinaryNode) newnode;
        else
          aux1.left = (BinaryNode) newnode;
      }
      else
        tt = (BinaryNode) newnode;

      return tt;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode remove( Comparable x, BinaryNode tt )
    {
       BinaryNode aux = tt, aux1 = null, aux2, aux3, paiaux2;
       int size;

       Comparable c = super.find(x);        

        while(aux.element.compareTo(x) != 0)
        {
            aux1 = aux;
            ((BinaryNodeWithSize) aux).size--;
            if (aux.element.compareTo(x) > 0)
              aux = aux.left;
            else
              aux = aux.right;
        }

        if (aux.left == aux.right && aux.left == null) { //caso 1: nó folha
          if (aux == tt)
            return null; //é a raiz

          if (aux1.left == aux)
            aux1.left = null;
          else
            aux1.right = null;

          return tt;
        } else {

	        if (aux.right == null || aux.left == null) { //caso 2: só tem um filho
	
	          if (aux.left != null) { //só tem filho da esquerda
	            if (aux1 != null) { //tem pai?
	              if (aux1.left == aux) //testa se é filho esquerdo ou direito do pai
	                aux1.left = aux.left;
	              else
	                aux1.right = aux.left;
	            }
	            else
	              tt = aux.left; //é raíz
	          } else { //só tem filho da direita
	            if (aux1 != null) { //tem pai?
	              if (aux1.left == aux) //testa se é filho esquerdo ou direito do pai
	                aux1.left = aux.right;
	              else
	                aux1.right = aux.right;
	            }
	            else
	              tt = aux.right; //é a raíz
	          }

	          return tt;
	        } else {

	        //caso 3: tem dois filhos
	        aux2 = aux.right;
	
	        //o filho da direita é folha
	        if (aux2.right == aux2.left && aux2.right == null) {
	          if (aux1 != null) {
	            if (aux1.right == aux)
	              aux1.right = aux.right;
	            else
	              aux1.left = aux.right;
	          }
	          else
	            tt = aux2;
	          aux2.left = aux.left;
	          return tt;
	        }
	
	        //o filho da direita não é folha
	        paiaux2 = aux2;
	        while (aux2.left != null) {
	          ((BinaryNodeWithSize) aux2).size--;
	          paiaux2 = aux2;
	          aux2 = aux2.left;
	        }
	
	        paiaux2.left = aux2.right; //se o menor da subarvore da dir. tem filho da direita
	        //o pai dele->filhodaesquerda recebe seu filho da direita
	
	        if (aux1 != null) { //se não é raíz
	          if (aux1.left == aux) //troca o aux pelo menor da subarvore da dir.
	            aux1.left = aux2;
	          else
	            aux1.right = aux2;
	          //atualiza pesos para baixo
	          ((BinaryNodeWithSize) aux2).size = ((BinaryNodeWithSize) aux).size-1;
	        }
	        else
	        {
	          tt = aux2;
	          ((BinaryNodeWithSize) aux2).size = 1;
	        }
	
	        aux2.right = aux.right;
	        aux2.left = aux.left;
	        return tt;
	    }
	  }
    }

    /**
     * Internal method to remove the smallest item from a subtree,
     *     adjusting size fields as appropriate.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundExcetion if the subtree is empty.
     */
    protected BinaryNode removeMin(BinaryNode tt) {
      BinaryNode aux, aux1 = null;

      aux = tt;
      while (aux.left != null)
        aux = aux.left;

      tt = remove(aux.element, tt);
      return tt;
    }
}
