package renderEngine;

/**
 * Represent a 3D model stored in memory.
 * 
 * @author Matthieu
 *
 */
public class RawModel {

	// VAO ID
	private int vaoID;
	// Number of verteces in the 3D model.
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}	
}
