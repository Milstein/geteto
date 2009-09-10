package binarytreesearch;

import junit.framework.*;
import probe.DefaultProber;

/**
 * A sample test case, testing <code>BinarySearchTreeWithRank</code>.
 */
public class BinTreeTestCase3 extends TestCase {
        private BinarySearchTree tree;
        private BinarySearchTreeWithRank rankTree;

        public BinTreeTestCase3( String str ) {
                super( str );
        }

        public BinTreeTestCase3(  ) {
                this( "" );
        }



        public void setUp() {
                tree = new BinarySearchTree();
                rankTree = new BinarySearchTreeWithRank();
        }


	public void testCase1() { //def-uso da variável sl
          Comparable f;
          rankTree.insert(new Integer(50));
          rankTree.insert(new Integer(40));
          rankTree.insert(new Integer(60));
	  rankTree.insert(new Integer(39));
          rankTree.insert(new Integer(41));
          rankTree.insert(new Integer(59));
	  rankTree.insert(new Integer(61));
	  rankTree.insert(new Integer(38));
          f = rankTree.findKth(6);
	  
	  assertEquals("59", f.toString());
        }

	public void testCase2() { //k < 1
          Comparable f;
          rankTree.insert(new Integer(50));
          
	  try {
            f = rankTree.findKth(0);
	    fail("Excessão deveria ser gerada!");
 	  } catch (IllegalArgumentException success) {}
        }

       public void testCase3() { //cobertura do método void removeMin
         Comparable f;
         rankTree.insert(new Integer(4));
         rankTree.removeMin();
         try {
           f = rankTree.find(new Integer(4));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }

       public void testCase4() { //cobertura do método void BinarySearchTreeWithRank::removeMin
         Comparable f;
         rankTree.insert(new Integer(4));
	 rankTree.insert(new Integer(3));
	 rankTree.insert(new Integer(2));
         rankTree.removeMin();
         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }

       public void testCase5() { //cobertura do método void BinarySearchTree::findMax
         Comparable f;
         tree.insert(new Integer(1));
	 tree.insert(new Integer(2));
	 tree.insert(new Integer(3));
	 tree.insert(new Integer(4));
         f = tree.findMax();
         assertEquals(new Integer(4), f);
       }

       public void testCase6() { //cobertura do método void BinarySearchTree::findMax
         Comparable f;
         tree.insert(new Integer(1));
         f = tree.findMax();
         assertEquals(new Integer(1), f);
       }

       public void testCase7() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         tree.insert(new Integer(2));
	 tree.insert(new Integer(1));
	 tree.insert(new Integer(3));
	 tree.insert(new Integer(4));
         tree.remove(new Integer(2));
         try {
           f = tree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }
     
       public void testCase8() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         tree.insert(new Integer(1));
	 tree.insert(new Integer(2));
         tree.remove(new Integer(2));
         try {
           f = tree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }

       public void testCase9() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         tree.insert(new Integer(1));
	 tree.insert(new Integer(2));
	 tree.insert(new Integer(3));
         tree.remove(new Integer(2));
         try {
           f = tree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }

       public void testCase10() { //cobertura do método void BinarySearchTreeWithRank::remove         
         Comparable f;
         rankTree.insert(new Integer(1));
	 rankTree.insert(new Integer(2));
	 rankTree.insert(new Integer(3));
         rankTree.remove(new Integer(2));
         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }              

       public void testCase11() { //cobertura do método void BinarySearchTreeWithRank::remove         
         Comparable f;
         rankTree.insert(new Integer(2));
	 rankTree.insert(new Integer(1));
	 rankTree.insert(new Integer(5));
	 rankTree.insert(new Integer(4));
	 rankTree.insert(new Integer(3));
         rankTree.remove(new Integer(2));

         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       } 

       public void testCase12() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         tree.insert(new Integer(1));
	 tree.insert(new Integer(2));
	 tree.insert(new Integer(3));
         tree.remove(new Integer(2));
         try {
           f = tree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }              

       public void testCase13() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         tree.insert(new Integer(2));
	 tree.insert(new Integer(1));
	 tree.insert(new Integer(5));
	 tree.insert(new Integer(4));
	 tree.insert(new Integer(3));
         tree.remove(new Integer(2));

         try {
           f = tree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       } 

       public void testCase14() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         rankTree.insert(new Integer(2));
	 rankTree.insert(new Integer(1));
	 rankTree.insert(new Integer(3));
	 rankTree.insert(new Integer(4));
         rankTree.remove(new Integer(2));
         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }
     
       public void testCase15() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         rankTree.insert(new Integer(1));
	 rankTree.insert(new Integer(2));
         rankTree.remove(new Integer(2));
         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }

       public void testCase16() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         rankTree.insert(new Integer(1));
	 rankTree.insert(new Integer(2));
	 rankTree.insert(new Integer(3));
         rankTree.remove(new Integer(2));
         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }

       public void testCase17() { //cobertura do método void BinarySearchTree::remove         
         Comparable f;
         tree.insert(new Integer(5));
	 tree.insert(new Integer(1));
	 tree.insert(new Integer(8));
	 tree.insert(new Integer(6));
	 tree.insert(new Integer(7));
         tree.remove(new Integer(5));

         try {
           f = tree.find(new Integer(5));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       } 



  protected void tearDown() {
  	 DefaultProber.dump();
  }

}