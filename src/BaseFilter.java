
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaseFilter {
    
    public void displayMessage() {
        System.out.println("\n");
    }

    
    public <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        System.out.println("Available Tables Filtered by Ascending Order:\n");
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}