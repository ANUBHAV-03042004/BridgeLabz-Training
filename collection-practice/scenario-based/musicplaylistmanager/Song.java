package musicplaylistmanager;

public class Song {

    private final String songId;
    private final String title;
    private final String artist;
    private final Genre  genre;
    private final int    durationSeconds;

    public Song(String songId, String title, String artist,
                Genre genre, int durationSeconds) {
        this.songId          = songId;
        this.title           = title;
        this.artist          = artist;
        this.genre           = genre;
        this.durationSeconds = durationSeconds;
    }

    public String getSongId()          { return songId;          }
    public String getTitle()           { return title;           }
    public String getArtist()          { return artist;          }
    public Genre  getGenre()           { return genre;           }
    public int    getDurationSeconds() { return durationSeconds; }

    public String formattedDuration() {
        return String.format("%d:%02d", durationSeconds / 60, durationSeconds % 60);
    }

  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        return songId.equals(((Song) o).songId);
    }

    @Override
    public int hashCode() { return songId.hashCode(); }

    @Override
    public String toString() {
        return String.format("Song{id='%s', title='%s', artist='%s', genre=%s, duration=%s}",
                songId, title, artist, genre, formattedDuration());
    }
}

