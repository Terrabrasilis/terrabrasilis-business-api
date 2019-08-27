package br.inpe.dpi.terrabrasilis.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jether
 */
@Document(collection = "vision_to_vision")
public final class VisionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @DBRef
    @Indexed
    private Vision root;
    @DBRef
    private List<Vision> visions;

    public String getRootVisionId() {
    	return this.root.getId();
    }
    
    public Vision getVision() {
        return this.root;
    }

    public void setRoot(Vision root) {
        this.root = root;
    }

    public void setVisions(List<Vision> visions) {
        this.visions = visions;
    }

    public List<Vision> getVisions() {
        return this.visions == null ? new ArrayList<Vision>()
        			: Collections.unmodifiableList(this.visions);
    }
}