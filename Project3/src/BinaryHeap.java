import java.util.Arrays;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
  private static final int DEFAULT_CAPACITY = 32;

  private int theSize;
  private AnyType[] data;

  public BinaryHeap()
  {
    this(DEFAULT_CAPACITY);
  }

  public BinaryHeap(int capacity)
  {
    theSize = 0;
    data = (AnyType[]) new Comparable[capacity];
  }

  public BinaryHeap(AnyType[] items)
  {
    bottomUpHeapConstruction(items);
  }

  public int size() { return theSize; }

  public boolean isEmpty() { return (size() == 0); }

  public void insert(AnyType v)
  {
    if (size() == data.length) resize(2 * data.length);

    data[size()] = v;
    theSize++;
    upHeapBubbling(size()-1);
  }

  public AnyType deleteMinimum() throws IllegalStateException
  {
    AnyType minimumValue;

    if (isEmpty()) throw new IllegalStateException("Heap is empty");

    minimumValue = data[0];
    data[0] = data[size()-1];
    data[size()-1] = null;
    theSize--;
    downHeapBubbling(0);

    if (size() <= (data.length / 4)) resize(data.length / 2);

    return minimumValue;
  }

  private int parent(int i) { return ((i - 1) / 2); }

  private int leftChild(int i) { return (2 * i + 1); }

  private int rightChild(int i) { return (2 * i + 2); }

  private boolean hasLeftChild(int i) { return (leftChild(i) < size()); }

  private boolean hasRightChild(int i) { return (rightChild(i) < size()); }

  private void upHeapBubbling(int index)
  {
    int parentIndex;
    AnyType upBubbleValue;

    upBubbleValue = data[index];
    while (index > 0)
    {
      parentIndex = parent(index);
      if (upBubbleValue.compareTo(data[parentIndex]) >= 0) break;
      data[index] = data[parentIndex];
      index = parentIndex;
    }
    data[index] = upBubbleValue;
  }

  private void downHeapBubbling(int index)
  {
    int leftChildIndex, rightChildIndex, smallestChildIndex;
    AnyType downBubbleValue;

    downBubbleValue = data[index];
    while (hasLeftChild(index))
    {
      leftChildIndex = leftChild(index);
      smallestChildIndex = leftChildIndex;
      if (hasRightChild(index))
      {
        rightChildIndex = rightChild(index);
        if (data[leftChildIndex].compareTo(data[rightChildIndex]) > 0)
          smallestChildIndex = rightChildIndex;
      }
      if (data[smallestChildIndex].compareTo(downBubbleValue) >= 0) break;
      data[index] = data[smallestChildIndex];
      index = smallestChildIndex;
    }
    data[index] = downBubbleValue;
  }

  private void bottomUpHeapConstruction(AnyType[] items)
  {
    int n;

    n = items.length;
    theSize = n;
    data = Arrays.copyOfRange(items, 0, n);
    heapify();
  }

  private void heapify()
  {
    int i, startIndex;

    startIndex = parent(size() - 1);
    for (i = startIndex; i >= 0; i--) downHeapBubbling(i);
  }

  protected void resize(int newCapacity)
  {
    int n = size();

    AnyType[] temp = (AnyType[]) new Comparable[newCapacity];
    for (int i=0; i < n; i++)
      temp[i] = data[i];
    data = temp;
  }
}