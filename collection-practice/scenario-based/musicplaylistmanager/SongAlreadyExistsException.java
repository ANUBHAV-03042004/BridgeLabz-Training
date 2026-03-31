package musicplaylistmanager;

public class SongAlreadyExistsException extends Exception {
    private final String songId;
    private final String playlistName;

    public SongAlreadyExistsException(String songId, String playlistName) {
        super(String.format("Song [%s] already exists in playlist '%s'.", songId, playlistName));
        this.songId       = songId;
        this.playlistName = playlistName;
    }

    public String getSongId()       { return songId;       }
    public String getPlaylistName() { return playlistName; }
}