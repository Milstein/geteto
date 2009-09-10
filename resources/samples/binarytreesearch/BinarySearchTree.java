package binarytreesearch;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// void removeMin( )      --> Remove minimum item
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Exceptions are thrown by insert, remove, and removeMin if warranted

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
*/
public class BinarySearchTree
{

    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {   root = null; }

    /**
     * Insert into the tree.
     * @param x the item to insert.
     * @throws DuplicateItemException if x is already present.
     */
    public void insert( Comparable x )
    {   root = insert( x, root ); }

    /**
     * Remove from the tree..
     * @param x the item to remove.
     * @throws ItemNotFoundException if x is not found.
     */
    public void remove( Comparable x )
    {   root = remove( x, root ); }

    /**
     * Remove minimum item from the tree.
     * @throws ItemNotFoundException if tree is empty.
     */
    public void removeMin( )
    {   root = removeMin( root ); }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public Comparable findMin( )
    {   return elementAt( findMin( root ) ); }



    /**
     * Find the largest item in the tree.
     * @return the largest item or null if empty.
     */
    public Comparable findMax( )
    {   return elementAt( findMax( root ) ); }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public Comparable find( Comparable x )
    {   return elementAt( find( x, root ) ); }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {   root = null; }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {   return root == null; }

    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private Comparable elementAt( BinaryNode t )
    {
      if  (t == null)
        return null;
      else
        return t.element;
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected BinaryNode insert( Comparable x, BinaryNode t )
    {

      BinaryNode newnode, aux, aux1;
      newnode = new BinaryNode(x);

      if (t != null) {
      	aux = t;
        do {
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
        } while(aux != null);

        if (aux1.element.compareTo(x) < 0)
          aux1.right = newnode;
        else
          aux1.left = newnode;
      }
      else t = newnode;

      return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode remove( Comparable x, BinaryNode t )
    {
        BinaryNode aux = t, aux1 = null, aux2, paiaux2;

        while(aux != null && elementAt(aux).compareTo(x) != 0)
        {
            aux1 = aux;
            if (elementAt(aux).compareTo(x) > 0)
              aux = aux.left;
            else
              aux = aux.right;
        }

        if (aux == null)
          throw new ItemNotFoundException();

        if (aux.left == aux.right && aux.left == null) { //caso 1: nó folha
          if (aux == t)
            return null; //é a raiz

          if (aux1.left == aux)
            aux1.left = null;
          else
            aux1.right = null;

          return t;
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
	              t = aux.left; //é raíz
	          } else { //só tem filho da direita
	            if (aux1 != null) { //tem pai?
	              if (aux1.left == aux) //testa se é filho esquerdo ou direito do pai
	                aux1.left = aux.right;
	              else
	                aux1.right = aux.right;
	            }
	            else
	              t = aux.right; //é a raíz
	          }
	
	          return t;
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
	            t = aux2;
	          aux2.left = aux.left;
	          return t;
	        }
	
	        //o filho da direita não é folha
	        paiaux2 = aux2;                
	        while (aux2.left != null) {
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
	        }
	        else
	          t = aux2;
	
	        aux2.right = aux.right;
	        aux2.left = aux.left;
	        return t;
	     }
	   }
    }

    /**
     * Internal method to remove minimum item from a subtree.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode removeMin( BinaryNode t )
    {
      BinaryNode aux, aux1 = null;

      aux = t;
      while (aux.left != null)
        aux = aux.left;

      t = remove(aux.element, t);
      return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected BinaryNode findMin( BinaryNode t )
    {
      BinaryNode aux;
      if (t == null)
        return t;

      aux = t;
      while (aux.left != null)
        aux = aux.left;

      return aux;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    protected BinaryNode findMax( BinaryNode t )
    {
      BinaryNode aux;
      if (t == null)
        return t;

      aux = t;
      while (aux.right != null)
        aux = aux.right;

      return aux;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    protected BinaryNode find( Comparable x, BinaryNode t )
    {
      BinaryNode aux = t;

      while (aux != null && elementAt(aux).compareTo(x) != 0) {
        if (elementAt(aux).compareTo(x) > 0)
          aux = aux.left;
        else
          aux = aux.right;
      }

      if (aux == null)
        throw new ItemNotFoundException();

      return aux;
    }

      /** The tree root. */
    protected BinaryNode root;
}
