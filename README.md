# Epidemic

## Overview
This program simulates how an epidemic spreads through a network of connected locations (for example airport routes).

It builds a graph from `routes.txt`, starts with one infected location (`JFK`), then repeatedly infects neighboring locations until every node in the graph is infected.

## Why It Is Interesting
- Models a real-world process (contagion spread) using graph traversal concepts.
- Demonstrates how local neighborhood expansion eventually covers an entire connected network.
- Uses randomized outbreak selection to produce different infection sequences across runs.

## Files
- `Epidemic.java`: Simulation logic.
- `routes.txt`: Input graph data.

## Run
From this folder:

```bash
javac utils/*.java Epidemic.java
java Epidemic
```

## License
This project is licensed under the [GNU General Public License v3.0 (GPLv3)](LICENSE).
