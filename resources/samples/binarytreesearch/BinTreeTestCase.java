package binarytreesearch;

import junit.framework.*;
import probe.DefaultProber;

/**
 * A sample test case, testing <code>BinarySearchTreeWithRank</code>.
 */
public class BinTreeTestCase extends TestCase {
        private BinarySearchTree tree;
        private BinarySearchTreeWithRank rankTree;

        public BinTreeTestCase( String str ) {
                super( str );
        }

        public BinTreeTestCase(  ) {
                this( "" );
        }



        public void setUp() {
                tree = new BinarySearchTree();
                rankTree = new BinarySearchTreeWithRank();
        }

        //funcional test cases

        //test cases for insertion
        public void testCase1() {
          tree.root = tree.insert(new Integer (1), tree.root);
          tree.root = tree.insert(new Integer (4), tree.root);
          tree.root = tree.insert(new Integer (2), tree.root);
          tree.root = tree.insert(new Integer (3), tree.root);

          assertEquals("3",tree.root.right.left.right.element.toString());


          rankTree.root = rankTree.insert( new Integer (1), rankTree.root);
          rankTree.root = rankTree.insert( new Integer (4), rankTree.root);
          rankTree.root = rankTree.insert( new Integer (2), rankTree.root);
          rankTree.root = rankTree.insert( new Integer (3), rankTree.root);

          assertEquals("3",rankTree.root.right.left.right.element.toString());

          //1.1
          assertEquals(4, ((BinaryNodeWithSize)rankTree.root).size);

          //1.2
          assertEquals(3, ((BinaryNodeWithSize)rankTree.root.right).size);

          //1.3
          assertEquals(2, ((BinaryNodeWithSize)rankTree.root.right.left).size);

          //1.4
          assertEquals(1, ((BinaryNodeWithSize)rankTree.root.right.left.right).size);
        }

        public void testCase2() {
          tree.root = tree.insert(new Integer (1), tree.root);
          tree.root = tree.insert(new Integer (4), tree.root);
          tree.root = tree.insert(new Integer (2), tree.root);

          assertEquals("2", tree.root.right.left.element.toString());
        }

        public void testCase3() {
          tree.root = tree.insert(new Integer (1), tree.root);

          assertEquals("1", tree.root.element.toString());
        }

        public void testCase4() {
          tree.root = tree.insert( new Integer (1), tree.root);
          tree.root = tree.insert( new Integer (4), tree.root);
          try {
            tree.root = tree.insert( new Integer (1), tree.root);
            fail("Excessão DuplicateItemException deveria ser gerada!");
          }
          catch (DuplicateItemException success) {}
        }

        //test cases for deletion

        public void testCase5() {
          tree.root = tree.insert(new Integer (1), tree.root);
          tree.root = tree.insert(new Integer (4), tree.root);
          tree.root = tree.insert(new Integer (2), tree.root);
          tree.root = tree.remove(new Integer (4), tree.root);

          assertEquals("1", tree.root.element.toString());
          assertEquals("2", tree.root.right.element.toString());

          rankTree.root = rankTree.insert(new Integer (1), rankTree.root);
          rankTree.root = rankTree.insert(new Integer (4), rankTree.root);
          rankTree.root = rankTree.insert(new Integer (2), rankTree.root);
          rankTree.root = rankTree.remove(new Integer (4), rankTree.root);

          assertEquals("1", rankTree.root.element.toString());
          assertEquals("2", rankTree.root.right.element.toString());

          //5.1
          assertEquals(2, ((BinaryNodeWithSize)rankTree.root).size);
          //5.2
          assertEquals(1, ((BinaryNodeWithSize)rankTree.root.right).size);
        }

        public void testCase6() {
         tree.root = tree.insert(new Integer (1), tree.root);
         tree.root = tree.insert(new Integer (4), tree.root);
         tree.root = tree.insert(new Integer (2), tree.root);
         tree.root = tree.remove(new Integer (2), tree.root);

         assertEquals("1", tree.root.element.toString());
         assertEquals("4", tree.root.right.element.toString());
       }

