/**
 * A program to simulate the spread of an epidemic through a network of locations. The program uses a 
 * SymbolGraph to represent the network of locations and their connections, and it simulates the 
 * spread of the epidemic by infecting locations and their neighbors until all locations are infected.
 */

import utils.StdOut;
import utils.StdRandom;
import utils.SymbolGraph;


public class Epidemic {
    private SymbolGraph sg;
    private boolean[] infectedCitiesBool;
    private String[] infectedCitiesStr;


    public Epidemic(SymbolGraph sg) {
        this.sg = sg;
        infectedCitiesBool = new boolean[sg.graph().V()];
        infectedCitiesStr = new String[sg.graph().V()];
    }

    /**
     * Marks the location as infected.
     * @param location the location to infect
     */
    public void infect(String location) {
        if (!infectedCitiesBool[sg.indexOf(location)]) {
        	infectedCitiesBool[sg.indexOf(location)] = true;
        	infectedCitiesStr[sg.indexOf(location)] = location;
        }
    }

    /**
     * Infects all neighbors of the location, if the location is infected.
     * @param location the location whose neighbors to infect
    */
    public void infectNeighbors(String location) {
        if (infectedCitiesBool[sg.indexOf(location)]) {
        	int s = sg.indexOf(location);
        	for (int v : sg.graph().adj(s)) {
                infect(sg.name(v));
            }
        }
    }

    /**
     * Returns an array of all infected locations.
     * @return an array of all infected locations
     */
    public String[] getInfected() {        
        int count = 0;
        for (int i = 0; i < infectedCitiesBool.length; i++) {
        	if (infectedCitiesBool[i]) {
        		count++;
        	}
        }
        String[] retCities = new String[count];
        count = 0;
        for (int i = 0; i < infectedCitiesBool.length; i++) {
        	if (infectedCitiesBool[i]) {
        		retCities[count] = infectedCitiesStr[i];
        		count++;
        	}
        }
        return retCities; 
    }

    /**
     * Returns true if all locations are infected, and false otherwise.
     * @return true if all locations are infected, and false otherwise
     */
    public boolean allInfected() {
        boolean flag = true;
        
        for (int i = 0; i < infectedCitiesBool.length; i++) {
        	flag = (flag && infectedCitiesBool[i]);
        }
        return flag; 
    }


    public static void main(String[] args) {
        // Initialize a new SymbolGraph model on which to simulate an epidemic
        String filename = "routes.txt";
        String delim = " ";
        SymbolGraph sg = new SymbolGraph(filename, delim);
        Epidemic model = new Epidemic(sg);
        

        model.infect("JFK"); // Starts an epidemic in the "JFK" location
        String[] infected;
        while (!model.allInfected()) { // Spread the infection randomly until no healthy locations remain
            infected = model.getInfected();
            String nextOutbreak = infected[StdRandom.uniform(infected.length)];
            model.infectNeighbors(nextOutbreak);
            StdOut.println("Outbreak in " + nextOutbreak + "!");
            StdOut.println("Infected: " + String.join(", ", model.getInfected()));
        }
        StdOut.println("All is lost.");

    }
}