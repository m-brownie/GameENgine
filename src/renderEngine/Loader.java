package renderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * This class load a 3D models into the memory
 * by storing position data about the model into VAO.
 * 
 * @author Matthieu
 *
 */
public class Loader {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();

	public RawModel loadToVAO(float[] positions, int[] indices) {
		// Create an empty VAO.
		int vaoID = createVAO();
		// Bind the indices buffer to the VAO.
		bindIndicesBuffer(indices);
		// Store the position data into an attribute list of the VAO. 
		// Here, the attribute list at index 0.
		storeDataInAttributeList(0, positions);
		// Unbind the VAO.
		unbindVAO();
		
		// Return the data (the informations about the VAO) as a RawModel.
		return new RawModel(vaoID, indices.length);
	}
	
	/**
	 * Delete the VAOs and VBOs.
	 */
	public void cleanUp() {
		for (int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		
		for (int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
	}
	
	/**
	 * Create a VAO.
	 * 
	 * @return the ID of the VAO created.
	 */
	private int createVAO() {
		// Create an empty VAO.
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		// Activate the VAO.
		GL30.glBindVertexArray(vaoID);
		
		return vaoID;
	}
	
	/**
	 * Store the data into the attribute list of the VAO.
	 */
	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		// Create an empty VBO.
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		// Bind the VBO.
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		// Convert the data into a FloatBuffer.
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		// Store the data (buffer) into the VBO.
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		// Put the VBO into the VAO.
		GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		// Unbind the VBO.
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Unbind the VAO.
	 */
	private void unbindVAO() {
		// Unbind the currently bound VAO.
		GL30.glBindVertexArray(0);
	}
	
	/**
	 * Load indices buffer and bind it to the VAO.
	 * 
	 * @param indices the indices to bind.
	 */
	private void bindIndicesBuffer(int[] indices) {
		// Create an empty VBO.
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		// Bind the VBO.
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		// Convert the data into a IntBuffer.
		IntBuffer buffer = storeDataInIntBuffer(indices);
		// Store the data (buffer) into the VBO.
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	/**
	 * Convert a float array of data into a float buffer.
	 * 
	 * @param data : the data to convert.
	 * @return the FloatBuffer.
	 */
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		// Create an empty FloatBuffer.
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		// Put the data into the FloatBuffer
		buffer.put(data);
		// Finish the writting in the buffer.
		buffer.flip();
		
		return buffer;
	}
	
	/**
	 * Convert an int array of data into a int buffer.
	 * 
	 * @param data : the data to convert.
	 * @return the IntBuffer.
	 */
	private IntBuffer storeDataInIntBuffer(int[] data) {
		// Create an empty IntBuffer.
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		// Put the data into the IntBuffer
		buffer.put(data);
		// Finish the writting in the buffer.
		buffer.flip();
		
		return buffer;
	}
}
