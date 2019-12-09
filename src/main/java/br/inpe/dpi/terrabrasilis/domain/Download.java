package br.inpe.dpi.terrabrasilis.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author jether.rodrigues
 *
 */
@Document(collection = "download")
public final class Download implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	//@Indexed(unique = true)
	private String name;
	private String description;
	private String link;
	private String metadata;
	private String lang;
	private String category;
	private boolean enabled;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created;
	
	public Download() {
		this.created = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getLink() {
		return link;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getLang() {
		return lang;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Download other = (Download) obj;
		return Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Download [id=").append(id).append(", name=").append(name).append(", description=")
				.append(description).append(", link=").append(link).append(", lang=").append(lang).append(", enabled=").append(enabled)
				.append(", metadata=").append(metadata)
				.append(", created=").append(created).append("]");
		return builder.toString();
	}
}
