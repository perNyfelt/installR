package se.alipsa.installr;

import com.jcabi.aether.Aether;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.resolution.DependencyResolutionException;
import org.sonatype.aether.util.artifact.DefaultArtifact;
import org.sonatype.aether.util.artifact.JavaScopes;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

public class Resolver {

  File repoDir;

  Collection<RemoteRepository> remotes;

  public Resolver(File repoDir) {
    this.repoDir = repoDir;

    remotes = Arrays.asList(
        new RemoteRepository(
            "bedatadriven",
            "default",
            "https://nexus.bedatadriven.com/content/groups/public/"
        )
    );
  }

  public Collection<Artifact> resolve(String groupId, String artifactId, String version ) throws DependencyResolutionException {
    Collection<Artifact> deps = new Aether(remotes, repoDir).resolve(
        new DefaultArtifact(groupId, artifactId, "", "jar", version),
        JavaScopes.RUNTIME
    );
    return deps;
  }

  public Collection<RemoteRepository> getRemotes() {
    return remotes;
  }
}
