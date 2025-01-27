public class Country {
    private final String name;
    private final String population;
    private final String continent;
    private final String capital;

    public Country(String name, String population, String continent, String capital) {
        this.name = name;
        this.population = population;
        this.continent = continent;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }

    public String getCapital() {
        return capital;
    }
}