       public void testCase7() {
         try {
           tree.root = tree.insert(new Integer (1), tree.root);
           tree.root = tree.insert(new Integer (4), tree.root);
           tree.root = tree.remove( new Integer (2), tree.root);
           fail("Excessão ItemNotFoundException deveria ser gerada!");
         }
         catch (ItemNotFoundException success) {}
       }


       public void testCase8() {
         try {
           tree.root = tree.remove( new Integer (2), tree.root);
           fail("Excessão ItemNotFoundException deveria ser gerada!");
         } catch (ItemNotFoundException success) {}
       }

       //test cases for searching

       public void testCase9() {
         Comparable f;
         tree.root = tree.insert( new Integer (1), tree.root);
         tree.root = tree.insert( new Integer (4), tree.root);
         tree.root = tree.insert( new Integer (2), tree.root);
         f = tree.find(new Integer (2));

         assertEquals("2", f.toString());
       }

       public void testCase10() {
         try {
           Comparable f;
           tree.root = tree.insert( new Integer (1), tree.root);
           tree.root = tree.insert( new Integer (4), tree.root);
           tree.root = tree.insert( new Integer (2), tree.root);
           f = tree.find( new Integer (3));
           fail("Exceçao ItemNotFoundException deveria ser gerada!");
         }
         catch (ItemNotFoundException success) {}
       }

       public void testCase11() {
         Comparable f;
         tree.root = tree.insert( new Integer (1), tree.root);
         tree.root = tree.insert( new Integer (4), tree.root);
         tree.root = tree.insert( new Integer (2), tree.root);
         f = tree.findMin();

         assertEquals("1", f.toString());
       }

       public void testCase12() {
         Comparable f;
         f = tree.findMin();

         assertEquals(null, f);
       }

       public void testCase13() {
         Comparable f;
         tree.root = tree.insert( new Integer (1), tree.root);
         tree.root = tree.insert( new Integer (4), tree.root);
         tree.root = tree.insert( new Integer (2), tree.root);
         f = tree.findMax();

         assertEquals("4", f.toString());
       }

       public void testCase14() {
         Comparable f;
         f = tree.findMax();

         assertEquals(null, f);
       }

       public void testCase15() {
         Comparable f;
         rankTree.root = rankTree.insert( new Integer (1), rankTree.root);
         rankTree.root = rankTree.insert( new Integer (4), rankTree.root);
         rankTree.root = rankTree.insert( new Integer (2), rankTree.root);

         f = rankTree.findKth(1);
         assertEquals("1", f.toString());

         //15.1
         f = rankTree.findKth(2);
         assertEquals("2", f.toString());

         //15.2
         f = rankTree.findKth(3);
         assertEquals("4", f.toString());
       }

       public void testCase16() {
         BinaryNode f;
         rankTree.root = rankTree.insert( new Integer (1), rankTree.root);
         rankTree.root = rankTree.insert( new Integer (4), rankTree.root);
         rankTree.root = rankTree.insert( new Integer (2), rankTree.root);

         try {
           f = rankTree.findKth(5, rankTree.root);
           fail("Excessão IllegalArgumentException deveria ser gerada!");
         } catch (IllegalArgumentException success) {}
         catch (Exception e)  {
           fail("Excessão incorreta gerada!");
         }
       }

       public void testCase17() {
         tree.root = tree.insert( new Integer (1), tree.root);
         tree.root = tree.insert( new Integer (4), tree.root);
         tree.root = tree.insert( new Integer (2), tree.root);

         assertEquals(false, tree.isEmpty());
       }

       public void testCase18() {
         assertEquals(true, tree.isEmpty());
       }

       public void testCase19() {
         tree.root = tree.insert( new Integer (1), tree.root);
         tree.root = tree.insert( new Integer (4), tree.root);
         tree.root = tree.insert( new Integer (2), tree.root);

         tree.makeEmpty();
         assertEquals(true, tree.isEmpty());
       }



  protected void tearDown() {
   	 DefaultProber.dump();
  }

}
