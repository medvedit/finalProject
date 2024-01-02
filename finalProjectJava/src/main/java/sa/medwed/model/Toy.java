package sa.medwed.model;

public class Toy {

    private int id;
    private String name;
    private int count;
    private int drop;

    public Toy(int id, String name, int count, int drop) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.drop = drop;
    }

    public Toy(String name, int count, int drop) {
        this.name = name;
        this.count = count;
        this.drop = drop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDrop() {
        return drop;
    }

    public void setDrop(int drop) {
        this.drop = drop;
    }

    @Override
    public String toString() {
        return "ID = " + id +
                ", название грушки ='" + name + '\'' +
                ", количество игрушек = " + count + "шт." +
                ", Частота выпадения (вес) = " + drop +
                '%';
    }

}
