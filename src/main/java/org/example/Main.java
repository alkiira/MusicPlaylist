package org.example;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        org.example.CRUDOperations crudOperations = new org.example.CRUDOperations();

        System.out.println("Welcome to the Music Playlist Manager!");
        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Add a song");
            System.out.println("2. Update a song");
            System.out.println("3. Delete a song");
            System.out.println("4. List all songs");
            System.out.println("5. Login");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter song details:");
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Album: ");
                    String album = scanner.nextLine();
                    System.out.print("Duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    Song newSong = new Song(0, title, artist, album, duration);
                    crudOperations.createSong(newSong);
                    System.out.println("Song added successfully!");
                    break;
                case 2:
                    System.out.print("Enter the ID of the song to update: ");
                    int songIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Song songToUpdate = crudOperations.readSong(songIdToUpdate);
                    if (songToUpdate != null) {
                        System.out.println("Enter new details for the song:");
                        System.out.print("Title (Enter to keep current: " + songToUpdate.getTitle() + "): ");
                        String updatedTitle = scanner.nextLine();
                        if (!updatedTitle.isEmpty()) {
                            songToUpdate.setTitle(updatedTitle);
                        }
                        System.out.print("Artist (Enter to keep current: " + songToUpdate.getArtist() + "): ");
                        String updatedArtist = scanner.nextLine();
                        if (!updatedArtist.isEmpty()) {
                            songToUpdate.setArtist(updatedArtist);
                        }
                        System.out.print("Album (Enter to keep current: " + songToUpdate.getAlbum() + "): ");
                        String updatedAlbum = scanner.nextLine();
                        if (!updatedAlbum.isEmpty()) {
                            songToUpdate.setAlbum(updatedAlbum);
                        }
                        System.out.print("Duration in seconds (Enter to keep current: " + songToUpdate.getDuration() + "): ");
                        String durationInput = scanner.nextLine();
                        if (!durationInput.isEmpty()) {
                            songToUpdate.setDuration(Integer.parseInt(durationInput));
                        }
                        crudOperations.updateSong(songToUpdate);
                        System.out.println("Song updated successfully!");
                    } else {
                        System.out.println("Song not found with ID: " + songIdToUpdate);
                    }
                    break;
                case 3:
                    System.out.print("Enter the ID of the song to delete: ");
                    int songIdToDelete = scanner.nextInt();
                    scanner.nextLine();
                    crudOperations.deleteSong(songIdToDelete);
                    System.out.println("Song deleted successfully!");
                    break;
                case 4:
                    List<Song> allSongs = CRUDOperations.getAllSongs();

                    if (!allSongs.isEmpty()) {
                        System.out.println("Listing all songs:");
                        for (Song song : allSongs) {
                            System.out.println("Title: " + song.getTitle());
                            System.out.println("Artist: " + song.getArtist());
                            System.out.println("Album: " + song.getAlbum());
                            System.out.println("Duration: " + song.getDuration());
                            System.out.println();
                        }
                    } else {
                        System.out.println("No songs found in the database.");
                    }
                    break;
                case 5:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (crudOperations.login(username, password)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

}