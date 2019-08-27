package br.inpe.dpi.terrabrasilis.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.inpe.dpi.terrabrasilis.enuns.DatasourceType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author jether
 *
 */
@Document(collection = "datasource")
public final class Datasource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	private String description;
	private String host;
	private String metadata;
	private boolean enabled;
	@Indexed
	private DatasourceType type;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created;
	
	@DBRef
	private List<Download> downloads;
	@DBRef
	private List<Tool> tools;
	
	public Datasource() {
		this.created = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public List<Download> getDownloads() {
		return this.downloads == null ? new ArrayList<Download>() 
				: Collections.unmodifiableList(this.downloads);
	}

	public void setDownloads(List<Download> downloads) {
		this.downloads = downloads;
	}

	public List<Tool> getTools() {
		return this.tools == null ? new ArrayList<Tool>() 
				: Collections.unmodifiableList(this.tools);
	}

	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}

	public void setType(DatasourceType type) {
		this.type = type;
	}

	public DatasourceType getType() {
		return this.type;
	}

	@Override
	public int hashCode() {		
		return Objects.hash(id, name, host);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		Datasource tool = (Datasource) other;
		return Objects.equals(id, tool.id)
					&& Objects.equals(name, tool.name)
					&& Objects.equals(host, tool.host);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Datasource [id=").append(id).append(", name=").append(name).append(", description=")
				.append(description).append(", host=").append(host).append(", metadata=").append(metadata)
				.append(", enabled=").append(enabled).append(", created=").append(created)
				.append(", type=").append(type).append("]");
		return builder.toString();
	}

}
