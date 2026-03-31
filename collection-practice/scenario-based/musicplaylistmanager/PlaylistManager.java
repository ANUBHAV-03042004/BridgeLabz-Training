package musicplaylistmanager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class PlaylistManager {

    private final String userId;
    private final Map<String, Playlist> playlists = new LinkedHashMap<>();

    public PlaylistManager(String userId) {
        this.userId = userId;
    }

    public Playlist createPlaylist(String name) {
        if (playlists.containsKey(name)) {
            System.out.println("[WARN] Playlist '" + name + "' already exists.");
            return playlists.get(name);
        }
        Playlist p = new Playlist(name);
        playlists.put(name, p);
        System.out.println("Created playlist: '" + name + "'");
        return p;
    }

    public Playlist getPlaylist(String name) {
        Playlist p = playlists.get(name);
        if (p == null) throw new NoSuchElementException("Playlist not found: " + name);
        return p;
    }

    public void deletePlaylist(String name) {
        if (playlists.remove(name) != null)
            System.out.println("Deleted playlist: '" + name + "'");
        else
            System.out.println("[WARN] Playlist '" + name + "' not found.");
    }

    public void printAllPlaylists() {
        System.out.println("\nPlaylists for User: " + userId);
        playlists.forEach((n, p) ->
                System.out.printf("   %-20s %d songs%n", "'" + n + "'", p.size()));
    }
}