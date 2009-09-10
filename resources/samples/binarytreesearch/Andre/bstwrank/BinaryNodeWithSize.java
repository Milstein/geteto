package binarytreesearch;

// Basic node stored in unbalanced binary search trees
// Note that this class is not accessible outside
// of this package.

class BinaryNodeWithSize extends BinaryNode
{
  BinaryNodeWithSize( Comparable x )
  {
    super( x );
    size = 0;
  }

  int size;
}
