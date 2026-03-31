package musicplaylistmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Playlist {

    private final String                name;
    private final LinkedList<Song>      songs;          
    private final Set<String>           songIdSet;     
    private final Stack<Song>           recentlyPlayed; 
    private static final int            HISTORY_LIMIT = 10;

    public Playlist(String name) {
        this.name           = name;
        this.songs          = new LinkedList<>();
        this.songIdSet      = new HashSet<>();
        this.recentlyPlayed = new Stack<>();
    }

    
    public void addSong(Song song) throws SongAlreadyExistsException {
        if (songIdSet.contains(song.getSongId())) {
            throw new SongAlreadyExistsException(song.getSongId(), name);
        }
        songs.addLast(song);
        songIdSet.add(song.getSongId());
        System.out.println("Added to '" + name + "': " + song.getTitle()
                           + " – " + song.getArtist());
    }

   
    public void addSongFirst(Song song) throws SongAlreadyExistsException {
        if (songIdSet.contains(song.getSongId())) {
            throw new SongAlreadyExistsException(song.getSongId(), name);
        }
        songs.addFirst(song);
        songIdSet.add(song.getSongId());
        System.out.println("Added to front of '" + name + "': " + song.getTitle());
    }

  
    public void addSongAt(int index, Song song) throws SongAlreadyExistsException {
        if (songIdSet.contains(song.getSongId())) {
            throw new SongAlreadyExistsException(song.getSongId(), name);
        }
        songs.add(index, song); // LinkedList supports O(n) indexed insert
        songIdSet.add(song.getSongId());
        System.out.println("Inserted at position " + index
                           + " in '" + name + "': " + song.getTitle());
    }

 
    public boolean removeSong(String songId) {
        Iterator<Song> it = songs.iterator();
        while (it.hasNext()) {
            Song s = it.next();
            if (s.getSongId().equals(songId)) {
                it.remove();
                songIdSet.remove(songId);
                System.out.println("Removed from '" + name + "': " + s.getTitle());
                return true;
            }
        }
        System.out.println("[WARN] Song '" + songId + "' not found in '" + name + "'.");
        return false;
    }

  
    public Song playNext() {
        if (songs.isEmpty()) {
            System.out.println("[INFO] Playlist '" + name + "' is empty.");
            return null;
        }
        Song song = songs.removeFirst();
        pushToHistory(song);
        System.out.println("Now playing: " + song.getTitle()
                           + " – " + song.getArtist()
                           + " [" + song.formattedDuration() + "]");
        return song;
    }

  
    public Song playSong(String songId) {
        Iterator<Song> it = songs.iterator();
        while (it.hasNext()) {
            Song s = it.next();
            if (s.getSongId().equals(songId)) {
                it.remove();
                songIdSet.remove(songId);
                pushToHistory(s);
                System.out.println("Playing: " + s.getTitle()
                                   + " – " + s.getArtist()
                                   + " [" + s.formattedDuration() + "]");
                return s;
            }
        }
        System.out.println("[WARN] Song '" + songId + "' not found in playlist.");
        return null;
    }

    public Song undoLastPlay() {
        if (recentlyPlayed.isEmpty()) {
            System.out.println("[INFO] No play history to undo.");
            return null;
        }
        Song lastPlayed = recentlyPlayed.pop();
        songs.addFirst(lastPlayed);
        songIdSet.add(lastPlayed.getSongId());
        System.out.println("Undo: '" + lastPlayed.getTitle() + "' moved back to front.");
        return lastPlayed;
    }

  
    public Song peekLastPlayed() {
        if (recentlyPlayed.isEmpty()) return null;
        return recentlyPlayed.peek();
    }

  
    public Song peekNext() {
        if (songs.isEmpty()) return null;
        return songs.peekFirst();
    }


    public void printPlaylist() {
        System.out.println("\nPlaylist: '" + name + "' (" + songs.size() + " songs)");
        if (songs.isEmpty()) { System.out.println("   (empty)"); return; }
        int i = 1;
        int totalSec = 0;
        for (Song s : songs) {
            System.out.printf("   %2d. %-30s %-20s [%s] %s%n",
                    i++, s.getTitle(), s.getArtist(),
                    s.formattedDuration(), s.getGenre());
            totalSec += s.getDurationSeconds();
        }
        System.out.printf("   Total duration: %d:%02d%n", totalSec / 60, totalSec % 60);
    }

    public void printHistory() {
        System.out.println("\nRecently Played History (newest first)");
        if (recentlyPlayed.isEmpty()) { System.out.println("   (none)"); return; }

  
        List<Song> history = new ArrayList<>(recentlyPlayed);
        Collections.reverse(history); 
        int i = 1;
        for (Song s : history) {
            System.out.printf("   %2d. %s – %s%n", i++, s.getTitle(), s.getArtist());
        }
    }

 

    public String           getName()    { return name;           }
    public int              size()       { return songs.size();   }
    public boolean          isEmpty()    { return songs.isEmpty();}
    public LinkedList<Song> getSongs()   { return new LinkedList<>(songs); }

    private void pushToHistory(Song song) {
        recentlyPlayed.push(song);
      
        if (recentlyPlayed.size() > HISTORY_LIMIT) {
            recentlyPlayed.remove(0); 
        }
    }
}