package sa.medwed.model;

import static sa.medwed.model.Constants.SEPARATOR;

public class ToyConverter {

    public String convert(Toy toy) {
        return String.format("%s;%s;%s;%s", toy.getId(), toy.getName(), toy.getCount(),
                toy.getDrop());
    }

    public Toy convert(String line) {
        String[] lines = line.split(SEPARATOR);
        return new Toy(Integer.parseInt(lines[0]), lines[1], Integer.parseInt(lines[2]),
                Integer.parseInt(lines[3]));
    }

}
