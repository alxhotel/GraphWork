package graphwork;

import java.util.Collections;
import java.util.List;

public class FinderDestructiveImproved extends Finder {

	public FinderDestructiveImproved(Graph graph) {
		super(graph);
	}

	/**
	 * Get minimum cover tree based on least connected known node
	 * IMPROVED with:
	 *	- Articulation points
	 * 
	 * Note: by they way we select the next node, is always a vertex cover
	 * 
	 * @return Graph
	 */
	@Override
	public Graph getMinimumCoverTree() {
		// Select the whole graph (clone it)
		this.newGraph = new Graph(this.graph);

		while (true) {
			// Get known vertices with all known neighbors
			List<Vertex> solutionArray = this.graph.getAllKnownVerticesWithAllKnownNeighbors(this.newGraph);
			if (solutionArray.isEmpty()) {
				// No more vertices available
				
				// Finished: Make sure graph is a tree
				this.newGraph.convertToTree();
				break;
			}
			
			// Articulation points
			List<Vertex> articulationPoints = this.newGraph.getArticulationPoints();
			
			// Remove articulation points
			solutionArray.removeAll(articulationPoints);
			if (solutionArray.isEmpty()) {
				// No more vertices available
				
				// Finished: Make sure graph is a tree
				this.newGraph.convertToTree();
				break;
			}
			
			// Order by least connected
			Collections.sort(solutionArray, (Vertex t, Vertex t1) -> {
				if (this.newGraph.getNeighbors(t).size() < this.newGraph.getNeighbors(t1).size()) {
					return -1;
				} else if (this.newGraph.getNeighbors(t).size() > this.newGraph.getNeighbors(t1).size()) {
					return 1;
				} else {
					return 0;
				}
			});
			
			// Selected vertex
			Vertex selectedVertex = solutionArray.get(0);
			
			// Remove vertex
			this.newGraph.removeVertex(selectedVertex);
		}
		
		return this.newGraph;
	}
	
}