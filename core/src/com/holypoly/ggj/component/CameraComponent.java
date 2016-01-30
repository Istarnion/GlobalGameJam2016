package com.holypoly.ggj.component;

/**
 *
 * @author istarnion
 */
public class CameraComponent extends Component {

    public int targetID;

    public int cameraID = 0;

    public CameraComponent(int entityID, int targetId, int camera) {
        super(entityID);
        this.targetID = targetId;
        this.cameraID = camera;
    }

}
