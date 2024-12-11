import java.util.List;
import java.util.function.Predicate;

public class Filters extends BaseFilter {

    // Constructor using super() to call the constructor of the BaseFilter class
    public Filters() {
        super();  // Calls the constructor of BaseFilter
    }

    // Overriding the filterList method
    @Override
    public <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        // Using super. to invoke the method from BaseFilter
        System.out.println("\n");
        return (List<T>) super.filterList(list, predicate);  // Calls the method from BaseFilter
    }
}