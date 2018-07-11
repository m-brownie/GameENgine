package renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * This class render the model from the VAO.
 * 
 * @author Matthieu
 *
 */
public class Renderer {

	/**
	 * Clear the color of the last frame.
	 * This method is called at every frame.
	 */
	public void prepare() {
		// Reset the color of the background.
		GL11.glClearColor(1, 0, 0, 1);
		// Activate the reset of the color.
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Render the model.
	 * 
	 * @param model
	 */
	public void render(RawModel model) {
		// Bind the VAO that we want to render.
		GL30.glBindVertexArray(model.getVaoID());
		// Activate the attribute list in which the data is stored.
		GL20.glEnableVertexAttribArray(0);
		// Render
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		// Disable the attribute list.
		GL20.glDisableVertexAttribArray(0);
		// Unbind VAO.
		GL30.glBindVertexArray(0);
	}
}
