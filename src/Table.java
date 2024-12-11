import java.io.Serializable;

public class Table implements Serializable {
    private final int id;
    private final int capacity;
    private boolean reserved;

    // Using this() to call the primary constructor
    public Table(int capacity) {
        this(0, capacity);  // Calling the primary constructor with default id as 0
    }    

    // Using this. to refer to the instance of the class 
    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.reserved = false;
    }

    public int getId() { return id; }
    public int getCapacity() { return capacity; }
    public boolean isReserved() { return reserved; }
    public void reserve() { this.reserved = true; }
    public void release() { this.reserved = false; }

    @Override
    public String toString() {
        return "Table{id=" + id + ", capacity=" + capacity + ", reserved=" + reserved + "}";
    }
}
