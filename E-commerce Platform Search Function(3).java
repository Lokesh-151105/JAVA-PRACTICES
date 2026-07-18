import java.util.*;

class Item {
    int id;
    String title;
    double cost;
    String type;
    double rating;

    Item(int id, String title, double cost, String type, double rating) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.type = type;
        this.rating = rating;
    }

    void show() {
        System.out.println("#" + id + "  " + title + "  -  Rs." + cost + "  [" + type + "]  rating " + rating);
    }
}

public class ShopFinder {

    static Map<Integer, Item> inventory = new HashMap<>();
    static Map<String, Set<Integer>> wordIndex = new HashMap<>();

    static void stock(Item item) {
        inventory.put(item.id, item);
        // break title into words so we dont have to loop the whole list every search
        String[] words = item.title.toLowerCase().split(" ");
        for (String w : words) {
            if (!wordIndex.containsKey(w)) {
                wordIndex.put(w, new HashSet<>());
            }
            wordIndex.get(w).add(item.id);
        }
        // also index the category, makes it searchable too
        String cat = item.type.toLowerCase();
        if (!wordIndex.containsKey(cat)) {
            wordIndex.put(cat, new HashSet<>());
        }
        wordIndex.get(cat).add(item.id);
    }

    static void loadStuff() {
        stock(new Item(101, "Wireless Mouse", 549, "Electronics", 4.3));
        stock(new Item(102, "Noise Cancelling Headphones", 2299, "Electronics", 4.6));
        stock(new Item(103, "Trail Running Shoes", 2799, "Footwear", 4.1));
        stock(new Item(104, "Cotton Round Neck Tee", 349, "Clothing", 4.0));
        stock(new Item(105, "Slim Leather Wallet", 799, "Accessories", 4.4));
        stock(new Item(106, "Fitness Smart Watch", 3499, "Electronics", 4.2));
        stock(new Item(107, "Non Slip Yoga Mat", 649, "Fitness", 4.5));
        stock(new Item(108, "Padded Laptop Backpack", 1399, "Accessories", 4.3));
        stock(new Item(109, "Mechanical Gaming Keyboard", 1899, "Electronics", 4.7));
        stock(new Item(110, "Canvas Sneakers", 1199, "Footwear", 3.9));
    }

    static List<Item> lookup(String term) {
        String key = term.trim().toLowerCase();
        Set<Integer> ids = new TreeSet<>();

        // check for a direct word hit first
        if (wordIndex.containsKey(key)) {
            ids.addAll(wordIndex.get(key));
        }

        // then also grab anything where the word just contains what we typed
        for (String w : wordIndex.keySet()) {
            if (w.contains(key)) {
                ids.addAll(wordIndex.get(w));
            }
        }

        List<Item> out = new ArrayList<>();
        for (int id : ids) {
            out.add(inventory.get(id));
        }
        return out;
    }

    static List<Item> underBudget(List<Item> list, double budget) {
        List<Item> res = new ArrayList<>();
        for (Item i : list) {
            if (i.cost <= budget) res.add(i);
        }
        return res;
    }

    // sorting manually instead of using a comparator, easier to follow
    static void sortByRatingDesc(List<Item> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).rating < list.get(j + 1).rating) {
                    Item temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        loadStuff();
        Scanner sc = new Scanner(System.in);

        System.out.print("search for: ");
        String term = sc.nextLine();

        List<Item> results = lookup(term);

        if (results.size() == 0) {
            System.out.println("nothing matched that, try again");
            sc.close();
            return;
        }

        System.out.print("max budget (or just hit enter to skip): ");
        String budgetIn = sc.nextLine();

        if (budgetIn.trim().length() > 0) {
            double budget = Double.parseDouble(budgetIn.trim());
            results = underBudget(results, budget);
        }

        if (results.size() == 0) {
            System.out.println("nothing in that price range");
        } else {
            sortByRatingDesc(results);
            System.out.println("\nresults (best rated first):");
            for (Item i : results) {
                i.show();
            }
        }

        sc.close();
    }
}