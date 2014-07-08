package physics;

import processing.core.PVector;

public interface Force {

	public PVector act(PVector pos, PVector vel);
}
