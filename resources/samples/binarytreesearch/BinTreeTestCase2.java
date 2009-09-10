package binarytreesearch;

import junit.framework.*;
import probe.DefaultProber;

/**
 * A sample test case, testing <code>BinarySearchTreeWithRank</code>.
 */
public class BinTreeTestCase2 extends TestCase {
        private BinarySearchTree tree;
        private BinarySearchTreeWithRank rankTree;

        public BinTreeTestCase2( String str ) {
                super( str );
        }

        public BinTreeTestCase2(  ) {
                this( "" );
        }



        public void setUp() {
                tree = new BinarySearchTree();
                rankTree = new BinarySearchTreeWithRank();
        }

        //structural test cases
        public void testCase() { //testa remoção para elemento que não está na árvore
         tree.root = tree.insert(new Integer(1), tree.root);
         tree.root = tree.insert(new Integer(4), tree.root);
         tree.root = tree.insert(new Integer(2), tree.root);
         try {
           tree.root = tree.remove( new Integer(6), tree.root);
           fail("Exceçao deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase1() { //cobertura do método void insert
         tree.insert(new Integer(1));
         assertEquals("1", tree.root.element.toString());
        }

        public void testCase2() { //cobertura do método void remove
         tree.insert(new Integer(1));
         tree.remove(new Integer(1));
         assertEquals(null, tree.root);
        }

        public void testCase3() { //cobertura do método void findMin
         Comparable f;
         tree.root = tree.insert(new Integer(1), tree.root);
         tree.root = tree.insert(new Integer(4), tree.root);
         tree.root = tree.insert(new Integer(2), tree.root);

         tree.removeMin();
         try {
           f = tree.find(new Integer(1));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase4() { //cobertura do caso da folha ser filho direito no remove
         Comparable f;
         tree.insert(new Integer(1));
         tree.insert(new Integer(2));
         tree.remove(new Integer(2));

         try {
           f = tree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase5() { //cobertura do caso do removido com um filho ser filho direito
         Comparable f;
         tree.insert(new Integer(4));
         tree.insert(new Integer(3));
         tree.insert(new Integer(2));
         tree.remove(new Integer(3));

         try {
           f = tree.find(new Integer(3));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}

         tree.remove(new Integer(4));

         try {
           f = tree.find(new Integer(4));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}

         tree.insert(new Integer(4));
         tree.remove(new Integer(2));

         try {
           f = tree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase6() { //cobertura do caso do removido com um filho ser filho direito
         Comparable f;
         tree.insert(new Integer(3));
         tree.insert(new Integer(1));
         tree.insert(new Integer(2));
         tree.remove(new Integer(1));

         try {
           f = tree.find(new Integer(1));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase7() { //cobertura do caso do removido com um filho ser filho direito
         Comparable f;
         tree.insert(new Integer(1));
         tree.insert(new Integer(3));
         tree.insert(new Integer(2));
         tree.remove(new Integer(3));

         try {
           f = tree.find(new Integer(3));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase8() { //cobertura do caso do removido com um filho ser filho direito
         Comparable f;
         tree.insert(new Integer(1));
         tree.insert(new Integer(2));
         tree.insert(new Integer(3));
         tree.remove(new Integer(2));

         try {
           f = tree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase9() {
          Comparable f;
          tree.insert(new Integer(2));
          tree.insert(new Integer(1));
          tree.insert(new Integer(4));
          tree.insert(new Integer(3));
          tree.remove(new Integer(2));

          try {
            f = tree.find(new Integer(2));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase10() {
          Comparable f;
          tree.insert(new Integer(2));
          tree.insert(new Integer(4));
          tree.insert(new Integer(3));
          tree.insert(new Integer(5));
          tree.remove(new Integer(4));

          try {
            f = tree.find(new Integer(4));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase11() {
          Comparable f;
          tree.insert(new Integer(6));
          tree.insert(new Integer(4));
          tree.insert(new Integer(3));
          tree.insert(new Integer(5));
          tree.remove(new Integer(4));

          try {
            f = tree.find(new Integer(4));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase12() {
          Comparable f;
          tree.insert(new Integer(6));
          tree.insert(new Integer(4));
          tree.insert(new Integer(3));
          tree.insert(new Integer(5));
          tree.remove(new Integer(4));

          try {
            f = tree.find(new Integer(4));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase13() {
          Comparable f;
          tree.insert(new Integer(2));
          tree.insert(new Integer(1));
          tree.insert(new Integer(3));
          tree.remove(new Integer(2));

          try {
            f = tree.find(new Integer(2));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase14() {
          Comparable f;
          tree.insert(new Integer(5));
          tree.insert(new Integer(10));
          tree.insert(new Integer(7));
          tree.insert(new Integer(13));
          tree.insert(new Integer(12));
          tree.insert(new Integer(14));
          tree.remove(new Integer(10));

          try {
            f = tree.find(new Integer(2));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase15() {
          Comparable f;
          tree.insert(new Integer(14));
          tree.insert(new Integer(10));
          tree.insert(new Integer(7));
          tree.insert(new Integer(12));
          tree.insert(new Integer(11));
          tree.insert(new Integer(13));
          tree.remove(new Integer(10));

          try {
            f = tree.find(new Integer(10));
            fail("Excessão deveria ser gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase16() {
          Comparable f;
          tree.insert(new Integer(3));
          tree.insert(new Integer(2));
          tree.insert(new Integer(1));
          tree.removeMin();

          try {
            f = tree.find(new Integer(1));
            fail("Excessão deveria ser gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase17() {
          Comparable f;
          tree.insert(new Integer(3));
          tree.insert(new Integer(2));
          tree.insert(new Integer(1));
          f = tree.findMin();
          assertEquals("1", f.toString());
        }


        //Testes para a BinarySearchTreeWithRank - pegando dos casos para a BinarySearchTree

        public void testCase18() {
          rankTree.insert(new Integer (1));
          rankTree.insert(new Integer (4));
          rankTree.insert(new Integer (2));

          assertEquals("2", rankTree.root.right.left.element.toString());
        }

        public void testCase19() {
          rankTree.root = rankTree.insert(new Integer (1), rankTree.root);

          assertEquals("1", rankTree.root.element.toString());
        }

        public void testCase20() {
          rankTree.insert( new Integer (1));
          rankTree.insert( new Integer (4));
          try {
            rankTree.root = rankTree.insert( new Integer (1), rankTree.root);
            fail("Excessão DuplicateItemException deveria ser gerada!");
          }
          catch (DuplicateItemException success) {}
        }

        //test cases for deletion

        public void testCase21() {
         rankTree.insert(new Integer (1));
         rankTree.insert(new Integer (4));
         rankTree.insert(new Integer (2));
         rankTree.remove(new Integer (2));

         assertEquals("1", rankTree.root.element.toString());
         assertEquals("4", rankTree.root.right.element.toString());
       }

       public void testCase22() {
         try {
           rankTree.insert(new Integer (1));
           rankTree.insert(new Integer (4));
           rankTree.remove( new Integer (2));
           fail("Excessão ItemNotFoundException deveria ser gerada!");
         }
         catch (ItemNotFoundException success) {}
       }


       public void testCase23() {
         try {
           rankTree.insert( new Integer (1));
           rankTree.remove( new Integer (2));
           fail("Excessão ItemNotFoundException deveria ser gerada!");
         } catch (ItemNotFoundException success) {}
       }

       public void testCase24() { //cobertura do método void removeMin
         Comparable f;
         rankTree.insert(new Integer(4));
         rankTree.insert(new Integer(1));
         rankTree.insert(new Integer(2));

         rankTree.removeMin();
         try {
           f = rankTree.find(new Integer(1));
           fail("Exceção deveria ter sido gerada!");
         }
         catch (ItemNotFoundException success) {}
       }
       
       //testa outros casos da remoção para BinarySearchTreeWithRank
       public void testCase25() {
          Comparable f;
          rankTree.insert(new Integer(5));
          rankTree.insert(new Integer(10));
          rankTree.insert(new Integer(7));
          rankTree.insert(new Integer(13));
          rankTree.insert(new Integer(12));
          rankTree.insert(new Integer(14));
          rankTree.remove(new Integer(10));

          try {
            f = rankTree.find(new Integer(10));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
          
          assertEquals(4, ((BinaryNodeWithSize) rankTree.root.right).size);
          assertEquals(1, ((BinaryNodeWithSize) rankTree.root.right.left).size);
          assertEquals(2, ((BinaryNodeWithSize) rankTree.root.right.right).size);
        }

        public void testCase26() {
          Comparable f;
          rankTree.insert(new Integer(14));
          rankTree.insert(new Integer(10));
          rankTree.insert(new Integer(7));
          rankTree.insert(new Integer(12));
          rankTree.insert(new Integer(11));
          rankTree.insert(new Integer(13));
          rankTree.remove(new Integer(10));

          try {
            f = rankTree.find(new Integer(10));
            fail("Excessão deveria ser gerada!");
          } catch (ItemNotFoundException success) {}
        }
        
        public void testCase27() { //testa remoção para elemento que não está na árvore
         rankTree.insert(new Integer(1));
         rankTree.insert(new Integer(4));
         rankTree.insert(new Integer(2));
         try {
           rankTree.remove( new Integer(6));
           fail("Exceçao deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase28() { //cobertura do método void insert
         rankTree.insert(new Integer(1));
         assertEquals("1", rankTree.root.element.toString());
        }

        public void testCase29() { //cobertura do método void remove
         rankTree.insert(new Integer(1));
         rankTree.remove(new Integer(1));
         assertEquals(null, rankTree.root);
        }
        
        public void testCase30() { //cobertura do caso da folha ser filho direito no remove
         Comparable f;
         rankTree.insert(new Integer(1));
         rankTree.insert(new Integer(2));
         rankTree.remove(new Integer(2));

         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
         assertEquals(1, ((BinaryNodeWithSize) rankTree.root).size);
        }

        public void testCase31() { //cobertura do caso do removido com um filho ser filho direito
         Comparable f;
         rankTree.insert(new Integer(4));
         rankTree.insert(new Integer(3));
         rankTree.insert(new Integer(2));
         rankTree.remove(new Integer(3));

         try {
           f = rankTree.find(new Integer(3));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
         
         assertEquals(2, ((BinaryNodeWithSize) rankTree.root).size);

         rankTree.remove(new Integer(4));

         try {
           f = rankTree.find(new Integer(4));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
         
         assertEquals(1, ( (BinaryNodeWithSize) rankTree.root).size);

         rankTree.insert(new Integer(4));
         rankTree.remove(new Integer(2));

         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase32() { //cobertura do caso do removido com um filho ser filho direito
         Comparable f;
         rankTree.insert(new Integer(3));
         rankTree.insert(new Integer(1));
         rankTree.insert(new Integer(2));
         rankTree.remove(new Integer(1));

         try {
           f = rankTree.find(new Integer(1));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase33() { //cobertura do caso do removido com um filho ser filho direito
         Comparable f;
         rankTree.insert(new Integer(1));
         rankTree.insert(new Integer(3));
         rankTree.insert(new Integer(2));
         rankTree.remove(new Integer(3));

         try {
           f = rankTree.find(new Integer(3));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase34() { //cobertura do caso do removido com um filho ser filho direito
         Comparable f;
         rankTree.insert(new Integer(1));
         rankTree.insert(new Integer(2));
         rankTree.insert(new Integer(3));
         rankTree.remove(new Integer(2));

         try {
           f = rankTree.find(new Integer(2));
           fail("Exceção deveria ter sido gerada!");
         } catch (ItemNotFoundException success) {}
        }

        public void testCase35() {
          Comparable f;
          rankTree.insert(new Integer(2));
          rankTree.insert(new Integer(1));
          rankTree.insert(new Integer(4));
          rankTree.insert(new Integer(3));
          rankTree.remove(new Integer(2));

          try {
            f = rankTree.find(new Integer(2));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase36() {
          Comparable f;
          rankTree.insert(new Integer(2));
          rankTree.insert(new Integer(4));
          rankTree.insert(new Integer(3));
          rankTree.insert(new Integer(5));
          rankTree.remove(new Integer(4));

          try {
            f = rankTree.find(new Integer(4));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase37() {
          Comparable f;
          rankTree.insert(new Integer(6));
          rankTree.insert(new Integer(4));
          rankTree.insert(new Integer(3));
          rankTree.insert(new Integer(5));
          rankTree.remove(new Integer(4));

          try {
            f = rankTree.find(new Integer(4));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase38() {
          Comparable f;
          rankTree.insert(new Integer(6));
          rankTree.insert(new Integer(4));
          rankTree.insert(new Integer(3));
          rankTree.insert(new Integer(5));
          rankTree.remove(new Integer(4));

          try {
            f = rankTree.find(new Integer(4));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }

        public void testCase39() {
          Comparable f;
          rankTree.insert(new Integer(2));
          rankTree.insert(new Integer(1));
          rankTree.insert(new Integer(3));
          rankTree.remove(new Integer(2));

          try {
            f = rankTree.find(new Integer(2));
            fail("Exceção deveria ter sido gerada!");
          } catch (ItemNotFoundException success) {}
        }


  protected void tearDown() {
  	 DefaultProber.dump();
  }

}
