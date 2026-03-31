package musicplaylistmanager;

public class MusicPlaylistManager {

    public static void main(String[] args) {

     
        Song s1 = new Song("S001", "Blinding Lights",   "The Weeknd",     Genre.POP,        200);
        Song s2 = new Song("S002", "Bohemian Rhapsody", "Queen",          Genre.ROCK,       355);
        Song s3 = new Song("S003", "Take Five",         "Dave Brubeck",   Genre.JAZZ,       324);
        Song s4 = new Song("S004", "Lose Yourself",     "Eminem",         Genre.HIPHOP,     326);
        Song s5 = new Song("S005", "Strobe",            "deadmau5",       Genre.ELECTRONIC, 607);
        Song s6 = new Song("S006", "Hotel California",  "Eagles",         Genre.ROCK,       391);
        Song s7 = new Song("S007", "Moonlight Sonata",  "Beethoven",      Genre.CLASSICAL,  370);

        PlaylistManager manager  = new PlaylistManager("user_42");
        Playlist        workout  = manager.createPlaylist("Workout Mix");
        Playlist        chill    = manager.createPlaylist("Chill Vibes");
        manager.createPlaylist("Workout Mix"); // duplicate — warns

        try {
            workout.addSong(s1);
            workout.addSong(s4);
            workout.addSong(s5);
            workout.addSongFirst(s6);           // inserts at front
            workout.addSongAt(2, s2);           // inserts at index 2

            chill.addSong(s3);
            chill.addSong(s7);
            chill.addSong(s2);
        } catch (SongAlreadyExistsException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        workout.printPlaylist();
        chill.printPlaylist();

        try {
            workout.addSong(s1); // already in workout
        } catch (SongAlreadyExistsException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         songId=" + e.getSongId()
                               + ", playlist='" + e.getPlaylistName() + "'");
        }

        try {
            chill.addSong(s3);
        } catch (SongAlreadyExistsException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        workout.playNext();
        workout.playNext();
        workout.playSong("S005"); 

        workout.printPlaylist(); 
        workout.printHistory();   

 
    
        Song next = workout.peekNext();
        if (next != null)
            System.out.println("[peek next] " + next.getTitle() + " – " + next.getArtist());

        Song last = workout.peekLastPlayed();
        if (last != null)
            System.out.println("[peek last played] " + last.getTitle() + " – " + last.getArtist());

        workout.undoLastPlay();   
        workout.printPlaylist();
        workout.printHistory();

     
        workout.removeSong("S002");
        workout.removeSong("S999"); 

        workout.printPlaylist();

        while (!workout.isEmpty()) workout.playNext();
        workout.playNext(); 

        Playlist fresh = manager.createPlaylist("Fresh");
        fresh.undoLastPlay();
     
        manager.printAllPlaylists();
    }
}